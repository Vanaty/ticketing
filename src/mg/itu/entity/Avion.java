package mg.itu.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mg.itu.dao.TypeSiegeDAO;


@Entity
@Table(name = "avion")
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String modele;
    @Column(name =  "date_fabrication")
    LocalDate dateFab;

    @OneToMany(mappedBy = "avion", cascade = CascadeType.ALL)
    private List<Siege> sieges = new ArrayList<>();
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getModele() {
        return modele;
    }
    public void setModele(String modele) {
        this.modele = modele;
    }
    public LocalDate getDateFab() {
        return dateFab;
    }
    public void setDateFab(LocalDate dateFab) {
        this.dateFab = dateFab;
    }
}
