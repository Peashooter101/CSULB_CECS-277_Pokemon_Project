/**
 * HpDown Debuff Decorator
 * - Lowers HP by 1.
 */
public class HpDown extends PokemonDecorator {

	/**
	 * Constructor
	 * @param p Pokemon to Decorate
	 */
	public HpDown(Pokemon p) { super(p, "-HP", -1); }

}
