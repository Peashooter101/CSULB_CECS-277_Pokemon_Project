package Types;

import Entity.Pokemon.Pokemon;

public interface Grass {
  public String specialMenu = "1. Vine Whip\n2. Razor Leaf\n3. Solar Beam";
  public int numSpecialMenuItems = 3;
  
  public String vineWhip(Pokemon p);
  public String razorLeaf(Pokemon p);
  public String solarBeam(Pokemon p);
}