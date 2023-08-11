package model.piece;

import controller.player;

/**
 * class that creates and manages a scout
 * @version 1.1
 * @author dami karv - csd4897
 */
public class scout extends movablepiece{
    /**
     * <b>constructor</b>: Constructs a new scout
     */
    public scout(player owner) {
        super( 2,"scout");
        setplayer(owner);}}
