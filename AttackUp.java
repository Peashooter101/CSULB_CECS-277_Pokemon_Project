import java.util.Random;
/**
 * AttackUp Buff Decorator
 * - Increases base attack score by 1 - 2.
 */
public class AttackUp extends PokemonDecorator {

	/**
	 * Constructor
	 * @param p Pokemon to Decorate
	 */
	public AttackUp(Pokemon p) {
		super(p, "+ATK", 0);
	}

	/**
	 * Returns the attack bonus effect.
	 * @param atkType Attack Type represented by int.
	 * @return Total attack bonus from the Decorators.
	 */
	public int getAttackBonus(int atkType) {
		Random random = new Random();
		return random.nextInt(2) + 1 + super.getAttackBonus(atkType);
	}
}
