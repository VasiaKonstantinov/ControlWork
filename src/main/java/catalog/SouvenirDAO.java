package catalog;

import model.Creator;
import model.Souvenir;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SouvenirDAO {

    private final SouvenirStore store = new SouvenirStore();
    Scanner scanner = new Scanner(System.in);
    public List<Souvenir> getSouvenir(){
        return store.getSouvenirs();
    }
    public void addSouvenir(Souvenir souvenir, Creator creator){
        souvenir.setCreator(creator);
        store.addSouvenir(souvenir);
    }

    public void showSouvenir() {
        int i = 0;
        for (Souvenir souvenir: store.getSouvenirs()){
            i++;
            System.out.println(i + " " + souvenir);
        }
    }

    public void updateSouvenir(int index, Souvenir newSouvenir){
        store.updateSouvenir(index, newSouvenir);
    }

    public void removeSouvenir(int index){
        store.removeSouvenir(index);
    }

    public List<Souvenir> getSouvenirsByCountry(String country, SouvenirDAO souvenirDAO) {
       return souvenirDAO.getSouvenir().stream()
                .filter(souvenir -> souvenir.getCreator().getCountry().equals(country))
                .collect(Collectors.toList());
    }

    public void getSouvenirsByYear(SouvenirDAO souvenirDAO) {
        List<Souvenir> tempList= souvenirDAO.getSouvenir();
        tempList.sort(Comparator.comparingInt(Souvenir::getYear));
        int temp = 0;
        for(Souvenir souvenir: tempList){
            if(souvenir.getYear() == temp){
                System.out.println(souvenir);
            }
            else {
            System.out.println("Year: " + souvenir.getYear());
            System.out.println(souvenir);
            }
            temp = souvenir.getYear();
        }
    }

    public void getSouvenirsByCreator(String name, CreatorDAO creatorDAO){
        for(Creator creator: creatorDAO.getCreators()){
            if(creator.getName().equals(name)){
                for (Souvenir souvenir: creator.getSouvenirs()) {
                    System.out.println(souvenir);
                }
            }
        }
    }

    public Souvenir createSouvenir() {
        System.out.println("Enter souvenir name: ");
        String name = scanner.next();
        System.out.println("Enter souvenir year: ");
        int year = scanner.nextInt();
        System.out.println("Enter souvenir price: ");
        int price = scanner.nextInt();
        System.out.println("Enter creator name: ");
        String creatorName = scanner.next();
        return new Souvenir(name, creatorName, year, price);
    }

}
