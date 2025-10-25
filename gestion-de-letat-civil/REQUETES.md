# Requêtes HQL et Critères API

Ce document liste toutes les requêtes HQL utilisées dans l'application.

## HommeService

### 1. Afficher les épouses d'un homme entre deux dates
```hql
FROM Mariage m 
WHERE m.homme.id = :hommeId 
AND m.dateDebut BETWEEN :dateDebut AND :dateFin
```

### 2. Afficher le nombre d'hommes mariés à quatre femmes entre deux dates
```hql
SELECT COUNT(DISTINCT m.homme.id) 
FROM Mariage m 
WHERE m.dateDebut BETWEEN :dateDebut AND :dateFin 
GROUP BY m.homme.id 
HAVING COUNT(m.femme.id) = 4
```

### 3. Récupérer tous les hommes
```hql
FROM Homme
```

## FemmeService

### 1. Afficher la femme la plus âgée
```hql
FROM Femme f 
ORDER BY f.dateNaissance ASC
```
*Note: On prend le premier résultat (setMaxResults(1))*

### 2. Afficher le nombre d'enfants d'une femme entre deux dates
```hql
SELECT SUM(m.nbrEnfants) 
FROM Mariage m 
WHERE m.femme.id = :femmeId 
AND m.dateDebut BETWEEN :dateDebut AND :dateFin
```

### 3. Afficher les femmes mariées deux fois ou plus
```hql
SELECT m.femme 
FROM Mariage m 
GROUP BY m.femme 
HAVING COUNT(m.femme) >= 2
```

### 4. Récupérer toutes les femmes
```hql
FROM Femme
```

## MariageService

### 1. Afficher les mariages d'un homme donné
```hql
FROM Mariage m 
WHERE m.homme.id = :hommeId
```

### 2. Afficher les mariages en cours (sans date de fin)
```hql
FROM Mariage m 
WHERE m.dateFin IS NULL
```

### 3. Afficher les mariages échus (avec date de fin)
```hql
FROM Mariage m 
WHERE m.dateFin IS NOT NULL
```

### 4. Récupérer tous les mariages
```hql
FROM Mariage
```

## Exemples d'utilisation avec Criteria API

Si vous souhaitez utiliser l'API Criteria au lieu de HQL, voici quelques exemples :

### Exemple 1 : Récupérer toutes les femmes
```java
CriteriaBuilder cb = session.getCriteriaBuilder();
CriteriaQuery<Femme> cq = cb.createQuery(Femme.class);
Root<Femme> root = cq.from(Femme.class);
cq.select(root);
List<Femme> femmes = session.createQuery(cq).getResultList();
```

### Exemple 2 : Femme la plus âgée
```java
CriteriaBuilder cb = session.getCriteriaBuilder();
CriteriaQuery<Femme> cq = cb.createQuery(Femme.class);
Root<Femme> root = cq.from(Femme.class);
cq.select(root).orderBy(cb.asc(root.get("dateNaissance")));
Femme femmePlusAgee = session.createQuery(cq).setMaxResults(1).uniqueResult();
```

### Exemple 3 : Mariages entre deux dates
```java
CriteriaBuilder cb = session.getCriteriaBuilder();
CriteriaQuery<Mariage> cq = cb.createQuery(Mariage.class);
Root<Mariage> root = cq.from(Mariage.class);
cq.select(root).where(
    cb.and(
        cb.equal(root.get("homme").get("id"), hommeId),
        cb.between(root.get("dateDebut"), dateDebut, dateFin)
    )
);
List<Mariage> mariages = session.createQuery(cq).getResultList();
```

## Requêtes SQL natives équivalentes

### 1. Liste des femmes
```sql
SELECT * FROM femme f 
INNER JOIN personne p ON f.id = p.id;
```

### 2. Femme la plus âgée
```sql
SELECT * FROM femme f 
INNER JOIN personne p ON f.id = p.id 
ORDER BY p.dateNaissance ASC 
LIMIT 1;
```

### 3. Mariages d'un homme
```sql
SELECT m.*, 
       h.id as homme_id, hp.nom as homme_nom, hp.prenom as homme_prenom,
       f.id as femme_id, fp.nom as femme_nom, fp.prenom as femme_prenom
FROM mariage m
INNER JOIN homme h ON m.homme_id = h.id
INNER JOIN personne hp ON h.id = hp.id
INNER JOIN femme f ON m.femme_id = f.id
INNER JOIN personne fp ON f.id = fp.id
WHERE m.homme_id = ?;
```

### 4. Femmes mariées deux fois ou plus
```sql
SELECT f.*, p.* 
FROM femme f
INNER JOIN personne p ON f.id = p.id
INNER JOIN mariage m ON f.id = m.femme_id
GROUP BY f.id, p.nom, p.prenom, p.dateNaissance, p.telephone, p.adresse
HAVING COUNT(m.id) >= 2;
```

### 5. Nombre d'enfants d'une femme entre deux dates
```sql
SELECT SUM(m.nbrEnfants) as total_enfants
FROM mariage m
WHERE m.femme_id = ?
AND m.dateDebut BETWEEN ? AND ?;
```

## Notes importantes

1. **Héritage JOINED** : Les tables `homme` et `femme` sont liées à `personne` par une clé étrangère.

2. **Relations Many-to-One** : Chaque mariage est lié à un homme et une femme.

3. **Fetch Type** : Les mariages sont chargés en EAGER pour éviter les problèmes de lazy loading.

4. **Dates nullables** : `dateFin` peut être NULL pour les mariages en cours.

5. **Agrégations** : Utilisation de COUNT, SUM pour les statistiques.
