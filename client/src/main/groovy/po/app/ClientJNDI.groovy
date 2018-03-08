package po.app

import po.po.dao.record.services.RecordProcessingService
import po.po.domain.Record

import javax.naming.Context
import javax.naming.InitialContext

/**
 * MAIN CLASS SECTION
 */
Properties jndiProps = new Properties()

jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory")

jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080")

jndiProps.put("jboss.naming.client.ejb.context", true)

Context jndi = new InitialContext(jndiProps)

RecordProcessingService service = (RecordProcessingService) jndi.lookup("galaxy/RecordProcessingImpl!po.po.dao.record.services.RecordProcessingService")

getAllRecords(service)

//addNewRecord(service)

def recordId = 1l;
// removeRecord(service, recordId)

findRecord(service, "MD.")

/**
 *  METHODS SECTION
 */
private static removeRecord(RecordProcessingService service, long recordId) {
        service.removeRecord(recordId)
    }

private static Iterable<Record> findRecord(RecordProcessingService service, String searching) {
    printf("searching for ... %s", searching)
    service.findRecord(searching).each { println it }
}

private static addNewRecord(RecordProcessingService service) {
    service.addRecord(new Record("MD.", "Denys Popov"))
}

private static Iterable<Record> getAllRecords(RecordProcessingService service) {
    service.getRecords().each { println it }
}