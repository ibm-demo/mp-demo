package dev.appsody.starter.rest;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import dev.appsody.starter.client.HttpBinRestClient;
import dev.appsody.starter.client.UnknownUrlException;

@Path("/")
public class HttpBinWrapper {

    Logger LOG = Logger.getLogger(HttpBinWrapper.class.getName());

    @Inject
    @RestClient
    private HttpBinRestClient httpBinRestClient;

    @GET
    @Path("properties")
    @Produces(MediaType.APPLICATION_JSON)
    public Properties getProperties() {
        System.getProperties().put("hello", "world");
        return System.getProperties();
    }

    @GET
    @Path("status/{statusCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getByStatusCode(@PathParam("statusCode") String statusCode) {
        LOG.fine("getByStatusCode:" + statusCode);
        try {
            return httpBinRestClient.getStatus(statusCode);
        } catch (ProcessingException | UnknownUrlException e) {
            LOG.log(Level.SEVERE, "Exception processing getByStatusCode", e);
            return "error";
        }
    }

    @GET
    @Path("delay/{delay}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDelay(@PathParam("delay") String delay) {
        LOG.fine("getDelay:" + delay);
        try {
            return httpBinRestClient.getDelay(delay);
        } catch (ProcessingException | UnknownUrlException e) {
            LOG.log(Level.SEVERE, "Exception processing getDelay", e);
            return "error";
        }
    }

    @GET
    @Path("/anything/{anything}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnything(@PathParam("anything") String anything) {
        LOG.fine("getAnything:" + anything);
        try {
            return httpBinRestClient.getAnything(anything);
        } catch (ProcessingException | UnknownUrlException e) {
            LOG.log(Level.SEVERE, "Exception processing getAnything", e);
            return "error";
        }
    }

    @GET
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    public Properties getJson() {
        LOG.fine("getJson:");
        try {
            return httpBinRestClient.getJson();
        } catch (ProcessingException | UnknownUrlException e) {
            LOG.log(Level.SEVERE, "Exception processing getJson", e);
            Properties props = new Properties();
            props.put("Error", e.getMessage());
            return props;
        }
    }
}
