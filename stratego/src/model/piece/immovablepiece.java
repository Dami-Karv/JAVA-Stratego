package model.piece;

import controller.player;

/**
 * class immovable piece is a
 * sub-class of piece for all
 * pieces that cannot be moved during the game
 * @version 1.1
 * @author dami karv - csd4897
 */
public class immovablepiece extends piece{
    private int piecerank=0;
    private String name;
    private player owner;

    /**
     * <b>Accessor:</b> returns piece rank
     * <b>postcondition:</b>
     * @return piece rank
     */
    public int getpiecerank() {
        return this.piecerank;
    }

    /**
     * <b>Accessor:</b> returns piece name
     * <b>precondition:</b>
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

    /**
     * <b>constructor</b>: Constructs a new immovable piece
     * <b>precondition:</b>
     * <b>postcondition:</b>
     * @param name
     */
    public immovablepiece(String name) {
        setname(name);}}



































