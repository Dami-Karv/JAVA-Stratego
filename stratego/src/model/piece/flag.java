package model.piece;

import controller.player;

/**
 * class that creates and manages a flag
 * @version 1.1
 * @author dami karv - csd4897
 */
public class flag extends immovablepiece{
    /**
     * <b>constructor</b>: Constructs a new flag
     */
    public flag(player owner) {
        super("flag");
        setplayer(owner);}}
