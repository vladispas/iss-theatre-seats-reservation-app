package repository.database;

import model.Booking;
import model.Seat;
import model.Tuple;
import repository.IRepositoryBooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class DatabaseRepositoryBooking implements IRepositoryBooking {

    private JdbcUtils utils;

    public DatabaseRepositoryBooking(Properties properties) {
        utils = new JdbcUtils(properties);
    }

    @Override
    public void add(Booking booking) {
        Connection connection = utils.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Bookings (show_id, seats) values (?, ?)")) {
            preparedStatement.setInt(1, booking.getShowID());
            String seats = "";
            for (Tuple seat : booking.getSeats()) {
                seats += seat.getPosX() + ";" + seat.getPosY() + ",";
            }
            preparedStatement.setString(2, seats);

            int result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }
    }

    @Override
    public void delete(Booking booking) {
        Connection connection = utils.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from Bookings where id = ?")) {
            preparedStatement.setInt(1, booking.getID());

            int result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }
    }

    @Override
    public void update(Booking booking, Integer ID) {
        Connection connection = utils.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement("update Bookings set seats = ? where id = ?")) {
            String seats = "";
            for (Tuple seat : booking.getSeats()) {
                seats += seat.getPosX() + ";" + seat.getPosY() + ",";
            }
            preparedStatement.setString(1, seats);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }
    }

    @Override
    public Booking findByID(Integer ID) {
        Connection connection = utils.getConnection();

        Booking booking = new Booking();

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from Bookings where id = ?")) {
            preparedStatement.setInt(1, ID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer bookingID = resultSet.getInt("id");
                    Integer showID = resultSet.getInt("show_id");
                    String seatsString = resultSet.getString("seats");
                    String[] seats_array = seatsString.split(",");
                    List<Tuple> seats = new ArrayList<>();
                    for (String seat : seats_array) {
                        String[] pos = seat.split(";");
                        Tuple seatID = new Tuple(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
                        seats.add(seatID);
                    }

                    booking.setID(bookingID);
                    booking.setShowID(showID);
                    booking.setSeats(seats);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }

        return booking;
    }

    @Override
    public Collection<Booking> findAll() {
        Connection connection = utils.getConnection();

        List<Booking> bookings = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from Bookings")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer bookingID = resultSet.getInt("id");
                    Integer showID = resultSet.getInt("show_id");
                    String seatsString = resultSet.getString("seats");
                    String[] seats_array = seatsString.split(",");
                    List<Tuple> seats = new ArrayList<>();
                    for (String seat : seats_array) {
                        String[] pos = seat.split(";");
                        Tuple seatID = new Tuple(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
                        seats.add(seatID);
                    }

                    Booking booking = new Booking();
                    booking.setID(bookingID);
                    booking.setShowID(showID);
                    booking.setSeats(seats);

                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }

        return bookings;
    }

    @Override
    public Integer findByLastID() {
        Connection connection = utils.getConnection();

        Integer ID = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from Bookings")) {
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
