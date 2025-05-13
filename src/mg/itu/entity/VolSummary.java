package mg.itu.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import mg.itu.validation.NotNull;

@Table(name = "vol_summary")
@Entity
public class VolSummary {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_avion")
    Avion avion;
    @ManyToOne
    @JoinColumn(name = "id_ville_dep")
    VilleDesservie villeDepart;
    @ManyToOne
    @JoinColumn(name = "id_ville_arr")
    VilleDesservie villeArrive;
    @Column(name = "datedepart")
    LocalDate dtDepart;
    @Column(name = "heuredepart")
    LocalTime heureDepart;
    LocalTime duree;
    private Integer nbrSiege;
    private Integer nbrSiegeReservee;
    private Integer nbrSiegeLibre;
    private Integer prixTotal;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public VilleDesservie getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(VilleDesservie villeDepart) {
        this.villeDepart = villeDepart;
    }

    public VilleDesservie getVilleArrive() {
        return villeArrive;
    }

    public void setVilleArrive(VilleDesservie villeArrive) {
        this.villeArrive = villeArrive;
    }

    public LocalDate getDtDepart() {
        return dtDepart;
    }

    public void setDtDepart(LocalDate dtDepart) {
        this.dtDepart = dtDepart;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public LocalTime getDuree() {
        return duree;
    }

    public void setDuree(LocalTime duree) {
        this.duree = duree;
    }

    public Integer getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Integer prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Integer getNbrSiege() {
        return nbrSiege;
    }

    public void setNbrSiege(Integer nbrSiege) {
        this.nbrSiege = nbrSiege;
    }

    public Integer getNbrSiegeReservee() {
        return nbrSiegeReservee;
    }

    public void setNbrSiegeReservee(Integer nbrSiegeReservee) {
        this.nbrSiegeReservee = nbrSiegeReservee;
    }

    public Integer getNbrSiegeLibre() {
        return nbrSiegeLibre;
    }

    public void setNbrSiegeLibre(Integer nbrSiegeLibre) {
        this.nbrSiegeLibre = nbrSiegeLibre;
    }
    
}
