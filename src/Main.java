import java.util.*;

public class Main {
    public static void main(String[] args) {
//        task 1
//        DockSimulation simulation = new DockSimulation();
//
//        int simulationTime = 1440;
//        int[] passengerArrivalRates = {5, 3, 7};
//        int[] boatArrivalRates = {10, 200, 100};
//        boolean[] terminalTypes = {true, false, true};
//
//        simulation.generatePassengers(simulationTime, passengerArrivalRates);
//        simulation.generateBoats(simulationTime, boatArrivalRates, terminalTypes);
//
//        double avgWaitingTime = simulation.calculateAverageWaitingTime();
//        System.out.println("Average waiting time for passengers: " + avgWaitingTime + " min");
//
//        int maxPassengers = 50;
//        int optimalInterval = simulation.calculateOptimalBoatInterval(maxPassengers, boatArrivalRates);
//        System.out.println("Optimal interval between boats: " + optimalInterval + " min");


//        task2
//        Dictionary dictionary = new Dictionary();
//
//        dictionary.addWord("hello", Arrays.asList("qwe", "asd"));
//        dictionary.addWord("bye", Arrays.asList("vbn", "zxc"));
//
//        System.out.println("Translations for hello: " + dictionary.getTranslations("hello"));
//
//        dictionary.addTranslation("hello", "123");
//        System.out.println(dictionary.getTranslations("hello"));
//
//        dictionary.replaceTranslations("hello", Arrays.asList("hi", "salut"));
//        System.out.println(dictionary.getTranslations("hello"));
//
//        dictionary.removeTranslation("hello", "hi");
//        System.out.println(dictionary.getTranslations("hello"));
//
//        dictionary.removeWord("bye");
//        System.out.println("Translations for bye: " + dictionary.getTranslations("bye"));
//
//        dictionary.getTranslations("hello");
//        dictionary.getTranslations("hello");
//        dictionary.addWord("bye", Arrays.asList("bye"));
//        dictionary.getTranslations("bye");
//
//        System.out.println("Top 10 popular words: " + dictionary.getTopPopularWords());
//
//        System.out.println("Top 10 unpopular words: " + dictionary.getTopUnpopularWords());


//        task 3
        TaxFinesDatabase db = new TaxFinesDatabase();

        db.addPerson("1", "qwe", "Odesa");
        db.addPerson("2", "asd", "Kyiv");

        db.addFine("1", new Fine("nalog1", 150, "Odesa"));
        db.addFine("2", new Fine("nalog2", 50, "Kyiv"));
        db.addFine("1", new Fine("nalog1", 200, "Odesa"));

        System.out.println("Full database:");
        db.printDatabase();

        System.out.println("\nData for ID 12345:");
        db.printById("1");

        System.out.println("\nData for fine nalog1:");
        db.printByFineType("nalog1");

        System.out.println("\nData for Odesa:");
        db.printByCity("Odesa");
    }
}