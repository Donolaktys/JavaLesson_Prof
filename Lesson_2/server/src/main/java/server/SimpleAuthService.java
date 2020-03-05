package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement ps;

    public SimpleAuthService() {

    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {

        try {
            connectUserDB();
            ps = connection.prepareStatement("SELECT password, nick FROM Users WHERE login = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.getString(1).equals(password)) {
                return rs.getString(2);
            }
        } catch (Exception e) {
            return null;
        } finally {
            disconnectUserDB();
        }
        return null;
    }

    @Override
    public boolean registration(String login, String password, String nickname) {

        try {
            connectUserDB();
            ps = connection.prepareStatement("INSERT INTO Users (login, password, nick) VALUES (?,?,?);");
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, nickname);
            ps.executeUpdate();
        } catch (Exception e) {
            return false;
        } finally {
            disconnectUserDB();
        }
        return true;
    }

    public static void connectUserDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:regUsers.db");
        stmt = connection.createStatement();
    }

    public static void disconnectUserDB() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
