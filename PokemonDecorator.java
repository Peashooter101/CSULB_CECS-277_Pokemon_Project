
public abstract class PokemonDecorator extends Pokemon {
	private Pokemon pokemon;
	
	public PokemonDecorator(Pokemon p, String extraName, int extraHp) {
		// ? not sure if correct super constructor call
		super(p.getName() + extraName, p.getHp() + extraHp, p.getMaxHp() + extraHp);
		pokemon = p;
	}
	
	public String getAttackMenu(int atkType) {
		
	}
	
	public int getNumAttackMenuItems(int atkType) {
		
	}
	
	public String getAttackString(int atkType, int move) {
		
	}
	
	public int getAttackDamage(int atkType, int move) {
		
	}
	
	public int getAttackMultiplier(Pokemon p, int type) {
		
	}
	
	public int getAttackbonus(int type) {
		
	}
	
}
