/**
 * Class for testing aspects of the program.
 */
public class Test {

    public static void main(String[] args) {

        System.out.println("Testing Systems...");

        try { testPokemonGen(); }
        catch (Exception e) { System.out.println("Failed to successfully generate a Random Pokemon"); }

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

}
