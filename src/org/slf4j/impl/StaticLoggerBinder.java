package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

import edomingues.slf4j.impl.GoogleAnalyticsLoggerFactory;
import android.util.Log;

/**
 * The binding of {@link LoggerFactory} class with an actual instance of
 * {@link ILoggerFactory} is performed using information returned by this class.
 *
 */
public class StaticLoggerBinder implements LoggerFactoryBinder
{

	/**
	 * The unique instance of this class.
	 */
	private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

	/**
	 * Return the singleton of this class.
	 *
	 * @return the StaticLoggerBinder singleton
	 */
	public static final StaticLoggerBinder getSingleton()
	{
		Log.d("StaticLoggerBinder", "getSingleton");
		return SINGLETON;
	}

	/**
	 * Declare the version of the SLF4J API this implementation is compiled
	 * against. The value of this field is usually modified with each release.
	 */
	// to avoid constant folding by the compiler, this field must *not* be final
	public static String REQUESTED_API_VERSION = "1.7.5"; // !final

	private static final String loggerFactoryClassStr = GoogleAnalyticsLoggerFactory.class.getName();

	/**
	 * The ILoggerFactory instance returned by the {@link #getLoggerFactory}
	 * method should always be the same object
	 */
	private final ILoggerFactory loggerFactory;

	private StaticLoggerBinder()
	{
		loggerFactory = new GoogleAnalyticsLoggerFactory();
	}
	
	public ILoggerFactory getLoggerFactory()
	{
		Log.d("StaticLoggerBinder", "getLoggerFactory");
		return loggerFactory;
	}

	public String getLoggerFactoryClassStr()
	{
		return loggerFactoryClassStr;
	}
}
