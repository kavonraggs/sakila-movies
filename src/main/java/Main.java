import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Enter last name of actor: ");
        String choice = scanner.nextLine();

        try (BasicDataSource dataSource = new BasicDataSource()) {
            dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
            dataSource.setUsername("root");
            dataSource.setPassword("yearup");

            DataManager dataManager = new DataManager(dataSource);
            List<Actor> actors = dataManager.searchActorByLastName(choice);

            for (Actor actor: actors){
                System.out.println(actor);
            }

            if (actors.isEmpty()){
                System.out.println("No matches found!");
                return;
            }

            System.out.println("Enter Actor ID: ");
            int input = scanner.nextInt();
            scanner.nextLine();
            List<Film> films = dataManager.getFilmByActorId(input);

            for (Film film: films){
                System.out.println(film);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
