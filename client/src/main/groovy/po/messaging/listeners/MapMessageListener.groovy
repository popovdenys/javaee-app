package po.messaging.listeners

import javax.jms.MapMessage
import javax.jms.Message
import javax.jms.MessageListener
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MapMessageListener implements MessageListener {

    @Override
    void onMessage(Message message) {

        println("//////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\")

        if(message instanceof MapMessage) {
            MapMessage mapMessage = message

            int sku = mapMessage.getInt("sku")
            String qui = mapMessage.getString("qui")
            LocalDateTime date = Instant.ofEpochMilli( mapMessage.getLong("epochDay") )
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime()

            printf("%d : %s on %s\n", sku, qui, date.format(DateTimeFormatter.ofPattern("MMMM dd, YYYY, hh:mm:ss")).toString())

        } else
            println("Not expected message type received.")

    }
}
