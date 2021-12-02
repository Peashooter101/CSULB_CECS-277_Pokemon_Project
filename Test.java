import java.util.ArrayList;
import java.util.Random;

/**
 * Class for testing aspects of the program.
 */
public class Test {

    public static void main(String[] args) {

        System.out.println("Testing Systems...");

        try { testPokemonGen(); }
        catch (Exception e) { System.out.println("Failed: Generate a random Pokemon"); }

        try { testPokemonDebuff(); }
        catch (Exception e) { System.out.println("Failed: Pokemon Debuff"); }

        try { testPokemonBuff(); }
        catch (Exception e) { System.out.println("Failed: Pokemon Buff"); }

        try { testPokemonBuffArraylist(); }
        catch (Exception e) { System.out.println("Failed: Pokemon Buff Array List"); }

        try { testPokemonAttackValues(); }
        catch (Exception e) { System.out.println("Failed: Pokemon Attack Values"); }

        try { testPokemonAttackValuesBuffs(); }
        catch (Exception e) { System.out.println("Failed: Pokemon Attack Values Buffs"); }

        try { testPokemonAttackValuesBuffsSlam(); }
        catch (Exception e) { System.out.println("Failed: Pokemon Attack Values Buffs Slam"); }

        try { testPokemonFight(); }
        catch (Exception e) { System.out.println("Failed: Pokemon Fight"); }


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

    private static void testPokemonAttackValues() {
        System.out.println("╔═════\n║ Pokemon Attack Values\n╚═════");
        Pokemon p1 = PokemonGenerator.getInstance().generateRandomPokemon(1);
        Pokemon p2 = PokemonGenerator.getInstance().generateRandomPokemon(1);
        System.out.print("Attacker: " + p1.toString());
        System.out.print("Defender: " + p2.toString());
        System.out.println("═════\n" + p1.attack(p2, 1, 1));
        System.out.println(p1.attack(p2, 1, 2));
        System.out.println(p1.attack(p2, 1, 3) + "\n═════");
        System.out.print("Attacker: " + p1.toString());
        System.out.print("Defender: " + p2.toString());
        System.out.println("═════\n" + p1.attack(p2, 2, 1));
        System.out.println(p1.attack(p2, 2, 2));
        System.out.println(p1.attack(p2, 2, 3) + "\n═════");
        System.out.print("Attacker: " + p1.toString());
        System.out.print("Defender: " + p2.toString());
    }

    private static void testPokemonAttackValuesBuffs() {
        System.out.println("╔═════\n║ Pokemon Attack Values (Level 20)\n╚═════");
        Pokemon p1 = PokemonGenerator.getInstance().generateRandomPokemon(20);
        Pokemon p2 = PokemonGenerator.getInstance().generateRandomPokemon(20);
        System.out.print("Attacker: " + p1.toString());
        System.out.print("Defender: " + p2.toString());
        System.out.println("═════\n" + p1.attack(p2, 1, 1));
        System.out.println(p1.attack(p2, 1, 2));
        System.out.println(p1.attack(p2, 1, 3) + "\n═════");
        System.out.print("Attacker: " + p1.toString());
        System.out.print("Defender: " + p2.toString());
        System.out.println("═════\n" + p1.attack(p2, 2, 1));
        System.out.println(p1.attack(p2, 2, 2));
        System.out.println(p1.attack(p2, 2, 3) + "\n═════");
        System.out.print("Attacker: " + p1.toString());
        System.out.print("Defender: " + p2.toString());
    }

    private static void testPokemonAttackValuesBuffsSlam() {
        System.out.println("╔═════\n║ Pokemon Attack Values (Level 20)\n╚═════");
        Pokemon p1 = PokemonGenerator.getInstance().generateRandomPokemon(20);
        Pokemon p2 = PokemonGenerator.getInstance().generateRandomPokemon(1);
        System.out.print("Attacker: " + p1.toString());
        System.out.print("Defender: " + p2.toString());
        System.out.println("═════");
        for (int i = 0; i < 100000; i++) { System.out.println(p1.attack(p2, 1, 1)); }
    }

    private static void testPokemonFight() {
        System.out.println("╔═════\n║ Pokemon Fight\n╚═════");
        Pokemon p1 = PokemonGenerator.getInstance().generateRandomPokemon(1);
        Pokemon p2 = PokemonGenerator.getInstance().generateRandomPokemon(1);

        p2 = PokemonGenerator.getInstance().addRandomDebuff(p2);

        Trainer player = new Trainer("TESTER", p1);
        Main.trainerAttack(player,p2, false);

        for (int i = 0; i < 1; i++) { System.out.println(p1.attack(p2, 1, 1)); }
    }

}
