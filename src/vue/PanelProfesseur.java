package vue;

import controleur.Classe;
import controleur.Controleur;
import controleur.Professeur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelProfesseur extends PanelPrincipal implements ActionListener {
	private JTextField txtNom = new JTextField();

	private JTextField txtPrenom = new JTextField();

	private JTextField txtEmail = new JTextField();
	private JTextField txtDiplome = new JTextField();

	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregister");

	private JPanel pannelFrom = new JPanel();

	public PanelProfesseur () {
		super ("Gestion des professeurs"); 
	}
	{
		//construire le pannel formulaire : saisie de la classe.
		this.pannelFrom.setBounds(20,80,250,200);
		this.pannelFrom.setBackground(Color.gray);
		this.pannelFrom.setLayout(new GridLayout(5,2));
		this.pannelFrom.add(new JLabel("Nom Professeur : "));
		this.pannelFrom.add(this.txtNom);
		this.pannelFrom.add(new JLabel("Prenom Professeur : "));
		this.pannelFrom.add(this.txtPrenom);
		this.pannelFrom.add(new JLabel("Email Professeur : "));
		this.pannelFrom.add(this.txtEmail);
		this.pannelFrom.add(new JLabel("Diplome Préparé :"));
		this.pannelFrom.add(this.txtDiplome);
		this.pannelFrom.add(this.btAnnuler);
		this.pannelFrom.add(this.btEnregistrer);

		//on ajoute le formulaire à la fenetre
		this.add(this.pannelFrom);

		//rendre les boutons ecoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btAnnuler){
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtDiplome.setText("");
		} else if (e.getSource() == this.btEnregistrer) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String email = this.txtEmail.getText();
			String diplome = this.txtDiplome.getText();

			//Instanciation d'un Professeur
			Professeur unProfesseur = new Professeur(nom,prenom, email,diplome);

			//Insertion des proffesseur dans la base de données
			Controleur.insertProfesseur(unProfesseur);

			// Actualiser

			JOptionPane.showMessageDialog(this,"Insertion réussie du professeur");
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtDiplome.setText("");
		}
	}

	}