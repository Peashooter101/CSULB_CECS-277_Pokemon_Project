import java.util.ArrayList;

/**
 * Class for testing aspects of the program.
 */
public class Test {

    public static void main(String[] args) {

        System.out.println("Testing Systems...");

        try { testPokemonGen(); }
        catch (Exception e) { System.out.println("Failed to successfully generate a Random Pokemon"); }

        try { testPokemonDebuff(); }
        catch (Exception e) { System.out.println("Failed to successfully debuff a Pokemon"); }

        try { testPokemonBuff(); }
        catch (Exception e) { System.out.println("Failed to successfully buff a Pokemon"); }

        try { testPokemonBuffArraylist(); }
        catch (Exception e) { System.out.println("Failed to successfully buff a Pokemon"); }

    }

    private static void testPokemonGen() {
        System.out.println("╔═════\n║ Pokemon Generator\n╚═════");
        Pokemon p1 = PokemonGenerator.getInstance().generateRandomPokemon(1);
        System.out.print("Random Pokemon Level 1: " + p1.toString());
        System.out.println("   Type: " + p1.getType());
        Pokemon p5 = PokemonGenerator.getInstance().generateRandomPokemon(5);
        System.out.print("Random Pokemon Level 5: " + p5.toString());
        System.out.println("   Type: " + p5.getType());
        Pokemon p10 = PokemonGenerator.getInstance().generateRandomPokemon(10);
        System.out.print("Random Pokemon Level 10: " + p10.toString());
        System.out.println("   Type: " + p10.getType());
    }

    private static void testPokemonDebuff() {
        System.out.println("╔═════\n║ Pokemon Debuff\n╚═════");
        Pokemon p1 = PokemonGenerator.getInstance().generateRandomPokemon(1);
        System.out.print("Random Pokemon Level 1: " + p1.toString());
        p1 = PokemonGenerator.getInstance().addRandomDebuff(p1);
        p1 = PokemonGenerator.getInstance().addRandomDebuff(p1);
        p1 = PokemonGenerator.getInstance().addRandomDebuff(p1);
        p1 = PokemonGenerator.getInstance().addRandomDebuff(p1);
        p1 = PokemonGenerator.getInstance().addRandomDebuff(p1);
        System.out.print("Random Pokemon Level 1 w/ 5 Debuffs: " + p1.toString());
    }

    private static void testPokemonBuff() {
        System.out.println("╔═════\n║ Pokemon Buff\n╚═════");
        Pokemon p1 = PokemonGenerator.getInstance().generateRandomPokemon(1);
        System.out.print("Random Pokemon Level 1: " + p1.toString());
        p1.takeDamage(10);
        System.out.print("Random Pokemon Level 1 (-10 HP): " + p1.toString());
        p1 = PokemonGenerator.getInstance().addRandomBuff(p1);
        p1 = PokemonGenerator.getInstance().addRandomBuff(p1);
        p1 = PokemonGenerator.getInstance().addRandomBuff(p1);
        p1 = PokemonGenerator.getInstance().addRandomBuff(p1);
        p1 = PokemonGenerator.getInstance().addRandomBuff(p1);
        System.out.print("Random Pokemon Level 1 w/ 5 Buffs: " + p1.toString());
    }

    private static void testPokemonBuffArraylist() {
        System.out.println("╔═════\n║ Pokemon Buff ArrayList Use\n╚═════");
        ArrayList<Pokemon> pokemon = new ArrayList<>();
        pokemon.add(PokemonGenerator.getInstance().generateRandomPokemon(1));
        pokemon.add(PokemonGenerator.getInstance().generateRandomPokemon(1));
        pokemon.add(PokemonGenerator.getInstance().generateRandomPokemon(1));
        System.out.println("Random Pokemon Level 1 in ArrayList (x3):");
        for (Pokemon p : pokemon) {
            System.out.print("   " + p.toString());
        }
        pokemon.set(1, PokemonGenerator.getInstance().addRandomBuff(pokemon.get(1)));
        pokemon.set(2, PokemonGenerator.getInstance().addRandomDebuff(pokemon.get(2)));
        System.out.println("Random Pokemon Level 1 in ArrayList (x3) (Index 1 Buffed / Index 2 Debuffed):");
        for (Pokemon p : pokemon) {
            System.out.print("   " + p.toString());
        }
    }

}
