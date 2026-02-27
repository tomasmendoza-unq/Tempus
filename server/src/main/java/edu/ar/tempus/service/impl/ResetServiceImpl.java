package edu.ar.tempus.service.impl;

import edu.ar.tempus.service.ResetService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.metamodel.EntityType;
import jakarta.transaction.Transactional;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@Transactional
public class ResetServiceImpl implements ResetService {

    @PersistenceContext
    private EntityManager entityManager;

    private final Neo4jClient neo4jClient;

    public ResetServiceImpl(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    @Override
    public void resetAll() {
        resetSQL();
        resetNeo4j();
    }

    private void resetSQL() {
        Set<String> tablasExistentes = obtenerTablasExistentes();

        if (tablasExistentes.isEmpty()) {
            return;
        }

        for (String tableName : tablasExistentes) {
            limpiarTabla(tableName);
        }
    }

    private Set<String> obtenerTablasExistentes() {
        Set<String> tablas = new java.util.HashSet<>();

        try {
            String query = """
                    SELECT table_name 
                    FROM information_schema.tables 
                    WHERE table_schema = 'public' 
                    AND table_type = 'BASE TABLE'
                    """;

            @SuppressWarnings("unchecked")
            java.util.List<String> resultados = entityManager.createNativeQuery(query).getResultList();

            for (String tabla : resultados) {
                tablas.add(tabla);
            }

        } catch (Exception e) {
            // Fallback: usar las entidades JPA
            Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();
            for (EntityType<?> entityType : entityTypes) {
                tablas.add(getTableName(entityType));
            }
        }

        return tablas;
    }

    private void limpiarTabla(String tableName) {
        try {
            String truncateQuery = "TRUNCATE TABLE " + tableName + " CASCADE";
            entityManager.createNativeQuery(truncateQuery).executeUpdate();
        } catch (Exception truncateException) {
            // Si TRUNCATE falla, intentar DELETE
            String deleteQuery = "DELETE FROM " + tableName;
            entityManager.createNativeQuery(deleteQuery).executeUpdate();
        }
    }

    private String getTableName(EntityType<?> entityType) {
        Class<?> entityClass = entityType.getJavaType();

        // Verificar si tiene anotación @Table personalizada
        jakarta.persistence.Table tableAnnotation = entityClass.getAnnotation(jakarta.persistence.Table.class);

        if (tableAnnotation != null && !tableAnnotation.name().isEmpty()) {
            return tableAnnotation.name();
        }

        return entityClass.getSimpleName();
    }

    private void resetNeo4j() {
        neo4jClient.query("MATCH (n) DETACH DELETE n").fetch().all();
    }

}