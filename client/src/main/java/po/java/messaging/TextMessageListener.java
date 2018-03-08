package po.java.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TextMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {

        System.out.println("---------------------------------------------------");

        if(message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            String text = null;
            try {
                text = textMessage.getText( );
            } catch (JMSException e) {
                e.printStackTrace();
            }
            System.out.println( text );
        } else
            System.out.println( "Not expected message type received." );

    }
}
