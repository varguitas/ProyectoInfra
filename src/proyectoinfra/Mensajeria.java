
package proyectoinfra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Mensajeria {
    ArrayList <Proceso> general = new ArrayList<Proceso>();
    int tamaño; //Tamaño de la mensajería (cantidad de procesos)
    int sincro_send = 0; //tipo de sincronización en el send
    int sincro_receive = 0; //tipo de sincronización en el receive
    int direc_directo = 0;  //Direccionamiento directo 1 - Explícito , 2 - Implícito
    int direc_indirecto = 0; //Direccionamiento Indirecto 1 - Estático, 2 Dinámico

    Mensajeria(int tamano, int sy_send, int sy_receive, int dir_direct, int dir_indirect){
        this.tamaño = tamano;
        this.sincro_send = sy_send;
        this.sincro_receive = sy_receive;
        this.direc_directo = dir_direct;
        this.direc_indirecto = dir_indirect;
        
        //Constructor del arreglo de procesos
        for (int i=0;i<tamano;i++){
            this.general.add(new Proceso(i+1,"P"+(i+1)+""));
        }
    }
    //SEND Y RECEIVE DIRECTO EXPLÍCITO
    void send(String origen, String destino, String msj){
        Mensaje mensaje = new Mensaje(origen,destino,msj);
        for (int i=0;i<(this.tamaño);i++){            
           if (general.get(i).nombre.equals(destino)){
               (general.get(i).entrada).add(mensaje);//Agrega el mensaje al buzón de entrada del proceso correspondiente
           }
           if (general.get(i).nombre.equals(origen)){
               (general.get(i).salida).add(mensaje);//Agrega el mensaje al buzón de entrada del proceso correspondiente
    }}
    }
    
    void receive(String proceso, String origen){
        for (int i=0;i<(this.tamaño);i++){            
            if (general.get(i).nombre.equals(proceso)){
                for (int j=0;j<(general.get(i).entrada).size();j++){ 
                    if ((general.get(i).entrada).get(j).origen.equals(origen)){
                        general.get(i).recibido.add(general.get(i).entrada.get(j)); //agrega el mensaje al buzon recibidos
                        general.get(i).entrada.remove(j);//Borra el elemento del arreglo entrada
    }}}}
    }
    
    //SEND Y RECEIVE DIRECTO IMPLÍCITO
    void send(String origen, Cola pcola_mensajes, Mensaje pmensaje){
        Cola cola_mensajes = pcola_mensajes;
        cola_mensajes.repartirMensajes(pmensaje);
    }
        
    //SEND Y RECEIVE INDIRECTO DINÁMICO
    void send(String origen, ArrayList<Proceso> pprocesos, Mensaje pmensaje){
        ArrayList<Proceso> procesos = pprocesos;
        Mensaje mensaje = pmensaje;
        for (Proceso proceso_actual : procesos){
            proceso_actual.entrada.add(mensaje);
    }
    }
    
    //GENERADOR DE LA COLA PRINCIPAL, LA CUAL CONTIENE MENSAJES AÚN NO RECIBIDOS POR LOS PROCESOS
    void generar_cola(){ //Método que obtiene todos los mensajes en cola de entrada para todos los procesos.
        ArrayList <Mensaje> cola_general = new ArrayList<Mensaje>();
        for (Proceso proceso : this.general){
            for(Mensaje m : proceso.entrada){
                cola_general.add(m);
            }
        }
        ArrayList<Integer> secuencia = new ArrayList<Integer>();
        for (Mensaje m : cola_general){
            secuencia.add(m.id_mensaje);
        }
        Collections.sort(secuencia);
        ArrayList <Mensaje> cola_ordenada = new ArrayList<Mensaje>();
        for (int s : secuencia){
            for (Mensaje m : cola_general){
                if (m.id_mensaje == s){
                    cola_ordenada.add(m);
                }
            }
        }
        //Print de la cola ordenada
        System.out.println("COLA DE MENSAJES");
        for (Mensaje m : cola_ordenada){
            m.imprimir();
        }
        System.out.println("FIN DE LA COLA DE MENSAJES");
    }
    
    //DEVULEVE EL PROCESO COMO ARGUMENTO DEL RETURN
    public Proceso getProceso(String nombre,boolean with_alias){
        if (with_alias){
            for (Iterator<Proceso> it = general.iterator(); it.hasNext();) {
                Proceso elemento = it.next();
                if(elemento.alias.equals(nombre)) {
                    return elemento;
                }
            }
            return null;
        } else {
            for (Iterator<Proceso> it = general.iterator(); it.hasNext();) {
                Proceso elemento = it.next();
                if(elemento.nombre.equals(nombre)) {
                    return elemento;
                }
            }
            return null;
        }
    }
    
    //IMPRIMIR
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