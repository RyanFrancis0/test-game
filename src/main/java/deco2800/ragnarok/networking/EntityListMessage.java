package deco2800.ragnarok.networking;

import deco2800.ragnarok.entities.AbstractEntity;

import java.util.concurrent.CopyOnWriteArrayList;

@Deprecated
// Don't use this, should be SingleEntityUpdateMessage to avoid buffer overflow
public class EntityListMessage {
    public CopyOnWriteArrayList<AbstractEntity> entityList;
}
