import java.util.Random;
/**
 * AttackUp Buff Decorator
 * - Increases base attack score by 1 - 2.
 */
public class AttackUp extends PokemonDecorator {
	
	public AttackUp(Pokemon p) {
		super(p, "+ATK", 0);
	}
	
	public int getAttackBonus(int atkType) {
		Random random = new Random();
		return random.nextInt(2) + 1 + super.getAttackBonus(atkType);
	}
}
