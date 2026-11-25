import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {

    private static final int PUERTO = 5555;

    public static void main(String[] args) {
        System.out.println("Servidor UDP escuchando en el puerto " + PUERTO + "...");

        try(DatagramSocket socket = new DatagramSocket(PUERTO)) {
            byte[] buffer = new byte[1024];

            while(true) {
                DatagramPacket recibido = 

            }

        } catch(Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());        }
    }
}