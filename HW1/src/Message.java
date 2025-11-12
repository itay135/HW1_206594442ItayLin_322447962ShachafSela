import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public abstract class Message 
{
	protected String sender;
	protected String content;
	protected Date sendDate;
	protected float MBsize;
	
	public Message(String sender, String content, Date sendDate, float MBsize) 
	{
		setSender(sender);
		setContent(content);
		this.sendDate=sendDate;
		setSize(MBsize);
	}
	public Message(String sender, String content, float MBsize) 
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
		if(sender==null || sender.trim().isEmpty()) 
		{
			throw new IllegalArgumentException("Sender name cannot be empty!");
		}
		this.sender=sender;
	}
	
	public String getContent() {
		return content;

	}
	
	public void setContent(String content) throws IllegalArgumentException {
		if(content==null || content.trim().isEmpty()) {
			throw new IllegalArgumentException("Content cannot be empty");
		}
		this.content=content;
	}
	
	public Date getDate() {
		return sendDate;
	}
	
	
	public float getSize() {
		return MBsize;
	}
	public void setSize(float MBsize) {
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
	
	// f - method of our own
	public float SizeInGB() {
		return MBsize/1000;
	}
	
	public abstract String generatePreview();
		
}
