package com.companyname.automation.commontools;

/**
 * The StopWatch Class allows you to determine elapsed time from a given time
 * @author Tim Desktop
 *
 */
public class StopWatch {

	private long startTime = 0;
	private long stopTime = 0;
	private boolean running = false;

	/**
	 * Starts the stopwatch
	 */
	public void start() {
		this.startTime = System.currentTimeMillis();
		this.running = true;
	}

	/**
	 *  Stops the stopwatch
	 */
	public void stop() {
		this.stopTime = System.currentTimeMillis();
		this.running = false;
	}

	// elaspsed time in milliseconds
	/**
	 * The Method returns the elapsed time in milliseconds
	 * if the stopwatch is running then the current elapsed time is given.
	 * if the stopwatch is not running then the elapsed time when stopwatch is stopped is given
	 * @return a Long that returns the number elapsed time in milliseconds
	 */
	public long getElapsedTime() {
		long elapsed;
		if (running) {
			elapsed = (System.currentTimeMillis() - startTime);
		} else {
			elapsed = (stopTime - startTime);
		}
		return elapsed;
	}

	// elaspsed time in seconds
	/**
	 * The Method returns the elapsed time in seconds
	 * if the stopwatch is running then the current elapsed time is given.
	 * if the stopwatch is not running then the elapsed time when stopwatch is stopped is given
	 * @return a double that returns the number elapsed time in seconds
	 */
	public double getElapsedTimeSecs() {
		double elapsed;
		if (running) {
			elapsed = ((System.currentTimeMillis() - startTime) / 1000.000);
		} else {
			elapsed = ((stopTime - startTime) / 1000.000);
		}
		return elapsed;
	}

}