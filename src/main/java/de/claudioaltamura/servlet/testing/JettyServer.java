package de.claudioaltamura.servlet.testing;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

public class JettyServer {

	private static final Log log = LogFactory.getLog(JettyServer.class);

	private Server server;
	private int port;

	public JettyServer(int port) {
		this.port = port;
	}

	public void start() throws Exception {
		server = new Server(port);
		server.addBean(log);

		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		String webxmlLocation = JettyServer.class.getResource("/webapp/WEB-INF/web.xml").toString();
		webAppContext.setDescriptor(webxmlLocation);

		String resLocation = JettyServer.class.getResource("/webapp").toString();
		webAppContext.setResourceBase(resLocation);

		webAppContext.setParentLoaderPriority(true);

		webAppContext.getMetaData().addContainerResource(Resource.newResource(
				new File(JettyServer.class.getProtectionDomain().getCodeSource().getLocation().getPath())));

		webAppContext.setConfigurations(new Configuration[] { new WebInfConfiguration(), new WebXmlConfiguration(),
				new MetaInfConfiguration(), new FragmentConfiguration(), new EnvConfiguration(),
				new PlusConfiguration(), new AnnotationConfiguration(), new JettyWebXmlConfiguration() });

		server.setHandler(webAppContext);
		server.start();
	}

	public void stop() throws Exception {
		server.stop();
		server.join();
	}

}
