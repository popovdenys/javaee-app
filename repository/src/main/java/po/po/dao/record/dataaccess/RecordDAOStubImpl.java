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
import po.po.dataaccess.qualifiers.Stub;
import po.po.domain.Record;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Alternative
@Stub
public class RecordDAOStubImpl implements RecordDAO {
    @Override
    public void insert(Record record) {

    }

    @Override
    public void updateRecord(Record record) throws NoRecordException {

    }

    @Override
    public List<Record> selectAll() {

        List<Record> records = new ArrayList<>();

        Record record1 = new Record("depart1", "depart 1");
        Record record2 = new Record("depart2", "depart 2");
        Record record3 = new Record("depart3", "depart 3");
        Record record4 = new Record("depart4", "depart 4");
        Record record5 = new Record("depart5", "depart 5");

        records.add(record1);
        records.add(record2);
        records.add(record3);
        records.add(record4);
        records.add(record5);

        return records;
    }

    @Override
    public List<Record> find(String record) {
        List<Record> foundRecords = new ArrayList<>();
        List<Record> records = selectAll();
        for (Record r: records) {
            if(r.getDepart().equals(record)) {
                foundRecords.add(r);
            }
        }
        return foundRecords;
    }

    @Override
    public Record getById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Record> getAllRecordsBetween(long startId, long endId) {
        // todo
        return null;
    }
}
