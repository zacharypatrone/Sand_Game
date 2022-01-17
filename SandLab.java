import java.awt.*;
import java.util.*;

public class SandLab
{
  public static void main(String[] args)
  {
    SandLab lab = new SandLab(120, 80);
    lab.run();
  }
  
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int WOOD = 4;
  public static final int LAVA = 5;
  public static final int STONE = 6;
  public static final int BIRD = 7;
  
  //do not add any more fields
  private int[][] grid;
  private SandDisplay display;
  
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    names = new String[8];
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    names[WOOD] = "Wood";
    names[LAVA] = "Lava";
    names[STONE] = "Stone";
    names[BIRD] = "Bird";
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
    grid = new int[numRows][numCols];
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
	  grid[row][col] = tool;
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
	  Color BROWN = new Color(102, 51, 0);
	  Color ORANGE = new Color(255, 102, 0);
	  Color STONE = new Color(204, 204, 204);
	  Color BIRD = new Color(92, 116, 90);
	  
	  for (int row = 0; row < grid.length; row++) {
		  
		  for (int col = 0; col < grid[row].length; col++) {
			  
			  if (grid[row][col] == 0) display.setColor(row, col, Color.BLACK);
			  if (grid[row][col] == 1) display.setColor(row, col, Color.GRAY);
			  if (grid[row][col] == 2) display.setColor(row, col, Color.YELLOW);
			  if (grid[row][col] == 3) display.setColor(row, col, Color.BLUE);
			  if (grid[row][col] == 4) display.setColor(row, col, BROWN);
			  if (grid[row][col] == 5) display.setColor(row, col, ORANGE);
			  if (grid[row][col] == 6) display.setColor(row, col, STONE);
			  if (grid[row][col] == 7) display.setColor(row, col, BIRD);
		  }
	  }
  }

  //called repeatedly.
  //causes one random particle to maybe do something.
  public void step()
  {
	  Random rand = new Random();
	  int row = rand.nextInt(grid.length);
	  int col = rand.nextInt(grid[0].length);
	  
	  if (grid[row][col] == SAND) {
		  
		  if (grid[row+1][col] == EMPTY && row+1 < grid.length-1) {
			  
			  grid[row][col] = EMPTY;
			  grid[row+1][col] = SAND;
		  }
		  
		  else if (grid[row+1][col] == WATER && row+1 < grid.length-1) {
			  
			  grid[row][col] = WATER;
			  grid[row+1][col] = SAND;
		  }
	  }
	  
	  if (grid[row][col] == WATER) {

		  Random randW = new Random();
		  int dir = randW.nextInt(3);  
		  
		  switch(dir) {
		  
		  case 0:  
			  if (grid[row][col+1] == EMPTY && col+1 < grid[0].length-1) {
				  
				  grid[row][col] = EMPTY;
				  grid[row][col+1] = WATER;
			  }
			  break;
		  
		  case 1:
			  if (grid[row][col-1] == EMPTY && col-1 > 0) {
				  
				  grid[row][col] = EMPTY;
				  grid[row][col-1] = WATER;
			  }
			  break;
			  
		  case 2:
			  if (grid[row+1][col] == EMPTY && row+1 < grid.length-1) {
				  
				  grid[row][col] = EMPTY;
				  grid[row+1][col] = WATER;
			  }
			  break;
		  }
	  }
	  
	  if (grid[row][col] == WOOD) {
		  
		  if (grid[row+1][col] == EMPTY && row+1 < grid.length-1) {
			  
			  grid[row][col] = EMPTY;
			  grid[row+1][col] = WOOD;
		  }
		  
		  else if (grid[row+1][col] == WATER && row+1 < grid.length-1) {
			  
			  grid[row][col] = WOOD;
			  grid[row+1][col] = WATER;
		  }
	  }
	  
	  if (grid[row][col] == LAVA) {

		  Random randL = new Random();
		  int dir = randL.nextInt(4);  
		  
		  switch(dir) {
		  
		  case 0:  
			  if (grid[row][col+1] == EMPTY && col+1 < grid[0].length-1) {
				  
				  grid[row][col] = EMPTY;
				  grid[row][col+1] = LAVA;
			  }
		
			  if (grid[row][col+1] == WATER && col+1 < grid[0].length-1) {
				  
				  grid[row][col] = EMPTY;
				  grid[row][col+1] = STONE;
			  }
			  break;
		  
		  case 1:
			  if (grid[row][col-1] == EMPTY && col-1 > 0) {
				  
				  grid[row][col] = EMPTY;
				  grid[row][col-1] = LAVA;
			  }
			  
			  if (grid[row][col-1] == WATER && col-1 > 0) {
				  
				  grid[row][col] = EMPTY;
				  grid[row][col-1] = STONE;
			  }
			  break;
			  
		  case 2:
			  if (grid[row+1][col] == EMPTY && row+1 < grid.length-1) {
				  
				  grid[row][col] = EMPTY;
				  grid[row+1][col] = LAVA;
			  }
			  
			  if (grid[row+1][col] == WATER && row+1 < grid.length-1) {
				  
				  grid[row][col] = EMPTY;
				  grid[row+1][col] = STONE;
			  }
			  break;
			  
		  case 3:
			  if (grid[row-1][col] == WATER && row-1 > 0) {
				  
				  grid[row][col] = EMPTY;
				  grid[row-1][col] = STONE;
			  }
		  } 
	  }
	  
	  if (grid[row][col] == BIRD) {

		  Random randW = new Random();
		  int dir = randW.nextInt(4);  
		  
		  switch(dir) {
		  
		  case 0:  
			  if (grid[row][col+1] == EMPTY && col+1 < grid[0].length-1) {
				  
				  grid[row][col] = EMPTY;
				  grid[row][col+1] = BIRD;
			  }
			  break;
		  
		  case 1:
			  if (grid[row][col-1] == EMPTY && col-1 > 0) {
				  
				  grid[row][col] = EMPTY;
				  grid[row][col-1] = BIRD;
			  }
			  break;
			  
		  case 2:
			  if (grid[row+1][col] == EMPTY && row+1 < grid.length-1) {
				  
				  grid[row][col] = EMPTY;
				  grid[row+1][col] = BIRD;
			  }
			  if (grid[row+1][col] == LAVA && row+1 < grid.length-1) {
				  
				  grid[row][col] = EMPTY;
			  }
			  break;
			  
		  case 3:
			  if (grid[row-1][col] == EMPTY && row-1 > 0) {
				  
				  grid[row][col] = EMPTY;
				  grid[row-1][col] = BIRD;
			  }
			  break;
		  }
	  }
  }
  
  //do not modify
  public void run()
  {
    while (true)
    {
      for (int i = 0; i < display.getSpeed(); i++)
        step();
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
    }
  }
}
