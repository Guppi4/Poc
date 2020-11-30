package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DWGraph_AlgoTest {
    directed_weighted_graph g = new DWGraph_DS();
    node_data node = new NodeData();
    node_data node1 = new NodeData();
    node_data node2 = new NodeData();
    node_data node3 = new NodeData();
    node_data node4 = new NodeData();


    @Test
    void init() {
    }

    @Test
    void getGraph() {
    }

    @Test
    void copy() {
    }

    @Test
    void isConnected() {
        directed_weighted_graph g = new DWGraph_DS();
        node_data node = new NodeData();
        node_data node1 = new NodeData();
        node_data node2 = new NodeData();
        node_data node3 = new NodeData();
        node_data node4 = new NodeData();
        g.addNode(node);
        g.addNode(node1);
        g.addNode(node2);
        g.addNode(node3);
        g.connect(node.getKey(),node1.getKey(),2);
        g.connect(node1.getKey(),node2.getKey(),2);
        g.connect(node2.getKey(),node3.getKey(),2);
        g.connect(node3.getKey(),node.getKey(),2);
        DWGraph_Algo graph_algo = new DWGraph_Algo();
        graph_algo.init(g);
        boolean flag = graph_algo.isConnected();
        assertTrue(flag);
        g.addNode(node4);
         flag = graph_algo.isConnected();
         assertFalse(flag);
    }

    @Test
    void shortestPathDist() {
        directed_weighted_graph g = new DWGraph_DS();
        node_data node = new NodeData();
        node_data node1 = new NodeData();
        node_data node2 = new NodeData();
        node_data node3 = new NodeData();
        node_data node4 = new NodeData();
        g.addNode(node);
        g.addNode(node1);
        g.addNode(node2);
        g.addNode(node3);
        g.addNode(node4);
        g.connect(node.getKey(),node1.getKey(),10);
        g.connect(node1.getKey(),node2.getKey(),7);
        g.connect(node1.getKey(),node4.getKey(),20);
        g.connect(node2.getKey(),node3.getKey(),6);
        g.connect(node3.getKey(),node4.getKey(),5);
        g.connect(node2.getKey(),node4.getKey(),10);
        DWGraph_Algo graph_algo = new DWGraph_Algo();
        graph_algo.init(g);
        double dist = graph_algo.shortestPathDist(node.getKey(),node4.getKey());
        System.out.println(dist);
        assertEquals(dist,27);
    }

    @Test
    void shortestPath() {
        directed_weighted_graph g = new DWGraph_DS();
        node_data node = new NodeData();
        node_data node1 = new NodeData();
        node_data node2 = new NodeData();
        node_data node3 = new NodeData();
        node_data node4 = new NodeData();
        g.addNode(node);
        g.addNode(node1);
        g.addNode(node2);
        g.addNode(node3);
        g.addNode(node4);
        g.connect(node.getKey(),node1.getKey(),10);
        g.connect(node1.getKey(),node2.getKey(),7);
        g.connect(node1.getKey(),node4.getKey(),20);
        g.connect(node2.getKey(),node3.getKey(),6);
        g.connect(node3.getKey(),node4.getKey(),5);
        g.connect(node2.getKey(),node4.getKey(),10);
        DWGraph_Algo graph_algo = new DWGraph_Algo();
        graph_algo.init(g);
        List<node_data> l = graph_algo.shortestPath(node.getKey(),node4.getKey());
        assertTrue(l.size() == 4);
        l.stream().forEach(x -> System.out.print("-->"+x.getKey()));

    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}