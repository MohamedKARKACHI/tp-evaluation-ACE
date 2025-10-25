package ma.projet.service;

import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MariageService implements IDao<Mariage> {
    
    @Override
    public boolean create(Mariage o) {
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
    public boolean update(Mariage o) {
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
    public boolean delete(Mariage o) {
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
    public Mariage findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Mariage.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Mariage> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Mariage", Mariage.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Afficher les mariages d'un homme donné avec tous les détails
     */
    public List<Mariage> getMariagesParHomme(Homme homme) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Mariage m WHERE m.homme.id = :hommeId";
            Query<Mariage> query = session.createQuery(hql, Mariage.class);
            query.setParameter("hommeId", homme.getId());
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Afficher les mariages en cours (sans date de fin)
     */
    public List<Mariage> getMariagesEnCours() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Mariage m WHERE m.dateFin IS NULL";
            Query<Mariage> query = session.createQuery(hql, Mariage.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Afficher les mariages échus (avec date de fin)
     */
    public List<Mariage> getMariagesEchus() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Mariage m WHERE m.dateFin IS NOT NULL";
            Query<Mariage> query = session.createQuery(hql, Mariage.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
