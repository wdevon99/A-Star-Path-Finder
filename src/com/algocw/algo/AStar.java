package com.algocw.algo;

import com.algocw.gui.GridPopulator;
import com.algocw.gui.MainScreen;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar {

    //the 2D array of Nodes (The DiGraph)
    Node nodeMatrix [][] = new Node[20][20];

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
    ArrayList<Node> visitedArraylist = new ArrayList<>();

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

                //Adding to the nodeMatrix 2D array
                nodeMatrix [x][y]= node ;

                //For debugging
                System.out.println(node );

            }
            //For debugging
            System.out.println();

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



    public double updateGCost(){
        double newGCost=0;
        //TODO
        return newGCost;
    }

    //========================================================================

    public  void findPath(){

        //creating the start and end nodes
        Node startNode= new Node(MainScreen.getStartX(),MainScreen.getStartY());
        Node endNode = new Node(MainScreen.getEndX(),MainScreen.getEndY());

        //setting the gCost of the start node to 0
        startNode.setgCost(0);
        startNode.setParent(null);

        //assigning the current node as the start node at the begging
        Node currentNode = startNode;

        //adding the current node to the priority queue
        priorityQueue.add(currentNode);

        while (priorityQueue.isEmpty()) {

            //checking if we have reached the END point and breaking the loop
            if(currentNode.getxCoordinate()== endNode.getxCoordinate()  &&  currentNode.getyCoordinate()== endNode.getyCoordinate() ){
                //message for debugging
                System.out.println("You have Reached the end!");
                //breaking the while loop
                break;
            }

            currentNode=priorityQueue.poll();
            visitedArraylist.add(currentNode);


            /*
            *      N
            *
            *  W   O   E
            *
            *      S
            * */

            //the coordinates of the current Node
            int x=currentNode.getxCoordinate();
            int y=currentNode.getyCoordinate();


            //getting the nodes around the current node and adding to the priority que

            //checking for the top left corner
            if(x==0 && y==0){
                //only check the east , south-east and south nodes



            }

            //checking for the bottom left corner
            else if(x==0 && y==19){
                //only check the north , north east and east nodes



            }

            //checking for the top right corner
            else if(x==19 && y==0){
                //only check the west , south-west and south nodes



            }

            //checking for the bottom right corner
            else if(x==19 && y==19){
                //only check west , north-west and north nodes



            }

            //check for horizontal TOP border cases
            else if(y==0 && x>0 && x<19 ){
                //only check  east , west ,south-east ,south-west and south


            }

            //check for horizontal BOTTOM border cases
            else if(y==19 && x>0 && x<19 ){
                //only check  east , west ,north-east ,north-west and north
            }


            //checking for vertical RIGHT border cases
            else if(x==0 && y>0 && y<19 ){

                //only check for north , south , east , north-east and south-east

            }

            //checking for vertical LEFT border cases
            else if(x==19 && y>0 && y<19 ){
                //only check for north , south , west , north-west and south-west



            }

            //if non of the conditions are satisfied , it means the current node is in a middle position
            else{

                //check all

            }



            //north node
            Node northNode=nodeMatrix[x][y-1];

            if(!northNode.isVisited() && !northNode.isBlocked()){
                //update gCost
                northNode.setgCost(currentNode.getNodeWeight() + northNode.getNodeWeight());
                //added to priority queue
                priorityQueue.add(northNode);
            }



            //south node
            Node southNode=nodeMatrix[x][y+1];
            priorityQueue.add(southNode);

            //east node
            Node eastNode=nodeMatrix[x+1][y];
            priorityQueue.add(eastNode);

            //west node
            Node westNode=nodeMatrix[x-1][y];
            priorityQueue.add(westNode);

            //north-east node
            Node northEastNode=nodeMatrix[x+1][y-1];
            priorityQueue.add(northEastNode);

            //south east node
            Node southEastNode=nodeMatrix[x+1][y+1];
            priorityQueue.add(southEastNode);

            //north-west node
            Node northWestNode=nodeMatrix[x-1][y-1];
            priorityQueue.add(northWestNode);

            //south-west node
            Node southWestNode=nodeMatrix[x-1][y+1];
            priorityQueue.add(southWestNode);







            //north node
            Node northNode=nodeMatrix[x][y-1];

            if(!northNode.isVisited() && !northNode.isBlocked()){
                //update gCost
                northNode.setgCost(currentNode.getNodeWeight() + northNode.getNodeWeight());
                //added to priority queue
                priorityQueue.add(northNode);
            }




        }


    }



    public Node updateChildNode(Node currentNode , String relativeDirection){

        int x=currentNode.getxCoordinate();
        int y=currentNode.getyCoordinate();

        Node node=null;

        switch (relativeDirection){
            case "NORTH":
                node=nodeMatrix[x][y-1];
                break;
            case "EAST":
                node=nodeMatrix[x+1][y];
                break;
            case "SOUTH":
                node=nodeMatrix[x][y+1];
                break;
            case "WEST":
                node=nodeMatrix[x-1][y];
                break;
            case "NORTH-EAST":
                node=nodeMatrix[x+1][y-1];
                break;
            case "NORTH-WEST":
                node=nodeMatrix[x-1][y-1];
                break;
            case "SOUTH-EAST":
                node=nodeMatrix[x+1][y+1];
                break;
            case "SOUTH-WEST":
                node=nodeMatrix[x-1][y+1];
                break;


        }

        if(!node.isVisited() && !node.isBlocked()){
            //update gCost
            node.setgCost(currentNode.getNodeWeight() + node.getNodeWeight());
            //added to priority queue
            priorityQueue.add(node);
        }

        return node;
    }





    //========================================================================




    public static void main(String[] args) {

        AStar as= new AStar();

        //as.populateNodeMatrix();

        //System.out.println(" >>>>>>>"+as.nodeMatrix[1][0]);

        Node one= new Node(1,2);
        Node two= new Node(1,2);
        Node three= new Node(1,2);
        Node four= new Node(1,2);

        one.sethCost(5);
        two.sethCost(-400);
        three.sethCost(88);
        four.sethCost(-3);
        four.sethCost(-300);



        as.priorityQueue.add(one);
        as.priorityQueue.add(two);
        as.priorityQueue.add(three);
        as.priorityQueue.add(four);


        System.out.println("Priori");

        while (!as.priorityQueue.isEmpty()){
            System.out.println(as.priorityQueue.poll());
        }



    }



}
