package sample;

import sample.GetSet.Admins;
import sample.GetSet.User;
import sample.GetSet.Worker;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseHandler extends Config {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + databaseHost + ":" + databasePort + "/" + databaseName + "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, databaseUser, databasePass);
        return dbConnection;
    }

    // Запись пользователя в бд
    public void singUpUser(User user) {
        String insert = "INSERT INTO " + Сonstants.USER_TABLE + "(" + Сonstants.USER_FIRSTNAME + ","
                + Сonstants.USER_LASTNAME + "," + Сonstants.USER_LOGIN + "," + Сonstants.USER_PASSWORD + ")" +
                "VALUES(?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstname());
            prSt.setString(2, user.getLastname());
            prSt.setString(3, user.getLogin());
            prSt.setString(4, user.getPassword());
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Авторизация
    public ResultSet getUser(User user) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Сonstants.USER_TABLE + " WHERE " + Сonstants.USER_LOGIN +
                "=? AND " + Сonstants.USER_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    //Авторизация работника
    public ResultSet getWorker(Worker worker) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Сonstants.WORKER_TABLE + " WHERE " + Сonstants.WORKER_LOGIN +
                "=? AND " + Сonstants.WORKER_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, worker.getLogin());
            prSt.setString(2, worker.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    //Авторизация Администратора
    public ResultSet getAdmins(Admins admins) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Сonstants.ADMINS_TABLE + " WHERE " + Сonstants.ADMINS_LOGIN +
                "=? AND " + Сonstants.ADMINS_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, admins.getLogin());
            prSt.setString(2, admins.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }


    // Считывание услуг

    public void ResultSet() {
        try {
            
            ResultSet resSet = dbConnection.createStatement().executeQuery("SELECT * FROM service");
            while (resSet.next()) {
                resSet.getInt("idService");
                resSet.getString("NameService");
                resSet.getString("PriceService");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}