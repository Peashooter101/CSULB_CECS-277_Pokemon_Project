package Entity.Pokemon;

import Types.Fire;
import java.util.Random;

public class Ponyta extends Pokemon implements Fire {

  public Ponyta() {
    super("Ponyta");
  }

  public String getSpecialMenu() { return Fire.specialMenu; }

  public int getNumSpecialMenuItems() { return Fire.numSpecialMenuItems; }

  public String specialAttack(Pokemon p, int move) {

    switch (move) {
      case 1:
        return ember(p);
      case 2:
        return fireBlast(p);
      case 3:
        return firePunch(p);
    }

    return "[ERROR] " + getName() + " FAILED TO SPECIAL ATTACK " + p.getName();

  }
  
  /*
  * Represents Ponyta's Ember.
  * Damage of 0-4 dealt.
  *
  * @param p Pokemon to attack.
  * @return Message describing the event.
  */
  public String ember(Pokemon p) {
    double multiplier = Pokemon.battleTable[this.getType()][p.getType()];
    Random rand = new Random();
    String returnString = "";

    int dmg = (int)Math.ceil((rand.nextInt(5))*multiplier);
    p.takeDamage(dmg);

    returnString += getName() + " lights a fire on " + p.getName() + " with EMBER for " + dmg + " damage!";
    if (multiplier == 0.5) { returnString += "\nIt's not very effective..."; }
    if (multiplier == 2) { returnString += "\nIt's super effective!"; }

    return returnString;
  }
  
  /*
  * Represents Ponyta's Fire Blast.
  * Damage of 2-5 dealt.
  *
  * @param p Pokemon to attack.
  * @return Message describing the event.
  */
  public String fireBlast(Pokemon p) {
    double multiplier = Pokemon.battleTable[this.getType()][p.getType()];
    Random rand = new Random();
    String returnString = "";

    int dmg = (int)Math.ceil((rand.nextInt(4) + 2)*multiplier);
    p.takeDamage(dmg);

    returnString += getName() + " blasts " + p.getName() + " with FIRE BLAST for " + dmg + " damage!";
    if (multiplier == 0.5) { returnString += "\nIt's not very effective..."; }
    if (multiplier == 2) { returnString += "\nIt's super effective!"; }

    return returnString;
  }
  
  /*
  * Represents Ponyta's Fire Punch.
  * Damage of 1-4 dealt.
  *
  * @param p Pokemon to attack.
  * @return Message describing the event.
  */
  public String firePunch(Pokemon p) {
    double multiplier = Pokemon.battleTable[this.getType()][p.getType()];
    Random rand = new Random();
    String returnString = "";

    int dmg = (int)Math.ceil((rand.nextInt(4) + 1)*multiplier);
    p.takeDamage(dmg);

    returnString += getName() + " lands a blow on " + p.getName() + " using FIRE PUNCH for " + dmg + " damage!";
    if (multiplier == 0.5) { returnString += "\nIt's not very effective..."; }
    if (multiplier == 2) { returnString += "\nIt's super effective!"; }

    return returnString;
  }
  
}