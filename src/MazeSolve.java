import java.util.ArrayList;
import java.util.Objects;

public class MazeSolve{
    private Coordinate end;
    private String[][] maze;
    private ArrayList<Coordinate> steps;
    private ArrayList<Coordinate> forks;
    private Coordinate start = new Coordinate(0,0);


    private Coordinate currentPosition;

    public MazeSolve(int x, int y, String[][] grid){
        end = new Coordinate(x,y);
        System.out.println(x+", "+y);
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
        return (currentPosition.toString());
    }

    public String moveDown(){
        currentPosition.setRow(currentPosition.getRow()+1);
        return (currentPosition.toString());
    }

    public String moveRight(){
        currentPosition.setColumn(currentPosition.getColumn()+1);
        return (currentPosition.toString());
    }

    public String moveLeft(){
        currentPosition.setColumn(currentPosition.getColumn()-1);
        return (currentPosition.toString());
    }

    public String directionFrom(){
        if (currentPosition.toString().equals(start.toString())){
            return "Nowhere";
        }
        if(steps.getLast().getColumn()==currentPosition.getColumn()-1){
            return "Left";
        }
        if(steps.getLast().getColumn()==currentPosition.getColumn()+1){
            return "Right";
        }

        if(steps.getLast().getRow()==currentPosition.getRow()+1){
            return "Up";
        }
        if(steps.getLast().getRow()==currentPosition.getRow()-1){
            return "Down";
        }
        return "how";
    }

    public String solveMaze(){
        ArrayList<String> stepString = new ArrayList<String>();
        boolean win =false;
        while (!win){
            if(canMoveLeft()|| !Objects.equals(directionFrom(), "Left")){
                stepString.add(moveLeft());
                System.out.println("left");
            }
            if(canMoveRight()|| !Objects.equals(directionFrom(), "Right")){
                stepString.add(moveRight());
                System.out.println("right");

            }
            if(canMoveUp()|| !Objects.equals(directionFrom(), "Up")){
                stepString.add(moveUp());
                System.out.println("up");

            }
            if(canMoveDown()|| !Objects.equals(directionFrom(), "Down")){
                stepString.add(moveDown());
                System.out.println("down");

            }

            if(currentPosition.toString().equals(end.toString())){
                win=true;
            }
        }

        String stepsText = "";

        for(String coord :stepString){
            stepsText+=coord+" --> ";
        }
        return stepsText;
    }


}
