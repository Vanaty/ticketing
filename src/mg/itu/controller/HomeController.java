package mg.itu.controller;

import java.time.LocalDate;

import mg.itu.annotation.Controleur;
import mg.itu.annotation.GET;
import mg.itu.annotation.Param;
import mg.itu.annotation.Security;
import mg.itu.annotation.Url;
import mg.itu.controleur.ModelView;
import mg.itu.dao.AvionDAO;
import mg.itu.dao.VilleDessevieDAO;
import mg.itu.dao.VolDAO;
import mg.itu.type.VolSearch;

@Controleur
public class HomeController {
    @GET
    @Url("/admin")
    @Security(levelUser = 10, errorPage = "/auth/login")
    public ModelView admin() {
        return new ModelView("/vols");
    }

    @GET
    @Url("/")
    public ModelView home(@Param(value = "vol",ignorValidation = true) VolSearch vs) throws Exception {
        ModelView mv = new ModelView("/front/vols.jsp");
        mv.addObject("vols", VolDAO.findAll(vs));
        mv.addObject("avions", AvionDAO.findAll());
        mv.addObject("villes", VilleDessevieDAO.findAll());
        return mv;
    }
}
