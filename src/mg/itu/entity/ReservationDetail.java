package mg.itu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import mg.itu.dao.PricingRuleDAO;
import mg.itu.dao.PrixVolDAO;
import mg.itu.dao.ReservationDAO;
import mg.itu.dao.TypeSiegeDAO;
import mg.itu.dao.VolDAO;
import mg.itu.validation.NotNull;

@Entity
public class ReservationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "id_typesiege")
    private TypeSiege typeSiege;

    @ManyToOne
    @JoinColumn(name = "id_pricingrule")
    private PricingRule pricingRule;
    private Double prixNormale;
    private Double pourcRed;
    private Integer nbrPersonnes;
    private Double prix;


    //Field form
    @Transient
    @NotNull
    private Integer idReservation;
    @Transient
    @NotNull
    private Integer idTypeSiege;
    @Transient
    @NotNull
    private Integer idPricingRule;

    
    public ReservationDetail(Reservation reservation,Integer nbrPersonnes, Integer idTypeSiege, Integer idPricingRule) throws Exception {
        this.setIdPricingRule(idPricingRule);
        this.setIdTypeSiege(idTypeSiege);
        this.setReservation(reservation);
        this.setNbrPersonnes(nbrPersonnes);
        this.setAllPrix();
    }

    public ReservationDetail() {

    }

    private void setAllPrix() throws Exception {
        PrixVol pv = PrixVolDAO.findByIdTypeSiegeAndVol(getTypeSiege().getId(), reservation.getIdVol());
        double prix = pv.getPrixApresPromotion(reservation.getDaty().toLocalDate());
        prix = prix * getNbrPersonnes() * (1 - getPricingRule().getDiscountPercentage() / 100);
        setPrixNormale(pv.getPrix() * (1 - getPricingRule().getDiscountPercentage() / 100));
        setPourcRed(VolDAO.findByIdWithBatchPromotion(reservation.getIdVol()).getPromotionActive(reservation.getDaty().toLocalDate()).getPourcentageReduction());
        setPrix(prix);
    }

    public Integer getIdReservation() {
        return idReservation;
    }

    public Integer getNbrPersonnes() {
        return nbrPersonnes;
    }

    public void setNbrPersonnes(Integer nbrPersonnes) {
        this.nbrPersonnes = nbrPersonnes;
    }
    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
        setReservation(ReservationDAO.findById(idReservation));
    }
    public Integer getIdTypeSiege() {
        return idTypeSiege;
    }
    public void setIdTypeSiege(Integer idTypeSiege) {
        this.setTypeSiege(TypeSiegeDAO.findByid(idTypeSiege));
        this.idTypeSiege = idTypeSiege;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Reservation getReservation() {
        return reservation;
    }
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    public TypeSiege getTypeSiege() {
        return typeSiege;
    }
    public void setTypeSiege(TypeSiege typeSiege) {
        this.typeSiege = typeSiege;
    }
    public PricingRule getPricingRule() {
        return pricingRule;
    }
    public void setPricingRule(PricingRule pricingRule) {
        this.pricingRule = pricingRule;
    }
    public Double getPrixNormale() {
        return prixNormale;
    }
    public void setPrixNormale(Double prixNormale) {
        this.prixNormale = prixNormale;
    }
    public Double getPourcRed() {
        return pourcRed;
    }
    public void setPourcRed(Double pourcRed) {
        this.pourcRed = pourcRed;
    }
    public Double getPrix() {
        return prix;
    }
    public void setPrix(Double prix) {
        this.prix = prix;
    }
    public Integer getIdPricingRule() {
        return idPricingRule;
    }
    public void setIdPricingRule(Integer idPricingRule) {
        this.setPricingRule(PricingRuleDAO.findById(idPricingRule));
        this.idPricingRule = idPricingRule;
    }
}

