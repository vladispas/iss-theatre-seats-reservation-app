package repository.database;

import model.Show;
import repository.IRepositoryShow;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class DatabaseRepositoryShow implements IRepositoryShow {

    private JdbcUtils utils;

    public DatabaseRepositoryShow(Properties properties) {
        utils = new JdbcUtils(properties);
    }

    @Override
    public void add(Show show) {
        Connection connection = utils.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Shows (title, date, duration)")) {
            preparedStatement.setString(1, show.getTitle());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(show.getDate()));
            preparedStatement.setDouble(3, show.getDuration());

            int result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }
    }

    @Override
    public void delete(Show show) {

    }

    @Override
    public void update(Show show, Integer ID) {

    }

    @Override
    public Show findByID(Integer ID) {
        Connection connection = utils.getConnection();

        Show show = new Show();

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from Shows where id = ?")) {
            preparedStatement.setInt(1, ID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer showID = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    Timestamp date = resultSet.getTimestamp("date");
                    Integer duration = resultSet.getInt("duration");
                    Integer numberOfSeats = resultSet.getInt("number_of_seats");

                    show.setID(showID);
                    show.setTitle(title);
                    show.setDate(date.toLocalDateTime());
                    show.setDuration(duration);
                    show.setNumberOfSeats(numberOfSeats);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }

        return show;
    }

    @Override
    public Collection<Show> findAll() {
        Connection connection = utils.getConnection();

        List<Show> shows = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from Shows")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer ID = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    Timestamp date = resultSet.getTimestamp("date");
                    Integer duration = resultSet.getInt("duration");
                    Integer numberOfSeats = resultSet.getInt("number_of_seats");

                    Show show = new Show(title, date.toLocalDateTime(), duration);
                    show.setID(ID);
                    show.setNumberOfSeats(numberOfSeats);

                    shows.add(show);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }

        return shows;
    }

    @Override
    public Integer findByDetails(String title, LocalDateTime date, Integer duration) {
        Connection connection = utils.getConnection();

        Integer ID = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from Shows where title = ? and date = ? and duration = ?")) {
            preparedStatement.setString(1, title);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(date));
            preparedStatement.setInt(3, duration);

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
