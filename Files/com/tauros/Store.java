package com.tauros;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ahmedtalha
 */
public class Store implements IStore {

    private String storeName;
    private Category category;

    public static IStore newInstance() {
        Store dt = new Store();

        return (IStore) dt;
    }

    public static IStore newInstance(String storeName, Category category) {
        Store dt = new Store(storeName, category);

        return (IStore) dt;
    }

    public Store() {
    }

    public Store(String storeName, Category category) {
        this.storeName = storeName;
        this.category = category;
    }

    @Override
    public String getStoreName() {
        return this.storeName;
    }

    @Override
    public Category getStoreCategory() {
        return this.category;
    }

    @Override
    public void setStoreName(String newName) throws StoreAlreadyExists {


        String query = "SELECT * FROM stores WHERE name ='" + newName + "' AND category='" + this.getStoreCategory().toString()
                + "'";
        ResultSet res;

        try {

            PreparedStatement stm = ShoppingMall.conn.prepareStatement(query);
            res = stm.executeQuery();

            if (res.next()) {
                throw new StoreAlreadyExists();
            } else {
                String query2 = "UPDATE stores SET name='" + newName + "' WHERE name ='" + this.getStoreName() + "'";
                Statement stm2 = ShoppingMall.conn.createStatement();
                stm2.executeUpdate(query2);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    @Override
    public void setStoreCategory(Category newCategory) throws StoreAlreadyExists {

        String query = "SELECT * FROM stores WHERE name ='" + this.getStoreName() + "' AND category='" + category.toString()
                + "'";
        ResultSet res;

        try {

            PreparedStatement stm = ShoppingMall.conn.prepareStatement(query);
            res = stm.executeQuery();

            if (res.next()) {
                throw new StoreAlreadyExists();
            } else {
                String query2 = "UPDATE stores SET category='" + category + "' WHERE name='" + this.getStoreName() + "'AND category='" + this.getStoreCategory().toString() + "'";
                Statement stm2 = ShoppingMall.conn.createStatement();
                stm2.executeUpdate(query2);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public IShopping[] getShoppings(String startDate, String endDate) {

        String query = "SELECT * FROM shopping  WHERE "
                + " sName='" + this.getStoreName()
                + "' AND category='" + this.getStoreCategory().toString()
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
    public IShopping[] getShoopings(String date) {

        String query = "SELECT * FROM shopping S  WHERE "
                + "S.sName='" + this.getStoreName()
                + "' AND S.category='" + this.getStoreCategory().toString()
                + "' AND S.sDate=STR_TO_DATE('" + date.replace(".", "/") + "','%d/%m/%Y') "
                + "ORDER BY S.sDate DESC";

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
    public IShopping[] getShoppings() {

        String query = "SELECT * FROM shopping  WHERE "
                + "sName='" + this.getStoreName()
                + "' AND category='" + this.getStoreCategory().toString()
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
}
