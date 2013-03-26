package clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

public class Atacante {

	private String direccion;
	private int puerto;
	private Vector <String> combinaciones;
	
	public Atacante(String direccion, int puerto)
	{
		this.direccion=direccion;
		this.puerto=puerto;
		
	}
	public String atacar(String usuario,int maxLen){
		//DataOutputStream salida=new DataOutputStream(socket.getOutputStream());
		//DataInputStream entrada=new DataInputStream(socket.getInputStream());
		this.combinaciones=new Vector<String>();
		String cadena="USER "+usuario+" PASSWORD ";
		String alfabeto="";
		for(int i=32;i<126;i++)
			alfabeto=alfabeto+(char)i;
		char[] elementos = alfabeto.toCharArray();
		
		for(int i=1;i<=maxLen;i++)
			generarCombinaciones(elementos, "", i);
		
		for(int i=0;i<combinaciones.size();i++)
		{
			try{
				Socket socket=new Socket(this.direccion,this.puerto);
				
				InputStreamReader ir = new InputStreamReader(socket.getInputStream());
			    BufferedReader entrada=new BufferedReader(ir);
			    OutputStreamWriter or = new OutputStreamWriter(socket.getOutputStream());
			    BufferedWriter salida=new BufferedWriter(or);
			    
			    salida.write(cadena+combinaciones.get(i)+"\0");
			    salida.flush();
				
				String a=entrada.readLine();
				if(a.equals("true"))
				{
					socket.close();
					return(combinaciones.get(i));
				}
				socket.close();

			} catch (IOException e) {
			    return null;
			}
		}
		return null;
	}
	public void generarCombinaciones(char[] elementos, String actual, int cantidad) {
        if(cantidad==0) {
            this.combinaciones.add(actual);
        }
        else {
            for(int i=0; i<elementos.length; i++) {
                generarCombinaciones(elementos, actual+elementos[i],cantidad-1);
            }
        }
    }
}