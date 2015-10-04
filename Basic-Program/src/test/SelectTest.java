package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class SelectTest {

	/**
	 * @param args
	 */
	private static final String PERSISTENCE_UNIT_NAME = "todos";
	  private static EntityManagerFactory factory;
	public static void main(String[] args) {
		 
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    EntityManager em = factory.createEntityManager();
	    // read the existing entries and write to console
	    Query q = em.createQuery("select e from emp e");
	    List<EMP11> emps = q.getResultList();
	    for (EMP11 emp : emps) {
	      System.out.println(emp);
	    }
	    System.out.println("Size: " + emps.size());

	    // create new todo
	    em.getTransaction().begin();
	    EMP11 emp = new EMP11();
	    emp.setId(2);
	    emp.setName("This is a test");
	    em.persist(emp);
	    em.getTransaction().commit();

	    em.close();

	}

}
