package mg.itu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mg.itu.util.JpaConfiguration;

import mg.itu.entity.Siege;

public class SiegeDAO {
    private static final EntityManagerFactory emf = JpaConfiguration.createEntityManagerFactory();
    public static List<Siege> findAll() {
        EntityManager em = emf.createEntityManager();
        try{
            return em.createQuery("SELECT s FROM Siege s",Siege.class).getResultList();
        } finally{
            em.close();
        }
    }
}
