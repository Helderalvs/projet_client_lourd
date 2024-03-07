package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.*;

public class Modele {
	private static BDD uneBDD = new BDD("root", "", "localhost","prestigealpin");
	
	public static User selectWhereUser (String email, String mdp) {
		User unUser = null;
		String requete ="select * from user where email='"+email+"' and mdp='"+mdp+"' ;";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			if (unResultat.next()) {
				unUser = new User(unResultat.getInt("id_user"), unResultat.getString("nom"),
						unResultat.getString("prenom"), unResultat.getString("email"), 
						unResultat.getString("mdp"), unResultat.getString("role"));
			}
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
		return unUser;
	}

	public static void updateUser(User unUser) {
	 String requete="update user set nom='"
			 +unUser.getNom()+"',prenom = '"
			 +unUser.getPrenom()+"',email = '"
			 +unUser.getEmail()+"',role = '"
			 +unUser.getRole()+"',mdp = '"
			 +unUser.getMdp()+"' where iduser = "
			 +unUser.getIduser() +";"; 
		 try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement(); 
			
			unStat.execute(requete);
			
			unStat.close();
			uneBDD.seDeConnecter();	
		 }
		 catch (SQLException exp)
			{
				System.out.println("Erreur de requete : " + requete );
			}	
		
	}


    public static void insertMateriel(Materiel unMateriel) {
		String requete = "insert into materiel values(null, '"
				+ unMateriel.getNom()+"','"
				+ unMateriel.getMarque()+"','"
				+ unMateriel.getPrix_loca()+"','"
				+ unMateriel.getStock_initial()+"','"
				+ unMateriel.getEtat_materiel()+"','"
				+ unMateriel.getRole()+"' ) ;";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();

			unStat.execute(requete);

			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
    }

	public static void deleteMateriel(int id_materiel) {
		String requete = "DELETE FROM materiel WHERE id_materiel = " + id_materiel+";";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();

			unStat.execute(requete);

			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
	}

	public static void updateMateriel(Materiel unMateriel) {
		String requete = "update materiel set nom='" + unMateriel.getNom()
				+"', marque='"+ unMateriel.getMarque() +"' , "
				+" prix_loca='"+ unMateriel.getPrix_loca() +"' , "
				+" stock_initial='"+ unMateriel.getStock_initial() +"' , "
				+" etat_materiel='" + unMateriel.getEtat_materiel() +"' where role='"+unMateriel.getRole()+"';";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();

			unStat.execute(requete);

			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
	}

    public static void insertProfesseur(Professeur unProfesseur) {
		String requete = "insert into Professeur values(null, '"
				+ unProfesseur.getNom()+"',' "
				+ unProfesseur.getPrenom()+"','"
				+ unProfesseur.getEmail()+"','"
				+ unProfesseur.getDiplome()+"' ) ;";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();

			unStat.execute(requete);

			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
	}
	public static ArrayList<Materiel> selectAllMateriel (String filtre, String role) {
		String requete = "";

		if (filtre.equals("")){
			requete = "select * from "+role+";";
		} else {
			requete = "select * from "+role+" where nom like '%"
					+ filtre +"%' or marque like '%" + filtre
					+ filtre +"%' or prix_loca like '%" + filtre
					+ filtre +"%' or stock_materiel like '%" + filtre
					+ filtre +"%' or etat_materiel like '%" + filtre
					+"%' or role = '%" + filtre +"%' ;";
		}
		ArrayList<Materiel> lesMateriel = new ArrayList<>();
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Materiel unMateriel;

				if (role.equals("Mat_neige")) {
					unMateriel = new Mat_neige(
							desRes.getInt("id_materiel"),
							desRes.getString("nom"),
							desRes.getString("marque"),
							desRes.getFloat("prix_loca"),
							desRes.getInt("stock_initial"),
							desRes.getString("etat_materiel"),
							desRes.getString("role"),
							desRes.getFloat("longeur_skis"),
							desRes.getString("type_fixation"),
							desRes.getString("niveau_usure"),
							desRes.getString("type_ski")
					);
				}else {
					unMateriel = new Mat_rando(
							desRes.getInt("id_materiel"),
							desRes.getString("nom"),
							desRes.getString("marque"),
							desRes.getInt("prix_loca"),
							desRes.getInt("stock_initial"),
							desRes.getString("etat_materiel"),
							desRes.getString("role"),
							desRes.getFloat("taille_harnais"),
							desRes.getFloat("poids_max"),
							desRes.getString("type_corde"),
							desRes.getString("type_ancrage"),
							desRes.getString("niveau_regidite")
					);
				}

				lesMateriel.add(unMateriel);
			}
			unStat.close();
			uneBDD.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
			exp.printStackTrace();
		} return lesMateriel;
	}
	public static void insertEtudiant(Etudiant unEtudiant) {
		String requete = "insert into Etudiant values(null, '"
				+ unEtudiant.getNom()+"',' "
				+ unEtudiant.getPrenom()+"','"
				+ unEtudiant.getEmail()+"','"
				+ unEtudiant.getDateNais()+"','"
				+ unEtudiant.getIdclasse()+"' ) ;";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();

			unStat.execute(requete);

			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
	}

	public static Materiel selectWhereMateriel(String nom, String marque, float prix_loca, int stock_initial, String etat_materiel, String role) {
		Materiel unMateriel = null;
		String requete = "SELECT * FROM materiel WHERE nom='" + nom + "' AND marque='" + marque + "' AND stock_initial=" + stock_initial + " AND prix_loca=" + prix_loca + " AND etat_materiel='" + etat_materiel + "' AND role='" + role + "'";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);
			if (desRes.next()) {
				unMateriel = new Materiel (
						desRes.getInt("id_materiel"),
						desRes.getString("nom"),
						desRes.getString("marque"),
						desRes.getFloat("prix_loca"),
						desRes.getInt("stock_initial"),
						desRes.getString("etat_materiel"),
						desRes.getString("role"));
			}
			unStat.close();
			uneBDD.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
			exp.printStackTrace();
		}
		return unMateriel;
	}

	public static void insertMat_neige(Mat_neige unMateriel) {
		String requete = "INSERT INTO mat_neige (nom, marque, prix_loca, stock_initial, etat_materiel, longeur_skis, type_fixation, niveau_usure, type_ski, role) VALUES ('"
				+ unMateriel.getNom() + "', '"
				+ unMateriel.getMarque() + "', "
				+ unMateriel.getPrix_loca() + ", "
				+ unMateriel.getStock_initial() + ", '"
				+ unMateriel.getEtat_materiel() + "', "
				+ unMateriel.getLongeur_skis() + ", '"
				+ unMateriel.getType_fixation() + "', '"
				+ unMateriel.getNiveau_usure() + "', '"
				+ unMateriel.getType_ski() + "', '"
				+ unMateriel.getRole() + "')";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();

			unStat.execute(requete);

			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
	}

	public static void insertMat_rando(Mat_rando unMateriel) {
		String requete = "insert into mat_rando values(null, '"
				+ unMateriel.getNom() + "', '"
				+ unMateriel.getMarque() + "', "
				+ unMateriel.getPrix_loca() + ", "
				+ unMateriel.getStock_initial() + ", '"
				+ unMateriel.getEtat_materiel() + "', "
				+ unMateriel.getTaille_harnais() + ", '"
				+ unMateriel.getType_corde() + "', '"
				+ unMateriel.getPoids_max() + "', '"
				+ unMateriel.getType_ancrage() + "', '"
				+ unMateriel.getNiveau_regidite() + "', '"
				+ unMateriel.getRole() + "')";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();

			unStat.execute(requete);

			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
	}

}















