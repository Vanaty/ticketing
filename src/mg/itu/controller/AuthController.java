package mg.itu.controller;

import mg.itu.annotation.Controleur;
import mg.itu.annotation.GET;
import mg.itu.annotation.POST;
import mg.itu.annotation.Param;
import mg.itu.annotation.Url;
import mg.itu.controleur.ModelView;
import mg.itu.dao.AuthDAO;
import mg.itu.entity.Utilisateur;
import mg.itu.security.handler.SecurityHandler;

@Controleur
public class AuthController {
    @GET
    @Url("/auth/login")
    public ModelView index() {
        ModelView mv = new ModelView("/auth/login.jsp");
        return mv;
    }

    @GET
    @Url("/auth/logout")
    public ModelView logout() {
        SecurityHandler.removeUser();
        ModelView mv = new ModelView("/auth/login");
        return mv;
    }

    @POST
    @Url("/auth/login")
    public ModelView auth(@Param("u") Utilisateur u) {
        Utilisateur user = null;
        try {
            user = AuthDAO.findByUP(u);
        } catch(Exception e) {
            ModelView mv = new ModelView("/auth/login","GET");
            mv.addObject("message", e.getMessage()); 
            return mv;
        }

        SecurityHandler.saveUser(user);
        ModelView mv =new ModelView("/vols","GET");
        return mv;
    }
}
