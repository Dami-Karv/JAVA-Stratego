package model.piece;

import controller.player;

/**
 * class that creates and manages a slayer
 * @version 1.1
 * @author dami karv - csd4897
 */
public class slayer extends movablepiece{
    /**
     * <b>constructor</b>: Constructs a new slayer
     */
    public slayer(player owner) {
        super(1,"slayer");
        setplayer(owner);
    }}
