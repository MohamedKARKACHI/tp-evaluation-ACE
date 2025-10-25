package ma.projet.service;

import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class HommeService implements IDao<Homme> {
    
    @Override
    public boolean create(Homme o) {
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
    public boolean update(Homme o) {
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
    public boolean delete(Homme o) {
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
    public Homme findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Homme.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Homme> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Homme", Homme.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Afficher les épouses d'un homme entre deux dates
     */
    public List<Mariage> getEpousesEntreDeuxDates(Homme homme, Date dateDebut, Date dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Mariage m WHERE m.homme.id = :hommeId AND m.dateDebut BETWEEN :dateDebut AND :dateFin";
            Query<Mariage> query = session.createQuery(hql, Mariage.class);
            query.setParameter("hommeId", homme.getId());
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Afficher le nombre d'hommes mariés à quatre femmes entre deux dates
     */
    public long getNombreHommesMarieQuatreFemmes(Date dateDebut, Date dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(DISTINCT m.homme.id) FROM Mariage m " +
                        "WHERE m.dateDebut BETWEEN :dateDebut AND :dateFin " +
                        "GROUP BY m.homme.id " +
                        "HAVING COUNT(m.femme.id) = 4";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            List<Long> results = query.list();
            return results.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
