package proyectoinfra;

import java.util.ArrayList;

public class Proceso {
    int rank; // identificador del mensaje (0 a p-1) con p = procesos
    String nombre;
    String alias;
    boolean estado = true;
    ArrayList <Mensaje> salida = new ArrayList<>(); //Contiene los mensajes enviados por un proceso
    ArrayList <Mensaje> entrada = new ArrayList<>(); //Contiene los mensajes de entrada de un proceso
    ArrayList <Mensaje> recibido = new ArrayList<>();// Contiene los mensajes que ya han sido recibidos bajo la primitiva
    String waitfor ="";
    
    Proceso(int id, String name){
        this.rank = id;
        this.nombre = name;
    }
    
    void imprime_entrada(){
        for (int i=0; i<entrada.size();i++){
            System.out.print("\nMensaje Entrada#"+(i+1)+" - Proceso: "+ this.nombre+"\n");
            entrada.get(i).imprimir();
        }
    }
    
    void imprime_salida(){
        for (int i=0; i<salida.size();i++){
            System.out.print("\nMensaje Salida#"+(i+1)+" - Proceso: "+ this.nombre+"\n");
            salida.get(i).imprimir();
        }
    }
    
    void imprime_recibido(){
        for (int i=0; i<recibido.size();i++){
            System.out.print("\nMensaje Recibido#"+(i+1)+" - Proceso: "+ this.nombre+"\n");
            recibido.get(i).imprimir();
        }
    }
    
    
}