package model.piece;

import controller.player;

/**
 * class that creates and manages a lavabeast or a yeti
 * according to team allignment
 * @version 1.1
 * @author dami karv - csd4897
 */
public class lavabeast_yeti extends movablepiece{
    /**
     * <b>constructor</b>: Constructs a new lavabeast/yeti
     */
    public lavabeast_yeti(player owner) {
        super(5,"lavabeast_yeti");
        setplayer(owner);
    }}
