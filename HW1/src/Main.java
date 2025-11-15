import java.awt.Menu;
import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.midi.VoiceStatus;
public class Main 
{

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		ArrayList<Message> messages = new ArrayList<Message>();
		EmailMessage email1 = new EmailMessage("Test" , "Shachaf" , "You have a test on sunday", 0.12f);
		try 
		{
			SMSMessage sms1 = new SMSMessage(26, "Itay", "The test will be very easy!", 0.13f);
			messages.add(sms1);
		}
		catch (IllegalAgeException e)
		{
			System.out.println(e.getMessage());
		}
		messages.add(email1);
		int choice = 0;
		while (!exit)
		{
			Menu.showMenu();
			choice = scanner.nextInt();
			switch (choice) 
			{
			case 1: 
			{
				try 
				{
					
				} 
				catch (Exception e) 
				{
					// TODO: handle exception
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
			
		}
		
	}
	public static class Menu
	{
		public static void showMenu()
		{
			System.out.println("Please choose an option:");
			System.out.println("1.New message.");
			System.out.println("2.Delete message");
			System.out.println("3.Display all messages");
			System.out.println("4.Display message count containing words");
		}
		public static int messageType(Scanner scanner) throws IllegalArgumentException
		{
			
			System.out.println("Please choose message type:");
			System.out.println("1.Email");
			System.out.println("2.Board message");
			System.out.println("3.SMS");
			int choice = scanner.nextInt();
			return choice;
			
		}
		
		public static void addMessage(Scanner scanner) throws IllegalArgumentException
		{
			System.out.println("Please enter sender's name: ");
			String sender = scanner.nextLine(); 
			System.out.println("Please enter message's content: ");
			String content = scanner.nextLine();
			// we need to convert to Date format and ask user if he wants to add a date
			//System.out.println("Please enter date in format dd/mm/yyyy: ");
			//Date sendDate = (Date)scanner.nextLine(); 
			//we need to make sure that the input is a float
			System.out.println("Please enter messege's size in MB: ");
			float MBsize = scanner.nextFloat();
			scanner.nextLine();
			int choice =Menu.messageType(scanner);	
			switch (choice) {
			case 1: //email
			{
				
				try 
				{
					System.out.println("Please enter the subject: ");
					String subject = scanner.nextLine();
					
					
				}
				catch (Exception e) {
					
				}
			}
			case 2://BoardMessage
			{
				
			}
			case 3://SMSMessage
			{
				
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
			
		}
		
	}

}
