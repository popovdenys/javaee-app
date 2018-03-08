package po.app

import po.po.domain.Record

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.EntityTransaction
import javax.persistence.Persistence
import javax.persistence.Query

/**
 * MAIN CLASS SECTION
 */
EntityManagerFactory emf = Persistence.createEntityManagerFactory("chuldb-derby")
EntityManager em = emf.createEntityManager()

EntityTransaction tx = em.getTransaction()
tx.begin()

//createRecord(em)
getAllRecords(em)

tx.commit()
em.close()
emf.close()

/**
 *  METHODS SECTION
 */
private static void getAllRecords(EntityManager em) {
    em.createQuery("select record from Record record")
            .getResultList()
            .each { println it };
}

private static void createRecord(EntityManager em) {
    Record record = new Record("Popov", "Denys")
    em.persist(record)
}