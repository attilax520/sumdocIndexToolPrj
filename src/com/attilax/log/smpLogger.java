package com.attilax.log;

import com.attilax.log.util.Marker;

public class smpLogger implements Logger ,com.attilax.log.util.Logger {

	@Override
	public void log(String string) {
		System.out.println(string);

	}

	@Override
	public void err(Exception e) {
		System.out.println(e);
		
	}

	@Override
	public void log(Exception e) {
		System.out.println(e);
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTraceEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void trace(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(String format, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(String format, Object... arguments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(String msg, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTraceEnabled(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void trace(Marker marker, String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(Marker marker, String format, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(Marker marker, String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(Marker marker, String format, Object... argArray) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(Marker marker, String msg, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void debug(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String format, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String format, Object... arguments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String msg, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDebugEnabled(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void debug(Marker marker, String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(Marker marker, String format, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(Marker marker, String format, Object... arguments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(Marker marker, String msg, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInfoEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void info(String msg) {
		System.out.println(msg);
		
	}

	@Override
	public void info(String format, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String format, Object... arguments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String msg, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInfoEnabled(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void info(Marker marker, String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(Marker marker, String format, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(Marker marker, String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(Marker marker, String format, Object... arguments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(Marker marker, String msg, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isWarnEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void warn(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(String format, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(String format, Object... arguments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(String msg, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isWarnEnabled(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void warn(Marker marker, String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(Marker marker, String format, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(Marker marker, String format, Object... arguments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(Marker marker, String msg, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isErrorEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void error(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String format, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String format, Object... arguments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String msg, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isErrorEnabled(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void error(Marker marker, String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(Marker marker, String format, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(Marker marker, String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(Marker marker, String format, Object... arguments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(Marker marker, String msg, Throwable t) {
		// TODO Auto-generated method stub
		
	}

}
