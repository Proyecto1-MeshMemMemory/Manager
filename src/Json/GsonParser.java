/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json;

/**
 *
 * @author Luis08
 */
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class GsonParser {

    public static void main(String[] args) {

        Gson gson = new Gson();

        try (Reader reader = new FileReader("C:\\Users\\Luis08\\Desktop\\archivo.json")) {
            
            JsonElement json = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(json);
            System.out.println(jsonInString);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}