package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		selectNextPath(maze.getCell(randGen.nextInt(width), randGen.nextInt(height)));
		
		//5. call selectNextPath method with the randomly selected cell
		
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> unvisited=getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(unvisited.size()!=0) {
			Cell newCell=unvisited.get(randGen.nextInt(unvisited.size()));
			uncheckedCells.push(newCell);
			removeWalls(currentCell,newCell);
			newCell.setBeenVisited(true);
			selectNextPath(newCell);
		}else if(uncheckedCells.size()!=0){
			Cell newCell=uncheckedCells.pop();
			selectNextPath(newCell);
		}
		
			//C1. select one at random.
			
			//C2. push it to the stack
		
			//C3. remove the wall between the two cells

			//C4. make the new cell the current cell and mark it as visited
		
			//C5. call the selectNextPath method with the current cell
			
			
		//D. if all neighbors are visited
		
			//D1. if the stack is not empty
			
				// D1a. pop a cell from the stack
		
				// D1b. make that the current cell
		
				// D1c. call the selectNextPath method with the current cell
				
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(Math.abs(c1.getX()-c2.getX())+Math.abs(c1.getY()-c2.getY())==1) {
			if(c1.getX()-c2.getX()==1) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			}else if(c1.getX()-c2.getX()==-1) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}else if(c1.getY()-c2.getY()==1) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}else if(c1.getY()-c2.getY()==1) {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> neighbors=new ArrayList<Cell>();
		if(c.getX()+1!=maze.getWidth() && !maze.getCell(c.getX()+1, c.getY()).hasBeenVisited()) {
			neighbors.add(maze.getCell(c.getX()+1, c.getY()));
		}
		if(c.getX()!=0 && !maze.getCell(c.getX()-1, c.getY()).hasBeenVisited()) {
			neighbors.add(maze.getCell(c.getX()-1, c.getY()));
		}
		if(c.getY()+1!=maze.getHeight() && !maze.getCell(c.getX(), c.getY()+1).hasBeenVisited()) {
			neighbors.add(maze.getCell(c.getX(), c.getY()+1));
		}
		if(c.getY()!=0 && !maze.getCell(c.getX(), c.getY()-1).hasBeenVisited()) {
			neighbors.add(maze.getCell(c.getX(), c.getY()-1));
		}
		return neighbors;
	}
}
