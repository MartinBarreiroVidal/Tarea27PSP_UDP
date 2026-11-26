import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class ServidorUDP {
    private static final int PUERTO = 3452;

    public static void main(String[] args) {
        System.out.println("Servidor UDP escuchando en el puerto " + PUERTO + "...");

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket recibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(recibido);

                String mensaje = new String(recibido.getData(), 0, recibido.getLength(), StandardCharsets.UTF_8).trim();
                System.out.println("Recibido de " + recibido.getAddress() + ":" + recibido.getPort() + " -> " + mensaje);

                String textoRespuesta = procesarPalabras(mensaje);

                byte[] datosRespuesta = textoRespuesta.getBytes(StandardCharsets.UTF_8);
                DatagramPacket paqueteRespuesta = new DatagramPacket(
                        datosRespuesta,
                        datosRespuesta.length,
                        recibido.getAddress(),
                        recibido.getPort());
                socket.send(paqueteRespuesta);
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static String procesarPalabras(String mensaje) {
        if (mensaje.isEmpty()) {
            return "ERROR No se han enviado palabras";
        }

        String[] palabras = mensaje.split(" ");
        String masLarga = palabras[0];

        for (int i = 1; i < palabras.length; i++) {
            if (palabras[i].length() > masLarga.length()) {
                masLarga = palabras[i];
            }
        }
        return "OK " + masLarga + " " + masLarga.length();
    }
}