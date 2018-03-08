/*
 * File : RecordDAOStubImpl.java
 * Description : stub implementation for DAO Record
 *
 * Author : Popov Denys
 * Created : 28/02/18
 *
 * Modified : { date: 28/02/18
 *             ,time: 7:25 PM }
 * Modified by: Popov Denys
 *
 * Last modification : record DAO stub implementation
 */

package po.po.dao.record.dataaccess;

import po.po.dao.exceptions.record.NoRecordException;
import po.po.dataaccess.qualifiers.Production;
import po.po.domain.Record;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Default
@Production
public class RecordDAOImpl implements RecordDAO {

    @PersistenceContext(unitName = "chuldb-mysql")
    // @PersistenceUnit(name = "")  // todo : look in spec
    private EntityManager em;

    @Override
    public void insert(Record record) {
        em.persist(record);
    }

    @Override
    public void updateRecord(Record record) throws NoRecordException {
        Record recordToUpdate = getById(record.getId());
        recordToUpdate.setDepart( record.getDepart() );
        recordToUpdate.setDescrition( record.getDescrition() );
    }

    @Override
    public List<Record> selectAll() {
        return (List<Record>) em.createQuery("select record from Record record")
                .getResultList();
    }

    @Override
    public Record getById(long id) throws NoRecordException {
        try {
            return (Record) em.createQuery("select record from Record record where record.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) { throw new NoRecordException();}
    }

    @Override
    public List<Record> find(String record) {
        return (List<Record>) em.createQuery("select record from Record record where record.depart = :depart")
                .setParameter("depart", record)
                .getResultList();
    }

    @Override
    public void delete(long id) throws NoRecordException {
        em.remove( getById( id ) );
    }

    @Override
    public List<Record> getAllRecordsBetween(long startId, long endId) {
        return (List<Record>) em.createQuery("select record from Record record where record.id >= :startId and record.id <= :endId")
                .setParameter("startId", startId)
                .setParameter("endId", endId)
                .getResultList();
    }
}
