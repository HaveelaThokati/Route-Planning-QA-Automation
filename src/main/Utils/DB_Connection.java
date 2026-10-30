// Author: haveela | Project: Route_Planning_Automation
package src.main.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB_Connection {

    public static ResultSet main(String query) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        // replace below details
        String url = "jdbc:postgresql://localhost:5432/route_planning_db";
        String username = "postgres";
        String password = "change_me";

        ResultSet rs = null;
        List<Object> sample = new ArrayList<>();

        try {
            Connection db = DriverManager.getConnection(url, username, password);
            // create object for the Statement class
            Statement st = db.createStatement();
            // execute the quesry on database
            rs = st.executeQuery(query);

            System.out.println("Data retrieved from the PostgreSQL database ");
// while (rs.next());
//            rs.close();
            // close the result set
//            st.close();
            //close the database connection
//            db.close();

            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
