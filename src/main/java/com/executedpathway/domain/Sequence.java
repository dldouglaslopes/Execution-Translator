package com.executedpathway.domain;

import java.io.Serializable;
import java.net.URL;

import javax.persistence.Entity;

@Entity
public class Sequence implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private URL next;
	private URL previous;

	public Sequence() {}

	public Sequence(URL next, URL previous) {
		super();
		this.next = next;
		this.previous = previous;
	}

	public URL getNext() {
		return next;
	}

	public void setNext(URL next) {
		this.next = next;
	}

	public URL getPrevious() {
		return previous;
	}

	public void setPrevious(URL previous) {
		this.previous = previous;
	}
	
	
}
