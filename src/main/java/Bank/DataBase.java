package Bank;

import java.sql.*;

class DataBase {

    String url = "jdbc:sqlite:";

    public DataBase(String input) {
        url = url + input;
        createNewDataBase();
    }

    //method to create the initial database
    public void createNewDataBase() {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                createNewTable();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //method to create the card table
    public void createNewTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS card (\n"
                + "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " number TEXT,\n"
                + " pin TEXT,\n"
                + " balance INTEGER DEFAULT 0\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertData(String num, String pin) {
        String insertSql = "INSERT INTO card (number, pin) VALUES(?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, num);
            pstmt.setString(2, pin);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void displayTable() {
        String selectAll = "SELECT * FROM card;";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(selectAll)) {

            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("number") + "\t" +
                        rs.getString("pin") + "\t" +
                        rs.getInt("balance"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}