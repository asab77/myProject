

import javax.swing.*;

import Storage.Items;
import Storage.elements;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

public class CustomerShoppingCart extends JFrame 
{
	private static final int FRAME_WIDTH = 762 ,FRAME_HEIGHT = 603;
	
	private JButton addToCart,LogOut, CheckOut;
	private JLabel searchLabel;
	private JTextField quantity,searchField;
	private JList<Items> list1;
	private JList<Items>list2;
	private JScrollPane scrollPane;
	static String text;
	int quant;
	private ArrayList<Integer> Quantity = new ArrayList<Integer>();
	private ArrayList<Double> price = new ArrayList<Double>();
	private ArrayList<Items> cart = new ArrayList<Items>();
	DefaultListModel defaultListModel=new DefaultListModel();
	DefaultListModel cartModel=new DefaultListModel();
	
	elements ab = new elements();
	
	public CustomerShoppingCart()
	{
		createComponents();
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setVisible(true);
	}
	

	
	  private void searchTxtKeyReleased(KeyEvent e) 
	  {                                      
	        searchFilter(searchField.getText());
	   }               
	
	 
	 public class AddListener implements ActionListener 
	 {
		    public void actionPerformed(ActionEvent e) 
		    {
		    	if( e.getSource() == addToCart)
		    	{
		    	    text = quantity.getText();
					
					 if( text.equals(""))
				    	{
				    		 JOptionPane.showMessageDialog(null, "Please enter quantity");
				    		   //System.exit(0);
				    	}
					
					
					
					if(text != null)
					{
						quant = Integer.parseInt(text);
				    	
				    	Quantity.add(quant);
				    	
				    	Items add = new Items("",0,0);
				    	add = list1.getSelectedValue();
				        cart.add(add);
				        
				        
				        
				        double EachItemPrice = list1.getSelectedValue().getPrice() * quant;
				        price.add(EachItemPrice);
				        list2.setModel(cartModel);
						cartModel.addElement(add);
					}
 
		    	}
		    	
		    	else if(e.getSource() == CheckOut)
		    	{
		    		double sum = 0;
		    		for( int i = 0 ; i < price.size() ; i++)
		    			sum += price.get(i);
		    		JOptionPane.showMessageDialog(null, "Your total amount is " + sum);
		    	}
		    	
		    	else if(e.getSource() == LogOut)
		    	{
		    		/*if(e.getSource() != CheckOut)
		    		{
		    			
		    			Connection c = null;
						Statement stmt = null;
						
						
		    			try
		    			{
		    				Class.forName("org.postgresql.Driver");
							c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/finalproject" , "postgres" , "aria1377");
							String Query = "INSERT INTO shoppingcart value ('" + user_name + "','" + item_name + "','" + item_price + "','" + item_quantity +  "')";
							c.setAutoCommit(false);
						    stmt = c.createStatement();
		    			}
		    			catch(Exception e1)
		    			{
		    				e1.printStackTrace();
							System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
		    			}
		    		}*/
		    		
		    		dispose();
		    	}
		    }
	  }
	 

	 
	 public Items[] MakeArray(ArrayList<Items> storage4)
	 {
		 Items[] it = new Items[storage4.size()];
		 for(int i= 0 ; i < storage4.size() ; i++)
			 it[i] = storage4.get(i);
		 return it;
	 }
	
	private void createComponents()
	{
		employeeProfile a = new employeeProfile(ab);
		
		JPanel panel = new JPanel();
		scrollPane = new JScrollPane();
		searchField = new JTextField();
		searchLabel = new JLabel();
		list1 = new JList(MakeArray(item()));
		list2 = new JList(MakeArray(cart));
		quantity = new JTextField();
		addToCart = new JButton("Add To Cart");
		CheckOut = new JButton("Check out");
		LogOut = new JButton("LogOut");
		
		list1.setFont(new Font("Arial",Font.PLAIN,10));
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list1.setBounds(28, 61, 284, 468);
		
		list2.setFont(new Font("Arial",Font.PLAIN,10));
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.setBounds(527, 32, 187, 475);
		
		
		searchField.setBounds(28, 30, 284, 20);
		searchField.addKeyListener(new KeyAdapter() {
	            public void keyReleased(KeyEvent evt) {
	                searchTxtKeyReleased(evt);
	            }
	        });
		
		scrollPane.setViewportView(list1);
		
		quantity.setBounds(371, 214, 96, 20);
		
		
		addToCart.setBounds(360, 140, 111, 23);
		ActionListener listener3 = new AddListener();
		addToCart.addActionListener(listener3);
		
		CheckOut.setBounds(371, 388, 89, 23);
		ActionListener listener4 = new AddListener();
		CheckOut.addActionListener(listener4);
		
		LogOut.setBounds(371, 452, 89, 23);
		LogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if( e.getSource() == LogOut)
				{
					dispose();
				}
			}
		});
		
		panel.add(list1);
		panel.add(list2);
		panel.add(searchField);
		panel.add(addToCart);
		panel.add(quantity);
		panel.add(CheckOut);
		panel.add(LogOut);
		panel.setLayout(null);
		add(panel);
	}
	
	private ArrayList<Items> item()
	{
		elements h = new elements();
	    return h.display();
	}
	
	private void bindData()
	{
		item().stream().forEach((storage) -> { defaultListModel.addElement(storage);});
		list1.setModel(defaultListModel);
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	//Search/Filter data
	private void searchFilter(String searchTerm)
	{
		DefaultListModel filteredItems=new DefaultListModel();
		
		ArrayList<Items> stars = item();
		
		stars.stream().forEach((star) -> { String starName = star.toString().toLowerCase();
		if( starName.contains(searchTerm.toLowerCase()))
			filteredItems.addElement(star);
		});
		
		defaultListModel = filteredItems;
		list1.setModel(defaultListModel);
	}
	
}
