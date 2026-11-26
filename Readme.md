
## Formato elegido para el envío
Para enviar las palabras desde el cliente al servidor por UDP (puerto `3452`, codificación UTF-8) he elegido enviar una única cadena de texto en un solo datagrama, con todas las palabras separadas por un espacio en blanco ().

- Ejemplo de lo que envía el cliente: 

He elegido este formato porque es simple y entra todo en un solo mensaje UDP. En el servidor separo las palabras con split(" ") y voy comparando la longitud de cada una para quedarme con la más larga.

## Funcionamiento y reintentos
- El servidor recibe el paquete, saca la IP y el puerto del cliente con `getAddress()` y `getPort()`, y le devuelve la palabra más larga junto a su longitud.

- El cliente tiene un tiempo de espera de 2 segundos (`setSoTimeout(2000)`). Tendremos que ejecutar tanto `ServidorUDP` como `ClienteUDP`, si solo ejecutamos `ClienteUDP` nos dará un error de conexión con el servidor, reintentará el envío hasta 3 veces y, si sigue sin haber respuesta, muestra `Servidor no disponible` y cierra el programa.

![Captura desde 2026-11-26 13-40-02.png](src/Capturas/Captura%20desde%202026-11-26%2013-40-02.png)

![Captura desde 2026-11-26 13-22-31.png](src/Capturas/Captura%20desde%202026-11-26%2013-22-31.png)


