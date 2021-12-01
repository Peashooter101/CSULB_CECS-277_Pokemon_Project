/**
 * HpDown Debuff Decorator
 * - Lowers HP by 1.
 */
public class HpDown extends PokemonDecorator {
	
	public HpDown(Pokemon p) { super(p, "-HP", -1); }

}
