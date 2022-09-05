import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class login extends JFrame
{
	//Private Frame Value
	private static final int FRAME_WIDTH = 900 , FRAME_HEIGHT = 500;
	
	//private variables 
	private JButton button1,button2,button3;
	private JPasswordField pass;
	private JTextField userText,passText;
	private JLabel signin,username,password,signup;
	
	public login()
	{
		createComponent();
		setSize(FRAME_WIDTH,FRAME_HEIGHT);	
	}
	
	// login button action listener
	public class enter implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == button1)
			{
				Connection c = null;
				Statement stmt = null;
				
				String Username = userText.getText();
				String Password = passText.getText();
				
				String empty="";
				userText.setText(empty);
				passText.setText(empty);
				
				
				try
				{
					Class.forName("org.postgresql.Driver");
					c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/finalproject" , "postgres" , "aria1377");	
					stmt = c.createStatement();
					
					//Postgres query to filter the user name and password 
					String Query =  " select * from database where user_name = '" + Username + "' and pass_word = '" + Password + "'"; 
					
					ResultSet rs = stmt.executeQuery(Query);
					
					
					if(rs.next())
					{
						//If the user name and password is true then log in to the shopping cart page 
						//dispose(); // close login page
					
						
						String position = rs.getString("position");
						
						if( position.equals("costumer"))
						{
							CustomerShoppingCart customerCart = new CustomerShoppingCart();
							customerCart.setVisible(true);
						}
						else 
						{
							employeeProfile employeeCart = new employeeProfile();
							employeeCart.setVisible(true);
						}
					}
					else 
					{
						// If the user name or password is wrong show a massage
						JOptionPane.showMessageDialog(button1, "The User name Or Password is Wrong");
						userText.setText("");
						passText.setText("");
						
					}
					
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					System.exit(0);
				}
			}	
		}
	}
	
	// Customer account creation button action Listener
	public class CustomerAccountCreation implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == button2)
			{
				Caccount customerAccount = new Caccount();
				customerAccount.setVisible(true);
			}
		}
	}
	
	// Employee / manager account creation action Listener
	public class EmployeeAccountCreation implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == button3)
			{
				Eaccount EmployeeAccount = new Eaccount();
				EmployeeAccount.setVisible(true);
			}
		}
	}
	
	private void createComponent()
	{
		// login
		button1 = new JButton("Login");
		button1.setSize(100,30);
		button1.setLocation(120, 250);
		ActionListener listener1 = new enter();
		button1.addActionListener(listener1);
		
		//Account creation for customer
		button2 = new JButton("Customer Account Creation");
		button2.setFont(new Font("Arial",Font.PLAIN,10));
		button2.setSize(200, 50);
		button2.setLocation(550, 120);
		ActionListener listener2 = new CustomerAccountCreation();
		button2.addActionListener(listener2);
		
		//Account creation for employee / manager
		button3 = new JButton("Employee / manager Account Creation");
		button3.setFont(new Font("Arial",Font.PLAIN,10));
		button3.setSize(250,50);
		button3.setLocation(550, 220);
		ActionListener listener3 = new EmployeeAccountCreation();
		button3.addActionListener(listener3);
		
		//labels
		//Sign in label
		signin = new JLabel("Sign in");
		signin.setFont(new Font("Arial",Font.PLAIN,30));
		signin.setSize(150, 100);
		signin.setLocation(120,20);
		
		//User name Label
		username = new JLabel("Username");
		username.setFont(new Font("Arial",Font.PLAIN,20));
		username.setSize(100,100);
		username.setLocation(120, 60);
		
		//Password Label
		password = new JLabel("Password");
		password.setFont(new Font("Arial",Font.PLAIN,20));
		password.setSize(100, 100);
		password.setLocation(120,140);
		
		//sign Up
		//sign up label
		signup = new JLabel("Sign up");
		signup.setFont(new Font("Arial",Font.PLAIN,30));
		signup.setSize(150, 100);
		signup.setLocation(600, 20);
		
		// Text field
		// user name text field
		userText = new JTextField();
		userText.setSize(200,30);
		userText.setLocation(120, 125);
		
		//password text field
		passText = new JTextField();
		passText.setSize(200, 30);
		passText.setLocation(120, 210);
		
		//JPanel
		JPanel panel = new JPanel();
		
		panel.add(signin);
		panel.add(username);
		panel.add(userText);
		panel.add(password);
		panel.add(passText);
		panel.add(button1);
		panel.add(signup);
		panel.add(button2);
		panel.add(button3);
		
		panel.setLayout(null);
		add(panel);
	}
	
	public static void main(String[] args) 
	{
		JFrame frame = new login();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
