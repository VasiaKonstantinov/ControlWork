package catalog;

import model.Creator;
import model.Souvenir;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SouvenirService {
    private final SouvenirDAO souvenirDAO;
    private final CreatorDAO creatorDAO;
    Scanner scanner = new Scanner(System.in);

    public SouvenirService(SouvenirDAO souvenirDAO, CreatorDAO creatorDAO) {
        this.souvenirDAO = souvenirDAO;
        this.creatorDAO = creatorDAO;
    }

    public void addSouvenir() {
        Souvenir newSouvenir = souvenirDAO.createSouvenir();
        List<Souvenir> newSou = new ArrayList<>();
        String creator = newSouvenir.getCreatorName();
        int i = 0;
        for (Creator cre: creatorDAO.getCreators()){
            if(cre.getName().equals(creator)){
                cre.getSouvenirs().add(newSouvenir);
                for(Souvenir souvenir: cre.getSouvenirs()){
                    Souvenir souvenir1 = new Souvenir(souvenir.getName(), souvenir.creatorName(), souvenir.getYear(), souvenir.getPrice());
                    newSou.add(souvenir1);
                }
                creatorDAO.updateCreator(i + 1, cre, newSou);
                souvenirDAO.addSouvenir(newSouvenir, cre);
                return;
            }
            i++;
        }
        System.out.println("Creator not found");

    }


    public void removeSouvenir() {
        souvenirDAO.showSouvenir();
        System.out.println("Enter number of souvenir witch you want to remove: ");
        int index = scanner.nextInt();
        int i = 0;
        for (Creator cre: creatorDAO.getCreators()){
            if(cre.getName().equals(souvenirDAO.getSouvenir().get(index - 1).getCreatorName())){
                cre.getSouvenirs().remove(souvenirDAO.getSouvenir().get(index - 1));
                creatorDAO.updateCreator(i + 1, cre, cre.getSouvenirs());
                souvenirDAO.removeSouvenir(index);
                return;
            }
            i++;
        }

    }

    public void updateSouvenir() {
        souvenirDAO.showSouvenir();
        System.out.println("Enter number of souvenir witch you want to update: ");
        int index = scanner.nextInt();
        Souvenir upSouvenir = souvenirDAO.createSouvenir();
        for (Creator cre : creatorDAO.getCreators()) {
            if (cre.getName().equals(souvenirDAO.getSouvenir().get(index - 1).getCreatorName())) {
                for (int i = 0; i < cre.getSouvenirs().size(); i++) {
                    if (cre.getSouvenirs().get(i).equals(souvenirDAO.getSouvenir().get(index - 1))) {
                        cre.getSouvenirs().set(i, upSouvenir);
                        for(int j = 0; j < creatorDAO.getCreators().size(); j++){
                            if(creatorDAO.getCreators().get(j).equals(cre)){
                                creatorDAO.updateCreator(j + 1, cre, cre.getSouvenirs());
                            }
                        }
                    }
                }
            }
            souvenirDAO.updateSouvenir(index, upSouvenir);
        }
    }

    public void updateCreator() {
        creatorDAO.showCreators();
        System.out.println("Enter number of creator witch you want to update: ");
        int index = scanner.nextInt();
        Creator newCreator = creatorDAO.createCreator();
        List<Souvenir> souvenirs = creatorDAO.getCreators().get(index - 1).getSouvenirs();
        List<Souvenir> newCreatorSouvenir = new ArrayList<>();
        int i = 0;
        for(Souvenir sou: souvenirDAO.getSouvenir()){
            if(sou.equals(souvenirs.get(i))) {
                sou.setCreator(newCreator);
                sou.setCreatorName(newCreator.getName());
                souvenirDAO.updateSouvenir(i, sou);
                newCreatorSouvenir.add(sou);
            }
            i++;
        }
        creatorDAO.updateCreator(index, newCreator, newCreatorSouvenir);
    }

    public void getAllSouvenirs() {
        souvenirDAO.showSouvenir();
    }

    public void getSouvenirsByCountry(String country) {
        for (Souvenir souvenir: souvenirDAO.getSouvenirsByCountry(country, souvenirDAO)){
            System.out.println(souvenir);
        }
    }

    public void getCreatorByPrice(int price) {
        int i = 0;
        for(Creator cre: creatorDAO.getCreatorByPrice(price, creatorDAO)){
            i++;
            System.out.println(i + ": " + cre.getName());
        }
    }

    public void getCreatorByYearAndSouvenir(int year, String name) {
        List<Creator> creators = creatorDAO.getCreatorsByParams(name, year, creatorDAO);
        for (Creator creator: creators){
            System.out.println(creator);
        }
    }

    public void getSouvenirsByYears() {
        souvenirDAO.getSouvenirsByYear(souvenirDAO);
    }

    public void addCreator() {
        Creator creator = creatorDAO.createCreator();
        for (Creator cre: creatorDAO.getCreators()){
            if(cre.equals(creator)){
                return;
            }
        }
        creatorDAO.addCreator(creator);
    }

    public void removeCreator() {
        creatorDAO.showCreators();
        System.out.println("Enter number of creator witch you want to remove: ");
        int index = scanner.nextInt();
        List<Souvenir> toDelete = creatorDAO.getCreators().get(index - 1).getSouvenirs();
        List<Souvenir> souvenirs = souvenirDAO.getSouvenir();
        List<Integer> indexesToRemove = new ArrayList<>();

        for (int i = 0; i < souvenirs.size(); i++) {
            Souvenir sou = souvenirs.get(i);
            for (Souvenir souvenir : toDelete) {
                if (sou.equals(souvenir)) {
                    indexesToRemove.add(i);
                    break;
                }
            }
        }
        for (int i = indexesToRemove.size() - 1; i >= 0; i--) {
            int index1 = indexesToRemove.get(i);
            souvenirDAO.removeSouvenir(index1 + 1);
        }
        creatorDAO.getCreators();
        creatorDAO.removeCreator(index);

    }

    public void getAllCreators() {
        creatorDAO.showCreators();
    }

    public void getSouvenirsByCreator(String name) {
        souvenirDAO.getSouvenirsByCreator(name, creatorDAO);
    }
}
