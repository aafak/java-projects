package mypack;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Demo {

	private static final String PERSISTENCE_UNIT_NAME = "JPAFirst";
	private static EntityManagerFactory factory;

	public static void main(String[] args){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();
		Emp emp = new Emp();
		emp.setId(5);
		emp.setName("aakash");
		em.persist(emp);
		em.getTransaction().commit();

		em.close();

	}
}
