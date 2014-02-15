package com.tauros;

import java.sql.*;
import com.tauros.*;
import java.util.*;

public class Ceng351hw1 {
	public static void drop(String[] a)
	{

		int size=a.length;
		for(int i=0;i<size;i++){
		   String query1="DROP TABLE IF EXISTS "+a[i];

		   try
		   {
		   Statement s= ShoppingMall.conn.createStatement();
		   s.executeUpdate(query1);
		   
		   }
		   catch(SQLException e){e.printStackTrace();}
		}
		

		   	System.out.println("Tablolar başarıyla silindi!");
	}

	public static void main(String[] args){
		  Scanner scanner = new Scanner( System.in );
	      System.out.println("Bu program database'indeki bütün tabloları silip sonra işlemleri başlatacak.");
	   System.out.println("Table isimlerini arada bi space bırakarak gir panpa:");
	      String input = scanner.nextLine();
	      String[] parts=input.split(" ");
		IShoppingMall x= Starter.getShoppingMallHandler();
		x.onStart();
		//drop(parts);
		try{x.createDatabase();} catch(DatabaseAlreadyCreated e){e.printStackTrace();}
	int count=0;
	System.out.println("onStart() çalıştırıldı...");
	System.out.println("Şimdi userları ekleyelim...");
		try{
			x.registerUser("cemal","10","104/2");
			x.registerUser("ece","9","KIPRIS");
			x.registerUser("name1","3","A3");
			x.registerUser("name2","4","A4");
			x.registerUser("name3","5","A5");
			x.registerUser("ogul","8","104/5");
			x.registerUser("oguz","7","104/1");
			x.registerUser("panpa1","6","A6");
			x.registerUser("panpa2","1","A1");
			x.registerUser("panpa3","2","A2");
			x.registerUser("silinecek","11","asdasd");
			}
		catch(UserAlreadyExists e){System.out.println(e.message);}
		System.out.println("sonra alfabetik sıraya göre dizelim");
		
		IUser[] users=x.searchUser("");
		boolean b;
		b=users[0].getId().equals("10") && users[0].getFullName().equals("cemal") &&users[0].getAddress().equals("104/2");
		System.out.println(users[0].getId()+"-"+users[0].getFullName()+"-"+users[0].getAddress()+"-"+b);
		
		
		b=users[1].getId().equals("9") && users[1].getFullName().equals("ece") &&users[1].getAddress().equals("KIPRIS");
		System.out.println(users[1].getId()+"-"+users[1].getFullName()+"-"+users[1].getAddress()+"-"+b);
		if(b) count++;
		b=users[2].getId().equals("3") && users[2].getFullName().equals("name1") &&users[2].getAddress().equals("A3");
		System.out.println(users[2].getId()+"-"+users[2].getFullName()+"-"+users[2].getAddress()+"-"+b);
		if(b) count++;
		b=users[3].getId().equals("4") && users[3].getFullName().equals("name2") &&users[3].getAddress().equals("A4");
		System.out.println(users[3].getId()+"-"+users[3].getFullName()+"-"+users[3].getAddress()+"-"+b);
		if(b) count++;
		b=users[4].getId().equals("5") && users[4].getFullName().equals("name3") &&users[4].getAddress().equals("A5");
		System.out.println(users[4].getId()+"-"+users[4].getFullName()+"-"+users[4].getAddress()+"-"+b);
		if(b) count++;
		b=users[5].getId().equals("8") && users[5].getFullName().equals("ogul") &&users[5].getAddress().equals("104/5");
		System.out.println(users[5].getId()+"-"+users[5].getFullName()+"-"+users[5].getAddress()+"-"+b);
		if(b) count++;
		b=users[6].getId().equals("7") && users[6].getFullName().equals("oguz") &&users[6].getAddress().equals("104/1");
		System.out.println(users[6].getId()+"-"+users[6].getFullName()+"-"+users[6].getAddress()+"-"+b);
		if(b) count++;
		b=users[7].getId().equals("6") && users[7].getFullName().equals("panpa1") &&users[7].getAddress().equals("A6");
		System.out.println(users[7].getId()+"-"+users[7].getFullName()+"-"+users[7].getAddress()+"-"+b);
		if(b) count++;
		b=users[8].getId().equals("1") && users[8].getFullName().equals("panpa2") &&users[8].getAddress().equals("A1");
		System.out.println(users[8].getId()+"-"+users[8].getFullName()+"-"+users[8].getAddress()+"-"+b);
		if(b) count++;
		b=users[9].getId().equals("2") && users[9].getFullName().equals("panpa3") &&users[9].getAddress().equals("A2");
		System.out.println(users[9].getId()+"-"+users[9].getFullName()+"-"+users[9].getAddress()+"-"+b);
		if(b) count++;
		b=users[10].getId().equals("11") && users[10].getFullName().equals("silinecek") &&users[10].getAddress().equals("asdasd");
		System.out.println(users[10].getId()+"-"+users[10].getFullName()+"-"+users[10].getAddress()+"-"+b);
		if(b) count++;
		IUser p1= x.getUser("6");
		IUser p2=x.getUser("1");
		IUser p3= x.getUser("2");
		IUser n1=x.getUser("3");
		IUser n2= x.getUser("4");
		IUser n3=x.getUser("5");
		IUser oguz= x.getUser("7");
		IUser ogul=x.getUser("8");
		IUser ece= x.getUser("9");
		IUser cemal=x.getUser("10");
		IUser sil= x.getUser("11");
		System.out.println("**************************************************");
		System.out.println("id si 11 olan userı silelim...");
		try{
			x.unregisterUser(sil);
		}

		catch(UserNotExist e){System.out.println("aooo bunu silmen lazımdı :(");b=false;}
		
		if(b) System.out.println("Başarıyla silindi :)");
		if(b) count++;
		System.out.println("şimdi bi daha silelim...");
		try{
			b=false;
			x.unregisterUser(sil);
		}

		catch(UserNotExist e){System.out.println("Bi kere sildik yeter! aferin doğru gidiyorsun!");b=true;}
		if(b) count++;
	 if(!b) System.out.println("Bu satırı görüyorsan unregisterUser'da exception atamıyon panpa");
	 System.out.println("**************************************************");
	 System.out.println("ece'yi tekrar eklemeye çalışalım...");
	 try{
			b=false;
			x.registerUser("ece","9","KIPRIS");
		}

		catch(UserAlreadyExists e){System.out.println("Bu hayatta ece bi tane, baska eklemeyelim. Doğru yoldasın devam!");b=true;}
		
		if(b) count++;
		if(!b) System.out.println("Bu satırı görüyorsan registerUser'da exception atamıyon panpa");
		
		System.out.println("**************************************************");	
	IUser[] a=	x.searchUser("ce");
	String q0=a[0].getFullName();
	String q1=a[1].getFullName();
	System.out.println("isminde 'ce' olanlar:");
	System.out.println(q0+"-->"+q0.equals("cemal"));
	System.out.println(q1+"-->"+q1.equals("ece"));
		
		
	
		try{
		x.addStore("Decathlon", Category.WEAR);
		x.addStore("Mavi", Category.WEAR);
		x.addStore("Tekin Acar", Category.COSMETIC);
		x.addStore("Ayakkabi dunyasi", Category.SHOE);
		x.addStore("Kaplanlar", Category.COSMETIC);
		x.addStore("Flooo", Category.SHOE);
		x.addStore("IKEA", Category.FURNITURE);
		x.addStore("DepeHome", Category.FURNITURE);
		x.addStore("YILDIRIM Abi", Category.FOOD);
		x.addStore("Altan Pizza", Category.FOOD);
		x.addStore("zzzzzzzzzzz", Category.FOOD);
		
		}
		catch(StoreAlreadyExists e){System.out.println("Ekleyemedin panpa bi sorun var");}
		
	
		IStore[] stores=x.searchStore("");
		IStore Altan=stores[0];
		IStore AyakkabıDünyası=stores[1];
		IStore Decatlon=stores[2];
		IStore Depe=stores[3];
		IStore Flo=stores[4];
		IStore ikea=stores[5];
		IStore kaplanlar=stores[6];
		IStore mavi=stores[7];
		IStore YILDIRIM=stores[9];
		IStore tekin=stores[8];
		IStore temp=stores[10];
		System.out.println("**************************************************");
		b=stores[0].getStoreName().equals("Altan Pizza") && (stores[0].getStoreCategory()+"").equals("FOOD");
		System.out.println(stores[0].getStoreName()+"-"+stores[0].getStoreCategory()+"-"+b);
		if(b) count++;
		b=stores[1].getStoreName().equals("Ayakkabi dunyasi") && (stores[1].getStoreCategory()+"").equals("SHOE");
		System.out.println(stores[1].getStoreName()+"-"+stores[1].getStoreCategory()+"-"+b);
		if(b) count++;
		b=stores[2].getStoreName().equals("Decathlon") && (stores[2].getStoreCategory()+"").equals("WEAR");
		System.out.println(stores[2].getStoreName()+"-"+stores[2].getStoreCategory()+"-"+b);
		if(b) count++;
		b=stores[3].getStoreName().equals("DepeHome") && (stores[3].getStoreCategory()+"").equals("FURNITURE");
		System.out.println(stores[3].getStoreName()+"-"+stores[3].getStoreCategory()+"-"+b);
		if(b) count++;
		b=stores[4].getStoreName().equals("Flooo") && (stores[4].getStoreCategory()+"").equals("SHOE");
		System.out.println(stores[4].getStoreName()+"-"+stores[4].getStoreCategory()+"-"+b);
		if(b) count++;
		b=stores[5].getStoreName().equals("IKEA") && (stores[5].getStoreCategory()+"").equals("FURNITURE");
		System.out.println(stores[5].getStoreName()+"-"+stores[5].getStoreCategory()+"-"+b);
		if(b) count++;
		b=stores[6].getStoreName().equals("Kaplanlar") && (stores[6].getStoreCategory()+"").equals("COSMETIC");
		System.out.println(stores[6].getStoreName()+"-"+stores[6].getStoreCategory()+"-"+b);
		if(b) count++;
		b=stores[7].getStoreName().equals("Mavi") && (stores[7].getStoreCategory()+"").equals("WEAR");
		System.out.println(stores[7].getStoreName()+"-"+stores[7].getStoreCategory()+"-"+b);
		if(b) System.out.println("****************************************************");
		   
		   
	
		b=stores[9].getStoreName().equals("YILDIRIM Abi") && (stores[9].getStoreCategory()+"").equals("FOOD");
		System.out.println(stores[9].getStoreName()+"-"+stores[9].getStoreCategory()+"-"+b);
		if(b) count++;
		b=stores[8].getStoreName().equals("Tekin Acar") && (stores[8].getStoreCategory()+"").equals("COSMETIC");
		System.out.println(stores[8].getStoreName()+"-"+stores[8].getStoreCategory()+"-"+b);
		if(b) count++;
		b=stores[10].getStoreName().equals("zzzzzzzzzzz") && (stores[10].getStoreCategory()+"").equals("FOOD");
		System.out.println(stores[10].getStoreName()+"-"+stores[10].getStoreCategory()+"-"+b);
		System.out.println("**************************************************");
		
		try{
         	x.removeStore(temp);	
		}
		catch(StoreNotExist e){System.out.println("aooo bunu silmen lazımdı");b=false;}
		if(b) count++;
		if(b) System.out.println("Başarıyla silindi :)");
		
        try{
		b=false;
		x.removeStore(temp);
		}
		catch(StoreNotExist e){System.out.println("Bi kere sildik yeter! Aferin doğru gidiyorsun");b=true;}
        if(b) count++;
	    if(!b) System.out.println("Bu satırı görüyorsan removeStore'da exception atamıyon panpa");
		System.out.println("*************************************************");
	    try{
			b=false;
			x.addStore("Tekin Acar", Category.COSMETIC);
			}
			catch(StoreAlreadyExists e){System.out.println("Aynından var! Doğru gidiyorsun");b=true;}
	        if(b) count++;
		    if(!b) System.out.println("Bu satırı görüyorsan addStore'da exception atamıyon panpa");
			
		    System.out.println("Az bekle queryler yoğun bu ara...");
		    
		    
		    
		    p1.shopped(Flo,"05.01.2013", 300);
		    p1.shopped(tekin,"06.02.2012", 530);
		    p1.shopped(ikea,"07.05.2012", 3300);
		    p1.shopped(Altan,"18.06.2013", 30);
		    p1.shopped(Decatlon,"24.10.2012",156);
		    p1.shopped(mavi,"16.11.2013", 80);
		    
		    p2.shopped(kaplanlar,"05.08.2013",187);
		    p2.shopped(mavi,"25.06.2013",51);
		    p2.shopped(AyakkabıDünyası,"25.06.2013", 300);
		    p2.shopped(tekin,"25.06.2013", 268);
		    p2.shopped(tekin,"25.06.2013", 268);
		    
		    
		    p3.shopped(mavi,"05.11.2012", 180);
		    p3.shopped(mavi,"05.12.2012", 180);
		    p3.shopped(mavi,"05.01.2013", 180);
		    p3.shopped(mavi,"05.11.2013", 180);
		    p3.shopped(ikea,"05.11.2013", 180);
		    p3.shopped(mavi,"04.11.2013", 180);
		    p3.shopped(mavi,"04.11.2013", 180);
		    p3.shopped(mavi,"28.10.2013", 180);
		    p3.shopped(mavi,"29.10.2013", 180);
		    p3.shopped(Altan,"05.11.2013", 180);
		    p3.shopped(AyakkabıDünyası,"05.11.2013", 180);
		    p3.shopped(Decatlon,"05.11.2013", 180);
		    
		    n1.shopped(Altan,"05.11.2013", 300);
		    n1.shopped(AyakkabıDünyası,"05.11.2013", 300);
		    n1.shopped(Decatlon,"05.11.2013", 300);
		
		    n2.shopped(mavi,"05.11.2013", 300);
		    n2.shopped(mavi,"05.11.2013", 300);
		    n2.shopped(mavi,"05.11.2013", 300);
		    n2.shopped(mavi,"05.11.2013", 300);
		 
		    ogul.shopped(Altan,"05.11.2013", 18);
		    ogul.shopped(AyakkabıDünyası,"05.11.2013", 180);
		    ogul.shopped(Decatlon,"05.11.2013", 180);
		    ogul.shopped(Depe,"05.11.2013", 1180);
		    ogul.shopped(Flo,"05.11.2013", 180);
		    ogul.shopped(ikea,"05.11.2013", 1180);
		    ogul.shopped(kaplanlar,"05.11.2013", 180);
		    ogul.shopped(mavi,"05.11.2013", 180);
		    ogul.shopped(YILDIRIM,"05.11.2013", 7.75);
		    ogul.shopped(tekin,"05.11.2013", 580);
		    
		    cemal.shopped(Altan,"05.11.2013",15);
		    cemal.shopped(Flo,"05.11.2013", 180);
		    cemal.shopped(ikea,"05.11.2013", 1180);
		    cemal.shopped(kaplanlar,"05.11.2013", 180);
		    cemal.shopped(kaplanlar,"16.10.2013", 180);
		    cemal.shopped(kaplanlar,"17.10.2013", 180);
		    cemal.shopped(kaplanlar,"22.10.2013", 180);
		    cemal.shopped(kaplanlar,"21.10.2013", 180);
		    cemal.shopped(kaplanlar,"25.10.2013", 180);
		    
		    ece.shopped(mavi, "02.11.2013",150);
		    ece.shopped(Altan,"05.11.2013", 18);
		    ece.shopped(AyakkabıDünyası,"05.11.2013", 180);
		    ece.shopped(Decatlon,"05.11.2013", 180);
		    ece.shopped(Depe,"05.11.2013", 1180);
		    ece.shopped(Flo,"25.10.2013", 180);
		    ece.shopped(ikea,"26.10.2013", 1180);
		    ece.shopped(kaplanlar,"27.10.2013", 180);
		    ece.shopped(mavi,"28.10.2013", 180);
		    ece.shopped(YILDIRIM,"29.10.2013", 7.75);
		    ece.shopped(tekin,"30.10.2013", 580);
		    ece.shopped(mavi,"02.11.2012", 180);
		    ece.shopped(mavi,"02.12.2012", 180);
		    ece.shopped(mavi,"03.01.2013", 180);
		    ece.shopped(mavi,"04.11.2013", 180);
		    ece.shopped(ikea,"05.11.2013", 180);
		   // ece.shopped(temp, "05.12.2005", 150);
		    
		    oguz.shopped(mavi, "02.11.2013",150);
		    oguz.shopped(Altan,"05.11.2013", 18);
		    oguz.shopped(AyakkabıDünyası,"05.11.2013", 180);
		    oguz.shopped(Decatlon,"05.11.2013", 180);
		    oguz.shopped(Depe,"08.11.2013", 1180);
		    oguz.shopped(Flo,"25.10.2013", 180);
		    oguz.shopped(ikea,"26.10.2013", 1180);
		    oguz.shopped(kaplanlar,"27.10.2013", 180);
		    oguz.shopped(mavi,"28.10.2013", 180);
		    oguz.shopped(YILDIRIM,"29.10.2013", 7.75);
		    oguz.shopped(mavi,"30.10.2013", 580);
		    oguz.shopped(mavi,"02.11.2012", 180);
		    oguz.shopped(mavi,"02.12.2012", 180);
		    oguz.shopped(mavi,"03.01.2013", 180);
		    oguz.shopped(mavi,"04.11.2013", 180);
		    oguz.shopped(ikea,"05.11.2013", 180);
		    
		    System.out.println("**************************************************");
		    System.out.println("Ece'nin yaptığı tüm alışverişler");
		    IShopping[] eceShops=ece.getShoppings();
		    b=eceShops[0].getStore().getStoreName().equals("IKEA");if(b) count++;
		    System.out.println(eceShops[0].getStore().getStoreName()+"-->"+eceShops[0].getDate()+"-"+b);
		    b=eceShops[1].getStore().getStoreName().equals("Altan Pizza");if(b)count++;
		    System.out.println(eceShops[1].getStore().getStoreName()+"-->"+eceShops[1].getDate()+"-"+b);
		    b=eceShops[2].getStore().getStoreName().equals("Ayakkabi dunyasi");if(b)count++;
		    System.out.println(eceShops[2].getStore().getStoreName()+"-->"+eceShops[2].getDate()+"-"+b);
		    b=eceShops[3].getStore().getStoreName().equals("Decathlon");if(b)count++;
		    System.out.println(eceShops[3].getStore().getStoreName()+"-->"+eceShops[3].getDate()+"-"+b);
		    b=eceShops[4].getStore().getStoreName().equals("DepeHome");if(b)count++;
		    System.out.println(eceShops[4].getStore().getStoreName()+"-->"+eceShops[4].getDate()+"-"+b);
		    b=eceShops[5].getStore().getStoreName().equals("Mavi");if(b)count++;
		    System.out.println(eceShops[5].getStore().getStoreName()+"-->"+eceShops[5].getDate()+"-"+b);
		    b=eceShops[6].getStore().getStoreName().equals("Mavi");if(b)count++;
		    System.out.println(eceShops[6].getStore().getStoreName()+"-->"+eceShops[6].getDate()+"-"+b);
		    b=eceShops[7].getStore().getStoreName().equals("Tekin Acar");if(b)count++;
		    System.out.println(eceShops[7].getStore().getStoreName()+"-->"+eceShops[7].getDate()+"-"+b);
		    b=eceShops[8].getStore().getStoreName().equals("YILDIRIM Abi");if(b)count++;
		    System.out.println(eceShops[8].getStore().getStoreName()+"-->"+eceShops[8].getDate()+"-"+b);
		    b=eceShops[9].getStore().getStoreName().equals("Mavi");if(b)count++;
		    System.out.println(eceShops[9].getStore().getStoreName()+"-->"+eceShops[9].getDate()+"-"+b);
		    b=eceShops[10].getStore().getStoreName().equals("Kaplanlar");if(b)count++;
		    System.out.println(eceShops[10].getStore().getStoreName()+"-->"+eceShops[10].getDate()+"-"+b);
		    b=eceShops[11].getStore().getStoreName().equals("IKEA");if(b)count++;
		    System.out.println(eceShops[11].getStore().getStoreName()+"-->"+eceShops[11].getDate()+"-"+b);
		    b=eceShops[12].getStore().getStoreName().equals("Flooo");if(b)count++;
		    System.out.println(eceShops[12].getStore().getStoreName()+"-->"+eceShops[12].getDate()+"-"+b);
		    b=eceShops[13].getStore().getStoreName().equals("Mavi");if(b)count++;
		    System.out.println(eceShops[13].getStore().getStoreName()+"-->"+eceShops[13].getDate()+"-"+b);
		    b=eceShops[14].getStore().getStoreName().equals("Mavi");if(b)count++;
		    System.out.println(eceShops[14].getStore().getStoreName()+"-->"+eceShops[14].getDate()+"-"+b);
		    b=eceShops[15].getStore().getStoreName().equals("Mavi");if(b)count++;
		    System.out.println(eceShops[15].getStore().getStoreName()+"-->"+eceShops[15].getDate()+"-"+b);
		    
		    
		    System.out.println("**************************************************");
		    
		    System.out.println("Ece'nin 01.11.2013-04.11.2013 arasında Maviden yaptığı  alışverişler");
		    
		    eceShops= ece.getShoppings(mavi, "01.11.2013", "04.11.2013");
		    b=eceShops[0].getStore().getStoreName().equals("Mavi") && (eceShops[0].getDate().equals("01.11.2013") || eceShops[0].getDate().equals("02.11.2013")||eceShops[0].getDate().equals("03.11.2013")||eceShops[0].getDate().equals("04.11.2013"));
		    System.out.println(eceShops[0].getStore().getStoreName()+"-->"+eceShops[0].getDate()+"-"+b);
		    if(b) count++;
		    b=eceShops[0].getStore().getStoreName().equals("Mavi") && (eceShops[1].getDate().equals("01.11.2013") || eceShops[1].getDate().equals("02.11.2013")||eceShops[1].getDate().equals("03.11.2013")||eceShops[1].getDate().equals("04.11.2013"));
		    System.out.println(eceShops[1].getStore().getStoreName()+"-->"+eceShops[1].getDate()+"-"+b);
		    if(b) count++;
		    System.out.println("************************************");
		    System.out.println("name3'ün yaptığı alışverişler");
		    IShopping[] n3shop=n3.getShoppings();
		    if(n3shop == null) {System.out.println("Hiç yok! Doğru!");count++;}
		    else {System.out.println("Null dönmesi lazım panpa :(");}
		
		    
		    System.out.println("************************************");
		    System.out.println("Tekin Acar'dan yapılan alışverişler");
		    IShopping[] s=tekin.getShoppings("30.10.2013", "05.11.2013");
		    b=s[0].getUser().getFullName().equals("ogul") && s[0].getStore().getStoreName().equals("Tekin Acar");
		    System.out.println(s[0].getStore().getStoreName()+"-->"+s[0].getDate()+"-"+b);
		    if(b) count++;
		    b=s[1].getUser().getFullName().equals("ece") && s[1].getStore().getStoreName().equals("Tekin Acar");
		    System.out.println(s[1].getStore().getStoreName()+"-->"+s[1].getDate()+"-"+b);
		    if(b) count++;
		    System.out.println("Tekin Acar'dan 30.10.2013'de yapılan alışverişler");
		    s=tekin.getShoopings("30.10.2013");
		    b=s[0].getDate().equals("30.10.2013");
		    System.out.println(s[0].getUser().getFullName()+"-->"+s[0].getDate()+"-"+s[0].getAmount()+"-"+b);
		    if(b) count++;
		    
		    try{
		    	b=true;
				x.addStore("YILDIRIM Abi", Category.COSMETIC);
				}
				catch(StoreAlreadyExists e){System.out.println("ekleyemedin panpa bi sorun var");b=false;}
		   if(b) {System.out.println("YILDIRIM Abi --> COSMETIC  başarıyla eklendi");count++;}
		    
		   System.out.println("Şimdi de 'YILDIRIM Abi --> COSMETIC' i    'YILDIRIM Abi --> FOOD' yapalım... ");
		    
		   stores=x.searchStore("");
		   IStore y=stores[10];
		   try
		   {	b=true;
			   y.setStoreCategory(Category.FOOD);
		   }
		   catch(StoreAlreadyExists e){b=false;System.out.println("YILDIRIM Abi --> FOOD zaten vardı o yüzden değiştiremedik. Doğru!");}
		   if(b) System.out.println("setSoreCategory 'StoreAlreadyExists' fırlatmadı... :(");
		   else count++;
		   System.out.println("****************************************************");
		   
		   stores=x.searchStore("");
		   y=stores[9];
		   try
		   {	b=true;
			   y.setStoreName("Tekin Acar");
		   }
		   catch(StoreAlreadyExists e){b=false;System.out.println("Tekin Acar --> COSMETIC zaten vardı o yüzden değiştiremedik. Doğru!");}
		   if(b) System.out.println("setStoreName 'StoreAlreadyExists' fırlatmadı... :(");
		   else count++;
		   
		    System.out.println("****************************************************");
		    System.out.println("Prime Minister Offer'ı olanlar");
		    String[] names=new String[4];
		    String[] all={"panpa3","ogul","ece","oguz"};
		    for(int i=1,j=0;i<11;i++)
		    {
		    	IUser t=x.getUser(""+i);
		    	if(t.checkPrimeMinisterOffer()) 
		    	{
		    	System.out.println(t.getFullName());
		    	names[j++]=t.getFullName();
		    	}
		    }
		    if(Arrays.asList(names).containsAll(Arrays.asList(all)) && Arrays.asList(all).containsAll(Arrays.asList(names))) 
		    		{System.out.println("Yeap! Hepsi doğru!");count++;}
		    
		    else System.out.println("checkPrimeMinisterOffer'da yanlışlık var panpa :(");
		    
		    System.out.println("****************************************************");
		    System.out.println("Governer Offer'ı olanlar");
		    names=new String[1];
		    all=new String[1];
		    all[0]="cemal";
		    for(int i=1,j=0;i<11;i++)
		    {
		    	IUser t=x.getUser(""+i);
		    	if(t.checkGovernorOffer()) 
		    	{
		    	System.out.println(t.getFullName());
		    	names[j++]=t.getFullName();
		    	}
		    }
		    if(Arrays.asList(names).containsAll(Arrays.asList(all)) && Arrays.asList(all).containsAll(Arrays.asList(names))) 
		    		{System.out.println("Yeap! Hepsi doğru!");count++;}
		    
		    else System.out.println("checkGovernorOffer'da yanlışlık var panpa :(");
		    System.out.println("****************************************************");
		    System.out.println("Mayor Offer'ı olanlar");
		    names=new String[1];
		    all=new String[1];
		    all[0]="oguz";
		    for(int i=1,j=0;i<11;i++)
		    {
		    	IUser t=x.getUser(""+i);
		    	if(t.checkMayorOffer()) 
		    	{
		    	System.out.println(t.getFullName());
		    	names[j++]=t.getFullName();
		    	}
		    }
		    if(Arrays.asList(names).containsAll(Arrays.asList(all)) && Arrays.asList(all).containsAll(Arrays.asList(names))) 
		    		{System.out.println("Yeap! Hepsi doğru!");count++;}
		    
		    else System.out.println("checkMayorOffer'da yanlışlık var panpa :(");
		    System.out.println("****************************************************");
			   
			   
			   System.out.println("Genel Değerlendirme:");
			    if(count==53) System.out.println("Bu  kod yüz alır panpa!");
			    else System.out.println("Hata Sayısı="+" "+(53-count));
			
		    System.out.println("****************************************************");
			
		System.out.println("Bi anda bi anda da bitiveedi!");
		System.out.println("End of Main");
}
}
