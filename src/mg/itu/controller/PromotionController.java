package mg.itu.controller;

import mg.itu.annotation.Controleur;
import mg.itu.annotation.GET;
import mg.itu.annotation.POST;
import mg.itu.annotation.Param;
import mg.itu.annotation.Security;
import mg.itu.annotation.Url;
import mg.itu.controleur.ModelView;
import mg.itu.dao.PromotionDAO;
import mg.itu.dao.TypeSiegeDAO;
import mg.itu.dao.VolDAO;
import mg.itu.entity.Promotion;

@Controleur(path = "/promotion")
@Security(levelUser = 10, errorPage = "/auth/login")
public class PromotionController {
    @GET
    @Url("/config")
    public ModelView configPromotion(@Param("id") Integer id) {
        ModelView mv = new ModelView("/promotion/config.jsp");
        if (id == null) {
            return new ModelView("/vols", "GET");
        }
        mv.addObject("page", "Vol");
        mv.addObject("title", "Programation promotion");

        mv.setCallbackValidation("/promotion/config");
        mv.addObject("vol", VolDAO.findById(id));
        mv.addObject("typeSieges", TypeSiegeDAO.findAll());
        return mv;
    }

    @POST
    @Url("/config")
    public ModelView persistePromotion(@Param("prom") Promotion prom) {
        ModelView mv = new ModelView("/vols/details?id="+prom.getVol().getId(),"GET");
        prom.setActive(true);
        PromotionDAO.insert(prom);
        return mv;
    }
}
