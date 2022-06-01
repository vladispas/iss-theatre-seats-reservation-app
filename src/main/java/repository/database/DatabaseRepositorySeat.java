package repository.database;

import model.Seat;
import model.Show;
import model.Tuple;
import repository.IRepositorySeat;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class DatabaseRepositorySeat implements IRepositorySeat {

    private JdbcUtils utils;

    public DatabaseRepositorySeat(Properties properties) {
        utils = new JdbcUtils(properties);
    }

    @Override
    public void add(Seat seat) {

    }

    @Override
    public void delete(Seat seat) {

    }

    @Override
    public void update(Seat seat, Tuple ID) {

    }

    @Override
    public Seat findByID(Tuple ID) {
        Connection connection = utils.getConnection();

        Seat seat = new Seat();

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from Seats where pos_x = ? and pos_y = ?")) {
            preparedStatement.setInt(1, ID.getPosX());
            preparedStatement.setInt(2, ID.getPosY());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer posX = resultSet.getInt("pos_x");
                    Integer posY = resultSet.getInt("pos_y");
                    Double price = resultSet.getDouble("price");

                    Tuple seatID = new Tuple(posX, posY);
                    seat.setID(seatID);
                    seat.setPrice(price);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }

        return seat;
    }

    @Override
    public Collection<Seat> findAll() {
        Connection connection = utils.getConnection();

        List<Seat> seats = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from Seats")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer posX = resultSet.getInt("pos_x");
                    Integer posY = resultSet.getInt("pos_y");
                    Double price = resultSet.getDouble("price");

                    Tuple seatID = new Tuple(posX, posY);
                    Seat seat = new Seat();
                    seat.setID(seatID);
                    seat.setPrice(price);

                    seats.add(seat);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }

        return seats;
    }

}
