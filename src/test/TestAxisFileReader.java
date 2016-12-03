package test;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.BeforeClass;
import org.junit.Test;

import source.AxisClientThread;
import source.AxisFileReader;

public class TestAxisFileReader{
	private static AxisFileReader axisFileReader;
	private static String outputFilePath;
	private static InputStream inputStream;
	private static InputStream inputStream2;
	
	@BeforeClass
	public static void  initializeVariables() throws FileNotFoundException{
		axisFileReader = new AxisFileReader();
		outputFilePath = "C:/axisfolder/assignment1/axis2.txt";
		inputStream =  new FileInputStream("C:/axisfolder/assignment1/axis.txt");
		inputStream2 =  new FileInputStream("C:/axisfolder/assignment1/caner.jpg");
	}
	
	
	
	@Test
	public void testReadInputStream(){
		InputStream is = new ByteArrayInputStream( "Caner".getBytes() );		
	}
}
