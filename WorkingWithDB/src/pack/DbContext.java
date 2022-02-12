package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbContext {
    String dbUser = "postgres";
    String dbPassword = "root";
    String dbName = "usersdb";
    Connection connection;

    public void connect () throws ClassNotFoundException, SQLException {
//        String URL = "jdbc:postgresql://localhost:5432/" + dbName + "?ssl=false";
        String URL = "jdbc:postgresql://localhost:5432/%s?ssl=false";
        URL = String.format(URL, dbName);
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(URL, dbUser, dbPassword);
    }

    public void close () throws SQLException {
        connection.close();
    }

    public void addUser(User user) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "insert into users(name,age,activated) values('%s', %d, %b)";
//      String sql = "insert into users(name,age,activated) values('" + user.getName() + "', " + user.getAge() + ", " + user.isActivated() + ")";
//      sql = "insert into users(name,age,activated) values('Alex', 22, true)";
        sql = String.format(sql, user.getName(), user.getAge(), user.isActivated());
        statement.executeUpdate(sql);
    }

    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("create table if not exists users(" +
                "id serial, " +
                "name varchar(50) not null, " +
                "age smallint, " +
                "activated boolean" +
                ")");
    }


}
