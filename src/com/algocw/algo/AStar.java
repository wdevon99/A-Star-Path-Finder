package com.algocw.algo;

import com.algocw.gui.GridPopulator;
import com.algocw.gui.MainScreen;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar {

    //the 2D array of Nodes (The DiGraph)
    public Node nodeMatrix [][] = new Node[20][20];

    //this priority que will be used as the open list when finding the path (This will be sorted in Ascending order of fCost)
    PriorityQueue<Node> priorityQueue = new PriorityQueue<>(400, new Comparator<Node>() {

        //overriding the compare method to compare two nodes from the fCost
        @Override
        public int compare(Node current, Node next) {
            if(current.fCost()<next.fCost()){
                return -1;
            }else if(current.fCost()>next.fCost()){
                return 1;
            }else{
                return 0;
            }

        }
    });


    //This arraylist will store all the node which have been visited and no longer needed to be checked
    public ArrayList<Node> visitedArraylist = new ArrayList<>();

    //This arraylist will store all the node included in the final path
    ArrayList finalPathArrayList= new ArrayList();


    /**
     * This method will calculate and return the heristic of a given node
     * @param type : The method of calculation - Manhattan, Euclidean,or Chebyshev
     * @param node : The node for which you want to calculate the heuristic
     * @return : The heuristic value of the particular node
     */
    public double getHeuristicOfNode(String type , Node node ){

        double heuristic=0.0;

        //calculating a setting the heuristic value based on the type of heuristic calculation method selected
        switch (type){

            case "Manhattan":
                heuristic = Math.abs(node.getxCoordinate() - MainScreen.getEndX()) + Math.abs(node.getyCoordinate() - MainScreen.getEndY());
                break;
            case "Euclidean":
                heuristic= Math.sqrt(Math.pow((node.getxCoordinate() - MainScreen.getEndX()), 2) + Math.pow((node.getyCoordinate() - MainScreen.getEndY()), 2));
                break;
            case "Chebyshev":
                heuristic= Math.max(Math.abs(node.getxCoordinate() - MainScreen.getEndX()), Math.abs(node.getyCoordinate() - MainScreen.getEndY()));
                break;

        }

        return  heuristic;
    }


    /**
     *
     * This method will assign the Heuristic Values of each node and add to the nodeMatrix
     *
     */
    public void populateNodeMatrix(){

        //these nested for loops will add the nodes to the nodeMatrix grid , one single row of nodes at a time
        for(int y=0 ; y < nodeMatrix.length ; y++){
            for (int x=0 ; x < nodeMatrix[y].length ; x++){
                //creating a new Node object with x and y coordinates
                Node node= new Node(x,y);

                //setting the heuristic (hCost of the node)
                node.sethCost(getHeuristicOfNode(MainScreen.getSelectedMethod() , node));

                //setting the weight of the node
                node.setNodeWeight(getWeightOfNode(node));

                //setting the blocked nodes
                if(getWeightOfNode(node) == 5){
                    node.setBlocked(true);
                }

                //Adding to the nodeMatrix 2D array
                nodeMatrix [x][y]= node ;

                //For debugging
                //System.out.println(node );

            }
            //For debugging
//            System.out.println();
//
        }

    }

    /**
     * this method is used to get the weight of the node
     * @param node : The current node object
     * @return
     */
    public int getWeightOfNode(Node node){
        return GridPopulator.matrixArray[node.getxCoordinate()][node.getyCoordinate()];
    }


    //========================================================================
    //TODO
    public double updateGCost(){
        double newGCost=0;
        //TODO
        return newGCost;
    }



    //========================================================================

    public  void findPath(){


        //creating the start and end nodes
        Node startNode= nodeMatrix[MainScreen.getStartX()][MainScreen.getStartY()];
        Node endNode =nodeMatrix[MainScreen.getEndX()][MainScreen.getEndY()];

        //setting the gCost of the start node to 0
        startNode.setgCost(0);
        startNode.setParent(null);

        //assigning the current node as the start node at the begging
        Node currentNode = startNode;

        //adding the current node to the priority queue
        priorityQueue.add(currentNode);

        while (!priorityQueue.isEmpty()) {

            //checking if we have reached the END point and breaking the loop
            if(currentNode.getxCoordinate()== endNode.getxCoordinate()  &&  currentNode.getyCoordinate()== endNode.getyCoordinate() ){

                //Added the final Node //TODO
                visitedArraylist.add(currentNode);

                //message for debugging
                System.out.println("You have Reached the end!");
                //breaking the while loop
                break;
            }


            //the coordinates of the current Node
            int x=currentNode.getxCoordinate();
            int y=currentNode.getyCoordinate();


            /* getting the nodes around the current node and adding to the priority que */

            //checking for the top left corner
            if(x==0 && y==0){
                //only check and update the east , south-east and south nodes
                updateChildNode(currentNode,"EAST");
                updateChildNode(currentNode,"SOUTH-EAST");
                updateChildNode(currentNode,"SOUTH");
            }

            //checking for the bottom left corner
            else if(x==0 && y==19){
                //only check the north , north east and east nodes
                updateChildNode(currentNode,"EAST");
                updateChildNode(currentNode,"NORTH-EAST");
                updateChildNode(currentNode,"NORTH");
            }

            //checking for the top right corner
            else if(x==19 && y==0){
                //only check the west , south-west and south nodes
                updateChildNode(currentNode,"WEST");
                updateChildNode(currentNode,"SOUTH-WEST");
                updateChildNode(currentNode,"SOUTH");
            }

            //checking for the bottom right corner
            else if(x==19 && y==19){
                //only check west , north-west and north nodes
                updateChildNode(currentNode,"WEST");
                updateChildNode(currentNode,"NORTH-WEST");
                updateChildNode(currentNode,"NORTH");
            }

            //check for horizontal TOP border cases
            else if(y==0 && x>0 && x<19 ){
                //only check  east , west ,south-east ,south-west and south
                updateChildNode(currentNode,"EAST");
                updateChildNode(currentNode,"SOUTH-EAST");
                updateChildNode(currentNode,"WEST");
            }

            //check for horizontal BOTTOM border cases
            else if(y==19 && x>0 && x<19 ){
                //only check  east , west ,north-east ,north-west and north
                updateChildNode(currentNode,"EAST");
                updateChildNode(currentNode,"WEST");
                updateChildNode(currentNode,"NORTH-WEST");
            }


            //checking for vertical RIGHT border cases
            else if(x==19 && y>0 && y<19 ){
                //only check for north , south , east , north-east and south-east
                updateChildNode(currentNode,"NORTH");
                updateChildNode(currentNode,"SOUTH");
                updateChildNode(currentNode,"WEST");
                updateChildNode(currentNode,"NORTH-WEST");
                updateChildNode(currentNode,"SOUTH-WEST");

            }

            //checking for vertical LEFT border cases
            else if(x==0 && y>0 && y<19 ){
                //only check for north , south , west , north-west and south-west
                updateChildNode(currentNode,"NORTH");
                updateChildNode(currentNode,"SOUTH");
                updateChildNode(currentNode,"EAST");
                updateChildNode(currentNode,"NORTH-EAST");
                updateChildNode(currentNode,"SOUTH-EAST");

            }

            //if non of the conditions are satisfied , it means the current node is in a middle position
            else{

                //check and update all
                updateChildNode(currentNode,"NORTH");
                updateChildNode(currentNode,"SOUTH");
                updateChildNode(currentNode,"WEST");
                updateChildNode(currentNode,"EAST");
                updateChildNode(currentNode,"NORTH-WEST");
                updateChildNode(currentNode,"SOUTH-WEST");
                updateChildNode(currentNode,"NORTH-EAST");
                updateChildNode(currentNode,"SOUTH-EAST");

            }

            //removing the top element in the priority que
            currentNode=priorityQueue.remove();
            //adding to the visited list
            visitedArraylist.add(currentNode);
            //setting the current node visited status to true
            currentNode.setVisited(true);

        }


    }


    /**
     * This method will update the gCost of the surrounding nodes of an given node AND add to the priority que
     * @param currentNode - the current node
     * @param relativeDirection - This could be NORTH or EAST or SOUTH or WEST or NORTH-EAST or NORTH-WEST or SOUTH-EAST or SOUTH-WEST
     */
    public void updateChildNode(Node currentNode , String relativeDirection){

        int x=currentNode.getxCoordinate();
        int y=currentNode.getyCoordinate();

        int nextX=0;
        int nextY=0;

        Node nextNode=null;

        switch (relativeDirection){
            case "NORTH":
                nextX=x;
                nextY=y-1;
                break;
            case "EAST":
                nextX=x+1;
                nextY=y;
                break;
            case "SOUTH":
                nextX=x;
                nextY=y+1;
                break;
            case "WEST":
                nextX=x-1;
                nextY=y;
                break;
            case "NORTH-EAST":
                nextX=x+1;
                nextY=y-1;
                break;
            case "NORTH-WEST":
                nextX=x-1;
                nextY=y-1;
                break;
            case "SOUTH-EAST":
                nextX=x+1;
                nextY=y+1;
                break;
            case "SOUTH-WEST":
                nextX=x-1;
                nextY=y+1;
                break;

        }


        nextNode=nodeMatrix[nextX][nextY];

        if(!nextNode.isVisited() || !nextNode.isBlocked()){
            //update gCost
            nextNode.setgCost(currentNode.getgCost() + nextNode.getNodeWeight());

            //TODO
            nextNode.setParent(currentNode);

            nodeMatrix[nextX][nextY]= nextNode ;

            //added to priority queue
            priorityQueue.add(nextNode);

        }
    }


    //========================================================================


    public void printArray(Node[][] ar){
        for(int i=0;i<20 ;i++){
            for(int j=0;j<20 ;j++){
                System.out.print( nodeMatrix[j][i]);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {

        AStar as= new AStar();
        as.populateNodeMatrix();
        as.findPath();

        as.printArray(as.nodeMatrix);
        as.findPath();


    }




}
