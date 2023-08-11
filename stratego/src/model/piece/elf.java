package model.piece;

import controller.player;

/**
 * class that creates and manages an elf
 * @version 1.1
 * @author dami karv - csd4897
 */
public class elf extends movablepiece{
    /**
     * <b>constructor</b>: Constructs a new elf
     */
    public elf(player owner) {
        super(4,"elf");
        setplayer(owner);
    }}
