package clases;

/**
* clases/TrabajoHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interfaz.idl
* miércoles 27 de marzo de 2013 13H37' CET
*/

public final class TrabajoHolder implements org.omg.CORBA.portable.Streamable
{
  public clases.Trabajo value = null;

  public TrabajoHolder ()
  {
  }

  public TrabajoHolder (clases.Trabajo initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = clases.TrabajoHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    clases.TrabajoHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return clases.TrabajoHelper.type ();
  }

}
