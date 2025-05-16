package mg.itu.service;

import java.util.List;

import mg.itu.dao.PromotionDAO;
import mg.itu.entity.Promotion;
import mg.itu.entity.Reservation;
import mg.itu.entity.ReservationDetail;

public class PromotionService {

    public Reservation appliquerPromotion(Reservation res) {
        List<Promotion> prs = PromotionDAO.findAllByIdVol(res.getVol().getId());
        res.getVol().setPromotions(prs);
        
        for (ReservationDetail rd : res.getDetails()) {
            for (Promotion p : prs) {
                if (p.isPromotionActive(res.getDaty().toLocalDate())) {
                    int resteP = p.getNbrSiegeDispo() - rd.getNbrPersonnes();
                    if (resteP >= 0) {
                        p.setNbrSReserve(p.getNbrSReserve() + rd.getNbrPersonnes());
                        
                        rd.setNbrPlaceEnProm(rd.getNbrPersonnes());
                        rd.setPrix(rd.getPrix() * (1 - p.getPourcentageReduction() / 100));
                    } else {
                        int placeEnProm = Math.abs(resteP);
                        p.setNbrSReserve(p.getNbrSReserve() + placeEnProm);
                        
                        rd.setNbrPlaceEnProm(placeEnProm);
                        double prix = (rd.getPrix() - rd.getPrixNormale() * placeEnProm);
                        prix += rd.getPrixNormale() * placeEnProm * (1 - p.getPourcentageReduction() / 100);
                        rd.setPrix(prix);
                    }
                    rd.setPourcRed(p.getPourcentageReduction());
                    return res;
                }
            }
        }
        return res;
    }
}