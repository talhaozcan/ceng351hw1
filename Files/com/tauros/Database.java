package com.tauros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ahmedtalha
 */
public class Database {

    private String user = "username";
    private String password = "password";
    private String host = "localhost";
    private String database = "database";
    private int port = 3306;
    private Connection conn;

    public  Database() {

        String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database;


        try {

            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, this.user, this.password);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
        }

    }

    public Connection DBConnection(){
    
        return conn;
    }
            
    
}
