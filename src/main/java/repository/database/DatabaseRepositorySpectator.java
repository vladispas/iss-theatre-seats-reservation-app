package repository.database;

import model.Spectator;
import repository.IRepositorySpectator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class DatabaseRepositorySpectator implements IRepositorySpectator {

    private JdbcUtils utils;

    public DatabaseRepositorySpectator(Properties properties) {
        utils = new JdbcUtils(properties);
    }

    @Override
    public void add(Spectator spectator) {
        Connection connection = utils.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Spectators (email, password) values (?, ?)")) {
            preparedStatement.setString(1, spectator.getEmail());
            preparedStatement.setString(2, spectator.getPassword());

            int result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }
    }

    @Override
    public void delete(Spectator spectator) {

    }

    @Override
    public void update(Spectator spectator, Integer ID) {

    }

    @Override
    public Spectator findByID(Integer ID) {
        Connection connection = utils.getConnection();

        Spectator spectator = new Spectator();

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from Spectators where id = ?")) {
            preparedStatement.setInt(1, ID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer spectatorID = resultSet.getInt("id");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String bookings_string = resultSet.getString("bookings");
                    String[] bookings_array = bookings_string.split(",");
                    List<Integer> bookings = new ArrayList<>();
                    for (String booking : bookings_array) {
                        bookings.add(Integer.parseInt(booking));
                    }

                    spectator.setID(spectatorID);
                    spectator.setEmail(email);
                    spectator.setPassword(password);
                    spectator.setBookings(bookings);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }

        return spectator;
    }

    @Override
    public Collection<Spectator> findAll() {
        Connection connection = utils.getConnection();

        List<Spectator> spectators = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from Spectators")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer ID = resultSet.getInt("id");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String bookings_string = resultSet.getString("bookings");
                    String[] bookings_array = bookings_string.split(",");
                    List<Integer> bookings = new ArrayList<>();
                    for (String booking : bookings_array) {
                        bookings.add(Integer.parseInt(booking));
                    }

                    Spectator spectator = new Spectator(email, password);
                    spectator.setID(ID);
                    spectator.setBookings(bookings);

                    spectators.add(spectator);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }

        return spectators;
    }

    @Override
    public Integer findByDetails(String email, String password) {
        Connection connection = utils.getConnection();

        Integer ID = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from Spectators where email = ? and password = ?")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ID = resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }

        return ID;
    }

    @Override
    public Integer findByEmail(String email) {
        Connection connection = utils.getConnection();

        Integer ID = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from Spectators where email = ?")) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ID = resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }

        return ID;
    }
}
