import java.util.HashMap;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Singleton Class used to create Pokemon from a provided list.
 */
public class PokemonGenerator {
	
	private HashMap<String, String> pokemon;
	private static PokemonGenerator instance = null;

	/**
	 * Private Constructor
	 * Accessible only through getInstance().
	 * Only one instance of this class may exist.
	 */
	private PokemonGenerator() {
		try {
			pokemon = new HashMap<>();
			Scanner r = new Scanner(new File("PokemonList.txt"));
			
			while (r.hasNextLine()) {
				String[] temp = r.nextLine().split(",");
				pokemon.put(temp[0], temp[1]);
			}
		} catch (FileNotFoundException e) { System.out.println("PokemonList.txt not found."); }
	}

	/**
	 * Retrieves the PokemonGenerator.
	 * If instance does not exist, create it.
	 * @return PokemonGenerator Instance
	 */
	public static PokemonGenerator getInstance() {
		if (instance == null) { instance = new PokemonGenerator(); }
		return instance;
	}

	/**
	 * Returns a random Pokemon.
	 * A valid Pokemon is polymorphic to Fire / Water / Grass
	 * Every level above 1 adds a buff.
	 * @param level Level Number
	 * @return Random Pokemon of that level.
	 */
	public Pokemon generateRandomPokemon(int level) {
		Random rand = new Random();
		int randIndex = rand.nextInt(pokemon.size());
		String randPokemonName = pokemon.keySet().toArray(new String[0])[randIndex];
		Pokemon randomPokemon = getPokemon(randPokemonName);

		for (int i = 1; i < level; i++) {
			randomPokemon = addRandomBuff(randomPokemon);
		}
		return randomPokemon;
	}

	/**
	 * Returns a Pokemon Object based on the name.
	 * This pokemon is a basic pokemon (Level 1).
	 * @param name Name of the Pokemon
	 * @return Level 1 Pokemon Object (No Buffs / Debuffs).
	 */
	public Pokemon getPokemon(String name) {
		Random rand = new Random();
		int randHp = rand.nextInt(6) + 20;
		switch(pokemon.get(name)) {
			case "Fire":
				return new Fire(name, randHp, randHp);
			case "Grass":
				return new Grass(name, randHp, randHp);
			case "Water":
				return new Water(name, randHp, randHp);
			default:
				System.out.println("Unable to find correct type");
				return null;
		}
	}

	/**
	 * Returns a Pokemon decorated with a random buff.
	 * @param p Pokemon to buff.
	 * @return Buffed Pokemon
	 */
	public Pokemon addRandomBuff(Pokemon p) {
		Random rand = new Random();
		if (rand.nextInt(2) == 0) { return new AttackUp(p); }
		return new HpUp(p);
	}

	/**
	 * Returns a Pokemon decorated with a random debuff.
	 * @param p Pokemon to debuff.
	 * @return Debuffed Pokemon
	 */
	public Pokemon addRandomDebuff(Pokemon p) {
		Random rand = new Random();
		if (rand.nextInt(2) == 0) { return new AttackDown(p); }
		return new HpDown(p);
	}
}
