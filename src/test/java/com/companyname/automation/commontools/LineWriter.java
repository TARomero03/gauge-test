package com.companyname.automation.commontools;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LineWriter {
	private FileWriter fw99 = null;
	//ReportNGUtils ru99 = new ReportNGUtils();
	
	private PrintWriter pw99 = null;

	private String sFileName="";

	/**
	 * @param sFileName
	 */
	public LineWriter(String sFileName) {
		super();
		int iLst = sFileName.lastIndexOf(File.separator);
		if(iLst > -1)
		{
		String sDirs = sFileName.substring(0,iLst);
		File fDirs = new File(sDirs);
		try {
			fDirs.mkdirs();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		this.sFileName = sFileName;
		try {
			fw99 = new FileWriter(sFileName);
			pw99 = new PrintWriter(fw99);
			
		} catch (Exception e88) {
			System.out.println("Problem Log Start");
		}

	}
	
	public BooleanMessage WriteLine(String sLine)
	{
		try {
			pw99.println(sLine);
			pw99.flush();
			return new BooleanMessage(true, "Line written");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new BooleanMessage(false, "Line not written");
		}
	}

	public BooleanMessage WriteLines(ArrayList<String> alLines)
	{
		try {
			for(int iLns=0; iLns<alLines.size();iLns++)
			{
			pw99.println(alLines.get(iLns));
			}
			pw99.flush();
			return new BooleanMessage(true, "Lines written");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new BooleanMessage(false, "Lines not written");
		}
	}

	public BooleanMessage Close()
	{
		try {
			pw99.flush();
			pw99.close();
			fw99.close();
			return new BooleanMessage(true,"File Closed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new BooleanMessage(false,"File Not Closed");

		}
	}
}
