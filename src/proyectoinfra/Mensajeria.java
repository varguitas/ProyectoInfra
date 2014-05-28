
package proyectoinfra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Mensajeria {
    ArrayList <Proceso> general = new ArrayList<>();
    int tamaño; //Tamaño de la mensajería (cantidad de procesos)
    boolean sincro_send; //tipo de sincronización en el send
    boolean sincro_receive; //tipo de sincronización en el receive

    Mensajeria(int tamano, boolean sy_send, boolean sy_receive){
        this.tamaño = tamano;
        this.sincro_send = sy_send;
        this.sincro_receive = sy_receive;
        //Constructor del arreglo de procesos
        for (int i=0;i<tamano;i++){
            this.general.add(new Proceso(i+1,"P"+(i+1)+""));
        }
    }
    //DIRECTO EXPLÍCITO: Se envía a un proceso en específico con el nombre del proceso
    void sendDirectoExplicito(String origen, String destino, String msj){
        boolean proceso1 = true;  //Banderas para saber si los procesos están en bloqueados anteriormente o no
        boolean proceso2 = true;
        //Comprobación de bloqueo de procesos
        if (this.sincro_send || this.sincro_receive){ //Si alguna de las sincronizaciones aplica entonces revisa
            for (int i=0;i<(this.tamaño);i++){ //Recorre por primera vez el arreglo de procesos a ver si estan bloqueados
                 if (general.get(i).nombre.equals(destino)){
                     if (general.get(i).estado || general.get(i).waitfor.equals(origen)){
                         proceso1 = true;
                         general.get(i).waitfor = "";
                     }
                     else{
                         proceso1 = false;
                     }
                 }
                 if (general.get(i).nombre.equals(origen)){
                     if (general.get(i).estado){
                         proceso2 = true;
                     }
                     else{
                         proceso2 = false;
                     }
                 }
            }
        }
        if (proceso1 && proceso2){ //Analiza si alguno de los dos procesos está bloqueado
            Mensaje mensaje = new Mensaje(origen,destino,msj);
            for (int i=0;i<(this.tamaño);i++){            
               if (general.get(i).nombre.equals(destino)){
                   (general.get(i).entrada).add(mensaje); //Agrega el mensaje al buzón de entrada del proceso correspondiente
                    general.get(i).estado = true;
                   
               }
               if (general.get(i).nombre.equals(origen)){
                   (general.get(i).salida).add(mensaje);//Agrega el mensaje al buzón de salida del proceso correspondiente
                   //Si el send es blocking, el proceso envío se bloquea hasta que el origen lo reciba
                   if (this.sincro_send){
                       general.get(i).estado = false; 
                   }
               }
            }
        }
    }
    
    void receiveDirectoExplicito(String proceso, String origen){ //Se especifica el proceso de origen para así recibirlo
        boolean encontrado = false; //Se especifica la bandera en false, hasta que se encuentre el mensaje solicitado
        for (int i=0;i<(this.tamaño);i++){ //recorro la lista de procesos           
            if (general.get(i).nombre.equals(proceso)){ //Busca el proceso adecuado
                for (int j=0;j<(general.get(i).entrada).size();j++){ //recorre el buzón de entrada del proceso adecuado
                    if ((general.get(i).entrada).get(j).origen.equals(origen)){ //Si el origen del mensaje es igual al indicado
                        encontrado = true; //Se encontró el mensaje
                        if (sincro_send){
                            for (Proceso h : this.general){
                                if (h.nombre.equals(origen)){
                                    h.estado = true; //Si el send es blocking se desbloquea el proceso recibido
                                }
                            }
                        }
                        general.get(i).recibido.add(general.get(i).entrada.get(j)); //agrega el mensaje al buzon recibidos
                        general.get(i).entrada.remove(j);//Borra el elemento del arreglo entrada
                    }
                }
            }
        }
        if (sincro_receive){
            if (!encontrado){ //Si no lo encuentra se coloca en estado de espera a que le llegue el mensaje del proceso adecuado
                for (Proceso h : this.general){ 
                    if (h.nombre.equals(proceso)){
                        h.waitfor = origen;
                        h.estado = false;
                    }
                }
            }
        }
    }
    
    //DIRECTO IMPLÍCITO: Se envía con un alias en vez de un nombre y se recibe con ese alias. Aquí es donde Bogarín es un mamador.
    void sendDirectoImplicito(String origen, String alias, String msj){
        /*Mensaje mensaje = new Mensaje(origen,alias,msj);
        for (int i=0;i<(this.tamaño);i++){            
           if (general.get(i).alias.equals(alias)){
               (general.get(i).entrada).add(mensaje);//Agrega el mensaje al buzón de entrada del proceso correspondiente
           }
           if (general.get(i).alias.equals(origen)){
               (general.get(i).salida).add(mensaje);//Agrega el mensaje al buzón de entrada del proceso correspondiente
           }
        }
        */
        boolean proceso1 = true;  //Banderas para saber si los procesos están en bloqueados anteriormente o no
        boolean proceso2 = true;
        //Comprobación de bloqueo de procesos
        if (this.sincro_send || this.sincro_receive){ //Si alguna de las sincronizaciones aplica entonces revisa
            for (int i=0;i<(this.tamaño);i++){ //Recorre por primera vez el arreglo de procesos a ver si estan bloqueados
                 if (general.get(i).alias.equals(alias)){
                     if (general.get(i).estado || general.get(i).waitfor.equals(origen)){
                         proceso1 = true;
                         general.get(i).waitfor = "";
                     }
                     else{
                         proceso1 = false;
                     }
                 }
                 if (general.get(i).alias.equals(origen)){
                     if (general.get(i).estado){
                         proceso2 = true;
                     }
                     else{
                         proceso2 = false;
                     }
                 }
            }
        }
        if (proceso1 && proceso2){ //Analiza si alguno de los dos procesos está bloqueado
            Mensaje mensaje = new Mensaje(origen,alias,msj);
            for (int i=0;i<(this.tamaño);i++){            
               if (general.get(i).alias.equals(alias)){
                   (general.get(i).entrada).add(mensaje); //Agrega el mensaje al buzón de entrada del proceso correspondiente
                    general.get(i).estado = true;
                   
               }
               if (general.get(i).alias.equals(origen)){
                   (general.get(i).salida).add(mensaje);//Agrega el mensaje al buzón de salida del proceso correspondiente
                   //Si el send es blocking, el proceso envío se bloquea hasta que el origen lo reciba
                   if (this.sincro_send){
                       general.get(i).estado = false;
                   }
               }
            }
        }
    }
    
    void receiveDirectoImplicito(String alias, String origen){
        for (int i=0;i<(this.tamaño);i++){            
            if (general.get(i).alias.equals(alias)){
                for (int j=0;j<(general.get(i).entrada).size();j++){ 
                    if ((general.get(i).entrada).get(j).origen.equals(origen)){
                        general.get(i).recibido.add(general.get(i).entrada.get(j)); //agrega el mensaje al buzon recibidos
                        general.get(i).entrada.remove(j);//Borra el elemento del arreglo entrada
                    }
                }
            }
        }
        boolean encontrado = false; //Se especifica la bandera en false, hasta que se encuentre el mensaje solicitado
        for (int i=0;i<(this.tamaño);i++){ //recorro la lista de procesos           
            if (general.get(i).alias.equals(alias)){ //Busca el proceso adecuado
                for (int j=0;j<(general.get(i).entrada).size();j++){ //recorre el buzón de entrada del proceso adecuado
                    if ((general.get(i).entrada).get(j).origen.equals(origen)){ //Si el origen del mensaje es igual al indicado
                        encontrado = true; //Se encontró el mensaje
                        if (sincro_send){
                            for (Proceso h : this.general){
                                if (h.alias.equals(origen)){
                                    h.estado = true; //Si el send es blocking se desbloquea el proceso recibido
                                }
                            }
                        }
                        general.get(i).recibido.add(general.get(i).entrada.get(j)); //agrega el mensaje al buzon recibidos
                        general.get(i).entrada.remove(j);//Borra el elemento del arreglo entrada
                    }
                }
            }
        }
        if (sincro_receive){
            if (!encontrado){ //Si no lo encuentra se coloca en estado de espera a que le llegue el mensaje del proceso adecuado
                for (Proceso h : this.general){ 
                    if (h.alias.equals(alias)){
                        h.waitfor = origen;
                        h.estado = false;
                    }
                }
            }
        }
    }
        
    //INDIRECTO DINÁMICO
    void sendIndirectoDinamico (String proceso_origen,ArrayList <String> procesos,String mensaje){
        for (String proceso_destino : procesos){
            sendDirectoExplicito(proceso_origen, proceso_destino, mensaje);
        }
    }
    
    void receiveIndirectoDinamico (String destino){
        for (int i=0;i<(this.tamaño);i++){  
            if (general.get(i).nombre.equals(destino)){
                    for (int j=0;j<(general.get(i).entrada).size();j++){ 
                        if ((general.get(i).entrada).get(j).destino.equals(destino)){
                            general.get(i).recibido.add(general.get(i).entrada.get(j));
                            general.get(i).entrada.remove(j);
                    }
                }
            }
        }
    }
    
    //INDIRECTO ESTÁTICO
    void sendIndirectoEstatico(String origen, Cola pcola_mensajes, Mensaje pmensaje){
        Cola cola_mensajes = pcola_mensajes;
        cola_mensajes.repartirMensajes(pmensaje);
    }
    void receiveIndirectoEstatico(String proceso, String cola_origen){
        for (int i=0;i<(this.tamaño);i++){            
            if (general.get(i).nombre.equals(proceso)){
                for (int j=0;j<(general.get(i).entrada).size();j++){ 
                    if ((general.get(i).entrada).get(j).origen.equals(cola_origen)){
                        general.get(i).recibido.add(general.get(i).entrada.get(j));
                        general.get(i).entrada.remove(j);
                    }
                }
            }
        }
    }
    
    //GENERADOR DE LA COLA PRINCIPAL, LA CUAL CONTIENE MENSAJES AÚN NO RECIBIDOS POR LOS PROCESOS
    void generar_cola(){ //Método que obtiene todos los mensajes en cola de entrada para todos los procesos.
        ArrayList <Mensaje> cola_general = new ArrayList<>();
        for (Proceso proceso : this.general){
            for(Mensaje m : proceso.entrada){
                cola_general.add(m);
            }
        }
        ArrayList<Integer> secuencia = new ArrayList<>();
        for (Mensaje m : cola_general){
            secuencia.add(m.id_mensaje);
        }
        Collections.sort(secuencia);
        ArrayList <Mensaje> cola_ordenada = new ArrayList<>();
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
           System.out.println("Proceso #: "+general.get(i).rank +"");
           System.out.println("Nombre: "+general.get(i).nombre +"");
           System.out.println("Estado: "+general.get(i).estado +"\n");
        }
    }
}