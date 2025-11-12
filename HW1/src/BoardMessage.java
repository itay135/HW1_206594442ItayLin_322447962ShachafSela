import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

enum PriorityType{
	REGULAR,
	URGENT
}

public class BoardMessage extends Message
{
	
	private PriorityType priority;
	
	// se'if b 
	public BoardMessage(String sender, String content, Date sendDate, int MBsize, PriorityType priority) 
	{
		super(sender, content, sendDate, MBsize);
		this.priority=priority;
	}
	
	//c
	public BoardMessage(String sender, String content, int MBsize) 
	{
		super(sender,content,MBsize);
		this.priority = PriorityType.REGULAR;
		
	}
	
	//a
	public PriorityType getPriority() 
	{
		return priority;
	}
	
	public void setPriority(PriorityType priority) 
	{
		this.priority=priority;
	}
	
	//d
	public String toString() {
		return super.toString() + "Board Message Priority: "+priority;
	}
	
	//e - Method of our own
	
	public long SentXMinutesAgo() 
	{
	    long currTime = System.currentTimeMillis();
	    long sent = super.getDate().getTime();
	    long difference = currTime - sent;
	    long minutes = difference / (1000 * 60);
	    
	    return minutes;
	}
	
	//f
	
	@Override
	public String generatePreview() {
		if(super.getContent().length() >= 15) 
		{
			return "[Board] "+ super.getSender()+": "
		+super.getContent().substring(0,15) + "...";

		}
		else {
			return "[Board] "+ super.getSender()+": "+super.getContent().substring(0,super.getContent().length());

		}
	}
	
}
