package de.claudioaltamura.servlet.testing;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.ServletConfig;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class HelloWorldServletTest {

	@Spy
	private HelloWorldServlet servlet;
	
	@Mock
	private ServletConfig servletConfig;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private ServletOutputStream outputStream;

	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() throws Exception {
		when(servlet.getServletConfig()).thenReturn(servletConfig);
		when(response.getOutputStream()).thenReturn(outputStream);

		servlet.doGet(request, response);

		verify(outputStream).println("Hello World!");
	}
}
