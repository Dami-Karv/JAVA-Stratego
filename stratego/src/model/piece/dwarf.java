package model.piece;

import controller.player;

/**
 * class that creates and manages a dwarf
 * @version 1.1
 * @author dami karv - csd4897
 */
public class dwarf extends movablepiece{
    /**
     * <b>constructor</b>: Constructs a new dwarf
     */
    public dwarf(player owner) {
        super(3,"dwarf");
        setplayer(owner);
    }}
