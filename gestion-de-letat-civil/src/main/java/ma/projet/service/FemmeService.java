package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class FemmeService implements IDao<Femme> {
    
    @Override
    public boolean create(Femme o) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(o);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean update(Femme o) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(o);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean delete(Femme o) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(o);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Femme findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Femme.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Femme> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Femme", Femme.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Afficher la femme la plus âgée
     */
    public Femme getFemmePlusAgee() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Femme f ORDER BY f.dateNaissance ASC";
            Query<Femme> query = session.createQuery(hql, Femme.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Afficher le nombre d'enfants d'une femme entre deux dates
     */
    public int getNombreEnfantsEntreDeuxDates(Femme femme, Date dateDebut, Date dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(m.nbrEnfants) FROM Mariage m WHERE m.femme.id = :femmeId AND m.dateDebut BETWEEN :dateDebut AND :dateFin";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("femmeId", femme.getId());
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            Long result = query.uniqueResult();
            return result != null ? result.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Afficher les femmes mariées deux fois ou plus
     */
    public List<Femme> getFemmesMarieeDeuxFoisOuPlus() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT m.femme FROM Mariage m GROUP BY m.femme HAVING COUNT(m.femme) >= 2";
            Query<Femme> query = session.createQuery(hql, Femme.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
