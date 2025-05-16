package mg.itu.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import mg.itu.dao.VolDAO;
import mg.itu.validation.NotNull;

@Entity
public class Vol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "id_avion")
    Avion avion;
    
    @NotNull
    @Transient
    Integer idAvion;

    @ManyToOne
    @JoinColumn(name = "id_ville_dep")
    VilleDesservie villeDepart;

    @NotNull
    @Transient
    Integer idVilleDepart;

    @ManyToOne
    @JoinColumn(name = "id_ville_arr")
    VilleDesservie villeArrive;

    @NotNull
    @Transient
    Integer idVilleArrive;

    @NotNull
    @Column(name = "datedepart")
    LocalDate dtDepart;

    @NotNull
    @Column(name = "heuredepart")
    LocalTime heureDepart;

    @NotNull
    LocalTime duree;

    @OneToMany(mappedBy = "vol", cascade = CascadeType.ALL)
    List<PrixVol> prixVols = new ArrayList<>();

    @OneToMany(mappedBy = "vol", cascade = CascadeType.ALL)
    List<Promotion> promotions = new ArrayList<>();

    public Boolean isPromotionActive(LocalDate date) {
        return promotions.stream().filter(p -> p.isPromotionActive(date)).findAny().isPresent();
    }

    public Promotion getPromotionActive(LocalDate date) {
        return promotions.stream().filter(p -> p.isPromotionActive(date)).findFirst().orElse(new Promotion());
    }

    public void setPromotions(List<Promotion> ps) {
        this.promotions = ps;
    }

    public PrixVol getPrixVol(TypeSiege typeSiege) {
        return prixVols.stream()
        .filter(p -> p.getTypeSiege().getId().intValue() == typeSiege.getId().intValue())
        .findFirst()
        .orElse(new PrixVol());
    }

    public Double getPrixFinal(TypeSiege typeSiege,PricingRule pr, LocalDate date) {
        Vol volP = VolDAO.findByIdWithBatchPromotion(getId());
        Promotion p = volP.getPromotionActive(date);
        if (p.getId() != null) {
            return getPrixVol(typeSiege).getPrixApresPromotion(p) * (1 - pr.getDiscountPercentage()/100);
        } else {
            return getPrixVol(typeSiege).getPrix() * (1 - pr.getDiscountPercentage()/100);
        }
    }

    public Integer getIdVilleDepart() {
        return idVilleDepart;
    }

    public LocalTime getDuree() {
        return duree;
    }

    public void setDuree(LocalTime duree) {
        this.duree = duree;
    }

    public void setIdVilleDepart(Integer idVilleDepart) {
        this.idVilleDepart = idVilleDepart;
        VilleDesservie v = new VilleDesservie();
        v.setId(idVilleDepart);
        setVilleDepart(v);
    }

    public Integer getIdVilleArrive() {
        return idVilleArrive;
    }

    public void setIdVilleArrive(Integer idVilleArrive) {
        this.idVilleArrive = idVilleArrive;
        VilleDesservie v = new VilleDesservie();
        v.setId(idVilleArrive);
        setVilleArrive(v);
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

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

    public Integer getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(Integer idAvion) {
        this.idAvion = idAvion;
        Avion a = new Avion();
        a.setId(idAvion);
        this.setAvion(a);
    }

    public List<PrixVol> getPrixVols() {
        return prixVols;
    }

    public void setPrixVols(List<PrixVol> prixVols) {
        this.prixVols = prixVols;
    }    
}