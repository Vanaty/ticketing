package mg.itu.controller;

import java.util.ArrayList;
import java.util.List;

import mg.itu.annotation.Controleur;
import mg.itu.annotation.GET;
import mg.itu.annotation.POST;
import mg.itu.annotation.Param;
import mg.itu.annotation.Restapi;
import mg.itu.annotation.Security;
import mg.itu.annotation.Url;
import mg.itu.controleur.ModelView;
import mg.itu.dao.AvionDAO;
import mg.itu.dao.PricingRuleDAO;
import mg.itu.dao.ReservationDAO;
import mg.itu.dao.TypeSiegeDAO;
import mg.itu.dao.VilleDessevieDAO;
import mg.itu.dao.VolDAO;
import mg.itu.entity.Reservation;
import mg.itu.entity.ReservationDetail;
import mg.itu.service.ReservationService;
import mg.itu.type.VolSearch;

@Controleur(path = "/reservation")
public class ReservationController {
    ReservationService reservationService = new ReservationService();
    
    @GET
    @Url("/")
    @Security(levelUser = 1, errorPage = "/auth/login.jsp")
    public ModelView list(@Param("id") Integer id,
            @Param("datyMin") String datyMin, 
            @Param("datyMax") String datyMax,
            @Param("message") String message) throws Exception {
        ModelView mv = new ModelView("/reservation/reservation-list.jsp");
        List<Reservation> reservations = ReservationDAO.findAll(datyMin, datyMax);
        mv.addObject("reservations", reservations);
        mv.addObject("message", message);
        return mv;
    }

    @GET
    @Url("/creation")
    public ModelView creation(@Param("id") Integer id) throws Exception {
        ModelView mv = new ModelView("/front/form-reservation.jsp");
        mv.addObject("volPromotion", VolDAO.findByIdWithBatchPromotion(id));
        mv.addObject("vol", VolDAO.findByIdWithBatchPrixVol(id));
        mv.addObject("pricingrules", PricingRuleDAO.findAll());
        mv.addObject("typeS", TypeSiegeDAO.findAll());
        return mv;
    }

    @POST
    @Url("/creation")
    public ModelView creation(@Param("res") Reservation res, 
                            @Param("nbrs[]") String[] nbrs, 
                            @Param("idPRs[]") String[] idPRs,
                            @Param("idTypeSiege") Integer idTS) throws Exception {
        List<ReservationDetail> rDts = new ArrayList<>();
        for (int i = 0 ; i< idPRs.length; i++) {
            ReservationDetail rd = new ReservationDetail(res, Integer.parseInt(nbrs[i]), idTS, Integer.parseInt(idPRs[i]));
            rDts.add(rd);
        }
        res.setDetails(rDts);

        
        try {
            res = reservationService.createReservation(res);
        } catch (Exception e) {
            ModelView mv = new ModelView("/reservation/creation?id=" + res.getVol().getId(), "GET");
            mv.addObject("errorMessage", e.getMessage());
            return mv;
        }
        ModelView mv = new ModelView("/front/facture-reservation.jsp");
        mv.addObject("res", res);
        return mv;
    }

    @GET
    @Url("/annuler")
    public ModelView annuler(@Param("id") Integer id) throws Exception {
        Reservation res = ReservationDAO.findById(id);
        String message = "La réservation a été annulée avec succès";
        if (res != null) {
            try {
                reservationService.annuller(res);
            } catch (Exception e) {
                message = "La réservation n'a pas pu être annulée : " + e.getMessage() +"/error";
            }
        } else {
            message = "La réservation n'existe pas/error";
        }
        return new ModelView("/reservation?message=" + message, "GET");
    }

    @GET
    @Url("/facture")
    public ModelView facture(@Param("id") Integer idReservation) throws Exception {
        Reservation res =  ReservationDAO.findByIdWithBatchReservationDetail(idReservation);
        ModelView mv = new ModelView("/front/facture-reservation.jsp");
        mv.addObject("res", res);
        return mv;
    }
}
