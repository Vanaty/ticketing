package mg.itu.service;

import java.util.List;

import mg.itu.dao.PromotionDAO;
import mg.itu.entity.Promotion;
import mg.itu.entity.Reservation;

public class PromotionService {

    public Reservation appliquerPromotion(Reservation res) {
        List<Promotion> prs = PromotionDAO.findAllByIdVol(res.getVol().getId());
        for (Promotion p : prs) {
            if (p.isPromotionActive(res.getDaty().toLocalDate())) {
                res.setPrixTotal(res.getPrixTotal() * (1 - p.getPourcentageReduction() / 100));
                res.setNbrPlaces(res.getNbrPlaces() - p.getNbrSiege());
                res.setNbrPlacesAnnuller(p.getNbrSiege());
                return res;
            }
        }
        return res;
    }
    
}
