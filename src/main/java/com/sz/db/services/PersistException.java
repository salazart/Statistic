package com.sz.db.services;
public class PersistException extends Exception{
	public PersistException() { super(); }
	public PersistException(String text) { super(text); }
	public PersistException(Exception e) { super(e); }
}
