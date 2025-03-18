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
        forks =new ArrayList<Coordinate>();
         end = new Coordinate(y-1, x-1);
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
        if(!steps.isEmpty() && steps.get(steps.size()-2).getColumn()==currentPosition.getColumn()-1){
            return "Left";
        }
        if(!steps.isEmpty()&&steps.get(steps.size()-2).getColumn()==currentPosition.getColumn()+1){
            return "Right";
        }
        if(!steps.isEmpty() && steps.get(steps.size()-2).getRow()==currentPosition.getRow()-1){
            return "Up";
        }
        if(!steps.isEmpty() && steps.get(steps.size()-2).getRow()==currentPosition.getRow()+1){
            return "Down";
        }
        return "how";
    }

    public int hasFork(){
        int count =0;
        if(canMoveLeft() && !directionFrom().equals("Left")){
                count++;
        }

        if(canMoveRight()&& !directionFrom().equals("Right")){
            count++;
        }
        if(canMoveUp() && !directionFrom().equals("Up")){
            count++;
        }
        if(canMoveDown()&& !directionFrom().equals("Down")){
            count++;
        }

        return count;


    }

    public void addFork(ArrayList<String> fork){
        if(canMoveLeft() && !directionFrom().equals("Left")){
            fork.add("Left");
        }
        if(canMoveRight()&& !directionFrom().equals("Right")){
            fork.add("Right");
        }
        if(canMoveUp() && !directionFrom().equals("Up")){
            fork.add("Up");
        }
        if(canMoveDown()&& !directionFrom().equals("Down")){
            fork.add("Down");
        }
    }




    public String solveMaze(){
        Coordinate start = new Coordinate(0,0);
        boolean win =false;
        steps.add(start);
        int count = 0;
        ArrayList<String> forkDirections = new ArrayList<>();

        while (!win){
            boolean fork =false;
            boolean deadEnd =false;
            boolean hasMoved =false;

            System.out.println("________");
            System.out.println("CURRENT POS: ");

            System.out.println(currentPosition);
            System.out.println();
            if(!forks.isEmpty()) {
                System.out.println("FORKS: ");
                System.out.println(forks);
            }
            System.out.println("_______________________");
            System.out.println("POSSIBLE FORK MOVEMENT: ");
            System.out.println(forkDirections.toString());
            System.out.println("______");
            System.out.println("NUM OF POSSIBLE DIRECTIONS:");
            System.out.println(hasFork());
            if(hasFork()>1){
                Coordinate newCord = new Coordinate(currentPosition.getRow(), currentPosition.getColumn());
                System.out.println("fork detected");
                forks.add(newCord);
            }

            if(hasFork()==0){
                System.out.println("dead end detected");
                deadEnd =true;
                currentPosition.setColumn(forks.getLast().getColumn());
                currentPosition.setRow(forks.getLast().getRow());
            }
            System.out.println("DIRECTION: ");

            if(!hasMoved && canMoveLeft() && !directionFrom().equals("Left")){
                moveLeft();
                System.out.println("left");
                 hasMoved =true;

            }

            if(!hasMoved && canMoveRight()&& !directionFrom().equals("Right")){
                moveRight();
                System.out.println("right");
                hasMoved =true;

            }
            if(!hasMoved && canMoveUp() && !directionFrom().equals("Up")){
                moveUp();
                System.out.println("up");
                hasMoved =true;

            }
            if(!hasMoved && canMoveDown()&& !directionFrom().equals("Down")){
                moveDown();
                System.out.println("down");
                hasMoved =true;

            }

            if(currentPosition.toString().equals(end.toString())||count==10){
                win=true;
            }
            count++;

        }

        String stepsText = "";

        for(Coordinate coord :steps){
            stepsText+=coord.toString()+" --> ";
        }
        return stepsText;
    }


}
