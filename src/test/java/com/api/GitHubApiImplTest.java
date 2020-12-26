package com.api;


import com.parser.JSONParser;
import com.parser.JSONParserImpl;
import com.restreader.RestReader;
import com.restreader.RestReaderImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.logging.MockServerLogger;
import org.mockserver.socket.tls.KeyStoreFactory;
import util.MockData;

import javax.net.ssl.HttpsURLConnection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class GitHubApiImplTest {

    private static ClientAndServer mockServer;

    private GitHubApi api;

    private JSONParser jsonParser = new JSONParserImpl();

    private RestReader restReader = new RestReaderImpl();

    @BeforeClass
    public static void globalSetUp() {
        HttpsURLConnection.setDefaultSSLSocketFactory(new KeyStoreFactory(new MockServerLogger()).sslContext().getSocketFactory());
        mockServer = startClientAndServer(1080);

    }

    @Test
    public void testInit() {

        jsonParser = new JSONParserImpl();
        restReader = new RestReaderImpl();

        api = new GitHubApiImpl(restReader, jsonParser);

        assertNotNull(api);
    }

    @Test
    public void testGettingRepos() {

        api = new GitHubApiImpl(restReader, jsonParser);

        new MockServerClient("127.0.0.1", 1080)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/api.github.com/users/Oleg/repos"),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody(MockData.getMockResponseBody2())
                );

        api.setURL(MockData.getUrlPath2());

        List<String> repos = api.getRepos("Oleg");
        assertEquals("Fitenko", repos.get(0));
        assertEquals("tnko", repos.get(1));
        assertEquals("ST-MAN", repos.get(2));

    }

}
