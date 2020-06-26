package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private ExtFlightDelaysDAO dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	public Simulator sim;
	
	public Model() {
		this.dao = new ExtFlightDelaysDAO();
	}

	public void creaGrafo() {
		this.grafo = new DefaultDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		// aggiungo i vertici
		Graphs.addAllVertices(this.grafo, this.dao.getStates());
		
		// aggiungo gli archi
		for(Adiacenza a : this.dao.getAdiacenze()) {
			Graphs.addEdge(this.grafo, a.getPrimo(), a.getSecondo(), a.getPeso());
		}
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<String> getStates() {
		return this.dao.getStates();
	}
	
	public List<StatoVicino> visualizzaVelivoli(String stato) {
		List<StatoVicino> vicini = new ArrayList<>();
		for(String uscente : Graphs.successorListOf(this.grafo, stato)) {
			StatoVicino vicino = new StatoVicino(uscente,  (int) this.grafo.getEdgeWeight(this.grafo.getEdge(stato, uscente)));
			vicini.add(vicino);
		}
		
		Collections.sort(vicini);
		
		return vicini;
	}
	
	public Map<String, Integer> simula(int T, int G, String partenza) {
		this.sim = new Simulator(this.grafo);
		this.sim.setG(G);
		this.sim.setT(T);
		this.sim.init(partenza);
		this.sim.run();
		
		return this.sim.getStatoTuristi();
	}
	
}
