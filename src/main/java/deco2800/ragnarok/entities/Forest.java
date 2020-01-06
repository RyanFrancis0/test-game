package deco2800.ragnarok.entities;
import deco2800.ragnarok.worlds.Tile;

public class Forest extends StaticEntity{
    private static final String ENTITY_ID_STRING = "forest";

    public Forest() {
        this.setObjectName(ENTITY_ID_STRING);
    }

    public Forest(Tile tile) {
        super(tile, 2, "forest_1", false);
        this.setObjectName(ENTITY_ID_STRING);
    }

    @Override
    public void onTick(long i) {}
}
