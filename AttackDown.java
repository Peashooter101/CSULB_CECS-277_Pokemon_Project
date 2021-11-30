import java.util.Random;

public class AttackDown extends PokemonDecorator {
	
	public AttackDown(Pokemon p) { super(p, "-ATK", 0); }
	
	public int getAttackBonus(int atkType) { return -1 + super.getAttackBonus(atkType); }

}
