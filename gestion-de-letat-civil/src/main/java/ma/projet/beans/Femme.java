package ma.projet.beans;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Femme extends Personne {
    
    @OneToMany(mappedBy = "femme", fetch = FetchType.EAGER)
    private List<Mariage> mariages;
    
    public Femme() {
        super();
    }
    
    public Femme(String nom, String prenom, Date dateNaissance, String telephone, String adresse) {
        super(nom, prenom, dateNaissance, telephone, adresse);
    }
    
    public List<Mariage> getMariages() {
        return mariages;
    }
    
    public void setMariages(List<Mariage> mariages) {
        this.mariages = mariages;
    }
}
