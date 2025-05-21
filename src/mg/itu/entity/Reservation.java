package mg.itu.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import mg.itu.dao.VolDAO;
import mg.itu.validation.NotNull;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_vol")
    private Vol vol;

    private Integer nbrPlaces;
    private Integer nbrPlacesAnnuller = 0;
    private Double prixTotal;
    
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationDetail> details = new ArrayList<>();
    
    @NotNull
    private LocalDateTime daty;

    // Field Form
    @Transient
    private Integer idVol;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    public Integer getId() {
        return id;
    }

    public Integer getNbrPlacesAnnuller() {
        return nbrPlacesAnnuller;
    }

    public void setNbrPlacesAnnuller(Integer nbrPlacesAnnuller) {
        this.nbrPlacesAnnuller = nbrPlacesAnnuller;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vol getVol() {
        if (vol == null && idVol != null) {
            vol = VolDAO.findById(idVol);
        }
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public Integer getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(Integer nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public List<ReservationDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ReservationDetail> details) {
        this.details = details;
    }

    public LocalDateTime getDaty() {
        return daty;
    }

    public void setDaty(LocalDateTime daty) {
        this.daty = daty;
    }

    public Integer getIdVol() {
        return idVol;
    }

    public void setIdVol(Integer idVol) {
        this.setVol(VolDAO.findById(idVol));
        this.idVol = idVol;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
