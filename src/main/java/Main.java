import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.print("Enter last name of actor: ");
        String choice = scanner.nextLine();

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername("root");
        dataSource. setPassword("yearup");

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select last_name, first_name from actor where last_name = ?");
        ){
            preparedStatement.setString(1, choice);
            try (ResultSet lastNameSearch = preparedStatement.executeQuery()
            ){
                if (lastNameSearch.next()){
                    System.out.println("Matches:");
                    do{
                        String firstName = lastNameSearch.getString("first_name");
                        String lastName = lastNameSearch.getString("last_name");
                        System.out.printf("%s %s\n", firstName, lastName);
                    }
                    while(lastNameSearch.next());
                } else {
                    System.out.println("No matches found");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
