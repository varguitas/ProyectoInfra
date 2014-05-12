/*
 TECNOLÓGICO DE COSTA RICA
 * Licenciatura de Administración de TI
 * Infraestructura Tecnológica I
 * I Semetre de 2014
 * Proyecto #1: Message Passing
 * 
 * Integrantes:
 *  -Victor Vargas Ramírez
 *  -Benjamín Calvo De León
 *  -José Pablo Matamoros Moya
 */

package proyectoinfra;

import java.util.ArrayList;
import java.util.Arrays;

//Clase Mensaje, define la estructura que tendrán los mensajes que se envian entre procesos
class Mensaje{
    //Cuerpo del mensaje
    private String cuerpo;
    //Cabecera del Mensaje
    private String id_destino; //definir si se hace con int o con String
    private String id_origen;
    private int longitud;
    //Información de control
    private int id_mensaje;
    private static int numsec = 1; //Variable estatica que me permite controlar los numeros de secuencia de los mensajes
    //Contructor de la clase
    public Mensaje(String iddestino, String idorigen, String body){
        this.cuerpo = body;
        this.id_destino = iddestino;
        this.id_origen = idorigen;
        this.longitud = cuerpo.length();
        this.id_mensaje = Mensaje.numsec;
        Mensaje.numsec++;
    }
    public void imprimir_mensaje(){
        System.out.print("MENSAJE \n");
        System.out.print("ID Destino: " + id_destino + " - ID Origen: " + id_origen + "\n");
        System.out.print("Longitud del Mensaje: " + longitud + "\n");
        System.out.print("# de secuencia: " + id_mensaje + "\n");
        System.out.print("Mensaje: " + cuerpo + "\n");
    }
}

//Clase Consola, encargada de interpretar los comandos de la consola y parsear la información
//Devuelve True si todo sale bien, de lo contrario muestra el tipo de error de comandos
class Consola {
    private boolean hay_prioridad;
    public Consola(boolean prioridad){
        this.hay_prioridad = prioridad;
    }
    public boolean parse_query(String texto) {
        ArrayList<String> texto_lista = new ArrayList<String>(Arrays.asList(texto.split("\\(|\\)")));
        if (texto_lista.size()==3) {
            String comando = texto_lista.get(0);
            String parametros = texto_lista.get(1);
            /* Vacion es una variable que permite realizar el split al final del comando. Puede ser un
            * ';' , un ',' o un espacio
            */
            String vacio = texto_lista.get(2);
            if (vacio.trim().equals("") || vacio.trim().equals(";") || vacio.trim().equals(".") ) {
                // El comando parece cumplir con los requerimientos de sintaxis
                /*
                 * SEND COMMAND ############################################
                */
                if (comando.trim().toLowerCase().equals("send")) {
                    ArrayList<String> comandos = new ArrayList<String>(Arrays.asList(parametros.split(",")));
                    if( comandos.size()==2 || comandos.size()==3) {
                        String proceso_destino = comandos.get(0);
                        String contenido_mensaje = comandos.get(1);
                        String prioridad = null;
                        if (hay_prioridad == true) {
                            prioridad = comandos.get(2);
                        }
                        System.out.println(proceso_destino);
                        System.out.println(contenido_mensaje);
                        System.out.println(prioridad);
                    } else {
                        this.printMessageError(3);
                    }
                } else {
                    this.printMessageError(1);
                }
            } else {
                this.printMessageError(0);
            }

        } else {
            this.printMessageError(2);
        }
        return false;
    }
    public void printMessageError(int n){
        String h =  "Digite -h para ver la ayuda.";
        String e = "Error: ";
        String[] m = new String[]{
            e+"[00]: El comando no cumple con la estructura específicada."+h,
            e+"[01]: Comando no encontrado. "+h,
            e+"[02]: El comando no cumple con la estructura específicada Recuerde colocar el ';' al final del comando."+h,
            e+"[03]: Número de parámetros incorrectos. "+h
        };
        System.out.println(m[n]);
    }
}

public class ProyectoInfra {     
    public static void main(String[] args) {
        // TODO code application logic here
        Consola c = new Consola(true);
        c.parse_query("send('Hola mundo' ,8,9 );");
        //Pruebas Pablo
        Mensaje m = new Mensaje("P130", "P080", "Primer mensaje de prueba por PABLO");
        m.imprimir_mensaje();
        Mensaje z = new Mensaje("P280", "P620", "Segundo mensaje de prueba por PABLO");
        z.imprimir_mensaje();
    } 
}
