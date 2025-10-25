-- Script de création de la base de données pour l'application de gestion de l'état civil

-- Créer la base de données
CREATE DATABASE IF NOT EXISTS etat_civil CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Utiliser la base de données
USE etat_civil;

-- Note: Les tables seront créées automatiquement par Hibernate
-- grâce à la propriété hibernate.hbm2ddl.auto=update

-- Structure attendue des tables:

-- Table: personne (table mère)
-- - id (INT, PRIMARY KEY, AUTO_INCREMENT)
-- - nom (VARCHAR)
-- - prenom (VARCHAR)
-- - dateNaissance (DATE)
-- - telephone (VARCHAR)
-- - adresse (VARCHAR)

-- Table: homme (hérite de personne)
-- - id (INT, PRIMARY KEY, FOREIGN KEY vers personne)

-- Table: femme (hérite de personne)
-- - id (INT, PRIMARY KEY, FOREIGN KEY vers personne)

-- Table: mariage
-- - id (INT, PRIMARY KEY, AUTO_INCREMENT)
-- - dateDebut (DATE)
-- - dateFin (DATE, nullable)
-- - nbrEnfants (INT)
-- - homme_id (INT, FOREIGN KEY vers homme)
-- - femme_id (INT, FOREIGN KEY vers femme)
