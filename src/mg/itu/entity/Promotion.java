package mg.itu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import mg.itu.validation.NotNull;
import mg.itu.validation.Range;

import java.time.LocalDate;

@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_vol")
    private Vol vol;

    @Transient
    @NotNull
    private Integer idVol;

    @Range(min = 0, max = 100)
    private double pourcentageReduction;

    @Range(min = 1)
    private int nbrSiege;

    @Transient
    private int nbrSReserve = 0;

    @NotNull
    private LocalDate dateDebut;
    
    @NotNull
    private LocalDate dateFin;
    private boolean active;


    public boolean isPromotionActive(LocalDate date) {
        return (date.isAfter(dateDebut) || date.isEqual(dateDebut)) && (date.isBefore(dateFin) || date.isEqual(dateFin)) && active && getNbrSiegeDispo() > 0;
    }

    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Vol getVol() {
        return vol;
    }
    public void setVol(Vol vol) {
        this.vol = vol;
    }
    public double getPourcentageReduction() {
        return pourcentageReduction;
    }
    public void setPourcentageReduction(double pourcentageReduction) {
        this.pourcentageReduction = pourcentageReduction;
    }
    public LocalDate getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }
    public LocalDate getDateFin() {
        return dateFin;
    }
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public Integer getIdVol() {
        return idVol;
    }

    public void setIdVol(Integer idVol) {
        this.idVol = idVol;
        Vol vol = new Vol();
        vol.setId(idVol);
        this.setVol(vol);
    }

    public int getNbrSiege() {
        return nbrSiege;
    }
    public void setNbrSiege(int nbrSiege) {
        this.nbrSiege = nbrSiege;
    }


    public int getNbrSReserve() {
        return nbrSReserve;
    }


    public void setNbrSReserve(int nbrSReserve) {
        this.nbrSReserve = nbrSReserve;
    }
    
    public int getNbrSiegeDispo() {
        return nbrSiege - nbrSReserve;
    }
}

