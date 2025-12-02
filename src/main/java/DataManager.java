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
}
