/**
 *
 */
package com.leonarduk.webscraper.core.db.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * The Class TestClass.
 *
 * @author stephen
 * @version $Author: $: Author of last commit
 * @version $Rev$: Revision of last commit
 * @version $Date: 2015-02-08 21:26:51 +0000 (Sun, 08 Feb 2015) $: Date of last commit
 * @since 8 Feb 2015
 */
public class ExampleClass {
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

		Example test = em.find(Example.class, 1);
		if (test == null) {
			test = new Example();
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

	/**
	 * Instantiates a new test class.
	 */
	protected ExampleClass() {

	}
}
