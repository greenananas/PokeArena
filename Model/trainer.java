package Model;

import java.util.Scanner;

/**
 * Class décrivant un dresseur de Pokémon.
 */
public class trainer {

    /**
     * Pseudonyme du dresseur
     */
    private String displayName;

    /**
     * Équipe de Pokémon du dresseur
     */
    private pokemon[] pokemonTeam;

    /**
     * Créer un dresseur
     *
     * @param name Pseudonyme du dresseur
     * @param team Équipe de Pokémon du dresseur
     */
    public trainer(String name,pokemon[] team){
        this.displayName=name;
        this.pokemonTeam=team;
    }

    /**
     * Obtenir le pseudonyme d'un dresseur
     *
     * @return Pseudonyme du dresseur
     */
    public String getDisplayName(){return this.displayName;}

    /**
     * Obtenir l'équipe de Pokémon d'un dresseur
     *
     * @return Équipe de Pokémon
     */
    public pokemon[] getPokemonTeam(){return this.pokemonTeam;}

    /**
     * Obtenir un Pokémon parmi l'équipe d'un dresseur
     *
     * @param n Numéro du Pokémon dans l'équipe du dresseur
     * @return Pokémon choisi
     */
    public pokemon getPokemon(int n){return this.pokemonTeam[n];}

    /**
     * Modifier le pseudonyme d'un dresseur
     *
     * @param n Nouveau pseudonyme du dresseur
     */
    public void setDisplayName(String n){this.displayName=n;}

    /**
     * Modifier l'équipe de Pokémon d'un dresseur
     *
     * @param team Nouvelle équipe du dresseur
     */
    public void setPokemonTeam(pokemon[] team){this.pokemonTeam=team;}

    /**
     * Modifier le Pokémon d'un dresseur
     *
     * @param p Nouveau Pokémon du dresseur
     * @param n Emplacement du Pokémon dans l'équipe du dresseur
     */
    public void setPokemon(pokemon p, int n){this.pokemonTeam[n]=p;}

    public boolean hasPokemonLeft(){
        for (pokemon p:this.getPokemonTeam()) {
            if(!p.isKO()){return true;}
        }
        return false;
    }

    public pokemon changePokemon(){
        int counter=1;
        pokemon[] alive = new pokemon[5];
        System.out.println(this.getDisplayName()+" change de Pokémon.");
        for (pokemon p:this.getPokemonTeam()) {
            if(!p.isKO()){
                alive[counter-1]=p;
                System.out.println(counter+" "+p.getName());
                counter++;
            }
        }
        Scanner s=new Scanner(System.in);
        boolean check=false;
        int choice=0;
        while(!check){
            choice=s.nextInt();
            switch (choice) {
                case 1:
                    check=true;
                    break;
                case 2:
                    if(counter>=2){check=true;}
                    else{System.out.println("Pokémon introuvable.");}
                    break;
                case 3:
                    if(counter>=3){check=true;}
                    else{System.out.println("Pokémon introuvable.");}
                    break;
                case 4:
                    if(counter>=4){check=true;}
                    else{System.out.println("Pokémon introuvable.");}
                    break;
                case 5:
                    if(counter>=5){check=true;}
                    else{System.out.println("Pokémon introuvable.");}
                    break;
                case 6:
                    if(counter>=6){check=true;}
                    else{System.out.println("Pokémon introuvable.");}
                    break;
                default:
                    System.out.println("Tu troll mec.");
                    break;
            }
        }
        return alive[choice-1];
    }
}
