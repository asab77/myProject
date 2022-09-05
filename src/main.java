import javax.swing.JFrame;
import java.sql.*;


public class main 
{
	public static void main(String[] args) 
	{
		JFrame frame = new login();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//Data base
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/finalproject" , "postgres" , "aria1377");
			System.out.println("Connected to the database.");
		}
		catch( Exception e)
		{
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		/*try
		{
			stmt = c.createStatement();
			String sql = "CREATE TABLE DATABASE "+
			"(User_name CHAR(50) PRIMARY KEY NOT NULL,"+
			"Pass_word CHAR(150) NOT NULL,"+
			"First_name CHAR(50) NOT NULL,"+
			"Last_name CHAR(50) NOT NULL,"+
			"Email CHAR(150) NOT NULL,"+
			"ADDRESS CHAR(150) NOT NULL,"+
			"Position CHAR(50) NOT NULL)";
			
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
*/
	}

}
