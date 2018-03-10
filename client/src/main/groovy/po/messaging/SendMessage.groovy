package po.messaging

import javax.jms.Connection
import javax.jms.ConnectionFactory
import javax.jms.JMSException
import javax.jms.MapMessage
import javax.jms.MessageProducer
import javax.jms.Queue
import javax.jms.Session
import javax.jms.TextMessage
import javax.jms.Topic
import javax.naming.Context
import javax.naming.InitialContext
import javax.naming.NamingException
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

Properties jndi = new Properties()
jndi.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming")
jndi.put("jboss.naming.client.ejb.context", "true")
jndi.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory")
jndi.put(Context.PROVIDER_URL, "http-remoting://localhost:8080")

Connection connection = null

try {
    Context ctx = new InitialContext(jndi)
    // Queue queue = ctx.lookup("jms/RecordsQueue")
    Topic topic = ctx.lookup("jms/RecordTopic")

    ConnectionFactory cf = (ConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory")
    connection = cf.createConnection();

    Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE)

    // send Text message
    // sendTextMessage(session, queue)

    int priority
    // send Map message
    1.upto(15) {
        priority = Math.round(it * Math.random() / 15 * 9 )
        // sendMapMessage(session, queue, priority)
        sendMapMessage(session, topic, priority)
    }

    session?.commit()

}
catch (NamingException | JMSException e){ e.printStackTrace() }
finally { try { connection.close() } catch (JMSException e ) { } }

/**
 *  METHODS SECTION
 */
// send Text message
private void sendTextMessage(Session session, Queue queue) {
    MessageProducer messageProducer = session.createProducer(queue)

    TextMessage message = session.createTextMessage("Denys")

    messageProducer.send(message)
}

// send Map message to Topic
private void sendMapMessage(Session session, Topic topic, int priority) {
    MessageProducer messageProducer = session.createProducer(topic)

    messageProducer.setPriority(priority)
    messageProducer.setTimeToLive(0)

    MapMessage message = session.createMapMessage()
    message.setInt("sku", 1980)
    message.setString("qui", "Denys Popov " + priority)
    message.setLong("epochDay", Instant.now().toEpochMilli())

    messageProducer.send(message)
}

// send Map message
private void sendMapMessage(Session session, Queue queue, int priority) {
    MessageProducer messageProducer = session.createProducer(queue)

    messageProducer.setPriority(priority)
    messageProducer.setTimeToLive(18000)

    MapMessage message = session.createMapMessage()
    message.setInt("sku", 1980)
    message.setString("qui", "Denys Popov " + priority)
    message.setLong("epochDay", Instant.now().toEpochMilli())

    messageProducer.send(message)
}
