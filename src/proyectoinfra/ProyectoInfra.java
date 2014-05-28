package proyectoinfra;

import java.util.ArrayList;

public class ProyectoInfra {     
    public static void main(String[] args) {/*
        Consola c = new Consola(true);
        c.parse_query("send('Hola mundo' ,8,9 );");
        //Pruebas Pablo
        Mensaje m = new Mensaje("P130", "P080", "Primer mensaje de prueba por PABLO");
        m.imprimir_mensaje();
        Mensaje z = new Mensaje("P280", "P620", "Segundo mensaje de prueba por PABLO");
        z.imprimir_mensaje();*/
        //DESDE EL INICIO DEFINIMOS LOS PARAMERTOS DE CONFIGURACIÓN MEDIANTE VARIABLES GLOBALES QUE SE DEFINEN INICIALMENTE
        
        Mensajeria principal = new Mensajeria(8,true,true);
        /*principal.imprimir();
        System.out.print("\nENVIO DEL PRIMER MENSAJE\n");*/
        principal.sendDirectoExplicito("P1","P5", "aguacate");
        principal.sendDirectoExplicito("P2","P3", "frijoles");
        principal.receiveDirectoExplicito("P3","P4");
        principal.sendDirectoExplicito("P4","P3", "chayote");
        //principal.receiveDirectoExplicito("P3","P4");
        principal.sendDirectoExplicito("P3","P6", "camote");
        /*principal.imprimir_mensajes(principal);
        //Hace el receive
        System.out.print("\nRECEIVE DEL PRIMER MENSAJE\n");*/
        //principal.receiveDirectoExplicito("P5","P1");
       /* principal.imprimir_mensajes(principal);
        //Envío de otro mensaje
        System.out.print("\nENVIO DEL SEGUNDO MENSAJE\n");*/
        /*principal.send("P4","P2", "arroz");*/
        
       /* principal.send("P3","P4", "carne");
        principal.send("P5","P2", "papas");
        //principal.imprimir_mensajes(principal);
        //Haciendo el otro receive
        //System.out.print("\nRECEIVE DEL SEGUNDO MENSAJE\n");
      */
        //principal.imprimir_mensajes(principal);*/
        principal.generar_cola();
        principal.imprimir_procesos();
    }
}