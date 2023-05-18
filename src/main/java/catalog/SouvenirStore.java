package catalog;

import FileManager.CreatorFileManager;
import FileManager.SouvenirFileManager;
import model.Creator;
import model.Souvenir;

import java.util.ArrayList;
import java.util.List;

public class SouvenirStore {
    private SouvenirFileManager souvenirFileManager = new SouvenirFileManager();
    private CreatorFileManager creatorFileManager = new CreatorFileManager();
    private  List<Creator> creators;
    private List<Souvenir> souvenirs;

    public SouvenirStore() {
        creators = creatorFileManager.readCreators();
        if(creators == null){
            creators = new ArrayList<>();
        }
        souvenirs = souvenirFileManager.readSouvenirs();
        if(souvenirs == null){
            souvenirs = new ArrayList<>();
        }
    }

    public List<Creator> getCreators() {
        return creators;
    }

    public List<Souvenir> getSouvenirs() {
        return souvenirs;
    }

    public void addSouvenir(Souvenir souvenir) {
        souvenirs.add(souvenir);
        Souvenir souvenir1 = new Souvenir(souvenir.getName(), souvenir.creatorName(), souvenir.getYear(), souvenir.getPrice());
        souvenirFileManager.saveSouvenir(souvenir1);
    }
    public void addCreator(Creator creator) {
        creators.add(creator);
        creatorFileManager.saveCreator(creator);
    }

    public void removeSouvenir(int index) {
        souvenirs.remove(index - 1);
        souvenirFileManager.deleteSouvenir(index - 1);
    }
    public void removeCreator(int index) {
        creators.remove(index - 1);
        creatorFileManager.deleteCreator(index - 1);
    }

    public void updateSouvenir(int index, Souvenir newSouvenir) {
        souvenirs.set(index - 1, newSouvenir);
        souvenirFileManager.editSouvenir(index - 1, newSouvenir);
    }
    public void updateCreator(int index, Creator newCreator) {
        creators.set(index - 1, newCreator);
        creatorFileManager.editCreator(index - 1, newCreator);
    }
}
