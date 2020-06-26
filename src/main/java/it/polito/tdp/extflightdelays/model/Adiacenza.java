package it.polito.tdp.extflightdelays.model;

public class Adiacenza {

	private String primo;
	private String secondo;
	private Integer peso;

	public Adiacenza(String primo, String secondo, Integer peso) {
		super();
		this.primo = primo;
		this.secondo = secondo;
		this.peso = peso;
	}

	public String getPrimo() {
		return primo;
	}

	public void setPrimo(String primo) {
		this.primo = primo;
	}

	public String getSecondo() {
		return secondo;
	}

	public void setSecondo(String secondo) {
		this.secondo = secondo;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

}
