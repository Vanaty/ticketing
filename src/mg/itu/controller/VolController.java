package mg.itu.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import mg.itu.annotation.Controleur;
import mg.itu.annotation.GET;
import mg.itu.annotation.POST;
import mg.itu.annotation.Param;
import mg.itu.annotation.Security;
import mg.itu.annotation.Url;
import mg.itu.controleur.ModelView;
import mg.itu.dao.AvionDAO;
import mg.itu.dao.TypeSiegeDAO;
import mg.itu.dao.VilleDessevieDAO;
import mg.itu.dao.VolDAO;
import mg.itu.entity.PrixVol;
import mg.itu.entity.TypeSiege;
import mg.itu.entity.Vol;
import mg.itu.type.VolSearch;

@Controleur(path = "/vols")
@Security(levelUser = 10, errorPage = "/auth/login")
public class VolController {
    @GET
    @Url("/insertion")
    public ModelView index() throws Exception {
        ModelView mv =new ModelView("/vols/vol.jsp");
        mv.addObject("page", "Vol");
        mv.addObject("title", "Insetion Vol");

        mv.setCallbackValidation("/vols/insertion");
        mv.addObject("avions",  AvionDAO.findAll());
        mv.addObject("typeSieges",  TypeSiegeDAO.findAll());
        mv.addObject("villes",  VilleDessevieDAO.findAll());
        return mv;
    }

    @POST
    @Url("/")
    public ModelView create(@Param("vol") Vol vol,@Param("idTS[]") String[] idTS, @Param("prixs[]") String[] prixs) throws Exception {
        List<PrixVol> prixVols = new ArrayList<>();
        for (int i = 0; i < idTS.length; i++) {
            PrixVol pv = new PrixVol();
            TypeSiege ts = new TypeSiege(Integer.parseInt(idTS[i]));
            pv.setVol(vol);
            pv.setDaty(LocalDateTime.now());
            pv.setTypeSiege(ts);
            pv.setPrix(Double.parseDouble(prixs[i]));
            prixVols.add(pv);
        }
        vol.setPrixVols(prixVols);
        VolDAO.insert(vol);
        ModelView mv =new ModelView("/vols","GET");
        return mv;
    }

    @GET
    @Url("/delete")
    public ModelView delete(@Param("id") Integer id) throws Exception {
        ModelView mv =new ModelView("/vols");
        VolDAO.delete(id);
        return mv;
    }

    @POST
    @Url("/update")
    public ModelView update(@Param("vol") Vol vol,@Param("idTS[]") String[] idTS, @Param("prixs[]") String[] prixs) {
        ModelView mv = new ModelView("/vols","GET");
        Vol update = VolDAO.findByIdWithBatchPrixVol(vol.getId());
        for (int i = 0; i < idTS.length; i++) {
            TypeSiege ts = new TypeSiege(Integer.parseInt(idTS[i]));
            PrixVol pv = update.getPrixVol(ts);
            pv.setDaty(LocalDateTime.now());
            pv.setPrix(Double.parseDouble(prixs[i]));
        }
        update.setAvion(vol.getAvion());
        update.setDtDepart(vol.getDtDepart());
        update.setDuree(vol.getDuree());
        update.setHeureDepart(vol.getHeureDepart());
        update.setVilleArrive(vol.getVilleArrive());
        update.setVilleDepart(vol.getVilleDepart());
        VolDAO.update(update);
        return mv;
    }

    @GET
    @Url("/update")
    public ModelView formUpdate(@Param("id") Integer id) throws Exception {
        ModelView mv =new ModelView("/vols/vol-update.jsp");
        mv.addObject("page", "Vol");
        mv.addObject("title", "Update Vol");

        mv.setCallbackValidation("/vols/update");
        mv.addObject("vol", VolDAO.findByIdWithBatchPrixVol(id));
        mv.addObject("avions",  AvionDAO.findAll());
        mv.addObject("typeSieges",  TypeSiegeDAO.findAll());
        mv.addObject("villes",  VilleDessevieDAO.findAll());
        return mv;
        // return VolDAO.findById(id);
    }

    @GET
    @Url("/")
    public ModelView list(@Param(value = "vol",ignorValidation = true) VolSearch vol,@Param("id") Integer id, @Param("action") String action) throws Exception {
        ModelView mv =new ModelView("/vols/list.jsp");
        List<Vol> vols = null;

        vols = VolDAO.findAll(vol);
        mv.addObject("page", "Vol");
        mv.addObject("title", "List Vol");

        mv.addObject("vols", vols);
        mv.addObject("avions", AvionDAO.findAll());
        mv.addObject("villes", VilleDessevieDAO.findAll());
        return mv;
    }

    @GET
    @Url("/details")
    public ModelView details(@Param("id") Integer id) throws Exception {
        ModelView mv =new ModelView("/vols/detail.jsp");

        mv.addObject("page", "Vol");
        mv.addObject("title", "Detailes");

        mv.addObject("volPromotion", VolDAO.findByIdWithBatchPromotion(id));
        mv.addObject("vol", VolDAO.findByIdWithBatchPrixVol(id));
        return mv;
    }
}
