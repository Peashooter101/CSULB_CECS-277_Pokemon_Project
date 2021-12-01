/**
 * AttackDown Debuff Decorator
 * - Lowers base attack score by 1.
 */
public class AttackDown extends PokemonDecorator {
	
	public AttackDown(Pokemon p) { super(p, "-ATK", 0); }
	
	public int getAttackBonus(int atkType) { return -1 + super.getAttackBonus(atkType); }

}
