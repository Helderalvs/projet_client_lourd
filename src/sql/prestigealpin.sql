drop database if exists prestigeAlpin;
create database prestigeAlpin;
use prestigeAlpin;


create table reservation (
    id_resa int(10) not null auto_increment,
    date_resa DATE,
    prix float(6,2),
    dateDebuLoc date,
    dateFinLoc date,
    etat_resa VARCHAR(50),
    primary key (id_resa)
);


create table user (
    id_user int(10) not null auto_increment,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    email VARCHAR(50) unique,
    mdp VARCHAR(50),
    adresse VARCHAR(50),
    telephone char(12),
    role enum('client','representant'),
    primary key (id_user)
);


create table client (
    id_user int(10) not null auto_increment,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    email VARCHAR(50) unique,
    mdp VARCHAR(50),
    adresse VARCHAR(50),
    cp char(5),
    ville VARCHAR(50),
    telephone char(12),
    adresse_vac varchar(50),
    code_postal varchar(50),
    ville_vac VARCHAR(50),
    role enum('client','representant'),
    primary key (id_user)
);

create table representant (
    id_user int(10) not null auto_increment,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    email VARCHAR(50) unique,
    mdp VARCHAR(50),
    adresse VARCHAR(50),
    cp char(5),
    ville VARCHAR(50),
    telephone char(12),
    date_debut date,
    role enum('client','representant'),
    primary key (id_user)
);


create table materiel (
    id_materiel int(10) not null auto_increment,
    nom VARCHAR(50),
    marque VARCHAR(50),
    prix_loca float(6,2),
    stock_initial int(4),
    etat_materiel VARCHAR(50),
    role enum('mat_neige','mat_rando'),
    PRIMARY KEY (id_materiel)
);

create table mat_neige (
    id_materiel int(10) not null auto_increment,
    nom VARCHAR(50),
    marque VARCHAR(50),
    prix_loca float(30),
    stock_initial int(50),
    etat_materiel VARCHAR(50),
    longeur_skis float(6,2),
    type_fixation VARCHAR(50),
    niveau_usure VARCHAR(50),
    type_ski varchar(50),
    role enum('mat_neige','mat_rando'),
    PRIMARY KEY (id_materiel)
);

create table mat_rando (
    id_materiel int(10) not null auto_increment,
    nom VARCHAR(50),
    marque VARCHAR(50),
    prix_loca float(6,2),
    stock_initial int(4),
    etat_materiel VARCHAR(50),
    taille_harnais float(6,2),
    type_corde VARCHAR(50),
    poids_max float(6,2),
    type_ancrage varchar(50),
    niveau_regidite varchar(30),
    role enum('mat_neige','mat_rando'),
    PRIMARY KEY (id_materiel)
);



create table cours (
    id_cours int(10) not null auto_increment,
    nom_cours VARCHAR(50),
    descripion_cours varchar(200),
    niveaut_difficulte VARCHAR(100),
    date_heure date,
    duree_cours time,
    prix_cours float(6,2),
    nb_personne int(2),
    PRIMARY KEY (id_cours)
);


create table instructeur (
    id_instructeur int(10) not null auto_increment,
    nom varchar(50),
    prenom varchar(50),
    adresse_mail VARCHAR(50) unique,
    telephone char(12),
    descripiton varchar(200),
    primary key (id_instructeur)
);


create table facture (
    ref int(10) not null auto_increment,
    datef date,
    etat varchar(50),
    primary key (ref)
);




DROP TRIGGER IF EXISTS insert_client;
DELIMITER //
CREATE TRIGGER insert_client
BEFORE INSERT ON client
FOR EACH ROW
BEGIN
    if new.id_user is null or new.id_user in (select id_user from user) or new.id_user = 0
        then
            set new.id_user = ifnull((select id_user from user where id_user >= all(select id_user from user)), 0) + 1;
    end if;
    insert into user values (new.id_user, new.nom, new.prenom, new.email, new.mdp, new.adresse,new.telephone, new.role);
END //
DELIMITER ;

DROP TRIGGER IF EXISTS insert_representant;
DELIMITER //
CREATE TRIGGER insert_representant
BEFORE INSERT ON representant
FOR EACH ROW
BEGIN
    if new.id_user is null or new.id_user in (select id_user from user) or new.id_user = 0
        then
            set new.id_user = ifnull((select id_user from user where id_user >= all(select id_user from user)), 0) + 1;
    end if;
    insert into user values (new.id_user, new.nom, new.prenom, new.email, new.mdp, new.adresse,new.telephone, new.role);
END //
DELIMITER ;



insert into user values
(null, "alves", "helder", "helder@gmail.com", "123","rue victor hugo","0654344323","client");


insert into user values
(null, "Leveque", "Vincent", "vincent@gmail.com", "123","rue victor hugo","0654344323","client");

INSERT INTO client VALUES (null, "alves","helder","helder2@gmail.com","123", "rue victor hugo",'75016','Paris','0654455676','rue du ski','18000','val disere','client');

INSERT INTO representant VALUES (null, "lulu","lolo","vincent2@gmail.com","555", "rue victor hugo",'75016','Paris','0654455676',curdate(),'representant');



DROP TRIGGER IF EXISTS insert_mat_neige;
DELIMITER //
CREATE TRIGGER insert_mat_neige
BEFORE INSERT ON mat_neige
FOR EACH ROW
BEGIN
    if new.id_materiel is null or new.id_materiel in (select id_materiel from materiel) or new.id_materiel = 0
        then
            set new.id_materiel = ifnull((select id_materiel from materiel where id_materiel >= all(select id_materiel from materiel)), 0) + 1;
    end if;
    insert into materiel values (new.id_materiel, new.nom, new.marque, new.prix_loca, new.stock_initial, new.etat_materiel, new.role);
END //
DELIMITER ;

DROP TRIGGER IF EXISTS insert_mat_rando;
DELIMITER //
CREATE TRIGGER insert_mat_rando
BEFORE INSERT ON mat_rando
FOR EACH ROW
BEGIN
    if new.id_materiel is null or new.id_materiel in (select id_materiel from materiel) or new.id_materiel = 0
        then
            set new.id_materiel = ifnull((select id_materiel from materiel where id_materiel >= all(select id_materiel from materiel)), 0) + 1;
    end if;
    insert into materiel values (new.id_materiel, new.nom, new.marque, new.prix_loca, new.stock_initial, new.etat_materiel, new.role);
END //
DELIMITER ;


insert into mat_neige values (null, "BLIZZARD", "Pack Brahma 88 2024", 60.0, 500,"comme neuf",18.3,"dure","parfaite etat","polyvalent","mat_neige");

INSERT INTO mat_neige VALUES (null, 'le roi', 'nike',10.2, 50, 'Comme neuf', 30.6, 'forte', 'comme neuf','aerodrift','mat_neige');

INSERT INTO mat_rando VALUES (null, 'la reine', 'adidas', 10.2, 100, 'Comme neuf', 27.6, 'forte', 120.7, 'facile', 'super dur', 'mat_rando');

INSERT INTO cours VALUES (null, 'Ski', 'Dans ce cours, on apprend aux gens a skier', 'Facile', '2024-01-31', 15, 30.6, 3);
INSERT INTO reservation (date_resa, prix, dateDebuLoc, dateFinLoc, etat_resa) VALUES
('2024-03-01', 120.00, '2024-04-01', '2024-04-05', 'en attente'),
('2024-03-02', 150.50, '2024-04-10', '2024-04-15', 'confirmee'),
('2024-03-03', 200.75, '2024-05-01', '2024-05-10', 'en attente'),
('2024-03-04', 180.25, '2024-05-15', '2024-05-20', 'confirmee'),
('2024-03-05', 250.00, '2024-06-01', '2024-06-10', 'en attente'),
('2024-03-06', 175.00, '2024-06-15', '2024-06-20', 'confirmee'),
('2024-03-07', 300.00, '2024-07-01', '2024-07-10', 'en attente'),
('2024-03-08', 220.50, '2024-07-15', '2024-07-20', 'confirmee'),
('2024-03-09', 180.75, '2024-08-01', '2024-08-10', 'en attente'),
('2024-03-10', 210.25, '2024-08-15', '2024-08-20', 'confirmee');


INSERT INTO cours (nom_cours, descripion_cours, niveaut_difficulte, date_heure, duree_cours, prix_cours, nb_personne) VALUES
('Cours de Ski', 'Dans ce cours, on apprend aux gens à skier.', 'Facile', '2024-01-31', '15:00:00', 30.60, 3),
('Cours de Snowboard', 'Ce cours est conçu pour apprendre les bases du snowboard.', 'Moyen', '2024-02-15', '14:30:00', 35.50, 5),
('Cours de Patinage sur glace', 'Un cours amusant pour apprendre à patiner sur la glace.', 'Facile', '2024-02-20', '13:45:00', 25.75, 6),
('Cours de Raquette à neige', 'Découvrez les joies de la raquette à neige avec ce cours.', 'Facile', '2024-03-05', '12:00:00', 20.00, 8),
('Cours de Ski de fond', 'Ce cours vous apprendra les techniques de base du ski de fond.', 'Moyen', '2024-03-10', '14:00:00', 28.90, 4),
('Cours de Freestyle Ski', 'Un cours avancé pour maîtriser les sauts et les figures en ski.', 'Difficile', '2024-03-20', '16:00:00', 40.00, 2),
('Cours de Slalom Géant', 'Ce cours intensif vous prépare pour les compétitions de slalom.', 'Difficile', '2024-04-05', '17:30:00', 45.80, 3),
('Cours de Télémark', 'Apprenez l''art du télémark avec ce cours spécialisé.', 'Moyen', '2024-04-15', '13:45:00', 33.25, 4),
('Cours de Freeride', 'Un cours pour les amateurs de hors-piste et de descentes sauvages.', 'Difficile', '2024-05-01', '15:30:00', 37.50, 3),
('Cours de Ski Alpinisme', 'Découvrez l''alpinisme sur skis avec ce cours aventureux.', 'Difficile', '2024-05-10', '18:00:00', 50.00, 2);


