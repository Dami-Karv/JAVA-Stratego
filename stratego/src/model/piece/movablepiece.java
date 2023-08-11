package model.piece;

import controller.player;

/**
 * class movablepiece is a
 * sub-class of piece for all
 * pieces that can be moved during the game
 * @version 1.1
 * @author dami karv - csd4897
 */
public class movablepiece extends piece{

    private int piecerank;
    private String name;
    private player owner;

    /**
     * <b>Accessor:</b> returns piece rank
     * <b>postcondition:</b>
     * @return piece rank
     */
    public int getpiecerank(){return piecerank;}

    public void setpiecerank(int piecerank){this.piecerank = piecerank;}

    /**
     * <b>Accessor:</b> returns piece name
     * <b>postcondition:</b>
     * @return piece name
     */
    @Override
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
     * <b>constructor</b>: Constructs a new movable piece
     * <b>precondition:</b>
     * <b>postcondition:</b>
     * @param piecerank
     * @param name
     */
    public movablepiece(int piecerank,String name) {
            setpiecerank(piecerank);
            setname(name);}}
