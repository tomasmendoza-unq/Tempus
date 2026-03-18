package edu.ar.tempus.persistence.repository.impls;

import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.neo4J.ComisionNeo4JDAO;
import edu.ar.tempus.persistence.neo4J.MateriaNeo4JDAO;
import edu.ar.tempus.persistence.neo4J.entity.ComisionNeo4J;
import edu.ar.tempus.persistence.neo4J.entity.MateriaNeo4J;
import edu.ar.tempus.persistence.repository.ComisionRepository;
import edu.ar.tempus.persistence.repository.mapper.ComisionMapper;
import edu.ar.tempus.persistence.sql.ComisionDAOSQL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        System.out.println("Guardada en SQL con id: " + comisionGuardada.getComisionId());

        Long materiaId = comision.getMateria().getMateriaId();
        System.out.println("Buscando materia en Neo4j con id: " + materiaId);

        MateriaNeo4J materiaNeo = materiaNeo4JDAO.findById(materiaId)
                .orElseThrow(() -> new EntityNotFoundException(MateriaNeo4J.class.getName(), materiaId));

        System.out.println("Materia encontrada en Neo4j: " + materiaNeo);

        ComisionNeo4J neo = comisionMapper.toNeo4J(comisionGuardada, materiaNeo);
        ComisionNeo4J neoGuardado = comisionNeo4JDAO.save(neo);
        System.out.println("Guardada en Neo4j con id: " + neoGuardado.getId());

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

    @Override
    public boolean haySuperposicionHoraria(List<Long> comisionIds, List<Long> comisionesAnotadas) {
        return comisionNeo4JDAO.haySuperposicionHoraria(comisionIds, comisionesAnotadas);
    }

    @Override
    public boolean hayComisionesDeMismaMateriaEnNuevas(List<Long> comisionIds) {
        return comisionDAOSQL.hayComisionesDeMismaMateriaEnNuevas(comisionIds);
    }

    @Override
    public List<Materia> recuperarMateriasPorComision(List<Long> comisionIds) {
        return comisionDAOSQL.findAllMateriasByIds(comisionIds);
    }

    @Override
    public Page<Comision> recuperarComisiones(Pageable pageable, Long carreraId) {
        return comisionDAOSQL.findAll(pageable, carreraId);
    }

    @Override
    public void delete(Long idComision) {
        Comision comision = comisionDAOSQL.findById(idComision).orElseThrow(() -> new EntityNotFoundException(Comision.class.getName(), idComision));
        ComisionNeo4J comisionNeo4J = comisionNeo4JDAO.findById(idComision).orElseThrow(() -> new EntityNotFoundException(Comision.class.getName(), idComision));

        comisionNeo4JDAO.delete(comisionNeo4J);
        comisionDAOSQL.delete(comision);
    }

    private String buildQuery(int n) {

        StringBuilder sb = new StringBuilder();

        sb.append("""
    MATCH (m:Materia) WHERE m.id IN $materiasIds
        WITH m ORDER BY size([(m)<-[:PERTENECE_A]-(c:Comision) | c]) ASC
    MATCH (c:Comision)-[:PERTENECE_A]->(m)
        WITH m, collect(c.id) AS comisionesIdxM
        WITH collect(comisionesIdxM) AS grupos
    """);

        for (int i = 0; i < n; i++) {

            sb.append("UNWIND grupos[").append(i).append("] AS c").append(i).append("\n");

            if (i > 0) {

                sb.append("WITH grupos, ");
                sb.append(
                        IntStream.range(0, i + 1)
                                .mapToObj(j -> "c" + j)
                                .collect(Collectors.joining(", "))
                ).append("\n");

                sb.append("WHERE ");

                int finalI = i;

                sb.append(
                        IntStream.range(0, i)
                                .mapToObj(j -> """
                            NOT EXISTS {
                                MATCH (cA:Comision)-[:SE_DICTA_EL]->(h1:ClaseHorario),
                                      (cB:Comision)-[:SE_DICTA_EL]->(h2:ClaseHorario)
                                WHERE cA.id = c%d
                                  AND cB.id = c%d
                                  AND h1.dia = h2.dia
                                  AND h1.inicio < h2.fin
                                  AND h1.fin > h2.inicio
                            }
                            """.formatted(j, finalI))
                                .collect(Collectors.joining(" AND "))
                ).append("\n");
            }
        }

        sb.append("RETURN [");
        sb.append(
                IntStream.range(0, n)
                        .mapToObj(j -> "c" + j)
                        .collect(Collectors.joining(", "))
        );
        sb.append("] AS combinacion LIMIT $cantidadHorarios");

        return sb.toString();
    }
}
