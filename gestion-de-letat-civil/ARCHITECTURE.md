# Architecture de l'Application

## Vue d'ensemble

L'application suit une architecture en couches (Layered Architecture) avec le pattern DAO.

```
┌─────────────────────────────────────────────────────────┐
│                    Couche Présentation                   │
│                      (App.java)                          │
└─────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────┐
│                    Couche Service                        │
│         HommeService | FemmeService | MariageService     │
│                   (Logique métier)                       │
└─────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────┐
│                     Couche DAO                           │
│                     IDao<T>                              │
│              (Interface générique CRUD)                  │
└─────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────┐
│                  Couche Persistance                      │
│         Personne | Homme | Femme | Mariage              │
│                  (Entités JPA)                           │
└─────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────┐
│                    Hibernate ORM                         │
│                  (HibernateUtil)                         │
└─────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────┐
│                  Base de données MySQL                   │
│                    (etat_civil)                          │
└─────────────────────────────────────────────────────────┘
```

## Diagramme de classes

```
                    ┌─────────────────┐
                    │    Personne     │
                    ├─────────────────┤
                    │ - id: int       │
                    │ - nom: String   │
                    │ - prenom: String│
                    │ - dateNaissance │
                    │ - telephone     │
                    │ - adresse       │
                    └─────────────────┘
                           △
                           │ (Inheritance JOINED)
                ┌──────────┴──────────┐
                │                     │
        ┌───────┴────────┐    ┌──────┴────────┐
        │     Homme      │    │     Femme     │
        ├────────────────┤    ├───────────────┤
        │ - mariages     │    │ - mariages    │
        └────────────────┘    └───────────────┘
                │                     │
                │                     │
                │   ┌─────────────┐   │
                └──→│   Mariage   │←──┘
                    ├─────────────┤
                    │ - id        │
                    │ - dateDebut │
                    │ - dateFin   │
                    │ - nbrEnfants│
                    │ - homme     │
                    │ - femme     │
                    └─────────────┘
```

## Modèle relationnel (Base de données)

```sql
┌──────────────────────────────────────┐
│            PERSONNE                  │
├──────────────────────────────────────┤
│ PK  id (INT, AUTO_INCREMENT)         │
│     nom (VARCHAR)                    │
│     prenom (VARCHAR)                 │
│     dateNaissance (DATE)             │
│     telephone (VARCHAR)              │
│     adresse (VARCHAR)                │
└──────────────────────────────────────┘
              △
              │
    ┌─────────┴─────────┐
    │                   │
┌───┴──────────┐  ┌─────┴────────┐
│   HOMME      │  │    FEMME     │
├──────────────┤  ├──────────────┤
│ PK,FK id     │  │ PK,FK id     │
└──────────────┘  └──────────────┘
    │                   │
    │  ┌──────────────┐ │
    └─→│   MARIAGE    │←┘
       ├──────────────┤
       │ PK id        │
       │ FK homme_id  │
       │ FK femme_id  │
       │ dateDebut    │
       │ dateFin      │
       │ nbrEnfants   │
       └──────────────┘
```

## Patterns utilisés

### 1. DAO Pattern (Data Access Object)
```
IDao<T> (Interface)
   ↓
HommeService implements IDao<Homme>
FemmeService implements IDao<Femme>
MariageService implements IDao<Mariage>
```

**Avantages** :
- Séparation des préoccupations
- Facilite les tests unitaires
- Abstraction de la couche de persistance

### 2. Singleton Pattern
```java
// HibernateUtil utilise un Singleton pour SessionFactory
private static SessionFactory sessionFactory;
```

**Avantages** :
- Une seule instance de SessionFactory
- Économie de ressources
- Thread-safe

### 3. Template Method Pattern
```java
// Méthode générique dans IDao
public interface IDao<T> {
    boolean create(T o);
    boolean update(T o);
    boolean delete(T o);
    T findById(int id);
    List<T> findAll();
}
```

### 4. Strategy Pattern
Chaque service implémente sa propre stratégie pour les requêtes spécifiques :
- `HommeService.getEpousesEntreDeuxDates()`
- `FemmeService.getFemmePlusAgee()`
- `MariageService.getMariagesEnCours()`

## Flux de données

### Exemple : Création d'un homme

```
1. App.java
   ↓
   Homme h1 = new Homme(...)
   ↓
2. HommeService.create(h1)
   ↓
   Session session = HibernateUtil.getSessionFactory().openSession()
   ↓
   Transaction transaction = session.beginTransaction()
   ↓
   session.save(h1)
   ↓
   transaction.commit()
   ↓
3. Hibernate génère le SQL
   ↓
   INSERT INTO personne (nom, prenom, ...) VALUES (...)
   INSERT INTO homme (id) VALUES (...)
   ↓
4. MySQL exécute les requêtes
   ↓
5. Retour : boolean (true/false)
```

### Exemple : Requête avec jointure

```
1. App.java
   ↓
   List<Mariage> mariages = mariageService.getMariagesParHomme(h1)
   ↓
2. MariageService.getMariagesParHomme()
   ↓
   HQL: "FROM Mariage m WHERE m.homme.id = :hommeId"
   ↓
3. Hibernate traduit en SQL
   ↓
   SELECT m.*, h.*, f.* 
   FROM mariage m
   LEFT JOIN homme h ON m.homme_id = h.id
   LEFT JOIN femme f ON m.femme_id = f.id
   WHERE m.homme_id = ?
   ↓
4. MySQL retourne les résultats
   ↓
5. Hibernate mappe les résultats en objets Java
   ↓
6. Retour : List<Mariage>
```

## Gestion des transactions

```java
Transaction transaction = null;
try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    transaction = session.beginTransaction();
    
    // Opérations de base de données
    session.save(object);
    
    transaction.commit();  // Valider
    return true;
} catch (Exception e) {
    if (transaction != null) {
        transaction.rollback();  // Annuler en cas d'erreur
    }
    e.printStackTrace();
    return false;
}
```

## Héritage JPA - Stratégie JOINED

```
Table PERSONNE (table mère)
├── id (PK)
├── nom
├── prenom
├── dateNaissance
├── telephone
└── adresse

Table HOMME (table fille)
└── id (PK, FK → PERSONNE.id)

Table FEMME (table fille)
└── id (PK, FK → PERSONNE.id)
```

**Avantages** :
- Normalisation des données
- Pas de duplication
- Intégrité référentielle

**Inconvénients** :
- Jointures nécessaires pour chaque requête
- Légèrement plus lent que SINGLE_TABLE

## Relations JPA

### One-to-Many (Homme → Mariages)
```java
@OneToMany(mappedBy = "homme", fetch = FetchType.EAGER)
private List<Mariage> mariages;
```

### Many-to-One (Mariage → Homme)
```java
@ManyToOne
@JoinColumn(name = "homme_id")
private Homme homme;
```

## Configuration Hibernate

### hibernate.cfg.xml
- Configuration de la connexion MySQL
- Mapping des entités
- Stratégie de génération du schéma

### application.properties
- Paramètres de configuration alternatifs
- Propriétés personnalisées

## Sécurité

### Bonnes pratiques implémentées :
1. ✅ Utilisation de paramètres dans les requêtes (évite SQL Injection)
   ```java
   query.setParameter("hommeId", homme.getId());
   ```

2. ✅ Gestion des transactions (ACID)
3. ✅ Try-with-resources pour fermer les sessions
4. ✅ Gestion des exceptions

### À améliorer (pour production) :
- ⚠️ Mot de passe MySQL en clair (utiliser un vault)
- ⚠️ Validation des données d'entrée
- ⚠️ Logging structuré (Log4j, SLF4J)
- ⚠️ Pool de connexions optimisé (HikariCP)

## Performance

### Optimisations implémentées :
1. ✅ FetchType.EAGER pour les mariages (évite N+1 queries)
2. ✅ Réutilisation de SessionFactory (Singleton)
3. ✅ Index automatiques sur les clés étrangères

### Optimisations possibles :
- Cache de second niveau (EhCache)
- Batch processing pour les insertions multiples
- Lazy loading sélectif

## Extensibilité

L'architecture permet facilement d'ajouter :

1. **Nouvelles entités** : Enfant, Divorce, Acte de naissance
2. **Nouveaux services** : EnfantService, ActeService
3. **Nouvelles requêtes** : Statistiques, Rapports
4. **API REST** : Exposer les services via Spring Boot
5. **Interface graphique** : JavaFX, Swing, ou Web

## Tests

### Niveaux de tests :
1. **Tests unitaires** : Services individuels
2. **Tests d'intégration** : Hibernate + MySQL
3. **Tests fonctionnels** : Scénarios complets (App.java)

## Documentation

### Fichiers de documentation :
- `README.md` : Vue d'ensemble
- `ARCHITECTURE.md` : Architecture détaillée (ce fichier)
- `INSTRUCTIONS.md` : Installation et exécution
- `REQUETES.md` : Requêtes HQL et SQL
- `TESTS.md` : Guide des tests

## Dépendances

```
Application
    ├── Hibernate Core 5.6.15
    │   ├── JPA API
    │   ├── Javassist
    │   └── JAXB
    ├── MySQL Connector 8.0.33
    └── JUnit 4.13.2 (tests)
```

## Évolutions futures possibles

1. **Migration vers Spring Boot**
   - Spring Data JPA
   - Spring MVC pour API REST
   - Spring Security

2. **Ajout de fonctionnalités**
   - Gestion des enfants
   - Historique des modifications
   - Génération de documents PDF

3. **Amélioration de la performance**
   - Cache Redis
   - Pagination des résultats
   - Requêtes asynchrones

4. **Monitoring**
   - Métriques avec Micrometer
   - Logs avec ELK Stack
   - Alertes avec Prometheus

## Conclusion

Cette architecture offre :
- ✅ Séparation claire des responsabilités
- ✅ Facilité de maintenance
- ✅ Extensibilité
- ✅ Testabilité
- ✅ Performance acceptable
- ✅ Respect des bonnes pratiques JPA/Hibernate
