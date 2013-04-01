package clases;


/**
* clases/TrabajoHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interfaz.idl
* lunes 1 de abril de 2013 18H16' CEST
*/

abstract public class TrabajoHelper
{
  private static String  _id = "IDL:clases/Trabajo:1.0";

  public static void insert (org.omg.CORBA.Any a, clases.Trabajo that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static clases.Trabajo extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [9];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_boolean);
          _members0[0] = new org.omg.CORBA.StructMember (
            "borrado",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ulong);
          _members0[1] = new org.omg.CORBA.StructMember (
            "id",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ulong);
          _members0[2] = new org.omg.CORBA.StructMember (
            "tipo",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "cadena",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ulong);
          _members0[4] = new org.omg.CORBA.StructMember (
            "puerto",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[5] = new org.omg.CORBA.StructMember (
            "usuario",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ulong);
          _members0[6] = new org.omg.CORBA.StructMember (
            "tam_maximo",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ulong);
          _members0[7] = new org.omg.CORBA.StructMember (
            "progress",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[8] = new org.omg.CORBA.StructMember (
            "resultado",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (clases.TrabajoHelper.id (), "Trabajo", _members0);
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

  public static clases.Trabajo read (org.omg.CORBA.portable.InputStream istream)
  {
    clases.Trabajo value = new clases.Trabajo ();
    value.borrado = istream.read_boolean ();
    value.id = istream.read_ulong ();
    value.tipo = istream.read_ulong ();
    value.cadena = istream.read_string ();
    value.puerto = istream.read_ulong ();
    value.usuario = istream.read_string ();
    value.tam_maximo = istream.read_ulong ();
    value.progress = istream.read_ulong ();
    value.resultado = istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, clases.Trabajo value)
  {
    ostream.write_boolean (value.borrado);
    ostream.write_ulong (value.id);
    ostream.write_ulong (value.tipo);
    ostream.write_string (value.cadena);
    ostream.write_ulong (value.puerto);
    ostream.write_string (value.usuario);
    ostream.write_ulong (value.tam_maximo);
    ostream.write_ulong (value.progress);
    ostream.write_string (value.resultado);
  }

}
