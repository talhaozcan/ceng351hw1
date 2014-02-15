package com.tauros;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahmedtalha
 */
public class User implements IUser {

    private String fullName;
    private String address;
    private String id;

    public User() {
    }

    public User(String id, String fullName, String address) {
        this.fullName = fullName;
        this.address = address;
        this.id = id;
    }

    public static IUser newInstance() {
        User dt = new User();

        return (IUser) dt;
    }

    public static IUser newInstance(String id, String fullName, String address) {
        User dt = new User(id, fullName, address);

        return (IUser) dt;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public void updateAddress(String newAdress) {

        String query = "UPDATE users SET address='" + newAdress + "' WHERE userID='" + this.getId() + "'";
        try {
            Statement stm = ShoppingMall.conn.createStatement();
            stm.executeQuery(query);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public void shopped(IStore store, String date, double amount) {


        String query = "INSERT INTO shopping (user_ID,sName,category,sDate,amount) VALUES ('"
                + this.getId() + "','"
                + store.getStoreName() + "','"
                + store.getStoreCategory().toString()
                + "',STR_TO_DATE('" + date.replace(".", "/") + "','%d/%m/%Y'),"
                + amount
                + ")";

        try {
            Statement stm = ShoppingMall.conn.createStatement();
            stm.executeUpdate(query);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public IShopping[] getShoppings(IStore store, String startDate, String endDate) {

        String query = "SELECT * FROM shopping  WHERE "
                + "user_ID='" + this.getId()
                + "' AND sName='" + store.getStoreName()
                + "' AND category='" + store.getStoreCategory().toString()
                + "' AND (sDate BETWEEN STR_TO_DATE('" + startDate.replace(".", "/") + "','%d/%m/%Y')"
                + " AND STR_TO_DATE('" + endDate.replace(".", "/") + "','%d/%m/%Y')) "
                + "ORDER BY sDate DESC";

        ResultSet res;

        List<IShopping> shoppings = new ArrayList<IShopping>();
        int i = 0;

        try {
            PreparedStatement pstm = ShoppingMall.conn.prepareStatement(query);
            res = pstm.executeQuery();

            while (res.next()) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                shoppings.add(Shopping.newInstance(res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        sdf.format(new java.util.Date(res.getString(4).replace("-", "/"))),
                        res.getDouble(5)));
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        IShopping[] sh = new IShopping[i];
        int j = 0;
        while (j < i) {

            sh[j] = shoppings.get(j);
            j++;
        }

        return sh;



    }

    @Override
    public IShopping[] getShoppings(IStore store) {
        String query = "SELECT * FROM shopping  WHERE "
                + "user_ID='" + this.getId()
                + "' AND sName='" + store.getStoreName()
                + "' AND category='" + store.getStoreCategory().toString()
                + "' ORDER BY sDate DESC";

        ResultSet res;

        List<IShopping> shoppings = new ArrayList<IShopping>();
        int i = 0;

        try {
            PreparedStatement pstm = ShoppingMall.conn.prepareStatement(query);
            res = pstm.executeQuery();

            while (res.next()) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                shoppings.add(Shopping.newInstance(res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        sdf.format(new java.util.Date(res.getString(4).replace("-", "/"))),
                        res.getDouble(5)));
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        IShopping[] sh = new IShopping[i];
        int j = 0;
        while (j < i) {

            sh[j] = shoppings.get(j);
            j++;
        }

        return sh;

    }

    @Override
    public IShopping[] getShoppings() {
        String query = "SELECT * FROM shopping  WHERE "
                + "user_ID='" + this.getId()
                + "' ORDER BY sDate DESC";

        ResultSet res;

        List<IShopping> shoppings = new ArrayList<IShopping>();
        int i = 0;

        try {
            PreparedStatement pstm = ShoppingMall.conn.prepareStatement(query);
            res = pstm.executeQuery();

            while (res.next()) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                shoppings.add(Shopping.newInstance(res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        sdf.format(new java.util.Date(res.getString(4).replace("-", "/"))),
                        res.getDouble(5)));
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        IShopping[] sh = new IShopping[i];
        int j = 0;
        if (shoppings.size() == 0) {
            return null;
        }
        while (j < i) {

            sh[j] = shoppings.get(j);
            j++;
        }

        return sh;
    }

    @Override
    public boolean checkPrimeMinisterOffer() {

        String query = "SELECT COUNT(DISTINCT sName) FROM shopping  "
                + " WHERE user_ID ='" + this.getId()
                + "' AND sDate BETWEEN SUBDATE(CURDATE(), INTERVAL 1 MONTH) AND NOW()"
                + " AND sName IN(SELECT s1.sName FROM shopping s1 WHERE s1.amount>=100 AND s1.user_ID='" + this.getId() + "' )";
        boolean b = false;
        ResultSet res;
        try {
            PreparedStatement pstm = ShoppingMall.conn.prepareStatement(query);
            res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {

                i = res.getInt(1);
                System.out.println("---------" + i + "--");
            }
            if (i >= 5) {
                b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public boolean checkGovernorOffer() {

        String query = "SELECT sName,COUNT(DISTINCT sDate) FROM shopping  "
                + " WHERE user_ID ='" + this.getId()
                + "' AND sDate BETWEEN SUBDATE(CURDATE(), INTERVAL 1 MONTH) AND NOW()"
                + " AND sName IN(SELECT s1.sName FROM shopping s1 WHERE s1.amount>=50 AND s1.user_ID='" + this.getId() + "' )"
                + " GROUP BY sName";
        boolean b = false;
        ResultSet res;
        try {

            PreparedStatement pstm = ShoppingMall.conn.prepareStatement(query);
            res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {

                i = res.getInt(2);
                if (i >= 5) {
                    b = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public boolean checkMayorOffer() {

        String query = "SELECT COUNT(DISTINCT sDate) FROM shopping  "
                + " WHERE user_ID ='" + this.getId()
                + "' AND sDate BETWEEN SUBDATE(CURDATE(), INTERVAL 1 MONTH) AND NOW()";
        boolean b = false;
        ResultSet res;
        try {

            PreparedStatement pstm = ShoppingMall.conn.prepareStatement(query);
            res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {

                i = res.getInt(1);
            }
            if (i >= 10) {
                b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
}