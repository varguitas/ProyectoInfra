package proyectoinfra;

import java.util.ArrayList;

public class Cola{
    int tamaño;
    String id;
    ArrayList <Proceso> procesos_relacionados = new ArrayList <> ();
    ArrayList <Mensaje> mensajes;
    
    Cola(int t, String identificador, ArrayList<Proceso> lista_procesos){
        this.id = identificador;
        this.procesos_relacionados = lista_procesos;
        this.mensajes = new ArrayList<>(t); 
    }
    
    void repartirMensajes (Mensaje pmensaje){
        for (Proceso proceso_actual : procesos_relacionados){
             Mensaje mnuevo = new Mensaje(this.id,proceso_actual.nombre,pmensaje.cuerpo);
             proceso_actual.entrada.add(mnuevo);
        }
    }
    void revisar_cola(){
        ArrayList <Mensaje> cola_final = new ArrayList<>();
        for (Mensaje m : this.mensajes){
            boolean hayMensaje = false;
            for(Proceso p : this.procesos_relacionados){
                for(Mensaje t : p.entrada){
                    if (m.id_mensaje==t.id_mensaje){
                        hayMensaje = true;
                        break;
                    }   
                }
            if (hayMensaje){
                break;
            }
            }
        if (hayMensaje == false){
            this.mensajes.remove(m);
        }
        }
    }
}
