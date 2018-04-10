package com.algocw.algo;

import com.algocw.gui.GridPopulator;
import com.algocw.gui.MainScreen;
import javafx.scene.paint.Color;

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


    //This array list will store all the node which have been visited and no longer needed to be checked
    public ArrayList<Node> visitedArraylist = new ArrayList<>();

    //This arraylist will store all the node included in the final path
    public ArrayList finalPathArrayList= new ArrayList();


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
        for(int x=0 ; x < nodeMatrix.length ; x++){
            for (int y=0 ; y < nodeMatrix[x].length ; y++){

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
        return GridPopulator.matrixArray[node.getyCoordinate()][node.getxCoordinate()];
    }


    //========================================================================
    //TODO
    public double updateGCost(){
        double newGCost=0;
        //TODO
        return newGCost;
    }



    //========================================================================

    /**
     *
     * This is the MAIN method that will run to find the path from the start point to the end point
     * @param startNode : The starting node
     */


    public void findPath(Node startNode ){

        //setting the gCost of the start node to 0
        startNode.setgCost(0);
        startNode.setParent(null);

        //assigning the current node as the start node at the begging
        Node currentNode = startNode;

        //adding the current node to the priority queue
        priorityQueue.add(currentNode);

        while (!priorityQueue.isEmpty()) {

            //removing the top element in the priority que
            boolean cont = false;
            while(!cont) {
                currentNode = priorityQueue.poll(); //TODO PUT IN TOOOOOPP
                if((! currentNode.isVisited()) && (!currentNode.isBlocked())){
                    cont = true;
                    System.out.println("Found visited Node");
                }
            }
            //For testing
            //System.out.println("LOOP RUNNING!");
            //System.out.println("Current Node :" +currentNode);


            //checking if we have reached the END point and breaking the loop
            if(currentNode.gethCost() ==0 ){
                //Message for debugging
                System.out.println("You have Reached the end!");

                //Added the final Node //TODO
                visitedArraylist.add(currentNode);

                //this method will backtrack and populate the final Path Array when the final node is passed
                processFinalPathArray(currentNode);

                //breaking the while loop
                break;
            }


            //the coordinates of the current Node
            int x=currentNode.getxCoordinate();
            int y=currentNode.getyCoordinate();


            /* getting the nodes around the current node and adding to the priority que */


            //check and update all
            try{
                updateChildNode(currentNode,"NORTH");
            }catch (IndexOutOfBoundsException e){
                System.out.println("Index out of bound : NORTH");
            }
            //check and update all
            try{
                updateChildNode(currentNode,"WEST");
            }catch (IndexOutOfBoundsException e){
                System.out.println("Index out of bound : WEST");
            }

            //check and update all
            try{
                updateChildNode(currentNode,"EAST");
            }catch (IndexOutOfBoundsException e){
                System.out.println("Index out of bound : EAST");
            }

            //check and update all
            try{
                updateChildNode(currentNode,"SOUTH");
            }catch (IndexOutOfBoundsException e){
                System.out.println("Index out of bound : SOUTH");
            }

            //check and update all
            try{
                updateChildNode(currentNode,"NORTH-WEST");
            }catch (IndexOutOfBoundsException e){
                System.out.println("Index out of bound : NORTH-WEST");
            }

            //check and update all
            try{
                updateChildNode(currentNode,"SOUTH-WEST");
            }catch (IndexOutOfBoundsException e){
                System.out.println("Index out of bound : SOUTH-WEST");
            }

            //check and update all
            try{
                updateChildNode(currentNode,"NORTH-EAST");
            }catch (IndexOutOfBoundsException e){
                System.out.println("Index out of bound : NORTH-EAST");
            }

            //check and update all
            try{
                updateChildNode(currentNode,"SOUTH-EAST");
            }catch (IndexOutOfBoundsException e){
                System.out.println("Index out of bound : SOUTH-EAST");
            }



            //adding to the visited list
            visitedArraylist.add(currentNode);
            //setting the current node visited status to true
            currentNode.setVisited(true);

        }


    }



    public void processFinalPathArray(Node finalNode){

        Node currentNode= finalNode;

        finalPathArrayList.add(currentNode);
        while (currentNode.getParent()!=null){

            finalPathArrayList.add(currentNode.getParent());
            currentNode=currentNode.getParent();

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

        int a=nextNode.getxCoordinate();
        int b=nextNode.getyCoordinate();
        MainScreen.colorAnCell(a,b, Color.RED);

        if(!nextNode.isVisited() && !nextNode.isBlocked()) {


            double newGCost = currentNode.getgCost() + nextNode.getNodeWeight();
            double newFCost = (newGCost + nextNode.gethCost());


            //update gCost
            if (newFCost < nextNode.fCost()) { //todo i changed thissss to fcost

                nextNode.setgCost(newGCost);

                nextNode.setParent(currentNode); //TODO I CHANGED THIS position

                nodeMatrix[nextX][nextY] = nextNode;  //TODO I CHANGED THIS position


            }


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

        //creating the start and end nodes
        Node startNode= as.nodeMatrix[0][0];
        //Node startNode= as.nodeMatrix[5][19];
        as.findPath(startNode);

        //as.printArray(as.nodeMatrix);

        //System.out.print("FINAL ARRAY:");
        //System.out.println(as.finalPathArrayList);


//        System.out.println(as.nodeMatrix[0][0]);


    }




}
