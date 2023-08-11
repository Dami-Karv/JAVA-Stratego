package model.piece;

import controller.player;

/**
 * class that creates and manages a trap
 * @version 1.1
 * @author dami karv - csd4897
 */
public class trap extends immovablepiece{
    /**
     * <b>constructor</b>: Constructs a new trap
     */
    public trap(player owner) {
        super("trap");
        setplayer(owner);}}
