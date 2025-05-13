package mg.itu.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mg.itu.dao.VolDAO;
@Entity
@Table(name = "prixvol")
public class PrixVol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "id_vol")
    Vol vol;

    @ManyToOne
    @JoinColumn(name = "id_typesiege")
    TypeSiege typeSiege;
    Double prix;
    LocalDateTime daty;

    public Double getPrixApresPromotion(Promotion p) {
        return getPrix() * (1 - p.getPourcentageReduction() / 100);
    }

    public Double getPrixApresPromotion(LocalDate date) {
        Vol volP = VolDAO.findByIdWithBatchPromotion(vol.getId());
        Promotion p = volP.getPromotionActive(date);
        if (p.getId() != null) {
            return getPrixApresPromotion(p);
        } else {
            return getPrix();
        }
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public TypeSiege getTypeSiege() {
        return typeSiege;
    }

    public void setTypeSiege(TypeSiege typeSiege) {
        this.typeSiege = typeSiege;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public LocalDateTime getDaty() {
        return daty;
    }

    public void setDaty(LocalDateTime daty) {
        this.daty = daty;
    }

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }
}
