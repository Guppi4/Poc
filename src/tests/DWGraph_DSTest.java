package tests;

import api.*;
import org.junit.jupiter.api.*;


import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DWGraph_DSTest {
    private static Random _rnd = null;

    directed_weighted_graph g = new DWGraph_DS();
    node_data node = new NodeData();
    node_data node1 = new NodeData();
    node_data node2 = new NodeData();
    node_data node3 = new NodeData();
    node_data node4 = new NodeData();


    @Test
    void nodeSize() {
        g.addNode(node);
        g.addNode(node1);
        g.addNode(node1);
        g.removeNode(node3.getKey());
        g.removeNode(node1.getKey());
        g.removeNode(node1.getKey());
        int s = g.nodeSize();
        assertEquals(1,s);

    }

    @Test
    void edgeSize() {
        g.addNode(node);
        g.addNode(node1);
        g.addNode(node2);
        g.addNode(node3);
        g.connect(node.getKey(),node1.getKey(),1);
        g.connect(node.getKey(),node2.getKey(),2);
        g.connect(node.getKey(),node3.getKey(),3);
        g.connect(node.getKey(),node.getKey(),1);
        int e_size =  g.edgeSize();
        assertEquals(3, e_size);
        edge_data w03 = g.getEdge(node.getKey(),node3.getKey());
        edge_data w30 = g.getEdge(node3.getKey(),node.getKey());
        assertNotEquals(w03, w30);
        assertEquals(w03.getWeight(), 3);
    }

    @Test
    void getV() {
        directed_weighted_graph g = new DWGraph_DS();
        g.addNode(node);
        g.addNode(node1);
        g.addNode(node2);
        g.addNode(node3);
        g.connect(node.getKey(),node1.getKey(),1);
        g.connect(node.getKey(),node2.getKey(),2);
        g.connect(node.getKey(),node3.getKey(),3);
        g.connect(node.getKey(),node.getKey(),1);
        Collection<node_data> v = g.getV();
        Iterator<node_data> iter = v.iterator();
        while (iter.hasNext()) {
            node_data n = iter.next();
            assertNotNull(n);
        }
    }

    @Test
    void connect() {
        directed_weighted_graph g = new DWGraph_DS();
        g.addNode(node);
        g.addNode(node1);
        g.addNode(node2);
        g.addNode(node3);
        g.connect(node.getKey(),node1.getKey(),1);
        g.connect(node.getKey(),node2.getKey(),2);
        g.connect(node.getKey(),node3.getKey(),3);
        g.removeEdge(node.getKey(),node1.getKey());
        assertNull(g.getEdge(node.getKey(),node1.getKey()));
        g.connect(node.getKey(),node1.getKey(),1);
        edge_data w = g.getEdge(node.getKey(),node1.getKey());
        assertEquals(w.getWeight(),1);
        assertNull(g.getEdge(node1.getKey(),node.getKey()));
    }

    @Test
    void removeNode() {
        directed_weighted_graph g = new DWGraph_DS();
        g.addNode(node);
        g.addNode(node1);
        g.addNode(node2);
        g.addNode(node3);
        g.connect(node.getKey(),node1.getKey(),1);
        g.connect(node.getKey(),node2.getKey(),2);
        g.connect(node.getKey(),node3.getKey(),3);
        g.removeNode(node4.getKey());
        g.removeNode(node.getKey());
        assertNull(g.getEdge(node.getKey(),node1.getKey()));
        assertNull(g.getEdge(node1.getKey(),node.getKey()));
        int e = g.edgeSize();
        assertEquals(0,e);
        assertEquals(3,g.nodeSize());
    }

    @Test
    void removeEdge() {
        directed_weighted_graph g = new DWGraph_DS();
        g.addNode(node);
        g.addNode(node1);
        g.addNode(node2);
        g.addNode(node3);
        g.connect(node.getKey(),node1.getKey(),1);
        g.connect(node.getKey(),node2.getKey(),2);
        g.connect(node.getKey(),node3.getKey(),3);
        g.removeEdge(node.getKey(),node1.getKey());
        edge_data w = g.getEdge(node.getKey(),node1.getKey());
        assertNull(w);
    }


    public static directed_weighted_graph graph_creator(int v_size, int e_size, int seed) {
        directed_weighted_graph g = new DWGraph_DS();
        _rnd = new Random(seed);
        for(int i=0;i<v_size;i++) {
            node_data node = new NodeData();
            g.addNode(node);
        }
        // Iterator<node_data> itr = V.iterator(); // Iterator is a more elegant and generic way, but KIS is more important
        int[] nodes = nodes(g);
        while(g.edgeSize() < e_size) {
            int a = nextRnd(0,v_size);
            int b = nextRnd(0,v_size);
            int i = nodes[a];
            int j = nodes[b];
            double w = _rnd.nextDouble();
            g.connect(i,j, w);
        }
        return g;
    }
    private static int nextRnd(int min, int max) {
        double v = nextRnd(0.0+min, (double)max);
        int ans = (int)v;
        return ans;
    }
    private static double nextRnd(double min, double max) {
        double d = _rnd.nextDouble();
        double dx = max-min;
        double ans = d*dx+min;
        return ans;
    }
    /**
     * Simple method for returning an array with all the node_data of the graph,
     * Note: this should be using an Iterator<node_edge> to be fixed in Ex1
     * @param g
     * @return
     */

    private static int[] nodes(directed_weighted_graph g) {
        int size = g.nodeSize();
        Collection<node_data> V = g.getV();
        node_data[] nodes = new node_data[size];
        V.toArray(nodes); // O(n) operation
        int[] ans = new int[size];
        for(int i=0;i<size;i++) {ans[i] = nodes[i].getKey();}
        Arrays.sort(ans);
        return ans;
    }

}