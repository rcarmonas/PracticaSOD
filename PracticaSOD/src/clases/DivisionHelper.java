package clases;


/**
* clases/DivisionHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interfaz.idl
* miércoles 8 de mayo de 2013 09H08' CEST
*/

abstract public class DivisionHelper
{
  private static String  _id = "IDL:clases/Division:1.0";

  public static void insert (org.omg.CORBA.Any a, clases.Division that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static clases.Division extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = clases.TrabajoHelper.type ();
          _members0[0] = new org.omg.CORBA.StructMember (
            "trabajo",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
          _members0[1] = new org.omg.CORBA.StructMember (
            "c",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (clases.DivisionHelper.id (), "Division", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static clases.Division read (org.omg.CORBA.portable.InputStream istream)
  {
    clases.Division value = new clases.Division ();
    value.trabajo = clases.TrabajoHelper.read (istream);
    value.c = istream.read_char ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, clases.Division value)
  {
    clases.TrabajoHelper.write (ostream, value.trabajo);
    ostream.write_char (value.c);
  }

}
