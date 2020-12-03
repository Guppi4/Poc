package api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

public class DWGraph_DS implements directed_weighted_graph {
    private HashMap<Integer,node_data> graph;
    private NodeData node;
    private int edgeSize;
    private int mc;


    public DWGraph_DS(){
        graph = new HashMap<>();
        edgeSize = 0;
        mc = 0;
    }


    @Override
    public node_data getNode(int key) {
        return this.graph.get(key);
    }

    @Override
    public edge_data getEdge(int src, int dest) {
        if(graph.containsKey(src) && graph.containsKey(dest)){
            node = (NodeData) graph.get(src);
           return node.getEdge(dest);
        }
        return null;
    }

    @Override
    public void addNode(node_data n) {
        if(graph.containsKey(n.getKey()))
            return;
        graph.put(n.getKey(),n);
        mc ++;
    }

        @Override
    public void connect(int src, int dest, double w) {
        if(w < 0  || src == dest || getEdge(src,dest)!=null)
            return;
        if(graph.containsKey(src)&&graph.containsKey(dest)){
            node = (NodeData)graph.get(src);
            node.createEdge(src,dest,w);
            edgeSize ++;
            mc ++;
        }
    }

    @Override
    public Collection<node_data> getV() {
        return this.graph.values();
    }

    @Override
    public Collection<edge_data> getE(int node_id) {
        if(!graph.containsKey(node_id))
        return null;
        node = (NodeData)graph.get(node_id);
        return node.getNi() ;
    }

    @Override
    public node_data removeNode(int key) {
        if(!graph.containsKey(key))
            return null;

        node = (NodeData) getNode(key);
        for(node_data i : this.getV()){
            NodeData s = (NodeData)i;
            if( s.getNi().contains(s)){
                s.removeEdge(key);
                edgeSize --;
                mc ++;
            }
        }
        edgeSize -= node.getNi().size();
        mc += node.getNi().size();
        return graph.remove(key);
    }

    @Override
    public edge_data removeEdge(int src, int dest) {
        if(getEdge(src,dest) == null)
            return null;
        node = (NodeData)getNode(src);
        edgeSize --;
        mc ++;
        return node.removeEdge(dest);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DWGraph_DS that = (DWGraph_DS) o;
        return edgeSize == that.edgeSize &&
                Objects.equals(graph, that.graph) ;
    }

    @Override
    public int nodeSize() {
        return graph.size();
    }

    @Override
    public int edgeSize() {
        return edgeSize;
    }

    @Override
    public int getMC() {
        return mc;
    }


}
