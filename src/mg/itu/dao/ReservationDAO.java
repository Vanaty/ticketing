package mg.itu.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import mg.itu.entity.Config;
import mg.itu.entity.Reservation;
import mg.itu.util.JpaConfiguration;

public class ReservationDAO {
    private static final EntityManagerFactory emf = JpaConfiguration.createEntityManagerFactory();
    
    public static void insertOrUpdateConfig(Config conf) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            if (conf.getId() != null) {
                em.merge(conf);
            } else {
                em.persist(conf);
            }
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public static Config getConfig() {
        EntityManager em = emf.createEntityManager();
        StringBuilder query = new StringBuilder("SELECT v FROM Config v");
        Query q = em.createQuery(query.toString(),Config.class);
        try {
            return (Config) q.getResultList().get(0);
        } catch(IndexOutOfBoundsException e) {
            return Config.preparerDefault();
        } finally {
            em.close();
        }
    }

    public static Reservation save(Reservation reservation) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            if (reservation.getId() != null) {
                reservation = em.merge(reservation); // Use merge for detached entities
            } else {
                em.persist(reservation);
            }
            et.commit();
            return reservation;
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public static Reservation findById(Integer id) {
        EntityManager em = emf.createEntityManager();
        StringBuilder query = new StringBuilder("SELECT v FROM Reservation v WHERE v.id = :id");
        Query q = em.createQuery(query.toString(),Reservation.class);
        q.setParameter("id", id);
        try {
            return (Reservation) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public static Reservation findByIdWithBatchReservationDetail(Integer id) {
        EntityManager em = emf.createEntityManager();
        StringBuilder query = new StringBuilder("SELECT v FROM Reservation v LEFT JOIN FETCH v.details WHERE v.id = :id");
        Query q = em.createQuery(query.toString(),Reservation.class);
        q.setParameter("id", id);
        try {
            return (Reservation) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public static List<Reservation> findAll(String datyMin, String datyMax) {
        EntityManager em = emf.createEntityManager();
        StringBuilder query = new StringBuilder("SELECT v FROM Reservation v WHERE 1=1 ");
        if (datyMin != null && !datyMin.isEmpty()) {
            query.append(" AND v.daty >= :datyMin");
        }
        if (datyMax != null && !datyMax.isEmpty()) {
            query.append(" AND v.daty <= :datyMax");
        }
        query.append(" ORDER BY v.daty DESC");
        Query q = em.createQuery(query.toString(),Reservation.class);
        if (datyMin != null && !datyMin.isEmpty()) {
            q.setParameter("datyMin", LocalDateTime.of(LocalDate.parse(datyMin), LocalTime.MIN));
        }
        if(datyMax != null && !datyMax.isEmpty()) {
            q.setParameter("datyMax", LocalDateTime.of(LocalDate.parse(datyMax), LocalTime.MIN));
        }
        try {
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
