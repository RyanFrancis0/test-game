package deco2800.thomas.entities;

import deco2800.thomas.managers.GameManager;
import deco2800.thomas.managers.NetworkManager;
import deco2800.thomas.renderers.Renderable;
import deco2800.thomas.util.HexVector;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.google.gson.annotations.Expose;

/**
 * A AbstractEntity is an item that can exist in both 3D and 2D worlds
 * AbstractEntities are rendered by Render2D and Render3D An item that does not
 * need to be rendered should not be a WorldEntity
 */
public abstract class AbstractEntity implements Renderable {
	private static final String ENTITY_ID_STRING = "entityID";
	
	@Expose
	private transient HashMap<String, Object> dataToSave = new HashMap<>();

	
	static int nextID = 0;

	static int getNextID() {
		return nextID++;
	}

	private String texture = "error_box";

	protected HexVector position;
	
	private int height;

	private float colRenderLength;

	private float rowRenderLength;
	
	@Expose
	private String objectName = null;

	private int entityID = 0;

	/** Whether an entity should trigger a collision when */
	private boolean collidable = true; 
	
	/**
	 * Constructor for an abstract entity
	 * @param col the col position on the world
	 * @param row the row position on the world
	 * @param height the height position on the world
     */

	public AbstractEntity(float col, float row, int height) {
		this(col, row, height, 1f, 1f);
		entityID = AbstractEntity.getNextID();
		this.setObjectName(ENTITY_ID_STRING);
	}

	public AbstractEntity() {
		this.position = new HexVector();
		this.colRenderLength = 1f;
		this.rowRenderLength = 1f;
		this.setObjectName(ENTITY_ID_STRING);
	}


	/**
	 * Constructor for an abstract entity
	 * @param col the col position on the world
	 * @param row the row position on the world
	 * @param height the height position on the world
	 * @param colRenderLength the rendered length in col direction
	 * @param rowRenderLength the rendered length in the row direction
     */
	public AbstractEntity(float col, float row, int height, float colRenderLength, float rowRenderLength) {
		this.position = new HexVector(col, row);
		this.height = height;
		this.colRenderLength = colRenderLength;
		this.rowRenderLength = rowRenderLength;
		this.entityID = AbstractEntity.getNextID();
	}

	/**
	 * Get the column position of this AbstractWorld Entity
	 */
	public float getCol() {
		return position.getCol();
	}

	/**
	 * Get the row position of this AbstractWorld Entity
	 */
	public float getRow() {
		return position.getRow();
	}

	/**
	 * Get the Z position of this AbstractWorld Entity
	 * 
	 * @return The Z position
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Sets the col coordinate for the entity
     */
	public void setCol(float col) {
		this.position.setCol(col);
	}

	/**
	 * Sets the row coordinate for the entity
	 */
	public void setRow(float row) {
		this.position.setRow(row);
	}

	/**
	 * Sets the height coordinate for the entity
	 */
	public void setHeight(int z) {
		this.height = z;
	}

	/**
	 * sets the position of the entity in the world
	 * @param col the x coordinate for the entity
	 * @param row the y coordinate for the entity
     * @param height the z coordinate for the entity
     */
	public void setPosition(float col, float row, int height) {
		setCol(col);
		setRow(row);
		setHeight(height);
	}

	public float getColRenderWidth() {
		return colRenderLength;
	}

	public float getRowRenderWidth() {
		return rowRenderLength;
	}

	/**
	 * Tests to see if the item collides with another entity in the world
	 * @param entity the entity to test collision with
	 * @return true if they collide, false if they do not collide
     */
	public boolean collidesWith(AbstractEntity entity) {
		//TODO: Implement this.
		return false;
	}

	@Override
	public float getColRenderLength() {
		return this.colRenderLength;
	}

	@Override
	public float getRowRenderLength() {
		return this.rowRenderLength;
	}

	/**
	 * Gives the string for the texture of this entity. This does not mean the
	 * texture is currently registered
	 * 
	 * @return texture string
	 */
	public String getTexture() {
		return texture;
	}

	/**
	 * Sets the texture string for this entity. Check the texture is registered with
	 * the TextureRegister
	 * 
	 * @param texture
	 *            String texture id
	 */
	public void setTexture(String texture) {
		this.texture = texture;
	}


	/**
	 * Set whether entity should trigger a collision on contact with another
	 * entity.
	 * @param collidable - boolean whether it should trigger COLLIDE events.
	 */
	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

	/**
	 * Return the collidable property's current value.
	 * @return Boolean, whether collidable or not.
	 */
	public boolean isCollidable() {
		return collidable;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AbstractEntity that = (AbstractEntity) o;
		return height == that.height &&
				Float.compare(that.colRenderLength, colRenderLength) == 0 &&
				Float.compare(that.rowRenderLength, rowRenderLength) == 0 &&
				entityID == that.entityID &&
				collidable == that.collidable &&
				Objects.equals(texture, that.texture) &&
				Objects.equals(position, that.position);
	}

	@Override
	public int hashCode() {
		int result = position != null ? position.hashCode() : 0;
		result = 31 * result + (texture != null ? texture.hashCode() : 0);
		return result;
	}

	/**
	 * Gets the distance from an abstract entity
	 * @param e the abstract entity
	 * @return the distance as a float
     */
	public float distance(AbstractEntity e) {
		return this.position.distance(e.position);
	}
	
	@Override
	public float getResizeRatio() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setResizeRatio(float newRatio) {
		// TODO Auto-generated method stub
	}

	public HexVector getPosition() {
		return position;
	}

	public abstract void onTick(long i);

	/**
	 * Set objectID (If applicable)
	 *
	 * @param name of object
	 */
	public void setObjectName(String name) {
		this.objectName = name;
	}

	/**
	 * Get objectID (If applicable)
	 *
	 * @return Name of object
	 */
	public String getObjectName() { return this.objectName; }
	
	
	public int getEntityID() {
		return entityID;
	}

	public void setEntityID(int id) {
		this.entityID = id;
	}

	public void dispose() {
		GameManager.get().getManager(NetworkManager.class).deleteEntity(this);
		GameManager.get().getWorld().getEntities().remove(this);
	}
	
	/**
	 * Adds data to the dataToSave dictionary.  Anything about an entity that needs to be
	 * saved should go through this function.
	 *
	 * @param key: A description of the data being saved.  This is used by the load function to figure out
	 *           which entity is being loaded.
	 */
	public void addDataToSave(String key, Object value) {
		this.dataToSave.put(key, value);
	}

	@Override
	public void saveData() {
		addDataToSave(ENTITY_ID_STRING, this.entityID);
		addDataToSave("row", this.getRow());
		addDataToSave("col", this.getCol());
		addDataToSave("texture", this.getTexture());
		addDataToSave("objectName", this.getObjectName());
	}

	public Map<String, Object> getDataToSave() {
		return this.dataToSave;
	}
	
}

