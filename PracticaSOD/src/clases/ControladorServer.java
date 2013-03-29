package clases;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class ControladorServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
		// Crear e inicializar el ORB
	      ORB orb = ORB.init(args, null);

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

}
