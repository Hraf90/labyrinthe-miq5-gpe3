

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

	public class Panneau extends JPanel implements Resolution{

	private N_echantillon n_echantillon=new N_echantillon(1,1);
	private int c=32; // Taille cellule
	private int nbIndividu;
	private int nbEchantillon;
	private int numeroIndividu;
	private int numeroEchantillon;
        private int numeroIndividuEncours;
	private int numeroEchantillonEncours;
	private float distanceCible=(float) 0.0;
	private int caseExplorees;
	private float moyenneTempsResolution;
	private float pourcentageReussite;
	private float critere1;
	private float critere2;
        private Individu individu_afficher;
	
        /*
         * Constructeurs
         */
	public Panneau(){	
	nbIndividu=1;
	nbEchantillon=1;
	numeroIndividu=1;
	numeroEchantillon=1;
        numeroIndividuEncours=1;
	numeroEchantillonEncours=1;
	n_echantillon=new N_echantillon(1,1);
        individu_afficher=n_echantillon.getEchantillon(numeroEchantillon).getIndividu(numeroIndividu); // Par défaut on affiche le premier individu
	}
	
	public Panneau(int nbE , int nbI){	
		nbIndividu=nbI;
		nbEchantillon=nbE;
		numeroIndividu=1;
		numeroEchantillon=1;
		n_echantillon=new N_echantillon(nbE,nbI);
		}
	
        /*
         Dessin du labyrinthe
         */
	public void paintComponent(Graphics g){
		//On décide d'une couleur de fond pour notre rectangle
		g.setColor(Color.white);
		//On dessine celui-ci afin qu'il prenne tout la surface
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
                // 2 boucles for imbriquées pour parcourir et dessiner le labyrinthe
		for (int x=0; x<individu_afficher.getLabyrinthe().n(); x++){
			for (int y=0; y<individu_afficher.getLabyrinthe().n(); y++) {
				if (individu_afficher.getLabyrinthe().estMur(x,y)) {
				    g.setColor(Color.gray);
				    g.fillRect(x*c, y*c, c, c);
				}
				if (individu_afficher.getLabyrinthe().estDepart(x,y)) {
				    g.setColor(Color.green);
				    g.fillRect(x*c, y*c, c, c);
				}
				if (individu_afficher.getLabyrinthe().estArrivee(x,y)) {
				    g.setColor(Color.red);
				    g.fillRect(x*c, y*c, c, c);
				 
				}
			}
		}
                // On prend le déplacement de l'individu et on le dessine
		int[][] dep=individu_afficher.getChemin().getDeplacement();
		individu_afficher.getChemin().updateNbcase();
		int ndep=individu_afficher.getChemin().getNbcases();
		int xd;
		int yd;
		g.setColor(Color.blue);
		for(int i=0; i< (ndep-1); i++){
			xd=dep[i][0];
			yd=dep[i][1];
			g.fillRect(xd*c+c/4, yd*c+c/4, c/2, c/2);
		}
		g.setColor(Color.green);
		xd=dep[ndep-1][0];
		yd=dep[ndep-1][1];
		g.fillRect(xd*c+c/4, yd*c+c/4, c/2, c/2);
		    
	}
        
        /*
         * Accesseurs
         */
	public N_echantillon getN_Echantillon(){
		return n_echantillon;
	}

	public int getNbIndividu() {
		return nbIndividu;
	}


	public void setNbIndividu(int nbIndividu) {
		this.nbIndividu = nbIndividu;
	}


	public int getNumeroIndividu() {
		return numeroIndividu;
	}


	public void setNumeroIndividu(int numeroIndividu) {
		this.numeroIndividu = numeroIndividu;
	}


	public int getNumeroEchantillon() {
		return numeroEchantillon;
	}


	public void setNumeroEchantillon(int numeroEchantillon) {
		this.numeroEchantillon = numeroEchantillon;
	}


	public float getDistanceCible() {
		return distanceCible;
	}


	public void setDistanceCible(float distanceCible) {
		this.distanceCible = distanceCible;
	}


	public int getCaseExplorees() {
		return caseExplorees;
	}


	public void setCaseExplorees(int caseExplorees) {
		this.caseExplorees = caseExplorees;
	}


	public float getMoyenneTempsResolution() {
		return moyenneTempsResolution;
	}


	public void setMoyenneTempsResolution(float moyenneTempsResolution) {
		this.moyenneTempsResolution = moyenneTempsResolution;
	}


	public float getPourcentageReussite() {
		return pourcentageReussite;
	}


	public void setPourcentageReussite(float pourcentageReussite) {
		this.pourcentageReussite = pourcentageReussite;
	}


	public float getCritere1() {
		return critere1;
	}


	public void setCritere1(float critere1) {
		this.critere1 = critere1;
	}


	public float getCritere2() {
		return critere2;
	}


	public void setCritere2(float critere2) {
		this.critere2 = critere2;
	}



	public int getNbEchantillon() {
		return nbEchantillon;
	}



	public void setNbEchantillon(int nbEchantillon) {
		this.nbEchantillon = nbEchantillon;
	}

        public int getnumeroIndividuEncours() {
		return nbEchantillon;
	}



	public void setnumeroIndividuEncours(int nbEchantillon) {
		this.nbEchantillon = nbEchantillon;
	}

        
                public int getnumeroEchantillonEncours() {
		return nbEchantillon;
	}



	public void setnumeroEchantillonEncours(int nbEchantillon) {
		this.nbEchantillon = nbEchantillon;
	}
        
        public void setIndividu_afficher(Individu ind) {
		this.individu_afficher = ind;
	}
        
        public Individu getIndividu_afficher(){
            return this.individu_afficher;
        }

        /*
         * Définition des méthodes de résolution
         */

	@Override
	public void BFS() {
	 Individu id_BFS=new Individu();
         id_BFS.getChemin().AddDeplacement(2, 1);
         setIndividu_afficher(id_BFS);
         repaint();
         
	}



	@Override
	public void DFS(int nbIndividu) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void AlgorithmeGreedy(int nbIndividu) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void AlgorithmeA(int nbIndividu) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void Escalade() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void RechercheAvecTabous() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void RecuitSimule() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void AlgorithmeGenetique() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void AlgorithmeColonieFourmis() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void LogiqueDeProposition() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void LogiqueDePredicats() {
		// TODO Auto-generated method stub
		
	}
	

	
}
