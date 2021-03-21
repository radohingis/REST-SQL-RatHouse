package sk.kosickaakademia.hingis.rat_house.database;

import sk.kosickaakademia.hingis.rat_house.entity.Rat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQL {

    public boolean insertRat(Rat rat) {

        try(Connection connection = DatabaseConnection.connect()) {

            if (connection == null) {
                System.out.println("Failed to get connection");
            }


            String insertRatQuery = "insert into rat (name, age, gender, color) values (?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertRatQuery);


            if(rat.getAge() > 0 || rat.getAge() <= 10
                                || rat.getGender() < 0
                                || rat.getGender() > 1
                                || rat.getName().isEmpty())
                                return false;

            preparedStatement
                    .setString(1, rat.getName());

            preparedStatement
                    .setByte(2, rat.getAge());

            preparedStatement
                    .setByte(3, rat.getGender());

            preparedStatement
                    .setString(4, rat.getColor());

            return preparedStatement.execute();

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

        return false;
    }

}
