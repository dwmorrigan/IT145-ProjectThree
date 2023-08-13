import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver class for Gravioso Salvare's Rescue tracking system
 * Provides an actionable menu that allows registration of new
 * animals, reserving an animal, and display of all registered 
 * animals by type
 * 
 * @author Global Rain
 * @version 0.1
 */
public class Driver {

    private static ArrayList<Dog> dogList = new ArrayList<Dog>();
    private static ArrayList<Monkey> monkeyList = new ArrayList<Monkey>();
    
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	String userInput = "";
        initializeDogList();
        initializeMonkeyList();

        // Loop displays menu, accepts user input, and takes appropriate action.
        // Minimal but effective input validation.
        do {
        	displayMenu();
        	userInput = scanner.nextLine();
        	// Could be, maybe should be switch statement?
        	if (userInput.startsWith("1")) {
        		intakeNewDog(scanner);
        	}
        	else if (userInput.startsWith("2")) {
        		intakeNewMonkey(scanner);
        	}
        	else if (userInput.startsWith("3")) {
        		reserveAnimal(scanner);
        	}
        	// Dog
        	else if (userInput.startsWith("4")) {
        		printOut("dog", scanner);
        	}
        	// Monkey
        	else if (userInput.startsWith("5")) {
        		printOut("monkey", scanner);
        	}
        	// Reserved
        	else if (userInput.startsWith("6")) {
        		printOut("available", scanner);
        	}
        } while (!userInput.startsWith("q"));

    }

    /**
     * displayMenu()
     */
    public static void displayMenu() {
        System.out.println("\n");
        System.out.println("************************************************************");
        System.out.println("*\t\tRescue Animal System Menu                  *");
        System.out.println("* [1] Intake a new dog                                     *");
        System.out.println("* [2] Intake a new monkey                                  *");
        System.out.println("* [3] Reserve an animal                                    *");
        System.out.println("* [4] Print a list of all dogs                             *");
        System.out.println("* [5] Print a list of all monkeys                          *");
        System.out.println("* [6] Print a list of all animals that are not reserved    *");
        System.out.println("* [q] Quit application                                     *");
        System.out.println("************************************************************");
        System.out.println("Enter a menu selection");
    }

    /**
     * Test set of dogs
     */
    public static void initializeDogList() {
        Dog dog1 = new Dog("Spot", "German Shepherd", "male", "1", "25.6", "05-12-2019", "United States", "intake", false, "United States");
        Dog dog2 = new Dog("Rex", "Great Dane", "male", "3", "35.2", "02-03-2020", "United States", "Phase I", false, "United States");
        Dog dog3 = new Dog("Bella", "Chihuahua", "female", "4", "25.6", "12-12-2019", "Canada", "in-service", true, "Canada");

        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
    }

    /**
     * Test set of monkeys
     */
    public static void initializeMonkeyList() {
        Monkey monkey1 = new Monkey("Chibi", "Tamarin", "male", "1", "5.6", "5", "5", "5", "06-17-2022", "France", "intake", false, "United States");
        Monkey monkey2 = new Monkey("Bobbo", "Marmoset", "male", "3", "1.2", "6", "6", "6", "03-02-2020", "Kenya", "Phase I", false, "United States");
        Monkey monkey3 = new Monkey("Lallo", "Capuchin", "female", "2", "3.6", "7", "7", "7", "12-12-2021", "Brazil", "in-service", false, "Brazil");

        monkeyList.add(monkey1);
        monkeyList.add(monkey2);
        monkeyList.add(monkey3);
    }

    /**
     * intakeNewDog collects user input and validates it, in order to create a new
     * dog and add it to the ArrayList dogList
     * @param scanner
     */
    public static void intakeNewDog(Scanner scanner) {
    	boolean inputValid = false;
    	String breed;
    	String gender = null;
    	String date = null;
    	String trainingStatus = null;
    	boolean res = false;

    	
        System.out.println("What is the dog's name?");
        String name = scanner.nextLine();
        for(Dog dog: dogList) {
            if(dog.getName().equalsIgnoreCase(name)) {
                System.out.println("\n\nThis dog is already in our system\n\n");
                continue; //returns to menu
            }
        }
        System.out.println("What breed is " + name);
        breed = scanner.nextLine();
        while (!inputValid) {
    		String[] validMGender = {"m", "male", "boy"};
    		String[] validFGender = {"f", "female", "girl"};
        	System.out.println("What gender is " + name + "?");
        	gender = scanner.nextLine();
        	for (String entry : validMGender) {
        		if (gender.equalsIgnoreCase(entry)) {
        			gender = "M";
        			inputValid = true;
        		}
        	}
        	for (String entry : validFGender) {
        		if (gender.equalsIgnoreCase(entry)) {
        			gender = "F";
        			inputValid = true;
        		}
        	}
    	}
    	System.out.println("How old are they?");
    	String age = scanner.nextLine();
    	System.out.println("How much do they weight?");
    	String weight = scanner.nextLine();
    	inputValid = false;
    	while (!inputValid) {
        	System.out.println("On what date were they aquired? (YYYY-MM-DD)");
        	date = scanner.nextLine();
        	// Regex pattern for very simply date validation copied from
        	// https://stackoverflow.com/questions/21054744/java-validate-that-a-given-string-represents-a-date-i-e-yyyy-mm-dd
        	if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
        		inputValid = true;
        	}
        	else {
        		System.out.println("Invalid date format.");
        	}
    	}
    	System.out.println("What country were they aquired in?");
    	String country = scanner.nextLine();
    	inputValid = false;
    	while (!inputValid) {
        	System.out.println("What is their training status?");
        	trainingStatus = scanner.nextLine();	
        	for (String entries : Dog.validStatus) {
        		if (entries.equalsIgnoreCase(trainingStatus)) {
        			trainingStatus = entries;
        			inputValid = true;
        			continue;
        		}
        	}
        	if (!inputValid) {
            	System.out.println("Invalid status. Enter a valid status:");
            	for (String entry : Dog.validStatus) {
            		System.out.print(entry + " ");
            	}
        		System.out.println("\n");
        	}
    	}
    	// Only those in-service can be reserved
    	if (trainingStatus.equalsIgnoreCase("in-service")) {
    	   	inputValid = false;
        	while (!inputValid) {
        		System.out.println("Is " + name + " currently reserved? (Y/N)");
        		String reserved = scanner.nextLine();
        		if (reserved.startsWith("y") || reserved.startsWith("Y")) {
        			res = true;
        			inputValid = true;
        		}
        		else if (reserved.startsWith("n") || reserved.startsWith("N")) {
        			res = false;
        			inputValid = true;
        		}	
        	}   		
    	}
    	else {
    		res = false;
    	}
    	System.out.println("What country is " + name + " working in?");
    	String inServiceCountry = scanner.nextLine();

        Dog dog = new Dog(name, breed, gender, age, weight, date, country, trainingStatus, res, inServiceCountry);
        dogList.add(dog);
        
        System.out.println("************************************************************");
        pressEnter(scanner);
    }

    /**
     * intakeNewMonkey collects user input and validates it, in order
     * to create a new monkey and add it to the ArrayList monkeyLIst
     * @param scanner
     */
    public static void intakeNewMonkey(Scanner scanner) {
    	boolean validInput = false;
    	String name = null;
    	String species = null;
    	String gender = null;
    	String trainingStatus = null;
    	String date = null;
    	boolean res = false;

    	while (!validInput) {
    		boolean nameInList = false;
    		System.out.println("What is the monkey's name?");
    		name = scanner.nextLine();
    		// For every monkey in monkeyList, check if this name is already stored in the list
    		for (Monkey monkey : monkeyList) {
    			// If a monkey has the name already, output that message, and set 
    			// nameInList to show the name is taken
    			if (monkey.getName().equalsIgnoreCase(name)) {
    				System.out.println("\n" + name + " is already in our system\n");
    				nameInList = true;
    			}
    		}
    		// If the name is NOT already taken, the input is valid and we can continue
    		// Otherwise, that means the name is taken and we will start this loop again.
    		if (!nameInList) {
    			validInput = true;
    		}
    	}
    	validInput = false;
    	while (!validInput) {
    		System.out.println("What species is " + name + "?");
    		species = scanner.nextLine();
    		System.out.println(species);
    		// Checks the input against a list of acceptable species. If valid, use the listed formating
    		// and quit the loop
    		for (String entries : Monkey.validSpecies) {
    			if (entries.equalsIgnoreCase(species)) {
    				species = entries;
    				validInput = true;
    				// continue;
    			}
    		}
    		// Otherwise, let the user know which species are valid and repeat the loop
    		if (!validInput) {
    			System.out.println("Valid species include: ");
        		for (String entries : Monkey.validSpecies) {
        			System.out.println("\t" + entries);
        		}
        		System.out.println("\n");
    		}
    		
    	}
    	validInput = false;
    	while (!validInput) {
    		String[] validMGender = {"m", "male", "boy"};
    		String[] validFGender = {"f", "female", "girl"};
        	System.out.println("What gender is " + name + "?");
        	gender = scanner.nextLine();
        	for (String entry : validMGender) {
        		if (gender.equalsIgnoreCase(entry)) {
        			gender = "M";
        			validInput = true;
        		}
        	}
        	for (String entry : validFGender) {
        		if (gender.equalsIgnoreCase(entry)) {
        			gender = "F";
        			validInput = true;
        		}
        	}
    	}
    	System.out.println("How old are they?");
    	String age = scanner.nextLine();
    	System.out.println("How much do they weight?");
    	String weight = scanner.nextLine();
    	System.out.println("How long is their tail?");
    	String tail = scanner.nextLine();
    	System.out.println("How tall are they at the shoulder?");
    	String height = scanner.nextLine();
    	System.out.println("How long is their body?");
    	String body = scanner.nextLine();
    	validInput = false;
    	while (!validInput) {
        	System.out.println("On what date were they aquired? (YYYY-MM-DD)");
        	date = scanner.nextLine();
        	// Regex pattern for very simply date validation copied from
        	// https://stackoverflow.com/questions/21054744/java-validate-that-a-given-string-represents-a-date-i-e-yyyy-mm-dd
        	if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
        		validInput = true;
        	}
        	else {
        		System.out.println("Invalid date format.");
        	}
    	}
    	System.out.println("What country were they aquired in?");
    	String country = scanner.nextLine();
    	validInput = false;
    	while (!validInput) {
        	System.out.println("What is their training status?");
        	trainingStatus = scanner.nextLine();	
        	for (String entries : Monkey.validStatus) {
        		if (entries.equalsIgnoreCase(trainingStatus)) {
        			trainingStatus = entries;
        			validInput = true;
        			continue;
        		}
        	}
        	if (!validInput) {
            	System.out.println("Invalid status. Enter a valid status:");
            	for (String entry : Monkey.validStatus) {
            		System.out.print(entry + " ");
            	}
        		System.out.println("\n");
        	}
    	}
    	// Only those in-service can be reserved
    	if (trainingStatus.equalsIgnoreCase("in-service")) {
    	   	validInput = false;
        	while (!validInput) {
        		System.out.println("Is " + name + " currently reserved? (Y/N)");
        		String reserved = scanner.nextLine();
        		if (reserved.startsWith("y") || reserved.startsWith("Y")) {
        			res = true;
        			validInput = true;
        		}
        		else if (reserved.startsWith("n") || reserved.startsWith("N")) {
        			res = false;
        			validInput = true;
        		}	
        	}   		
    	}
    	else {
    		res = false;
    	}
    	System.out.println("What country is the monkey working in?");
    	String inServiceCountry = scanner.nextLine();
    	
    	Monkey monkey = new Monkey(name, species, gender, age, weight, tail, height, body, date, country, trainingStatus, res, inServiceCountry);
    	monkeyList.add(monkey);
        System.out.println("************************************************************");
    	pressEnter(scanner);
    }

    /**
     * Ask user to enter an animal name, checks if it exists, checks if not reserved &
     * in service, then sets animal status to reserved 
     * @param scanner
     */
    public static void reserveAnimal(Scanner scanner) {
    	boolean notWorking = true;
    	while (notWorking) {
        	System.out.println("\nWhich animal is being reserved?");
        	System.out.println("(Type cancel to go back)");
        	String animalName = scanner.nextLine();
        	// TODO Complete error checking for instances where both a monkey and a dog share the same name
        	for (Dog dog:dogList) {
        		for (Monkey monkey:monkeyList) {
        			if (dog.getName().equalsIgnoreCase(monkey.getName())) {
        				System.out.println("Error: Name conflict");
        				return;
        			}
        		}
        	}
        	// IF the animal is in the system, not yet reserved, and is in-service then reserve
        	// the animal.
        	for (Dog dog:dogList) {
        		if (dog.getName().equalsIgnoreCase(animalName) && (!dog.getReserved()) && 
        				(dog.getTrainingStatus().equalsIgnoreCase("in-service"))) {
        			System.out.println(animalName + " is now reserved.");
        			dog.setReserved(true);
        			notWorking = false;
        		}
        	}
        	for (Monkey monkey:monkeyList) {
        		if (monkey.getName().equalsIgnoreCase(animalName) && (!monkey.getReserved()) && 
        				(monkey.getTrainingStatus().equalsIgnoreCase("in-service"))) {
        			System.out.println(animalName + " is now reserved.");
        			monkey.setReserved(true);
        			notWorking = false;
        		}
        	}
        	if (animalName.equalsIgnoreCase("cancel")) {
        		notWorking = false;
        	}
    	}
        System.out.println("************************************************************");
    	pressEnter(scanner);
    }

    /**
     * Depending on user's input, prints out a list of the dogs or monkeys already in the system
     * or displays the animals that are both available and in service.
     * @param mode
     * @param scanner
     */
    public static void printOut(String mode, Scanner scanner) {
    	System.out.println("");
    	if (mode == "dog") {
    		// Print the doggies
    		for (Dog i : dogList) {
    			System.out.printf("Name: %s, Status: %s, Country of Origin: %s, Reserved Status: %b", i.getName(), i.getTrainingStatus(), i.getAcquisitionLocation(), i.getReserved());
    			System.out.println("");
    		}
    	}
    	else if (mode == "monkey") {
    		// Print the monkeys
    		for (Monkey i : monkeyList) {
    			System.out.printf("Name: %s, Status: %s, Country of Origin: %s, Reserved Status: %b", i.getName(), i.getTrainingStatus(), i.getAcquisitionLocation(), i.getReserved());
    			System.out.println("");
    		}
    	}
    	else {
    		// Print the reserved list
    		boolean emptyList = true;
    		System.out.println("Dogs available for reservation:");
    		for (Dog i : dogList) {
    			if (!i.getReserved() && i.getTrainingStatus().equalsIgnoreCase("in-service")) {
    				System.out.println("\t" + i.getName());
    				emptyList = false;
    			}
    		}
    		if (emptyList) { System.out.println("\tNone available"); }
    		emptyList = true;
    		System.out.println("\nMonkeys available for reservation:");
    		for (Monkey i : monkeyList) {
    			if (!i.getReserved() && i.getTrainingStatus().equalsIgnoreCase("in-service")) {
    				System.out.println("\t" + i.getName());
    				emptyList = false;
    			}
    		}
    		if (emptyList) { System.out.println("\tNone available"); }
    	}
        System.out.println("************************************************************");
    	pressEnter(scanner);
    }

    /**
     * Following DRY principles, pressEnter() is used repeatedly throughout in order
     * to allow the user to see and respond to various prompts before the program continues
     * @param scanner
     */
    public static void pressEnter(Scanner scanner) {
    	System.out.println("\nPress enter to continue...");
    	scanner.nextLine();
    }
        
}