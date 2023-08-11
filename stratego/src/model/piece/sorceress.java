package model.piece;

import controller.player;

/**
 * class that creates and manages a sorceress
 * @version 1.1
 * @author dami karv - csd4897
 */
public class sorceress extends movablepiece{
    /**
     * <b>constructor</b>: Constructs a new sorceress
     */
    public sorceress(player owner) {
        super(6,"sorceress");
        setplayer(owner);
    }}
