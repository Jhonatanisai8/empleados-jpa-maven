package com.jhonatan.empleados.jpa.persistencia;

import com.jhonatan.empleados.jpa.persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TbpersonaJpaController implements Serializable {

    public TbpersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("empleadosjpaPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public TbpersonaJpaController() {
    }

    public void create(Tbpersona tbpersona) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tbpersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbpersona tbpersona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Integer id = tbpersona.getIdtbpersona();
            if (findTbpersona(id) == null) {
                throw new NonexistentEntityException("La persona con id " + id + " no existe.");
            } else {
                tbpersona = em.merge(tbpersona);
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbpersona tbpersona;
            try {
                tbpersona = em.getReference(Tbpersona.class, id);
                tbpersona.getIdtbpersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("La persona con id " + id + " no existe.", enfe);
            }
            em.remove(tbpersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbpersona> findTbpersonaEntities() {
        return findTbpersonaEntities(true, -1, -1);
    }

    public List<Tbpersona> findTbpersonaEntities(int maxResults, int firstResult) {
        return findTbpersonaEntities(false, maxResults, firstResult);
    }

    private List<Tbpersona> findTbpersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbpersona.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tbpersona findTbpersona(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbpersona.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbpersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbpersona> rt = cq.from(Tbpersona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
