package Storage;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

//import CustomerShoppingCart;


public class elements  
{
	public static ArrayList<Items> storage = new ArrayList<Items>();
	
	
	public void addItem(Items a)
	{
		
		storage.add(a);
	}
	
	public void remove(Items a)
	{
		storage.remove(a);
	}
	
	public ArrayList<Items> display()
	{
		return storage;
	}
	
	public void setlist(ArrayList<Items> a)
	{
		storage.addAll(a);
	}
	
	
	/*public static void main(String[] args)
	{
		elements a = new elements();
		
		Items b = new Items("lemon", 2.99,100);
		
		a.addItem(b);
		
		System.out.println(storage);
		storage.add(new Items("Milk", 2.13, 100));
		storage.add(new Items("eggs", 4.99, 100));
		storage.add(new Items("water(24)", 3.99,500));
		storage.add(new Items("toast bread(Whole Wheat)", 4.99, 30 ));
		storage.add(new Items("Chicken breast" , 6.99, 20));
		storage.add(new Items("Meat", 4.99 , 60));
		storage.add(new Items("Candy", 7.99, 60));
		
		storage.add(null)
		System.out.println(storage);
	}*/

}
