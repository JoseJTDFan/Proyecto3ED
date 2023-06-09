package Servidor;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Servidor {
    PantallaServer pantalla;
    public ArrayList<threadServer> hilosserver;
    Socket cliente;

    public Servidor(PantallaServer pant){
        this.pantalla = pant;
    }
    public void runServer()
    {
        try {

            ServerSocket serv = new ServerSocket(55555);
            pantalla.mostrar("Servidor iniciado");
            
            
            hilosserver = new ArrayList<threadServer>();
            int numJugador = 1;
            while (numJugador < 5)
            {
                cliente = serv.accept();
                pantalla.mostrar("Cliente " +numJugador+" conectado");
                threadServer hilo = new threadServer(cliente, this, numJugador);
                hilosserver.add(hilo);
                numJugador++;
                hilo.start();


            }
            //conectar el enemigo
            hilosserver.get(0).enemigo = hilosserver.get(2);
            hilosserver.get(1).enemigo = hilosserver.get(3);
            hilosserver.get(2).enemigo = hilosserver.get(0);
            hilosserver.get(3).enemigo = hilosserver.get(1);
            //conectar el enemigo2
            hilosserver.get(0).enemigo2 = hilosserver.get(3);
            hilosserver.get(1).enemigo2 = hilosserver.get(2);
            hilosserver.get(2).enemigo2 = hilosserver.get(1);
            hilosserver.get(3).enemigo2 = hilosserver.get(0);
            //conectar el enemigo3
            hilosserver.get(0).enemigo3 = hilosserver.get(1);
            hilosserver.get(1).enemigo3 = hilosserver.get(0);
            hilosserver.get(2).enemigo3 = hilosserver.get(3);
            hilosserver.get(3).enemigo3 = hilosserver.get(2);
            
            pantalla.mostrar("4 jugadores conectados");


            while (true)
            {
                
            }
            
        } catch (IOException ex) {
            pantalla.mostrar("ERROR ... en el servidor");
        }
    }







}
