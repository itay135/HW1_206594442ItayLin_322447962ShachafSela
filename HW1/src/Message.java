import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public abstract class Message 
{
	private String sender;
	private String content;
	private Date sendDate;
	private int MBsize;
	
	public Message(String sender, String content, Date sendDate, int MBsize) 
	{
		setSender(sender);
		setContent(content);
		this.sendDate=sendDate;
		setSize(MBsize);
	}
	public Message(String sender, String content, int MBsize) 
	{
		setSender(sender);
		setContent(content);
		this.sendDate=new Date();
		setSize(MBsize);
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) throws IllegalArgumentException {
		if(sender==null || sender.isEmpty()) 
		{
			throw new IllegalArgumentException("Sender name cannot be empty!");
		}
		this.sender=sender;
	}
	
	public String getContent() {
		return content;

	}
	
	public void setContent(String content) throws IllegalArgumentException {
		if(content==null || content.isEmpty()) {
			throw new IllegalArgumentException("Content cannot be empty");
		}
	}
	
	public Date getDate() {
		return sendDate;
	}
	
	public int getSize() {
		return MBsize;
	}
	public void setSize(int MBsize) {
		if(MBsize<0) {
			throw new IllegalArgumentException("Size cant be negative");
		}
		this.MBsize=MBsize;
	}
	
	@Override
	public String toString() 
	{
		return "Message sender: " + sender + " Content: " + content 
				+ " Send Date: "+ sendDate + "Message size in MB: "+MBsize+"MB";
	}
	
	public boolean find(ArrayList<String> words) 
	{
		
		for(String word : words) 
		{
			if (content.contains(word))
			{
				return true;
			}
		}
		return false;
	}
	
	public int SizeInGB() {
		return MBsize/1000;
	}
	
	public abstract String generatePreview();
		
}
