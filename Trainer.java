import java.util.ArrayList;
import java.awt.Point;

/**
 * Represents the player.
 * Player has 25 HP.
 */
public class Trainer extends Entity {

  private int money, potions, pokeballs;
  private Point loc;
  private ArrayList<Pokemon> pokemon;

  /**
   * Constructor, makes a new player.
   * @param n Name
   * @param p Starter Pokemon
   */
  public Trainer(String n, Pokemon p) {
    // Super Constructor: Entity(Name, Max HP)
    super(n, 25, 25);
    this.pokemon = new ArrayList<>();
    this.pokemon.add(p);
    this.loc = Map.getInstance().findStart();
    money = 25;
    potions = 1;
    pokeballs = 5;
  }

  /**
   * Getter, returns the Trainer's money.
   * @return Trainer's money amount.
   */
  public int getMoney() { return this.money; }

  /**
   * Spends the amount given if the Trainer has it.
   * If the Trainer has insufficient funds, nothing happens and returns false.
   * @param amt Amount of money to spend.
   * @return True if the Trainer spent it successfully. Otherwise, false.
   */
  public boolean spendMoney(int amt) {
    if (this.money < amt) { return false; }
    this.money -= amt;
    return true;
  }

  /**
   * Adds money to the Trainer's wallet.
   * Will not do anything if value input is negative.
   * To take away money, use Trainer.spendMoney(int).
   * To zero money, use Trainer.spendMoney(Trainer.getMoney()).
   * @param amt Amount of money to give to the Trainer.
   */
  public void receiveMoney(int amt) {
    if (amt < 0) { return; }
    this.money += amt;
  }

  /**
   * Returns if the trainer has any potions in their inventory.
   * @return True if they have 1+ potions. False otherwise.
   */
  public boolean hasPotion() { return (potions > 0); }

  /**
   * Adds a potion to the Trainer's inventory.
   */
  public void receivePotion() { potions++; }

  /**
   * Heals the selected Pokemon based on index.
   * Pokemon Index begins at 1.
   * If the given Pokemon is invalid, nothing happens.
   * @param pokeIndex Index of the Pokemon (Starting from 1)
   */
  public void usePotion(int pokeIndex) {
    Pokemon p = getPokemon(pokeIndex);
    if (p == null || !hasPotion()) { return; }
    p.heal();
    pokemon.set(pokeIndex - 1, PokemonGenerator.getInstance().addRandomBuff(p));
    potions--;
  }

  /**
   * Returns if the trainer has any pokeballs in their inventory.
   * @return True if they have 1+ pokeballs. False otherwise.
   */
  public boolean hasPokeball() { return (pokeballs > 0); }

  /**
   * Adds a pokeball to the trainer's inventory.
   */
  public void receivePokeball() { pokeballs++; }

  /**
   * Attempts to capture the Pokemon.
   * @return True if successful. Otherwise, false.
   */
  public boolean catchPokemon(Pokemon p) {
    double captureChance = ((double)(p.getMaxHp()-p.getHp()) / (double)(p.getMaxHp()));

    if (pokeballs <= 0) { return false; }
    pokeballs--;

    if (Math.random() <= captureChance) {
      this.pokemon.add(p);

      // Verify Size, max of 6
      if (pokemon.size() > 6) {
        System.out.println("You have too many pokemon in your team! You gotta get rid of one!");
        System.out.println(getPokemonList());
        int removeIndex = CheckInput.getIntRange(1, pokemon.size());
        System.out.println("Say goodbye to " + pokemon.get(removeIndex-1).getName() + "! :c");
        pokemon.remove(removeIndex-1);
      }
      return true;
    }
    return false;
  }

  /**
   * Getter, returns location defined by a Point Object.
   * @return Location, defined as a Point Object.
   */
  public Point getLocation() { return loc; }

  /**
   * Moves the player north if a valid location.
   * Location is based on Multi-Dimensional Array Map: [x][y]
   * -x goes North
   * @return If successful, char at new location. Otherwise, '\0'.
   */
  public char goNorth() {
    Point destination = new Point((int)this.loc.getX() - 1, (int)this.loc.getY());
    if (Map.getInstance().getCharAtLoc(destination) == '\0') { return '\0'; }
    this.loc = destination;
    Map.getInstance().reveal(loc);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /**
   * Moves the player south if a valid location.
   * Location is based on Multi-Dimensional Array Map: [x][y]
   * +x goes South
   * Will return 'f' and automatically switch maps when moved onto a goal spot.
   * @return If successful, char at new location. Otherwise, '\0'.
   */
  public char goSouth() {
    Point destination = new Point((int)this.loc.getX() + 1, (int)this.loc.getY());
    if (Map.getInstance().getCharAtLoc(destination) == '\0') { return '\0'; }
    this.loc = destination;
    Map.getInstance().reveal(loc);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /**
   * Moves the player east if a valid location.
   * Location is based on Multi-Dimensional Array Map: [x][y]
   * +y goes East
   * Will return 'f' and automatically switch maps when moved onto a goal spot.
   * @return If successful, char at new location. Otherwise, '\0'.
   */
  public char goEast() {
    Point destination = new Point((int)this.loc.getX(), (int)this.loc.getY() + 1);
    if (Map.getInstance().getCharAtLoc(destination) == '\0') { return '\0'; }
    this.loc = destination;
    Map.getInstance().reveal(loc);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /**
   * Moves the player west if a valid location.
   * Location is based on Multi-Dimensional Array Map: [x][y]
   * -y goes West
   * Will return 'f' and automatically switch maps when moved onto a goal spot.
   * @return If successful, char at new location. Otherwise, '\0'.
   */
  public char goWest() {
    Point destination = new Point((int)this.loc.getX(), (int)this.loc.getY() - 1);
    if (Map.getInstance().getCharAtLoc(destination) == '\0') { return '\0'; }
    this.loc = destination;
    Map.getInstance().reveal(loc);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /**
   * Gets the number of pokemon the Trainer has.
   * @return The number of pokemon the Trainer has..
   */
  public int getNumPokemon() { return pokemon.size(); }

  /**
   * Represents PokeCenter healing.
   * Heals all Pokemon on the Trainer.
   */
  public void healAllPokemon() {
    for (Pokemon p : pokemon) {
      p.heal();
    }
  }

  /**
   * Buffs all Pokemon.
   * Represents a level up for all pokemon, happens on Gym Battle Win.
   */
  public void buffAllPokemon(){
    for(int i = 0; i < pokemon.size(); i++){
      pokemon.set(i, PokemonGenerator.getInstance().addRandomBuff(pokemon.get(i)));
    }
  }

  /**
   * Debuffs a specific pokemon based on index.
   * Pokemon Index begins at 1.
   * Pokemon Index defined in this way because most menus start at 1.
   * @param index Index of the Pokemon (Starting from 1)
   */
  public void debuffPokemon(int index){
    Pokemon p = getPokemon(index);
    pokemon.set(index - 1, PokemonGenerator.getInstance().addRandomDebuff(p));
  }

  /**
   * Gets the Pokemon based on it's index number.
   * Pokemon Index begins at 1.
   * Pokemon Index defined in this way because most menus start at 1.
   * Returns null with an invalid index.
   * @param index Index of the Pokemon (Starting from 1)
   * @return Pokemon if a valid index is provided. Otherwise, null.
   */
  public Pokemon getPokemon(int index) {
    if (index < 1 || index > pokemon.size()) { return null; }
    return pokemon.get(index-1);
  }

  /**
   * Returns a list of the Pokemon formatted with the
   * Pokemon ID, name, then line break.
   */
  public String getPokemonList() {
    int i = 1;
    String returnString = "";
    for (Pokemon p : pokemon) {
      returnString += i + ". " + p.toString();
      i++;
    }
    return returnString;
  }

  /**
   * The toString() will return the name, hp/maxHp,
   * inventory, list of pokémon, and the map.
   */
  @Override
  public String toString() {
    String returnString = "";
    returnString += getName() + " HP: " + getHp() + "/" + getMaxHp() + "\n";
    returnString += "Money: " + this.money + "\n";
    returnString += "Potions: " + this.potions + "\n";
    returnString += "Poke Balls: " + this.pokeballs + "\n";
    returnString += "Pokemon\n-------\n";
    returnString += getPokemonList() + "\n";
    returnString += "Map:" + "\n";
    returnString += Map.getInstance().mapToString(this.loc);
    return returnString;
  }
}