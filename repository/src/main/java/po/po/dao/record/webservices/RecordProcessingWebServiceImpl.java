package po.po.dao.record.webservices;

import po.po.dao.exceptions.record.NoRecordException;
import po.po.dao.record.services.RecordProcessingServiceLocal;
import po.po.domain.Record;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;

@Stateless
@WebService( name = "RecordProcessingWebService" )
public class RecordProcessingWebServiceImpl {

    @Inject
    private RecordProcessingServiceLocal service;

    public Record getRecord(long id) throws NoRecordException { return service.getRecord( id ); }

    public List<Record> getRecords() {
        return service.getRecords();
    }
}
