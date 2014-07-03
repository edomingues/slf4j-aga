slf4j-aga
=========

This is an Android Library Project with a Simple Logging Facade for Java (SLF4J) implementation to send logs to Google Analytics.

GitHub: https://github.com/edomingues/slf4j-aga

Usage
-----

To use this library:

1. first, add it to your Android Project, using the [command line](http://developer.android.com/tools/projects/projects-cmdline.html#ReferencingLibraryProject) or [eclipse](http://developer.android.com/tools/projects/projects-eclipse.html#ReferencingLibraryProject);
2. then, on your [Application](http://developer.android.com/reference/android/app/Application.html) subclass, call `GoogleAnalyticsLogger.setContext` method passing the application context as argument. See the following code as an example. If you do not have a subclass, create it and do not forget to add its name in your AndroidManifest.xml's <application> tag, as described [here](http://developer.android.com/reference/android/app/Application.html).


	```java
	public class MyApplication extends Application {
	    public void onCreate() {
		super.onCreate();
	 
		GoogleAnalyticsLogger.setContext(this); // Set the context to be used to get the Google Analytics Tracker
		 
		if(BuildConfig.DEBUG) {
		    GoogleAnalyticsLogger.setCurrentLogLevel(LogLevel.TRACE); // Set the log level to print all logs, default is INFO
		    GoogleAnalyticsLogger.setGoogleAnalyticsEnabled(false); // Disable reporting logs to Google Analytics, print only locally. Default is true.
		}
		
		(...)
	 ```


3. Optionally, you can also configure the level of logging and disable sending logs to Google Analytics, as shown on the above code.

4. Besides this, create the file `res/values/analytics.xml` with the following content, as described [here](https://developers.google.com/analytics/devguides/collection/android/v3/#analytics-xml). Replace the placeholder ID with your tracking ID.

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources xmlns:tools="https://schemas.android.com/tools" tools:ignore="TypographyDashes">
 
    <!-- Replace placeholder ID with your tracking ID -->
    <string name="ga_trackingId">UA-XXXX-Y</string>
 
    <!-- Enable automatic activity tracking -->
    <bool name="ga_autoActivityTracking">true</bool>
 
    <!-- Enable automatic exception tracking -->
    <bool name="ga_reportUncaughtExceptions">true</bool>
 
</resources>
```

Log Uncaught Exceptions
-----------------------

To log [uncaught exception](https://developers.google.com/analytics/devguides/collection/android/v3/exceptions) add the following code to the `onCreate` method of the `Application` subclass:

```java
public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        
        (...)
        
        ExceptionReporter myHandler = new ExceptionReporter(EasyTracker.getInstance(this),
    		                                                GAServiceManager.getInstance(),             
    		                                                Thread.getDefaultUncaughtExceptionHandler(),
    		                                                this);       
    		
    	myHandler.setExceptionParser(new AnalyticsExceptionParser());

    	Thread.setDefaultUncaughtExceptionHandler(myHandler);
    	
    	(...)
```

