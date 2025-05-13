package mg.itu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import mg.itu.util.JpaConfiguration;
import mg.itu.entity.Vol;
import mg.itu.entity.VolSummary;
import mg.itu.type.VolSearch;

public class VolDAO {
    private static final EntityManagerFactory emf = JpaConfiguration.createEntityManagerFactory();
    
    public static void insert(Vol vol) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(vol);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public static void update(Vol vol) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(vol);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Vol> findAll(VolSearch vol) {
        EntityManager em = emf.createEntityManager();
        StringBuilder queString = new StringBuilder("SELECT v FROM Vol v WHERE 1=1");
    
        if (vol.getIdAvion() != null) {
            queString.append(" AND v.avion.id = :idAvion");
        }
        if (vol.getIdVilleArrive() != null) {
            queString.append(" AND v.villeArrive.id = :idVilleArrive");
        }
        if (vol.getIdVilleDepart() != null) {
            queString.append(" AND v.villeDepart.id = :idVilleDepart");
        }
        if (vol.getDtDepartMin() != null) {
            queString.append(" AND v.dtDepart >= :dtDepartMin");
        }
        if (vol.getDtDepartMax() != null) {
            queString.append(" AND v.dtDepart <= :dtDepartMax");
        }
        if (vol.getDureeMin() != null) {
            queString.append(" AND v.duree >= :dureeMin");
        }
        if (vol.getDureeMax() != null) {
            queString.append(" AND v.duree <= :dureeMax");
        }
        if (vol.getHeureDepartMin() != null) {
            queString.append(" AND v.heureDepart >= :heureDepartMin");
        }
        if (vol.getHeureDepartMax() != null) {
            queString.append(" AND v.heureDepart <= :heureDepartMax");
        }

        queString.append(" ORDER BY v.dtDepart DESC,v.heureDepart DESC");
    
        try {
            Query query = em.createQuery(queString.toString(), Vol.class);
    
            // Set parameters
            if (vol.getIdAvion() != null) {
                query.setParameter("idAvion", vol.getIdAvion());
            }
            if (vol.getIdVilleArrive() != null) {
                query.setParameter("idVilleArrive", vol.getIdVilleArrive());
            }
            if (vol.getIdVilleDepart() != null) {
                query.setParameter("idVilleDepart", vol.getIdVilleDepart());
            }
            if (vol.getDtDepartMin() != null) {
                query.setParameter("dtDepartMin", vol.getDtDepartMin());
            }
            if (vol.getDtDepartMax() != null) {
                query.setParameter("dtDepartMax", vol.getDtDepartMax());
            }
            if (vol.getDureeMin() != null) {
                query.setParameter("dureeMin", vol.getDureeMin());
            }
            if (vol.getDureeMax() != null) {
                query.setParameter("dureeMax", vol.getDureeMax());
            }
            if (vol.getHeureDepartMin() != null) {
                query.setParameter("heureDepartMin", vol.getHeureDepartMin());
            }
            if (vol.getHeureDepartMax() != null) {
                query.setParameter("heureDepartMax", vol.getHeureDepartMax());
            }
    
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    


    public static void delete(Integer id) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Vol v = em.find(Vol.class, id);
            if (v != null) {
                em.remove(v);
            } else {
                throw new Exception("Vol non trouvé avec l'ID " + id);
            }
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public static Vol findByIdWithBatchPrixVol(Integer id) {
        EntityManager em = emf.createEntityManager();
        StringBuilder query = new StringBuilder("SELECT v FROM Vol v LEFT JOIN FETCH v.prixVols WHERE v.id = :id");
        Query q = em.createQuery(query.toString(),Vol.class);
        q.setParameter("id", id);
        try {
            return (Vol) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public static Vol findById(Integer id) {
        EntityManager em = emf.createEntityManager();
        StringBuilder query = new StringBuilder("SELECT v FROM Vol v WHERE v.id = :id");
        Query q = em.createQuery(query.toString(),Vol.class);
        q.setParameter("id", id);
        try {
            return (Vol) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public static Vol findByIdWithBatchPromotion(Integer id) {
        EntityManager em = emf.createEntityManager();
        StringBuilder query = new StringBuilder("SELECT v FROM Vol v LEFT JOIN FETCH v.promotions WHERE v.id = :id");
        Query q = em.createQuery(query.toString(),Vol.class);
        q.setParameter("id", id);
        try {
            return (Vol) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public static VolSummary findSummaryById(Integer id) {
        EntityManager em = emf.createEntityManager();
        StringBuilder query = new StringBuilder("SELECT v FROM VolSummary v WHERE v.id = :id");
        Query q = em.createQuery(query.toString(),VolSummary.class);
        q.setParameter("id", id);
        try {
            return (VolSummary) q.getSingleResult();
        } finally {
            em.close();
        }
    }
}
