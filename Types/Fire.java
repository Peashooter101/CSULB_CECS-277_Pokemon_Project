package Types;

import Entity.Pokemon.Pokemon;

public interface Fire {
  public String specialMenu = "1. Ember\n2. Fire Blast\n3. Fire Punch";
  public int numSpecialMenuItems = 3;

  public String ember(Pokemon p);
  public String fireBlast(Pokemon p);
  public String firePunch(Pokemon p);
}