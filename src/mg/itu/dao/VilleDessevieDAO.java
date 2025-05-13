package mg.itu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import mg.itu.util.JpaConfiguration;

import mg.itu.entity.VilleDesservie;


public class VilleDessevieDAO {
    private static final EntityManagerFactory emf = JpaConfiguration.createEntityManagerFactory();
    public static List<VilleDesservie> findAll() {
        EntityManager em = emf.createEntityManager();
        try{
            return em.createQuery("SELECT vd FROM VilleDesservie vd",VilleDesservie.class).getResultList();
        } finally {
            em.close();
        }
    }
}
