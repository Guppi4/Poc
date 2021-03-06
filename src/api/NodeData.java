package api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

public class NodeData implements node_data{
    private HashMap<Integer,edge_data> neighbors=new HashMap<Integer, edge_data>();
    private int key ;
    private static int count = 0;
    private String Info;
    private double dist;
    private int tag;
    private double weight;
    private geo_location geo=new location();
    public NodeData(int key){
        this.key = key;

    }

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
    public void setEdge(int dest,edge_data e) {
        this.neighbors.put(dest,e);
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

                Double.compare(nodeData.weight, weight) == 0 &&
                Objects.equals(neighbors, nodeData.neighbors) ;

    }

    @Override
    public geo_location getLocation() {
        return this.geo;
    }

    public geo_location bildgeo(double x,double y,double z){
        location loc=new location() ;
        loc.setX(x);
        loc.setY(y);
        loc.setZ(z);
         return loc;
    }
    public void setLocation(double x, double y, double z) {
        location loc = (location) this.geo;
        loc.setX(x);
        loc.setY(y);
        loc.setZ(z);
    }

    @Override
    public void setLocation(geo_location p) {
        location loc = (location) this.geo;
        loc.setX(p.x());
        loc.setY(p.y());
        loc.setZ(p.z());
    }
    public static class location implements geo_location{
        private double x;
        private double y;
        private double z;

        public location(){
            this.x = 0;
            this.y = 0;
            this.z = 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            location location = (location) o;
            return Double.compare(location.x, x) == 0 &&
                    Double.compare(location.y, y) == 0 &&
                    Double.compare(location.z, z) == 0;
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
            double dx = this.x - g.x();
            double dy = this.y - g.y();
            double dz = this.z - g.z();
            double t = (dx*dx+dy*dy+dz*dz);
            return Math.sqrt(t);
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
