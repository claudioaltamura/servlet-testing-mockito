package de.claudioaltamura.servlet.testing;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelloWorldServletIntegrationTest {

	private static final int PORT = 8888;
	private JettyServer jettyTestServer;

	@Before
	public void setUp() throws Exception {
		jettyTestServer = new JettyServer(PORT);
		jettyTestServer.start();
	}

	@After
	public void tearDown() throws Exception {
		jettyTestServer.stop();
	}

	@Test
	public void helloworld() throws ClientProtocolException, IOException {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient httpClient = httpClientBuilder.build();
		HttpGet request = new HttpGet("http://localhost:" + PORT + "/helloworld");

		CloseableHttpResponse response = httpClient.execute(request);
		String responseAsString = EntityUtils.toString(response.getEntity());
		assertEquals("Hello World!", responseAsString);
	}
}
