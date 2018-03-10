package po.messaging

import po.po.chul.messaging.sender.RecordControlService
import po.po.exceptions.ServiceUnavailableException

import javax.jms.Connection
import javax.naming.Context
import javax.naming.InitialContext

Properties jndiProperties = new Properties()
jndiProperties.put("jboss.naming.client.ejb.context", "true")
jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory")
jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080")

Connection connection = null

Context jndi = new InitialContext(jndiProperties)
RecordControlService service = jndi.lookup("galaxy/RecordControlServiceImpl!po.po.chul.RecordControlService")

try {
    service.registerNewChulRecord(1l, "Test EJB", "Popov")
} catch (ServiceUnavailableException e) {
    println("The queue was unavailable")
}

