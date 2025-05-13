package mg.itu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import mg.itu.entity.Utilisateur;
import mg.itu.util.JpaConfiguration;

public class AuthDAO {
    private static final EntityManagerFactory emf = JpaConfiguration.createEntityManagerFactory();
    
    public static Utilisateur findByUP(Utilisateur u) throws Exception {
        EntityManager em = emf.createEntityManager();
        StringBuilder sb = new StringBuilder("SELECT u FROM Utilisateur u WHERE u.password = :pass AND u.nom = :nom ");
        try {
            Query query =  em.createQuery(sb.toString());
            query.setParameter("nom", u.getNom());
            query.setParameter("pass", u.getPassword());
            return (Utilisateur) query.getResultList().get(0);
        } catch(IndexOutOfBoundsException ee) {
            throw new Exception("Mots de passe ou Nom invalide");
        } finally {
            em.close();
        }
    }
}
