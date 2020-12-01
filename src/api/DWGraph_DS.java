package api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class DWGraph_DS implements directed_weighted_graph {
    private HashMap<Integer,node_data> Nodes;
    private HashMap<Integer, HashMap<Integer, edge_data>> Edges = new HashMap<Integer, HashMap<Integer, edge_data>>();

    private NodeData node;
    private int edgeSize;
    private int mc;


    public  DWGraph_DS() {
        this.Nodes = new HashMap<Integer, node_data>();
        this.Edges = new HashMap<Integer, HashMap<Integer, edge_data>>();
        this.edgeSize = 0;
        this.mc = 0;
    }
    public  DWGraph_DS( DWGraph_DS other)
    {
        this.Nodes=other.Nodes;
        this.Edges=other.Edges;
        this.edgeSize=other.edgeSize;
        this.mc=other.mc;
    }
    @Override
    public node_data getNode(int key) {
        return this.Nodes.get(key);
    }

    @Override
    public edge_data getEdge(int src, int dest) {
        if(this.Edges.isEmpty() ||  !this.Edges.containsKey(src) || this.Edges.get(src).get(dest) == null)
            return null;
        return this.Edges.get(src).get(dest);
    }

    @Override
    public void addNode(node_data n) {
        if (Nodes.keySet().contains(n.getKey())) {
            System.err.println("Err: key already exists, add fail");
            return;
        }
        if(n.getWeight()<0)
        {
            System.err.println("The weight must be positive! . The node hadn't been added successfully..");
            return;
        }
        int key = n.getKey();
        this.Nodes.put(key, n);//n used to be casted into (node)
        mc++;
    }

    @Override
    public void connect(int src, int dest, double w)
    {
        if(w<=0)
        {
            System.err.println("The weight must be positive! . connect failed");
            return;
        }
        NodeData.EdgeData e = new NodeData.EdgeData(src, dest, w);
        if (Nodes.get(src) == null || Nodes.get(dest) == null) {
            System.err.println("can't connect");
            //throw new RuntimeErrorException(null);
        }
        if (Edges.get(src) != null) {
            Edges.get(src).put(dest, e);

            mc++;
        } else {
            this.Edges.put(src, new HashMap<Integer, edge_data>());
            this.Edges.get(src).put(dest, e);

            mc++;
        }

    }

    @Override
    public Collection<node_data> getV() {
        Collection<node_data> col = this.Nodes.values();
        return col;
    }

    @Override
    public Collection<edge_data> getE(int node_id)
    {
        if(this.Edges.get(node_id)==null)
            return null;
        Collection<edge_data> col =  this.Edges.get(node_id).values();
        return col;
    }

    @Override
    public node_data removeNode(int key) {
//

        if (this.Nodes.get(key) == null) {
            return null;
        }
        node_data n = Nodes.get(key);
        Set<Integer> edgeKeys = Edges.keySet();
        for(Integer node: edgeKeys) {
            if(Edges.get(node).get(key)!=null)
            {
                Edges.get(node).remove(key);

            }
        }

        // remove all edges coming out of key-node.
        if(this.Edges.get(key) != null )
            edgeSize -= this.Edges.get(key).values().size();
        this.Edges.remove(key);
        // remove the key-node.
        this.Nodes.remove(key);
        mc++;

        return n;
    }

    @Override
    public edge_data removeEdge(int src, int dest) {
        if(getEdge(src,dest) == null) {
            return null;
        }
        NodeData.EdgeData e = (NodeData.EdgeData) this.Edges.get(src).get(dest);
        this.Edges.get(src).remove(dest);

        mc++;
        return e;
    }

    @Override
    public int nodeSize() {
        return this.Nodes.size();
    }

    @Override
    public int edgeSize() {
        Set<Integer> edgeKeys = Edges.keySet();
        for (Integer node : edgeKeys) {
            edgeSize =+Edges.get(node).size();
        }

       return edgeSize;
    }

    @Override
    public int getMC() {
        return mc;
    }


}
