package Project;
//Import Statements
import java.io.*;
import static java.lang.System.exit;
import java.sql.*;

class Invalid_seat extends Exception
{
	public String toString()
	{
		return "Error! Number of persons exceeds available number of seats in the vehicle";
	}
}

public class Main {

	public static void main(String[] args) throws IOException,Invalid_seat
	{
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		try
		{
		//Database Connectivity
		String url = "jdbc:mysql://localhost: 3306/Database-name";
		String uname = "your_username";
		String password = "your_password";
		String query ="select * from Journey";
		int serial,choice;
		try
		{
			System.out.println("JDBC Connection Info : ");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("\nJDBC Driver Loaded Successfully!");
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		con = DriverManager. getConnection (url, uname, password);
		statement = con.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		System.out.println("Connection Established Successfully!");
		
		String name;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Automobile a = new Automobile();
		System.out.println("\n\n---Welcome to your Journey Assistant----");
		while(true)
		{
			result = statement.executeQuery(query);
			System.out.println("\n---------User Menu---------");
			System.out.println("\n1-->Insert Data\n2-->Delete Data\n3-->Update Name\n4-->View Database\n5-->Exit\n");
			System.out.println("Please Enter your Choice: ");
			choice = Integer.parseInt(br.readLine());
			switch(choice)
			{
				case 1 : System.out.println("Enter the type of vehicle you are travelling: \n1.Car\n2.Bike\n");
						int type = Integer.parseInt(br.readLine());
						System.out.print("Enter Name of the vehicle: ");
						name = br.readLine();
						System.out.println("Enter the Manufacturer Name : ");
						String make = br.readLine();
						System.out.print("Enter the distance you want to travel(in Km): ");
						float dist = Float.parseFloat(br.readLine());
						System.out.println("Enter the Fuel type of the vehicle: \n1.Petrol\n2.Diesel\n3.CNG");
						int fuel_type = Integer.parseInt(br.readLine());
						System.out.println("Enter the average mileage of your vehicle: ");
						float mileage = Float.parseFloat(br.readLine());
						float quant = a.fuel_quantity(dist,mileage);
						System.out.println("Enter no.of persons travelling: ");
						int n = Integer.parseInt(br.readLine());
						result.last();
						serial = result.getRow() + 1;
						if(type == 1)
						{
							Car c = new Car(name,mileage,fuel_type);
							c.display(quant, dist, n);
							float p = c.price(fuel_type,quant);
							if(c.name!="" && make!="" && dist!=0)
								if(fuel_type == 1 ||fuel_type == 2 || fuel_type==3)
								{
									c.insert(con, "Car", make, dist, quant, p,serial);
										//result.first();
								}
								else
									System.out.println("Data Not Inserted");
							else
								System.out.println("Please Fill the correct Details!\n Data Not Inserted!!");
							break;
						}
						else if(type == 2)
						{
							Bike b = new Bike(name,mileage,fuel_type);
							b.display(quant, dist, n);
							float p = b.price(fuel_type, quant);
							if(b.name!="" && make!="" && dist!=0)
								if(fuel_type == 1 ||fuel_type == 2)
								{
									b.insert(con, "Bike", make, dist, quant, p, serial);
									//result.first();
								}
								else
									System.out.println("Data Not Inserted");
							else
								System.out.println("Please Fill the correct Details!\n Data Not Inserted!!");
							break;
						}
						else {
							System.out.println("Invalid Choice!");
							break;
						}
				case 2 : System.out.println("Delete Data from Database ");
						 System.out.println("Deleting the Last Record in the Database!");
						 result.last();
				         int no = result.getRow();
				         a.delete(con, no);
				         break;
				         
				case 3 : System.out.println("Updating The Value of the Data in the Database");
				         a.update(con);
				         break;
				         
				case 4 : System.out.println("---------The Journey Table----------");
						 result.first();
				 		 do
						 {
							
							System.out.println("____________________________________________________");
							System.out.println("SL_No : "+result.getInt(1));
							System.out.println("Name : "+result.getString(2));
							System.out.println("Type : "+result.getString(3));
							System.out.println("Manufacturer : "+result.getString(4));
							System.out.println("Fuel Type : "+result.getString(5));
							System.out.println("Mileage : "+result.getString(6));
							System.out.println("Distance : "+result.getString(7));
							System.out.println("Fuel Required : "+result.getString(8));
							System.out.println("Price : "+result.getString(9));
							System.out.println("____________________________________________________");
						}while(result.next());
				 		 System.out.println("Data Displayed From the Database Successfully!");
				 		break;
				case 5 : System.out.println("Thank You!");
				         exit(0);
				         break;
				default : System.out.println("Invalid Choice");
			}
		}
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		finally
		{
			try {
				con.close();
			}
			catch(Exception e)
			{
				System.err.println(e);
			}
		}
	}

}
