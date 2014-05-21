package proyectoinfra;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Cola {
    int tamaño;
    int id;
    
    Cola(int t, int identificador){
        this.tamaño = t;
        this.id = identificador;
        if (tamaño != 0){
           ArrayList <Mensaje> cola = new ArrayList<>(); 
        }
        else{
           JOptionPane.showMessageDialog(null, "¡Error: Tamaño de la cola incorrecto!");
        }
    }
}
