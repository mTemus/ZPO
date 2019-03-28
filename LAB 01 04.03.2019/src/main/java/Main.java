import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("src/java/view/employeeView.fxml"));
        primaryStage.setTitle("Panel administratora");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        try {
            Connection connMYSQL = DriverManager.getConnection("jdbc:mysql://localhost:3306/zpo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "admin");

            Statement myStatement = connMYSQL.createStatement();
            ResultSet myResultSet = myStatement.executeQuery("select * from employee");

            while (myResultSet.next()) {
                System.out.println(myResultSet.getString("id") + ", " + myResultSet.getString("name"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}

