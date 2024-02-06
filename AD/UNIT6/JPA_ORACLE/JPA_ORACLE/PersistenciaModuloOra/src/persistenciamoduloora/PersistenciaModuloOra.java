/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciamoduloora;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alumno
 */
public class PersistenciaModuloOra {

   public static void main(String[] args) {
       final Modulo modulo = new Modulo();
        modulo.setNombre("BASES DE DATOS");
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("PersistenciaModuloOraPU");
                                                //nombre de la unidad de persistencia
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(modulo);
            em.getTransaction().commit();
        } catch (final Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }

    }
    
}
