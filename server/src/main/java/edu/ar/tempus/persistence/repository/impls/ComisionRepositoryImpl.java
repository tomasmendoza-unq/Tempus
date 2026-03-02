package edu.ar.tempus.persistence.repository.impls;

import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.persistence.neo4J.ComisionNeo4JDAO;
import edu.ar.tempus.persistence.neo4J.MateriaNeo4JDAO;
import edu.ar.tempus.persistence.neo4J.entity.ComisionNeo4J;
import edu.ar.tempus.persistence.neo4J.entity.ComisionNeo4J;
import edu.ar.tempus.persistence.neo4J.entity.MateriaNeo4J;
import edu.ar.tempus.persistence.repository.ComisionRepository;
import edu.ar.tempus.persistence.repository.mapper.ComisionMapper;
import edu.ar.tempus.persistence.sql.ComisionDAOSQL;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ComisionRepositoryImpl implements ComisionRepository {
    
    private final ComisionMapper comisionMapper;
    private final ComisionNeo4JDAO comisionNeo4JDAO;
    private final ComisionDAOSQL comisionDAOSQL;
    private final MateriaNeo4JDAO materiaNeo4JDAO;
    private final Neo4jClient neo4jClient;

    public ComisionRepositoryImpl(ComisionMapper comisionMapper, ComisionNeo4JDAO comisionNeo4JDAO, ComisionDAOSQL comisionDAOSQL, MateriaNeo4JDAO materiaNeo4JDAO, Neo4jClient neo4jClient) {
        this.comisionMapper = comisionMapper;
        this.comisionNeo4JDAO = comisionNeo4JDAO;
        this.comisionDAOSQL = comisionDAOSQL;
        this.materiaNeo4JDAO = materiaNeo4JDAO;
        this.neo4jClient = neo4jClient;
    }

    @Override
    public Comision guardar(Comision comision) {
        Comision comisionGuardada = comisionDAOSQL.save(comision);

        Long materiaId = comision.getMateria().getMateriaId();

        MateriaNeo4J materiaNeo = materiaNeo4JDAO.findById(materiaId)
                .orElseThrow(() -> new EntityNotFoundException(MateriaNeo4J.class.getName(), materiaId));

        ComisionNeo4J neo = comisionMapper.toNeo4J(comisionGuardada, materiaNeo);
        neo.setMateria(materiaNeo);

        ComisionNeo4J neoGuardado = comisionNeo4JDAO.save(neo);


        comisionNeo4JDAO.vincularCompatibilidadesPorId(neoGuardado.getId());

        return comisionGuardada;

    }

    @Override
    public Comision recuperar(Long comisionId) {

        return comisionDAOSQL.findById(comisionId)
                .orElseThrow(() -> new EntityNotFoundException(Comision.class.getName(), comisionId));
    }


    @Override
    public List<List<Comision>> encontrarCombinacionCompatible(List<Long> materiasIds, Integer cantidadHorarios) {
        String query = buildQuery(materiasIds.size());

        Collection<?> result = neo4jClient.query(query)
                .bind(materiasIds).to("materiasIds")
                .bind(cantidadHorarios).to("cantidadHorarios")
                .fetchAs(List.class)
                .mappedBy((typeSystem, record) -> {
                    List<Long> ids = record.get("combinacion").asList(v -> v.asLong());
                    return this.findAll(ids);
                })
                .all();

        return new ArrayList<>((Collection<List<Comision>>) result);
    }

    @Override
    public List<Comision> findAll(List<Long> comisionesCompatibles) {
        return comisionDAOSQL.findAllById(comisionesCompatibles);
    }

    private String buildQuery(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append("""
        MATCH (m:Materia) WHERE m.id IN $materiasIds
            WITH m ORDER BY size([(m)<-[:PERTENECE_A]-(c:Comision) | c]) ASC
        MATCH (c:Comision)-[:PERTENECE_A]->(m)
            WITH m, collect(c.id) AS comisionesIdxM
            WITH collect(comisionesIdxM) AS grupos
        MATCH (c:Comision)-[:PERTENECE_A]->(m:Materia)
        WHERE m.id IN $materiasIds
            WITH grupos, collect(c.id) AS todasLasIds
        MATCH (c1c:Comision)-[:COMPATIBLE_CON]->(c2c:Comision)
        WHERE c1c.id IN todasLasIds AND c2c.id IN todasLasIds
            AND NOT any(g IN grupos WHERE c1c.id IN g AND c2c.id IN g)
            WITH grupos, collect([c1c.id, c2c.id]) AS compatibles
    """);

        for (int i = 0; i < n; i++) {
            sb.append("UNWIND grupos[").append(i).append("] AS c").append(i).append("\n");
            if (i > 0) {
                sb.append("WITH grupos, compatibles, ");
                sb.append(IntStream.range(0, i + 1).mapToObj(j -> "c" + j).collect(Collectors.joining(", "))).append("\n");
                sb.append("WHERE ");
                int finalI = i;
                int finalI1 = i;
                sb.append(IntStream.range(0, i)
                        .mapToObj(j -> "([c" + j + ", c" + finalI + "] IN compatibles OR [c" + finalI1 + ", c" + j + "] IN compatibles)")
                        .collect(Collectors.joining(" AND "))).append("\n");
            }
        }

        sb.append("RETURN [");
        sb.append(IntStream.range(0, n).mapToObj(j -> "c" + j).collect(Collectors.joining(", ")));
        sb.append("] AS combinacion LIMIT $cantidadHorarios");

        return sb.toString();
    }
}
