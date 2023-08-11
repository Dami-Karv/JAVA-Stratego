package model.piece;

import controller.player;

/**
 * class that creates and manages a knight
 * @version 1.1
 * @author dami karv - csd4897
 */
public class knight extends movablepiece{
    /**
     * <b>constructor</b>: Constructs a new knight
     */
    public knight(player owner) {
        super(8,"knight");
        setplayer(owner);
    }}
