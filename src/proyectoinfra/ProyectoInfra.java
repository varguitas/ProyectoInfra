package proyectoinfra;

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