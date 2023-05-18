import catalog.CreatorDAO;
import catalog.SouvenirDAO;
import catalog.SouvenirService;

import java.util.Scanner;

public class Menu {
    Scanner in = new Scanner(System.in);
    CreatorDAO creatorDAO = new CreatorDAO();
    SouvenirDAO souvenirDAO = new SouvenirDAO();
    SouvenirService service = new SouvenirService(souvenirDAO, creatorDAO);

    public void mainMenu(){
        System.out.println("What would you like to do?");
        System.out.println("1. Interact with Souvenirs");
        System.out.println("2. Interact with Creators");
        System.out.println("3. Exit");
        mainChoose();
    }

    public void mainChoose() {
        int choice = in.nextInt();

        switch (choice) {
            case 1:
                System.out.println("What would you like to do with souvenirs?");
                System.out.println("1. View all souvenirs");
                System.out.println("2. View souvenirs by creator");
                System.out.println("3. View souvenirs by country");
                System.out.println("4. View souvenirs by years");
                System.out.println("5. Add a new souvenir");
                System.out.println("6. Edit an existing souvenir");
                System.out.println("7. Remove an existing souvenir");
                System.out.println("8. Exit");
                chooseSouvenir();

            case 2:
                System.out.println("What would you like to do with creators?");
                System.out.println("1. View all creators");
                System.out.println("2. View creators by price");
                System.out.println("3. View creators by souvenir and year");
                System.out.println("4. Add a new creator");
                System.out.println("5. Edit an existing creator");
                System.out.println("6. Remove an existing creator");
                System.out.println("7. Exit");
                chooseCreator();
            case 3:
                break;
        }
    }

    public void chooseSouvenir(){
        int choice = in.nextInt();
        switch (choice){
            case 1: service.getAllSouvenirs();
                mainMenu();
            case 2: {
                System.out.println("Enter creator name");
                String name = in.next();
                service.getSouvenirsByCreator(name);
                mainMenu();
            }
            case 3: {
                System.out.println("Enter country name");
                String country = in.next();
                service.getSouvenirsByCountry(country);
                mainMenu();
            }
            case 4: {
                service.getSouvenirsByYears();
                mainMenu();
            }
            case 5: {
                service.addSouvenir();
                mainMenu();
            }
            case 6: {
                service.updateSouvenir();
                mainMenu();
            }
            case 7: {
                service.removeSouvenir();
                mainMenu();
            }
            case 8: {
            mainMenu();
            }
        }
    }

    public void chooseCreator(){
        int choice = in.nextInt();
        switch (choice){
            case 1: service.getAllCreators();
                mainMenu();
            case 2: {
                System.out.println("Enter price");
                int price = in.nextInt();
                service.getCreatorByPrice(price);
                mainMenu();
            }
            case 3: {
                System.out.println("Enter name:");
                String name = in.next();
                System.out.println("Enter year:");
                int year = in.nextInt();
                service.getCreatorByYearAndSouvenir(year, name);
                mainMenu();
            }
            case 4: {
                service.addCreator();
                mainMenu();
            }
            case 5: {
                service.updateCreator();
                mainMenu();
            }
            case 6: {
                service.removeCreator();
                mainMenu();
            }
            case 7: {
                mainMenu();
            }
        }
    }
}
