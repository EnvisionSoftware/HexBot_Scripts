package zeejfps.script.utill;

/*
 * This enum has all the foods in RS I can think of...
 * Found the Ids Online... obviously lol
 */
public enum Food {
	
	CRAYFISH(13433, 2, "Crayfish"), 
	ANCHOIVIES(319, 1, "Anchovies"),
	SHRIMP(315, 3, "Shrimps"), 
	CHICKEN(2140, 3, "Cooked Chicken"),
	MEAT(2142, 3, "Cooked Meat"), 
	SARDINE(325, 4, "Sardine"),
	BREAD(2309, 5, "Bread"), 
	HERRING(347, 5, "Herring"),
	MACKERAL(355, 6, "Mackeral"), 
	TROUT(333, 7, "Trout"),
	COD(339, 7, "Cod"), 
	PIKE(351, 8, "Pike"),
	SALMON(329, 9, "Salmon"), 
	TUNA(361, 10, "Tuna"),
	LOBSTER(379, 12, "Lobster"), 
	BASS(365, 13, "Bass"),
	SWORDFISH(373, 14, "Swordfish"), 
	POTATOWITHCHEESE(6705, 16, "Potato with cheese"),
	MONKFISH(7946, 16, "Monkfish"), 
	SHARK(385, 20, "Shark"),
	CAVEFISH(15266, 20, "Cavefish"), 
	SEATURTLE(397, 21, "Sea turtle"),
	MANTARAY(391, 22, "Manta ray"), 
	TUNAPOTATO(7060, 22, "Tuna potato"),
	ROCKTAIL(15272, 23, "Rocktail"), 
	BARONSHARK(19948, 30, "Baron Shark");
	
	private final int id;
	private final int healAmount;
	private final String name;
	
	Food(int id, int healAmount, String name) {
		this.id = id;
		this.healAmount = healAmount;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public int getHealAmount() {
		return healAmount;
	}
	
	public String getName() {
		return name;
	}
	
}
