package model.piece;

import controller.player;

/**
 * class that creates and manages a dragon
 * @version 1.1
 * @author dami karv - csd4897
 */
public class dragon extends movablepiece{
    /**
     * <b>constructor</b>: Constructs a new dragon
     */
    public dragon(player owner) {
        super(10,"dragon");
        setplayer(owner);

    }}
