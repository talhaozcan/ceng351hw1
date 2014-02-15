package com.tauros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ahmedtalha
 */
public class Shopping implements IShopping {

    private String userID;
    private String storeName;
    private String sCategory;
    private String sdate;
    private double amount;

    public Shopping() {
    }

    public Shopping(String userID, String storeName, String sCategory, String sdate, double amount) {
        this.userID = userID;
        this.storeName = storeName;
        this.sdate = sdate;
        this.sCategory = sCategory;
        this.sdate = sdate;
        this.amount = amount;
    }

    public static IShopping newInstance() {
        Shopping dt = new Shopping();

        return (IShopping) dt;
    }

    public static IShopping newInstance(String userID, String storeName, String sCategory, String sdate, double amount) {
        sdate = sdate.replace("/", ".");
        Shopping dt = new Shopping(userID, storeName, sCategory, sdate, amount);

        return (IShopping) dt;
    }

    public String getUserID() {
        return this.userID;
    }

    @Override
    public IUser getUser() {

        String query = "SELECT * FROM users WHERE userID='" + this.getUserID() + "'";
        ResultSet res;
        IUser user = null;

        try {

            PreparedStatement pstm = ShoppingMall.conn.prepareStatement(query);
            res = pstm.executeQuery();

            while (res.next()) {

                user = User.newInstance(res.getString(1),
                        res.getString(2),
                        res.getString(3));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public IStore getStore() {
        IStore store = Store.newInstance(this.storeName, Category.valueOf(this.sCategory));
        return store;
    }

    @Override
    public String getDate() {
        return this.sdate;
    }

    @Override
    public double getAmount() {
        return this.amount;
    }
}
