package ma.projet;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;
import ma.projet.util.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class App {
    
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public static void main(String[] args) {
        try {
            // Initialisation des services
            HommeService hommeService = new HommeService();
            FemmeService femmeService = new FemmeService();
            MariageService mariageService = new MariageService();
            
            // Création des hommes (5 hommes)
            Homme h1 = new Homme("SAID", "Ahmed", sdf.parse("15/05/1980"), "0612345678", "Casablanca");
            Homme h2 = new Homme("ALAMI", "Hassan", sdf.parse("20/08/1975"), "0623456789", "Rabat");
            Homme h3 = new Homme("BENNANI", "Youssef", sdf.parse("10/03/1985"), "0634567890", "Fes");
            Homme h4 = new Homme("IDRISSI", "Karim", sdf.parse("25/11/1978"), "0645678901", "Marrakech");
            Homme h5 = new Homme("TAZI", "Omar", sdf.parse("05/07/1982"), "0656789012", "Tanger");
            
            hommeService.create(h1);
            hommeService.create(h2);
            hommeService.create(h3);
            hommeService.create(h4);
            hommeService.create(h5);
            
            // Création des femmes (10 femmes)
            Femme f1 = new Femme("SAFI", "Fatima", sdf.parse("03/09/1990"), "0661234567", "Casablanca");
            Femme f2 = new Femme("AMAL", "Nadia", sdf.parse("03/09/1995"), "0672345678", "Rabat");
            Femme f3 = new Femme("MAFA", "Amina", sdf.parse("04/11/2000"), "0683456789", "Fes");
            Femme f4 = new Femme("KARIMA", "Salma", sdf.parse("03/09/1989"), "0694567890", "Marrakech");
            Femme f5 = new Femme("LAILA", "Meriem", sdf.parse("15/06/1992"), "0605678901", "Tanger");
            Femme f6 = new Femme("ZAHRA", "Samira", sdf.parse("22/02/1988"), "0616789012", "Agadir");
            Femme f7 = new Femme("HANANE", "Khadija", sdf.parse("30/04/1993"), "0627890123", "Oujda");
            Femme f8 = new Femme("SOUKAINA", "Rachida", sdf.parse("12/12/1991"), "0638901234", "Meknes");
            Femme f9 = new Femme("IMANE", "Latifa", sdf.parse("08/08/1994"), "0649012345", "Kenitra");
            Femme f10 = new Femme("NOUR", "Zineb", sdf.parse("18/01/1996"), "0650123456", "Tetouan");
            
            femmeService.create(f1);
            femmeService.create(f2);
            femmeService.create(f3);
            femmeService.create(f4);
            femmeService.create(f5);
            femmeService.create(f6);
            femmeService.create(f7);
            femmeService.create(f8);
            femmeService.create(f9);
            femmeService.create(f10);
            
            // Création des mariages
            // Mariages en cours (sans date de fin)
            Mariage m1 = new Mariage(sdf.parse("03/09/1990"), null, 4, h1, f1);
            Mariage m2 = new Mariage(sdf.parse("03/09/1995"), null, 2, h2, f2);
            Mariage m3 = new Mariage(sdf.parse("04/11/2000"), null, 3, h3, f3);
            
            // Mariages échus (avec date de fin)
            Mariage m4 = new Mariage(sdf.parse("03/09/1989"), sdf.parse("03/09/1990"), 0, h1, f4);
            Mariage m5 = new Mariage(sdf.parse("15/06/1992"), sdf.parse("20/08/2000"), 1, h4, f5);
            Mariage m6 = new Mariage(sdf.parse("22/02/1988"), sdf.parse("10/05/1995"), 2, h5, f6);
            
            // Mariages pour tester les hommes mariés à 4 femmes
            Mariage m7 = new Mariage(sdf.parse("30/04/1993"), sdf.parse("15/12/2005"), 1, h2, f7);
            Mariage m8 = new Mariage(sdf.parse("12/12/1991"), sdf.parse("25/06/2010"), 0, h2, f8);
            Mariage m9 = new Mariage(sdf.parse("08/08/1994"), null, 1, h2, f9);
            
            // Mariage pour tester femme mariée 2 fois
            Mariage m10 = new Mariage(sdf.parse("18/01/1996"), null, 2, h4, f4);
            
            mariageService.create(m1);
            mariageService.create(m2);
            mariageService.create(m3);
            mariageService.create(m4);
            mariageService.create(m5);
            mariageService.create(m6);
            mariageService.create(m7);
            mariageService.create(m8);
            mariageService.create(m9);
            mariageService.create(m10);
            
            System.out.println("\n========================================");
            System.out.println("PROGRAMME DE TEST - GESTION ETAT CIVIL");
            System.out.println("========================================\n");
            
            // 1. Afficher la liste des femmes
            System.out.println("1. LISTE DES FEMMES:");
            System.out.println("--------------------");
            List<Femme> femmes = femmeService.findAll();
            for (Femme f : femmes) {
                System.out.println("- " + f.getNom() + " " + f.getPrenom() + 
                                 " (Née le: " + sdf.format(f.getDateNaissance()) + ")");
            }
            
            // 2. Afficher la femme la plus âgée
            System.out.println("\n2. FEMME LA PLUS ÂGÉE:");
            System.out.println("----------------------");
            Femme femmePlusAgee = femmeService.getFemmePlusAgee();
            if (femmePlusAgee != null) {
                System.out.println("- " + femmePlusAgee.getNom() + " " + femmePlusAgee.getPrenom() + 
                                 " (Née le: " + sdf.format(femmePlusAgee.getDateNaissance()) + ")");
            }
            
            // 3. Afficher les épouses d'un homme entre deux dates
            System.out.println("\n3. ÉPOUSES DE " + h1.getNom() + " " + h1.getPrenom() + 
                             " ENTRE 01/01/1989 ET 31/12/1991:");
            System.out.println("--------------------------------------------------");
            Date date1 = sdf.parse("01/01/1989");
            Date date2 = sdf.parse("31/12/1991");
            List<Mariage> epouses = hommeService.getEpousesEntreDeuxDates(h1, date1, date2);
            if (epouses != null && !epouses.isEmpty()) {
                for (Mariage m : epouses) {
                    System.out.println("- Femme: " + m.getFemme().getNom() + " " + m.getFemme().getPrenom() + 
                                     " | Date Début: " + sdf.format(m.getDateDebut()) + 
                                     " | Nbr Enfants: " + m.getNbrEnfants());
                }
            } else {
                System.out.println("Aucune épouse trouvée dans cette période.");
            }
            
            // 4. Afficher le nombre d'enfants d'une femme entre deux dates
            System.out.println("\n4. NOMBRE D'ENFANTS DE " + f1.getNom() + " " + f1.getPrenom() + 
                             " ENTRE 01/01/1989 ET 31/12/2000:");
            System.out.println("--------------------------------------------------");
            int nbrEnfants = femmeService.getNombreEnfantsEntreDeuxDates(f1, date1, sdf.parse("31/12/2000"));
            System.out.println("Nombre d'enfants: " + nbrEnfants);
            
            // 5. Afficher les femmes mariées deux fois ou plus
            System.out.println("\n5. FEMMES MARIÉES DEUX FOIS OU PLUS:");
            System.out.println("------------------------------------");
            List<Femme> femmesMariees = femmeService.getFemmesMarieeDeuxFoisOuPlus();
            if (femmesMariees != null && !femmesMariees.isEmpty()) {
                for (Femme f : femmesMariees) {
                    System.out.println("- " + f.getNom() + " " + f.getPrenom());
                }
            } else {
                System.out.println("Aucune femme mariée deux fois ou plus.");
            }
            
            // 6. Afficher le nombre d'hommes mariés à quatre femmes entre deux dates
            System.out.println("\n6. NOMBRE D'HOMMES MARIÉS À 4 FEMMES ENTRE 01/01/1990 ET 31/12/2000:");
            System.out.println("--------------------------------------------------------------------");
            long nbrHommes = hommeService.getNombreHommesMarieQuatreFemmes(
                sdf.parse("01/01/1990"), sdf.parse("31/12/2000"));
            System.out.println("Nombre d'hommes: " + nbrHommes);
            
            // 7. Afficher les mariages d'un homme donné
            System.out.println("\n7. MARIAGES DE " + h2.getNom() + " " + h2.getPrenom() + 
                             " AVEC TOUS LES DÉTAILS:");
            System.out.println("--------------------------------------------------");
            List<Mariage> mariagesHomme = mariageService.getMariagesParHomme(h2);
            if (mariagesHomme != null && !mariagesHomme.isEmpty()) {
                for (Mariage m : mariagesHomme) {
                    System.out.println("\nMariage avec: " + m.getFemme().getNom() + " " + m.getFemme().getPrenom());
                    System.out.println("  - Date Début: " + sdf.format(m.getDateDebut()));
                    System.out.println("  - Date Fin: " + (m.getDateFin() != null ? sdf.format(m.getDateFin()) : "En cours"));
                    System.out.println("  - Nombre d'enfants: " + m.getNbrEnfants());
                    System.out.println("  - Femme née le: " + sdf.format(m.getFemme().getDateNaissance()));
                    System.out.println("  - Adresse femme: " + m.getFemme().getAdresse());
                }
            }
            
            System.out.println("\n========================================");
            System.out.println("FIN DU PROGRAMME DE TEST");
            System.out.println("========================================\n");
            
            // Fermeture de la SessionFactory
            HibernateUtil.shutdown();
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
