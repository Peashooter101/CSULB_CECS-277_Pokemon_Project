/**
 * Decorator Class
 * Controls the Decorators for Pokemon Entities
 * Pokemon Decorators are Buffs and Debuffs:
 * - HpUp (Buff)
 * - AttackUp (Buff)
 * - HpDown (Debuff)
 * - AttackDown (Debuff)
 */
public abstract class PokemonDecorator extends Pokemon {
	private Pokemon pokemon;
	
	public PokemonDecorator(Pokemon p, String extraName, int extraHp) {
		super(p.getName() + " " + extraName, p.getHp() + extraHp, p.getMaxHp() + extraHp);
		pokemon = p;
	}

	/**
	 * Returns the attack menu.
	 * Special Menu should be in Fire / Water / Grass
	 * Basic Menu should be in Pokemon
	 * @param atkType Attack Type represented as int.
	 * @return String representing the Attack Menu
	 */
	@Override
	public String getAttackMenu(int atkType) { return pokemon.getAttackMenu(atkType); }

	/**
	 * Returns the attack menu.
	 * Special Menu should be in Fire / Water / Grass
	 * Basic Menu should be in Pokemon
	 * @param atkType Attack Type represented as int.
	 * @return int representing the number of items in the menu.
	 */
	@Override
	public int getNumAttackMenuItems(int atkType) { return pokemon.getNumAttackMenuItems(atkType); }

	/**
	 * Returns the partial string for the attack.
	 * Specials should be in Fire / Water / Grass
	 * Basics should be in Pokemon
	 * @param atkType Attack Type represented as int.
	 * @param move Move represented as int.
	 * @return
	 */
	@Override
	public String getAttackString(int atkType, int move) {
		return pokemon.getAttackString(atkType, move);
	}

	/**
	 * Returns the total attack damage without multiplier.
	 * If atkType is 1 (Basic), the base damage is in Pokemon
	 * If atkType is 2 (Special), the base damage is in Fire / Water / Grass
	 * Total Attack Damage w/o Multiplier = Base Damage + Attack Bonus
	 * @param atkType Attack Type represented as int.
	 * @param move Move represented as int.
	 * @return int representing total attack damage without multiplier.
	 */
	@Override
	public int getAttackDamage(int atkType, int move) { return pokemon.getAttackDamage(atkType, move) + pokemon.getAttackBonus(atkType); }

	/**
	 * Returns the attack multiplier.
	 * If type is 1 (Basic), it should return 1.0.
	 * If type is 2 (Special), it should pull from the table.
	 * @param p Pokemon
	 * @param type Attack Type represented as int.
	 * @return double representing the attack multiplier.
	 */
	@Override
	public double getAttackMultiplier(Pokemon p, int type) {
		return pokemon.getAttackMultiplier(p, type);
	}

	/**
	 * Calculates and returns the attack bonus.
	 * Attack Bonus does not include Base Attack.
	 * @param atkType Attack Type represented by int.
	 * @return int representing the attack bonus.
	 */
	@Override
	public int getAttackBonus(int atkType) { return pokemon.getAttackBonus(atkType); }

	/**
	 * Returns the type based on the Class used for the Pokemon.
	 * 0 represents Fire.
	 * 1 represents Water.
	 * 2 represents Grass.
	 * @return Integer representing Pokemon Type. -1 if no type found.
	 */
	@Override
	public int getType() { return pokemon.getType(); }
	
}
