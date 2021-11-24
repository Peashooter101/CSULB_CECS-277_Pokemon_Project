package Entity.Pokemon;

import Entity.Entity;
import Types.*;
import java.util.Random;

/*
* Represents a Pokemon Entity.
*/
public abstract class Pokemon extends Entity {
  
  /*
  * Battle Table represents type advantages for Fire [0], Water [1], and Grass [2].
  * battleTable[Attacking Type][Defending Type]
  */
  public static final double [][] battleTable = {{1,.5,2},{2,1,.5},{.5,2,1}};

  /*
  * Constructor creates a new Pokemon.
  * A random HP (20 - 30) is selected upon creation.
  *
  * @param n String representing the Pokemon's name.
  */
  public Pokemon(String n) {
    super(n, ((int) (Math.random()*(11)+20)));
  }

  public abstract String getSpecialMenu();
  public abstract int getNumSpecialMenuItems();
  public abstract String specialAttack(Pokemon p, int move);

  /*
  * Returns the basic attack menu.
  *
  * @return String representing the basic attack menu.
  */
  public String getBasicMenu() { return "1. Slam\n2. Tackle\n3. Punch"; }

  /*
  * Returns the number of items in the basic menu.
  *
  * @return Integer for total menu items.
  */
  public int getNumBasicMenuItems() { return 3; }

  /*
  * Accomplishes a basic attack.
  *
  * @param p Pokemon to attack.
  * @param move Integer representing the attack.
  * @return Message describing the event. An error message if invalid input.
  */
  public String basicAttack(Pokemon p, int move) {

    switch (move) {
      case 1:
        return slam(p);
      case 2:
        return tackle(p);
      case 3:
        return punch(p);
    }

    return "[ERROR] " + getName() + " FAILED TO ATTACK " + p.getName();

  }

  /*
  * Returns the attack type menu.
  *
  * @return String representing the attack type menu.
  */
  public String getAttackMenu() { return "1. Basic Attack\n2. Special Attack"; }

  /*
  * Returns the number of items in the attack type menu.
  *
  * @return Integer for total menu items.
  */
  public int getNumAttackMenuItems() { return 2; }

  /*
  * Represents the slam move.
  * Damage of 0-5 dealt.
  *
  * @param p Pokemon to attack.
  * @return Message describing the event.
  */
  public String slam(Pokemon p) {
    Random rand = new Random();
    int dmg = rand.nextInt(6);
    p.takeDamage(dmg);
    return getName() + " absolutely SLAMMED " + p.getName() + " for " + dmg + " damage!";
  }

  /*
  * Represents the tackle move.
  * Damage of 2-3 dealt.
  *
  * @param p Pokemon to attack.
  * @return Message describing the event.
  */
  public String tackle(Pokemon p) {
    Random rand = new Random();
    int dmg = rand.nextInt(2) + 2;
    p.takeDamage(dmg);
    return getName() + " TACKLED down " + p.getName() + " for " + dmg + " damage!";
  }

  /*
  * Represents the punch move.
  * Damage of 1-4 dealt.
  *
  * @param p Pokemon to attack.
  * @return Message describing the event.
  */
  public String punch(Pokemon p) {
    Random rand = new Random();
    int dmg = rand.nextInt(4) + 1;
    p.takeDamage(dmg);
    return getName() + " sucker PUNCHED " + p.getName() + " for " + dmg + " damage!";
  }

  /*
  * Returns the type based on the Interface used for the Pokemon.
  * 0 represents Fire.
  * 1 represents Water.
  * 2 represents Grass.
  *
  * @return Integer representing Pokemon Type. -1 if no type found.
  */
  public int getType() {
    if (this instanceof Fire) { return 0; }
    if (this instanceof Water) { return 1; }
    if (this instanceof Grass) { return 2; }
    return -1;
  }

}