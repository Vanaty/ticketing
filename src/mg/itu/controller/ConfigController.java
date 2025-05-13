package mg.itu.controller;

import mg.itu.annotation.Controleur;
import mg.itu.annotation.GET;
import mg.itu.annotation.POST;
import mg.itu.annotation.Param;
import mg.itu.annotation.Security;
import mg.itu.annotation.Url;
import mg.itu.controleur.ModelView;
import mg.itu.dao.ReservationDAO;
import mg.itu.entity.Config;

@Controleur(path = "/reservation")
@Security(levelUser = 10, errorPage = "/auth/login")
public class ConfigController {
    @GET
    @Url("/config")
    public ModelView configuration() {
        ModelView mv = new ModelView("/reservation/config.jsp");
        mv.addObject("page", "Reservation");
        mv.addObject("title", "Configuration");

        mv.addObject("configuration", ReservationDAO.getConfig());
        return mv;
    }

    @POST
    @Url("/config")
    public ModelView saveConfiguration(@Param("res") Config conf) throws Exception {
        ModelView mv = new ModelView("/reservation/config","GET");
        ReservationDAO.insertOrUpdateConfig(conf);
        return mv;
    }
}
