package ma.projet.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Mariage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    
    private int nbrEnfants;
    
    @ManyToOne
    @JoinColumn(name = "homme_id")
    private Homme homme;
    
    @ManyToOne
    @JoinColumn(name = "femme_id")
    private Femme femme;
    
    public Mariage() {
    }
    
    public Mariage(Date dateDebut, Date dateFin, int nbrEnfants, Homme homme, Femme femme) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrEnfants = nbrEnfants;
        this.homme = homme;
        this.femme = femme;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getDateDebut() {
        return dateDebut;
    }
    
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    public Date getDateFin() {
        return dateFin;
    }
    
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    public int getNbrEnfants() {
        return nbrEnfants;
    }
    
    public void setNbrEnfants(int nbrEnfants) {
        this.nbrEnfants = nbrEnfants;
    }
    
    public Homme getHomme() {
        return homme;
    }
    
    public void setHomme(Homme homme) {
        this.homme = homme;
    }
    
    public Femme getFemme() {
        return femme;
    }
    
    public void setFemme(Femme femme) {
        this.femme = femme;
    }
}
