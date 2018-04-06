package com.algocw.algo;

public class Node {

    //the parent of this current node
    private Node parent;

    //the distance from the start node to the current node
    private double gCost;
    //the heuristic value ( distance from this node to the final target Node )
    private double hCost;

    //the total cost (Sum of the gCost And hCost)
    public double fCost(){
        return this.gCost+this.hCost;
    }

    //the visited or not visited state of the node
    private boolean isVisited;
    //the blocked or not blocked state of the node
    private boolean blocked;

    //the X and Y coordinates of the Node in the grid
    private int xCoordinate;
    private int yCoordinate;

    //the weight of the Node (This is used when calculating the gCost)
    private double nodeWeight;

    /**
     * The Node Constructor
     * @param xCoordinate
     * @param yCoordinate
     */
    public Node(int xCoordinate, int yCoordinate){
        //setting the coordinates
        this.xCoordinate=xCoordinate;
        this.yCoordinate=yCoordinate;

    }

    /**
     * Overriding the toString Method to get a human readable output when the object is printed
     * @return a formatted string with some details about a Node object
     */
    @Override
    public String toString(){
        return "      Node [( "+this.xCoordinate+" , "+this.yCoordinate+") -> W: "+this.nodeWeight+" , hCost: "+this.hCost+" , gCost: "+this.gCost+" , fCost: "+this.fCost()+"]      ";
    }

    //the getters and setters of encapsulated fields
    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public double getgCost() {
        return gCost;
    }

    public void setgCost(double gCost) {
        this.gCost = gCost;
    }

    public double gethCost() {
        return hCost;
    }

    public void sethCost(double hCost) {
        this.hCost = hCost;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public double getNodeWeight() {
        return nodeWeight;
    }

    public void setNodeWeight(double nodeWeight) {
        this.nodeWeight = nodeWeight;
    }




}
