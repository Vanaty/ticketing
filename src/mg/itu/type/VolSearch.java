package mg.itu.type;

import java.time.LocalDate;
import java.time.LocalTime;

import mg.itu.entity.Vol;

public class VolSearch extends Vol {
    LocalDate dtDepartMin;
    LocalDate dtDepartMax;
    LocalTime heureDepartMin;
    LocalTime heureDepartMax;
    LocalTime dureeMin;
    LocalTime dureeMax;
    
    public LocalDate getDtDepartMin() {
        return dtDepartMin;
    }
    public void setDtDepartMin(LocalDate dtDepartMin) {
        this.dtDepartMin = dtDepartMin;
    }
    public LocalDate getDtDepartMax() {
        return dtDepartMax;
    }
    public void setDtDepartMax(LocalDate dtDepartMax) {
        this.dtDepartMax = dtDepartMax;
    }
    public LocalTime getHeureDepartMin() {
        return heureDepartMin;
    }
    public void setHeureDepartMin(LocalTime heureDepartMin) {
        this.heureDepartMin = heureDepartMin;
    }
    public LocalTime getHeureDepartMax() {
        return heureDepartMax;
    }
    public void setHeureDepartMax(LocalTime heureDepartMax) {
        this.heureDepartMax = heureDepartMax;
    }
    public LocalTime getDureeMin() {
        return dureeMin;
    }
    public void setDureeMin(LocalTime dureeMin) {
        this.dureeMin = dureeMin;
    }
    public LocalTime getDureeMax() {
        return dureeMax;
    }
    public void setDureeMax(LocalTime dureeMax) {
        this.dureeMax = dureeMax;
    }

    
}
