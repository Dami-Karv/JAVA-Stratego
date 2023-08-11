package controller;

/**
 * class that contains the basic elements
 * of a player (2 instances in total)
 * can change stats and is used to check a players situation
 * @version 1.1
 * @author dami karv - csd4897
 */
public class player {
    private boolean status;
    public int totalattacks=0,attackswon=0;

    /**
     * <b>Accessor:</b> returns player status (false for dead true for alive)
     * <b>postcondition:</b>player's status is returned
     * @return player status
     */
    public boolean getstatus() {return status;}

    /**
     * <b>Transformer:</b> sets the player status (false for dead true for alive)
     * <b>postcondition:</b> player's status is set
     * @param status
     */
    public void setstatus(boolean status) {this.status = status;}

    /**
     * <b>Accessor:</b> returns attack win possibility or 0
     * <b>postcondition:</b> the attack win possibility is returned
     * @return attack win possibility or 0
     */
    public int getattackstat(){
        if(totalattacks!=0){
        return this.attackswon/this.totalattacks;}
        else{return 0;}}

    /**
     * <b>constructor</b>: Constructs a new player
     * <b>postcondition:</b> a new player has been constructed
     * @param status
     */
    public player(boolean status) {setstatus(status);}}
