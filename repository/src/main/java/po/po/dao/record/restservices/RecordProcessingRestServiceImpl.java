package po.po.dao.record.restservices;

import po.po.dao.exceptions.record.NoRecordException;
import po.po.exceptions.ServiceUnavailableException;
import po.po.dao.record.services.RecordProcessingServiceLocal;
import po.po.domain.Record;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

// @Stateless
// @RequestScoped
@Path( "/records" )
public class RecordProcessingRestServiceImpl {

    @Inject
    private RecordProcessingServiceLocal service;

    @Context
    private  UriInfo uriInfo;
/*
    @GET
    @Produces( { "application/JSON", "application/XML" } )
    public List<Record> getRecords() {
        return (List<Record>) service.getRecords();
    }
*/
    @GET
    @Produces( { "application/JSON", "application/XML" } )
    public Response getRecordsBetween( @DefaultValue("0") @QueryParam("startId") Long startId, @QueryParam("endId") Long endId ) {
    //@Path( "{id1}-{id2}" )
    //public Response getRecordsBetween( @PathParam("id1") Long startId, @PathParam("id2") Long endId ) {
        if( (endId != null) && (startId < endId ) ) {
            GenericEntity<List<Record>> records = new GenericEntity<>( service.getRecordsBetween(startId, endId) ) {};
            return Response.ok( records ).build();
        }

        if( endId == null ) {
            GenericEntity<List<Record>> records = new GenericEntity<>( service.getRecords() ) {};
            return Response.ok( records ).build();
        }
        return Response.status(404).build();
    }

    @GET
    @Produces( { "application/JSON", "application/XML" } )
    @Path( "{id}" )
    public Response getRecord( @PathParam("id") long id, @Context HttpHeaders headers ) {
        System.out.println(headers.getRequestHeaders());
        try {
            Record record = service.getRecord( id );

            Link selfLink = Link.fromUri(uriInfo.getAbsolutePath()).rel("self").type("get").build();
            Link deleteLink = Link.fromUri(uriInfo.getAbsolutePath()).rel("delete").type("delete").build();
            Link updateLink = Link.fromUri(uriInfo.getAbsolutePath()).rel("update").type("put").build();

            return Response.ok( record ).links( selfLink, updateLink, deleteLink ).build();
        } catch (NoRecordException e) {
            return Response.status(404).build();
        }
    }

    @POST
    @Produces( { "application/JSON", "application/XML" } )
    @Consumes( { "application/JSON", "application/XML" } )
    public Response createRecord(Record record) {
        try {
            service.addRecord(record);

            URI uri = null;
            try { uri = new URI(uriInfo.getAbsolutePath()+ "/" + record.getId()); }
            catch (URISyntaxException e) { }

            return Response.created(uri).build();

        } catch (ServiceUnavailableException e) {
            return Response.status(504).build();
        }
    }

    @DELETE
    @Path( "{id}" )
    public Response deleteRecord( @PathParam("id") long id ) {
        try {
            service.removeRecord(id);
            return  Response.status(204).build();
        } catch (NoRecordException e) {
            return  Response.status(404).build();
        }
    }

    @PUT
    @Path( "{id}" )
    @Produces( { "application/JSON", "application/XML" } )
    @Consumes( { "application/JSON", "application/XML" } )
    public Response updateRecord( @PathParam("id") long id, Record record) {
        try {
            service.updateRecord(record);
            return Response.ok( service.getRecord( id ) ).build();
        } catch (NoRecordException e) {
            return  Response.status(404).build();
        }
    }
}
