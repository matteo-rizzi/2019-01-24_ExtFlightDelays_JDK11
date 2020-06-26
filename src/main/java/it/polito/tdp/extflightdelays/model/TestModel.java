package it.polito.tdp.extflightdelays.model;

import java.util.Map;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model m = new Model();
		m.creaGrafo();
		
		Map<String, Integer> result = m.simula(50, 20, "NY");
		
		for(String stato : result.keySet()) {
			System.out.println(stato + " " + result.get(stato));
		}
	}

}
