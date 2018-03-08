/*
 * File : RecordDAO.java
 * Description : record data access object
 *
 * Author : Popov Denys
 * Created : 28/02/18
 *
 * Modified : { date: 28/02/18
 *             ,time: 7:248 PM }
 * Modified by: Popov Denys
 *
 * Last modification : DAO methods
 */

package po.po.dao.record.dataaccess;

import po.po.dao.exceptions.record.NoRecordException;
import po.po.domain.Record;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RecordDAO {
    public void insert(Record record);
    public void updateRecord(Record record) throws NoRecordException;
    public List<Record> selectAll();
    public Record getById(long id) throws NoRecordException;
    public List<Record> find(String record);
    public void delete(long id) throws NoRecordException;
    public List<Record> getAllRecordsBetween(long startId, long endId);
}
