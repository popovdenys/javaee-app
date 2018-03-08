package po.java.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class ClientJavaREST {

    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/galaxy/appwebservice/records/1")
                .request()
                .buildGet()
                .invoke();

        System.out.println(response.readEntity(Record.class));

        response.close();
    }
}
