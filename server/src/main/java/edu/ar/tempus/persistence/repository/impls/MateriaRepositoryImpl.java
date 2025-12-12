package edu.ar.tempus.persistence.repository.impls;

import edu.ar.tempus.exceptions.EntityNotFoundException;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.repository.MateriaRepository;
import edu.ar.tempus.persistence.sql.MateriaSQLDAO;
import org.springframework.stereotype.Component;

@Component
public class MateriaRepositoryImpl implements MateriaRepository {

    private final MateriaSQLDAO materiaSQLDAO;

    public MateriaRepositoryImpl(MateriaSQLDAO materiaSQLDAO) {
        this.materiaSQLDAO = materiaSQLDAO;
    }

    @Override
    public Materia save(Materia materia) {
        return materiaSQLDAO.save(materia);
    }

    @Override
    public Materia getById(Long materiaId) {
        return materiaSQLDAO.findById(materiaId).orElseThrow(() -> new EntityNotFoundException(Materia.class.getName(), materiaId));
    }
}
