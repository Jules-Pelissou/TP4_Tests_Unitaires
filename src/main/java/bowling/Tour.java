package bowling;

public class Tour {
	
	// Attributs
	private int numTour;
	private int nbQuilles;
	private boolean isStrike;
	private boolean isSpare;
	private int scoreTour;
	private int nbLance;
	
	// Constructeur;
	
	public Tour(int numTour) {
		this.numTour = numTour;
		nbQuilles = 10;
		isStrike = false;
		isSpare = false;
		scoreTour = 0;
		nbLance = 1;
	}
	
	// Méthodes
	
	// Getters
	
	public int getNumTour() {
		return numTour;
	}
	public int getNbQuilles() {
		return nbQuilles;
	}
	public boolean isStrike() {
		return isStrike;
	}
	public boolean isSpare() {
		return isSpare;
	}
	public int getScoreTour() {
		return scoreTour;
	}
	public int getNbLance() {
		return nbLance;
	}
	
	// Setters
	
	public void setNbQuilles(int nbQuilles) {
		this.nbQuilles -= nbQuilles;
	}
	public void setStrike() {
		isStrike = true;
	}
	public void setSpare() {
		isSpare = true;
	}
	public void setScoreTour(int scoreTour) {
		this.scoreTour += scoreTour;
	}
	public void setNbLance(int nbLance) {
		this.nbLance += nbLance;
	}
	
}
