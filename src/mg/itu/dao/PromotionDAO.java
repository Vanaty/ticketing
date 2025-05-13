package mg.itu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import mg.itu.entity.Promotion;
import mg.itu.util.JpaConfiguration;

public class PromotionDAO {
    private static EntityManagerFactory emf = JpaConfiguration.createEntityManagerFactory();

    public static void insert(Promotion p) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(p);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Promotion> findAllByIdVol(Integer idVol) {
        EntityManager em = emf.createEntityManager();
        StringBuilder query = new StringBuilder("SELECT v FROM Promotion v WHERE v.vol.id = :id");
        Query q = em.createQuery(query.toString(),Promotion.class);
        q.setParameter("id", idVol);
        try {
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
