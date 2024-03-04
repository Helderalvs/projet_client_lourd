package vue;

import controleur.Controleur;
import controleur.Etudiant;
import controleur.Professeur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelEtudiant extends PanelPrincipal implements ActionListener {
	private JTextField txtNom = new JTextField();

	private JTextField txtPrenom = new JTextField();

	private JTextField txtEmail = new JTextField();

	private JTextField txtDateNais = new JTextField();

	private JTextField txtIdClasse = new JTextField();

	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregister");

	private JPanel pannelFrom = new JPanel();

	public PanelEtudiant () {
		super ("Gestion des Etudiants");
		{
			//construire le pannel formulaire : saisie de la classe.
			this.pannelFrom.setBounds(20,80,250,200);
			this.pannelFrom.setBackground(Color.gray);
			this.pannelFrom.setLayout(new GridLayout(6,2));
			this.pannelFrom.add(new JLabel("Nom Etudiant : "));
			this.pannelFrom.add(this.txtNom);
			this.pannelFrom.add(new JLabel("Prenom Etudiant : "));
			this.pannelFrom.add(this.txtPrenom);
			this.pannelFrom.add(new JLabel("Email Etudiant :"));
			this.pannelFrom.add(this.txtEmail);
			this.pannelFrom.add(new JLabel("Date naissance Etudiant :"));
			this.pannelFrom.add(this.txtDateNais);
			this.pannelFrom.add(new JLabel("Id Etudiant :"));
			this.pannelFrom.add(this.txtIdClasse);
			this.pannelFrom.add(this.btAnnuler);
			this.pannelFrom.add(this.btEnregistrer);
			//on ajoute le formulaire à la fenetre
			this.add(this.pannelFrom);
		}
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btAnnuler){
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtDateNais.setText("");
			this.txtIdClasse.setText("");
		} else if (e.getSource() == this.btEnregistrer) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String email = this.txtEmail.getText();
			String dateNais = this.txtDateNais.getText();
			int idClasse = Integer.parseInt(this.txtIdClasse.getText());

			//Instanciation d'un Etudiant
			Etudiant unEtudiant = new Etudiant(nom,prenom, email,dateNais,idClasse);

			//Insertion des Etudiant dans la base de données
			Controleur.insertEtudiant(unEtudiant);

			// Actualiser

			JOptionPane.showMessageDialog(this,"Insertion réussie du professeur");
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtDateNais.setText("");
			this.txtIdClasse.setText("");
		}
	}
}

