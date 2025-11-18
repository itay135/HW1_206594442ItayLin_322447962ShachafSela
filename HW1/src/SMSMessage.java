import java.util.Date;

public class SMSMessage extends Message implements IDigital
{
	private static final int maxSize = 160;
	private int age;
	
	public void setAge(int age) throws IllegalAgeException
	{
		if (age < 8)
		{
			throw new IllegalAgeException(" Sender's age must be at least 8 years");
		}
		this.age = age;
	}
	public int getAge()
	{
		return age;
	}
	public SMSMessage (int age, String sender, String content, Date sendDate , float MBsize ) throws IllegalArgumentException, IllegalAgeException
	{
		super(sender , content , sendDate , MBsize);
		setAge(age);

	}
	public SMSMessage (int age , String sender , String content , float MBsize) throws IllegalArgumentException , IllegalAgeException
	{
		super(sender, content, MBsize);
		setAge(age);
	}
	@Override
	public void setContent(String content) throws IllegalArgumentException
	{
		if(content.length()>maxSize && content!=null)
		{
			throw new IllegalArgumentException("SMS content exceeds the maximum length of " + maxSize + " characters.");
		}
		super.setContent(content);
	}
	public String printCommunicationMetod()
	{
		return "sent via SMS";
	}
	public String toString()
	{
		return super.toString() + "Sender's age: " + age;
	}
	@Override
	public String generatePreview()
	{
		
		return "[SMS] content: " + content + "from: " + sender;
	}
	
	
	

}
