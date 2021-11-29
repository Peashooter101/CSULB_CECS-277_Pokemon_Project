import java.util.HashMap;
import java.io.*;
import java.util.Scanner;

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
		} catch (FileNotFoundException e) { System.out.println("[ERROR] PokemonList.txt not found."); }
	}
	
	public static PokemonGenerator getInstance() {
		if (instance == null) {
			instance = new PokemonGenerator();
		}
		return instance;
	}
	
	public Pokemon generateRandomPokemon(int level) {
		
	}
	
	public Pokemon getPokemon(String name) {
		
	}
	
	public Pokemon addRandomBuff(Pokemon p) {
		
	}
	
	public Pokemon addRandomDebuff(Pokemon p) {
		
	}
}
