package com.epam.testapp.database.dao;

import com.epam.testapp.database.exception.DaoException;
import com.epam.testapp.model.News;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class JpaNewsDao implements INewsDao {
    private static final Logger LOGGER = Logger.getLogger(JpaNewsDao.class);
    private EntityManagerFactory managerEntity;
    private static final String SELECT_ALL = "SELECT n FROM News n ORDER BY n.date DESC";
    
    public JpaNewsDao() {}

    @Override
    public Integer insert(News news) throws DaoException {
        EntityManager em = null;
        try {
            em = managerEntity.createEntityManager();
            em.getTransaction().begin();
            em.persist(news);
            em.flush();
            em.getTransaction().commit();
        } catch (PersistenceException exc) {
            LOGGER.error(exc);
            throw new DaoException(exc);
        } finally {
            closeManager(em);
        }
        return news.getId();
    }
    
    @Override
    public void update(News news) throws DaoException {
        EntityManager em = null;
        try {
            em = managerEntity.createEntityManager();
            em.getTransaction().begin();
            em.merge(news);
            em.getTransaction().commit();
        } catch (PersistenceException exc) {
            LOGGER.error(exc);
            throw new DaoException(exc);
        } finally {
            closeManager(em);
        }
    }
    
    @Override
    public void delete(List<News> newsList) throws DaoException {
        EntityManager em = null;
        try {
            em = managerEntity.createEntityManager();
            em.getTransaction().begin();
            for (News news : newsList) {
                news = em.find(News.class, news.getId());
                em.remove(news);
            }
            em.getTransaction().commit();
        } catch (PersistenceException exc) {
            LOGGER.error(exc);
            throw new DaoException(exc);
        } finally {
            closeManager(em);
        }
    }
    
    @Override
    public List selectAll() throws DaoException {
        EntityManager em = null;
        List<News> newsList;
        try {
            em = managerEntity.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Select");
            //Query query = em.createQuery(SELECT_ALL);
            newsList = query.getResultList();
            em.getTransaction().commit();
        } catch (PersistenceException exc) {
            LOGGER.error(exc);
            throw new DaoException(exc);
        } finally {
            closeManager(em);
        }
        return newsList;
    }

    @Override
    public News fetchById(News news) throws DaoException {
        EntityManager em = null;
        try {
            em = managerEntity.createEntityManager();
            em.getTransaction().begin();
            news = em.find(News.class, news.getId());
            em.getTransaction().commit();
        } catch (PersistenceException exc) {
            LOGGER.error(exc);
            throw new DaoException(exc);
        } finally {
            closeManager(em);
        }
        return news;
    }
        
    private void closeManager(EntityManager manager) {
        if ((manager != null) && manager.isOpen()) {
            try {
                manager.close();
            } catch (IllegalStateException exc) {
                LOGGER.error(exc);
            }
        }
    }
    
    public EntityManagerFactory getManagerEntity() {
            return managerEntity;
    }

    public void setManagerEntity(EntityManagerFactory managerEntity) {
            this.managerEntity = managerEntity;
    }

    
    
    
}
