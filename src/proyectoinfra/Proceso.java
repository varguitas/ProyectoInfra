package proyectoinfra;

import java.util.ArrayList;

public class Proceso {
    int rank; // identificador del mensaje (0 a p-1) con p = procesos
    String nombre;
    boolean estado = true;
    ArrayList <Mensaje> salida = new ArrayList<Mensaje>(); //Contiene los mensajes enviados por un proceso
    ArrayList <Mensaje> entrada = new ArrayList<Mensaje>(); //Contiene los mensajes de entrada de un proceso
    ArrayList <Mensaje> recibido = new ArrayList<Mensaje>();// Contiene los mensajes que ya han sido recibidos bajo la primitiva
    
    Proceso(int id, String name){
        this.rank = id;
        this.nombre = name;
    }
    
    void imprime_entrada(){
        for (int i=0; i<entrada.size();i++){
            System.out.print("Mensaje Entrada#"+(i+1)+" - Proceso: "+ this.nombre+"\n");
            entrada.get(i).imprimir();
        }
    }
    
    void imprime_salida(){
        for (int i=0; i<salida.size();i++){
            System.out.print("Mensaje Salida#"+(i+1)+" - Proceso: "+ this.nombre+"\n");
            salida.get(i).imprimir();
        }
    }
    
    void imprime_recibido(){
        for (int i=0; i<recibido.size();i++){
            System.out.print("Mensaje Recibido#"+(i+1)+" - Proceso: "+ this.nombre+"\n");
            recibido.get(i).imprimir();
        }
    }
    String get_name(){
        return this.nombre;
    }
}