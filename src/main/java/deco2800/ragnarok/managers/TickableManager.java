package deco2800.ragnarok.managers;

import deco2800.ragnarok.Tickable;

public abstract class TickableManager extends AbstractManager implements Tickable{

	 public abstract void onTick(long i);
}
