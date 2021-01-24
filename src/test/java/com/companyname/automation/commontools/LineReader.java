package com.companyname.automation.commontools;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LineReader {
	
	private String sFileName="";
	private File flName;
	private FileInputStream isfOne;
	private DataInputStream in;
	private BufferedReader br; 
	private String strLine=null;

	/**
	 * @param sFileName
	 */
	public LineReader(String sFileName) {
		super();
		this.sFileName = sFileName;
		flName = new File(sFileName);
		
		try {
			isfOne = new FileInputStream(flName);
			in = new DataInputStream(isfOne);
				
			br = new BufferedReader(new InputStreamReader(in));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public BooleanMessage OpenFile(String sFileName)
	{
		flName = new File(sFileName);
		
		try {
			isfOne = new FileInputStream(flName);
			in = new DataInputStream(isfOne);
			br = new BufferedReader(new InputStreamReader(in));
			

			return new BooleanMessage(true,"File Opened");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new BooleanMessage(false,"File failed to open");

		}

	}

	public String ReadNextLine()
	{
		
		try {
			if ((strLine = br.readLine()) != null)   {
				  // Print the content on the console
				  System.out.println (strLine);
				  return strLine;
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> ReadAllNextLines()
	{
		ArrayList<String> alLines = new ArrayList();
		try {
			while ((strLine = br.readLine()) != null)   {
				  // Print the content on the console
				  System.out.println (strLine);
				  alLines.add(strLine);
				}
			return alLines;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> ReadAllLines()
	{
		OpenFile(sFileName);
		ArrayList<String> alLines = new ArrayList();
		try {
			while ((strLine = br.readLine()) != null)   {
				  // Print the content on the console
				  System.out.println (strLine);
				  alLines.add(strLine);
				}
			
			return alLines;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void Close()
	{
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

