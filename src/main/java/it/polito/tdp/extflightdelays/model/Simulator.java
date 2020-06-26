package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulator {

	// CODA DEGLI EVENTI
	private PriorityQueue<Event> queue;

	// PARAMETRI DI SIMULAZIONE
	private int T = 100;
	private int G = 30;

	// MODELLO DEL MONDO
	private Graph<String, DefaultWeightedEdge> grafo;
	private int giorni;

	// VALORI DA CALCOLARE
	private Map<String, Integer> statoTuristi;

	public Simulator(Graph<String, DefaultWeightedEdge> grafo) {
		this.grafo = grafo;
	}

	public Map<String, Integer> getStatoTuristi() {
		return statoTuristi;
	}

	public void setT(int t) {
		T = t;
	}

	public void setG(int g) {
		G = g;
	}

	public void init(String partenza) {
		this.queue = new PriorityQueue<>();
		this.statoTuristi = new HashMap<>();
		for (String stato : this.grafo.vertexSet()) {
			if (stato.equals(partenza))
				this.statoTuristi.put(stato, this.T);
			else
				this.statoTuristi.put(stato, 0);
		}

		this.giorni = 1;

		for (int i = 0; i < this.T; i++) {
			Event e = new Event(partenza, 1);
			this.queue.add(e);
		}
	}

	public void run() {
		while (!this.queue.isEmpty() && giorni <= this.G) {
			Event e = this.queue.poll();
			System.out.println(e);
			this.processEvent(e);
		}
	}

	private void processEvent(Event e) {
		giorni = e.getTempo();
		if (giorni <= this.G) {
			// tolgo il turista che sta partendo
			this.statoTuristi.replace(e.getStato(), statoTuristi.get(e.getStato()) - 1);
			String assegnato = assegnaStato(e);
			if (assegnato != null) {
				this.statoTuristi.replace(assegnato, statoTuristi.get(assegnato) + 1);
				queue.add(new Event(assegnato, e.getTempo() + 1));
			}
		}
	}

	private String assegnaStato(Event e) {
		Double sommaPesi = 0.0;
		for (String successivo : Graphs.successorListOf(this.grafo, e.getStato())) {
			sommaPesi += this.grafo.getEdgeWeight(this.grafo.getEdge(e.getStato(), successivo));
		}

		String assegnato = null;
		Double cumulativo = 0.0;
		Double probabilita = Math.random();
		System.out.println("Probabilita: " + probabilita);

		for (String successivo : Graphs.successorListOf(this.grafo, e.getStato())) {
			Double peso = this.grafo.getEdgeWeight(this.grafo.getEdge(e.getStato(), successivo));
			Double p = peso / sommaPesi;
			cumulativo += p;
			System.out.println("p: " + p);
			System.out.println("cumulativo: " + cumulativo);
			if (probabilita < cumulativo) {
				assegnato = successivo;
				break;
			}
		}
		System.out.println(assegnato);
		return assegnato;
	}

}
