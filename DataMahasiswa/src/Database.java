import java.sql.*;

public class Database {
    private Connection connection;
    private Statement statement;

    //constructur
    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mahasiswa", "root", "");
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //digunakan untuk SELECT
    public ResultSet selectQuery(String sql) {
        try {
            statement.executeQuery(sql);
            return statement.getResultSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insertUpdateDeleteQuery(String sql) {
        int rowsAffected = 0;
        try {
            // Execute the SQL query
            Statement statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(sql);
        } catch (SQLException e) {
            // Handle any potential SQLException
            e.printStackTrace();
        }
        return rowsAffected;
    }

    //getter
    public Statement getStatement() {
        return statement;
    }


}
