package controleur;

import modele.Modele;

import java.util.ArrayList;

public class Controleur {
	
	public static User selectWhereUser (String email, String mdp) {
		return Modele.selectWhereUser(email, mdp);
	}

	public static void updateUser(User unUser) {
		 
		Modele.updateUser(unUser);
	}

    public static void insertMateriel(Materiel unMateriel) {
		Modele.insertMateriel(unMateriel);
    }

    public static void insertProfesseur(Professeur unProfesseur) {
		Modele.insertProfesseur(unProfesseur);
    }

	public static void insertEtudiant(Etudiant unEtudiant) {
		Modele.insertEtudiant(unEtudiant);
	}

	public static ArrayList<Materiel> selectAllMateriel(String filtre, String role){
		return Modele.selectAllMateriel(filtre, role);
	}

	public static void deleteMateriel (int id_materiel){
		Modele.deleteMateriel(id_materiel);
	}

	public static void updateMateriel (Materiel unMateriel){
		Modele.updateMateriel(unMateriel);
	}

	public static Materiel selectWhereMateriel(String nom, String marque, float prix_loca, int stock_initial, String etat_materiel, String role) {
		return Modele.selectWhereMateriel(nom,marque, prix_loca,stock_initial,etat_materiel,role);
	}

	public static void insertMat_neige(Mat_neige unMateriel) {
		Modele.insertMat_neige(unMateriel);
	}

	public static void insertMat_rando(Mat_rando unMateriel) {
		Modele.insertMat_rando(unMateriel);
	}

}
