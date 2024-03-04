drop database if exists prestigeAlpin;
create database prestigeAlpin;
use prestigeAlpin;


create table reservation (
    id_resa int(10) not null auto_increment,
    date_resa DATE,
    prix int(50),
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
    telephone int(20),
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
    prix_loca float(10,2),
    stock_initial int(50),
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
    longeur_skis float(10,2),
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
    prix_loca float(10,2),
    stock_initial int(50),
    etat_materiel VARCHAR(50),
    taille_harnais float(20,7),
    type_corde VARCHAR(50),
    poids_max float(10,2),
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
    duree_cours int(50),
    prix_cours int(50),
    nb_personne int(50),
    PRIMARY KEY (id_cours)
);


create table instructeur (
    id_instructeur int(10) not null auto_increment,
    nom varchar(50),
    prenom varchar(50),
    adresse_mail VARCHAR(50) unique,
    telephone int(50),
    descripiton varchar(200),
    primary key (id_instructeur)
);


create table facture (
    ref int(10) not null auto_increment,
    date varchar(50),
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

INSERT INTO client VALUES (null, "alves","helder","helder@gmail.com","123", "rue victor hugo",'75016','Paris','0654455676','rue du ski','18000','val disere','client');

INSERT INTO representant VALUES (null, "lulu","lolo", "rue victor hugo",'75016','Paris','0654455676',curdate(),'representant');





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
