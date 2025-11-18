import java.awt.Menu;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;


public class Main 
{

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		ArrayList<Message> messages = new ArrayList<Message>();

		try 
		{
			SMSMessage sms1 = new SMSMessage(26, "Itay", "The test will be very easy!", 1.0f);
			SMSMessage sms2= new SMSMessage(25,"Shachaf","Pls send me your home address",3.0f);
			EmailMessage email1 = new EmailMessage("Test" , "Shachaf" , "You have a test on sunday", 0.12f);
			EmailMessage email2= new EmailMessage("Task","Itay","Your task is to find a blue monkey",6.0f);
			BoardMessage bm1= new BoardMessage("Bill","dont come to school tomorrow", 5.0f);
			bm1.setPriority(PriorityType.URGENT);
			BoardMessage bm2 = new BoardMessage("Chris","We have ice-cream today!",2.0f);		
			
			messages.add(sms1);
			messages.add(sms2);
			messages.add(email1);
			messages.add(email2);
			messages.add(bm1);
			messages.add(bm2);
		}
		catch (IllegalAgeException e)
		{
			System.out.println(e.getMessage());
		}

		int choice = 0;
		while (!exit)
		{
			String prompt =  "Please choose an option:\n" + "1.New message\n" + "2.Delete message\n"
		+ "3.Display all messages\n" + "4.Display message count containing words\n"
					+"5.Print Digital messages\n"+ "6.Show largest message";
		
			choice = Menu.readIntInRange(scanner, prompt, 1, 6);
		switch (choice) 
		{
			case 1: 
			{
				try 
				{
					Message newMessage = Menu.createMessage(scanner);
					messages.add(newMessage);
					break;
				} 
				catch (IllegalArgumentException e) 
				{
					System.out.println(e.getMessage());
				}
				catch (InputMismatchException e)
				{
					System.out.println(e.getMessage());
				}
				catch (IllegalAgeException e)
				{
					System.out.println(e.getMessage());
				}
				break;
			}

				
			case 2:
			{
				Menu.deleteMessage(messages,scanner);
				break;
			}
			
			case 3:
			{
				for(int i=0;i<messages.size();i++) {
					System.out.println("Message number "+(i+1)+ messages.get(i).toString());
				}
				break;
			}
			case 4:
			{
				
				Menu.showFind(messages, scanner);
				break;
			}
			case 5:
			{
		
				System.out.println("Digital messages: ");
				for(Message m : messages) 
				{
					if(m instanceof IDigital) 
					{
						
						IDigital digMessage=(IDigital) m;
						System.out.println(digMessage.toString() +digMessage.printCommunicationMetod());
					}
				}
				break;
			}
			case 6:
			{
				float max=0;
				Message largest=messages.getFirst();
				for(Message m:messages) 
				{
					if(m.getSize()>max) 
					{
						max=m.getSize();
						largest=m;
					}
				}
				System.out.println("The largest message weights "+max+" MB and is"+largest.generatePreview());
				break;
			}
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
		}
		
	}
		scanner.close();
		
}
	public static class Menu
	{

		//making sure user's answer is valid.
		public static int readIntInRange(Scanner scanner, String prompt, int min, int max)
		{
			int choice;
			while (true)
			{
				try 
				{
					System.out.println(prompt);
					choice =scanner.nextInt();
					scanner.nextLine(); 
					if (choice >= min && choice <=max)
					{
						break;//valid input
					}
					else
					{
						System.out.println("Invalid input.");
					}
				}
				catch (InputMismatchException e)
				{
					System.out.println(e.getMessage());
					scanner.nextLine();	
				}
			}
			return choice;
		}
		
		public static int messageType(Scanner scanner) throws IllegalArgumentException
		{
			String prompt = "Please choose a message type:\n" 
					+ "1.email\n" + "2.Board message\n" + "3.SMS\n";
			return readIntInRange(scanner, prompt, 1, 3);
		}
		//
		public static Date addDate(Scanner scanner)
		{
			while (true)
			{
				System.out.println("Do you want to add a date? (y\n)");
				String choice = scanner.nextLine().trim().toLowerCase();
				if (choice.equals("y"))
				{
					while(true)
					{
						try
						{
							System.out.println("Please enter a date in dd/mm/yyyy:");
							String input = scanner.nextLine();
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
							Date date = formatter.parse(input);
							return date;
						}
						catch (ParseException e)
						{
							System.out.println(e.getMessage());
						}
					}
				}
				else if (choice.equals("n"))
				{
					return null;
				}
				else 
				{
					System.out.println("Invalid answer. Please try again");
				}
			}
		}
		//making sure we wont get empty strings
		public static String readNonEmptyString(Scanner scanner, String prompt)
		{
			String input;
			while (true)
			{
				System.out.println(prompt);
				input = scanner.nextLine();
				if(input==null || input.trim().isEmpty())
				{
					System.out.println("Input cannot be empty. Please try again."); 
				}
				else 
				{
					break;
				}
			}
			return input;
		}
		//making sure size is in a correct format
		public static float readPositiveFloat(Scanner scanner , String prompt)
		{
			float value;
			while (true)
			{
				try
				{
					System.out.println(prompt);
					value = scanner.nextFloat();
					scanner.nextLine();
					if (value<0)
					{
						System.out.println("value must be positive. Please try again.");
					}
					else
					{
						break;
					}
				}
				catch (InputMismatchException e)
				{
					System.out.println(e.getMessage());
					scanner.nextLine();
				}
				
			}
			return value;
		}
		//creates a Message.
		public static Message createMessage(Scanner scanner) throws IllegalArgumentException , IllegalAgeException
		{
			String sender = readNonEmptyString(scanner, "Please enter sender's name");
			String content = readNonEmptyString(scanner, "Please entrer message's content");
			Date date = addDate(scanner);
			float MBSize = readPositiveFloat(scanner, "Please enter message's size in MB");
			int choice = messageType(scanner);
			switch (choice)
			{
			case 1: //Email
			{
				String subject = readNonEmptyString(scanner, "Please enter email's subject");
				EmailMessage newEmail;
				if(date == null)
				{
					newEmail = new EmailMessage(subject, sender, content, MBSize);
				}
				else 
				{
					newEmail = new EmailMessage(subject, sender, content, date, MBSize);				}
				
				while(true)
				{
					File file = createFile(scanner);
					if (file != null)
					{
						newEmail.addAttachment(file);
						System.out.println("Attachment added successfully");
					}
					else
					{
						break;
					}
				}
				return newEmail;
			}
		
			case 2://Board Message
			{
				if(date ==null)
				{
					System.out.println("No date provided. Priority will be set to regular");
					return new BoardMessage(sender, content, MBSize);
				}
				else 
				{
					PriorityType priorit = readPriorityType(scanner);
					return new BoardMessage(sender, content, date, MBSize, priorit);
				}
			}
			case 3:// SMS
			{
				int age=0;
				while (true)
				{
					try
					{
						System.out.println("Please enter sender's age");
						age = scanner.nextInt();
						if(age<=0)
						{
							throw new IllegalArgumentException();
						}
						break;
					}
					catch (IllegalArgumentException e)
					{
						System.out.println(e.getMessage());
						scanner.nextLine();
					}
					catch (InputMismatchException e)
					{
						System.out.println(e.getMessage());
						scanner.nextLine();
					}
				}
				return new SMSMessage(age , sender, content, MBSize);
			}
			default: throw new IllegalArgumentException();
		}
		}
		public static File createFile(Scanner scanner)
		{		
			String prompt = "Add an attachment? Please choose an option:/n" + "1.Yes/n" + "2.No";
			if(readIntInRange(scanner, prompt, 1, 2)==1)
			{
				String fileName = readNonEmptyString(scanner, "Please enter attachments name");
				String fileType = readNonEmptyString(scanner, "Please enter attachment's type");
				File file = new File(fileName, fileType); 
				return file;
			}
			else return null;
			
		}
		// func for adding a Message to  the arraylist.
		public static PriorityType readPriorityType(Scanner scanner)
		{
			String prompt = "Please choose priority:\n" + "1.REGULAR\n" + "2.URGENT";
			int choice = readIntInRange(scanner, prompt, 1, 2);
			if (choice==2)
			{
				return PriorityType.URGENT;
			}
			else
			{
				return PriorityType.REGULAR;
			}
		}
		
		public static void deleteMessage(ArrayList<Message> messages,Scanner scanner) {
			if(messages.isEmpty()) {
				System.out.println("There are no messages to delete!");
			}
			System.out.println("You have these messages: ");
			for(int i=0;i<messages.size();i++) {
				System.out.println((i+1)+") " + messages.get(i).generatePreview());
			}
			int num = readIntInRange(scanner,"Please choose message number to delete: ",1,messages.size());
			messages.remove((num-1));
		}
		
		public static void showFind(ArrayList<Message> messages,Scanner scanner) {
			ArrayList<String> words = new ArrayList<String>();
			String word;
			System.out.println("Enter words to search from, press '0' to end: ");
			
			while(true) 
			{
				word=scanner.nextLine();
				if(word.equals("0")) 
				{
					System.out.println("Thanks!");
					break;
				}
				if(word==null || word.trim().isEmpty())
				{
					System.out.println("Input cannot be empty. Please try again."); 
				}
				else {
					words.add(word);
					
				}
			}
			int count=0;
			for(int j=0;j<messages.size();j++) 
			{
				if(messages.get(j).find(words)) 
				{
					count++;
				}
			}
			System.out.println("You have "+count+ " messages with those words");
		}
		
	}
}
