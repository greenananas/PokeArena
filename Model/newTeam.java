package Model;

import java.sql.*;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class newTeam {

    public int menu() {
        System.out.println("Salut, veux-tu créer une équipé de 3 ou 6 pokémons ?");
        System.out.println("1 - Equipe de 3");
        System.out.println("2 - Equipe de 6");
        System.out.println("3 - Quitter");

        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();

        //Nombre de pokémons restants à ajouter dans l'équipe
        int nb_pok = 0;
        switch (choix) {
            case 1:
                System.out.println("Team de 3");
                nb_pok = 3;
                break;
            case 2:
                nb_pok = 6;
                System.out.println("team de 6");
                break;
            default:
                System.out.println("Aurevoir");
                System.exit(1);
                break;
        }
        return nb_pok;
    }

    public int ret_set(Connection Mycon, int id_pok) {

        PreparedStatement Prep_statement;
        ResultSet Myresults;

        int id_set = 0;

        try {
            Prep_statement = Mycon.prepareStatement("SELECT id from sets WHERE pokemon = ?");
            Prep_statement.setInt(1, id_pok);
            Myresults = Prep_statement.executeQuery();
            id_set = Myresults.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (id_set);
    }

    public Move init_move(Connection Mycon, int id_move) {

        PreparedStatement Prep_statement;
        ResultSet Myresults;


        //2.0
        String string_name = null;
        String type = null;
        String damage_class = null;
        int power = 0;
        int pp = 0;
        int prio = 0;
        int crit_rate = 0;
        boolean physical;
        int accuracy = 0;
        try {
            Prep_statement = Mycon.prepareStatement("SELECT name, type, damage_class, power, pp, priority, crit_rate, accuracy from move where id = ?");
            Prep_statement.setInt(1, id_move);
            Myresults = Prep_statement.executeQuery();
            string_name = Myresults.getString(1);
            type = Myresults.getString(2);
            damage_class = Myresults.getString(3);
            power = Myresults.getInt(4);
            pp = Myresults.getInt(5);
            prio = Myresults.getInt(6);
            crit_rate = Myresults.getInt(7);
            accuracy = Myresults.getInt(8);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        PokeTypes.type move_type;
        move_type = init_poketype(type);

        //MOVE PHYSICAL OR SPECIAL
        physical = damage_class.equals("physical");
        return (new Move(id_move, string_name, move_type, physical, power, accuracy, pp, prio, crit_rate));
    }

    public PokeTypes.type init_poketype(String type) {
        switch (type) {
            case ("grass"):
                return (PokeTypes.type.GRASS);
            case ("poison"):
                return (PokeTypes.type.POISON);
            case ("fire"):
                return (PokeTypes.type.FIRE);
            case ("flying"):
                return (PokeTypes.type.FLYING);
            case ("water"):
                return (PokeTypes.type.WATER);
            case ("bug"):
                return (PokeTypes.type.BUG);
            case ("normal"):
                return (PokeTypes.type.NORMAL);
            case ("electric"):
                return (PokeTypes.type.ELECTRIC);
            case ("ground"):
                return (PokeTypes.type.GROUND);
            case ("fairy"):
                return (PokeTypes.type.FAIRY);
            case ("fighting"):
                return (PokeTypes.type.FIGHTING);
            case ("psychic"):
                return (PokeTypes.type.PSYCHIC);
            case ("rock"):
                return (PokeTypes.type.ROCK);
            case ("steel"):
                return (PokeTypes.type.STEEL);
            case ("ice"):
                return (PokeTypes.type.ICE);
            case ("ghost"):
                return (PokeTypes.type.GHOST);
            case ("dragon"):
                return (PokeTypes.type.DRAGON);
            case ("dark"):
                return (PokeTypes.type.DARK);
            default:
                System.out.println("Ajouter une exception (mauvaise écriture du type dans la BDD)");
                return null;
        }
    }

    /*public Nature init_nature(String nature) {
        switch (nature) {
            case ("bold"):
                return (Nature.BOLD);
            case ("quirky"):
                return (Nature.QUIRKY);
            case ("brave"):
                return (Nature.BRAVE);
            case ("calm"):
                return (Nature.CALM);
            case ("quiet"):
                return (Nature.QUIET);
            case ("docile"):
                return (Nature.DOCILE);
            case ("mild"):
                return (Nature.MILD);
            case ("rash"):
                return (Nature.RASH);
            case ("gentle"):
                return (Nature.GENTLE);
            case ("hardy"):
                return (Nature.HARDY);
            case ("jolly"):
                return (Nature.JOLLY);
            case ("lax"):
                return (Nature.LAX);
            case ("impish"):
                return (Nature.IMPISH);
            case ("sassy"):
                return (Nature.SASSY);
            case ("naughty"):
                return (Nature.NAUGHTY);
            case ("modest"):
                return (Nature.MODEST);
            case ("naive"):
                return (Nature.NAIVE);
            case ("hasty"):
                return (Nature.HASTY);
            case ("careful"):
                return (Nature.CAREFUL);
            case ("bashful"):
                return (Nature.BASHFUL);
            case ("relaxed"):
                return (Nature.RELAXED);
            case ("adamant"):
                return (Nature.ADAMANT);
            case ("serious"):
                return (Nature.SERIOUS);
            case ("lonely"):
                return (Nature.LONELY);
            case ("timid"):
                return (Nature.TIMID);
            default:
                System.out.println("La nature dans la BDD est mal rédigée (AJOUTER UNE EXCPETION !)");
        }
        return null;
    }*/


    public Pokemon init_pok(Connection Mycon, String name_pok, int id_pok) {


        //Récupération du numéro du set du Pokémon
        int id_set = ret_set(Mycon, id_pok);

        PreparedStatement Prep_statement;
        ResultSet Myresults = null;

        //Récupération du ou des types des Pokémons
        String type = "a";
        String type_test = "b";

        //Obtention du nombre de type du Pokémon
        int nb_types = 0;
        try {
            Prep_statement = Mycon.prepareStatement("SELECT count(*) from pokemon_type WHERE pokemon = ?");
            Prep_statement.setInt(1, id_pok);
            Myresults = Prep_statement.executeQuery();
            nb_types = Myresults.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //Ajout des types dans les variables type1 et type2
        try {
            Prep_statement = Mycon.prepareStatement("SELECT type from pokemon_type WHERE pokemon = ?");
            Prep_statement.setInt(1, id_pok);
            Myresults = Prep_statement.executeQuery();
            type = Myresults.getString(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PokeTypes.type type1 = init_poketype(type);
        PokeTypes.type type2 = null;
        if (nb_types > 1) {
            try {
                while (Myresults.next()) {
                    type_test = Myresults.getString(1);
                    if (!type_test.equals(type1)) {
                        type = type_test;
                        type2 = init_poketype(type);
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        //Récupération des stats du Pokémon d'identifiant id_pok
        int hp = 0;
        int attaque = 0;
        int defense = 0;
        int att_spe = 0;
        int def_spe = 0;
        int vitesse = 0;
        try {
            //HP
            Prep_statement = Mycon.prepareStatement("SELECT base_stat FROM pokemon_stat WHERE pokemon = ? and stat = ?");
            Prep_statement.setInt(1, id_pok);
            Prep_statement.setInt(2, 1);
            Myresults = Prep_statement.executeQuery();
            hp = Myresults.getInt(1);

            //ATTAQUE
            Prep_statement.setInt(1, id_pok);
            Prep_statement.setInt(2, 2);
            Myresults = Prep_statement.executeQuery();
            attaque = Myresults.getInt(1);

            //DEFENSE
            Prep_statement.setInt(1, id_pok);
            Prep_statement.setInt(2, 3);
            Myresults = Prep_statement.executeQuery();
            defense = Myresults.getInt(1);

            //ATTAQUE SPÉCIALE
            Prep_statement.setInt(1, id_pok);
            Prep_statement.setInt(2, 4);
            Myresults = Prep_statement.executeQuery();
            att_spe = Myresults.getInt(1);

            //DÉFENSE SPÉCIALE
            Prep_statement.setInt(1, id_pok);
            Prep_statement.setInt(2, 5);
            Myresults = Prep_statement.executeQuery();
            def_spe = Myresults.getInt(1);

            //VITESSE
            Prep_statement.setInt(1, id_pok);
            Prep_statement.setInt(2, 6);
            Myresults = Prep_statement.executeQuery();
            vitesse = Myresults.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        //Récupération des stats EV du Pokémon d'identifiant id_pok
        int hp_ev = 0;
        int attaque_ev = 0;
        int defense_ev = 0;
        int att_spe_ev = 0;
        int def_spe_ev = 0;
        int vitesse_ev = 0;

        try {
            //HP EV
            Prep_statement = Mycon.prepareStatement("SELECT effort FROM sets_stat WHERE id = ? and stat = ?");
            Prep_statement.setInt(1, id_set);
            Prep_statement.setInt(2, 1);
            Myresults = Prep_statement.executeQuery();
            hp_ev = Myresults.getInt(1);

            //ATTAQUE EV
            Prep_statement.setInt(1, id_set);
            Prep_statement.setInt(2, 2);
            Myresults = Prep_statement.executeQuery();
            attaque_ev = Myresults.getInt(1);

            //DEFENSE EV
            Prep_statement.setInt(1, id_set);
            Prep_statement.setInt(2, 3);
            Myresults = Prep_statement.executeQuery();
            defense_ev = Myresults.getInt(1);

            //ATTAQUE SPÉCIALE EV
            Prep_statement.setInt(1, id_set);
            Prep_statement.setInt(2, 4);
            Myresults = Prep_statement.executeQuery();
            att_spe_ev = Myresults.getInt(1);

            //DÉFENSE SPÉCIALE EV
            Prep_statement.setInt(1, id_set);
            Prep_statement.setInt(2, 5);
            Myresults = Prep_statement.executeQuery();
            def_spe_ev = Myresults.getInt(1);

            //VITESSE EV
            Prep_statement.setInt(1, id_set);
            Prep_statement.setInt(2, 6);
            Myresults = Prep_statement.executeQuery();
            vitesse_ev = Myresults.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        //Récupération de la nature du Pokémon
        String nat = null;
        try {
            Prep_statement = Mycon.prepareStatement("SELECT nature from sets WHERE id = ?");
            Prep_statement.setInt(1, id_set);
            Myresults = Prep_statement.executeQuery();
            nat = Myresults.getString(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        //Récupération des moves du pokémon
        List<Move> Moves = new ArrayList<>();
        int id_move = 0;
        Move real_move;
        try {
            Prep_statement = Mycon.prepareStatement("SELECT move from sets_move WHERE id = ?");
            Prep_statement.setInt(1, id_set);
            Myresults = Prep_statement.executeQuery();
            while (Myresults.next()) {
                id_move = Myresults.getInt(1);
                real_move = init_move(Mycon, id_move);
                Moves.add(real_move);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return (new Pokemon(name_pok, type1, type2, 50, hp, attaque, defense, att_spe, def_spe, vitesse,
                hp_ev, attaque_ev, defense_ev, att_spe_ev, def_spe_ev, vitesse_ev, nat, Moves.get(0), Moves.get(1), Moves.get(2), Moves.get(3)));
    }


    //6 arguments avec 6 names différents et throw des exceptions si les champs ne sont pas bons (renvoyer le numéro du champ)
    public Team create() {

        //Affiche les choix possible et récupere la taille de l'équipe à créer
        int nb_pok = menu();

        //Création des variables de connexion et d'accès à la BDD de nos Pokémons
        System.out.println("Voici la liste des Pokémons existants");
        Connection Mycon = dbConnection.connect();
        Statement Norm_statement;
        PreparedStatement Prep_statement = null;
        ResultSet Myresults;

        //Liste stockant le nom de tous les Pokémons ayant un set dans la BDD
        List <String> poke_with_set = new ArrayList<>();

        //Liste stockant le nom de tous les Pokémons faisant partie de l'équipe
        List <String> poke_in_team = new ArrayList<>();

        //Liste stockant les id de tous les Pokémons faisant partie de l'équipe.
        List <Integer> poke_ids = new ArrayList<>();
        //Affichage du nom de tous les Pokémons existants dans la BDD.
        try {
            String sql = "SELECT DISTINCT pretty_name from pokemon,sets WHERE pokemon.id = sets.pokemon";
            Norm_statement = Mycon.createStatement();

            Myresults = Norm_statement.executeQuery(sql);
            System.out.println("Liste des Pokémons :");
            while (Myresults.next()) {
                String name = Myresults.getString("pretty_name");
                poke_with_set.add(name);
                System.out.println("Nom : " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Tant que la team n'est pas complétée on demande un nouveau Pokémon à ajouter
        Scanner sc = new Scanner(System.in);
        String wanted_pokemon; // Nom du pokémon à ajouter
        int id_pok = 0; //Id du Pokémon à ajouter
        Team New_team = new Team(); //Objet de la Team des Pokémons
        Pokemon New_pokemon; // Objet qui contiendra le Pokémon à ajouter à la Team
        while (nb_pok > 0) {
            System.out.println("Quel est le nom du Pokémon à ajouter ?");
            wanted_pokemon = sc.next();
            if (poke_with_set.contains(wanted_pokemon)){
                if (!poke_in_team.contains(wanted_pokemon)){
                    //Récupération de l'ID du pokémon
                    System.out.println("Pokémon voulu : " + wanted_pokemon);
                    try {
                        Prep_statement = Mycon.prepareStatement("SELECT id FROM pokemon WHERE pretty_name like ? ");
                        Prep_statement.setString(1, wanted_pokemon);
                        Myresults = Prep_statement.executeQuery();
                        id_pok = Myresults.getInt(1);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    System.out.println("Id du pokémon : " + id_pok);
                    New_pokemon = init_pok(Mycon, wanted_pokemon, id_pok);
                    poke_ids.add(id_pok);
                    poke_in_team.add(wanted_pokemon);
                    New_team.addPokemon(New_pokemon);
                    nb_pok--;
                }
                else {
                    System.out.println("Ce Pokémon fait déjà partie de votre équipe !");
                }
            }
            else {
                System.out.println("Veuillez entrer le nom d'un des Pokémons listé !");
            }
        }
        System.out.println("Sauvegarde de l'équipe...");
        if (poke_ids.size() == 3){
            String sql = "INSERT INTO Team3 (P1, P2, P3) VALUES (?, ?, ?)";
            try {
                Prep_statement= Mycon.prepareStatement(sql);
                int j = 0;
                for(int i=1; i<=4; i++){
                    Prep_statement.setInt(i, poke_ids.get(j));
                    j++;
                }
                int row = Prep_statement.executeUpdate();
                System.out.println("Ligne affectée : " + row);
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return (New_team);
    }
}
