package server;
import java.io.File;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

/**
 * Server program.
**/
public class RmiServer {
	public RmiServer() {
		TCP con = new TCP();
		// Setup RMI
		try{
			LocateRegistry.createRegistry(1099);
			File f1 = new File("./bin/");
			String codeBase=f1.getAbsoluteFile().toURI().toURL().toString();
			System.setProperty("java.rmi.server.codebase", codeBase);		
			RmiIntf r = new AverageTemp(con);
			Naming.rebind("rmi://localhost:1099/RMIServer", r);
		}
		catch (Exception e){
			System.out.println("Errors: "+ e);
			return;
		}
		// Start thread for sensor
		con.startTCP();
	}


	public static void main (String[] argv) {
		RmiServer s = new RmiServer();
		System.out.println(s.getClass().getName()+" starting...");
	}
}