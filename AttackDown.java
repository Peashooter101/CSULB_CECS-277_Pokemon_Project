/**
 * AttackDown Debuff Decorator
 * - Lowers base attack score by 1.
 */
public class AttackDown extends PokemonDecorator {

	/**
	 * Constructor
	 * @param p Pokemon to Decorate
	 */
	public AttackDown(Pokemon p) { super(p, "-ATK", 0); }

	/**
	 * Returns the attack bonus effect.
	 * @param atkType Attack Type represented by int.
	 * @return Total attack bonus from the Decorators.
	 */
	public int getAttackBonus(int atkType) { return -1 + super.getAttackBonus(atkType); }

}
