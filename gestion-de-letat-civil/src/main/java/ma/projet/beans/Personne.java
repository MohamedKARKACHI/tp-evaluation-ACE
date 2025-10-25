package ma.projet.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nom;
    private String prenom;
    
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    
    private String telephone;
    private String adresse;
    
    public Personne() {
    }
    
    public Personne(String nom, String prenom, Date dateNaissance, String telephone, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.adresse = adresse;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public Date getDateNaissance() {
        return dateNaissance;
    }
    
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
