package mg.itu.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import mg.itu.dao.ReservationDAO;
import mg.itu.entity.Reservation;
import mg.itu.entity.ReservationDetail;
import mg.itu.entity.Status;
import mg.itu.entity.Vol;

public class ReservationService {

    public Reservation createReservation(Reservation reservation) throws Exception {
        double sumPrix = 0;
        int sumPers = 0;
        for (ReservationDetail rd : reservation.getDetails()) {
            sumPrix += rd.getPrix();
            sumPers += rd.getNbrPersonnes();
        }
        reservation.setNbrPlaces(sumPers);
        reservation.setPrixTotal(sumPrix);
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
        }
        return false;
    }
}
