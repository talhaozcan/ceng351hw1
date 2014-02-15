package com.tauros;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmedtalha
 */
public class ShoppingMall implements IShoppingMall {

    static public Database db;
    static public Connection conn;

    public ShoppingMall() {
    }

    public static IShoppingMall newInstance() {
        ShoppingMall dt = new ShoppingMall();
        

        return (IShoppingMall) dt;
    }

    @Override
    public void onStart() { //------------------------!!!!!!!!!!!!!!!!!!!!!!!!
        db = new Database();
        conn = db.DBConnection();
    }

    @Override
    public boolean createDatabase() throws DatabaseAlreadyCreated { //---------------return false

        boolean x = false;
        int i = 0;

        try {

            DatabaseMetaData metData = this.conn.getMetaData();
            ResultSet res = metData.getTables(null, null, "stores", null);

            if (res.next()) {
                i++;
            }
            res = metData.getTables(null, null, "users", null);
            if (res.next()) {
                i++;
            }
            res = metData.getTables(null, null, "shopping", null);
            if (res.next()) {
                i++;
            }

            if (i == 3) {
                throw new DatabaseAlreadyCreated();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingMall.class.getName()).log(Level.SEVERE, null, ex);
        }

        String query1 = "CREATE TABLE stores ("
                + "name varchar(50),"
                + "category varchar(50),"
                + "PRIMARY KEY(name,category)"
                + ")";
        String query2 = "CREATE TABLE users ("
                + "userID VARCHAR(11) NOT NULL,"
                + "fullName VARCHAR(50),"
                + "address TEXT(500),"
                + "PRIMARY KEY(userID)"
                + ")";
        String query3 = "CREATE TABLE shopping ("
                + "user_ID VARCHAR(11),"
                + "sName varchar(50),"
                + "category varchar(50),"
                + "sDate DATE,"
                + "amount REAL NOT NULL,"
                + "FOREIGN KEY (sName,category) REFERENCES stores(name,category) ON DELETE SET NULL,"
                + "FOREIGN KEY (user_ID) REFERENCES users(userID) ON DELETE SET NULL"
                + ")";

        try {
            Statement stm1 = this.conn.createStatement();
            stm1.executeUpdate(query1);

            Statement stm2 = this.conn.createStatement();
            stm2.executeUpdate(query2);

            Statement stm3 = this.conn.createStatement();
            stm3.executeUpdate(query3);

            x = true;
            return x;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    @Override
    public void addStore(String storeName, Category category) throws StoreAlreadyExists {

        String query = "SELECT * FROM stores WHERE name ='" + storeName + "' AND category='" + category.toString()
                + "'";
        ResultSet res;

        try {
            PreparedStatement stm = this.conn.prepareStatement(query);
            res = stm.executeQuery();

            if (res.next()) {
                throw new StoreAlreadyExists();
            } else {
                String query2 = "INSERT INTO stores (name,category) VALUES ('" + storeName + "','" + category.toString() + "')";
                Statement stm2 = this.conn.createStatement();
                stm2.executeUpdate(query2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeStore(IStore store) throws StoreNotExist {

        String query = "SELECT * FROM stores WHERE name='" + store.getStoreName() + "' AND category='" + store.getStoreCategory().toString() + "'";
        ResultSet res;
        try {
            PreparedStatement pstm = this.conn.prepareStatement(query);
            res = pstm.executeQuery();

            if (res.next()) {
                String query2 = "DELETE FROM stores WHERE name='" + store.getStoreName() + "' AND category ='" + store.getStoreCategory().toString() + "'";

                Statement stm = this.conn.createStatement();
                stm.executeUpdate(query2);
            } else {
                throw new StoreNotExist();
            }



        } catch (Exception e) {
        }
    }

    @Override
    public void registerUser(String fullname, String id, String address) throws UserAlreadyExists {

        String query = "INSERT INTO users(userID,fullName,address) values (" + id + ",'" + fullname + "','" + address + "') ";
        int res;
        try {
            Statement stm1 = this.conn.createStatement();
            res = stm1.executeUpdate(query);

            if (res == 0) {

                throw new UserAlreadyExists();
            }
        } catch (SQLException e) {
            throw new UserAlreadyExists();

        }


    }

    @Override
    public void unregisterUser(IUser user) throws UserNotExist {

        String query = "DELETE FROM users WHERE userID='" + user.getId() + "'";
        int res;

        try {
            Statement stm = ShoppingMall.conn.createStatement();
            res = stm.executeUpdate(query);

            if (res == 0) {
                throw new UserNotExist();
            }
        } catch (Exception e) {
               throw new UserNotExist();
        }

    }

    @Override
    public IStore[] searchStore(String nameIncludes) {
        String query = "SELECT * FROM stores  WHERE "
                + "name LIKE '%" + nameIncludes
                + "%' ORDER BY name ";

        ResultSet res;

        List<IStore> stores = new ArrayList<IStore>();
        int i = 0;

        try {
            PreparedStatement pstm = ShoppingMall.conn.prepareStatement(query);
            res = pstm.executeQuery();

            while (res.next()) {


                stores.add(Store.newInstance(res.getString(1), Category.valueOf(res.getString(2))));
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        IStore[] st = new IStore[i];
        int j = 0;
        if (stores.size() == 0) {
            return null;
        }
        while (j < i) {

            st[j] = stores.get(j);
            j++;
        }
        return st;
    }

    @Override
    public IStore[] searchStore(String nameIncudes, Category category) {
        String query = "SELECT * FROM stores  WHERE "
                + "name LIKE '%" + nameIncudes
                + "%' AND category='" + category.toString()
                + "' ORDER BY name ";

        ResultSet res;

        List<IStore> stores = new ArrayList<IStore>();
        int i = 0;

        try {
            PreparedStatement pstm = ShoppingMall.conn.prepareStatement(query);
            res = pstm.executeQuery();

            while (res.next()) {


                stores.add(Store.newInstance(res.getString(1), Category.valueOf(res.getString(2))));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        IStore[] st = new IStore[i];
        int j = 0;
        if (stores.size() == 0) {
            return null;
        }
        while (j < i) {

            st[j] = stores.get(j);
            j++;
        }
        return st;
    }

    @Override
    public IStore[] searchStore(Category category) {
        String query = "SELECT * FROM stores  WHERE "
                + "category='" + category.toString()
                + "' ORDER BY name ";

        ResultSet res;

        List<IStore> stores = new ArrayList<IStore>();
        int i = 0;

        try {
            PreparedStatement pstm = ShoppingMall.conn.prepareStatement(query);
            res = pstm.executeQuery();

            while (res.next()) {


                stores.add(Store.newInstance(res.getString(1), Category.valueOf(res.getString(2))));
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        IStore[] st = new IStore[i];
        int j = 0;
        if (stores.size() == 0) {
            return null;
        }
        while (j < i) {

            st[j] = stores.get(j);
            j++;
        }

        return st;
    }
    @Override
    public IUser[] searchUser(String nameIncludes) {
        String query = "SELECT * FROM users WHERE "
                + "fullName LIKE '%" + nameIncludes
                + "%' ORDER BY fullName ";

        ResultSet res;

        List<IUser> users = new ArrayList<IUser>();
        int i = 0;

        try {
            PreparedStatement pstm = ShoppingMall.conn.prepareStatement(query);
            res = pstm.executeQuery();

            while (res.next()) {

                users.add((IUser)new User(res.getString(1),
                        res.getString(2),
                        res.getString(3)));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        IUser[] us = new IUser[i];
        int j = 0;
        if (users.size() == 0) {
            return null;
        }
        while (j < i) {

            us[j] = users.get(j);
            j++;
        }
        return us;
    }

    @Override
    public IUser getUser(String id) {
        String query = "SELECT * FROM users WHERE "
                + "userID = '" + id
                + "' ORDER BY fullName ";

        ResultSet res;

        IUser users = null;

        try {
            PreparedStatement pstm = ShoppingMall.conn.prepareStatement(query);
            res = pstm.executeQuery();
            while (res.next()) {

                users = User.newInstance(res.getString(1),
                        res.getString(2),
                        res.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}