import java.util.ArrayList;
import java.util.Objects;

public class MazeSolve{
    private String[][] maze;
    private ArrayList<Coordinate> steps;
    private ArrayList<Coordinate> forks;
    private final Coordinate start = new Coordinate(0,0);
    private Coordinate currentPosition;
    private Coordinate end;

    public MazeSolve(int x, int y, String[][] grid){
        end = new Coordinate(y-1, x-1);
        maze = grid;
        steps= new ArrayList<Coordinate>();
        currentPosition= new Coordinate(0,0);
    }



    public boolean canMoveUp(){
        if(currentPosition.getRow()==0){
            return false;
        }
        if(maze[currentPosition.getRow()-1][currentPosition.getColumn()].equals("#")){
            return false;
        }
        return true;
    }

    public boolean canMoveDown(){
        if(currentPosition.getRow()==maze.length-1){
            return false;
        }
        if(maze[currentPosition.getRow()+1][currentPosition.getColumn()].equals("#")){
            return false;
        }
        return true;
    }

    public boolean canMoveRight(){
        if(currentPosition.getColumn()== maze[0].length-1){
            return false;
        }
        if(maze[currentPosition.getRow()][currentPosition.getColumn()+1].equals("#")){
            return false;

        }
        return true;
    }

    public boolean canMoveLeft() {
        if (currentPosition.getColumn() == 0) {
            return false;
        }
        if (maze[currentPosition.getRow()][currentPosition.getColumn() - 1].equals("#")) {
            return false;
        }
        return true;
    }

    public String moveUp(){
        currentPosition.setRow(currentPosition.getRow()-1);
        Coordinate move = new Coordinate(currentPosition.getRow(),currentPosition.getColumn());
        steps.add(move);
        return (currentPosition.toString());

    }

    public String moveDown(){
        currentPosition.setRow(currentPosition.getRow()+1);
        Coordinate move = new Coordinate(currentPosition.getRow(),currentPosition.getColumn());
        steps.add(move);
        return (currentPosition.toString());
    }

    public String moveRight(){
        currentPosition.setColumn(currentPosition.getColumn()+1);
        Coordinate move = new Coordinate(currentPosition.getRow(),currentPosition.getColumn());
        steps.add(move);
        return (currentPosition.toString());
    }

    public String moveLeft(){
        currentPosition.setColumn(currentPosition.getColumn()-1);
        Coordinate move = new Coordinate(currentPosition.getRow(),currentPosition.getColumn());
        steps.add(move);
        return (currentPosition.toString());
    }

    public String directionFrom(){
        if (currentPosition.toString().equals(start.toString())){
            return "Nowhere";
        }
        if (steps.size() > 1) {

            if (!steps.isEmpty() && steps.get(steps.size() - 2).getColumn() == currentPosition.getColumn() - 1) {
                return "Left";
            }
            if (!steps.isEmpty() && steps.get(steps.size() - 2).getColumn() == currentPosition.getColumn() + 1) {
                return "Right";
            }
            if (!steps.isEmpty() && steps.get(steps.size() - 2).getRow() == currentPosition.getRow() - 1) {
                return "Up";
            }
            if (!steps.isEmpty() && steps.get(steps.size() - 2).getRow() == currentPosition.getRow() + 1) {
                return "Down";
            }
        }
        return "how";
    }

    public boolean isDeadEnd() {
        String directionCameFrom = directionFrom();

        // If all directions are blocked except the direction from which we came
        if ("Up".equals(directionCameFrom)) {
            return !(canMoveLeft() || canMoveRight() || canMoveDown());
        }
        if ("Down".equals(directionCameFrom)) {
            return !(canMoveLeft() || canMoveRight() || canMoveUp());
        }
        if ("Left".equals(directionCameFrom)) {
            return !(canMoveUp() || canMoveDown() || canMoveRight());
        }
        if ("Right".equals(directionCameFrom)) {
            return !(canMoveUp() || canMoveDown() || canMoveLeft());
        }

        return !(canMoveUp() || canMoveDown() || canMoveLeft() || canMoveRight());
    }








    public String solveMaze(){

        Coordinate start = new Coordinate(0,0);
        boolean deadEnd =false;


        boolean win =false;

        steps.add(start);
        int count = 0;




        while (!win){

            boolean hasMoved =false;
            if(deadEnd){
                currentPosition.setRow(0);
                currentPosition.setColumn(0);
                deadEnd = false;


            }

            if(isDeadEnd()){
                deadEnd=true;
                maze[currentPosition.getRow()][currentPosition.getColumn()] = "#";
//                System.out.println("Wall placed at "+currentPosition.toString());
                for(int i =0 ; i<count;i++){
                    if(!steps.isEmpty()) {
                        steps.remove(steps.size()-1);
                    }
                }
            }

            if(!hasMoved && canMoveLeft() && !directionFrom().equals("Left")&&!deadEnd){
                moveLeft();
                hasMoved =true;
                count++;

            }

            if(!hasMoved && canMoveRight()&& !directionFrom().equals("Right")&&!deadEnd){
                moveRight();
                hasMoved =true;
                count++;

            }
            if(!hasMoved && canMoveUp() && !directionFrom().equals("Up")&&!deadEnd){
                moveUp();
                hasMoved =true;
                count++;

            }
            if(!hasMoved && canMoveDown()&& !directionFrom().equals("Down")&&!deadEnd){
                moveDown();
                hasMoved =true;
                count++;

            }


            if(currentPosition.toString().equals(end.toString())){
                win=true;
            }

            hasMoved=false;

        }

        String stepsText = "";

        for(Coordinate coord :steps){
            stepsText+=coord.toString()+" --> ";
        }
        return (stepsText.substring(11,stepsText.length()-5));

    }


}