package mg.itu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import mg.itu.entity.PricingRule;
import mg.itu.entity.Siege;
import mg.itu.util.JpaConfiguration;

public class PricingRuleDAO {
    private static final EntityManagerFactory emf = JpaConfiguration.createEntityManagerFactory();
        
    public static PricingRule findById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(PricingRule.class, id);
        } finally {
            em.close();
        }
    }

    public static List<PricingRule> findAll() {
        EntityManager em = emf.createEntityManager();
        try{
            return em.createQuery("SELECT s FROM PricingRule s",PricingRule.class).getResultList();
        } finally{
            em.close();
        }
    }
}
