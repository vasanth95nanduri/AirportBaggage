package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.model.Edge;
import com.model.Graph;
import com.model.Vertex;


public class TestBaggageRouting {

	private List<Vertex> nodes;
	private List<Edge> edges;
	private Graph graph;
	private BaggageRoutingMain route;
	private Map<String,Vertex[]> bags;
	private Map<String,Vertex> departures;

	public static void main(String[] args) {
		new TestBaggageRouting().testExcute();
	}
	
	public void testExcute() {
		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		
		nodes.add(new Vertex("Concourse_A_Ticketing", "Concourse_A_Ticketing"));
		nodes.add( new Vertex("BaggageClaim", "BaggageClaim"));
		nodes.add(new Vertex("A1", "A1"));
		nodes.add(new Vertex("A2", "A2"));
		nodes.add(new Vertex("A3", "A3"));
		nodes.add(new Vertex("A4", "A4"));
		nodes.add(new Vertex("A5", "A5"));
		nodes.add(new Vertex("A6", "A6"));
		nodes.add(new Vertex("A7", "A7"));
		nodes.add(new Vertex("A8", "A8"));
		nodes.add(new Vertex("A9", "A9"));
		nodes.add(new Vertex("A10", "A10"));
		
		
	
		addEdges();
		

		graph = new Graph(nodes, edges);
		route = new BaggageRoutingMain(graph);
		
		departures = new HashMap<>();
		departures.put("UA11", nodes.get(1));
		departures.put("UA12", nodes.get(1));
		departures.put("UA13", nodes.get(2));
		departures.put("UA14", nodes.get(2));
		departures.put("UA10", nodes.get(1));
		departures.put("UA15", nodes.get(2));
		departures.put("UA16", nodes.get(3));
		departures.put("UA17", nodes.get(4));
		departures.put("UA18", nodes.get(5));
		departures.put("ARRIVAL", nodes.get(11));
		
		bags = new LinkedHashMap<>();
		bags.put("0001", new Vertex[]{nodes.get(0), departures.get("UA12")} );
		bags.put("0002", new Vertex[]{nodes.get(5), departures.get("UA17")} );
		bags.put("0003", new Vertex[]{nodes.get(2), departures.get("UA10")} );
		bags.put("0004", new Vertex[]{nodes.get(8), departures.get("UA18")} );
		bags.put("0005", new Vertex[]{nodes.get(7), departures.get("ARRIVAL")} );
		
		for (Map.Entry<String,Vertex[]> bag : bags.entrySet()) {
			getPathDistance(bag);
		}

	}
	
	private void getPathDistance(Map.Entry<String,Vertex[]> bag) {
		String bagName=bag.getKey();
		Vertex[] vertexes=bag.getValue();
		route.execute(vertexes[0]);
		Map<Integer,LinkedList<Vertex>> path = route.getPath(vertexes[1]);

	

		for (Map.Entry<Integer,LinkedList<Vertex>> pathDistance : path.entrySet()) {
			System.out.println(bagName+"\t"+pathDistance.getValue().toString()+"\t"+pathDistance.getKey());
		}
	}
	
	private void addEdges(){
		//Add Edges
				addLane("Edge_0", 0, 5, 5);
				addLane("Edge_1", 5, 11, 5);
				addLane("Edge_2", 5, 10, 4);
				addLane("Edge_3", 5, 1, 6);
				addLane("Edge_4", 1, 2, 1);
				addLane("Edge_5", 2, 3, 1);
				addLane("Edge_6", 3, 4, 1);
				addLane("Edge_7", 10, 9, 1);
				addLane("Edge_8", 9, 8, 1);
				addLane("Edge_9", 8, 7, 1);
				addLane("Edge_10", 7, 6, 1);
				
	}

	private void addLane(String laneId, int sourceLocNo, int destLocNo,
			int duration) {
		
		//Bidirectional Edges
		Edge lane = new Edge(laneId, nodes.get(sourceLocNo),
				nodes.get(destLocNo), duration);
		edges.add(lane);
		
		lane = new Edge(laneId, nodes.get(destLocNo),
				nodes.get(sourceLocNo), duration);
		edges.add(lane);
	}
}