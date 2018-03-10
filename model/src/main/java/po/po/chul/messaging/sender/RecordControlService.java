package po.po.chul.messaging.sender;

import po.po.exceptions.ServiceUnavailableException;

import javax.ejb.Remote;

@Remote
public interface RecordControlService {
    public void registerNewChulRecord(long recordId, String depart, String description) throws ServiceUnavailableException;
}
