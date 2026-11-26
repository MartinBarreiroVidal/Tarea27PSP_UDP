import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClienteUDP {

    private static final String HOST = "localhost";
    private static final int PUERTO_SERVIDOR = 3452;
    private static final int TIMEOUT_MS = 2000;
    private static final int MAX_REINTENTOS = 3;

    public static void main(String[] args) {
        try (Scanner teclado = new Scanner(System.in);
             DatagramSocket socket = new DatagramSocket()) {

            socket.setSoTimeout(TIMEOUT_MS);
            InetAddress ipServidor = InetAddress.getByName(HOST);

            System.out.println("Escribe varias palabras separadas por espacios:");
            String entrada = teclado.nextLine().trim();

            byte[] datosEnvio = entrada.getBytes(StandardCharsets.UTF_8);
            DatagramPacket paqueteEnvio = new DatagramPacket(
                    datosEnvio, datosEnvio.length, ipServidor, PUERTO_SERVIDOR);

            byte[] buffer = new byte[1024];
            boolean respuestaRecibida = false;

            for (int intento = 1; intento <= MAX_REINTENTOS; intento++) {
                try {
                    socket.send(paqueteEnvio);

                    DatagramPacket paqueteRespuesta = new DatagramPacket(buffer, buffer.length);
                    socket.receive(paqueteRespuesta);

                    String respuesta = new String(
                            paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength(), StandardCharsets.UTF_8);

                    System.out.println("Respuesta del servidor: " + respuesta);
                    respuestaRecibida = true;
                    break;

                } catch (SocketTimeoutException e) {
                    System.out.println("Sin respuesta del servidor (intento " + intento + " de " + MAX_REINTENTOS + ")");
                }
            }

            if (!respuestaRecibida) {
                System.out.println("Servidor no disponible");
            }

        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}