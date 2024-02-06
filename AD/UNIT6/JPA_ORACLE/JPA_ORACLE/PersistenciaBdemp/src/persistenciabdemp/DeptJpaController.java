/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciabdemp;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistenciabdemp.exceptions.IllegalOrphanException;
import persistenciabdemp.exceptions.NonexistentEntityException;
import persistenciabdemp.exceptions.PreexistingEntityException;

/**
 *
 * @author alumno
 */
public class DeptJpaController implements Serializable {

    public DeptJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dept dept) throws PreexistingEntityException, Exception {
        if (dept.getEmpCollection() == null) {
            dept.setEmpCollection(new ArrayList<Emp>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Emp> attachedEmpCollection = new ArrayList<Emp>();
            for (Emp empCollectionEmpToAttach : dept.getEmpCollection()) {
                empCollectionEmpToAttach = em.getReference(empCollectionEmpToAttach.getClass(), empCollectionEmpToAttach.getEmpno());
                attachedEmpCollection.add(empCollectionEmpToAttach);
            }
            dept.setEmpCollection(attachedEmpCollection);
            em.persist(dept);
            for (Emp empCollectionEmp : dept.getEmpCollection()) {
                Dept oldDeptnoOfEmpCollectionEmp = empCollectionEmp.getDeptno();
                empCollectionEmp.setDeptno(dept);
                empCollectionEmp = em.merge(empCollectionEmp);
                if (oldDeptnoOfEmpCollectionEmp != null) {
                    oldDeptnoOfEmpCollectionEmp.getEmpCollection().remove(empCollectionEmp);
                    oldDeptnoOfEmpCollectionEmp = em.merge(oldDeptnoOfEmpCollectionEmp);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDept(dept.getDeptno()) != null) {
                throw new PreexistingEntityException("Dept " + dept + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dept dept) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dept persistentDept = em.find(Dept.class, dept.getDeptno());
            Collection<Emp> empCollectionOld = persistentDept.getEmpCollection();
            Collection<Emp> empCollectionNew = dept.getEmpCollection();
            List<String> illegalOrphanMessages = null;
            for (Emp empCollectionOldEmp : empCollectionOld) {
                if (!empCollectionNew.contains(empCollectionOldEmp)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Emp " + empCollectionOldEmp + " since its deptno field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Emp> attachedEmpCollectionNew = new ArrayList<Emp>();
            for (Emp empCollectionNewEmpToAttach : empCollectionNew) {
                empCollectionNewEmpToAttach = em.getReference(empCollectionNewEmpToAttach.getClass(), empCollectionNewEmpToAttach.getEmpno());
                attachedEmpCollectionNew.add(empCollectionNewEmpToAttach);
            }
            empCollectionNew = attachedEmpCollectionNew;
            dept.setEmpCollection(empCollectionNew);
            dept = em.merge(dept);
            for (Emp empCollectionNewEmp : empCollectionNew) {
                if (!empCollectionOld.contains(empCollectionNewEmp)) {
                    Dept oldDeptnoOfEmpCollectionNewEmp = empCollectionNewEmp.getDeptno();
                    empCollectionNewEmp.setDeptno(dept);
                    empCollectionNewEmp = em.merge(empCollectionNewEmp);
                    if (oldDeptnoOfEmpCollectionNewEmp != null && !oldDeptnoOfEmpCollectionNewEmp.equals(dept)) {
                        oldDeptnoOfEmpCollectionNewEmp.getEmpCollection().remove(empCollectionNewEmp);
                        oldDeptnoOfEmpCollectionNewEmp = em.merge(oldDeptnoOfEmpCollectionNewEmp);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = dept.getDeptno();
                if (findDept(id) == null) {
                    throw new NonexistentEntityException("The dept with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dept dept;
            try {
                dept = em.getReference(Dept.class, id);
                dept.getDeptno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dept with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Emp> empCollectionOrphanCheck = dept.getEmpCollection();
            for (Emp empCollectionOrphanCheckEmp : empCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Dept (" + dept + ") cannot be destroyed since the Emp " + empCollectionOrphanCheckEmp + " in its empCollection field has a non-nullable deptno field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(dept);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dept> findDeptEntities() {
        return findDeptEntities(true, -1, -1);
    }

    public List<Dept> findDeptEntities(int maxResults, int firstResult) {
        return findDeptEntities(false, maxResults, firstResult);
    }

    private List<Dept> findDeptEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dept.class));
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

    public Dept findDept(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dept.class, id);
        } finally {
            em.close();
        }
    }

    public int getDeptCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dept> rt = cq.from(Dept.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
