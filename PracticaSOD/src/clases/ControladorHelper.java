package clases;


/**
* clases/ControladorHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interfaz.idl
* jueves 28 de marzo de 2013 09H18' CET
*/

abstract public class ControladorHelper
{
  private static String  _id = "IDL:clases/Controlador:1.0";

  public static void insert (org.omg.CORBA.Any a, clases.Controlador that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static clases.Controlador extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (clases.ControladorHelper.id (), "Controlador");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static clases.Controlador read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ControladorStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, clases.Controlador value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static clases.Controlador narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof clases.Controlador)
      return (clases.Controlador)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      clases._ControladorStub stub = new clases._ControladorStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static clases.Controlador unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof clases.Controlador)
      return (clases.Controlador)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      clases._ControladorStub stub = new clases._ControladorStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
