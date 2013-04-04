package clases;


/**
* clases/ControladorPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interfaz.idl
* jueves 4 de abril de 2013 09H48' CEST
*/

public abstract class ControladorPOA extends org.omg.PortableServer.Servant
 implements clases.ControladorOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_trabajos", new java.lang.Integer (0));
    _methods.put ("_set_trabajos", new java.lang.Integer (1));
    _methods.put ("finTrabajo", new java.lang.Integer (2));
    _methods.put ("borrarTrabajo", new java.lang.Integer (3));
    _methods.put ("getDivision", new java.lang.Integer (4));
    _methods.put ("getTrabajo", new java.lang.Integer (5));
    _methods.put ("setDivision", new java.lang.Integer (6));
    _methods.put ("dividirTrabajo", new java.lang.Integer (7));
    _methods.put ("crearMD5", new java.lang.Integer (8));
    _methods.put ("crearSHA", new java.lang.Integer (9));
    _methods.put ("crearRed", new java.lang.Integer (10));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // clases/Controlador/_get_trabajos
       {
         clases.Trabajo $result[] = null;
         $result = this.trabajos ();
         out = $rh.createReply();
         clases.ControladorPackage.trabajosListHelper.write (out, $result);
         break;
       }

       case 1:  // clases/Controlador/_set_trabajos
       {
         clases.Trabajo newTrabajos[] = clases.ControladorPackage.trabajosListHelper.read (in);
         this.trabajos (newTrabajos);
         out = $rh.createReply();
         break;
       }

       case 2:  // clases/Controlador/finTrabajo
       {
         int id = in.read_ulong ();
         String clave = in.read_string ();
         this.finTrabajo (id, clave);
         out = $rh.createReply();
         break;
       }

       case 3:  // clases/Controlador/borrarTrabajo
       {
         int id = in.read_ulong ();
         this.borrarTrabajo (id);
         out = $rh.createReply();
         break;
       }

       case 4:  // clases/Controlador/getDivision
       {
         clases.Division $result = null;
         $result = this.getDivision ();
         out = $rh.createReply();
         clases.DivisionHelper.write (out, $result);
         break;
       }

       case 5:  // clases/Controlador/getTrabajo
       {
         int id = in.read_ulong ();
         clases.Trabajo $result = null;
         $result = this.getTrabajo (id);
         out = $rh.createReply();
         clases.TrabajoHelper.write (out, $result);
         break;
       }

       case 6:  // clases/Controlador/setDivision
       {
         clases.Division t = clases.DivisionHelper.read (in);
         boolean $result = false;
         $result = this.setDivision (t);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 7:  // clases/Controlador/dividirTrabajo
       {
         clases.Trabajo t = clases.TrabajoHelper.read (in);
         this.dividirTrabajo (t);
         out = $rh.createReply();
         break;
       }

       case 8:  // clases/Controlador/crearMD5
       {
         String cadena = in.read_string ();
         int tam_maximo = in.read_ulong ();
         clases.Trabajo $result = null;
         $result = this.crearMD5 (cadena, tam_maximo);
         out = $rh.createReply();
         clases.TrabajoHelper.write (out, $result);
         break;
       }

       case 9:  // clases/Controlador/crearSHA
       {
         String cadena = in.read_string ();
         int tam_maximo = in.read_ulong ();
         clases.Trabajo $result = null;
         $result = this.crearSHA (cadena, tam_maximo);
         out = $rh.createReply();
         clases.TrabajoHelper.write (out, $result);
         break;
       }

       case 10:  // clases/Controlador/crearRed
       {
         String cadena = in.read_string ();
         int puerto = in.read_ulong ();
         String usuario = in.read_string ();
         int tam_maximo = in.read_ulong ();
         clases.Trabajo $result = null;
         $result = this.crearRed (cadena, puerto, usuario, tam_maximo);
         out = $rh.createReply();
         clases.TrabajoHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:clases/Controlador:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Controlador _this() 
  {
    return ControladorHelper.narrow(
    super._this_object());
  }

  public Controlador _this(org.omg.CORBA.ORB orb) 
  {
    return ControladorHelper.narrow(
    super._this_object(orb));
  }


} // class ControladorPOA
