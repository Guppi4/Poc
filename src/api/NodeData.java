package api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

public class NodeData implements node_data{
    private HashMap<Integer,edge_data> neighbors;
    private int key ;
    private static int count = 0;
    private String Info;
    private double dist;
    private int tag;
    private double weight;
    private geo_location geo;


    public NodeData(){
        this.key = count;
        neighbors = new HashMap<>();
        this.geo = new location();
        this.dist = Integer.MAX_VALUE;
        this.weight = 0;
        this.Info = "white";
        this.tag = Integer.MAX_VALUE;
        count ++;
    }

    public NodeData(node_data other){
        this.key = other.getKey();
        neighbors = new HashMap<>();
        this.weight = other.getWeight();
        this.dist = Integer.MAX_VALUE;
        this.Info = other.getInfo();
        this.tag = other.getTag();
    }

    public edge_data getEdge(int dest) {
        if(this.neighbors == null)
            return null;
        return neighbors.get(dest);
    }

    public  Collection<edge_data> getNi() {
        if(this.neighbors == null)
            return null;
        return this.neighbors.values();
    }

    public void createEdge(int src, int dest, double weight){
        edge_data edge = new EdgeData(src,dest,weight);
        neighbors.put(dest,edge);
    }

    public edge_data removeEdge( int dest){
        return this.neighbors.remove(dest);
    }
    public double getDist(){
        return this.dist;
    }

    public void setDist(double dist){
        this.dist = dist;
    }

    private class EdgeData implements edge_data{
        private int src;
        private int dest;
        private double weight;
        private String info;
        private int tag;

        public EdgeData(int src,int dest,double w){
            this.src = src;
            this.dest = dest;
            this.weight = w;
            info = "";
            tag = -1;
        }

        @Override
        public int getSrc() {
            return this.src;
        }

        @Override
        public int getDest() {
            return this.dest;
        }

        @Override
        public double getWeight() {
            return this.weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EdgeData edgeData = (EdgeData) o;
            return src == edgeData.src &&
                    dest == edgeData.dest &&
                    Double.compare(edgeData.weight, weight) == 0 &&
                    tag == edgeData.tag &&
                    Objects.equals(info, edgeData.info);
        }

        @Override
        public String getInfo() {
            return this.info;
        }

        @Override
        public void setInfo(String s) {
            this.info = s;
        }

        @Override
        public int getTag() {
            return this.tag;
        }

        @Override
        public void setTag(int t) {
            this.tag = t;
        }
    }
    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeData nodeData = (NodeData) o;
        return key == nodeData.key &&
                tag == nodeData.tag &&
                Double.compare(nodeData.weight, weight) == 0 &&
                Objects.equals(neighbors, nodeData.neighbors) &&
                Objects.equals(Info, nodeData.Info);
    }

    @Override
    public geo_location getLocation() {
        return this.geo;
    }

    @Override
    public void setLocation(geo_location p) {
        location loc = (location) this.geo;
        loc.setX(p.x());
        loc.setY(p.y());
        loc.setZ(p.z());
    }
    private class location implements geo_location{
        private double x;
        private double y;
        private double z;

        public location(){
            this.x = 0;
            this.y = 0;
            this.z = 0;
        }

        public void setX(double x){
            this.x =x;
        }

        @Override
        public double x() {
            return this.x;
        }
        public void setY(double y){
            this.y =y;
        }

        @Override
        public double y() {
            return this.y;
        }
        public void setZ(double z){
            this.z =z;
        }

        @Override
        public double z() {
            return this.z;
        }

        @Override
        public double distance(geo_location g) {
            return 0;
        }
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return this.Info;
    }

    @Override
    public void setInfo(String s) {
        this.Info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}
