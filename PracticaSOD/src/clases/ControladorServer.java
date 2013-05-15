package clases;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
/**
 * Servidor que se encarga de mantener el controlador del sistema
 * @author Rafael Carmona Sánchez
 * @author José Manuel Herruzo Ruiz
 */
public class ControladorServer {

	/**
	 * Ejecuta el servidor
	 * @param args Número de puerto donde se ejecutará
	 */
	public static void main(String[] args) {
		try{
			String ip =InetAddress.getLocalHost().getHostAddress();
			
			String linea []={"-ORBInitialPort",args[0],"-ORBServerHost",ip};
			
		  // Crear e inicializar el ORB
	      ORB orb = ORB.init(linea, null);

	      // Obtener la referencia del POA raiz
	      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

	      // Activar el POA raiz
	      rootpoa.the_POAManager().activate();

	      // Crear el Sirviente
	      ControladorImpl controlador = new ControladorImpl();

	      // Obtener una referencia CORBA del Sirviente
	      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(controlador);

	      // A partir de la referencia CORBA, creamos la Calculadora accesible
	      Controlador cref = ControladorHelper.narrow(ref);
		  
	      // Referencia CORBA al servidor de nombres
	      org.omg.CORBA.Object objRef =
	          orb.resolve_initial_references("NameService");
	      // Referencia contextual de dicho servidor
	      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

	      // Identificar el controlador con su nombre
	      String name = "Controlador";
	      NameComponent path[] = ncRef.to_name( name );
	      ncRef.rebind(path, cref);
		  
	      URL url = new URL("http://rcarmonas.16mb.com/sod.php?modo=servidor&ip="+ip+"&puerto="+args[0]);
	      URLConnection con = url.openConnection();
	      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      in.close();
	      
	      
	      System.out.println("ControladorServer listo y esperando...");

	      // wait for invocations from clients
	      orb.run();
	} 
	
    catch (Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }

	}

}
