import java.util.*;

public class HillClimbing extends Algorithms{
    private ArrayList<Node> tempNodeList = new ArrayList<Node>(); // store the shortest SLD of each node and become one path
     private Node goal = this.checkerObject.getGoal();
     private ArrayList<Node> deadEndList = null;
    public HillClimbing(){
        super();
    }

    public HillClimbing(String filename){
        super(filename);
        deadEndList = new ArrayList<Node>();
    }

    // it won't compute again cause it ONLY find the shortest SLD and NOT backtracking
    public void compute(){
        this.view.printMaze(this.currentNode, this.closedNodes); // Print the current maze
        if(this.checkerObject.isGoal(this.currentNode)) {
            // End of algorithm
            this.view.printSolution(this.closedNodes, this.deadEndList);
            this.view.printSuccess();
            return;
        }

        this.checkerObject.nodeCheckAll(this.currentNode, this.closedNodes);
        if(!stageOne()) {
            return;
        }
        compute();
    }

    public boolean stageOne(){
         int[] disList = new int[4];
         int lowest=100, count =0, i = 0, j =-1;
        
        this.closedNodes.add(new Node(this.currentNode.getX(), this.currentNode.getY())); // Create new Node to prevent pointing to currentNode's address
        System.out.println("x " + this.currentNode.getY());
        tempNodeList.add(this.currentNode);
        this.expandCounter++;
        this.view.printCounter(this.expandCounter);
        
        if (verifyDeadEnd()){
                System.out.println("No solution");
                return false;
        }
         
        if(this.checkerObject.getUp()==1) {
            disList[0] = calculateDistance(this.currentNode, tempNodeList);
        
        } else if (this.checkerObject.getLeft()==1) {
            disList[1] = calculateDistance(this.currentNode, tempNodeList);
           
        } else if (this.checkerObject.getDown()==1) {
            disList[2] = calculateDistance(this.currentNode, tempNodeList);
           
        } else if (this.checkerObject.getRight()==1) {
            disList[3] = calculateDistance(this.currentNode, tempNodeList);
          
        }
                
        while( i < 4 ){         
             if (disList[i] < lowest && disList[i] != 0){
                lowest = disList[i];
                j = i;

            }
            i++;            
        }
     
        switch (j){
            case 0: {
                this.currentNode.setX(this.currentNode.getX()-1);
                return true;

            }
            case 1: {
                this.currentNode.setY(this.currentNode.getY()-1);
                return true;
        
            }
            case 2:{
                this.currentNode.setX(this.currentNode.getX()+1);
                return true;
             
            }
            case 3:{
                this.currentNode.setY(this.currentNode.getY()+1);
             
                return true;
             
            }
        }
        return false;
    }

    public int calculateDistance(Node node) {
         System.out.println("ch4 "+ node.getX());
        return (Math.abs(this.goal.getX() - node.getX()) + Math.abs(this.goal.getY() - node.getY()));
    }

    public int calculateDistance(Node node, ArrayList<Node> tempNodeList) {
         int n = Math.abs(this.goal.getX() - node.getX()) + Math.abs(this.goal.getY() - node.getY());
         int m =0;
        for ( m= 0 ; m < tempNodeList.size() ; m++)
        {
            if (m == 0)
                m = 0;
            else
                m = tempNodeList.size();
        }
        return (n + m);
    
    }

    public boolean verifyDeadEnd() {
        int deadCount = 0;
        if(this.checkerObject.getUp()==0) {
            deadCount++;
        }
        if(this.checkerObject.getLeft()==0) {
            deadCount++;
        }
        if(this.checkerObject.getDown()==0) {
            deadCount++;
        }
        if(this.checkerObject.getRight()==0) {
            deadCount++;
        }

        if(deadCount==3) {
            deadEndList.add(this.currentNode);
            return true;
        }return false;
    }

} 
