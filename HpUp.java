/**
 * HpUp Buff Decorator
 * - Increases HP by 1 - 2.
 */
public class HpUp extends PokemonDecorator {

	/**
	 * Constructor
	 * @param p Pokemon to Decorate
	 */
	public HpUp(Pokemon p) { super(p, "+HP", (int)(Math.random()*2+1)); }

}
