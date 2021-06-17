package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Utils.Pair;

public class TeamBuilder {

    /**
     * Fonction de récupération de le l'ID du set du Pokémon passé en paramètre
     * @param id_pok ID du Pokémon
     * @return l'identifiant du set
     */
    public static int ret_set(Connection Mycon, int id_pok) {

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

    /**
     * Fonction de création d'un objet Move
     * @param id_move ID du Move
     * @return un Move correspond à l'id_move
     */
    public static Move init_move(Connection Mycon, int id_move){

        PreparedStatement Prep_statement;
        ResultSet Myresults;

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
            Prep_statement = Mycon.prepareStatement("SELECT pretty_name, type, damage_class, power, pp, priority, crit_rate, accuracy from move where id = ?");
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

    /**
     * Fonction de création d'un Type (pour les Pokémons ou les moves)
     * @param type Chaîne de caractères représentant le type
     * @return Un objet issu de l'énumération type de la classe PokeTypes
     */
    public static PokeTypes.type init_poketype(String type){
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
                throw new WrongTypeBDDException("Un champ type de la BDD n'est pas correctement inscrit");
        }
    }

    /**
     * Fonction de création d'un Pokémon
     * @param Mycon Connection établie avec notre BDD locale
     * @param name_pok Nom du Pokémon à instancier
     * @param id_pok Identifiant BDD du Pokémon à instancier
     * @return L'objet Pokemon instancié
     */
    public static Pokemon init_pok(Connection Mycon, String name_pok, int id_pok){


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
                hp_ev, attaque_ev, defense_ev, att_spe_ev, def_spe_ev, vitesse_ev, nat, Moves.get(0), Moves.get(1), Moves.get(2), Moves.get(3), id_pok));
    }

    /**
     * Fonction de récupération de l'ID d'un Pokémon à partir de son nom
     * @param Mycon Connection établie avec notre BDD locale
     * @param pok_name Nom du Pokémon à traiter
     * @return L'identifiant BDD du Pokémon
     */
    public int get_id_pok(Connection Mycon, String pok_name) {

        PreparedStatement Prep_statement;
        ResultSet Myresults;

        int id_pok = 0;
        try {
            Prep_statement = Mycon.prepareStatement("SELECT id FROM pokemon WHERE pretty_name like ? ");
            Prep_statement.setString(1, pok_name);
            Myresults = Prep_statement.executeQuery();
            id_pok = Myresults.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id_pok;
    }

    /**
     * Liste contenant toutes les équipes de 3 Pokémons
     */
    public static List<Team> allTeams3 = new ArrayList<>();

    /**
     * Liste contenant toutes les équipes de 6 Pokémons
     */
    public static List<Team> allTeams6 = new ArrayList<>();

    /**
     * Fonction de récupération de tous les Pokémons jouables de notre BDD
     * @return Une association entre les IDs BDD et les noms des Pokémons jouables
     */
    public static Pair<List<Integer>, List<String>> getAvailablePokemons(){

        Connection mycon = dbConnection.connect();
        Statement stmt;
        ResultSet Myresults = null;

        List<Integer> id_available_poks = new ArrayList<>();
        List<String> name_available_poks = new ArrayList<>();

        try {
            String sql = "SELECT * from Sets LEFT JOIN pokemon WHERE Sets.pokemon = pokemon.id ORDER BY pokemon";
            stmt = mycon.createStatement();
            Myresults = stmt.executeQuery(sql);
            while(Myresults.next()){
                id_available_poks.add(Myresults.getInt("pokemon"));
                name_available_poks.add(Myresults.getString("pretty_name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (new Pair<>(id_available_poks, name_available_poks));
    }

    /**
     * Fonction de récupération d'une équipe Pokémon à partir de son nom
     * @param teamName Le nom de l'équipe recherchée
     * @param allTeams La liste d'équipe dans laquelle rechercher l'équipe
     * @return L'équipe Pokémon associée au nom
     */
    public static Team getTeamByName(String teamName, List<Team> allTeams){
        for(Team t : allTeams){
            if(teamName.equals(t.getName())){
                return (t);
            }
        }
        return null;
    }


    /**
     * Procédure de création d'une équipe Pokémon à partir des noms des Pokémons à y ajouter et du nom de l'équipe.
     * L'équipe créée sera stockée dans un objet Liste de Teams pour la session courante et sera également stockée dans la BDD
     * pour être sauvegardée pour les prochaines sessions de jeu.
     * @param wanted_pokemons Liste des noms des Pokémons à ajouter dans l'équipe à créer
     * @param team_name Le nom à attribuer à l'équipe à créer
     */
    //6 arguments avec 6 names différents et throw des exceptions si les champs ne sont pas bons (renvoyer le numéro du champ)
    public void create(List<String> wanted_pokemons, String team_name) throws UnknownPokemonException, MultipleSamePokemonException, TeamNameAlreadyExistsException{

        //Création des variables de connexion et d'accès à la BDD de nos Pokémons
        Connection Mycon = dbConnection.connect();
        Statement Norm_statement;
        Statement stmt;
        PreparedStatement Prep_statement = null;
        ResultSet Myresults;


        //On récupere maintenant la taille de la liste de Pokémons voulus pour savoir si l'on affaire à une équipe de 3 ou de 6
        int nb_poks = wanted_pokemons.size();

        //Liste stockant le nom de tous les Pokémons ayant un set dans la BDD
        List<String> poke_with_set = new ArrayList<>();

        //Liste stockant le nom de tous les Pokémons faisant partie de l'équipe
        List<String> poke_in_team = new ArrayList<>();

        //Liste stockant les id de tous les Pokémons faisant partie de l'équipe.
        List<Integer> poke_ids = new ArrayList<>();

        //Liste stockant les id de tous les Pokémons faisant partie de l'équipe.
        List<String> teams_names = new ArrayList<>();

        //Créer une liste Java contenant tous les noms des Pokémons de notre BDD ayant un set

        try {
            String sql = "SELECT DISTINCT pretty_name from pokemon,sets WHERE pokemon.id = sets.pokemon";
            stmt = Mycon.createStatement();

            Myresults = stmt.executeQuery(sql);
            System.out.println("Liste des Pokémons :");
            while (Myresults.next()) {
                String name = Myresults.getString("pretty_name");
                poke_with_set.add(name);
                System.out.println("Nom : " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String table = null;
        if(nb_poks == 3){
            table = "Team3";
        }
        else if(nb_poks == 6){
            table = "Team6";
        }


        try {
            Norm_statement = Mycon.createStatement();
            Myresults = Norm_statement.executeQuery("SELECT name from " + table);
            while (Myresults.next()){
                teams_names.add(Myresults.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(teams_names.contains(team_name)){
            throw new TeamNameAlreadyExistsException("Il existe déjà une équipe portant ce nom !");
        }


        //Vérifier que les noms des Pokémons soient corrects et qu'il n'y ait pas de doublons
        //Permet égalment de faire une liste d'entier contenant les ID des pokémons voulus
        int loc_wrong_pok = 0;
        int id_pok;
        for (String wp : wanted_pokemons) {
            loc_wrong_pok++;
            if (!poke_with_set.contains(wp)) {
                throw new UnknownPokemonException("Le Pokémon n'est pas connu dans la BDD", loc_wrong_pok);
            }
            if (poke_in_team.contains(wp)) {
                throw new MultipleSamePokemonException("Le Pokémon fait déjà partie de votre équipe !", loc_wrong_pok);
            }
            poke_in_team.add(wp);
            id_pok = get_id_pok(Mycon, wp);
            poke_ids.add(id_pok);
        }

        //On ajoute les ids des pokémons à la table Team3 pour une équipe de 3 Pokémons
        if (nb_poks == 3) {
            String sql = "INSERT INTO Team3 ('name', 'P1', 'P2', 'P3')VALUES (?, ?, ?, ?)";
            try {
                Prep_statement = Mycon.prepareStatement(sql);
                Prep_statement.setString(1, team_name);
                Prep_statement.setInt(2, poke_ids.get(0));
                Prep_statement.setInt(3, poke_ids.get(1));
                Prep_statement.setInt(4, poke_ids.get(2));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //On ajoute les ids des pokémons à la table Team6 pour une équipe de 3 Pokémons
        else if (nb_poks == 6) {
            String sql = "INSERT INTO Team6 ('name', 'P1', 'P2', 'P3', 'P4', 'P5', 'P6') VALUES (?, ?, ?, ?, ?, ?, ?)";
            try {
                Prep_statement = Mycon.prepareStatement(sql);
                Prep_statement.setString(1, team_name);
                Prep_statement.setInt(2, poke_ids.get(0));
                Prep_statement.setInt(3, poke_ids.get(1));
                Prep_statement.setInt(4, poke_ids.get(2));
                Prep_statement.setInt(5, poke_ids.get(3));
                Prep_statement.setInt(6, poke_ids.get(4));
                Prep_statement.setInt(7, poke_ids.get(5));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //Exécution de la requête d'insertion
        int row = 0;
        try {
            row = Prep_statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Ligne modifiée : " + row);

        //Création et attribution de l'équipe à la liste des équipes
        Team New_team = new Team(team_name); //Objet de la Team des Pokémons
        Pokemon New_pokemon; // Objet qui contiendra le Pokémon à ajouter à la Team
        for (int i = 0; i < nb_poks; i++) {
            New_pokemon = init_pok(Mycon, wanted_pokemons.get(i), poke_ids.get(i));
            New_team.addPokemon(New_pokemon);
        }

        //Ajout de la nouvelle équipe dans l'objet de liste d'équipes adéquat
        if(nb_poks == 3){
            TeamBuilder.allTeams3.add(New_team);
        }
        else if(nb_poks == 6){
            TeamBuilder.allTeams6.add(New_team);
        }
        try {
            Mycon.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Procédure de chargement de toutes les Teams de notre BDD dans l'objet Liste de Teams.
     * Cette procédure sera exécutée à chaque lancement du jeu.
     */
    public static void load_teams(){
        //Création des variables de connexion et d'accès à la BDD de nos Pokémons
        Connection Mycon = dbConnection.connect();
        Statement Norm_statement;
        PreparedStatement Prep_statement = null;
        ResultSet Myresults = null;
        ResultSet Myresultsid = null;

        String sql = "SELECT * from Team6";

        try {
            Norm_statement = Mycon.createStatement();
            Myresults = Norm_statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Team temp_team;
        String team_name;
        Pokemon temp_pokemon;
        int id_pok;
        String pok_name;
        try {
            while (Myresults.next()) {
                team_name = Myresults.getString("name");
                temp_team = new Team(team_name);
                for (int i = 2; i <= 7; i++) {
                     id_pok = Myresults.getInt(i);
                     Prep_statement = Mycon.prepareStatement("SELECT pretty_name from pokemon where id = ?");

                     Prep_statement.setInt(1,id_pok);
                     Myresultsid = Prep_statement.executeQuery();
                     pok_name = Myresultsid.getString("pretty_name");
                     temp_pokemon = init_pok(Mycon, pok_name, id_pok);
                     temp_team.addPokemon(temp_pokemon);
                }
                TeamBuilder.allTeams6.add(temp_team);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        sql = "SELECT * from Team3";

        try {
            Norm_statement = Mycon.createStatement();
            Myresults = Norm_statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            while (Myresults.next()) {
                team_name = Myresults.getString("name");
                temp_team = new Team(team_name);
                for (int i = 2; i <= 4; i++) {
                    id_pok = Myresults.getInt(i);
                    Prep_statement = Mycon.prepareStatement("SELECT pretty_name from pokemon where id = ?");

                    Prep_statement.setInt(1,id_pok);
                    Myresultsid = Prep_statement.executeQuery();
                    pok_name = Myresultsid.getString("pretty_name");
                    temp_pokemon = init_pok(Mycon, pok_name, id_pok);
                    temp_team.addPokemon(temp_pokemon);
                }
                TeamBuilder.allTeams3.add(temp_team);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Mycon.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Procédure de suppression d'une équipe à partir de son nom.
     * @param team_name Nom de l'équipe à supprimer
     * @param size Taille de l'équipe à supprimer
     */
    public static void remove_team(String team_name, int size) throws UnknownTeamException {
        //Création des variables de connexion et d'accès à la BDD de nos Pokémons
        Connection Mycon = dbConnection.connect();
        PreparedStatement pstmt = null;
        Statement nstmt = null;
        ResultSet rslts = null;

        String sql = null;

        if(size == 3 ){
            sql = "SELECT name from Team3";
        }
        else if(size == 6){
            sql = "SELECT name from Team3";
        }

        List<String> teams_names = new ArrayList<>();
        try {
            nstmt = Mycon.createStatement();
            rslts = nstmt.executeQuery(sql);
            while (rslts.next()){
                teams_names.add(rslts.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(!teams_names.contains(team_name)){
            throw new UnknownTeamException("L'équipe que vous essayez de supprimer n'existe pas !");
        }

        if(size == 3 ){
            sql = "DELETE from Team3 WHERE name like ?";
        }
        else if(size == 6){
            sql = "DELETE from Team6 WHERE name like ?";
        }

        try {
            pstmt = Mycon.prepareStatement(sql);
            pstmt.setString(1,team_name);
            pstmt.executeUpdate();
            System.out.println("C'est supprimé !");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Mycon.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
}
