package api;

import api.*;
import org.junit.jupiter.api.*;


import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DWGraph_DSTest {

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





}