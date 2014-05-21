package proyectoinfra;

import java.util.ArrayList;

public class ProyectoInfra {     
    public static void main(String[] args) {/*
        // TODO code application logic here
        Consola c = new Consola(true);
        c.parse_query("send('Hola mundo' ,8,9 );");
        //Pruebas Pablo
        Mensaje m = new Mensaje("P130", "P080", "Primer mensaje de prueba por PABLO");
        m.imprimir_mensaje();
        Mensaje z = new Mensaje("P280", "P620", "Segundo mensaje de prueba por PABLO");
        z.imprimir_mensaje();*/
        
       //Pruebas de comunicación
         ArrayList <Proceso> general = new ArrayList<Proceso>();
         int cantidad = 5;
         //Crea una cantidad n de procesos en una estructura de arreglo de procesos, los cuales se puede comunicar.
         for (int i=0;i<cantidad;i++){
            general.add(new Proceso(i+1,"P"+(i+1)+""));
         }
         //Imprimir Arreglo
        for (int i=0;i<cantidad;i++){
            System.out.print("Proceso #: "+general.get(i).rank +"\n");
            System.out.print("Nombre: "+general.get(i).nombre +"\n");
        }
    }

}