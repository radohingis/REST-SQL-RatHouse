package sk.kosickaakademia.hingis.rat_house.database;

import sk.kosickaakademia.hingis.rat_house.entity.Rat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL {

    private static final String KILL_THAT_RAT = "delete from rat where id = ?";

    public boolean insertRat(Rat rat) {

        try(Connection connection = DatabaseConnection.connect()) {

            if (connection == null) {
                System.out.println("Failed to get connection");
            }


            String insertRatQuery = "insert into rat (name, age, gender, color) values (?,?,?,?)";

            PreparedStatement preparedStatement
                                = connection
                                .prepareStatement(insertRatQuery);


                    if(rat.getAge() < (byte) 0
                    || rat.getAge() > (byte) 10
                    || rat.getGender().getGender() < 0
                    || rat.getGender().getGender() > 1
                    || rat.getName().isEmpty())
                    return false;


            preparedStatement
                    .setString(1, rat.getName());

            preparedStatement
                    .setInt(2, rat.getAge());

            preparedStatement
                    .setInt(3, rat.getGender()
                                                .getGender());

            preparedStatement
                    .setString(4, rat.getColor());

            return preparedStatement.execute();

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

        return false;
    }

    public Rat getRatById(int id) {

        if(id >= 0) {

            try(Connection connection = DatabaseConnection.connect()) {

                String getRatByIdQuery = "select * from rat where id = ?";

                PreparedStatement preparedStatement
                        = connection
                        .prepareStatement(getRatByIdQuery);

                preparedStatement.setInt(1, id);

                ResultSet result = preparedStatement.executeQuery();

                if(result.next()) {

                    String name = result.getString("name");
                    byte age = result.getByte("age");
                    byte gender = result.getByte("gender");
                    String color = result.getString("color");

                    Rat rat = new Rat(name, age, color, gender);

                    System.out.println(rat.stringify());


                    return new Rat(name, age, color, gender);

                } else {

                    return null;
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return null;
    }

    public boolean updateRatsAge(int id, byte age) {

                if(id < 0
                || age < 0
                || age > 9)
                return false;

                    try(Connection connection = DatabaseConnection.connect()) {

                        String updateRatsAgeQuery = "update rat set age = ? where id = ?";

                        PreparedStatement preparedStatement
                                            = connection
                                            .prepareStatement(updateRatsAgeQuery);

                        preparedStatement.setByte(1, age);
                        preparedStatement.setInt(2, id);

                        int querieAffected = preparedStatement.executeUpdate();

                        if(querieAffected == 1) return true;
                                                else return false;

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                return false;
    }

    public boolean killThatRat(int id) {

        if(id < 0) return false;

        try(Connection connection = DatabaseConnection.connect()) {

            PreparedStatement preparedStatement
                    = connection
                    .prepareStatement(KILL_THAT_RAT);

            preparedStatement.setInt(1, id);

            int result = preparedStatement
                        .executeUpdate();

            if(result == 1) return true;
                            else return false;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

}
