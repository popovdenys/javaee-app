/*
 * File : RecordProcessingImpl.java
 * Description : processing Record base implemantation
 *
 * Author : Popov Denys
 * Created : 26/02/18
 *
 * Modified : { date: 26/02/18
 *             ,time: 9:44 PM }
 * Modified by: Popov Denys
 *
 * Last modification : processing Record implementation
 */

package po.po.dao.record.services;

import po.po.dao.exceptions.record.NoRecordException;
import po.po.exceptions.ServiceUnavailableException;
import po.po.dao.record.dataaccess.RecordDAO;
import po.po.domain.Record;
import po.po.dataaccess.qualifiers.Production;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RecordProcessingImpl implements RecordProcessingService, RecordProcessingServiceLocal {

    @Inject
    @Production
    private RecordDAO dao;

    @Override
    public void addRecord(Record record) throws ServiceUnavailableException { dao.insert(record); }
    @Override
    public void updateRecord(Record record) throws NoRecordException { dao.updateRecord(record); }

    @Override
    public Record getRecord(long id) throws NoRecordException { return dao.getById(id); }

    @Override
    public List<Record> getRecords() {
        return dao.selectAll();
    }

    @Override
    public List<Record> findRecord(String record) { return dao.find(record); }

    @Override
    public void removeRecord(long id) throws NoRecordException { dao.delete(id); }

    @Override
    public List<Record> getRecordsBetween(long startId, long endId) {
        return dao.getAllRecordsBetween(startId, endId);
    }
}
