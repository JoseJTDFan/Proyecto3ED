package Cliente;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class Cliente {
   public static String IP_SERVER = "localhost"; 
   Form ventanaCliente; 
   DataInputStream entrada = null;
   DataOutputStream salida = null;
   Socket cliente = null;
   String nomCliente;
   
   public Cliente(Form vent) throws IOException
   {      
      this.ventanaCliente=vent;
   }
   public void conexion() throws IOException 
   {
      try {
         cliente = new Socket(Cliente.IP_SERVER, 55555);
         entrada = new DataInputStream(cliente.getInputStream());
         salida = new DataOutputStream(cliente.getOutputStream());

         nomCliente = JOptionPane.showInputDialog("Introducir Nick :");

         ventanaCliente.setTitle(nomCliente);
         salida.writeUTF(nomCliente);
      } catch (IOException e) {
      }
      new threadClient(entrada, ventanaCliente).start();
   }
   
   //GETTET AND SETTER
   public String getNombre()
   {
      return nomCliente;
   }


   
}
