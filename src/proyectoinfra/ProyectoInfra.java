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
        int sync_send; // 1 Blocking - 0 Nonblocking
        int sync_receive; // 1 Blocking - 0 Nonblocking
        int dir_directo; // 0 Desactivado - 1 Explícito - 2 Implícito
        int dir_indirecto; // 0 Desactivado - 1 Estático - 2 Dinámico
        
        Mensajeria principal = new Mensajeria(5,0,0);
        /*principal.imprimir();
        System.out.print("\nENVIO DEL PRIMER MENSAJE\n");
        principal.send("P1","P5", "aguacate");
        principal.imprimir_mensajes(principal);
        //Hace el receive
        System.out.print("\nRECEIVE DEL PRIMER MENSAJE\n");
        principal.receive("P5","P1");
        principal.imprimir_mensajes(principal);
        //Envío de otro mensaje
        System.out.print("\nENVIO DEL SEGUNDO MENSAJE\n");
        principal.send("P1","P3", "chayote");
        principal.send("P4","P2", "arroz");
        principal.send("P2","P3", "frijoles");
        principal.send("P3","P4", "carne");
        principal.send("P5","P2", "papas");
        //principal.imprimir_mensajes(principal);
        //Haciendo el otro receive
        //System.out.print("\nRECEIVE DEL SEGUNDO MENSAJE\n");
        principal.receive("P3","P1");
        //principal.imprimir_mensajes(principal);
        principal.generar_cola();*/
    }
}