import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class File 
{
	private String fileName;
	private String fileType;
	
	public File(String fileName, String fileType) throws IllegalArgumentException
	{
		setName(fileName);
		setType(fileType);
	}
	
	
	public String getName() 
	{
		return fileName;
	}
	public void setName(String fileName) throws IllegalArgumentException
	{
		if(fileName==null || fileName.isEmpty()) 
		{
			throw new IllegalArgumentException("File name cannot be empty!");
		}
		
		this.fileName=fileName;
	
		
	}
	
	public String getType() 
	{
		return fileType;
	}
	public void setType(String fileType) throws IllegalArgumentException
	{
		if(fileType==null || fileType.isEmpty()) 
		{
			throw new IllegalArgumentException("File type cannot be empty!");
		}
		this.fileType=fileType;
	}
	
	@Override
	public String toString() 
	{
		return "File name: " + fileName + " File type: " + fileType;
	}
}
