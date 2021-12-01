/**
 * Defines any entity within this game.
 * Entities describe any "living thing".
 */
public abstract class Entity {
  private String name;
  private int hp;
  private int maxHp;

  /**
   * Constructor.
   * Defines an entity's base parts.
   * @param n Entity Name
   * @param m Maximum Hit Points
   */
  public Entity(String n, int h, int m) {
    this.name = n;
    this.hp = h;
    this.maxHp = m;
  }

  /**
   * Getter, returns the Entity's Name.
   * @return The entity's name.
   */
  public String getName() { return this.name; }

  /**
   * Getter, returns the Entity's HP.
   * @return The entity's HP.
   */
  public int getHp() { return this.hp; }

  /**
   * Getter, returns the Entity's Maximum HP.
   * @return The entity's Maximum HP.
   */
  public int getMaxHp() { return this.maxHp; }


  /**
   * Applies damage to the Entity's HP.
   * @param d Damage taken.
   */
  public void takeDamage(int d) {
    this.hp -= d;
    if (this.hp < 0) { this.hp = 0; }
  }

  /**
   * Restores HP to Max.
   */
  public void heal() {
    this.hp = this.maxHp;
  }

  /**
   * Formatted String representing the following information:
   * Name HP: hp/maxHp
   * @return The resulting string representing the Entity Object.
   */
  @Override
  public String toString() {
    return name + " HP: " + this.hp + "/" + this.maxHp + "\n";
  }
}