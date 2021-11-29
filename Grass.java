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

    return "[FATAL ERROR] INVALID ATTACK MENU in " + this.getClass().getname();
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
    if (atkType == 1) { super.getAttackString(atkType, move); }
    if (atkType == 2) {
      switch(move) {
        case 1:
          return "SPECIAL 1";
        case 2:
          return "SPECIAL 2";
        case 3:
          return "SPECIAL 3";
      }
    }

    return "[ERROR]";
  }

  public int getAttackDamage(int atkType, int move) {

  }

  public double getAttackMulitplier(Pokemon p, int atkType) {

  }

}