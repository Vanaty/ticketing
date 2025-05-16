package mg.itu.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import mg.itu.dao.ReservationDAO;
import mg.itu.dao.VolDAO;
import mg.itu.entity.Config;
import mg.itu.entity.Reservation;
import mg.itu.entity.ReservationDetail;
import mg.itu.entity.Status;
import mg.itu.entity.Vol;
import mg.itu.entity.VolSummary;

public class ReservationService {

    PromotionService ps = new PromotionService();

    public Reservation createReservation(Reservation reservation) throws Exception {
        ps.appliquerPromotion(reservation);
        
        double sumPrix = 0;
        int sumPers = 0;
        for (ReservationDetail rd : reservation.getDetails()) {
            sumPrix += rd.getPrix();
            sumPers += rd.getNbrPersonnes();
        }
        reservation.setNbrPlaces(sumPers);
        reservation.setPrixTotal(sumPrix);

        VolSummary vs = VolDAO.findSummaryById(reservation.getVol().getId());
        Config conf = ReservationDAO.getConfig();
        LocalDateTime dateReservationFinal = LocalDateTime.of(vs.getDtDepart(),vs.getHeureDepart()).minusHours(conf.getReservationBeforeHours());
        if (vs == null) {
            throw new Exception("Flight not found.");
        }
        if (reservation.getNbrPlaces() > vs.getNbrSiegeLibre()) {
            throw new Exception("Not enough seats available.");
        }
        if (reservation.getDaty().isAfter(dateReservationFinal)) {
            throw new Exception("Reservation time limit exceeded ("+dateReservationFinal.toString()+").");
        }
        return ReservationDAO.save(reservation);
    }

    public Reservation annuller(Reservation reservation) throws Exception {
        if (canBeAnnuler(reservation)) {
            reservation.setStatus(Status.CANCELLED);
        } else {
            throw new Exception("Reservation already cancelled or cannot be cancelled.");
        }
        return ReservationDAO.save(reservation);
    }
    private boolean canBeAnnuler(Reservation reservation) {
        Vol vol = reservation.getVol();
        LocalDateTime dp =LocalDateTime.of(vol.getDtDepart(), vol.getHeureDepart());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateAnnulation = dp.minusHours(ReservationDAO.getConfig().getCancellationBeforeHours());
        if (dateAnnulation.isAfter(now) || dateAnnulation.isEqual(now)) {
            return reservation.getStatus() == null || reservation.getStatus().equals(Status.PENDING);
        } else {
            throw new RuntimeException("Reservation cannot be cancelled after the cancellation period ("+ dateAnnulation.toString() +")./error");
        }
    }
}
