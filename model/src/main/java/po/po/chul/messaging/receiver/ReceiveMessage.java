package po.po.chul.messaging.receiver;

import po.po.exceptions.ServiceUnavailableException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
        , @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/RecordTopic")
})
public class ReceiveMessage implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message instanceof MapMessage) {
            MapMessage mapMessage = (MapMessage) message;

            try {
                int sku = mapMessage.getInt("sku");
                String qui = mapMessage.getString("qui");
                LocalDateTime date = Instant.ofEpochMilli( mapMessage.getLong("epochDay") )
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                System.out.printf("Attempting redelivery number %d\n", mapMessage.getIntProperty("JMSXDeliveryCount"));
                System.out.printf("%d : %s on %s\n", sku, qui, date.format(DateTimeFormatter.ofPattern("MMMM dd, YYYY, hh:mm:ss")).toString());

                // Todo : simulate system crash
                if(Math.random() < 0.8) {
                    throw new ServiceUnavailableException();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }

        } else {
            throw new RuntimeException("Not expected message type received.");
        }
    }
}
