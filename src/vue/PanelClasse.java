package vue;

import controleur.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PanelClasse extends PanelPrincipal implements ActionListener {

	private JTextField txtNom = new JTextField();
	private JTextField txtMarque = new JTextField();
	private JTextField txtPrix = new JTextField();
	private JTextField txtStock = new JTextField();
	private JTextField txtEtat = new JTextField();

	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregister");

	private JPanel pannelFrom = new JPanel();

	private JTable tableMateriel;
	private JScrollPane uneScroll;

	private Tableau unTableau;
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");

	private JLabel nbClasses = new JLabel();

  // heritage pour mat neige
	private static JComboBox<String> txtRole = new JComboBox<String>();
	private JTextField txtLongeur_skis = new JTextField();
	private JTextField txtType_fixation = new JTextField();
	private JTextField txtNiveau_usure = new JTextField();
	private JTextField txtType_ski = new JTextField();



	// heritage pour mat rando
	private JTextField txtTaille_harnais = new JTextField();

	private JTextField txtPoids_max = new JTextField();
	private JTextField txtType_corde = new JTextField();
	private JTextField txtType_ancrage = new JTextField();
	private JTextField txtNiveau_regidite = new JTextField();



	private JPanel panelMat_neige = new JPanel();

	private JPanel panelMat_rando = new JPanel();

	private int nb = 0;
	public PanelClasse() {
		super("Gestion des classes");
		{
			//construire le pannel formulaire : saisie de la classe.
			this.pannelFrom.setBounds(20,80,250,160);
			this.pannelFrom.setBackground(Color.gray);
			this.pannelFrom.setLayout(new GridLayout(6,2));
			this.pannelFrom.add(new JLabel("Nom Materiel : "));
			this.pannelFrom.add(this.txtNom);
			this.pannelFrom.add(new JLabel(" Marque : "));
			this.pannelFrom.add(this.txtMarque);
			this.pannelFrom.add(new JLabel("Prix location :"));
			this.pannelFrom.add(this.txtPrix);
			this.pannelFrom.add(new JLabel("Stock :"));
			this.pannelFrom.add(this.txtStock);
			this.pannelFrom.add(new JLabel("Etat_materiel :"));
			this.pannelFrom.add(this.txtEtat);



			this.pannelFrom.add(new JLabel("Role :"));
			this.pannelFrom.add(this.txtRole);
			this.txtRole.addItem("Mat_neige");
			this.txtRole.addItem("Mat_rando");
			this.txtRole.addActionListener(this);
			//on ajoute le formulaire à la fenetre
			this.add(this.pannelFrom);

			//appel méthode remplir id classe
			this.remplirCbxIdClasse();

			//ajout des deux pannaux

			this.panelMat_neige.setBounds(20,250,250,120);
			this.panelMat_neige.setBackground(Color.gray);
			this.panelMat_neige.setLayout(new GridLayout(5,2));
			this.panelMat_neige.add(new JLabel("Longeur du Skis :"));
			this.panelMat_neige.add(this.txtLongeur_skis);
			this.panelMat_neige.add(new JLabel("Type de fixation :"));
			this.panelMat_neige.add(this.txtType_fixation);
			this.panelMat_neige.add(new JLabel("Niveau d'usure :"));
			this.panelMat_neige.add(this.txtNiveau_usure);
			this.panelMat_neige.add(new JLabel("Type de ski :"));
			this.panelMat_neige.add(this.txtType_ski);
			this.add(this.panelMat_neige);
			this.panelMat_neige.setVisible(true);


			this.panelMat_rando.setBounds(20,300,250,60);
			this.panelMat_rando.setBackground(Color.gray);
			this.panelMat_rando.setLayout(new GridLayout(4,2));
			this.panelMat_rando.add(new JLabel("Taille du harnais :"));
			this.panelMat_rando.add(this.txtTaille_harnais);
			this.panelMat_rando.add(new JLabel("Type de corde :"));
			this.panelMat_rando.add(this.txtType_corde);
			this.panelMat_rando.add(new JLabel("Type d'ancrage :"));
			this.panelMat_rando.add(this.txtType_ancrage);
			this.panelMat_rando.add(new JLabel("Niveau de regidite :"));
			this.panelMat_rando.add(this.txtNiveau_regidite);
			this.add(this.panelMat_rando);
			this.panelMat_rando.setVisible(false);

			this.btAnnuler.setBounds(20,380,100,30);
			this.add(this.btAnnuler);
			this.btEnregistrer.setBounds(160,380,100,30);
			this.add(this.btEnregistrer);


			//on ajoute le formulaire à la fenetre
			//this.add(this.pannelFrom);

			// contruction du panel filtre
			this.panelFiltre.setBounds(360,80,460,30);
			this.panelFiltre.setBackground(Color.gray);
			this.panelFiltre.setLayout(new GridLayout(1,2));
			this.panelFiltre.add(new JLabel("Filtrer par :"));
			this.panelFiltre.add(this.txtFiltre);
			this.panelFiltre.add(this.btFiltrer);
			this.add(this.panelFiltre);

			// contruction de la table
			String entetes [] = {"Id materiel" , "Nom" , "Marque" , "Prix_loca", "Stock_materiel","Etat_materiel","Role"};
			this.unTableau = new Tableau(this.obtenirDonnes(""),entetes);
			this.tableMateriel = new JTable(this.unTableau);
			this.uneScroll = new JScrollPane(this.tableMateriel);
			this.uneScroll.setBounds(360,130,460,200);
			this.add(this.uneScroll);

			// interdire l'ordre des colonnes
			this.tableMateriel.getTableHeader().setReorderingAllowed(false);

			//contrusction du label Nb
			this.nbClasses.setBounds(400,380,250,20);
			this.add(this.nbClasses);
			this.nb = this.unTableau.getRowCount();
			this.nbClasses.setText("Nombre de classes : " + nb );


			//supprestion et modification
			this.tableMateriel.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int numLigne, id_materiel;
					if(e.getClickCount() >=2){
						numLigne = tableMateriel.getSelectedRow();
						id_materiel = Integer.parseInt(unTableau.getValueAt(numLigne,0).toString());
						//suprimer dans la base
						Controleur.deleteMateriel(id_materiel);
						//actualiser l'affichage
						unTableau.supprimerLigne(numLigne);
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}
			});

			//rendre les boutons ecoutables
			this.btAnnuler.addActionListener(this);
			this.btEnregistrer.addActionListener(this);
			this.btFiltrer.addActionListener(this);
		}
	}

	public static void remplirCbxIdClasse() {
		ArrayList<Materiel> lesMateriel = Controleur.selectAllMateriel("");
		// supprimer toutes les données existantes dans le cambo Id Classe
		txtRole.removeAllItems();
		// ajout des classes
		for (Materiel unMateriel : lesMateriel){
			txtRole.addItem(unMateriel.getRole()+"-"+unMateriel.getNom());
		}
	}

	public Object[][] obtenirDonnes(String filtre) {
		// transformer l'arrayList en une matrice []
		ArrayList<Materiel> lesMateriel = Controleur.selectAllMateriel(filtre);
		if (lesMateriel != null) {
			Object[][] matrice = new Object[lesMateriel.size()][7]; // Changement de la taille de la matrice
			int i = 0;
			for (Materiel unMateriel : lesMateriel) {
				matrice[i][0] = unMateriel.getId_materiel();
				matrice[i][1] = unMateriel.getNom();
				matrice[i][2] = unMateriel.getMarque();
				matrice[i][3] = unMateriel.getPrix_loca();
				matrice[i][4] = unMateriel.getStock_initial();
				matrice[i][5] = unMateriel.getEtat_materiel();
				matrice[i][6] = unMateriel.getRole();
				i++;
			}
			return matrice;
		} else {
			// Si aucun matériel n'est trouvé, retourner une matrice vide
			return new Object[0][0];
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btAnnuler){
			this.txtNom.setText("");
			this.txtMarque.setText("");
			this.txtPrix.setText("");
			this.txtStock.setText("");
			this.txtEtat.setText("");
		} else if (e.getSource() == this.btEnregistrer) {
			String nom = this.txtNom.getText();
			String marque = this.txtMarque.getText();
			float prix_loca = Float.parseFloat(this.txtPrix.getText());
			int stock_initial = Integer.parseInt(this.txtStock.getText());
			String etat_materiel = this.txtEtat.getText();

			String chaine = this.txtRole.getSelectedItem().toString();
			String tab[] = chaine.split("-");
			String role = String.valueOf((tab[0]));

			float longeur_skis=0,taille_harnais=0,poids_max=0;
			String type_fixation="",niveau_usure="",type_ski="",type_corde="",type_ancrage="",niveau_regidite="";
			String statut = this.txtRole.getSelectedItem().toString();
			Materiel unMateriel;
			if (statut.equals("Mat_neige")){
				longeur_skis = Float.parseFloat((txtLongeur_skis.getText()));
				type_fixation = txtType_fixation.getText();
				niveau_usure = txtNiveau_usure.getText();
				type_ski = txtType_ski.getText();
				unMateriel = new Mat_neige(nom,marque,prix_loca,stock_initial,etat_materiel,role,longeur_skis,type_fixation,niveau_usure,type_ski);
				Controleur.insertMat_neige((Mat_neige) unMateriel);
			} else if (statut.equals("Mat_rando")){
				taille_harnais = Float.parseFloat((txtTaille_harnais.getText()));
				poids_max = Float.parseFloat((txtPoids_max.getText()));
				type_corde = txtType_corde.getText();
				type_ancrage = txtType_ancrage.getText();
				niveau_regidite = txtNiveau_regidite.getText();
				unMateriel = new Mat_rando(nom,marque,prix_loca,stock_initial,etat_materiel,role,taille_harnais,poids_max,type_corde,type_ancrage,niveau_regidite);
				//Insertion des Etudiant dans la base de données
				Controleur.insertMat_rando((Mat_rando) unMateriel);
			}


			//Instanciation d'une classe
			//Materiel unMateriel = new Materiel(nom,marque,prix_loca,stock_initial,etat_materiel,role);

			//Insertion dans la classe dans la base de données
			//Controleur.insertMateriel(unMateriel);

			//recuperation de l'id de la classe insere auprès de Mysql
			unMateriel = Controleur.selectWhereClasse(nom,marque,prix_loca,stock_initial,etat_materiel,role);

			if (unMateriel != null) {
			// Mettez le code pour actualiser l'affichage ici
			Object ligne[] = {unMateriel.getId_materiel(), nom, marque, prix_loca, stock_initial, etat_materiel, role};
			this.unTableau.ajouterLigne(ligne);
			this.nb = this.unTableau.getRowCount();
			this.nbClasses.setText("Nombre de classes : " + nb);
			} else {
			// Gérer le cas où unMateriel est null
			System.out.println("Aucun matériel trouvé avec les critères spécifiés.");
			}

			JOptionPane.showMessageDialog(this,"Insertion réussie de la classe");
			this.txtNom.setText("");
			this.txtMarque.setText("");
			this.txtPrix.setText("");
			this.txtStock.setText("");
			this.txtEtat.setText("");
		}
		else if(e.getSource() == this.btFiltrer){
			String filtre = this.txtFiltre.getText();
			//recuperation des donnes de la bdd avec le filtre
			Object matrice [][] = this.obtenirDonnes(filtre);

			//actualisation de l'affichage
			this.unTableau.setDonnes(matrice);
		}
	}
}