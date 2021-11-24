package Entity.Pokemon;

import Types.Grass;
import java.util.Random;

public class Oddish extends Pokemon implements Grass {

  public Oddish() {
    super("Oddish");
  }

  public String getSpecialMenu() { return Grass.specialMenu; }

  public int getNumSpecialMenuItems() { return Grass.numSpecialMenuItems; }

  public String specialAttack(Pokemon p, int move) {

    switch (move) {
      case 1:
        return vineWhip(p);
      case 2:
        return razorLeaf(p);
      case 3:
        return solarBeam(p);
    }

    return "[ERROR] " + getName() + " FAILED TO SPECIAL ATTACK " + p.getName();
    
  }
  
  /*
  * Represents Oddish's Vine Whip.
  * Damage of 1-3 dealt.
  *
  * @param p Pokemon to attack.
  * @return Message describing the event.
  */
  public String vineWhip(Pokemon p) {
    double multiplier = Pokemon.battleTable[this.getType()][p.getType()];
    Random rand = new Random();
    String returnString = "";

    int dmg = (int)Math.ceil((rand.nextInt(3) + 1)*multiplier);
    p.takeDamage(dmg);

    returnString += getName() + " snaps at " + p.getName() + " with VINE WHIP for " + dmg + " damage!";
    if (multiplier == 0.5) { returnString += "\nIt's not very effective..."; }
    if (multiplier == 2) { returnString += "\nIt's super effective!"; }

    return returnString;
  }
  
  /*
  * Represents Oddish's Razor Leaf.
  * Damage of 2-4 dealt.
  *
  * @param p Pokemon to attack.
  * @return Message describing the event.
  */
  public String razorLeaf(Pokemon p) {
    double multiplier = Pokemon.battleTable[this.getType()][p.getType()];
    Random rand = new Random();
    String returnString = "";

    int dmg = (int)Math.ceil((rand.nextInt(3) + 2)*multiplier);
    p.takeDamage(dmg);

    returnString += getName() + " cuts " + p.getName() + " with RAZOR LEAF for " + dmg + " damage!";
    if (multiplier == 0.5) { returnString += "\nIt's not very effective..."; }
    if (multiplier == 2) { returnString += "\nIt's super effective!"; }

    return returnString;
  }
  
  /*
  * Represents Oddish's Solar Beam.
  * Damage of 0-5 dealt.
  *
  * @param p Pokemon to attack.
  * @return Message describing the event.
  */
  public String solarBeam(Pokemon p) {
    double multiplier = Pokemon.battleTable[this.getType()][p.getType()];
    Random rand = new Random();
    String returnString = "";

    int dmg = (int)Math.ceil((rand.nextInt(6))*multiplier);
    p.takeDamage(dmg);

    returnString += getName() + " beams " + p.getName() + " with SOLAR BEAM for " + dmg + " damage!";
    if (multiplier == 0.5) { returnString += "\nIt's not very effective..."; }
    if (multiplier == 2) { returnString += "\nIt's super effective!"; }

    return returnString;
  }
  
}