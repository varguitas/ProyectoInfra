
package proyectoinfra;

import java.util.ArrayList;

public class Mensajeria {
    ArrayList <Proceso> general = new ArrayList<Proceso>();
    int tamaño;

    Mensajeria(int tamano){
        this.tamaño = tamano;
        for (int i=0;i<tamano;i++){
        this.general.add(new Proceso(i+1,"P"+(i+1)+""));
        }
    }
    
    void imprimir(){
        for (int i=0;i<(this.tamaño);i++){
           System.out.print("Proceso #: "+general.get(i).rank +"\n");
           System.out.print("Nombre: "+general.get(i).nombre +"\n");
        }
    }
    
    void send(String origen, String destino, String msj){
        Mensaje mensaje = new Mensaje(origen,destino,msj);
        for (int i=0;i<(this.tamaño);i++){            
           if (general.get(i).nombre.equals(destino)){
               (general.get(i).entrada).add(mensaje);//Agregra el mensaje al buzón de entrada del proceso correspondiente
           }
           if (general.get(i).nombre.equals(origen)){
               (general.get(i).salida).add(mensaje);//Agregra el mensaje al buzón de entrada del proceso correspondiente
           }
        }
    }
}