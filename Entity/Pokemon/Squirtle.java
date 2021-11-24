package Entity.Pokemon;

import Types.Water;
import java.util.Random;

public class Squirtle extends Pokemon implements Water {

  public Squirtle() {
    super("Squirtle");
  }

  public String getSpecialMenu() { return Water.specialMenu; }

  public int getNumSpecialMenuItems() { return Water.numSpecialMenuItems; }

  public String specialAttack(Pokemon p, int move) {

    switch (move) {
      case 1:
        return waterGun(p);
      case 2:
        return bubbleBeam(p);
      case 3:
        return waterfall(p);
    }

    return "[ERROR] " + getName() + " FAILED TO SPECIAL ATTACK " + p.getName();

  }
  
  /*
  * Represents Squirtle's Water Gun.
  * Damage of 2-5 dealt.
  *
  * @param p Pokemon to attack.
  * @return Message describing the event.
  */
  public String waterGun(Pokemon p) {
    double multiplier = Pokemon.battleTable[this.getType()][p.getType()];
    Random rand = new Random();
    String returnString = "";

    int dmg = (int)Math.ceil((rand.nextInt(4)+2)*multiplier);
    p.takeDamage(dmg);

    returnString += getName() + " sprays " + p.getName() + " with WATER GUN for " + dmg + " damage!";
    if (multiplier == 0.5) { returnString += "\nIt's not very effective..."; }
    if (multiplier == 2) { returnString += "\nIt's super effective!"; }

    return returnString;
  }
  
  /*
  * Represents Squirtle's Bubble Beam.
  * Damage of 1-3 dealt.
  *
  * @param p Pokemon to attack.
  * @return Message describing the event.
  */
  public String bubbleBeam(Pokemon p) {
    double multiplier = Pokemon.battleTable[this.getType()][p.getType()];
    Random rand = new Random();
    String returnString = "";

    int dmg = (int)Math.ceil((rand.nextInt(3) + 1)*multiplier);
    p.takeDamage(dmg);

    returnString += getName() + " blows bubbles at " + p.getName() + " with BUBBLE BEAM for " + dmg + " damage!";
    if (multiplier == 0.5) { returnString += "\nIt's not very effective..."; }
    if (multiplier == 2) { returnString += "\nIt's super effective!"; }

    return returnString;
  }
  
  /*
  * Represents Squirtle's Waterfall.
  * Damage of 1-4 dealt.
  *
  * @param p Pokemon to attack.
  * @return Message describing the event.
  */
  public String waterfall(Pokemon p) {
    double multiplier = Pokemon.battleTable[this.getType()][p.getType()];
    Random rand = new Random();
    String returnString = "";

    int dmg = (int)Math.ceil((rand.nextInt(4) + 1)*multiplier);
    p.takeDamage(dmg);

    returnString += getName() + " wets " + p.getName() + " using WATERFALL for " + dmg + " damage!";
    if (multiplier == 0.5) { returnString += "\nIt's not very effective..."; }
    if (multiplier == 2) { returnString += "\nIt's super effective!"; }

    return returnString;
  }
  
}