package FileManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Creator;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CreatorFileManager {
    private String filePath = "src/main/resources/Data/Creators.json";
    private Gson gson = new Gson();

    public void saveCreator(Creator creator) {
        List<Creator> creators = readCreators();
        if(creators == null){
            creators = new ArrayList<>();
        }
        creators.add(creator);
        saveCreators(creators);
    }

    public void editCreator(int index, Creator newCreator) {
        List<Creator> creators = readCreators();
        if (index >= 0 && index < creators.size()) {
            creators.set(index, newCreator);
            saveCreators(creators);
        } else {
            System.out.println("Недійсний індекс творця.");
        }
    }

    public void deleteCreator(int index) {
        List<Creator> creators = readCreators();
        if (index >= 0 && index < creators.size()) {
            creators.remove(index);
            saveCreators(creators);
        } else {
            System.out.println("Недійсний індекс творця.");
        }
    }

    private void saveCreators(List<Creator> creators) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(creators, writer);
            System.out.println("Творці успішно збережені у файлі.");
        } catch (IOException e) {
            System.out.println("Помилка при збереженні творців у файл: " + e.getMessage());
        }
    }

    public List<Creator> readCreators() {
        List<Creator> creators = new ArrayList<>();
        try (Reader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<Creator>>() {}.getType();
            creators = gson.fromJson(reader, listType);
            System.out.println("Творці успішно зчитані з файлу.");
        } catch (IOException e) {
            System.out.println("Помилка при зчитуванні творців з файлу: " + e.getMessage());
        }
        return creators;
    }
}
