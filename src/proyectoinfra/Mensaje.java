package proyectoinfra;
//Clase Mensaje, define la estructura que tendrán los mensajes que se envian entre procesos
class Mensaje {
    //Cuerpo del mensaje
    String cuerpo;
    //Cabecera del Mensaje
    String destino;
    String origen;
    int longitud;
    //Información de control
    int id_mensaje;
    static int numsec = 1; //Variable estatica que me permite controlar los numeros de secuencia de los mensajes
    //Contructor de la clase
    Mensaje(String idorigen,String iddestino,String body){
        this.cuerpo = body;
        this.destino = iddestino;
        this.origen = idorigen;
        this.longitud = cuerpo.length();
        this.id_mensaje = Mensaje.numsec;
        Mensaje.numsec++;
    }

    void imprimir(){
        //System.out.print("MENSAJE \n");
        System.out.print("ID Destino: " + destino + " - ID Origen: " + origen + "\n");
        System.out.print("Longitud del Mensaje: " + longitud + "\n");
        System.out.print("# de secuencia: " + id_mensaje + "\n");
        System.out.print("Mensaje: " + cuerpo + "\n");
    }
    
    int getSecuencia(){ //Devuelve el numero de secuencia del mensaje como el id_mensaje
        return this.id_mensaje;
    }
}
