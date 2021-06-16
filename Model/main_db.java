package Model;
import java.util.ArrayList;
import java.util.List;

public class main_db {
    public static void main(String[] args) throws MultipleSamePokemonException, UnknownPokemonException, TeamNameAlreadyExistsException, UnknownTeamException, WrongTypeBDDException {

        //Test_ajout
        List<String> wanted_pokemons = new ArrayList<>();
        TeamBuilder.load_teams();
        wanted_pokemons.add("Tortank");
        wanted_pokemons.add("Dracaufeu");
        wanted_pokemons.add("Florizarre");
        TeamBuilder nt = new TeamBuilder();
        nt.create(wanted_pokemons,"wowowo2_crew");
        System.out.println("Équipe " + TeamBuilder.allTeams3.get(TeamBuilder.allTeams3.size() - 1).getName()+ " terminée, voici les pokémons :");
        for(Team t : TeamBuilder.allTeams3){
            System.out.println("----------------------------------------------------------");
            for(Pokemon pok : t.getPokemons()){
                System.out.println("Identifiant du Pokémon : " + pok.getId());
                System.out.println("Nom : " + pok.getName());
                System.out.println("Stats :");
                System.out.println(" HP  : " + pok.getHP() +
                        " Attaque : " + pok.getAttack() +
                        " Défense : " + pok.getDefence() +
                        " Attaque Spéciale : " + pok.getSpeAttack() +
                        " Défense Spéciale : " + pok.getSpeDefence() +
                        " Vitesse : " + pok.getSpeed());
                System.out.println("Moves :");
                System.out.println(" Attaque 1 : " + pok.getMove1().getName() +
                        " Attaque 2 : " + pok.getMove2().getName() +
                        " Attaque 3 : " + pok.getMove3().getName() +
                        " Attaque 4 : " + pok.getMove4().getName());
                System.out.println("----------------------------------------------");
            }
        }

        //Test Suppression
        /*newTeam.remove_team("last3_crew",3);*/
        String findTeam = "leny_crew";
        Team t = TeamBuilder.getTeamByName(findTeam, TeamBuilder.allTeams3);
        System.out.println("Team : " + t.getName() + " trouvé !");
    }
}

