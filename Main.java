import java.awt.Point;
import java.util.Random;

class Main {
  /*
  * Game Execution
  */
  public static void main(String[] args) {
    
    // Constants
    final Point MAP_1_FINISH = new Point(4, 4);
    final Point MAP_2_FINISH = new Point(0, 3);
    final Point MAP_3_FINISH = new Point(2, 0);

    // Program Variables
    Trainer player;
    Map map = new Map();
    Random rand = new Random();
    
    // Menu Boolean
    boolean mainMenuActive = true;

    // Prompt Name and Pokemon
    String playerName;
    Pokemon playerPokemon = null;

    System.out.println("Prof. Oak: Hello there new trainer! What is your name?");
    playerName = CheckInput.getString();

    System.out.println("Great to meet you " + playerName + "\nChoose your first Pokemon:");
    System.out.println("1. Charmander\n2. Bulbasaur\n3. Squirtle");
    // TODO: Give player a starter.
    System.out.println();

    // Create Player Trainer
    player = new Trainer(playerName, playerPokemon, map);

    // Prompt Main Menu
    while (mainMenuActive) {
      char mapMarker = 'Q';

      // Exit Condition: Player Death
      if (player.getHp() == 0) {
        System.out.println("You have died...");
        mainMenuActive = false;
        continue;
      }

      // Trainer Display
      System.out.println(player.toString());

      // Main Menu Selection
      switch(mainMenu()) {
        case 1:
          mapMarker = player.goNorth();
          break;
        case 2:
          mapMarker = player.goSouth();
          break;
        case 3:
          mapMarker = player.goEast();
          break;
        case 4:
          mapMarker = player.goWest();
          break;
        case 5:
          mainMenuActive = false;
          break;
      }

      // == Process Map Markers == \\
      // Exit Condition: Player Quit
      if (mapMarker == 'Q') {
        mainMenuActive = false;
      }
      // Invalid Direction
      if (mapMarker == '\0') {
        System.out.println("You cannot go this way...\n");
      }
      // Finish and Switch Maps
      if (mapMarker == 'f') {
        System.out.println("You found the finish!\n");
        if (player.getLocation().equals(MAP_1_FINISH)) {
          map.loadMap(2);
          map.reveal(player.getLocation());
        }
        if (player.getLocation().equals(MAP_2_FINISH)) {
          map.loadMap(3);
          map.reveal(player.getLocation());
        }
        if (player.getLocation().equals(MAP_3_FINISH)) {
          map.loadMap(1);
          map.reveal(player.getLocation());
        }
      }
      // Nothing Found
      if (mapMarker == 'n') {
        System.out.println("There's nothing here...\n");
      }
      // Item Found
      if (mapMarker == 'i') {
        switch(rand.nextInt(2)) {
          case 0:
            player.receivePokeball();
            System.out.println("You found a Pokeball!\n");
            break;
          case 1:
            player.receivePotion();
            System.out.println("You found a Potion!\n");
            break;
        }
        map.removeCharAtLoc(player.getLocation());
      }
      // Wild Pokemon Encounter
      if (mapMarker == 'w') {
        Pokemon wild = chooseRandomPokemon();
        System.out.println("You encountered a wild " + wild.getName() + "!\n");
        int pokemonNum = player.getNumPokemon();
        trainerAttack(player, wild);

        // If Pokemon is defeated.
        if (wild.getHp() == 0) {
          int moneyGained = rand.nextInt(7) + 4;
          map.removeCharAtLoc(player.getLocation());
          player.receiveMoney(moneyGained);
          System.out.println("You defeated the wild " + wild.getName() + "!");
          System.out.println("You gained " + moneyGained + " money for defeating the wild " + wild.getName() + "!\n");
        };

        // If Pokemon was captured.
        if (pokemonNum != player.getNumPokemon()) { map.removeCharAtLoc(player.getLocation()); }
      }
      // Person Encounter
      if (mapMarker == 'p') {
        Point eventLoc = player.getLocation();
        switch(rand.nextInt(3)) {
          // Misty Encounter
          case 0:
            int dmgMisty = rand.nextInt(3) + 1;
            System.out.println(player.getName() + ": \"Hey Misty! You okay? You're looking pretty hot today.\"");
            player.takeDamage(dmgMisty);
            System.out.println("Misty gasps and slaps you, dealing " + dmgMisty + " damage");

            if (player.getHp() != 0) {
              System.out.println(player.getName() + ": \"OW, I WAS TALKING ABOUT THE HEAT TODAY!\"");
              System.out.println("Misty: \"Oh sorry... Let me go to the PokeMart and get you some medicine.\"");
              System.out.println("Misty leaves the area embarrassed...\n");
            }
            break;
          // Help a Team Rocket Grunt Encounter
          case 1:
            Pokemon pokemon = chooseRandomPokemon();

            System.out.println("A Team Rocket Grunt is screaming, running away from an angered " + pokemon.getName() + "!");
            System.out.println("Do you help him? (Y/N)");

            if (CheckInput.getYesNo()) {
              trainerAttack(player, pokemon);
              // If Pokemon was defeated.
              if (pokemon.getHp() == 0) {
                int moneyGained = rand.nextInt(7) + 4;
                player.receiveMoney(moneyGained);
                System.out.println("You gained " + moneyGained + " money for defeating " + pokemon.getName());
              }
            }
            break;
          // Strange Man Encounter
          case 2:
            System.out.println("A strange man approaches you...");
            System.out.println("Strange Man: \"Listen, I will give you $20 if this coin lands on heads...\"");
            System.out.println("Do you accept? (Y/N)");
            if (!CheckInput.getYesNo()) {
              System.out.println("You ignore the strange man...\n");
              break;
            }
            System.out.println("The strange man flips the coin...");
            if (rand.nextInt(2) == 0) {
              System.out.println("The coin lands on heads... The strange man hands you $20 and leaves...");
              System.out.println("You wonder what his deal is... You pocket the $20...\n");
              player.receiveMoney(20);
            } else {
              int dmgMan = rand.nextInt(4) + 2;
              System.out.println("The coin lands on heads... The strange man punches you in the face and leaves...");
              System.out.println("You take " + dmgMan + " damage...\n");
              player.takeDamage(dmgMan);
            }
            break;
        }
        map.removeCharAtLoc(eventLoc);
      }
      // City Encounter
      if (mapMarker == 'c') {
        store(player);
      }

    }
    
    // Exit Game
    System.out.println("Game Over");
  }

  /*
  * Prompts and returns the menu choice.
  *
  * @return Integer representing the choice. This is bounded by [1,5]
  */
  static int mainMenu() {
    final String MAIN_MENU = "Main Menu:\n1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit";
    System.out.println(MAIN_MENU);
    return CheckInput.getIntRange(1,5);
  }

  /*
  * Chooses a random pokemon.
  *
  * @return A random pokemon as a Pokemon Object.
  */
  static Pokemon chooseRandomPokemon() {
    Random rand = new Random();
    // TODO: Return random pokemon
    return null;
  }

  /*
  * Handles the entire combat system.
  * 
  * @param t Represents the attacking Trainer
  * @param wild Represents the wild Pokemon encountered.
  */
  static void trainerAttack(Trainer t, Pokemon wild) {
    
    final String FIGHT_MENU = "What will you do?\n1. Fight\n2. Use Potion\n3. Throw Poke Ball\n4. Run Away";

    boolean fightCycle = true;
    boolean allFaintCheck;
    Random rand = new Random();

    while (fightCycle) {
      System.out.println("Opponent\n" + wild.toString());

      // Exit Conditions: Battle Ends due to Pokemon Faint or Player Death.
      if (wild.getHp() == 0) { return; }
      if (t.getHp() == 0) { return; }

      // Checks if all user's pokemon are fainted
      allFaintCheck = true;
      for (int i = 1; i <= t.getNumPokemon(); i++) {
        if (t.getPokemon(i).getHp() != 0) { allFaintCheck = false; }
      }
      if (allFaintCheck) { 
        int dmg = rand.nextInt(5) + 1;
        System.out.println("All of your Pokemon have fainted!");
        System.out.println(wild.getName() + " charges at you and you take " + dmg + " damage!");
        t.takeDamage(dmg);
        System.out.println(wild.getName() + " begins to flee...");
        System.out.println("");
        return;
       }

      // Prompt and make selection.
      System.out.println(FIGHT_MENU);
      switch (CheckInput.getIntRange(1,4)) {
        // Fight
        case 1:
          // Prompt for Pokemon
          System.out.println("Choose a Pokemon to fight:");
          System.out.print(t.getPokemonList());
          Pokemon chosenPokemon = t.getPokemon(CheckInput.getIntRange(1,t.getNumPokemon()));
          System.out.println("");

          // If Pokemon is fainted.
          if (chosenPokemon.getHp() == 0) {
            int dmg = rand.nextInt(5) + 1;
            System.out.println(chosenPokemon.getName() + " is unable to battle!");
            System.out.println(wild.getName() + " charges at you and you take " + dmg + " damage!");
            t.takeDamage(dmg);
            System.out.println("");
            break;
          }

          System.out.println(chosenPokemon.getName() + ", I choose you!");

          // Choose move.
          System.out.println(chosenPokemon.getAttackMenu());
          if (CheckInput.getIntRange(1,chosenPokemon.getNumAttackMenuItems()) == 1) {
            System.out.println(chosenPokemon.getBasicMenu());
            System.out.println(chosenPokemon.basicAttack(wild, CheckInput.getIntRange(1,chosenPokemon.getNumBasicMenuItems())));
          } else {
            System.out.println(chosenPokemon.getSpecialMenu());
            System.out.println(chosenPokemon.specialAttack(wild, CheckInput.getIntRange(1,chosenPokemon.getNumSpecialMenuItems())));
          }

          // If Pokemon was defeated mid-turn.
          if (wild.getHp() == 0) { break; }

          // Pokemon Random Move
          System.out.println("The wild " + wild.getName() + " is attacking!");
          if (rand.nextInt(2) == 0) {
            System.out.println(wild.basicAttack(chosenPokemon, rand.nextInt(3) + 1));
          } else {
            System.out.println(wild.specialAttack(chosenPokemon, rand.nextInt(3) + 1));
          }

          System.out.println("");

          break;
        // Use Potion
        case 2:
          int pokeChoice;
          Pokemon pokemon;

          if (!t.hasPotion()) {
            System.out.println("You do not have any potions!\n");
            break;
          }

          System.out.println("Which Pokemon do you want to heal:");
          System.out.print(t.getPokemonList());

          pokeChoice = CheckInput.getIntRange(1,t.getNumPokemon());
          pokemon = t.getPokemon(pokeChoice);

          if (pokemon.getHp() == pokemon.getMaxHp()) {
            System.out.println(pokemon.getName() + " is already at full health!\n");
          } else if (pokemon.getHp() == 0) {
            System.out.println("You cannot use a potion on a fainted Pokemon!\n");
          } else {
            t.usePotion(pokeChoice);
            System.out.println("You used a potion:");
            System.out.println(pokemon.toString());
          }
          break;
        // Use Pokeball
        case 3:
          if (!t.hasPokeball()) {
            System.out.println("You do not have any pokeballs!\n");
            break;
          }
          
          if (t.catchPokemon(wild)) {
            System.out.println(wild.getName() + " was successfully captured!\n");
            return;
          } else {
            System.out.println("Failed to capture " + wild.getName() + "!\n");
          }
          break;
        // Run Away
        case 4:
          switch(rand.nextInt(4)) {
            case 0:
              if (t.goNorth() != '\0') { break; }
            case 1:
              if (t.goSouth() != '\0') { break; }
            case 2:
              if (t.goEast() != '\0') { break; }
            case 3:
              if (t.goWest() != '\0') { break; }
              if (t.goNorth() != '\0') { break; }
              if (t.goSouth() != '\0') { break; }
              if (t.goEast() != '\0') { break; }
          }
          System.out.println("You fled the battle...\n");
          return;
      }
    }
  }

  /*
  * Handles City Encounter
  * Includes PokeCenter and PokeMart
  *
  * @param t Represents the Trainer.
  */
  static void store(Trainer t) {
    // Constants
    final int COST_POKEBALL = 3;
    final int COST_POTION = 5;
    final String STORE_MENU_MAIN = "You find yourself in the city, where will you go?\n1. PokeCenter\n2. PokeMart\n3. Leave";
    final String STORE_MENU_POKECENTER = "Nurse Joy: \"Welcome to the PokeCenter!\"\nNurse Joy: \"Would you like me to take your pokemon to rest? (Y/N)\"";
    final String STORE_MENU_POKEMART = "Store Clerk: \"Welcome to the PokeMart, how may I help you?\"\n1. Buy Potion ($" + COST_POTION + ")\n2. Buy Pokeball ($" + COST_POKEBALL + ")\n3. Leave";

    // Menu Variables
    boolean mainMenu = true;
    boolean martMenu = true;

    while (mainMenu) {

      System.out.println(STORE_MENU_MAIN);
      switch(CheckInput.getIntRange(1,3)) {
        case 1:
          System.out.println(STORE_MENU_POKECENTER);
          if (CheckInput.getYesNo()) {
            System.out.println("Nurse Joy: \"Alright we will take your Pokemon for a moment...\"");
            t.healAllPokemon();
            System.out.println("Nurse Joy: \"We have restored your Pokemon to full health!\"");
          }
          System.out.println("Nurse Joy: \"Thanks for stopping by! Good luck on your travels!\"");
          break;
        case 2:
          martMenu = true;
          while (martMenu) {
            System.out.println("You have $" + t.getMoney() + ".");
            System.out.println(STORE_MENU_POKEMART);
            switch(CheckInput.getIntRange(1,3)) {
              case 1:
                if (t.getMoney() < COST_POTION) {
                  System.out.println("Store Clerk: \"Sorry, you don't have enough money to buy a potion!\"");
                } else {
                  t.spendMoney(COST_POTION);
                  t.receivePotion();
                  System.out.println("Store Clerk: \"Thank you for your purchase!\"");
                }
                break;
              case 2:
                if (t.getMoney() < COST_POKEBALL) {
                  System.out.println("Store Clerk: \"Sorry, you don't have enough money to buy a pokeball!\"");
                } else {
                  t.spendMoney(COST_POKEBALL);
                  t.receivePokeball();
                  System.out.println("Store Clerk: \"Thank you for your purchase!\"");
                }
                break;
              case 3:
                martMenu = false;
                break;
            }
          }
          System.out.println("\nStore Clerk: \"Thank you for stopping by!\"");
          break;
        case 3:
          mainMenu = false;
          break;
      }
      System.out.println();
    }
    
    System.out.println("You leave the city...\n");

  }
}