package clases.ControladorPackage;


/**
* clases/ControladorPackage/trabajosListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interfaz.idl
* viernes 29 de marzo de 2013 19H41' CET
*/

public final class trabajosListHolder implements org.omg.CORBA.portable.Streamable
{
  public clases.Trabajo value[] = null;

  public trabajosListHolder ()
  {
  }

  public trabajosListHolder (clases.Trabajo[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = clases.ControladorPackage.trabajosListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    clases.ControladorPackage.trabajosListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return clases.ControladorPackage.trabajosListHelper.type ();
  }

}