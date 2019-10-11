// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2018, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
// end::copyright[]
package it.io.openliberty.sample;

import static org.junit.Assert.assertEquals;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HealthEndpointTest {
    
    private static String baseUrl;
    private static final String HEALTH_ENDPOINT = "/health";
    private static final String LIVENESS_ENDPOINT = "/health/live";
    private static final String READINESS_ENDPOINT = "/health/ready";
    
    private Client client;
    private Response response;
    
    @BeforeClass
    public static void oneTimeSetup() {
        String port = System.getProperty("liberty.test.port");
        baseUrl = "http://localhost:" + port;
    }
    
    @Before
    public void setup() {
        response = null;
        client = ClientBuilder.newClient();
        client.register(JsrJsonpProvider.class);
    }
    
    @After
    public void teardown() {
        response.close();
        client.close();
    }

    @Test
    public void testHealthEndpoint() {
        String healthURL = baseUrl + HEALTH_ENDPOINT;
        response = this.getResponse(baseUrl + HEALTH_ENDPOINT);
        this.assertResponse(healthURL, response);
        
        JsonObject healthJson = response.readEntity(JsonObject.class);
        String expectedOutcome = "UP";
        String actualOutcome = healthJson.getString("status");
        assertEquals("Application should be healthy", expectedOutcome, actualOutcome);
       
        JsonObject healthCheck = healthJson.getJsonArray("checks").getJsonObject(0);
        String healthCheckName = healthCheck.getString("name");
        actualOutcome = healthCheck.getString("status");
        assertEquals(healthCheckName + " wasn't healthy", expectedOutcome, actualOutcome);

        healthCheck = healthJson.getJsonArray("checks").getJsonObject(1);
        healthCheckName = healthCheck.getString("name");
        actualOutcome = healthCheck.getString("status");
        assertEquals(healthCheckName + " wasn't healthy", expectedOutcome, actualOutcome);
    }

    @Test
    public void testLivenessEndpoint() {
        String livenessURL = baseUrl + LIVENESS_ENDPOINT;
        response = this.getResponse(baseUrl + LIVENESS_ENDPOINT);
        this.assertResponse(livenessURL, response);
        
        JsonObject healthJson = response.readEntity(JsonObject.class);
        String expectedOutcome = "UP";
        String actualOutcome = healthJson.getString("status");
        assertEquals("Applications liveness check passed", expectedOutcome, actualOutcome);
    }

    @Test
    public void testReadinessEndpoint() {
        String readinessURL = baseUrl + READINESS_ENDPOINT;
        response = this.getResponse(baseUrl + READINESS_ENDPOINT);
        this.assertResponse(readinessURL, response);
        
        JsonObject healthJson = response.readEntity(JsonObject.class);
        String expectedOutcome = "UP";
        String actualOutcome = healthJson.getString("status");
        assertEquals("Applications readiness check passed", expectedOutcome, actualOutcome);
    }
   
    /**
     * <p>
     * Returns response information from the specified URL.
     * </p>
     *
     * @param url
     *          - target URL.
     * @return Response object with the response from the specified URL.
     */
    private Response getResponse(String url) {
        return client.target(url).request().get();
    }

    /**
     * <p>
     * Asserts that the given URL has the correct response code of 200.
     * </p>
     *
     * @param url
     *          - target URL.
     * @param response
     *          - response received from the target URL.
     */
    private void assertResponse(String url, Response response) {
        assertEquals("Incorrect response code from " + url, 200, response.getStatus());
    }

}
