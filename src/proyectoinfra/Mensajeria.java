
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
    
    void send(String origen, String destino, String msj){
        Mensaje mensaje = new Mensaje(origen,destino,msj);
        for (int i=0;i<(this.tamaño);i++){            
           if (general.get(i).nombre.equals(destino)){
               (general.get(i).entrada).add(mensaje);//Agrega el mensaje al buzón de entrada del proceso correspondiente
           }
           if (general.get(i).nombre.equals(origen)){
               (general.get(i).salida).add(mensaje);//Agrega el mensaje al buzón de entrada del proceso correspondiente
           }
        }
    }
    
    void receive(String proceso, String origen){
        for (int i=0;i<(this.tamaño);i++){            
            if (general.get(i).nombre.equals(proceso)){
                for (int j=0;j<(general.get(i).entrada).size();j++){ 
                    if ((general.get(i).entrada).get(j).origen.equals(origen)){
                        general.get(i).recibido.add(general.get(i).entrada.get(j)); //agrega el mensaje al buzon recibidos
                        general.get(i).entrada.remove(j);//Borra el elemento del arreglo entrada
                    }
                }
            }
        }
    }
    void imprimir_mensajes(Mensajeria p){
        for (int i=0;i<p.tamaño;i++){
            p.general.get(i).imprime_salida();
            p.general.get(i).imprime_entrada();
            p.general.get(i).imprime_recibido();
        }
    }
    
    void imprimir_procesos(){
        for (int i=0;i<(this.tamaño);i++){
           System.out.print("Proceso #: "+general.get(i).rank +"\n");
           System.out.print("Nombre: "+general.get(i).nombre +"\n");
        }
    }
    
}