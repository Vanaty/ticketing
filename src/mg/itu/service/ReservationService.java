package mg.itu.service;

import mg.itu.dao.ReservationDAO;
import mg.itu.entity.Reservation;
import mg.itu.entity.ReservationDetail;
import mg.itu.entity.Status;

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
        if (reservation.getStatus() == null) {
            reservation.setStatus(Status.CANCELLED);
        } else if (reservation.getStatus().equals(Status.PENDING)) {
            reservation.setStatus(Status.CANCELLED);
        } else {
            throw new Exception("Reservation already cancelled or confirmed");
        }
        return ReservationDAO.save(reservation);
    }
}
