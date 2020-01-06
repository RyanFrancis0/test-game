package deco2800.ragnarok.worlds;

import java.util.*;

import deco2800.ragnarok.entities.AbstractEntity;
import deco2800.ragnarok.entities.StaticEntity;
import deco2800.ragnarok.entities.Tree;
import deco2800.ragnarok.entities.PlayerPeon;
import deco2800.ragnarok.entities.Rock;
import deco2800.ragnarok.managers.GameManager;
import deco2800.ragnarok.util.Cube;
import deco2800.ragnarok.util.HexVector;

import static java.lang.Math.*;

@SuppressWarnings("unused")
public class TestWorld extends AbstractWorld {
	/*
	 * radius to tiles ratio 1 - 7 2 - 19 3 - 37 4 - 61 5 - 91 10 - 331 25 - 1951 50 -
	 * 7,651 100 - 30,301 150 - 67,951 200 - 120601
	 * 
	 * N = 1 + 6 * summation[0 -> N]
	 */
	boolean notGenerated = true;

	private static int RADIUS = 25;

	public TestWorld() {
		super();
	}

	//5 tile building
	private StaticEntity  createBuilding1(float col, float row) {
		StaticEntity building;
		
		Map<HexVector, String> textures = new HashMap<HexVector, String>();
		
		textures.put(new HexVector(1, -0.5f), "spacman_ded");
		textures.put(new HexVector(-1, -0.5f), "spacman_ded");
		textures.put(new HexVector(-1, 0.5f), "spacman_ded");
		textures.put(new HexVector(1, 0.5f), "spacman_ded");
		textures.put(new HexVector(0, 0), "spacman_ded");

		return  new StaticEntity(col, row, 1, textures);
		
	}
	
	//building with a fence
	private StaticEntity createBuilding2(float col, float row) {
		Map<HexVector, String> textures = new HashMap<HexVector, String>();
		
		textures = new HashMap<HexVector, String>();
		textures.put(new HexVector(0, 0), "buildingA");
		
		textures.put(new HexVector(-2, 1), "fenceNE-S");
		textures.put(new HexVector(-2, 0), "fenceN-S");
		textures.put(new HexVector(-2, -1), "fenceN-SE");
		
		textures.put(new HexVector(-1, 1.5f), "fenceNE-SW");
		textures.put(new HexVector(-1, -1.5f), "fenceNW-SE");
		
		//textures.put(new HexVector(2, 0), "fenceN-S");
		textures.put(new HexVector(0, 2), "fenceSE-SW");
		textures.put(new HexVector(0, -2), "fenceNW-NE");
		
		textures.put(new HexVector(1, -1.5f), "fenceNE-SW");
		textures.put(new HexVector(1, 1.5f), "fenceNW-SE");
		
		textures.put(new HexVector(2, 1), "fenceNW-S");
		//textures.put(new HexVector(2, 0), "fenceN-S");
		textures.put(new HexVector(2, -1), "fenceN-SW");
		StaticEntity building =  new StaticEntity(col, row, 1, textures);
		
		return building;
		
	}
	
	private void addTree(float col, float row) {
		Map<HexVector, String> textures = new HashMap<HexVector, String>();				
		Tile t = GameManager.get().getWorld().getTile(col, row);
		Tree tree = new  Tree(t, true);		
		entities.add(tree);
	}

	private int[] adjacent_min(int[] noise){
		int[] output = new int[noise.length - 1];
		for (int i = 0; i < noise.length - 1; i++) {
			output[i] = min(noise[i], noise[i + 1]);
		}
		return output;
	}

	private void putStuffInWorld() {
		Random random = new Random();
		int tileCount = GameManager.get().getWorld().getTileMap().size();
		/*
		// Generate some rocks to mine later
		for (int i = 0; i < 200;  i++) {
			Tile t = GameManager.get().getWorld().getTile(random.nextInt(tileCount));
			if (t != null) {
				entities.add(new Rock(t,true));
			}
		}
		entities.add(createBuilding2(-5, 0.5f));
		*/
	}

	@Override
	protected void generateWorld() {
		Random random = new Random();
		String[] textures = {"water_1", "grass_1", "dessert"}; //"mountain_1", , "forest",, "snow"
		String type;
		ArrayList<Integer> roundOne = new ArrayList<>();
		ArrayList<Integer> roundTwo = new ArrayList<>();
		ArrayList<Integer> roundThree = new ArrayList<>();

		//1D noise generation
		for (int q = -1000; q < 1000; q++) {
			for (int r = -1000; r < 1000; r++) {
				if (Cube.cubeDistance(Cube.oddqToCube(q, r), Cube.oddqToCube(0, 0)) <= RADIUS) {
					int x = random.nextInt(2);
					//System.out.println(x);
					roundOne.add(x);
				}
			}
		}

		for (Integer integer : roundOne) {
			int newVal = integer;
			if (integer == 1) {
				newVal = random.nextInt(2) + 1;
			}
			roundTwo.add(newVal);
		}

		/*
		//1D noise smoothing
		for (int i = 0; i < roundOne.size() - 1; i++) {
			Integer x = (int) round((roundOne.get(i).doubleValue() + roundOne.get(i + 1).doubleValue()) / 2);
			roundTwo.add(x);
		}
		//double smoothing
		for (int i = 0; i < roundTwo.size() - 1; i++) {
			Integer x = (int) round((roundTwo.get(i).doubleValue() + roundTwo.get(i + 1).doubleValue()) / 2);
			roundThree.add(x);
		}
		roundThree.add(1);
		roundThree.add(1);
		*/

		int count = 0;
		for (int q = -1000; q < 1000; q++) {
			for (int r = -1000; r < 1000; r++) {
				if (Cube.cubeDistance(Cube.oddqToCube(q, r), Cube.oddqToCube(0, 0)) <= RADIUS) {
					int col = q;
					float oddCol = (col % 2 != 0 ? 0.5f : 0);
					type = textures[roundTwo.get(count)];
					tiles.add(new Tile(type, q, r + oddCol, false));
					count++;
				}
			}
		}





		//System.out.println(tiles);
		// Create the entities in the game
		addEntity(new PlayerPeon(0f, 0f, 0.05f));
		//putStuffInWorld();
	}

	@Override
	public void onTick(long i) {
		super.onTick(i);
		for (AbstractEntity e : this.getEntities()) {
			e.onTick(0);
		}
	}

}

/*
 * print out Neighbours for (Tile tile : tiles) { System.out.println();
 * System.out.println(tile); for (Entry<Integer, Tile> firend :
 * tile.getNeighbours().entrySet()) { switch (firend.getKey()) { case
 * Tile.north: System.out.println("north " +(firend.getValue())); break; case
 * Tile.north_east: System.out.println("north_east " + (firend.getValue()));
 * break; case Tile.north_west: System.out.println("north_west " +
 * (firend.getValue())); break; case Tile.south: System.out.println("south " +
 * (firend.getValue())); break; case Tile.south_east:
 * System.out.println("south_east " +(firend.getValue())); break; case
 * Tile.south_west: System.out.println("south_west " + (firend.getValue()));
 * break; } } }
 * 
 */
