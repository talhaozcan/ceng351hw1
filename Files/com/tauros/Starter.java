package com.tauros;

public class Starter {
	public static IShoppingMall getShoppingMallHandler() {
		IShoppingMall shopping_mall =  ShoppingMall.newInstance();
		
		/******************************************************************
		 * Create new instance of the class that implements IShoppingMall interface
		 * and assign it to shopping_mall variable.
		 ******************************************************************/ 
		 // shopping_mall = (IShoppingMall)new YourShoppingMallClass();
		
		return shopping_mall;
	}
}
