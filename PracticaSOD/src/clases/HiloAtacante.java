package clases;

public class HiloAtacante extends Thread {
	private boolean activo;
	private ControladorImpl ctrl;
	private Division trabajo;
	private boolean encontrado;
	private String resultado;
	
	HiloAtacante(ControladorImpl ctrl)
	{
		activo = true;
		this.ctrl = ctrl;
	}
	
	public void run()
	{
		while(activo)
		{
			//Inicialización de datos
			this.encontrado = false;
			this.resultado = null;
			StringBuffer cad = new StringBuffer();
			//Obtengo un trabajo:
			trabajo = ctrl.getDivision();
			//Añade la primera letra:
			cad.append(trabajo.c);
			//Comprueba todas las cadenas de forma recursiva:
			probarCadenas(cad); 
			//Comunica el fin de un trabajo:
			ctrl.finTrabajo(trabajo.trabajo.id, resultado);
		}
	}
	
	/**
	 * Para el hilo e informa al controlador de que el trabajo no se ha terminado
	 */
	public void disactive()
	{
		if(this.trabajo!=null && !this.encontrado)
			ctrl.setDivision(trabajo);
		this.activo = false;
	}
	/**
	 * Prueba de forma recursiva todas las cadenas posibles hasta encontrar
	 * un resultado o terminar
	 * @param str Cadena que llevamos por ahora
	 */
	private void probarCadenas(StringBuffer str)
	{
		if(trabajo.trabajo.tipo == ControladorImpl.MD5)
		{   //En caso de tratarse de un trabajo MD5:
			String aux = StrManager.MD5(str.toString());
			if(aux.equals(trabajo.trabajo.cadena))
			{
				this.encontrado = true;
				this.resultado = aux;
			}
		} else if(trabajo.trabajo.tipo == ControladorImpl.SHA)
		{	//En caso de tratarse de un trabajo SHA1:
			String aux = StrManager.SHA(str.toString());
			if(aux.equals(trabajo.trabajo.cadena))
			{
				this.encontrado = true;
				this.resultado = aux;
			}
		}else if(trabajo.trabajo.tipo == ControladorImpl.RED)
		{	//En caso de tratarse de un trabajo de red:
			//Probar red -> Hazlo tú!
		}
		
		if(str.length()<trabajo.trabajo.tam_maximo && activo && !encontrado)
			for(char j=(char)StrManager.INICIO;j<StrManager.FIN; j++)
			{
				str.append(j);
				probarCadenas(str);
				str.deleteCharAt(str.length()-1);
			}
	}
}
