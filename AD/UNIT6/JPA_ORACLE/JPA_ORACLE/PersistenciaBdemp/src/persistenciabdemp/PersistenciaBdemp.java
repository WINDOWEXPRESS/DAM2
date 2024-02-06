/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciabdemp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**PersistenciaBdempPU
 *
 * @author alumno
 */
public class PersistenciaBdemp {

     private static EntityManager em;
    
      public static void main(String[] args) {
     
         EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("PersistenciaBdempPU");
        DeptJpaController  dao = new  DeptJpaController(emf);
        List<Dept> lista = dao.findDeptEntities();
        for ( Dept  departamento : lista ){
         System.out.println("nombre departamento: " + departamento.getDname());
        }
        
        //ejecución 2 dar alta departamento
          em = emf.createEntityManager();
        Dept undept =new Dept();
 
        undept.setDeptno(80);
        undept.setDname("compras");
        undept.setLoc("Toledo");
            em.getTransaction().begin(); 
            em.persist(undept);
            em.getTransaction().commit();
        }
}
