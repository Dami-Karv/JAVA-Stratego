package model.piece;

import controller.player;

/**
 * class that creates and manages a beastrider
 * @version 1.1
 * @author dami karv - csd4897
 */
public class beastrider extends movablepiece{
    /**
     * <b>constructor</b>: Constructs a new beastrider
     */
    public beastrider(player owner) {
        super(7,"beastRider");
        setplayer(owner);
        }}
