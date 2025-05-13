package mg.itu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import mg.itu.util.JpaConfiguration;

import mg.itu.entity.Avion;
import mg.itu.entity.Vol;

public class AvionDAO {
    private static final EntityManagerFactory emf = JpaConfiguration.createEntityManagerFactory();

    public static List<Avion> findAll() throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Avion a", Avion.class).getResultList();
        } finally {
            em.close();
        }
    }

    public static Avion findByIdWithBatchSiege(Integer id) {
        EntityManager em = emf.createEntityManager();
        StringBuilder query = new StringBuilder("SELECT v FROM Avion v LEFT JOIN FETCH v.sieges WHERE v.id = :id");
        Query q = em.createQuery(query.toString(),Avion.class);
        q.setParameter("id", id);
        try {
            return (Avion) q.getSingleResult();
        } finally {
            em.close();
        }
    }
}
