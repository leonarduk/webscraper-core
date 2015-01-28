package com.leonarduk.core.db.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestClass {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		Test test = em.find(Test.class, 1);
		if (test == null) {
			test = new Test();
			test.id = 1;
			test.data = "a";

			tx.begin();
			em.persist(test);
			tx.commit();
		}

		System.out.format("Test{id=%s, data=%s}\n", test.id, test.data);

		em.close();
		emf.close();
	}
}
