package proyectoinfra;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Cola{
    int tamaño;
    int id;
    ArrayList <Proceso> procesos_relacionados = new ArrayList <> ();
    
    Cola(int t, int identificador, ArrayList<Proceso> lista_procesos){
        this.tamaño = t;
        this.id = identificador;
        this.procesos_relacionados = lista_procesos;
        if (tamaño != 0){
           ArrayList <Mensaje> cola = new ArrayList<>(); 
        }
        else{
           JOptionPane.showMessageDialog(null, "¡Error: Tamaño de la cola incorrecto!");
        }
    }
    void repartirMensajes (Mensaje pmensaje){
        Mensaje mensaje = pmensaje;
        for (Proceso proceso_actual : procesos_relacionados){
            proceso_actual.entrada.add(mensaje);
        }
    }
}
