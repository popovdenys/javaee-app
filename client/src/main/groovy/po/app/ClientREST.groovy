package po.app

import po.rest.domain.Record

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.core.GenericType
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * MAIN CLASS SECTION
 */
Client client = ClientBuilder.newClient()

// get Record by id
long id = 26
getRecord(client, id)

// update the Record
/*
Record updateRecord = new Record()
updateRecord.id = id
updateRecord.depart = "Popov"
updateRecord.descrition = "Denys"

updateRecord(updateRecord, client, id)
*/

// delete the Record
//deleteRecord(client, id)

// create a new Record

Record record = new Record()
record.depart = "Molière"
record.descrition = "Tartuffe"
addRecord(client, record)


// select all Records
//getRecords(client)

/**
 *  METHODS SECTION
 */
// get Record by id
private void getRecord(Client client, long id) {
    Response response = client.target("http://localhost:8080/galaxy/appwebservice/records/" + id)
            .request(MediaType.APPLICATION_XML_TYPE)
            .buildGet()
            .invoke()

    //println(response.readEntity(Record.class))

    // debug method section
    println("Header: " + response.getHeaders().toString())
    println("Status: " + response.getStatus())
    System.out.println(response.readEntity(String.class))

    response.getLinks().each { println "Link rel : " + it.getRel() + " ; method : " + it.getType() + " ; url : " + it.getUri() }

    response.close()
}

// select all Records
private void getRecords(Client client) {
    Response response = client.target("http://localhost:8080/galaxy/appwebservice/records")
            .request(MediaType.APPLICATION_XML_TYPE)
            .buildGet()
            .invoke()

    //System.out.println(response.readEntity(String.class))
    response.readEntity(new GenericType<List<Record>>() {})
            .each { println it }

    response.close()
}

// create a new Record
private void addRecord(Client client, Record record) {

    Entity recordEntity = Entity.entity(record, "application/JSON")
    Response response = client.target("http://localhost:8080/galaxy/appwebservice/records")
            .request()
            .buildPost(recordEntity)
            .invoke()

    println(String.format("adding Record returned status code : %d", response.getStatus()))
    if(response.getStatus() == 201 ) {
        println(response.getHeaders().toString())
    }

    response.close()
}

// delete the Record
private void deleteRecord(Client client, long id) {
    println( String.format("Trying to delete the Record with id = %d", id ) )
    Response response = client.target("http://localhost:8080/galaxy/appwebservice/records/" + id)
            .request()
            .buildDelete()
            .invoke()

    println("Delete status returned : " + response.getStatus())

    response.close()
}

// update the Record
private void updateRecord(Record updateRecord, Client client, long id) {

    Entity updateRecordEntity = Entity.entity(updateRecord, "application/JSON")
    Response response = client.target("http://localhost:8080/galaxy/appwebservice/records/" + id)
            .request(MediaType.APPLICATION_XML_TYPE)
            .buildPut(updateRecordEntity)
            .invoke()

    println("Update status: " + response.getStatus())
    println(response.readEntity(String.class))

    response.close()
}