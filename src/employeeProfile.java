import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.*;

import Storage.Items;
import Storage.elements;

public class employeeProfile extends JFrame
{
	private static final int FRAME_WIDTH = 762 ,FRAME_HEIGHT = 603;
	private JButton add,update,delete,logOut;
	private JTextField search,itemName,itemPrice,itemquantity;
	private JList<Items> list;
	private JLabel ItemName, ItemPrice,ItemQuantity,SearchLabel,ItemLabel;
	DefaultListModel defaultListModel = new DefaultListModel(); 
	
	static elements jj = new elements();
	
	public employeeProfile(elements a)
	{	
		a =jj ;
	}
	
	public elements display()
	{
		return jj;
	}
	public employeeProfile()
	{
		createComponents();
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setVisible(true);
	}
	
	 private void searchTxtKeyReleased(KeyEvent e) 
	  {                                      
	        searchFilter(search.getText());
	   }        
	
	public Items[] MakeArray(ArrayList<Items> storage4)
	 {
		 Items[] it = new Items[storage4.size()];
		 for(int i= 0 ; i < storage4.size() ; i++)
			 it[i] = storage4.get(i);
		 return it;
	 }
	
	public class action implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			
			if(e.getSource() == add)
			{
				if(itemName != null && itemPrice != null && itemquantity != null)
				{
					String name = itemName.getText();
					
					String TxtPrice = itemPrice.getText();
					double price = Double.parseDouble(TxtPrice);
					
					String TxtQuantity = itemquantity.getText();
					int quantity = Integer.parseInt(TxtQuantity);
					
					Items obj = new Items(name, price, quantity);
					
					jj.addItem(obj);
					String empty ="";
					
					itemName.setText(empty);
					itemPrice.setText(empty);
					itemquantity.setText(empty);
					
					list.setModel(defaultListModel);
					defaultListModel.addElement(obj);
				}		
			}
			
			else if(e.getSource() == delete)
			{				
				int index = list.getSelectedIndex();
				
				jj.remove(list.getSelectedValue());
				
				
				defaultListModel.removeElementAt(index);
				list.setModel(defaultListModel);
				
				//System.out.println(jj.display());
			}
			
			else if(e.getSource() == update)
			{
				
				String name = itemName.getName();
				
				String TxtPrice = itemPrice.getText();
				double price = Double.parseDouble(TxtPrice);
					
				String TxtQuantity = itemquantity.getText();
				int quantity = Integer.parseInt(TxtQuantity);
				
				int index = list.getSelectedIndex();
				
				jj.remove(list.getSelectedValue());
				defaultListModel.removeElementAt(index);
						
				Items s = new Items(name,price,quantity);	
				jj.addItem(s);
						
				list.setModel(defaultListModel);
				defaultListModel.addElement(s);
					
				//System.out.println(jj.display());
			}
			
			else if(e.getSource() == logOut)
			{
				dispose();
			}
		}	
	}
	
	private void createComponents()
	{
		add = new JButton("Add Item");
		add.setBounds(441, 150, 89, 23);
		ActionListener listener1 = new action();
		add.addActionListener(listener1);
		
		update = new JButton("Update Item");
		update.setBounds(441, 229, 89, 23);
		ActionListener listener2 = new action();
		update.addActionListener(listener2);
		
		
		delete = new JButton("Delete");
		delete.setBounds(441, 323, 89, 23);
		ActionListener listener3 = new action();
		delete.addActionListener(listener3);
		
		logOut = new JButton("Log Out");
		logOut.setBounds(516, 466, 89, 23);
		ActionListener listener4 = new action();
		logOut.addActionListener(listener4);
		
		list = new JList(MakeArray(item()));
		list.setFont(new Font("Arial",Font.PLAIN,10));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(26, 46, 284, 468);
		jj.display().stream().forEach((storage) -> { defaultListModel.addElement(storage);});
		
		search = new JTextField();
		search.setBounds(440, 51, 193, 20);
		search.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                searchTxtKeyReleased(evt);
            }
        });
		
		itemName = new JTextField();
		itemName.setBounds(593, 151, 96, 20);
		
		
		itemPrice = new JTextField();
		itemPrice.setBounds(593, 230, 96, 20);
		
		
		itemquantity = new JTextField();
		itemquantity.setBounds(593, 324, 96, 20);
		
		ItemName = new JLabel("Item Name");
		ItemName.setBounds(593, 136, 96, 14);
		
		ItemPrice = new JLabel("Item Price");
		ItemPrice.setBounds(593, 215, 96, 14);
		
		ItemQuantity = new JLabel("Item Quantity");
		ItemQuantity.setBounds(593, 311, 96, 14);
		
		ItemLabel = new JLabel("Items");
		ItemLabel.setBounds(127, 24, 81, 18);
		
		SearchLabel = new JLabel("Search");
		SearchLabel.setBounds(441, 39, 73, 14);
		
		JPanel panel = new JPanel();
		
		panel.add(add);
		panel.add(delete);
		panel.add(update);
		panel.add(logOut);
		panel.add(list);
		panel.add(search);
		panel.add(itemName);
		panel.add(itemPrice);
		panel.add(itemquantity);
		panel.add(ItemName);
		panel.add(ItemPrice);
		panel.add(ItemQuantity);
		panel.add(SearchLabel);
		panel.add(ItemLabel);
		
		panel.setLayout(null);
		add(panel);
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new employeeProfile();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private ArrayList<Items> item()
	{
		//Items h = new Items();
		elements h = new elements();
		return h.display();
	}
	private void bindData()
	{
		item().stream().forEach((storage) -> { defaultListModel.addElement(storage);});
		list.setModel(defaultListModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		list.setModel(defaultListModel);
	}
	
	
	

}
