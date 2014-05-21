package proyectoinfra;

import java.util.ArrayList;

public class Cola {
    int size;
    int rank;
    
    Cola(int tamano, int id){
        this.size = tamano;
        this.rank = id;
        if (size == 0){
           ArrayList <Mensaje> cola = new ArrayList<>(); 
        }
        else{
           Mensaje [] cola= new Mensaje[size];
        }
    }
}
