package po.messaging

import po.messaging.listeners.MapMessageListener
import po.messaging.listeners.TextMessageListener

import javax.jms.*
import javax.naming.Context
import javax.naming.InitialContext
import javax.naming.NamingException
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

Properties jndi = new Properties()
jndi.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming")
jndi.put("jboss.naming.client.ejb.context", "true")
jndi.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory")
jndi.put(Context.PROVIDER_URL, "http-remoting://localhost:8080")

Connection connection = null

try {
    Context ctx = new InitialContext(jndi)
    // Queue queue = ctx.lookup("jms/RecordsQueue")
    Queue queue = ctx.lookup("jms/DLQ")
    Topic topic = ctx.lookup("jms/RecordTopic")

    ConnectionFactory cf = (ConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory")
    connection = cf.createConnection();

    connection.setClientID("RecordProcessing")

    Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE)

    // receive text message
    // receiveTextMessage( session, queue, connection )

    // receive map message
    // receiveMapMessage( session, topic, connection )
    // userStoppedProgramm()

    // receive map message
    // alternativeReceiveMapMessage( session, queue, connection )

    // browser the queue
    browserTheQueue(session, queue)

}
catch (NamingException | JMSException e){ e.printStackTrace() }
finally { try { connection.close() } catch (JMSException e ) { } }

/**
 *  METHODS SECTION
 */
// receive map message
private void alternativeReceiveMapMessage(Session session, Queue queue, Connection connection) {
    connection.start()

    MessageConsumer consumer = session.createConsumer(queue)
    MapMessage mapMessage = consumer.receive()

    int sku = mapMessage.getInt("sku")
    String qui = mapMessage.getString("qui")
    LocalDateTime date = Instant.ofEpochMilli( mapMessage.getLong("epochDay") )
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()

    printf("%d : %s on %s\n", sku, qui, date.format(DateTimeFormatter.ofPattern("MMMM dd, YYYY, hh:mm:ss")).toString())

    mapMessage.acknowledge()

}

// receive text message
private void receiveTextMessage(Session session, Queue queue, Connection connection) {
    MessageConsumer consumer = session.createConsumer(queue)
    consumer.setMessageListener(new TextMessageListener())

    connection.start()
}

// receive map message from Topic
private void receiveMapMessage(Session session, Topic topic, Connection connection) {
    //MessageConsumer consumer = session.createConsumer(topic)
    MessageConsumer consumer = session.createDurableConsumer(topic, "record")
    consumer.setMessageListener(new MapMessageListener())

    connection.start()
}

// receive map message
private void receiveMapMessage(Session session, Queue queue, Connection connection) {
    MessageConsumer consumer = session.createConsumer(queue)
    consumer.setMessageListener(new MapMessageListener())

    connection.start()
}

private void userStoppedProgramm() {
    boolean userStopped = false
    while (!userStopped) {
        Scanner scanner = new Scanner(System.in)
        userStopped = scanner.nextLine().toLowerCase().equals("x")
    }
}

// browser the queue
private void browserTheQueue(Session session, Queue queue) {
    QueueBrowser browser = session.createBrowser(queue)

    Boolean finished = false
    while (!finished) {
        Enumeration<Message> e = browser.getEnumeration()
        int messageCount = 0

        while (e.hasMoreElements()) {
            Message message = e.nextElement()
            MapMessage mapMessage = message
            Enumeration<String> names = mapMessage.getMapNames()
            printf("Message # %d\n", messageCount)
            while (names.hasMoreElements()) {
                String k = names.nextElement()
                Object v = mapMessage.getObject(k)
                printf("%s : %s\n", k, v.toString())
            }
            messageCount++
        }

        printf("There are %d messages in the queue", messageCount)
        printf("Press %s to refresh or %c to exit", "ENTER", 'x'.toCharacter())
        Scanner scanner = new Scanner(System.in)
        String input = scanner.nextLine()

        if (input.toLowerCase().trim() == "x") finished = true

    }
}
