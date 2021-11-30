
public class HpUp extends PokemonDecorator {
	
	public HpUp(Pokemon p) { super(p, "+HP", (int)(Math.random()*2+1)); }

}
