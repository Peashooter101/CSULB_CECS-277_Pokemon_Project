package Types;

import Entity.Pokemon.Pokemon;

public interface Water {
  public String specialMenu = "1. Water Gun\n2. Bubble Beam\n3. Waterfall";
  public int numSpecialMenuItems = 3;

  public String waterGun(Pokemon p);
  public String bubbleBeam(Pokemon p);
  public String waterfall(Pokemon p);
}