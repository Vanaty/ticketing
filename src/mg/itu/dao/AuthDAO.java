package mg.itu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import mg.itu.entity.Utilisateur;
import mg.itu.util.JpaConfiguration;
import org.mindrot.jbcrypt.BCrypt;

public class AuthDAO {
    private static final EntityManagerFactory emf = JpaConfiguration.createEntityManagerFactory();
    
    public static Utilisateur findByUP(Utilisateur u) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.nom = :nom");
            query.setParameter("nom", u.getNom());
            List<Utilisateur> users = query.getResultList();
            if (users.isEmpty()) {
                throw new Exception("Nom invalide");
            }
            Utilisateur user = users.get(0);
            if (!BCrypt.checkpw(u.getPassword(), user.getPassword())) {
                throw new Exception("Mots de passe invalide");
            }
            return user;
        } finally {
            em.close();
        }
    }
}
