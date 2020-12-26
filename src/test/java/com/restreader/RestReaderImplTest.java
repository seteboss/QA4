package com.restreader;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.logging.MockServerLogger;
import org.mockserver.socket.tls.KeyStoreFactory;
import util.MockData;

import javax.net.ssl.HttpsURLConnection;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class RestReaderImplTest {

    private static ClientAndServer mockServer;

    private RestReader restReader;

    @BeforeClass
    public static void globalSetUp() {
        HttpsURLConnection.setDefaultSSLSocketFactory(new KeyStoreFactory(new MockServerLogger()).sslContext().getSocketFactory());
        mockServer = startClientAndServer(1080);

    }

    @Test
    public void testGettingJsonString() {

        restReader = new RestReaderImpl();

        new MockServerClient("127.0.0.1", 1080)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/testRest"),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody(MockData.getMockResponseBody1())
                );
        Optional<String> jsonString = restReader.getJsonString(MockData.getUrlPath1());
        assertEquals(MockData.getMockResponseBody1(), jsonString.get());
    }


}
