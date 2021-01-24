package com.companyname.automation.commontools;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;

import org.testng.Assert;



/**
 * ClearDirectory - Deletes the Contents of a Directory CopyDirectory - Copy the
 * Contents of a Directory to another Directory GetFeildsInClass - Returns all
 * the Fields in a class in an arraylist of FldValues which are field name and
 * field value pairs ExcelFileConnection - Creates a jdbc/odbc connection to an
 * Excel File , SqlServerConnection- Creates a jdbc/odbc connection to an SQL
 * Server Database , ReplaceHardTags - To replace tagged items within a string a
 * tagged item is anything surrounded by [~ & ~] i.e [~Machine~] Based on the
 * Machine being used , ReplaceSoftTags - To replace tagged items within a
 * string a tagged item is anything surrounded by [~ & ~] where the tag is on a
 * worksheet
 * 
 * @author Web Services V and V Automation Team
 * 
 */
public class NonObjectMethods implements ClipboardOwner {
	public NonObjectMethods() {
		// TODO Auto-generated constructor stub
	}

	public static void openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop()
				: null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void openWebpage(URL url) {
		try {
			openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Executes a Command String
	 * 
	 * @param sCommand
	 * @param iTime
	 * 
	 */

	public ArrayList<String> ExecuteCommand(String sCommand, double dTime) {
		StopWatch sw77 = new StopWatch();
		try {
			Runtime rt88 = java.lang.Runtime.getRuntime();
			sw77.start();
			System.out.println("Start: " + sw77.getElapsedTime());
			Process proc = rt88.exec(sCommand);
			System.out.println("Started: " + sw77.getElapsedTime());
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(
					proc.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			String s = "";
			String s2 = "";
			String sLines = "";
			int iCnt = 0;
			String sEOL = "" + (char) 10 + (char) 13;
			ArrayList<String> alRtns = new ArrayList();
			System.out.println("New ********************");
			while (sw77.getElapsedTimeSecs() < dTime) {
				System.out.println(sw77.getElapsedTimeSecs() + " -- " + dTime);
				s="";
				try {
					if(!stdInput.ready() && !stdError.ready()) continue;
					if(stdInput.ready()) {s = stdInput.readLine();}
					if(stdError.ready()) {s2 = stdError.readLine();}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					continue;
				}
				System.out.println("Read s: " + s);
				System.out.println("Read s2: " + s2);
				if (s == null)
					continue;
				try {
					System.out.println(s + " -- " + sw77.getElapsedTimeSecs()
							+ " -- " + dTime);
					alRtns.add(s);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			stdInput.close();
			proc.destroy();
			return alRtns;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
	}

	/**
	 * Executes a Command String
	 * 
	 * @param sCommand
	 * 
	 */

	public ArrayList<String> ExecuteCommand(String sCommand) {
		try {
			Runtime rt88 = java.lang.Runtime.getRuntime();
			Process proc = rt88.exec(sCommand);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(
					proc.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			String s = "";
			int iSc=0;
			while ((s = stdInput.readLine()) == null) {
				this.NapTime(0.1);
				iSc++;
				if(iSc>100) break;
			}

			String sLines = "";
			int iCnt = 0;
			String sEOL = "" + (char) 10 + (char) 13;
			ArrayList<String> alRtns = new ArrayList();
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
				alRtns.add(s);
			}
			stdInput.close();
			proc.destroy();
			return alRtns;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
	}

	/**
	 * Clears the contents of a Directory
	 * 
	 * @param sourceLocation
	 *            - The File Object for the Directory to be cleared
	 */
	public void ClearDirectory(File sourceLocation) {

		if (sourceLocation.isDirectory()) {

			String[] children = sourceLocation.list();
			for (int i = 0; i < children.length; i++) {
				System.out
						.println("In Get Children: " + children[i].toString());
				this.ClearDirectory(new File(sourceLocation, children[i]
						.toString()));
			}
		} else {
			System.err.println("Delete File: "
					+ sourceLocation.getAbsolutePath());
			try {
				sourceLocation.delete();
			} catch (Exception e88) {
				System.out.println("ClearDir Exception: " + e88.getMessage());
			}
		}

	}

	/**
	 * Copy the contents of a Directory to another Directory
	 * 
	 * @param sourceLocation
	 *            - The File Object for the Directory to be copied
	 * @param targetLocation
	 *            - The File Object for the Directory being copied to
	 */
	public void copyDirectory(File sourceLocation, File targetLocation) {

		if (sourceLocation.isDirectory()) {
			System.err.println("copyFromDir: "
					+ sourceLocation.getAbsolutePath());
			System.err
					.println("copyToDir: " + targetLocation.getAbsolutePath());
			if (!targetLocation.exists()) {
				targetLocation.mkdir();
			}

			String[] children = sourceLocation.list();
			for (int i = 0; i < children.length; i++) {
				System.out.println("In Copy Child: " + children[i].toString());
				this.copyDirectory(
						new File(sourceLocation, children[i].toString()),
						new File(targetLocation, children[i].toString()));
			}
		} else {
			System.err.println("copyFromFile: "
					+ sourceLocation.getAbsolutePath());
			System.err.println("copyToFile: "
					+ sourceLocation.getAbsolutePath());
			try {
				InputStream in = new FileInputStream(sourceLocation);
				OutputStream out = new FileOutputStream(targetLocation);

				// Copy the bits from instream to outstream
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
				out.close();
			} catch (Exception e88) {
				System.out.println("CopyDir Exception: " + e88.getMessage());
			}
		}

	}

	/**
	 * Returns an ArrayList of the differences between 2 files
	 * 
	 * @param sFile1
	 *            - The Name of the first file being compared
	 * @param sFile2
	 *            - The Name of the second file being compared
	 * @param sLogFile
	 *            - The Name of the file the results are logged to
	 * @param alIgnoreLines
	 *            - An Arraylist of IgnoreLinePart records
	 * @param iMaxErrors
	 *            - The maximum number of errors to display
	 * @return - The ArrayList of the FldValues which are field name and field
	 *         value pairs
	 */
	public ArrayList StraightFileCompare(String sFile1, String sFile2,
			String sLogFile, int iMaxErrors, ArrayList alIgnoreLines) {
		System.out.println("in SFC");
		String sTab = "" + (char) 9;
		ArrayList alRtn = new ArrayList();
		BufferedReader input = null;
		BufferedReader input2 = null;

		// Check to see if files exists
		boolean bFileNotThere = false;
		boolean bFileEmpty = false; // TAR new entry
		File f = new File(sFile1);
		if (f.exists() == false) {
			alRtn.add("File 1: " + sFile1 + " = does not exist");
			bFileNotThere = true;
		} else // TAR added only check length if not empty
		{
			// Check to see if files are empty - added Feb. 8th, 2009
			if (f.length() == 0) {
				alRtn.add("File 1: " + sFile1 + " = length is 0 - empty");
				bFileEmpty = true;
			}

		}

		f = new File(sFile2);
		if (f.exists() == false) {
			alRtn.add("File 2: " + sFile2 + " = does not exist");
			bFileNotThere = true;
		} else // TAR added only check length if not empty
		{
			if (f.length() == 0) {
				alRtn.add("File 2: " + sFile2 + " = length is 0 - empty");
				bFileEmpty = true;
			}

		}

		if (bFileNotThere) // TAR
		{
			return alRtn;
		}

		if (bFileEmpty) // TAR
		{
			// TAR
			// If both files are empty clear the return arraylist no entries in
			// - test passed
			if (((new File(sFile2)).length() == 0)
					&& ((new File(sFile1)).length() == 0)) {
				alRtn.clear();
			}
			return alRtn;
			// TAR
		}
		try {
			// use buffering, reading one line at a time

			input = new BufferedReader(new FileReader(sFile1));
			String line = null; // not declared within while loop
			input2 = new BufferedReader(new FileReader(sFile2));
			String line2 = null; // not declared within while loop

			ArrayList al99 = new ArrayList();
			ArrayList al992 = new ArrayList();

			while ((line = input.readLine()) != null) {
				al99.add(line.trim());
				// // System.out.println(line);
			}

			while ((line2 = input2.readLine()) != null) {
				al992.add(line2.trim());
				// // System.out.println(line2);
			}

			FileWriter outFile = new FileWriter(sLogFile);
			PrintWriter out = new PrintWriter(outFile);
			out.println("File Comparison:");
			out.println(sTab + "File 1: " + sFile1);
			out.println(sTab + "File 2: " + sFile2);

			int iCnt = al99.size();
			if (iCnt > al992.size()) {
				iCnt = al992.size();
			}

			int iErrors = 0;
			if (al99.size() != al992.size()) {
				iErrors++;
				iMaxErrors++;
				if (iErrors == 1) {
					out.println("Differences:");
					alRtn.add("Differences:");
				}
				out.println("");
				out.println(sTab
						+ "The files have a different number of lines ");
				out.println(sTab + sTab + "File 1 has " + al99.size()
						+ " lines File 2 has " + al992.size() + " lines");
				alRtn.add("");
				alRtn.add(" The files have a different number of lines ");
				alRtn.add("  File 1 has " + al99.size() + " lines File 2 has "
						+ al992.size() + " lines");

			}
			String sOtherLines = "";
			for (int iC = 0; iC < iCnt; iC++) {
				int iC2 = iC + 1;
				String sNewF1 = "";
				String sNewF2 = "";
				System.out.println("Compare Line: " + iC2);
				String s1 = al99.get(iC).toString();
				String s2 = al992.get(iC).toString();
				System.out.println(s1);
				System.out.println(s2);

				int iOldStrt = 0;
				int iStrt;
				int iEndX;
				System.out.println("iLines Count = " + alIgnoreLines.size());
				boolean bMtch = false;
				for (int iQ = 0; iQ < alIgnoreLines.size(); iQ++) {
					IgnoreLinePart ilp = (IgnoreLinePart) alIgnoreLines.get(iQ);
					System.out.println("Line= " + ilp.getiLine());
					if (iC2 == ilp.getiLine()) {
						bMtch = true;
						iStrt = ilp.getiStartPos();
						iEndX = ilp.getiEndPos();
						System.out.println("Strt= " + iStrt);
						System.out.println("End= " + iEndX);

						if ((iStrt == 0) && (iEndX == 0)) {
							sNewF1 = "";
							sNewF2 = "";
						}
						System.out.println("Old Start: " + iOldStrt
								+ " iStrt: " + iStrt);
						if ((iStrt > 0) && (iEndX > 0)) {
							if ((s1.length() >= iOldStrt)
									&& (s1.length() >= (iStrt - 1))) {
								sNewF1 = sNewF1
										+ s1.substring((iOldStrt), (iStrt - 1));
							}
							if ((s2.length() >= iOldStrt)
									&& (s2.length() >= (iStrt - 1))) {
								sNewF2 = sNewF2
										+ s2.substring((iOldStrt), (iStrt - 1));
							}
							iOldStrt = iEndX;
						}
						System.out.println("Line: " + iC2 + " - " + iStrt
								+ " to " + iEndX);
						System.out.println(sNewF1);

					}
				}
				if (iOldStrt > 0) {
					if (s1.length() >= iOldStrt) {
						sNewF1 = sNewF1
								+ s1.substring((iOldStrt), (s1.length()));
					}
					if (s2.length() >= iOldStrt) {
						sNewF2 = sNewF2
								+ s2.substring((iOldStrt), (s2.length()));
					}

				}
				if (bMtch == false) {
					sNewF1 = s1;
					sNewF2 = s2;
				}
				System.out.println(sNewF1);
				System.out.println(sNewF2);

				if (sNewF1.compareTo(sNewF2) != 0) {
					iErrors++;
					if (iErrors == 1) {
						out.println("Differences:");
						alRtn.add("Differences:");
					}

					if (((iErrors <= iMaxErrors) && (iMaxErrors > 0))
							|| (iMaxErrors == 0)) {
						out.println(sTab + "Line: " + String.valueOf(iC + 1));
						out.println(sTab + sTab + "File 1: "
								+ al99.get(iC).toString());
						out.println(sTab + sTab + "File 2: "
								+ al992.get(iC).toString());

						alRtn.add("&nbsp; &nbsp; Line: "
								+ String.valueOf(iC + 1));
						alRtn.add("&nbsp; &nbsp; &nbsp; &nbsp; File 1: "
								+ al99.get(iC).toString());
						alRtn.add("&nbsp; &nbsp; &nbsp; &nbsp; File 2: "
								+ al992.get(iC).toString());
					} else {
						if (iErrors == (iMaxErrors + 1)) {
							sOtherLines = "Other lines with differences are: "
									+ String.valueOf(iC + 1);
						} else {
							sOtherLines = sOtherLines + " , "
									+ String.valueOf(iC + 1);
						}
					}
				}
			}

			if (sOtherLines.length() > 0) {
				out.println("");
				out.println(sTab + sTab + sOtherLines);
				alRtn.add("");
				alRtn.add("&nbsp; &nbsp;" + sOtherLines);
			}

			if (iErrors == 0) {
				out.println("");
				out.println(sTab + "PASS - NO DIFFERENCES");
			}
			out.close();
			outFile.close();
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
			alRtn.add("error in spreadsheet " + sLogFile + " - ex; "
					+ ex.getMessage());

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					// flush and close both "input" and its underlying
					// FileReader
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			try {
				if (input2 != null) {
					// flush and close both "input" and its underlying
					// FileReader
					input2.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return alRtn;
	}

	/**
	 * Returns all the Fields in a class in an arraylist of FldValues which are
	 * field name and field value pairs
	 * 
	 * @param clss
	 *            - The Class for which the FldValues are being retrieved
	 * @return - The ArrayList of the FldValues which are field name and field
	 *         value pairs
	 */
	public ArrayList GetFeildsInClass(Class clss) {
		ArrayList alRtn = new ArrayList();
		String sClassName = clss.getName();
		int iNdx = sClassName.indexOf("$");
		sClassName = sClassName.substring((iNdx + 1));

		Field[] fld99 = clss.getDeclaredFields();

		ArrayList alNames = new ArrayList();
		ArrayList alValues = new ArrayList();
		FldValue fv99;
		for (int i = 0; i < fld99.length; i++) {
			Field fld = fld99[i];
			fv99 = new FldValue();
			fv99.setSName(sClassName + " : " + fld.getName());
			String sValue = "";

			try {
				sValue = (String) fld.get(toString());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fv99.setSValue(sValue);
			alRtn.add(fv99);
		}

		ArrayList alClasses = new ArrayList();
		Class cls99[] = clss.getClasses();

		boolean bKp = true;
		int iCnt = 0;
		while (bKp) {
			for (int iQ = 0; iQ < cls99.length; iQ++) {
				alClasses.add(cls99[iQ]);
			}
			bKp = false;
			if (iCnt < alClasses.size()) {
				cls99 = ((Class) alClasses.get(iCnt)).getClasses();
				iCnt++;
				bKp = true;
			}
		}

		for (int iR = 0; iR < alClasses.size(); iR++) {
			fld99 = ((Class) alClasses.get(iR)).getFields();
			sClassName = ((Class) alClasses.get(iR)).getName();
			iNdx = sClassName.indexOf("$");
			sClassName = sClassName.substring((iNdx + 1));

			for (int i = 0; i < fld99.length; i++) {
				Field fld = fld99[i];
				fv99 = new FldValue();
				fv99.setSName(sClassName + " : " + fld.getName());
				String sValue = "";

				try {
					sValue = (String) fld.get(toString());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fv99.setSValue(sValue);
				alRtn.add(fv99);
			}

		}

		return alRtn;
	}

	/**
	 * Creates a jdbc/odbc connection to an Excel File
	 * 
	 * @param sFileName
	 *            - name of the Excel File you want to connect to
	 * @return the jdbc/odbc connection
	 */
	public Connection ExcelFileConnection(String sFileName) {

		Connection con = null;
		try {
			// Enable JDBC ODBC Driver to access excel
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// Set Connection to Excel File
			con = DriverManager
					.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ="
							+ sFileName);

		} catch (Exception ee1) {
			System.out.println("Connection to the Excel File failed - "
					+ ee1.getMessage());
		}
		return con;
	}

	/**
	 * Create a jdbc connection to an Oracle Database
	 */

	public Connection OracleConnection(String sDbName, String sUser, String sPwd) {
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return null;

		}

		System.out.println("Oracle JDBC Driver Registered!");

		Connection connection = null;

		try {
			String sUrl = "jdbc:oracle:thin:@//" + sDbName;
			System.err.println("sUrl: " + sUrl);
			System.err.println("User: " + sUser);
			System.err.println("Pwd: " + sPwd);
			connection = DriverManager.getConnection(sUrl, sUser, sPwd);

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

		return connection;
	}

	/*
	 * public Connection OracleTNSconnection(String sTNSname, String sUser,
	 * String sPwd) { Connection conn = null; try { OracleDataSource ods = new
	 * OracleDataSource(); ods.setTNSEntryName(sTNSname); ods.setUser(sUser);
	 * ods.setPassword(sPwd); ods.setDriverType("oci"); conn =
	 * ods.getConnection(); } catch (SQLException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); return null; }
	 * 
	 * return conn;
	 * 
	 * }
	 */
	/**
	 * Creates a jdbc/odbc connection to a SQL Server database
	 * 
	 * @param sServer
	 *            - Name of the Server being connected to
	 * @param sDbName
	 *            - Name of the Datebase being connected to
	 * @param sUser
	 *            - The User ID being used to logon
	 * @param sPassword
	 *            - The Password being used to logon
	 * @return - the jdbc/odbc connection
	 */
	public Connection SqlServerConnection(String sServer, String sDbName,
			String sUser, String sPwd) {

		Connection con = null;
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// Set Connection to Excel File
			System.out.println("Class worked");
			// MYPC:1433/Blog;instance=SQLEXPRESS;user=sa;password=s3cr3t
			// con = DriverManager.getConnection("jdbc:jtds:sqlserver://"
			// + sServer + "/" + sDbName +
			// ";instance=SQLEXPRESS;user=qpayusr;password=ch7Unles");
			String sUrl = "jdbc:sqlserver://" + sServer + ";databaseName="
					+ sDbName + ";user=" + sUser + ";password=" + sPwd;
			if (sUser.length() == 0) {
				sUrl = "jdbc:sqlserver://" + sServer + ";databaseName="
						+ sDbName + ";integratedSecurity=true;";
			}
			// con =
			// DriverManager.getConnection("jdbc:sqlserver://192.168.71.200\\trans:51273;databaseName=QUANTUM;user=qausr;password=qpay2011!");
			System.out.println("Connection String: " + sUrl);
			con = DriverManager.getConnection(sUrl);
		} catch (Exception ee1) {
			System.out.println("Connection to the database failed: "
					+ ee1.getMessage());
		}
		return con;
	}

	/*	*//**
	 * Creates a jdbc/odbc connection to a database
	 * 
	 * @param sServer
	 *            - Name of the Server being connected to
	 * @param sDbName
	 *            - Name of the Datebase being connected to
	 * @param sUser
	 *            - The User ID being used to logon
	 * @param sPassword
	 *            - The Password being used to logon
	 * @return - the jdbc/odbc connection
	 */
	/*
	 * public Connection SqlServerConnection(String sServer, String
	 * sDbName,String sUser, String sPwd ) {
	 * 
	 * Connection con = null; try {
	 * 
	 * Class.forName("net.sourceforge.jtds.jdbc.Driver");
	 * 
	 * // Set Connection to Excel File System.out.println("Class worked");
	 * //MYPC:1433/Blog;instance=SQLEXPRESS;user=sa;password=s3cr3t // con =
	 * DriverManager.getConnection("jdbc:jtds:sqlserver://" // + sServer + "/" +
	 * sDbName + ";instance=SQLEXPRESS;user=qpayusr;password=ch7Unles"); String
	 * sUrl = "jdbc:jtds:sqlserver://" + sServer + "/" + sDbName + ";user=" +
	 * sUser +";password="+sPwd; con = DriverManager.getConnection(sUrl); }
	 * catch (Exception ee1) {
	 * System.out.println("Connection to the database failed: " +
	 * ee1.getMessage()); } return con; }
	 *//**
	 * Creates a jdbc/odbc connection to a database
	 * 
	 * @param sServer
	 *            - Name of the Server being connected to
	 * @param sDbName
	 *            - Name of the Datebase being connected to
	 * @param sUser
	 *            - The User ID being used to logon
	 * @param sPassword
	 *            - The Password being used to logon
	 * @return - the jdbc/odbc connection
	 */
	/*
	 * public Connection SqlServerConnectionWindowsAuthenticate(String sServer,
	 * String sDbName, String sUser,String sPwd ) {
	 * 
	 * Connection con = null; try { // String ConUrl = "jdbc:sqlserver://" +
	 * sServer + ":1433;" + "databaseName="+ sDbName + ";user=" + sUser +
	 * ";integratedSecurity=true;"; String ConUrl = "jdbc:sqlserver://" +
	 * sServer + ";" + "databaseName="+ sDbName + ";user=" + sUser +
	 * ";password=" + sPwd;
	 * 
	 * 
	 * // Set Connection to Excel File System.out.println("Class worked");
	 * 
	 * con = DriverManager.getConnection(ConUrl);
	 * 
	 * } catch (Exception ee1) {
	 * System.out.println("Connection to the database failed: " +
	 * ee1.getMessage()); } return con; }
	 */
	/**
	 * To replace tagged items within a string a tagged item is anything
	 * surrounded by [~ & ~] i.e [~Machine~] Tags include: Machine - name of
	 * machine UserID - user id of user logged in Datem/d/yyyy - Formatted Date
	 * - Current +/- days Datemm/dd/yyyy - Formatted Date - Current +/- days
	 * Dateyyyymmdd - Formatted Date - Current +/- days Dateyyyymmddhhmm -
	 * Formatted DateTime - Current +/- minutes
	 * 
	 * The values are extracted from the machine in use
	 * 
	 * @param sOrig
	 *            - the String that contains the tag(s) to be replaced
	 * @return - the string after the tag(s) have been replaced
	 */
	public String ReplaceHardTags(String sOrig) {
		// Script1Helper.logInfo("Made to QA Replace");
		String sFinal = sOrig;
		String sTagged;
		String sReplace = null;

		int iStrtPos;
		int iEndPos;
		int iPos;
		int iDays = 0;
		double dDays = 0.00;
		int iStrt = 0;
		String sDays = null;

		boolean bContinue = true;
		// // System.out.println("Made it to qa.replacetags: " + sOrig);
		sFinal = sOrig;
		while (bContinue == true) {
			bContinue = false;
			// Determine if the string is tagged
			iStrtPos = sOrig.indexOf("[~", iStrt); // Get the Start of the Tag
			iEndPos = sOrig.indexOf("~]", iStrt); // Get the End of the Tag
			iStrt = iEndPos + 2;
			// // System.out.println("in the loop");
			// // System.out.println(iStrtPos);
			// // System.out.println(iEndPos);
			// if both beginning and ending tags exist continue
			if ((iStrtPos >= 0) && (iEndPos >= 0)) {
				// Extract out the tagged item

				sTagged = sOrig.substring((iStrtPos + 2), (iEndPos));
				// System.out.println(" -- " + sTagged);
				// Script1Helper.logInfo(sTagged);
				if ((sTagged.indexOf("Dateyyyymmdd") >= 0)
						|| (sTagged.indexOf("Dateyyyy-mm-dd") >= 0)
						|| (sTagged.indexOf("Datemm/dd/yyyy") >= 0)
						|| (sTagged.indexOf("Datem/d/yyyy") >= 0)
						|| (sTagged.indexOf("DateTimeMM/dd/yyyy") >= 0)
						|| (sTagged.indexOf("GetTime") >= 0)
						|| (sTagged.indexOf("GetYear") >= 0)
						|| (sTagged.indexOf("Datjul") >= 0)) {

					iPos = sTagged.indexOf("+");
					if (iPos >= 0) {
						sDays = sTagged.substring(iPos + 1).trim();
						try {
							iDays = Integer.parseInt(sDays);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
						}
						try {
							dDays = Double.parseDouble(sDays);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
						}
					} else {
						iPos = sTagged.indexOf("-");
						if (iPos >= 0) {
							sDays = sTagged.substring(iPos + 1).trim();
							try {
								iDays = Integer.parseInt(sDays) * (-1);
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
							}
							try {
								dDays = Double.parseDouble(sDays) * (-1);
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
							}

						}
					}
				}

				if ((sTagged.indexOf("Dateyyyy-mm-dd") >= 0)) {

					iPos = sTagged.indexOf("+");
					if (iPos >= 0) {
						sDays = sTagged.substring(iPos + 1).trim();
						iDays = Integer.parseInt(sDays);
					} else {
						iPos = sTagged.lastIndexOf("-");
						if (iPos >= 0) {
							sDays = sTagged.substring(iPos + 1).trim();
							iDays = Integer.parseInt(sDays) * (-1);
						}
					}
				}

				Format sdfMMDDYYYY;
				Format sdfYYYYMMDD;
				Format sdfYYYYMMDDdashed;

				Format sdfYYYYMMDDHHMM;
				Format sdfMDYYYY;
				Format sdfMMDDYYhhmmam;
				Format sdfMMDDYYYYHHmmssSSS;
				Format sdfYYYYMMDDHHMMSSMM;
				Format sdfYYYYMMDDHHMMSSMMM;
				Format sdfYYYY;
				Format sdfHHMMSSMMM;

				// String sdf = "MM" + s + "dd" + s + "yyyy";
				String sdf = "MM/dd/yyyy";
				sdfMMDDYYYY = new SimpleDateFormat(sdf);
				String sdf2 = "yyyyMMdd";
				sdfYYYYMMDD = new SimpleDateFormat(sdf2);
				String sdf2dashed = "yyyy-MM-dd";
				sdfYYYYMMDDdashed = new SimpleDateFormat(sdf2dashed);

				String sdf3 = "yyyyMMddHHmm";
				sdfYYYYMMDDHHMM = new SimpleDateFormat(sdf3);
				String sdf3a = "yyyyMMddHHmmssSS";
				sdfYYYYMMDDHHMMSSMM = new SimpleDateFormat(sdf3a);
				String sdf3b = "yyyyMMddHHmmssSSs";
				sdfYYYYMMDDHHMMSSMMM = new SimpleDateFormat(sdf3b);
				String sdf4 = "M/d/yyyy";
				sdfMDYYYY = new SimpleDateFormat(sdf4);
				String sdf5 = "MM/dd/yyyy hh:mm a";
				sdfMMDDYYhhmmam = new SimpleDateFormat(sdf5);
				String sdf6 = "MM/dd/yyyy HH:mm.ss.SSS";
				sdfMMDDYYYYHHmmssSSS = new SimpleDateFormat(sdf6);
				String sdf7 = "yyyy";
				sdfYYYY = new SimpleDateFormat(sdf7);
				String sdf8 = "yyyyMMddHHmmssSSS";
				sdfHHMMSSMMM = new SimpleDateFormat(sdf8);

				GregorianCalendar gc1 = new GregorianCalendar();

				// gc1.roll(Calendar.DAY_OF_MONTH,iDays);
				// gc1.roll(Calendar.DATE,iDays);

				// // System.out.println(gc1.getTime().toString());

				boolean bFnd = false;
				if (sTagged.indexOf("GetYear") >= 0) {
					sReplace = String.valueOf(gc1.get(Calendar.YEAR) + iDays);
					System.out.println("Get Year " + sReplace);
					bFnd = true;
				}

				if (sTagged.indexOf("GetTimeYears") >= 0) {
					gc1.add(Calendar.YEAR, iDays);
					long tX = gc1.getTimeInMillis();
					sReplace = String.valueOf(tX);
					// // System.out.println(sReplace);
					bFnd = true;
				}
				if (sTagged.indexOf("GetTimeDays") >= 0) {
					gc1.add(Calendar.DATE, iDays);
					long tX = gc1.getTimeInMillis();
					sReplace = String.valueOf(tX);
					// // System.out.println(sReplace);
					bFnd = true;
				}
				if (sTagged.indexOf("GetTimeMinutes") >= 0) {
					gc1.add(Calendar.MINUTE, iDays);
					long tX = gc1.getTimeInMillis();
					sReplace = String.valueOf(tX);
					// // System.out.println(sReplace);
					bFnd = true;
				}

				if (sTagged.indexOf("Datem/d/yyyy") >= 0) {
					gc1.add(Calendar.DAY_OF_MONTH, iDays);
					sReplace = sdfMDYYYY.format(gc1.getTime());
					// // System.out.println(sReplace);
					bFnd = true;
				}
				if (sTagged.indexOf("Datemm/dd/yyyy") >= 0) {
					gc1.add(Calendar.DAY_OF_MONTH, iDays);
					sReplace = sdfMMDDYYYY.format(gc1.getTime());
					// // System.out.println(sReplace);
					bFnd = true;
				}

				if (sTagged.indexOf("Dateyyyymmdd") >= 0) {
					gc1.add(Calendar.DAY_OF_MONTH, iDays);
					sReplace = sdfYYYYMMDD.format(gc1.getTime());
					// // System.out.println(sReplace);
					bFnd = true;
				}

				if (sTagged.indexOf("Dateyyyy-mm-dd") >= 0) {
					gc1.add(Calendar.DAY_OF_MONTH, iDays);
					sReplace = sdfYYYYMMDDdashed.format(gc1.getTime());
					// // System.out.println(sReplace);
					bFnd = true;
				}

				if (sTagged.indexOf("DateTimeMM/dd/yyyyhhmma") >= 0) {
					gc1 = new GregorianCalendar();
					gc1.add(Calendar.DAY_OF_MONTH, iDays);
					sReplace = sdfMMDDYYhhmmam.format(gc1.getTime());
					bFnd = true;
				}

				if (sTagged.indexOf("DateTimeMM/dd/yyyyHHmmssSSS") >= 0) {
					gc1 = new GregorianCalendar();
					gc1.add(Calendar.DAY_OF_MONTH, iDays);
					sReplace = sdfMMDDYYYYHHmmssSSS.format(gc1.getTime());
					bFnd = true;
				}

				if (sTagged.indexOf("Dateyyyymmddhhmm") >= 0) {
					gc1 = new GregorianCalendar();
					gc1.add(Calendar.MINUTE, iDays);
					sReplace = sdfYYYYMMDDHHMM.format(gc1.getTime());
					bFnd = true;
				}

				if (sTagged.indexOf("Dateyyyymmddhhmmssmm") >= 0) {
					gc1 = new GregorianCalendar();
					gc1.add(Calendar.MINUTE, iDays);
					sReplace = sdfYYYYMMDDHHMMSSMM.format(gc1.getTime());
					bFnd = true;
				}

				if (sTagged.indexOf("DateBase62") >= 0) {
					gc1 = new GregorianCalendar();
					// gc1.add(Calendar.MINUTE, iDays);
					String sTemp = sdfYYYYMMDDHHMMSSMMM.format(gc1.getTime());
					double dTmp = Double.valueOf(sTemp);

					int iChkPt = 0;
					double dChkVal = 0;
					sReplace = "0";
					while (dChkVal <= dTmp) {
						dChkVal = Math.pow(62, iChkPt);
						if (dChkVal > dTmp)
							break;
						iChkPt++;

						sReplace = sReplace + "0";
					}
					// iChkPt--;
					// sReplace = sReplace.substring(1);

					double dCntDwn = dTmp;
					ArrayList<String> base62 = new ArrayList<String>(
							Arrays.asList("0", "1", "2", "3", "4", "5", "6",
									"7", "8", "9", "a", "b", "c", "d", "e",
									"f", "g", "h", "i", "j", "k", "l", "m",
									"n", "o", "p", "q", "r", "s", "t", "u",
									"v", "w", "x", "y", "z", "A", "B", "C",
									"D", "E", "F", "G", "H", "I", "J", "K",
									"L", "M", "N", "O", "P", "Q", "R", "S",
									"T", "U", "V", "V", "X", "Y", "Z"));
					char saReplace[] = sReplace.toCharArray();

					for (int iFg = iChkPt - 1; iFg > 0; iFg--) {
						double dMax = Math.pow(62, iFg);
						System.err.println(sTemp + " " + iFg + " - " + dCntDwn
								+ " / " + dMax);

						if (dMax > dCntDwn)
							continue;
						int iQtn = (int) (dCntDwn / dMax);
						System.err.println(sTemp + " " + iFg + " - " + dCntDwn
								+ " / " + dMax + " - " + iQtn + " -- "
								+ base62.get(iQtn).charAt(0));

						dCntDwn = dCntDwn - (dMax * iQtn);
						saReplace[(iChkPt - 1) - iFg] = base62.get(iQtn)
								.charAt(0);
					}
					System.err.println(sTemp + " 0  - " + dCntDwn + " -- "
							+ base62.get((int) dCntDwn).charAt(0));

					saReplace[iChkPt] = base62.get((int) dCntDwn).charAt(0);

					sReplace = String.copyValueOf(saReplace);
					bFnd = true;
				}

				if (sTagged.indexOf("Datejulhhmmssmmm") >= 0) {
					gc1 = new GregorianCalendar();
					gc1.add(Calendar.DAY_OF_YEAR, iDays);
					sReplace = gc1.DAY_OF_YEAR
							+ sdfHHMMSSMMM.format(gc1.getTime());
					bFnd = true;
				}

				if (sTagged.indexOf("Machine") >= 0) {
					try {
						java.net.InetAddress iA = java.net.InetAddress
								.getLocalHost();
						// // System.out.println(iA.getHostName()); // name
						sReplace = iA.getHostName();
						bFnd = true;
					} catch (Exception e) {
					}
				}


				if (sTagged.indexOf("MAC") >= 0) {
					InetAddress ip;
					try {

						ip = InetAddress.getLocalHost();
						System.out.println("Current IP address : "
								+ ip.getHostAddress());

						NetworkInterface network = NetworkInterface
								.getByInetAddress(ip);

						byte[] mac = network.getHardwareAddress();

						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < mac.length; i++) {
							sb.append(String.format("%02X%s", mac[i],
									(i < mac.length - 1) ? "-" : ""));
						}
						// MAC ADDRESS
						sReplace = sb.toString();
						if (sReplace.trim().length() == 0) {
							System.out
									.println("Normal MAC is blank - get first non blank Interface");
							Enumeration<NetworkInterface> nets = NetworkInterface
									.getNetworkInterfaces();
							for (NetworkInterface netint : Collections
									.list(nets)) {
								System.out.println("Display name: "
										+ netint.getDisplayName());
								System.out.println("Hardware address: "
										+ Arrays.toString(netint
												.getHardwareAddress()));
								if (sReplace.trim().length() == 0) {

									mac = netint.getHardwareAddress();

									try {
										sb = new StringBuilder();
										for (int i = 0; i < mac.length; i++) {
											sb.append(String.format("%02X%s",
													mac[i],
													(i < mac.length - 1) ? "-"
															: ""));
										}
										System.err.println(sb.toString());
										sReplace = sb.toString();
										String saT[] = sReplace.split("-");
										if (saT.length != 6) {
											sReplace = "";
										}
									} catch (Exception e) {
										// TODO Auto-generated catch block
									}

								}

							}

						}
						if (sReplace.length() > 0) {
							System.out.println("use this mac: " + sReplace);

						}
						bFnd = true;

					} catch (UnknownHostException e) {

						e.printStackTrace();

					} catch (SocketException e) {

						e.printStackTrace();

					}
				}

				if (sTagged.indexOf("IP") >= 0) {
					InetAddress ip;
					try {

						ip = InetAddress.getLocalHost();

						// IP
						sReplace = ip.getHostAddress();
						bFnd = true;

					} catch (UnknownHostException e) {

						e.printStackTrace();

					}
				}

				if (sTagged.indexOf("UserID") >= 0) {
					try {
						// // System.out.println(System.getProperty (
						// "user.name" ) ); // name
						sReplace = System.getProperty("user.name");
						
						bFnd = true;
					} catch (Exception e) {
					}
				}

				if (sTagged.indexOf("OS") >= 0) {
					try {
						// // System.out.println(System.getProperty (
						// "user.name" ) ); // name
						sReplace = System.getProperty("os.name");
						sReplace = sReplace + "_"
								+ System.getProperty("os.version");
						bFnd = true;
					} catch (Exception e) {
					}
				}

				if (bFnd == true) {
					sTagged = "[~" + sTagged + "~]";
					// // System.out.println(sTagged);
					// // System.out.println(sReplace);
					sOrig = sOrig.substring(0, (iStrtPos)) + sReplace
							+ sOrig.substring((iEndPos + 2));
					// // System.out.println(sOrig);
					bContinue = true;
					iStrt = 0;
				}
				sFinal = sOrig;

			}
		}
		// // System.out.println(sFinal);
		return sFinal;
	}

	/**
	 * To replace tagged items within a string a tagged item is anything
	 * surrounded by [~ & ~] where the tag is on a worksheet
	 * 
	 * @param sExcelFile
	 *            - the String that contains the Name of the File that holds the
	 *            Replace Values
	 * @param sWorkSheet
	 *            -the String that contains Name of the Worksheet that holds the
	 *            Replace Values
	 * @param sOrig
	 *            - the String that contains the tag(s) to be replaced
	 * @return - the string after the tag(s) have been replaced
	 */

	public String ReplaceSoftTags(String sExcelFile, String sWorkSheet,
			String sOrig) {
		Connection c2 = null;
		Statement stmnt2 = null;
		int i = 92;
		ResultSet rs2 = null;
		// Script1Helper.logInfo("Made to QA Replace");
		String sFinal = sOrig;
		// String sException;
		String sTagged;
		String sReplace = null;

		int iStrtPos;
		int iEndPos;
		int iStrt = 0;

		boolean bContinue = true;
		String sErr = "";
		try {
			try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				c2 = this.ExcelFileConnection(sExcelFile);
				stmnt2 = c2.createStatement();
				String query = "";
				try {
					query = "SELECT * FROM [" + sWorkSheet + "$]";
					rs2 = stmnt2.executeQuery(query);
				} catch (Exception EX) {
					// System.out.println("Ex. in qry " + EXqry.getMessage());
					sErr = "Exception ReplaceSoftTags Query " + query + " --- "
							+ EX.getMessage();
				}
				c2.close();
			} catch (Exception EX) {
				sErr = "Exception Querying ReplaceSoftTags Worksheet "
						+ sWorkSheet + " --- " + EX.getMessage();
				// // System.out.println(sException);

			}

			if (sErr.length() > 0) {
				System.out.println(sErr);
				return sOrig;
			}

			ResultSetMetaData metaData = rs2.getMetaData();
			System.out.println("Replace WorkSheet " + " GetCols");
			ArrayList alCols = new ArrayList();

			/*
			 * * Get the column names.
			 */

			try {
				for (int iX = 0; i < metaData.getColumnCount(); i++) {
					// System.out.println("Get Col: " + (i+1));
					alCols.add(metaData.getColumnLabel(iX + 1).toString());
				}
			} catch (Exception e99) {

			}

			rs2.close();
			stmnt2.close();

			sErr = "";

			String sT = "Orig";
			if (alCols.indexOf(sT) < 0) {
				sErr = " ReplaceSoftTags WorkSheet: " + sWorkSheet.trim()
						+ " -- Needs a Column titled: Orig";

			}

			sT = "Sub";
			if (alCols.indexOf(sT) < 0) {
				if (sErr.length() == 0) {
					sErr = " ReplaceSoftTags WorkSheet: " + sWorkSheet.trim()
							+ " -- Needs a Column titled: Sub";
				} else {
					sErr = sErr + " , a Column titled: Sub";
				}

			}
			rs2.close();
		} catch (Exception e99) {

		}

		// ////

		if (sErr.length() > 0) {
			System.out.println(sErr);
			return sOrig;
		}

		// // System.out.println("Made it to qa.replacetags: " + sOrig);
		sFinal = sOrig;
		while (bContinue == true) {
			bContinue = false;
			// Determine if the string is tagged
			iStrtPos = sOrig.indexOf("[~", iStrt); // Get the Start of the Tag
			iEndPos = sOrig.indexOf("~]", iStrt); // Get the End of the Tag
			iStrt = iEndPos + 2;
			// // System.out.println("in the loop");
			// // System.out.println(iStrtPos);
			// // System.out.println(iEndPos);
			// if both beginning and ending tags exist continue
			if ((iStrtPos >= 0) && (iEndPos >= 0)) {
				// Extract out the tagged item

				sTagged = sOrig.substring((iStrtPos + 2), (iEndPos));
				// // System.out.println(sTagged);
				// Script1Helper.logInfo(sTagged);
				boolean bFnd = false;
				if (bFnd == false) {
					boolean bKeepGoing = true;

					if (bKeepGoing) {
						try {
							Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
							c2 = this.ExcelFileConnection(sExcelFile);
							stmnt2 = c2.createStatement();
							try {
								String query = "SELECT * FROM [" + sWorkSheet
										+ "$] where Orig = " + "'" + sTagged
										+ "'";
								// String query = "Select * from [Replace$]";
								// System.out.println(query);

								rs2 = stmnt2.executeQuery(query);
								// // System.out.println("After Qry Exec");
								rs2.next();
								sReplace = rs2.getString("Sub");
								// // System.out.println("Replace: " +
								// sReplace);
								bFnd = true;
								rs2.close();
								stmnt2.close();
							} catch (Exception EXqry) {
								// System.out.println("Ex. in qry " +
								// EXqry.getMessage());
								bFnd = false;
							}
							c2.close();
						} catch (Exception EX) {
							// // System.out.println(sException);
							bFnd = false;
						}
					}
				}
				if (bFnd == true) {
					sTagged = "[~" + sTagged + "~]";
					// // System.out.println(sTagged);
					// // System.out.println(sReplace);
					sOrig = sOrig.substring(0, (iStrtPos)) + sReplace
							+ sOrig.substring((iEndPos + 2));
					// // System.out.println(sOrig);
					bContinue = true;
					iStrt = 0;
				}
				sFinal = sOrig;

			}
		}
		// System.out.println(sFinal);
		return sFinal;
	}

	// **********************************************************************************
	// ** **
	// ** Method: ReplaceResSetTags **
	// ** Purpose: To replace tagged items within a string **
	// ** a tagged item is anything surrounded as such [*-----*] **
	// ** Uses two ArrayLists to represent the colmn names and data of a row **
	// ** Parameters: **
	// ** 1) ArrayList alCols - Name of the Columns **
	// ** 2) ArrayList alDta - Data for those Columns **
	// ** 3 String sOrig - String to replace all the tags in **
	// ** ** **
	// ** Return: String **
	// ** A string that all the tags have been replaced **
	// ** **
	// **
	// ** Tested and in use **
	// **********************************************************************************
	public String ReplaceResSetTags(ArrayList alCols, ArrayList alDta,
			String sOrig) {
		// Script1Helper.logInfo("Made to QA Replace");
		String sFinal = sOrig;
		String sTagged;
		String sReplace = null;

		int iStrtPos;
		int iEndPos;
		int iStrt = 0;

		boolean bContinue = true;
		// // System.out.println("Made it to qa.replacetags: " + sOrig);
		sFinal = sOrig;
		while (bContinue == true) {
			bContinue = false;
			// Determine if the string is tagged
			iStrtPos = sOrig.indexOf("[*", iStrt); // Get the Start of the Tag
			iEndPos = sOrig.indexOf("*]", iStrt); // Get the End of the Tag
			iStrt = iEndPos + 2;
			// // System.out.println("in the loop");
			// // System.out.println(iStrtPos);
			// // System.out.println(iEndPos);
			// if both beginning and ending tags exist continue
			if ((iStrtPos >= 0) && (iEndPos >= 0)) {
				// Extract out the tagged item

				sTagged = sOrig.substring((iStrtPos + 2), (iEndPos));
				// // System.out.println(sTagged);
				// Script1Helper.logInfo(sTagged);
				boolean bFnd = false;
				if (bFnd == false) {
					boolean bKeepGoing = true;

					if (bKeepGoing) {
						try {
							sReplace = alDta.get(alCols.indexOf(sTagged))
									.toString();
							bFnd = true;
						} catch (Exception e8) {
							bFnd = false;
						}
					}
				}
				if (bFnd == true) {
					sTagged = "[~" + sTagged + "~]";
					// // System.out.println(sTagged);
					// // System.out.println(sReplace);
					sOrig = sOrig.substring(0, (iStrtPos)) + sReplace
							+ sOrig.substring((iEndPos + 2));
					// // System.out.println(sOrig);
					bContinue = true;
					iStrt = 0;
				}
				sFinal = sOrig;

			}
		}
		// System.out.println(sFinal);
		return sFinal;
	}

	/**
	 * Causes the program to pause a specified number of milliseconds
	 * 
	 * @param iMillisecs
	 *            - number of seconds to pause
	 */
	public void NapTime(double dSeconds) {
		try {
			Thread.sleep((long) (dSeconds * 1000));
		} catch (Exception e88) {

		}

	}

	/*
	 * public String[][] getTableArray(String xlFilePath, String sheetName,
	 * String tableName){ String[][] tabArray=null; try{ Workbook workbook =
	 * Workbook.getWorkbook(new File(xlFilePath)); Sheet sheet =
	 * workbook.getSheet(sheetName); int startRow,startCol, endRow,
	 * endCol,ci,cj; Cell tableStart=sheet.findCell(tableName);
	 * 
	 * startRow=tableStart.getRow(); startCol=tableStart.getColumn();
	 * 
	 * Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100,
	 * 64000, false);
	 * 
	 * endRow=tableEnd.getRow(); endCol=tableEnd.getColumn();
	 * System.out.println("startRow="+startRow+", endRow="+endRow+", " +
	 * "startCol="+startCol+", endCol="+endCol); tabArray=new
	 * String[endRow-startRow-1][endCol-startCol-1]; ci=0;
	 * 
	 * for (int i=startRow+1;i<endRow;i++,ci++){ cj=0; for (int
	 * j=startCol+1;j<endCol;j++,cj++){
	 * tabArray[ci][cj]=sheet.getCell(j,i).getContents(); } } } catch (Exception
	 * e) { System.out.println("error in getTableArray()"); }
	 * 
	 * return(tabArray); }
	 */
	/*
	 * public ArrayList<Object[]> LoadArrayListFromWorkSheet(String sExcelFile,
	 * String sWorkSheet) { ArrayList<Object[]> myEntries = new
	 * ArrayList<Object[]>();
	 * 
	 * File inputWorkbook = new File(sExcelFile);
	 * 
	 * Workbook w;
	 * 
	 * String Temp1, Temp2;
	 * 
	 * try {
	 * 
	 * w = Workbook.getWorkbook(inputWorkbook);
	 * 
	 * Sheet sheet = w.getSheet(sWorkSheet);
	 * 
	 * for (int iR = 0; iR < sheet.getRows(); iR++) {
	 * 
	 * // column, row Object cols[] = new Object[sheet.getColumns()]; for(int
	 * iC=0; iC < sheet.getColumns(); iC++) { Cell cell = sheet.getCell(iC, iR);
	 * 
	 * cols[iC] = cell.getContents(); myEntries.add(cols); }
	 * myEntries.add(cols); }
	 * 
	 * }
	 * 
	 * catch (Exception e)
	 * 
	 * {e.printStackTrace();}
	 * 
	 * System.out.println("Successfully read data file");
	 * 
	 * return myEntries; }
	 */

	public ArrayList<Object[]> LoadArrayListFromWorkSheetSql(String sExcelFile,
			String sWorkSheet, ArrayList<String> alColumns, String sWhere) {
		ArrayList<Object[]> myEntries = new ArrayList<Object[]>();

		Connection conn = null;
		Statement stmnt = null;
		ResultSet rs99 = null;
		String sQry = "";
		conn = this.ExcelFileConnection(sExcelFile);
		if (conn == null) {
			System.out.println("No Connection");
			Assert.assertTrue(false, sExcelFile
					+ " connection could not be made");
		}

		System.out.println(sWorkSheet);
		try {
			stmnt = conn.createStatement();
			sQry = "Select * from [" + sWorkSheet + "$]";
			if (sWhere.trim().length() > 0) {
				sQry = sQry + " where " + sWhere;
			}

			System.out.println(sQry);
			rs99 = stmnt.executeQuery(sQry);

			int iRow = -1;
			while (rs99.next()) {
				iRow++;
				System.out.println("Row: " + iRow);
				Object cols[] = new Object[alColumns.size()];
				for (int iCols = 0; iCols < alColumns.size(); iCols++) {
					System.out.println(alColumns.get(iCols));
					String sText = "";

					try {
						sText = rs99.getString(alColumns.get(iCols).toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						sText = "";
						e.printStackTrace();
					}
					try {
						if (sText.compareToIgnoreCase("null") == 0)
							sText = "";
					} catch (Exception e99) {
						sText = "";
					}
					cols[iCols] = sText;
				}
				myEntries.add(cols);
			}
		} catch (Exception e88) {
			System.out.println("ex6: " + e88.getMessage());

		}

		System.out.println("Successfully read data file");

		return myEntries;
	}

	public ArrayList<Object[]> LoadArrayListFromWorkSheet(String sExcelFile,
			String sWorkSheet) {
		ArrayList<Object[]> myEntries = new ArrayList<Object[]>();

		Connection conn = null;
		Statement stmnt = null;
		ResultSet rs99 = null;
		String sQry = "";
		conn = this.ExcelFileConnection(sExcelFile);
		if (conn == null) {
			System.out.println("No Connection");
			Assert.assertTrue(false, sExcelFile
					+ " connection could not be made");
		}

		System.out.println(sWorkSheet);
		try {
			stmnt = conn.createStatement();
			sQry = "Select * from [" + sWorkSheet + "$]";

			System.out.println(sQry);
			rs99 = stmnt.executeQuery(sQry);
			ResultSetMetaData metaData = rs99.getMetaData();
			int iCls = metaData.getColumnCount();
			int iRow = -1;
			while (rs99.next()) {
				iRow++;
				System.out.println("Row: " + iRow);
				Object cols[] = new Object[iCls];
				for (int iCols = 0; iCols < iCls; iCols++) {
					System.out.println(iCols);
					String sText = "";

					try {
						sText = rs99.getString(iCols);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						sText = "";
						e.printStackTrace();
					}
					try {
						if (sText.compareToIgnoreCase("null") == 0)
							sText = "";
					} catch (Exception e99) {
						sText = "";
					}
					cols[iCols] = sText;
				}
				myEntries.add(cols);
			}
		} catch (Exception e88) {
			System.out.println("ex6: " + e88.getMessage());

		}

		System.out.println("Successfully read data file");

		return myEntries;
	}

	public String GetMachineName() {
		return this.ReplaceHardTags("[~Machine~]");
	}

	public String GetMAC() {
		return this.ReplaceHardTags("[~MAC~]");
	}
	
	public String GetWorkingDirectory()
	{
		return FileSystems.getDefault().getPath(".").toAbsolutePath().toString();
	}

	public String GetIP() {
		return this.ReplaceHardTags("[~IP~]");
	}

	public String GetUserID() {
		return this.ReplaceHardTags("[~UserID~]");
	}

	public String GetOS() {
		return this.ReplaceHardTags("[~OS~]");
	}

	public Object WSsetInput(String input) {
		String sDQt = "" + (char) 34;
		String sSQt = "" + (char) 130;

		String iRtn = "null";
		if (input == null) {
			iRtn = "null";
		}
		if (input.trim().length() == 0) {
			iRtn = "null";
		} else {
			try {
				int iX = Integer.parseInt(input);
				return iX;
			} catch (Exception e77) {
				String s1 = "" + '"';
				input = input.replaceAll(s1, "");
				iRtn = input;
			}
		}
		return iRtn;

	}

	public ResultSet GetExcelResultSet(String sExcelFile, String sWorkSheet,
			String sQuery) {
		Connection con1 = null;
		Statement stmt1 = null;
		ResultSet ResSet = null;
		// String sQry = null;
		String sErr = "";

		con1 = this.ExcelFileConnection(sExcelFile);
		if (con1 == null) {
			System.out.println("No Connection");
			Assert.assertTrue(false, sExcelFile
					+ " connection could not be made");
		}

		try {
			stmt1 = con1.createStatement();
			if (sQuery.trim().length() == 0) {
				sQuery = "Select * from [" + sWorkSheet + "$]";
			}

			System.out.println("Query is: " + sQuery);

			ResultSet rs99 = stmt1.executeQuery(sQuery);
			return rs99;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getCause());
			return null;
		}
	}

	public ResultSet GetOracleResultSet(String sDbHost, String sPort,
			String sServiceName, String sUser, String sPwd, String sQuery) {
		String sDbName = sDbHost + ":" + sPort + "/" + sServiceName;
		Connection Conn = this.OracleConnection(sDbName, sUser, sPwd);
		try {
			Statement stmnt = Conn.createStatement();
			return stmnt.executeQuery(sQuery);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public String GetClipBoardText() {
		String result = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		// odd: the Object param of getContents is not currently used
		Transferable contents = clipboard.getContents(null);
		boolean hasTransferableText = (contents != null)
				&& contents.isDataFlavorSupported(DataFlavor.stringFlavor);
		if (hasTransferableText) {
			try {
				result = (String) contents
						.getTransferData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException ex) {
				// highly unlikely since we are using a standard DataFlavor
				System.out.println(ex);
				ex.printStackTrace();
			} catch (IOException ex) {
				System.out.println(ex);
				ex.printStackTrace();
			}
		}
		return result;

	}

	public boolean SetClipBoardText(String sText) {
		try {
			StringSelection stringSelection = new StringSelection(sText);
			Clipboard clipboard = Toolkit.getDefaultToolkit()
					.getSystemClipboard();
			clipboard.setContents(stringSelection, this);
			return true;
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public String ConvertBase62(double dTmp) {

		int iChkPt = 0;
		double dChkVal = 0;
		String sReplace = "0";
		while (dChkVal <= dTmp) {
			dChkVal = Math.pow(62, iChkPt);
			if (dChkVal > dTmp)
				break;
			iChkPt++;

			sReplace = sReplace + "0";
		}
		// iChkPt--;
		// sReplace = sReplace.substring(1);

		double dCntDwn = dTmp;
		ArrayList<String> base62 = new ArrayList<String>(
				Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
						"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
						"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
						"w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G",
						"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
						"S", "T", "U", "V", "V", "X", "Y", "Z"));
		char saReplace[] = sReplace.toCharArray();
		String sTemp = "";
		for (int iFg = iChkPt - 1; iFg > 0; iFg--) {
			double dMax = Math.pow(62, iFg);
			System.err.println(sTemp + " " + iFg + " - " + dCntDwn + " / "
					+ dMax);

			if (dMax > dCntDwn)
				continue;
			int iQtn = (int) (dCntDwn / dMax);
			System.err
					.println(sTemp + " " + iFg + " - " + dCntDwn + " / " + dMax
							+ " - " + iQtn + " -- "
							+ base62.get(iQtn).charAt(0));

			dCntDwn = dCntDwn - (dMax * iQtn);
			saReplace[(iChkPt - 1) - iFg] = base62.get(iQtn).charAt(0);
		}
		System.err.println(sTemp + " 0  - " + dCntDwn + " -- "
				+ base62.get((int) dCntDwn).charAt(0));

		saReplace[iChkPt] = base62.get((int) dCntDwn).charAt(0);

		sReplace = String.copyValueOf(saReplace);
		return sReplace;

	}

	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		// TODO Auto-generated method stub

	}
	
}
