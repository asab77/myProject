package Storage;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;

//import CustomerShoppingCart;

// This class creates an object of the Items in the grocery store
public class Items implements Serializable
{
	private String name;
	private double price;
	private int quantity;
	
	//private ArrayList<Items> storage = new ArrayList<Items>();
	
	public Items(String itemName, double itemPrice, int itemQuantity)
	{
		name = itemName;
		price = itemPrice;
		quantity = itemQuantity;
	}

	public String getName()
	{
		return name;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	
	/*public void Remove(int i)
	{
		storage.remove(i);
	}*/
	
	public String toString()
	{
		return String.format("%s [%s] [%s]", getName(),getPrice(),getQuantity());
	}
	
	/*public void additem(String itemName, double itemPrice, int itemQuantity)
	{
		storage.add(new Items(itemName, itemPrice,itemQuantity));
	}*/
	
	
	
	
	
	

}
