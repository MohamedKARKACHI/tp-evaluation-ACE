# Guide des Tests

## Tests réalisés par le programme principal

Le programme `App.java` effectue les tests suivants :

### 1. Test de création des entités
- ✅ Création de 5 hommes
- ✅ Création de 10 femmes
- ✅ Création de 10 mariages

### 2. Test de lecture (findAll)
- ✅ Récupération de toutes les femmes
- ✅ Affichage formaté des résultats

### 3. Test de requête avec tri
- ✅ Récupération de la femme la plus âgée (ORDER BY dateNaissance ASC)

### 4. Test de requête avec filtres et dates
- ✅ Épouses d'un homme entre deux dates (WHERE + BETWEEN)

### 5. Test d'agrégation SUM
- ✅ Nombre d'enfants d'une femme entre deux dates (SUM + WHERE + BETWEEN)

### 6. Test de GROUP BY et HAVING
- ✅ Femmes mariées deux fois ou plus (GROUP BY + HAVING COUNT >= 2)

### 7. Test de requête complexe
- ✅ Nombre d'hommes mariés à 4 femmes (GROUP BY + HAVING COUNT = 4)

### 8. Test de jointures
- ✅ Mariages d'un homme avec tous les détails (JOIN Homme, Femme)

## Tests unitaires à implémenter (optionnel)

Si vous souhaitez ajouter des tests JUnit, voici des exemples :

### HommeServiceTest.java

```java
package ma.projet.service;

import ma.projet.beans.Homme;
import ma.projet.util.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class HommeServiceTest {
    
    private HommeService hommeService;
    private SimpleDateFormat sdf;
    
    @Before
    public void setUp() {
        hommeService = new HommeService();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    @Test
    public void testCreateHomme() throws Exception {
        Homme homme = new Homme("TEST", "Jean", 
            sdf.parse("01/01/1980"), "0612345678", "Paris");
        
        boolean result = hommeService.create(homme);
        assertTrue("La création doit réussir", result);
        assertTrue("L'ID doit être généré", homme.getId() > 0);
    }
    
    @Test
    public void testFindById() throws Exception {
        Homme homme = new Homme("TEST", "Pierre", 
            sdf.parse("01/01/1985"), "0623456789", "Lyon");
        hommeService.create(homme);
        
        Homme found = hommeService.findById(homme.getId());
        assertNotNull("L'homme doit être trouvé", found);
        assertEquals("Le nom doit correspondre", "TEST", found.getNom());
    }
    
    @Test
    public void testFindAll() {
        List<Homme> hommes = hommeService.findAll();
        assertNotNull("La liste ne doit pas être null", hommes);
        assertTrue("La liste doit contenir des éléments", hommes.size() > 0);
    }
    
    @Test
    public void testUpdate() throws Exception {
        Homme homme = new Homme("TEST", "Paul", 
            sdf.parse("01/01/1990"), "0634567890", "Marseille");
        hommeService.create(homme);
        
        homme.setTelephone("0699999999");
        boolean result = hommeService.update(homme);
        
        assertTrue("La mise à jour doit réussir", result);
        
        Homme updated = hommeService.findById(homme.getId());
        assertEquals("Le téléphone doit être mis à jour", 
            "0699999999", updated.getTelephone());
    }
    
    @Test
    public void testDelete() throws Exception {
        Homme homme = new Homme("TEST", "Marc", 
            sdf.parse("01/01/1995"), "0645678901", "Toulouse");
        hommeService.create(homme);
        int id = homme.getId();
        
        boolean result = hommeService.delete(homme);
        assertTrue("La suppression doit réussir", result);
        
        Homme deleted = hommeService.findById(id);
        assertNull("L'homme ne doit plus exister", deleted);
    }
    
    @After
    public void tearDown() {
        // Nettoyage si nécessaire
    }
}
```

### FemmeServiceTest.java

```java
package ma.projet.service;

import ma.projet.beans.Femme;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;

public class FemmeServiceTest {
    
    private FemmeService femmeService;
    private SimpleDateFormat sdf;
    
    @Before
    public void setUp() {
        femmeService = new FemmeService();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    @Test
    public void testCreateFemme() throws Exception {
        Femme femme = new Femme("DUPONT", "Marie", 
            sdf.parse("01/01/1990"), "0656789012", "Nice");
        
        boolean result = femmeService.create(femme);
        assertTrue("La création doit réussir", result);
        assertTrue("L'ID doit être généré", femme.getId() > 0);
    }
    
    @Test
    public void testGetFemmePlusAgee() {
        Femme femmePlusAgee = femmeService.getFemmePlusAgee();
        assertNotNull("La femme la plus âgée doit être trouvée", femmePlusAgee);
    }
    
    @Test
    public void testGetFemmesMarieeDeuxFoisOuPlus() {
        List<Femme> femmes = femmeService.getFemmesMarieeDeuxFoisOuPlus();
        assertNotNull("La liste ne doit pas être null", femmes);
    }
}
```

### MariageServiceTest.java

```java
package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;

public class MariageServiceTest {
    
    private MariageService mariageService;
    private HommeService hommeService;
    private FemmeService femmeService;
    private SimpleDateFormat sdf;
    
    @Before
    public void setUp() {
        mariageService = new MariageService();
        hommeService = new HommeService();
        femmeService = new FemmeService();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    @Test
    public void testCreateMariage() throws Exception {
        Homme homme = new Homme("MARTIN", "Luc", 
            sdf.parse("01/01/1980"), "0612345678", "Paris");
        Femme femme = new Femme("BERNARD", "Sophie", 
            sdf.parse("01/01/1985"), "0623456789", "Paris");
        
        hommeService.create(homme);
        femmeService.create(femme);
        
        Mariage mariage = new Mariage(
            sdf.parse("01/06/2010"), null, 2, homme, femme);
        
        boolean result = mariageService.create(mariage);
        assertTrue("La création doit réussir", result);
        assertTrue("L'ID doit être généré", mariage.getId() > 0);
    }
    
    @Test
    public void testGetMariagesEnCours() {
        List<Mariage> mariages = mariageService.getMariagesEnCours();
        assertNotNull("La liste ne doit pas être null", mariages);
        
        for (Mariage m : mariages) {
            assertNull("La date de fin doit être null", m.getDateFin());
        }
    }
    
    @Test
    public void testGetMariagesEchus() {
        List<Mariage> mariages = mariageService.getMariagesEchus();
        assertNotNull("La liste ne doit pas être null", mariages);
        
        for (Mariage m : mariages) {
            assertNotNull("La date de fin ne doit pas être null", m.getDateFin());
        }
    }
}
```

## Exécution des tests

### Avec Maven
```bash
mvn test
```

### Avec un IDE
- Clic droit sur le package de test → Run Tests

## Configuration pour les tests

Pour les tests unitaires, il est recommandé d'utiliser une base de données de test séparée.

Créer un fichier `src/test/resources/hibernate.cfg.xml` :

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/etat_civil_test</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password"></property>
        
        <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>
        <property name="hibernate.show_sql">false</property>
        <property name="hibernate.hbm2ddl.auto">create-drop</property>
        
        <mapping class="ma.projet.beans.Personne"/>
        <mapping class="ma.projet.beans.Homme"/>
        <mapping class="ma.projet.beans.Femme"/>
        <mapping class="ma.projet.beans.Mariage"/>
    </session-factory>
</hibernate-configuration>
```

Créer la base de données de test :
```sql
CREATE DATABASE etat_civil_test;
```

## Tests d'intégration

Les tests d'intégration vérifient le bon fonctionnement de l'application complète :

1. **Test de bout en bout** : Exécuter `App.java` et vérifier les résultats
2. **Test de performance** : Mesurer le temps d'exécution des requêtes
3. **Test de cohérence** : Vérifier l'intégrité des données

## Couverture de code

Pour mesurer la couverture de code, ajouter le plugin JaCoCo dans `pom.xml` :

```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.8</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

Puis exécuter :
```bash
mvn clean test jacoco:report
```

Le rapport sera généré dans `target/site/jacoco/index.html`.

## Résultats attendus

Tous les tests doivent passer avec succès :

```
Tests run: 15, Failures: 0, Errors: 0, Skipped: 0
```

## Bonnes pratiques

1. ✅ Utiliser `@Before` pour initialiser les services
2. ✅ Utiliser `@After` pour nettoyer les données de test
3. ✅ Tester les cas normaux et les cas limites
4. ✅ Utiliser des assertions claires
5. ✅ Isoler chaque test (indépendance)
6. ✅ Nommer les tests de manière descriptive
