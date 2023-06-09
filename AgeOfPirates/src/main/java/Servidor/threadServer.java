package Servidor;

import java.io.*;
import java.net.*;

public class threadServer extends Thread{
    Socket cliente = null;
    DataInputStream entrada=null;
    DataOutputStream salida=null;	
    String nameUser;
    Servidor server;


    threadServer enemigo = null;
    threadServer enemigo2 = null;
    threadServer enemigo3 = null;
    int numeroDeJugador;
     
    public threadServer(Socket cliente, Servidor serv, int num){
        this.cliente = cliente;
        this.server = serv;
        this.numeroDeJugador = num;
        nameUser="";
     }
     
    public String getNameUser()
     {
       return nameUser;
     }
     public void setNameUser(String name)
     {
       nameUser=name;
     }
     
     @Override
     public void run()
     {
    	try
    	{
          entrada=new DataInputStream(cliente.getInputStream());
          salida=new DataOutputStream(cliente.getOutputStream());
          
          System.out.println("lee el nombre");
          this.setNameUser(entrada.readUTF());
          enviaUser();
    	}
    	catch (IOException e) {  
            e.printStackTrace();     
        }
        int opcion=0;
                
    	while(true)
    	{
          try
          {
             opcion=entrada.readInt();
             switch(opcion)
             {
                 case 1:
                     String mensaje = entrada.readUTF();
                     enemigo.salida.writeInt(1);
                     enemigo.salida.writeUTF(mensaje);
                     enemigo2.salida.writeInt(1);
                     enemigo2.salida.writeUTF(mensaje);
                     enemigo3.salida.writeInt(1);
                     enemigo3.salida.writeUTF(mensaje);
                     break;
                case 2:
                    salida.writeInt(2);
                    salida.writeInt(numeroDeJugador);
                    if (enemigo != null)
                        salida.writeUTF(enemigo.nameUser);
                    else
                        salida.writeUTF("");
                    if (enemigo2 != null)
                        salida.writeUTF(enemigo2.nameUser);
                    else
                        salida.writeUTF("");
                    if (enemigo3 != null)
                        salida.writeUTF(enemigo3.nameUser);
                    else
                        salida.writeUTF("");
                    break;
             }
          }
          catch (IOException e) {
            break;
          }
    	}
    	
    	try
    	{
           server.pantalla.mostrar("Se desconecto un usuario: "+nameUser);
          cliente.close();
    	}	
        catch(Exception et){
        }   
    }

    public void enviaUser()
     {
        if (enemigo != null)
        {
        try
        {
            enemigo.salida.writeInt(2);//escribe opcion de agregar 2
            enemigo.salida.writeUTF(this.getNameUser());//escribe nombre	
        }
        catch (IOException e) {
        
        }
        }
     }
}
