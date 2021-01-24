package com.companyname.automation.commontools;
public class IgnoreLinePart {

	private int iLine;
	private int iStartPos;
	private int iEndPos;

	public IgnoreLinePart(int iLine, int iStartPos, int iEndPos) {
		super();
		this.iLine = iLine;
		this.iStartPos = iStartPos;
		this.iEndPos = iEndPos;
	}

	public int getiLine() {
		return iLine;
	}

	public void setiLine(int iLine) {
		this.iLine = iLine;
	}

	public int getiStartPos() {
		return iStartPos;
	}

	public void setiStartPos(int iStartPos) {
		this.iStartPos = iStartPos;
	}

	public int getiEndPos() {
		return iEndPos;
	}

	public void setiEndPos(int iEndPos) {
		this.iEndPos = iEndPos;
	}

}
