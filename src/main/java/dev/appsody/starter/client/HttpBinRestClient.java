package dev.appsody.starter.client;

import java.util.Properties;
import javax.enterprise.context.Dependent;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Dependent
@RegisterRestClient
@RegisterProvider(UnknownUrlExceptionMapper.class)
@Path("/")
public interface HttpBinRestClient {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("status/{codes}")
  public String getStatus(@PathParam("codes") String codes) throws UnknownUrlException, ProcessingException;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("delay/{delay}")
  public String getDelay(@PathParam("delay") String delay) throws UnknownUrlException, ProcessingException;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("anything/{anything}")
  public String getAnything(@PathParam("anything") String anything) throws UnknownUrlException, ProcessingException;

  @GET
  @Path("json")
  @Produces(MediaType.APPLICATION_JSON)
  public Properties getJson() throws UnknownUrlException, ProcessingException;
}
