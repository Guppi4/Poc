package api;

import java.util.*;

public class DWGraph_Algo implements dw_graph_algorithms {
    directed_weighted_graph graph;
//check
    public DWGraph_Algo(){
        this.graph = new DWGraph_DS();
    }
    @Override
    public void init(directed_weighted_graph g) {
        this.graph = g;
    }

    @Override
    public directed_weighted_graph getGraph() {
        return this.graph;
    }

    @Override
    public directed_weighted_graph copy() {
        directed_weighted_graph g = new DWGraph_DS();
        for(node_data run : graph.getV()){
            node_data var = new NodeData(run);
            g.addNode(var);
        }
        for(node_data run : graph.getV()){
            for(edge_data run1 : graph.getE(run.getKey())){
                g.connect(run1.getSrc(),run1.getDest(),run1.getWeight());
            }
        }
        return g;
    }

    @Override
    public boolean isConnected() {
        if(graph.nodeSize()==0 || graph.nodeSize()==1)
            return true;
        boolean flag;
        node_data start = null;
        for (node_data run : this.graph.getV()){
            start = run;
            break;
        }
        if(start == null)
            return false;
        flag = bfs(start,this.graph);
        if(flag == false)
            return false;

        nullify();
        directed_weighted_graph g = transpose();
        start = g.getNode(start.getKey());
        return bfs(start,g);
    }

    public boolean bfs(node_data start,directed_weighted_graph graph){
        Queue<node_data> st1 = new ArrayDeque<node_data>();
        start.setInfo("blue");
        int count = 1;
        st1.add(start);
        while (!st1.isEmpty()){
            node_data p = st1.poll();
            for(edge_data run : graph.getE(p.getKey())){
                if(graph.getNode(run.getDest()).getInfo() == "white") {
                    graph.getNode(run.getDest()).setInfo("blue");
                    count ++;
                    st1.add(graph.getNode(run.getDest()));
                }
            }
        }
        return graph.nodeSize() == count;
    }
    public directed_weighted_graph transpose(){
        directed_weighted_graph g = new DWGraph_DS();
        for(node_data run : graph.getV()){
            node_data var = new NodeData(run);
            g.addNode(var);
        }
        for(node_data run : graph.getV()){
            for(edge_data run1 : graph.getE(run.getKey())){
                g.connect(run1.getDest(),run1.getSrc(),run1.getWeight());
            }
        }
        return g;
    }
    public void nullify(){
        for(node_data run : graph.getV()){
            run.setTag(Integer.MAX_VALUE);
            run.setInfo("white");
        }
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if(src == dest)
            return 0;
        if(graph.getV().contains(graph.getNode(src))&&graph.getV().contains(graph.getNode(dest))) {
            dijkstra(src, dest);
            NodeData var = (NodeData) this.graph.getNode(dest);
            double dist = var.getDist();
            if(dist == Integer.MAX_VALUE)
                return -1;
            nullify();
            return dist;
        }
        return -1;
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        LinkedList<node_data> path = new LinkedList<node_data>();
        if(src == dest) {
            node_data s = graph.getNode(dest);
            path.addFirst(s);
            return path;
        }
        if(graph.getV().contains(graph.getNode(src))&&graph.getV().contains(graph.getNode(dest))) {
            //HashMap with path
            dijkstra(src, dest);
            node_data tempVariable = graph.getNode(dest);
            //check if got to destination
            if(tempVariable.getTag()==Integer.MAX_VALUE )
                return null;
            path.addFirst(tempVariable);
            int keyOfparent = tempVariable.getTag();
            while (keyOfparent != src){
                tempVariable = graph.getNode(keyOfparent);
                path.addFirst(tempVariable);
                keyOfparent = tempVariable.getTag();
            }
            tempVariable = graph.getNode(keyOfparent);
            path.addFirst(tempVariable);
            nullify();
            return path;
        }
        return null;
    }
    public void dijkstra(int src, int dest){
        PriorityQueue<NodeData> pq = new PriorityQueue<>(new Comparator<NodeData>() {
            @Override
            public int compare(NodeData o1, NodeData o2) {
                return  Double.compare(o1.getDist(), o2.getDist());
            }
        });
        NodeData p = (NodeData) this.graph.getNode(src);
        p.setDist(0);
        pq.add(p);
        while(!pq.isEmpty()){
            NodeData temp = (NodeData) pq.poll();
            // blue the node which was visited
            if(temp.getInfo() != "blue"){
                temp.setInfo("blue");
                if(temp.getKey() == dest){
                    return;
                }
                // check all neighbors of visited node
                for(edge_data run : this.graph.getE(temp.getKey())){
                    if(graph.getNode(run.getDest()).getInfo() != "blue" ){
                        edge_data edge = graph.getEdge(run.getSrc(),run.getDest());
                        double dist = temp.getDist() + edge.getWeight();
                        NodeData var = (NodeData) graph.getNode(run.getDest());
                        if(dist < var.getDist()){
                            var.setDist(dist);
                            pq.add(var);
                            var.setTag(temp.getKey());

                        }
                    }
                }
            }
        }
    }


    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
