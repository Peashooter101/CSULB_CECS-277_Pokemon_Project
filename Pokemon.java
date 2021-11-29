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
  public Pokemon(String n, int h, int m) {
    super(n, h, m);
  }

  /**
   * Returns the attack type menu.
   *
   * @return String representing the attack type menu.
   */
  public String getAttackTypeMenu() { return "1. Basic Attack\n2. Special Attack"; }

  /**
   * Returns the number of items in the attack type menu.
   *
   * @return Integer for total menu items.
   */
  public int getNumAttackTypeMenuItems() { return 2; }

  /**
   * Returns a string representing the menu.
   * @param atkType Attack Type represented as int.
   * @return Menu for Attack Type, otherwise "INVALID MENU".
   */
  public String getAttackMenu(int atkType) {

    if (atkType == 1) { return "1. Slam\n2. Tackle\n3. Punch"; }

    return "[FATAL ERROR] INVALID ATTACK MENU in " + this.getClass().toString();

  }

  /**
   * Returns the number of items in the menu.
   * @param atkType Attack Type represented as int.
   * @return Number representing the attack menu items, invalid atkType returns -1.
   */
  public int getNumAttackMenuItems(int atkType) {
    if (atkType == 1) { return 3; }

    return -1;
  }

  /**
   * Accomplishes an attack.
   *
   * @param p Pokemon to attack.
   * @param atkType Attack Type
   * @param move Integer representing the attack.
   * @return Message describing the event.
   */
  public String attack(Pokemon p, int atkType, int move) {
    int attackDmg = getAttackDamage(atkType, move);
    String attackString = getAttackString(atkType, move);
    p.takeDamage(attackDmg);
    return getName() + " has " + attackString + " " + p.getName() + " dealing " + attackDmg + " damage!";
  }

  /**
   * Returns the partial string for the attack.
   * @param atkType Attack Type represented as int.
   * @param move Move represented as int.
   * @return Verb of the attack, partial string for the full attack string, [Error] if invalid atkType.
   */
  public String getAttackString(int atkType, int move) {
    if (atkType == 1) {
      switch(move) {
        case 1:
          return "SLAMMED";
        case 2:
          return "TACKLED";
        case 3:
          return "PUNCHED";
      }
    }

    return "[ERROR]";
  }

  /**
   * Returns the attack damage.
   * @param atkType
   * @param move
   * @return
   */
  public int getAttackDamage(int atkType, int move) {
    if (atkType == 1) {
      switch (move) {
        case 1:
          return 1; // TODO: Determine Attack Damage
        case 2:
          return 2; // TODO: Determine Attack Damage
        case 3:
          return 3; // TODO: Determine Attack Damage
      }
    }

    return -1;
  }



  /*
  * Represents the slam move.
  * Damage of 0-5 dealt.
  *
  * @param p Pokemon to attack.
  * @return Message describing the event.
  */
  @Deprecated
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
  @Deprecated
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
  @Deprecated
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