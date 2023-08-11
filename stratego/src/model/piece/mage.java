package model.piece;

import controller.player;

/**
 * class that creates and manages a mage
 * @version 1.1
 * @author dami karv - csd4897
 */
public class mage extends movablepiece{
    /**
     * <b>constructor</b>: Constructs a new mage
     */
    public mage(player owner) {
        super(9,"mage");
        setplayer(owner);}}
