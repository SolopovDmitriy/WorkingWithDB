package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void test(){
//        String URL = "jdbc:postgresql://localhost:5432/usersdb?user=postgres&password=H#r34Disciple&ssl=false";
        String URL = "jdbc:postgresql://localhost:5432/usersdb?ssl=false";
        String user = "postgres";
        String pass = "root";
        try{
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(URL, user, pass);
            Statement stat = connection.createStatement();
            System.out.println("Connected");

            stat.execute("create table if not exists users(" +
                    "id serial, " +
                    "name varchar(50) not null, " +
                    "age smallint, " +
                    "activated boolean" +
                    ")");

            String sql = "insert into users(name,age,activated) values('%s',%d,%b)";
            sql = String.format(sql, "A", 18, true);
            stat.executeUpdate(sql);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        testStringFormat();

        DbContext context = new DbContext();
        try {
            context.connect();
            context.createTable();
            User user1 = new User("Alex", 22, true);
            context.addUser(user1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Вывод в консоль ошибки
        } catch (SQLException e) {
            e.printStackTrace(); // Вывод в консоль ошибки
        }
    }

    public static void testStringFormat(){
        String h = "hello";
        String text = "ok good %s lalala else not for ";
        String result2 = "ok good " + h +  " lalala else not for ";
        String result = String.format(text, h);
        System.out.println(result);
        System.out.println(result2);
    }





}
