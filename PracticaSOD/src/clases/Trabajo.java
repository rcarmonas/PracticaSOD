package clases;


/**
* clases/Trabajo.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interfaz.idl
* domingo 31 de marzo de 2013 11H00' CEST
*/

public final class Trabajo implements org.omg.CORBA.portable.IDLEntity
{
  public int id = (int)0;
  public int tipo = (int)0;
  public String cadena = null;
  public int puerto = (int)0;
  public String usuario = null;
  public int tam_maximo = (int)0;
  public int progress = (int)0;
  public String resultado = null;

  public Trabajo ()
  {
  } // ctor

  public Trabajo (int _id, int _tipo, String _cadena, int _puerto, String _usuario, int _tam_maximo, int _progress, String _resultado)
  {
    id = _id;
    tipo = _tipo;
    cadena = _cadena;
    puerto = _puerto;
    usuario = _usuario;
    tam_maximo = _tam_maximo;
    progress = _progress;
    resultado = _resultado;
  } // ctor

} // class Trabajo
