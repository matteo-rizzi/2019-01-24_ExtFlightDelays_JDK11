package it.polito.tdp.extflightdelays.model;

public class StatoVicino implements Comparable<StatoVicino>{

	private String vicino;
	private Integer peso;

	public StatoVicino(String vicino, Integer peso) {
		super();
		this.vicino = vicino;
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Stato: " + this.vicino + " - peso: " + this.peso;
	}

	@Override
	public int compareTo(StatoVicino other) {
		return other.peso.compareTo(this.peso);
	}

}
