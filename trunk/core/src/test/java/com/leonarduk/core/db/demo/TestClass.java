/**
 *
 */
package com.leonarduk.core.db.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestClass {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		final EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction tx = em.getTransaction();

		Test test = em.find(Test.class, 1);
		if (test == null) {
			test = new Test();
			test.setId(1);
			test.setData("a");

			tx.begin();
			em.persist(test);
			tx.commit();
		}

		System.out.format("Test{id=%s, data=%s}\n", test.getId(), test.getData());

		em.close();
		emf.close();
	}
}
