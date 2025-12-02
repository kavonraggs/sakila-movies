import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataManager {
    private DataSource dataSource;

    public DataManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<Actor> searchActorByLastName(String lastName) {
        ArrayList<Actor> actorList = new ArrayList<Actor>();

        String sql = "select actor_id, last_name, first_name from actor where last_name = ?";

        try (Connection connection = dataSource.getConnection()) {


            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1,lastName);

                try (ResultSet results = statement.executeQuery()) {

                    if (results.next()) {
                        do {
                            int actorID = results.getInt("actor_id");
                            String firstName = results.getString("first_name");
                            String last = results.getString("last_name");
                            Actor actor = new Actor(actorID, firstName, last);
                            actorList.add(actor);
                        }
                        while (results.next());
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actorList;
    }

    public ArrayList<Film> getFilmByActorId(int actorID){
      ArrayList<Film> filmsList = new ArrayList<>();

      String sql = "select f.film_id, f.title, f.description, f.release_year, f.length FROM film f Join film_actor fa ON f.film_id = fa.film_id WHERE fa.actor_id = ?";

      try (Connection connection = dataSource.getConnection()){
           try (PreparedStatement statement = connection.prepareStatement(sql)) {

               statement.setInt(1, actorID);

               try(ResultSet results = statement.executeQuery()){
                   if (results.next()){
                       do {
                           int filmId = results.getInt("film_id");
                           String title = results.getString("title");
                           String description = results.getString("description");
                           int releaseYear = results.getInt("release_year");
                           int length = results.getInt("length");
                            Film film = new Film(filmId, title, description, releaseYear, length);
                           filmsList.add(film);
                       } while (results.next());
                   }
               }
           }

      } catch (SQLException e){
          throw new RuntimeException(e);
      }
        return filmsList;
    }
}
