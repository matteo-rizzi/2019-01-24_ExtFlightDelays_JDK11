package it.polito.tdp.extflightdelays.model;

public class Event implements Comparable<Event>{

	private String stato;
	private int tempo;

	public Event(String stato, int tempo) {
		super();
		this.stato = stato;
		this.tempo = tempo;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	@Override
	public String toString() {
		return "Event [stato=" + stato + ", tempo=" + tempo + "]";
	}

	@Override
	public int compareTo(Event other) {
		return this.tempo - other.getTempo();
	}

}
