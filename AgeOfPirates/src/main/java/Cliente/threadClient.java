package Cliente;

import java.io.*;

public class threadClient extends Thread{
   DataInputStream entrada;
   Form vcli;
   public threadClient (DataInputStream entrada,Form vcli) throws IOException
   {
      this.entrada=entrada;
      this.vcli=vcli;
   }
   @Override
   public void run()
   {
      String menser="";
      int opcion=0;
      
      while(true)
      {         
         try{
            opcion=entrada.readInt();
            
            switch(opcion)
            {
                case 1:
                  menser = entrada.readUTF();
                  vcli.mostrar(menser);
                    break;
                case 2:
                    menser=entrada.readUTF();
                    vcli.setEnemigo(menser);
                    break;
                case 3:
                    vcli.numeroJugador = entrada.readInt();
                    vcli.setEnemigo(entrada.readUTF());
                    break;
                    
            }
         }
         catch (IOException e){
            break;
         }
      }
   }
}
