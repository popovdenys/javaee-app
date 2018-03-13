package po.java.messaging;

import po.java.messaging.TextMessageListener;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.Scanner;

public class ReceiveMessage {

    public static void main(String[] args) {
        Properties jndi = new Properties();
        jndi.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndi.put("jboss.naming.client.ejb.context", "true");
        jndi.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndi.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

        Connection connection = null;

        try {
            Context ctx = new InitialContext(jndi);
            Queue queue = (Queue) ctx.lookup("jms/RecordsQueue");

            ConnectionFactory cf = (ConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory");
            connection = cf.createConnection();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            receiveTextMessage(session, queue, connection);

            userStoppedProgramm();

        }
        catch (NamingException | JMSException e){ e.printStackTrace(); }
        finally { try { connection.close(); } catch (JMSException e ) { } }
    }

    private static void receiveTextMessage(Session session, Queue queue, Connection connection) throws JMSException {
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new TextMessageListener());

        connection.start();
    }

    private static void userStoppedProgramm() {
        boolean userStopped = false;
        while (!userStopped) {
            Scanner scanner = new Scanner(System.in);
            userStopped = scanner.nextLine().toLowerCase().equals("x");
        }
    }
}
