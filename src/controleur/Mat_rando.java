package controleur;

public class Mat_rando extends Materiel{

    private float taille_harnais,poids_max;

    private String type_corde,type_ancrage,niveau_regidite;

    public Mat_rando(int id_materiel, String nom, String marque, float prix_loca, int stock_initial, String etat_materiel, String role, float taille_harnais,
                     float poids_max, String type_corde, String type_ancrage, String niveau_regidite) {
        super(id_materiel, nom, marque, prix_loca, stock_initial, etat_materiel, role);
        this.taille_harnais = taille_harnais;
        this.poids_max = poids_max;
        this.type_corde = type_corde;
        this.type_ancrage = type_ancrage;
        this.niveau_regidite = niveau_regidite;
    }

    public Mat_rando(String nom, String marque, float prix_loca, int stock_initial, String etat_materiel, String role, float taille_harnais,
                     float poids_max, String type_corde, String type_ancrage, String niveau_regidite) {
        super(nom, marque, prix_loca, stock_initial, etat_materiel, role);
        this.taille_harnais = taille_harnais;
        this.poids_max = poids_max;
        this.type_corde = type_corde;
        this.type_ancrage = type_ancrage;
        this.niveau_regidite = niveau_regidite;
    }
}
