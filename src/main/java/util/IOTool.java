package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public  class IOTool {

	public static void main(String[] args) {
		//Tool.saveFile(new Member("xyz", "xyz1234", "1234", "taipei", "09887744553"), "c://abc/abc.txt");
		//System.out.println(Tool.readFile("c://abc/abc.txt"));
	}
	
	//存取物件檔
	public static void saveFile(Object object, String fileName)
	{
		
		try {
			try(FileOutputStream fileOutputStream = new FileOutputStream(fileName))
			{
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(object);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Object readFile(String fileName) 
	{
		Object object = null;
		try(FileInputStream fileInputStream = new FileInputStream(fileName))
		{
			try(ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
			{
				object = objectInputStream.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

}