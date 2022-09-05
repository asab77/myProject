import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Eaccount  extends JFrame
{
    private static final int FRAME_WIDTH = 700 , FRAME_HEIGHT = 900;
	
	// private variables
	private JButton CreateButton,ResetButton;
	private JLabel Title , Username,Password,FirstName,
				   LastName,Email,Address,ID;
	private JTextField usertext,passtext,Nametext,Lasttext,
					Emailtext,Addtext,AddID;
	private JComboBox position;
	private JCheckBox term;
	
	public Eaccount()
	{
		createComponents();
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setVisible(true);	
	}
	
	public class button implements ActionListener
	{

		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == CreateButton)
			{
				if(term.isSelected())
				{
					Connection c = null;
					Statement stmt = null;
					
					String Username = usertext.getText();
					String Password = passtext.getText();
					String FirstName = Nametext.getText();
					String LastName = Lasttext.getText();
					String Email = Emailtext.getText();
					String Address = Addtext.getText(); 
					String ID = AddID.getText();
					String position1 = (String) position.getSelectedItem();
					
					// Connecting and inserting data to database
					try
					{
						Class.forName("org.postgresql.Driver");
						c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/finalproject" , "postgres" , "aria1377");
						String Query = "INSERT INTO database values ('"+ Username + "','" + Password + "','" + FirstName +"','" + LastName + "','" + 
					    		Email + "','" + Address + "','" + position1 + "')";
						c.setAutoCommit(false);
					    stmt = c.createStatement();
					    // system.out.print(" number of the customers that made an account : " , numberOfCostumers);
					    int x= stmt.executeUpdate(Query);
					    if(x == 0)
					    {
					    	JOptionPane.showMessageDialog(CreateButton, "exists");
					    }
					    else JOptionPane.showMessageDialog(CreateButton, "Your Account Has Been Created");
					    
					    //stm.close();
					    c.commit();
					    c.close();
					}
					
					catch( Exception e1)
					{
						e1.printStackTrace();
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						System.exit(0);
					}
				}
				
				else
				{
					JOptionPane.showMessageDialog(CreateButton, "Please Accept the Terms and Conditions");
				}
				
			}
			else if ( e.getSource() == ResetButton)
			{
				String empty="";
				usertext.setText(empty);
				passtext.setText(empty);
				Nametext.setText(empty);
				Lasttext.setText(empty);
				Emailtext.setText(empty);
				Addtext.setText(empty);
				term.setSelected(false);
			}
			
		}
		
	}
	
	//Item listener
	public void itemStateChanged(ItemEvent e)
	{
		if(e.getSource() == position)
		{
			
		}
	}
	
	public void createComponents()
	{
		// labels
				//Title label
				Title = new JLabel("Create your Account");
				Title.setFont(new Font("Arial",Font.PLAIN,30));
				Title.setSize(400,100);
				Title.setLocation(50,20);
				
				//user name label
				Username = new JLabel("Username");
				Username.setFont(new Font("Arial",Font.PLAIN,20));
				Username.setSize(100, 100);
				Username.setLocation(50, 60);
				
				//password label
				Password = new JLabel("Password");
				Password.setFont(new Font("Arial",Font.PLAIN,20));
				Password.setSize(100,100);
				Password.setLocation(50, 140);
				
				//Id label
				ID = new JLabel("ID Number");
				ID.setFont(new Font("Arial",Font.PLAIN,20));
				ID.setSize(100, 100);
				ID.setLocation(400,60);
				
				//First Name Label
				FirstName = new JLabel("First Name");
				FirstName.setFont(new Font("Arial",Font.PLAIN,20));
				FirstName.setSize(100,100);
				FirstName.setLocation(50, 220);
				
				//Last Name Label
				LastName = new JLabel("Last Name");
				LastName.setFont(new Font("Arial", Font.PLAIN,20));
				LastName.setSize(100,100);
				LastName.setLocation(50, 300);
				
				//Email Label
				Email = new JLabel("Email Address");
				Email.setFont(new Font("Arial" , Font.PLAIN,20));
				Email.setSize(150,100);
				Email.setLocation(50, 380);
				
				//Address Label
				Address = new JLabel("Address");
				Address.setFont(new Font("Arial" , Font.PLAIN,20));
				Address.setSize(150,100);
				Address.setLocation(50,460);
			
				//Text Field
				//user name
				usertext = new JTextField();
				usertext.setSize(200,30);
				usertext.setLocation(50, 120);
				
				//ID text field
				AddID = new JTextField();
				AddID.setSize(200, 30);
				AddID.setLocation(400, 120);
				
				//password text field
				passtext = new JTextField();
				passtext.setSize(200,30);
				passtext.setLocation(50, 200);
				
				//First Name text field
				Nametext = new JTextField();
				Nametext.setSize(200, 30);
				Nametext.setLocation(50,280);
				
				//Last Name text field
				Lasttext = new JTextField();
				Lasttext.setSize(200,30);
				Lasttext.setLocation(50, 360);
				
				//Email text field
				Emailtext = new JTextField();
				Emailtext.setSize(220,30);
				Emailtext.setLocation(50, 440);
				
				//Address text Field
				Addtext = new JTextField();
				Addtext.setFont(new Font("Arial", Font.PLAIN,15));
				Addtext.setSize(220,100);
				Addtext.setLocation(50,530);
				
				//Check box
				term = new JCheckBox("Accept Terms And Condition.");
				term.setFont(new Font("Arial", Font.PLAIN,15));
				term.setSize(250,100);
				term.setLocation(50, 700);
				
				//Combo Box
				String[]pos = {"Manager", "Employee"};
				position = new JComboBox(pos);
				position.addItemListener(null);
				position.setSize(150,30);
				position.setLocation(70,650);
				
				//Buttons
				// Create button
				CreateButton = new JButton("Create");
				CreateButton.setFont(new Font("Arial", Font.PLAIN,15));
				CreateButton.setSize(100,30);
				CreateButton.setLocation(50, 800);
				ActionListener listener1 = new button();
				CreateButton.addActionListener(listener1);
				
				//Reset Button
				ResetButton = new JButton("Reset");
				ResetButton.setFont(new Font("Arial", Font.PLAIN,15));
				ResetButton.setSize(100,30);
				ResetButton.setLocation(200,800);
				ActionListener listener2 = new button();
				ResetButton.addActionListener(listener2);
				// panel
				JPanel panel = new JPanel();
				
				panel.add(Title);
				panel.add(Username);
				panel.add(usertext);
				panel.add(Password);
				panel.add(passtext);
				panel.add(FirstName);
				panel.add(Nametext);
				panel.add(LastName);
				panel.add(Lasttext);
				panel.add(Email);
				panel.add(Emailtext);
				panel.add(Address);
				panel.add(Addtext);
				panel.add(position);
				panel.add(term);
				panel.add(CreateButton);
				panel.add(ResetButton);
				panel.add(ID);
				panel.add(AddID);
				panel.setLayout(null);
				add(panel);
			}

}
