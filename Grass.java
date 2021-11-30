import java.util.Random;

public class Grass extends Pokemon {

  public Grass(String n, int h, int m) {
    super(n,h,m);
  }

  /**
   * Returns a string representing the menu.
   * @param atkType Attack Type represented as int.
   * @return Menu for Attack Type, otherwise "INVALID MENU".
   */
  @Override
  public String getAttackMenu(int atkType) {
    if (atkType == 1) { super.getAttackMenu(atkType); }
    if (atkType == 2) { return "ATTACK MENU"; }

    return "[FATAL ERROR] INVALID ATTACK MENU in " + this.getClass().getName();
  }

  /**
   * Returns the number of items in the menu.
   * @param atkType Attack Type represented as int.
   * @return Number representing the attack menu items, invalid atkType returns -1.
   */
  @Override
  public int getNumAttackMenuItems(int atkType) {
    if (atkType == 1) { super.getNumAttackMenuItems(atkType); }
    if (atkType == 2) { return 3; }
    return -1;
  }

  /**
   * Returns the partial string for the attack.
   * @param atkType Attack Type represented as int.
   * @param move Move represented as int.
   * @return Verb of the attack, partial string for the full attack string, [Error] if invalid atkType.
   */
  @Override
  public String getAttackString(int atkType, int move) {
    if (atkType == 2) {
      switch(move) {
        case 1:
          return "WHIPPED WITH A VINE";
        case 2:
          return "CUT BY LEAVES";
        case 3:
          return "BEAMED BY THE SUN";
        default:
          return "[ERROR ATK MOVE]";
      }
    }
    return super.getAttackString(atkType, move);
  }

  /**
   * Returns the attack damage.
   * @param atkType Attack Type represented as int.
   * @param move Move represented as int.
   * @return Damage value to be used, -1 if invalid atkType / move.
   */
  @Override
  public int getAttackDamage(int atkType, int move) {
    if (atkType == 2) {
      Random random = new Random();
      switch (move) {
        case 1:
          return random.nextInt(3) + 1;
        case 2:
          return random.nextInt(3) + 2;
        case 3:
          return random.nextInt(6);
        default:
          return -1;
      }
    }
    return super.getAttackDamage(atkType, move);
  }

  /**
   * Returns the attack multiplier based on the battle table.
   * TODO: Figure out why this is overridden here.
   * @param p Pokemon
   * @param atkType Attack Type represented by int.
   * @return Multiplier depending on types, 1.0 if atkType is 1 (Basic).
   */
  @Override
  public double getAttackMultiplier(Pokemon p, int atkType) {
    return super.getAttackMultiplier(p, atkType);
  }

}