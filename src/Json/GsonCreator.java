/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

public class GsonCreator {

    public static void main(String[] args) {

        ArchivoJson archivo = crearArchivo();

        //convierte objeto a json string
        Gson gson = new Gson();
        String json = gson.toJson(archivo);
        System.out.println(json);

        //convierte objeto a json string y lo guarda en el directorio
        try (FileWriter writer = new FileWriter("C:\\Users\\Luis08\\Desktop\\archivo.json")) {

            gson.toJson(archivo, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static ArchivoJson crearArchivo() {

        ArchivoJson archivo = new ArchivoJson();

        archivo.setId(1);
        archivo.setIp("127.0.0.1");
        archivo.setPuerto(5000);
        archivo.setTipoDato("Boolean");
        archivo.setValorDato("true");
        archivo.setTipoNodo("master");


        return archivo;

    }

}
