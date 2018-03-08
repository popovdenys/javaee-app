package po.messaging.listeners

import javax.jms.Message
import javax.jms.MessageListener
import javax.jms.TextMessage

class TextMessageListener implements MessageListener {

    @Override
    void onMessage(Message message) {

        println("---------------------------------------------------")

        if(message instanceof TextMessage) {
            TextMessage textMessage = message
            String text = textMessage.getText( )
            println( text )
        } else
            println("Not expected message type received.")

    }
}
