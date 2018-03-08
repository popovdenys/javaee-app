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
import po.po.domain.Record;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RecordProcessingService extends RecordProcessingServiceLocal {
    /* todo
        void addRecord(Record record);

        List<Record> getRecords();

        Record getRecord(long id);

        void removeRecord(long id) throws NoRecordException;
    */
    List<Record> findRecord(String record);
}
