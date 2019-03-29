import java.sql.*;

public class Connector {

    {
        try {
            Connection connMYSQL = DriverManager.getConnection("jdbc:mysql://localhost:3306/zpo", "root", "admin");
            Statement myStatement = connMYSQL.createStatement();
            ResultSet myResultSet = myStatement.executeQuery("select * from employee");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
