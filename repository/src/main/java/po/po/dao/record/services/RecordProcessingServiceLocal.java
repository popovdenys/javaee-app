/*
 * File : RecordProcessingService.java
 * Description : processing Record base functions
 *
 * Author : Popov Denys
 * Created : 26/02/18
 *
 * Modified : { date: 26/02/18
 *             ,time: 9:28 PM }
 * Modified by: Popov Denys
 *
 * Last modification : processing Record interface
 */

package po.po.dao.record.services;

import po.po.dao.exceptions.record.NoRecordException;
import po.po.exceptions.ServiceUnavailableException;
import po.po.domain.Record;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RecordProcessingServiceLocal {

    void addRecord(Record record) throws ServiceUnavailableException;

    void updateRecord(Record record) throws NoRecordException;

    List<Record> getRecords();

    Record getRecord(long id) throws NoRecordException;

    List<Record> getRecordsBetween(long startId, long endId);

    void removeRecord(long id) throws NoRecordException;
}
