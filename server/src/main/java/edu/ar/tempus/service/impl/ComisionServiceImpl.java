package edu.ar.tempus.service.impl;

import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.neo4J.entity.ComisionNeo4J;
import edu.ar.tempus.persistence.repository.ComisionRepository;
import edu.ar.tempus.persistence.sql.ComisionDAOSQL;
import edu.ar.tempus.persistence.sql.MateriaSQLDAO;
import edu.ar.tempus.service.ComisionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ComisionServiceImpl implements ComisionService {

    private final ComisionRepository comisionRepository;

    private final MateriaSQLDAO materiaSQLDAO;


    public ComisionServiceImpl(ComisionRepository comisionRepository, MateriaSQLDAO materiaSQLDAO) {
        this.comisionRepository = comisionRepository;
        this.materiaSQLDAO = materiaSQLDAO;
    }

    @Override
    public Comision guardar(Comision comision, Long materiaId) {
        Materia materia = materiaSQLDAO.findById(materiaId).orElseThrow(() -> new EntityNotFoundException(Materia.class.getName(), materiaId));

        comision.setMateria(materia);
        materia.agregarComision(comision);

        return comisionRepository.guardar(comision);
    }

    @Override
    public Comision recuperar(Long comisionId) {
        return comisionRepository.recuperar(comisionId);
    }

    @Override
    public List<Comision> encontrarIdsUnaCombinacionCompatible(List<Long> materiasIds) {

        List<ComisionNeo4J> candidatas = comisionRepository.cargarCandidatas(materiasIds);

        Map<Long, List<Long>> porMateria = candidatas.stream()
                .collect(Collectors.groupingBy(
                        c -> c.getMateria().getId(),
                        Collectors.mapping(ComisionNeo4J::getId, Collectors.toList())
                ));

        List<Long> todasLasIds = candidatas.stream().map(ComisionNeo4J::getId).toList();
        Set<String> compatibles = new HashSet<>(comisionRepository.cargarParesCompatibles(todasLasIds));

        List<Long> ids = backtrack(new ArrayList<>(porMateria.values()), new ArrayList<>(), compatibles);

        if (ids == null) return List.of();
        return comisionRepository.encontrarPorIds(ids);
    }

    private List<Long> backtrack(List<List<Long>> grupos,
                                 List<Long> seleccion,
                                 Set<String> compatibles) {
        if (seleccion.size() == grupos.size()) return new ArrayList<>(seleccion);

        for (Long candidato : grupos.get(seleccion.size())) {
            boolean esCompatible = seleccion.stream()
                    .allMatch(ya -> compatibles.contains(clavePareja(ya, candidato)));

            if (esCompatible) {
                seleccion.add(candidato);
                List<Long> resultado = backtrack(grupos, seleccion, compatibles);
                if (resultado != null) return resultado;
                seleccion.remove(seleccion.size() - 1);
            }
        }
        return null;
    }

    private String clavePareja(Long a, Long b) {
        return Math.min(a, b) + "-" + Math.max(a, b);
    }

}
