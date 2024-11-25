package bowling;

import java.util.HashMap;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancers successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {

	// Attributs
	
	private int scoreTotal;
	private HashMap<Integer, Tour> tabTour;
	private boolean etatPartie;
	private int previousTour;
	private Tour tourActu;
	
	/**
	 * Constructeur
	 */
	public PartieMonoJoueur() {
		scoreTotal = 0;
		tabTour = new HashMap<>();
		for (int i=1; i<11;i++) {
			Tour tour = new Tour(i);
			this.tabTour.put(i, tour);
		}
		etatPartie = false;
		previousTour = 0;
		this.tourActu = tabTour.get(1);
	}

	/**
	 * Cette méthode doit être appelée à chaque lancer de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de ce lancer
	 * @throws IllegalStateException si la partie est terminée
	 * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux sinon	
	 */
	public boolean enregistreLancer(int nombreDeQuillesAbattues) {
		tourActu = tourCourant();
		if (tourActu.getNbLance() >2) {
			if (tourActu.getNumTour() == 10 && tourActu.isSpare() || tourActu.isStrike()) {
				tourActu.setNbLance(1);
				tourActu.setScoreTour(nombreDeQuillesAbattues);
				tourActu.setNbQuilles(nombreDeQuillesAbattues);
				return true;
			}else{
				throw new IllegalStateException("On ne doit pas pouvoir lancer plus de deux fois dans un tour");
			}
		} else {
			if (tourActu.getNbLance() == 2) {
				tourActu.setScoreTour(nombreDeQuillesAbattues);
				tourActu.setNbQuilles(nombreDeQuillesAbattues);
				tourActu = nextTour(tourActu);
				return false;
			}else{
				tourActu.setNbLance(1);
				tourActu.setScoreTour(nombreDeQuillesAbattues);
				tourActu.setNbQuilles(nombreDeQuillesAbattues);
				return true;
			}
		}
	}

	/**
	 * Cette méthode donne le score du joueur.
	 * Si la partie n'est pas terminée, on considère que les lancers restants
	 * abattent 0 quille.
	 * @return Le score du joueur
	 */
	public int score() {
		for (Tour tour : tabTour.values()) {
			scoreTotal += tour.getScoreTour();
		}
		return scoreTotal;
	}

	/**
	 * @return vrai si la partie est terminée pour ce joueur, faux sinon
	 */
	public boolean estTerminee() {
		return etatPartie;
	}


	/**
	 * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
	 */
	public int numeroTourCourant() {
		return tourActu.getNumTour();
	}
	
	// Retourne les valeurs du tour actuel
	
	public Tour tourCourant() {
		return tabTour.get(previousTour+1);
	}

	public Tour nextTour(Tour tour) {
		previousTour = tour.getNumTour();
		return  tabTour.get(tour.getNumTour()+1);
	}
	
	/**
	 * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
	 *         est fini
	 */
	public int numeroProchainLancer() {
		return tourActu.getNbLance();
	}
	
	public void faireUnStrike(){
		tourActu.setStrike();
		tourActu = nextTour(tourActu);
		scoreTotal +=10;
	}

	public void faireUnSpare(){
		tourActu.setSpare();
		tourActu = nextTour(tourActu);
		scoreTotal +=10;
	}

}
