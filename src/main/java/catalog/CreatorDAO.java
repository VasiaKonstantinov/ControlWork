package catalog;

import model.Creator;
import model.Souvenir;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreatorDAO {
    private final SouvenirStore store = new SouvenirStore();
    Scanner in = new Scanner(System.in);
    public List<Creator> getCreators() {
        return store.getCreators();
    }
    public void showCreators() {
        int i = 0;
        for (Creator creator: store.getCreators()){
            i++;
            System.out.println(i + " " + creator);
        }
    }

    public void addCreator(Creator creator){
        store.addCreator(creator);
    }

    public void updateCreator(int index, Creator newCreator, List<Souvenir> newSouvenirs){
        newCreator.setSouvenirs(newSouvenirs);
        store.updateCreator(index, newCreator);
    }

    public void removeCreator(int index){
        store.removeCreator(index);
    }

    public List<Souvenir> getCreatorsSouvenir(int index){
        return store.getCreators().get(index).getSouvenirs();
    }

    public List<Creator> getCreatorsByParams(String name, int year, CreatorDAO creatorDAO) {
        List<Creator> result = new ArrayList<>();
        for (Creator creator : creatorDAO.getCreators()) {
            boolean hasSouvenir = false;
            for (Souvenir sou : creator.getSouvenirs()) {
                if (sou.getName().equals(name) && sou.getYear() == year) {
                    hasSouvenir = true;
                    break;
                }
            }
            if (hasSouvenir) {
                result.add(creator);
            }
        }
        return result;
    }

    public List<Creator> getCreatorByPrice(int price, CreatorDAO creatorDAO) {
        List<Creator> result = new ArrayList<>();
        for (Creator creator : creatorDAO.getCreators()) {
            boolean hasCheapSouvenir = false;
            for (Souvenir sou : creator.getSouvenirs()) {
                if (sou.getPrice() < price) {
                    hasCheapSouvenir = true;
                    break;
                }
            }
            if (hasCheapSouvenir) {
                result.add(creator);
            }
        }
        return result;
    }

    public Creator createCreator() {
        System.out.println("Enter creator name: ");
        String name = in.nextLine();
        System.out.println("Enter creator country: ");
        String country = in.nextLine();
        return new Creator(name, country);
    }
}
