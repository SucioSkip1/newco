package com.example.newco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WebServiceInicioSesion {
    private static final String linkInsertar = "http://192.168.0.14:80/usu/iser.php";
    public String insertar( String nombre, String apellido, String correo, String password) {
        String aux = "";
        try {
            //Establecer URL a consultar en servidor
            URL url = new URL(linkInsertar);
            //Establecer conexiÃ³n con el webservice
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            //Habilitar envÃ­o de datos mediante POST
            conexion.setRequestMethod("POST");
            //Habilitar salida de datos
            conexion.setDoOutput(true);
            //Abrir Buffer de salida asociado a la conexiÃ³n
            OutputStreamWriter datSal = new OutputStreamWriter(conexion.getOutputStream());
            //Agregar valores  en formato web --> atributo = "valor";
            //Atributo
            //Verificar si el signo "?" es necesario como primer atributo
            String data ="NOMBRE="+ URLEncoder.encode(nombre, "UTF-8")
                    +"&APELLIDO="+URLEncoder.encode(apellido, "UTF-8")
                    +"&CORREO="+URLEncoder.encode(correo, "UTF-8")
                    +"&PASSWORD="+URLEncoder.encode(password, "UTF-8");

            //monitor.append("Valor en buffer: "+data+"\n");
            //salida.append("dato enviado: " + data);
            datSal.write(data);
            //Enviar datos al servidor
            datSal.flush();
            datSal.close();//Cerrar buffer de escritura
            //SI LA CONEXIÃ“N SE ESTABLECE CON Ã‰XITO

            //SI LA CONEXIÓN SE ESTABLECE CON EXITO
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //Apertura de buffer de entrada de datos desde el servidor
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                //Leer primer linea de la respuesta del servidor
                String linea = reader.readLine();
                //Mientras existan datos en el buffer de respuesta
                while (linea != null) {
                    aux = aux + linea;//Concatenar datos linea por linea
                    linea = reader.readLine();//leer siguiente linea
                }
                reader.close();//Cerrar buffer de lectura
                if (aux.equals("2002")) {
                    aux = "ERROR DE CONEXION AL SERVIDOR DE DATOS";
                } else if (aux.equals("000"))
                {
                    aux = "";
                } else if (aux.equals("001")) {
                    aux = "Faltan datos";
                } else if (aux.equals("200")) {
                    aux = "Datos agregado con exito! ";
                } else {
                    aux = "500";
                }
            }//SI NO HAY CONEXIÃƒâ€œN CON EL SERVIDOR...
            else {   //Se asocia el error a la salida en pantalla
                aux = "ERROR al procesar servicio: " + conexion.getResponseCode();
            }
            conexion.disconnect();//Se cierra la conexiÃƒÂ³n con el servvidor

        } catch (Exception ex) {
            aux = "ERROR de SERVIDOR: " + ex.getMessage();
        }
        return aux;
    }

    private static final String link = "http://192.168.0.14:80/usu/";
    public static String login(String usu, String pass) {
        String response = "";
        try {
            URL url = new URL(link + "validar.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            String data = "NOMBRE=" + usu + "&PASSWORD=" + pass;
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
                response = stringBuilder.toString();
            } else {
                response = "Error en la conexión";
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            response = "Error: " + e.getMessage();
        }
        return response;
    }

}
