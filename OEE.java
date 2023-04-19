package Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class Automobile
{
	String name,fuel;
	int no_of_wheels,range,fuel_type;
	float mileage;
	Automobile()
	{
		name = "Vehicle-Name";
		no_of_wheels = 2;
		range = 50;
	}
	Automobile(String name,float mileage,int fuel_type)
	{
		this.name = name;
		this.mileage = mileage;
		this.fuel_type = fuel_type;
		if(fuel_type == 1)
			fuel = "Petrol";
		else if(fuel_type == 2)
			fuel = "Diesel";
		else if(fuel_type == 3)
			fuel = "CNG";
		else
			fuel = "Invalid";
	}
	float fuel_quantity(float distance,float mileage)
	{
		float q;
		q = distance/mileage;
		return q;
	}
	void display(int type,float dist,int n) throws Invalid_seat
	{
		System.out.println("----Vehicle Details---");
		if(type == 1)
			System.out.println("Vehicle Type: Car");
		else if(type == 2)
			System.out.println("Vehicle Type: Bike");
	}
	float price(int type,float quant)
	{
		float fuel_price;
		if(type == 1)
			fuel_price = 102.630f;
		else if(type == 2)
			fuel_price = 88.42f;
		else if(type == 3)
			fuel_price = 87.50f;
		else
			fuel_price = 0;
		System.out.println(fuel+" Price : "+fuel_price+" Rs. per Litre/Kg");
		float fp = fuel_price*quant;
		System.out.println(fuel+" expense for the Journey: "+fp+" Rs.");
		return fp;
	}
	void insert(Connection con,String type,String make,float dist,float quant,float price,int sl)
	{
		PreparedStatement stmt=null;
		String m,d,q,p;
		try
		{
	     
			stmt=(PreparedStatement)con.prepareStatement("insert into Journey values(?,?,?,?,?,?,?,?,?)");
			
			stmt.setInt(1, sl);
			stmt.setString(2, name);
			stmt.setString(3, type);
			stmt.setString(4, make);
			stmt.setString(5, fuel);
			m = String.valueOf(mileage);
			q = String.valueOf(quant);
			if(fuel=="Petrol" || fuel == "Diesel")
			{
				m += " Km/L";
				q += " L";
				stmt.setString(6, m);
			}
			else {
				m += " Km/Kg";
				q += " Kg";
				stmt.setString(6, m);
			}
			d = String.valueOf(dist);
			d += " KMs";
			stmt.setString(7, d);
			stmt.setString(8, q);
			p = "Rs." + String.valueOf(price);
			stmt.setString(9, p);
			int x=stmt.executeUpdate();
			System.out.println("\nRecord has been inserted to the Table Successfully with return value="+x);
			System.out.println("please check the data base or call read method");
		
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		finally
		{
			try
			{
			stmt.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
		    }
		}
	}
	void delete(Connection con, int n)
	{
		PreparedStatement stmt=null;
		try
		{
			stmt=(PreparedStatement)con.prepareStatement("delete from Journey where SL_No=?");
			stmt.setInt(1,n);
			int x=stmt.executeUpdate();
			if(x!=0)
			{
				System.out.println("\nRecord has been deleted Successfully from the Table with return value="+x);
				System.out.println("Please check the data base or call read method");
			}
			else
				System.out.println("No record found");
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		finally
		{
			try
			{
				stmt.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			 }
		}
		
	}
	void update(Connection con)
	{
		PreparedStatement stmt=null;
		try
		{
			Scanner sc = new Scanner(System.in);
			stmt=(PreparedStatement)con.prepareStatement("update Journey set Name=? where SL_No=?");
			System.out.println("Enter SL_No. : ");
			int id=sc.nextInt();
			System.out.println("Enter new Name: ");
			String new_name = sc.next();
			stmt.setString(1, new_name);
			stmt.setInt(2, id);
			int x=stmt.executeUpdate();
			if(x!=0)
			{
				System.out.println("\nRecord has been updated with return value="+x);
				System.out.println("please check the data base or call read method");
			}
				else
					
					System.out.println("No record found");
			}
		catch(SQLException e)
		{
		}
		finally
		{
			try
			{
			stmt.close();
			}
			catch(Exception e)
			{
				
		} 	}
	}
	
}

class Car extends Automobile
{
	Car(String name,float mileage,int fuel_type)
	{
		super(name,mileage,fuel_type);
		no_of_wheels = 4;
	}
	void display(float quant,float dist,int n) throws Invalid_seat
	{
		System.out.println("---Journey Details---");
		System.out.println("\nDo wear your Seatbelts!!");
		System.out.println("Caution : Please Ensure that your car tires are filled with accurate air pressure!");
		System.out.println("\n\nCar Name: "+name);
		System.out.println("\nNo. of wheels: "+no_of_wheels);
		System.out.println("\nMileage: "+mileage+" Km/L");
		System.out.println("\nFuel Type : "+fuel);
		System.out.println("\nJourney Distance: "+dist+"km.");
		try 
		{
			if(seat_possibility(n))
			{
				System.out.println("\nYou would Require "+quant+"L of " +fuel +" to travel "+dist+"km.");
			}
			else
			{
				throw new Invalid_seat();
			}
		}
		catch(Invalid_seat e)
		{
			System.out.println(e);
		}
	}
	boolean seat_possibility(int n)
	{
		if(n<=9)
			return true;
		return false;
	}
}

class Bike extends Automobile
{
	Bike(String name,float mileage,int fuel_type)
	{
		super(name,mileage,fuel_type);
		no_of_wheels = 2;
	}
	void display(float quant,float dist,int n) throws Invalid_seat
	{
		System.out.println("---Journey Details---");
		System.out.println("\nDo Wear your Helmets for Safety!");
		System.out.println("\n\nBike Name: "+name);
		System.out.println("\nMileage: "+mileage);
		System.out.println("\nJourney Distance: "+dist+"km.");
		try 
		{
			if(seat_possibility(n))
			{
				System.out.println("\nNo.of persons: "+n);
				System.out.println("\nYou would Require "+quant+"L of Fuel to travel "+dist+"km.");
			}
			else
			{
				throw new Invalid_seat();
			}
		}
		catch(Invalid_seat e)
		{
			System.out.println(e);
		}
	}
	boolean seat_possibility(int n)
	{
		if(n<=2)
			return true;
		return false;
	}
	
}
