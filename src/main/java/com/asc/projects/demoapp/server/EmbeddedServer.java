package com.asc.projects.demoapp.server;

import java.net.URL;
import java.security.ProtectionDomain;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Starts the Jetty Server.
 * @author Abraham Soto
 */
public class EmbeddedServer {
    
    public static void main(String[] args) {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        
        //TODO: HTTPS Connector code goes here
        
        server.setConnectors(new Connector[]{ connector });
        WebAppContext context = new WebAppContext();
        context.setServer(server);
        context.setContextPath("/");
        
        ProtectionDomain pd = EmbeddedServer.class.getProtectionDomain();
        URL location = pd.getCodeSource().getLocation();
        context.setWar(location.toExternalForm());
        
        server.setHandler(context);
        
        //Start Jetty Server
        while(true){
            try {
                server.start();
                break;
            } catch (Exception ex) {
                Logger.getLogger(EmbeddedServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            System.in.read();
            server.stop();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(EmbeddedServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
