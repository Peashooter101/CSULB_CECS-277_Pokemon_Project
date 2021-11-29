import java.awt.Point;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
* Defines the map structure.
* Aggregated to Trainer.
*/
public class Map {
  private char [][] map;
  private boolean [][] revealed;
  private static Map instance = null;

  /*
  * Loads the first map on creation.
  */
  private Map() {
    loadMap(1);
  }

  /*
  * Singleton Design Pattern.
  * @return Instance of Map.
  */
  public static Map getInstance(){
    if(instance == null){
      instance = new Map();
    }
    return instance;
  }

  /*
  * Loads the map into memory based on the given integer.
  * Assumes format of File name as "AreaN.txt" where N is area number.
  * If FileNotFoundException is thrown, nothing happens.
  * Supports any sized map.
  *
  * @param mapNum Represents map text file number, i.e. 1 means file "Area1.txt"
  */
  public void loadMap(int mapNum) {
    Scanner fileScan;
    File file = new File("src/Area"+mapNum+".txt");
    int xCount = 0;
    int xIndex = 0;

    // Count Total Lines
    try { fileScan = new Scanner(file); }
    catch (FileNotFoundException e) {
      System.out.println("[FATAL ERROR] FAILED TO LOAD MAP, SEE FILE PATH IN Map.java");
      e.printStackTrace();
      return;
    }

    while (fileScan.hasNextLine()) {
      xCount++;
      fileScan.nextLine();
    }
    fileScan.close();

    // Create X-Axis (Vertical)
    map = new char[xCount][];

    // Process Each Line
    try { fileScan = new Scanner(file); }
    catch (FileNotFoundException e) { return; }

    // Create Y-Axis (Horizontal)
    while (fileScan.hasNextLine()) {
      String currentLine = fileScan.nextLine();
      currentLine = currentLine.replaceAll(" ","");
      map[xIndex] = currentLine.toCharArray();
      xIndex++;
    }
    fileScan.close();

    // Generate revealed 2D Array
    revealed = new boolean[xCount][];
    for (int i = 0; i < xCount; i++) { revealed[i] = new boolean[map[i].length]; }
  }

  /*
  * Finds the character at the location given.
  * Returns '\0' should there be an invalid point given.
  *
  * @return Character representing the location. '\0' if an invalid point.
  */
  public char getCharAtLoc(Point p) {
    if (p.getX() < 0 || p.getX() >= map.length) { return '\0'; }
    if (p.getY() < 0 || p.getY() >= map[(int) p.getX()].length) { return '\0'; }
    return map[(int) p.getX()][(int) p.getY()];
  }

  /*
  * Translates the map into a String.
  * The returned String will show x in unrevealed spots.
  * Provided location will be marked as * for the trainer.
  *
  * @param p Location of the Trainer.
  */
  public String mapToString(Point p) {
    String returnString = "";

    for (int x = 0; x < map.length; x++) {
      for (int y = 0; y < map[x].length; y++) {

        // Represent Trainer Location
        if (p.getX() == x && p.getY() == y) {
          returnString += "* ";
          continue;
        }

        // Show Revealed / Unrevealed Markers
        returnString += revealed[x][y] ? map[x][y] + " " : "x ";
      }
      returnString.trim();
      returnString += "\n";
    }

    return returnString;
  }

  /*
  * Returns the location of the start point.
  * If a start point cannot be found, null is returned.
  * If a start is found, it is revealed and returned.
  *
  * @return Point Object that represents the location. Returns null if not found.
  */
  public Point findStart() {
    int foundX = Integer.MIN_VALUE;
    int foundY = Integer.MIN_VALUE;

    if (map == null) { System.out.println("MAP IS NULL"); }

    for (int x = 0; x < map.length; x++) {
      for (int y = 0; y < map[x].length; y++) {
        if (map[x][y] == 's') {
          foundX = x;
          foundY = y;
          break;
        }
      }
      if (foundX != Integer.MIN_VALUE) { break; }
    }

    if (foundX == Integer.MIN_VALUE) { return null; }
    Point returnPoint = new Point(foundX, foundY);
    reveal(returnPoint);
    return returnPoint;
  }

  /*
  * Reveals a location on the map.
  * If the map location is invalid, nothing happens.
  *
  * @param p Point to reveal.
  */
  public void reveal(Point p) {

    if (p.getX() < 0 || p.getX() >= revealed.length) { return; }
    if (p.getY() < 0 || p.getY() >= revealed[(int) p.getX()].length) { return; }
    revealed[(int) p.getX()][(int) p.getY()] = true;

  }

  public void removeCharAtLoc(Point p) {

    if (p.getX() < 0 || p.getX() >= map.length) { return; }
    if (p.getY() < 0 || p.getY() >= map[(int) p.getX()].length) { return; }
    map[(int) p.getX()][(int) p.getY()] = 'n';
  }

}