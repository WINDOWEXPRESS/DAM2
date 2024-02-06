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
import persistenciabdemp.exceptions.NonexistentEntityException;
import persistenciabdemp.exceptions.PreexistingEntityException;

/**
 *
 * @author alumno
 */
public class EmpJpaController implements Serializable {

    public EmpJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Emp emp) throws PreexistingEntityException, Exception {
        if (emp.getEmpCollection() == null) {
            emp.setEmpCollection(new ArrayList<Emp>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dept deptno = emp.getDeptno();
            if (deptno != null) {
                deptno = em.getReference(deptno.getClass(), deptno.getDeptno());
                emp.setDeptno(deptno);
            }
            Emp mgr = emp.getMgr();
            if (mgr != null) {
                mgr = em.getReference(mgr.getClass(), mgr.getEmpno());
                emp.setMgr(mgr);
            }
            Collection<Emp> attachedEmpCollection = new ArrayList<Emp>();
            for (Emp empCollectionEmpToAttach : emp.getEmpCollection()) {
                empCollectionEmpToAttach = em.getReference(empCollectionEmpToAttach.getClass(), empCollectionEmpToAttach.getEmpno());
                attachedEmpCollection.add(empCollectionEmpToAttach);
            }
            emp.setEmpCollection(attachedEmpCollection);
            em.persist(emp);
            if (deptno != null) {
                deptno.getEmpCollection().add(emp);
                deptno = em.merge(deptno);
            }
            if (mgr != null) {
                mgr.getEmpCollection().add(emp);
                mgr = em.merge(mgr);
            }
            for (Emp empCollectionEmp : emp.getEmpCollection()) {
                Emp oldMgrOfEmpCollectionEmp = empCollectionEmp.getMgr();
                empCollectionEmp.setMgr(emp);
                empCollectionEmp = em.merge(empCollectionEmp);
                if (oldMgrOfEmpCollectionEmp != null) {
                    oldMgrOfEmpCollectionEmp.getEmpCollection().remove(empCollectionEmp);
                    oldMgrOfEmpCollectionEmp = em.merge(oldMgrOfEmpCollectionEmp);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmp(emp.getEmpno()) != null) {
                throw new PreexistingEntityException("Emp " + emp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Emp emp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emp persistentEmp = em.find(Emp.class, emp.getEmpno());
            Dept deptnoOld = persistentEmp.getDeptno();
            Dept deptnoNew = emp.getDeptno();
            Emp mgrOld = persistentEmp.getMgr();
            Emp mgrNew = emp.getMgr();
            Collection<Emp> empCollectionOld = persistentEmp.getEmpCollection();
            Collection<Emp> empCollectionNew = emp.getEmpCollection();
            if (deptnoNew != null) {
                deptnoNew = em.getReference(deptnoNew.getClass(), deptnoNew.getDeptno());
                emp.setDeptno(deptnoNew);
            }
            if (mgrNew != null) {
                mgrNew = em.getReference(mgrNew.getClass(), mgrNew.getEmpno());
                emp.setMgr(mgrNew);
            }
            Collection<Emp> attachedEmpCollectionNew = new ArrayList<Emp>();
            for (Emp empCollectionNewEmpToAttach : empCollectionNew) {
                empCollectionNewEmpToAttach = em.getReference(empCollectionNewEmpToAttach.getClass(), empCollectionNewEmpToAttach.getEmpno());
                attachedEmpCollectionNew.add(empCollectionNewEmpToAttach);
            }
            empCollectionNew = attachedEmpCollectionNew;
            emp.setEmpCollection(empCollectionNew);
            emp = em.merge(emp);
            if (deptnoOld != null && !deptnoOld.equals(deptnoNew)) {
                deptnoOld.getEmpCollection().remove(emp);
                deptnoOld = em.merge(deptnoOld);
            }
            if (deptnoNew != null && !deptnoNew.equals(deptnoOld)) {
                deptnoNew.getEmpCollection().add(emp);
                deptnoNew = em.merge(deptnoNew);
            }
            if (mgrOld != null && !mgrOld.equals(mgrNew)) {
                mgrOld.getEmpCollection().remove(emp);
                mgrOld = em.merge(mgrOld);
            }
            if (mgrNew != null && !mgrNew.equals(mgrOld)) {
                mgrNew.getEmpCollection().add(emp);
                mgrNew = em.merge(mgrNew);
            }
            for (Emp empCollectionOldEmp : empCollectionOld) {
                if (!empCollectionNew.contains(empCollectionOldEmp)) {
                    empCollectionOldEmp.setMgr(null);
                    empCollectionOldEmp = em.merge(empCollectionOldEmp);
                }
            }
            for (Emp empCollectionNewEmp : empCollectionNew) {
                if (!empCollectionOld.contains(empCollectionNewEmp)) {
                    Emp oldMgrOfEmpCollectionNewEmp = empCollectionNewEmp.getMgr();
                    empCollectionNewEmp.setMgr(emp);
                    empCollectionNewEmp = em.merge(empCollectionNewEmp);
                    if (oldMgrOfEmpCollectionNewEmp != null && !oldMgrOfEmpCollectionNewEmp.equals(emp)) {
                        oldMgrOfEmpCollectionNewEmp.getEmpCollection().remove(empCollectionNewEmp);
                        oldMgrOfEmpCollectionNewEmp = em.merge(oldMgrOfEmpCollectionNewEmp);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = emp.getEmpno();
                if (findEmp(id) == null) {
                    throw new NonexistentEntityException("The emp with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emp emp;
            try {
                emp = em.getReference(Emp.class, id);
                emp.getEmpno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The emp with id " + id + " no longer exists.", enfe);
            }
            Dept deptno = emp.getDeptno();
            if (deptno != null) {
                deptno.getEmpCollection().remove(emp);
                deptno = em.merge(deptno);
            }
            Emp mgr = emp.getMgr();
            if (mgr != null) {
                mgr.getEmpCollection().remove(emp);
                mgr = em.merge(mgr);
            }
            Collection<Emp> empCollection = emp.getEmpCollection();
            for (Emp empCollectionEmp : empCollection) {
                empCollectionEmp.setMgr(null);
                empCollectionEmp = em.merge(empCollectionEmp);
            }
            em.remove(emp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Emp> findEmpEntities() {
        return findEmpEntities(true, -1, -1);
    }

    public List<Emp> findEmpEntities(int maxResults, int firstResult) {
        return findEmpEntities(false, maxResults, firstResult);
    }

    private List<Emp> findEmpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Emp.class));
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

    public Emp findEmp(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Emp.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Emp> rt = cq.from(Emp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
