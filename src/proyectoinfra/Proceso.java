package proyectoinfra;

import java.util.ArrayList;

public class Proceso {
    int rank; // identificador del mensaje (0 a p-1) con p = procesos
    String nombre;
    boolean estado = true;
    ArrayList <Mensaje> salida = new ArrayList<Mensaje>(); //Arreglo que contiene los mensaje enviados por un proceso
    ArrayList <Mensaje> entrada = new ArrayList<Mensaje>(); //Arreglo que contiene los mensaje recibidos por un proceso

    Proceso(int id, String name){
        this.rank = id;
        this.nombre = name;
    }
}

