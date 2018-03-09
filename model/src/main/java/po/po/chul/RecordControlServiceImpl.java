package po.po.chul;

import po.po.exceptions.ServiceUnavailableException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;

@Stateless
public class RecordControlServiceImpl implements  RecordControlService {

    @Inject
    JMSContext context;

    @Resource(mappedName = "java:jboss/exported/jms/ChulRecordNotificationQueue")
    Queue queue;

    @Override
    public void registerNewChulRecord(long recordId, String depart, String description) throws ServiceUnavailableException {

        MapMessage message = context.createMapMessage();
        try {
            message.setLong("recordId", recordId);
            message.setString("depart", depart);
            message.setString("description", description);

            context.createProducer().send(queue, message);
        } catch (JMSException e) {
            throw new ServiceUnavailableException();
        }
    }
}
