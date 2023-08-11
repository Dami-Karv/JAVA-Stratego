package model.piece;

import controller.player;

/**
 * abstract class piece is a superclass
 * with all the basic elements to use for all pieces
 * @version 1.1
 * @author dami karv - csd4897
 */
public abstract class piece{
    private int piecerank;
    private String name;
    private player owner;
    /**
     * <b>Accessor:</b> returns piece rank
     * <b>postcondition:</b>
     * @return piece rank
     */
    public int getpiecerank(){return piecerank;}

    /**
     * <b>Accessor:</b> returns piece name
     * <b>postcondition:</b>
     * @return piece name
     */
    public String getname(){return name;}
    public void setname(String name){this.name=name;}
    /**
     * <b>Accessor:</b> returns piece owner
     * <b>postcondition:</b>
     * @return piece owner
     */
    public player getplayer(){return this.owner;}
    public void setplayer(player owner){this.owner=owner;}
}
