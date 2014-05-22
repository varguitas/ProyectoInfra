package proyectoinfra;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextArea;

class Consola {
    private boolean hay_prioridad;
    private JTextArea Bitacora;
    private Mensajeria mensajeria;
    public Consola(boolean prioridad,JTextArea bitacora, Mensajeria m){
        this.hay_prioridad = prioridad;
        this.Bitacora = bitacora;
        this.mensajeria = m;
    }
    public boolean parse_query(String texto) {
        ArrayList<String> texto_lista = new ArrayList<String>(Arrays.asList(texto.split("\\(|\\)")));
        if (texto_lista.size()==3) {
            ArrayList<String> origen_comando = new ArrayList<String>(Arrays.asList(texto_lista.get(0).split("\\.")));
            if (origen_comando.size()!=2) {
                return false;
            }
            String str_origen = origen_comando.get(0).replace("\"", "").replace("'", "");
            String comando = origen_comando.get(1);
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
                        String str_destino = comandos.get(0).replace("\"", "").replace("'", "");
                        String contenido_mensaje = comandos.get(1);
                        int prioridad;
                        if (hay_prioridad == true) {
                            if (comandos.size()!=3) {
                                this.printMessageError(7);
                            } else {
                                prioridad = Integer.valueOf(comandos.get(2));
                            }
                        }
                        Proceso origen = mensajeria.getProceso(str_origen, false);
                        Proceso destino = mensajeria.getProceso(str_destino, false);
                        if (origen == null) {
                            this.printMessageError(5);
                            return false;
                        }
                        if (destino == null) {
                            this.printMessageError(6);
                            return false;
                        }
                        if (destino == origen) {
                            this.printMessageError(8);
                            return false;
                        }
                        return true;
                        //System.out.println("BIEN");
                        //Bitacora.setText(Bitacora.getText()+"\nsend("+proceso_destino+",'"+contenido_mensaje+"');");
                        //System.out.println(proceso_destino);
                        //System.out.println(contenido_mensaje);
                        //System.out.println(prioridad);
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
    private void printMessageError(int n){
        String h =  "Digite -h para ver la ayuda.";
        String e = "\n> Error: ";
        String[] m = new String[]{
            e+"[00]: El comando no cumple con la estructura específicada."+h,
            e+"[01]: Comando no encontrado. "+h,
            e+"[02]: El comando no cumple con la estructura específicada Recuerde colocar el ';' al final del comando."+h,
            e+"[03]: Número de parámetros incorrectos. "+h,
            e+"[04]: El proceso origen debe ser especificado. "+h,
            e+"[05]: El proceso origen no existe. "+h,
            e+"[06]: El proceso destino no existe. "+h,
            e+"[07]: Debe especificar la prioridad del mensaje. "+h,
            e+"[07]: El proceso de origen no debe ser igual al proceso destino. "+h
        };
        this.Bitacora.setText(Bitacora.getText()+m[n]);
    }
}
