import java.util.HashMap;
import java.io.*;
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
		
		if (instance == null) {
			instance = new PokemonGenerator();
		}
		return instance;
		
	}
	
	public Pokemon generateRandomPokemon(int level) {
		
		ArrayList<String> keys = new ArrayList<String>();
		int randIndex = (int)(Math.random() * pokemon.size());
		Pokemon randomPokemon;
		
		for (String key : pokemon.keySet()) {
			keys.add(key);
		}
		
		switch(pokemon.get(keys.get(randIndex))) {
		
		case "Fire":
			System.out.println("Fire");
			randomPokemon = new Fire(keys.get(randIndex), 25, 25);
			break;
			
		case "Grass":
			System.out.println("Grass");
			randomPokemon = new Grass(keys.get(randIndex), 25, 25);
			break;
			
		case "Water":
			System.out.println("Water");
			randomPokemon = new Water(keys.get(randIndex), 25, 25);
			break;
		
		default:
			System.out.println("Unable to find correct type");
			randomPokemon = null;
		}
		
		return randomPokemon;
		
	}
	
	public Pokemon getPokemon(String name) {
		
		
	}
	
	public Pokemon addRandomBuff(Pokemon p) {
		
		int randomBuff = (int)(Math.random() * 1);
		
		switch(randomBuff) {
		
		case 0:
			//buff
		case 1:
			//buff
			
		}
		
	}
	
	public Pokemon addRandomDebuff(Pokemon p) {
		
		int randomDebuff = (int)(Math.random() * 1);
		
		switch(randomDebuff) {
		
		case 0:
			//debuff
		case 1:
			//debuff
			
		}
		
	}
}
