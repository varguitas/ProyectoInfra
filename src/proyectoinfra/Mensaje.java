package proyectoinfra;
//Clase Mensaje, define la estructura que tendrán los mensajes que se envian entre procesos
class Mensaje {
    //Cuerpo del mensaje
    private String cuerpo;
    //Cabecera del Mensaje
    private int id_destino; //definir si se hace con int o con String
    private int id_origen;
    private int longitud;
    //Información de control
    private int id_mensaje;
    private static int numsec = 1; //Variable estatica que me permite controlar los numeros de secuencia de los mensajes
    //Contructor de la clase
    Mensaje(int iddestino, int idorigen, String body){
        this.cuerpo = body;
        this.id_destino = iddestino;
        this.id_origen = idorigen;
        this.longitud = cuerpo.length();
        this.id_mensaje = Mensaje.numsec;
        Mensaje.numsec++;
    }
    void imprimir_mensaje(){
        System.out.print("MENSAJE \n");
        System.out.print("ID Destino: " + id_destino + " - ID Origen: " + id_origen + "\n");
        System.out.print("Longitud del Mensaje: " + longitud + "\n");
        System.out.print("# de secuencia: " + id_mensaje + "\n");
        System.out.print("Mensaje: " + cuerpo + "\n");
    }
}
