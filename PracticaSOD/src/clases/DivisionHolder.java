package clases;

/**
* clases/DivisionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interfaz.idl
* viernes 29 de marzo de 2013 17H06' CET
*/

public final class DivisionHolder implements org.omg.CORBA.portable.Streamable
{
  public clases.Division value = null;

  public DivisionHolder ()
  {
  }

  public DivisionHolder (clases.Division initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = clases.DivisionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    clases.DivisionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return clases.DivisionHelper.type ();
  }

}
