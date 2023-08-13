/**
 * Class Monkey extends RescueAnimal by adding
 * species, tail length, height, and body length attributes
 * 
 * @author Global Rain
 */
public class Monkey extends RescueAnimal {	
	// Class variable
	static String[] validSpecies = {"Capuchin", "Guenon", "Macaque", "Marmoset", "Squirrel Monkey", "Tamarin"};
	
	// Instance variable
	private String species;
	private String tailLength;
	private String height;
	private String bodyLength;
	
	// Default constructor
	public Monkey() {
	}
	
	/**
	 * Fully loaded constructor 
	 * @param name
	 * @param species
	 * @param gender
	 * @param age
	 * @param weight
	 * @param tailLength
	 * @param height
	 * @param bodyLength
	 * @param acquisitionDate
	 * @param acquisitionCountry
	 * @param trainingStatus
	 * @param reserved
	 * @param inServiceCountry
	 */
    public Monkey(String name, String species, String gender, String age,
    String weight, String tailLength, String height, String bodyLength, String acquisitionDate, String acquisitionCountry,
	String trainingStatus, boolean reserved, String inServiceCountry) {
        setName(name);
        setSpecies(species);
        setGender(gender);
        setAge(age);
        setWeight(weight);
        setTailLength(tailLength);
        setHeight(height);
        setBodyLength(bodyLength);
        setAcquisitionDate(acquisitionDate);
        setAcquisitionLocation(acquisitionCountry);
        setTrainingStatus(trainingStatus);
        setReserved(reserved);
        setInServiceCountry(inServiceCountry);

    }

	// Accessor/Getter methods
	public String getSpecies() {
		return species;
	}

	public String getTailLength() {
		return tailLength;
	}

	public String getHeight() {
		return height;
	}

	public String getBodyLength() {
		return bodyLength;
	}

	// Mutator/Setter methods
	public void setSpecies(String species) {
		this.species = species;
	}

	public void setTailLength(String tailLength) {
		this.tailLength = tailLength;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setBodyLength(String bodyLength) {
		this.bodyLength = bodyLength;
	}
    
    
}
