package clases;

import java.io.BufferedReader;
import java.io.IOException;
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
		
			String linea[];
			if(args.length == 1)
			{
				String ip =InetAddress.getLocalHost().getHostAddress();
				
				linea= new String[]{"-ORBInitialPort",args[0],"-ORBServerHost",ip};
				writeIpPort(ip, args[0]);
			}
			else
			{
				linea= args;
			}
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
		  

	      
	      
	      System.out.println("ControladorServer listo y esperando...");

	      // wait for invocations from clients
	      orb.run();
	} 
	
    catch (Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }

	}
	
	/**
	 * Escribe los datos necesarios en un archivo en la web para ser accedidos desde el cliente
	 * @param ip Ip del servidor
	 * @param port Puerto del servidor
	 * @throws IOException
	 */
	static void writeIpPort(String ip, String port) throws IOException
	{
	      URL url = new URL("http://rcarmonas.16mb.com/sod.php?modo=servidor&ip="+ip+"&puerto="+port);
	      URLConnection con = url.openConnection();
	      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      in.close();
	}

}
