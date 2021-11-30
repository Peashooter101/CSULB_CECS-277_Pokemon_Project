import java.util.HashMap;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class PokemonGenerator {
	
	private HashMap<String, String> pokemon;
	private static PokemonGenerator instance = null;
	
	private PokemonGenerator() {
		try {
			pokemon = new HashMap<String, String>();
			Scanner r = new Scanner(new File("PokemonList.txt"));
			
			while (r.hasNextLine()) {
				String[] temp = r.nextLine().split(",");
				pokemon.put(temp[0], temp[1]);
			}
		} catch (FileNotFoundException e) { System.out.println("PokemonList.txt not found."); }
	}
	
	public static PokemonGenerator getInstance() {
		if (instance == null) { instance = new PokemonGenerator(); }
		return instance;
	}
	
	public Pokemon generateRandomPokemon(int level) {
		Random rand = new Random();
		int randIndex = (int)(Math.random() * pokemon.size());
		String randPokemonName = pokemon.keySet().toArray(new String[0])[randIndex];
		Pokemon randomPokemon = getPokemon(randPokemonName);

		for (int i = 1; i < level; i++) {
			randomPokemon = addRandomBuff(randomPokemon);
		}
		return randomPokemon;
	}
	
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
	
	public Pokemon addRandomBuff(Pokemon p) {
		Random rand = new Random();
		if (rand.nextInt(2) == 0) { return new AttackUp(p); }
		return new HpUp(p);
	}
	
	public Pokemon addRandomDebuff(Pokemon p) {
		Random rand = new Random();
		if (rand.nextInt(2) == 0) { return new AttackDown(p); }
		return new HpDown(p);
	}
}
