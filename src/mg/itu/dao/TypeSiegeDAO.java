package mg.itu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import mg.itu.util.JpaConfiguration;

import mg.itu.entity.TypeSiege;
import mg.itu.entity.Vol;

public class TypeSiegeDAO {
    private static final EntityManagerFactory emf = JpaConfiguration.createEntityManagerFactory();
    public static List<TypeSiege> findAll() {
        EntityManager em = emf.createEntityManager();
        try{
            return em.createQuery("SELECT ts FROM TypeSiege ts",TypeSiege.class).getResultList();
        } finally {
            em.close();
        }
    }
    public static TypeSiege findByid(Integer id) {
        EntityManager em = emf.createEntityManager();
        StringBuilder query = new StringBuilder("SELECT v FROM TypeSiege v WHERE v.id = :id");
        Query q = em.createQuery(query.toString(),TypeSiege.class);
        q.setParameter("id", id);
        try {
            return (TypeSiege) q.getSingleResult();
        } finally {
            em.close();
        }
    }
}
