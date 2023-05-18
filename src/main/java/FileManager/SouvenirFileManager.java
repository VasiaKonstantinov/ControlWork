package FileManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Souvenir;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SouvenirFileManager {
    private String filePath = "src/main/resources/Data/Souvanirs.json";
    private Gson gson = new Gson();


    public void saveSouvenir(Souvenir souvenir) {
        List<Souvenir> souvenirs = readSouvenirs();
        if(souvenirs == null){
            souvenirs = new ArrayList<>();
        }
        souvenirs.add(souvenir);
        saveSouvenirs(souvenirs);
    }

    public void editSouvenir(int index, Souvenir newSouvenir) {
        List<Souvenir> souvenirs = readSouvenirs();
        if (index >= 0 && index < souvenirs.size()) {
            souvenirs.set(index, newSouvenir);
            saveSouvenirs(souvenirs);
        } else {
            System.out.println("Недійсний індекс сувеніра.");
        }
    }

    public void deleteSouvenir(int index) {
        List<Souvenir> souvenirs = readSouvenirs();
        if (index >= 0 && index < souvenirs.size()) {
            souvenirs.remove(index);
            saveSouvenirs(souvenirs);
        } else {
            System.out.println("Недійсний індекс сувеніра.");
        }
    }

    private void saveSouvenirs(List<Souvenir> souvenirs) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(souvenirs, writer);
            System.out.println("Сувеніри успішно збережені у файлі.");
        } catch (IOException e) {
            System.out.println("Помилка при збереженні сувенірів у файл: " + e.getMessage());
        }
    }

    public List<Souvenir> readSouvenirs() {
        List<Souvenir> souvenirs = new ArrayList<>();
        try (Reader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<Souvenir>>() {}.getType();
            souvenirs = gson.fromJson(reader, listType);
            System.out.println("Сувеніри успішно зчитані з файлу.");
        } catch (IOException e) {
            System.out.println("Помилка при зчитуванні сувенірів з файлу: " + e.getMessage());
        }
        return souvenirs;
    }
}
