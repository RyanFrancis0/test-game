package deco2800.ragnarok.util;

import java.util.List;

import deco2800.ragnarok.worlds.AbstractWorld;
import deco2800.ragnarok.worlds.Tile;

public abstract class Pathfinder {
	
	public  abstract List<Tile> pathfind(AbstractWorld world, HexVector origin, HexVector destination);

}
