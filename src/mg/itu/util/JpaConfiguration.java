package mg.itu.util;

import java.time.LocalDate;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import mg.itu.dao.VolDAO;
import mg.itu.entity.Avion;
import mg.itu.entity.Config;
import mg.itu.entity.PricingRule;
import mg.itu.entity.PrixVol;
import mg.itu.entity.Promotion;
import mg.itu.entity.Reservation;
import mg.itu.entity.ReservationDetail;
import mg.itu.entity.Siege;
import mg.itu.entity.TypeSiege;
import mg.itu.entity.Utilisateur;
import mg.itu.entity.VilleDesservie;
import mg.itu.entity.Vol;
import mg.itu.entity.VolSummary;

public class JpaConfiguration {
    static EntityManagerFactory emf;
    static EntityManager em;
    public static EntityManagerFactory createEntityManagerFactory() {
        if (emf != null && emf.isOpen()) return emf;
        // Créer la map pour les propriétés
        Properties properties = new Properties();
        // Définir les propriétés nécessaires pour la connexion à la base de données et la configuration JPA
        properties.put(AvailableSettings.URL, "jdbc:postgresql://localhost:5432/ticketing");
        properties.put(AvailableSettings.USER, "postgres");
        properties.put(AvailableSettings.PASS, "admin");
        properties.put(AvailableSettings.DRIVER, "org.postgresql.Driver");
        
        // Définir d'autres propriétés spécifiques à Hibernate (ou autre provider JPA)
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgresPlusDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        Configuration hbConfiguration = new Configuration();
        hbConfiguration.setProperties(properties);
        
        hbConfiguration.addAnnotatedClass(Avion.class);
        hbConfiguration.addAnnotatedClass(PrixVol.class);
        hbConfiguration.addAnnotatedClass(Siege.class);
        hbConfiguration.addAnnotatedClass(TypeSiege.class);
        hbConfiguration.addAnnotatedClass(VilleDesservie.class);
        hbConfiguration.addAnnotatedClass(Vol.class);
        hbConfiguration.addAnnotatedClass(Config.class);
        hbConfiguration.addAnnotatedClass(Promotion.class);
        hbConfiguration.addAnnotatedClass(Utilisateur.class);
        hbConfiguration.addAnnotatedClass(Reservation.class);
        hbConfiguration.addAnnotatedClass(ReservationDetail.class);
        hbConfiguration.addAnnotatedClass(PricingRule.class);
        hbConfiguration.addAnnotatedClass(VolSummary.class);
        
        SessionFactory sessionFactory = hbConfiguration.buildSessionFactory();
        return sessionFactory.unwrap(EntityManagerFactory.class);
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        VolSummary vol;
        VolDAO.findByIdWithBatchPromotion(6);
        // Utiliser l'EntityManagerFactory pour créer des EntityManagers, etc.
        System.out.println("EntityManagerFactory créé : " + emf);
        em.close();
        emf.close();
        // System.out.println(vol.getPrixTotal());
        //TODO Fermer l'EMF lorsque l'application est terminée
        // System.out.println((char)('a'+4));
    }

    public static EntityManager createEntityManager(EntityManagerFactory emf2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createEntityManager'");
    }

    public static EntityManager createEntityManager() {
        if (em!= null && em.isOpen()) return em;
        em =  createEntityManagerFactory().createEntityManager();
        return em;
    }
}

