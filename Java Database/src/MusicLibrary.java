import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MusicLibrary {

	
	public static void main(String[]args) throws ClassNotFoundException{
		
	Connection connection = null;	
	Statement smt = null;
	
		try{
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/musiclib.db");
		System.out.println("Opening: " + connection);
		
		}
		catch (Exception e ){
			System.out.println("Problem Encountered");
			System.exit(0);
		}
		System.out.println("Opened Successfully\n");
		
		
		System.out.println("CHOOSE ONE OF THE FOLLOWING OPTIONS:");
		System.out.println("(1)Find artist(s) by genre");
		System.out.println("(2)Find the year a song was released");
		System.out.println("(3)Find the artist based on song name");
		System.out.println("(4)Find the number of songs in specified album");
		System.out.println("(5)Find the length a song based on song name");
		System.out.println("(6)Find the awards won based on artist name");
		System.out.println("(7)Find the album based song name");
		System.out.println("(8)Find the artist based on album name");
		System.out.println("(9)Find the number of albums an artist has released");
		System.out.println("(10)Find the name and age of all members in a group");
		System.out.println("(11)Find the venues an artist has performed at");
		System.out.println("-------------------------------------------------");
		System.out.println("Enter Option #:");
		
		Scanner s = new Scanner(System.in);
		String choice = s.nextLine();
		
		if(choice.equals("1")){
			try{
				System.out.println("Enter the genre you would like to query:");
				choice = s.nextLine();
				smt = connection.createStatement();
				String sql = "SELECT Name FROM Artist WHERE Genre = '" + choice + "';";
				ResultSet rs = smt.executeQuery(sql);
				
				while(rs.next()){
				System.out.println(rs.getString("Name"));
				}
				
				smt.close();
				connection.close();
			}
			catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}
		else if(choice.equals("2")){
			try{
				System.out.println("Enter the song you are looking for:");
				choice = s.nextLine();
				smt = connection.createStatement();
				String sql = "SELECT Year FROM Songs WHERE name = '"+ choice + "';";
				ResultSet rs = smt.executeQuery(sql);
				
				while(rs.next()){
				System.out.println("Year Released: " +rs.getString("Year"));
				}
				
				smt.close();
				connection.close();
			}
			catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}
		else if(choice.equals("3")){
			try{
				System.out.println("Enter the song you are looking for:");
				choice = s.nextLine();
				smt = connection.createStatement();
				String sql = "SELECT name FROM Artist WHERE artistID IN (SELECT artistID FROM Songs WHERE Songs.Name ='"+ choice+"');";
				ResultSet rs = smt.executeQuery(sql);
				
				while(rs.next()){
				System.out.println("Artist: " +rs.getString("name"));
				}
				
				smt.close();
				connection.close();
			}
			catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}
		else if(choice.equals("4")){
			try{
				System.out.println("Enter the album you are looking for:");
				choice = s.nextLine();
				smt = connection.createStatement();
				String sql = "SELECT Count (*) FROM Songs WHERE AlbumID IN (SELECT AlbumID FROM Album WHERE Album.Name = '"+choice+"');";
				ResultSet rs = smt.executeQuery(sql);
				
				rs.next();
				int rowCount = rs.getInt(1);
				System.out.println("Song Count: " +rowCount);
				
				
				smt.close();
				connection.close();
			}
			catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}
		else if(choice.equals("5")){
			try{
				System.out.println("Enter the song you are looking for:");
				choice = s.nextLine();
				smt = connection.createStatement();
				String sql = "SELECT length FROM Songs WHERE name ='"+ choice+ "';";
				ResultSet rs = smt.executeQuery(sql);
				Date time = new SimpleDateFormat("mm:ss").parse(rs.getString("length"));
				System.out.println("Song Length: "+ time); //Time is correct but do not know how to get rid of additional information
				
				//System.out.println("Song Length: "+ rs.getTime("Length")); //Error When Parsing Time
				
				smt.close();
				connection.close();
			}
			catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}
		else if(choice.equals("6")){
			try{
				System.out.println("Enter the artist you are looking for:");
				choice = s.nextLine();
				smt = connection.createStatement();
				String sql = "SELECT Name FROM Awards WHERE artistID IN (SELECT artistID FROM Artist WHERE Artist.Name = '" + choice+"');";
				ResultSet rs = smt.executeQuery(sql);
				
				while(rs.next()){
				System.out.println("Award: " +rs.getString("Name"));
				}
				
				smt.close();
				connection.close();
			}
			catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}
		else if(choice.equals("7")){
			try{
				System.out.println("Enter the song you are looking for:");
				choice = s.nextLine();
				smt = connection.createStatement();
				String sql = "SELECT name FROM  Album WHERE albumID IN (SELECT albumID FROM Songs WHERE Songs.Name = '"+ choice+"');";
				ResultSet rs = smt.executeQuery(sql);
				
				while(rs.next()){
				System.out.println("Album: " +rs.getString("name"));
				}
				
				smt.close();
				connection.close();
			}
			catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}
		else if(choice.equals("8")){
			try{
				System.out.println("Enter the album you are looking for:");
				choice = s.nextLine();
				smt = connection.createStatement();
				String sql = "SELECT Name FROM  Artist WHERE artistID IN (SELECT artistID FROM Album WHERE Album.Name = '"+choice+"');";
				ResultSet rs = smt.executeQuery(sql);
				
				while(rs.next()){
				System.out.println("Artist: " +rs.getString("name"));
				}
				
				smt.close();
				connection.close();
			}
			catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}
		else if(choice.equals("9")){
			try{
				System.out.println("Enter the artist you are looking for:");
				choice = s.nextLine();
				smt = connection.createStatement();
				String sql = "SELECT Count (*) FROM Album WHERE artistID IN (SELECT artistID FROM Artist WHERE Artist.Name = '"+ choice + "');";
				ResultSet rs = smt.executeQuery(sql);
				
				rs.next();
				int rowCount = rs.getInt(1);
				System.out.println("Albums made: " +rowCount);
				
				
				smt.close();
				connection.close();
			}
			catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}
		else if(choice.equals("10")){
			try{
				System.out.println("Enter the artist you are looking for:");
				choice = s.nextLine();
				smt = connection.createStatement();
				String sql = "SELECT name,age FROM Members WHERE artistID IN (SELECT artistID FROM Artist WHERE Artist.name = '"+ choice +"');";
				ResultSet rs = smt.executeQuery(sql);
				
				while(rs.next()){
				System.out.println("Name: " +rs.getString("name"));
				System.out.println("Age: " +rs.getString("age"));
				System.out.println("---------------------------------");
				}
				
				smt.close();
				connection.close();
			}
			catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}
		else if(choice.equals("11")){
			try{
				System.out.println("Enter the artist you are looking for:");
				choice = s.nextLine();
				smt = connection.createStatement();
				String sql = "SELECT Venue FROM Concert join Performed using (ConcertID) join Artist using (artistID) WHERE Artist.Name = '"+choice + "';";
				ResultSet rs = smt.executeQuery(sql);
				
				while(rs.next()){
				System.out.println("Venue: " +rs.getString("Venue"));
				}
				
				smt.close();
				connection.close();
			}
			catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}
		
	}
}
//		
//		try{
//		smt = connection.createStatement();
//		String sql = "CREATE TABLE AAA(PersonID int);";
//		smt.executeUpdate(sql);
//		smt.close();
//		connection.close();
//		}
//		catch(Exception e){
//			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//			System.exit(0);
//		}

	

