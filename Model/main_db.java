package Model;
import java.sql.*;

public class main_db {
    public static void main(String[] args) {
        Connection con = dbConnection.connect();
        System.out.println("salut");
        try{
            String sql = "SELECT * FROM pokemons";
            Statement statement = con.createStatement();

            ResultSet result = statement.executeQuery(sql);
            System.out.println("Liste des Pokémons :");
            while(result.next()){
                String name = result.getString("nom");
                System.out.println("Nom " + name);
            }
        }
        catch (SQLException e){
            System.out.println("mais quoi");
        }
    }
}
