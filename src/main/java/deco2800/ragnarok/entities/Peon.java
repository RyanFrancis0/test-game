package deco2800.ragnarok.entities;

import deco2800.ragnarok.Tickable;
import deco2800.ragnarok.managers.GameManager;
import deco2800.ragnarok.managers.TaskPool;
import deco2800.ragnarok.tasks.AbstractTask;

public class Peon extends AgentEntity implements Tickable {
	protected transient AbstractTask task;

	public Peon() {
		super();
		this.setTexture("spacman_ded");
		 this.setObjectName("Peon");
		this.setHeight(1);
		this.speed = 0.05f;
	}

	/**
	 * Peon constructor
     */
	public Peon(float row, float col, float speed) {
		super(row, col, 3, speed);
		this.setTexture("spacman_ded");
	}

	@Override
	public void onTick(long i) {
		if(task != null && task.isAlive()) {
			if(task.isComplete()) {
				this.task = GameManager.getManagerFromInstance(TaskPool.class).getTask(this);
			}
			task.onTick(i);
		} else {
			this.task = GameManager.getManagerFromInstance(TaskPool.class).getTask(this);
		}
	}
	
	public AbstractTask getTask() {
		return task;
	}
}
