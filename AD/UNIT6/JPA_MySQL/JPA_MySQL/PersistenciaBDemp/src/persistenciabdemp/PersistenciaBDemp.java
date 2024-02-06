
package persistenciabdemp;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenciaBDemp {
    private static EntityManager em;
    
      public static void main(String[] args) {
     
         EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("PersistenciaBDempPU");
        DeptJpaController  dao = new  DeptJpaController(emf);
        List<Dept> lista = dao.findDeptEntities();
        for ( Dept  departamento : lista ){
         System.out.println("nombre departamento: " + departamento.getDname());
        }
        
        //ejecución 2 dar alta departamento
          em = emf.createEntityManager();
        Dept undept =new Dept();
        undept.setDeptno(60);
        undept.setDname("RRHH");
        undept.setLoc("Burgos");
            em.getTransaction().begin(); 
            em.persist(undept);
            em.getTransaction().commit();
        }
      }


