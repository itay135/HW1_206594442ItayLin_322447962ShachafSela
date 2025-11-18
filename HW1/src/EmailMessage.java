import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class EmailMessage extends Message implements IDigital
{
	private String subject;
	private ArrayList<File> files = new ArrayList<File>();
	public void setSubject(String subject) throws IllegalArgumentException
	{
		if(subject==null || subject.isEmpty()) 
		{
			throw new IllegalArgumentException("Subject cannot be empty!");
		}
		this.subject = subject;
	}
	public String getSubject()
	{
		return subject;
	}
	//b
	public EmailMessage (String subject, String sender, String content, Date sendDate , float MBsize ) throws IllegalArgumentException
	{
		super(sender , content , sendDate , MBsize);
		setSubject(subject);
	//c	
	}
	public EmailMessage (String subject, String sender , String content , float MBsize) throws IllegalArgumentException
	{
		super(sender, content, MBsize);
		setSubject(subject);
	}
	//d
	@Override 
	public String toString()
	{
		return super.toString() + " email subject: " + subject;
	}
	public String printCommunicationMetod()
	{
		return "Sent via Email";
	}
	public String generatePreview()
	{
		return "[Email] Subject: " + subject + " | From: " + sender;
	}
	public void addAttachment(File file)
	{
		files.add(file);
	}
	public void removeAttachment(File file) throws AttachmentException
	{
		boolean remove = files.removeIf(f -> f.equals(file));//checks every file and removes it only if its the same
		if(!remove)
		{
			throw new AttachmentException("File doesn't exist");
		}
	}
	
	
	

}
