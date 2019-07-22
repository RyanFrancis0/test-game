package deco2800.ragnarok.tasks;

import deco2800.ragnarok.Tickable;
import deco2800.ragnarok.entities.AgentEntity;

public abstract class AbstractTask implements Tickable {
	
	protected AgentEntity entity;
	
	
	public AbstractTask(AgentEntity entity) {
		this.entity = entity;
	}
	
	public abstract boolean isComplete();

	public abstract boolean isAlive();

}
