package mg.itu.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import mg.itu.entity.PrixVol;
import mg.itu.util.JpaConfiguration;

public class PrixVolDAO {
    private static final EntityManagerFactory emf = JpaConfiguration.createEntityManagerFactory();
    
    public static void insert(PrixVol pvol) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(pvol);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public static PrixVol findById(Integer id) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(PrixVol.class, id);
        } finally {
            em.close();
        }
    }
    public static PrixVol findByIdTypeSiegeAndVol(Integer idTypeSiege, Integer idVol) throws Exception {
        EntityManager em = emf.createEntityManager();
        System.out.println(idTypeSiege.toString() + " " + idVol.toString());
        try {
            return em.createQuery("SELECT p FROM PrixVol p WHERE p.typeSiege.id = :id AND p.vol.id= :idVol", PrixVol.class)
                    .setParameter("id", idTypeSiege)
                    .setParameter("idVol", idVol)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public static void update(PrixVol pvol) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(pvol);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
