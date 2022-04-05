package simplejpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class Client {

private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = 	Persistence.createEntityManagerFactory("JavaHelps");

public static void main(String[]args) {
	
	//create(1, "Alice");
	create(4, "bob");
	create(5, "charlie");
	
	
	
	ENTITY_MANAGER_FACTORY.close();
}

public static void create(int id,String name) {
	
	EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
	EntityTransaction transaction = null;
	
	try {
		transaction = manager.getTransaction();
		transaction.begin();
		
		Student stu = new Student();
		stu.setId(id);
		stu.setName(name);
		
		manager.persist(stu);
		
		transaction.commit();
	}catch (Exception ex) { 
		if (transaction != null) { 
			transaction.rollback();
		}
		
		ex.printStackTrace();
	}finally {
		manager.close();
		}
	}
}