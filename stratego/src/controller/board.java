package controller;


import model.piece.*;

/**
 * class that controls the game board
 * has size of 80 (8x10)
 * @version 1.1
 * @author dami karv - csd4897
 */
public class board {
    public piece[] board1;

    /**
     * <b>Transformer:</b>makes a piece from the board with index i null
     * <b>postcondition:</b> piece with index i is null
     * @param i
     */
    public void nullpiece(int i){this.board1[i]=null;}

    /**
     * <b>Accessor:</b> returns the piece's rank from index i
     * <b>postcondition:</b> piece's rank from index i is returned
     * @param i
     * @return the piece's rank from index i
     */
    public int getpiecerank(int i){return board1[i].getpiecerank();}

    /**
     * <b>Accessor:</b> returns piece from the board at index i
     * <b>postcondition:</b> piece from the board with index i is returned
     * @param i
     * @return the piece from location i
     */
    public piece getpiece(int i) {return this.board1[i];}

    /**
     * <b>Transformer:</b> sets the piece on the board for index i
     * <b>postcondition:</b> the piece on index i is set
     * @param i
     * @param piece
     */
    public void setpiece(int i,piece piece) {
        this.board1[i]=piece;
    }

    /**
     * <b>Accessor:</b> returns name with index i from the board
     * <b>postcondition:</b> the name with index i has been returned
     * @param i
     * @return piece's name from location i
     */
    public String getname(int i){return board1[i].getname();}

    /**
     * <b>Accessor:</b> returns player that controls piece with index i
     * <b>postcondition:</b> piece owner has been returned
     * @param i
     * @return piece's owner from location i
     */
    public player getplayer(int i){
        if(board1[i]!=null){return board1[i].getplayer();}
    else {return null;}}

    /**
     * <b>Transformer:</b> sets the board according to the gamemode
     * <b>postcondition:</b>board has 60 or 32 pieces set on fixed positions
     * (pieces are fixed and not randomised for efficiency purposes)
     * @param board1
     * @param player1
     * @param player2
     * @param reduced
     */
    public void boardinit(piece[] board1,player player1,player player2,boolean reduced){
        if(reduced==false){
        flag flagR=new flag(player1);
        board1[15]=flagR;
        trap trapR1=new trap(player1);
        board1[1]=trapR1;
        trap trapR2=new trap(player1);
        board1[10]= trapR2;
        trap trapR3=new trap(player1);
        board1[11]=trapR3;
        trap trapR4=new trap(player1);
        board1[20]=trapR4;
        trap trapR5=new trap(player1);
        board1[23]=trapR5;
        trap trapR6=new trap(player1);
        board1[25]=trapR6;
        slayer slayerR=new slayer(player1);
        board1[12]=slayerR;
        scout scoutR1=new scout(player1);
        board1[3]=scoutR1;
        scout scoutR2=new scout(player1);
        board1[16]=scoutR2;
        scout scoutR3=new scout(player1);
        board1[18]=scoutR3;
        scout scoutR4=new scout(player1);
        board1[28]=scoutR4;
        dwarf dwarfR1=new dwarf(player1);
        board1[0]=dwarfR1;
        dwarf dwarfR2=new dwarf(player1);
        board1[7]=dwarfR2;
        dwarf dwarfR3=new dwarf(player1);
        board1[8]=dwarfR3;
        dwarf dwarfR4=new dwarf(player1);
        board1[24]=dwarfR4;
        dwarf dwarfR5=new dwarf(player1);
        board1[27]=dwarfR5;
        elf elfR1=new elf(player1);
        board1[13]=elfR1;
        elf elfR2=new elf(player1);
        board1[29]=elfR2;
        lavabeast_yeti lavabeast_yetiR1=new lavabeast_yeti(player1);
        board1[2]=lavabeast_yetiR1;
        lavabeast_yeti lavabeast_yetiR2=new lavabeast_yeti(player1);
        board1[5]=lavabeast_yetiR2;
        sorceress sorceressR1=new sorceress(player1);
        board1[4]=sorceressR1;
        sorceress sorceressR2=new sorceress(player1);
        board1[6]=sorceressR2;
        beastrider beastriderR1=new beastrider(player1);
        board1[19]=beastriderR1;
        beastrider beastriderR2=new beastrider(player1);
        board1[22]=beastriderR2;
        beastrider beastriderR3=new beastrider(player1);
        board1[26]=beastriderR3;
        knight knightR1=new knight(player1);
        board1[14]=knightR1;
        knight knightR2=new knight(player1);
        board1[21]=knightR2;
        mage mageR=new mage(player1);
        board1[9]=mageR;
        dragon dragonR=new dragon(player1);
        board1[17]=dragonR;
        flag flagB=new flag(player2);
        board1[75]=flagB;
        trap trapB1=new trap(player2);
        board1[51]=trapB1;
        trap trapB2=new trap(player2);
        board1[57]=trapB2;
        trap trapB3=new trap(player2);
        board1[60]=trapB3;
        trap trapB4=new trap(player2);
        board1[64]=trapB4;
        trap trapB5=new trap(player2);
        board1[70]=trapB5;
        trap trapB6=new trap(player2);
        board1[77]=trapB6;
        slayer slayerB=new slayer(player2);
        board1[59]=slayerB;
        scout scoutB1=new scout(player2);
        board1[53]=scoutB1;
        scout scoutB2=new scout(player2);
        board1[62]=scoutB2;
        scout scoutB3=new scout(player2);
        board1[68]=scoutB3;
        scout scoutB4=new scout(player2);
        board1[72]=scoutB4;
        dwarf dwarfB1=new dwarf(player2);
        board1[52]=dwarfB1;
        dwarf dwarfB2=new dwarf(player2);
        board1[58]=dwarfB2;
        dwarf dwarfB3=new dwarf(player2);
        board1[71]=dwarfB3;
        dwarf dwarfB4=new dwarf(player2);
        board1[73]=dwarfB4;
        dwarf dwarfB5=new dwarf(player2);
        board1[79]=dwarfB5;
        elf elfB1=new elf(player2);
        board1[54]=elfB1;
        elf elfB2=new elf(player2);
        board1[65]=elfB2;
        lavabeast_yeti lavabeast_yetiB1=new lavabeast_yeti(player2);
        board1[74]=lavabeast_yetiB1;
        lavabeast_yeti lavabeast_yetiB2=new lavabeast_yeti(player2);
        board1[78]=lavabeast_yetiB2;
        sorceress sorceressB1=new sorceress(player2);
        board1[55]=sorceressB1;
        sorceress sorceressB2=new sorceress(player2);
        board1[76]=sorceressB2;
        beastrider beastriderB1=new beastrider(player2);
        board1[50]=beastriderB1;
        beastrider beastriderB2=new beastrider(player2);
        board1[67]=beastriderB2;
        beastrider beastriderB3=new beastrider(player2);
        board1[69]=beastriderB3;
        knight knightB1=new knight(player2);
        board1[56]=knightB1;
        knight knightB2=new knight(player2);
        board1[66]=knightB2;
        mage mageB=new mage(player2);
        board1[63]=mageB;
        dragon dragonB=new dragon(player2);
        board1[61]=dragonB;}
        else{
            flag flagR=new flag(player1);
            board1[9]=flagR;
            trap trapR1=new trap(player1);
            board1[8]=trapR1;
            trap trapR2=new trap(player1);
            board1[12]=trapR2;
            trap trapR3=new trap(player1);
            board1[11]=trapR3;
            slayer slayerR=new slayer(player1);
            board1[7]=slayerR;
            scout scoutR1=new scout(player1);
            board1[10]=scoutR1;
            scout scoutR2=new scout(player1);
            board1[13]=scoutR2;
            dwarf dwarfR1=new dwarf(player1);
            board1[0]=dwarfR1;
            dwarf dwarfR2=new dwarf(player1);
            board1[6]=dwarfR2;
            elf elfR=new elf(player1);
            board1[14]=elfR;
            sorceress sorceressR=new sorceress(player1);
            board1[2]=sorceressR;
            beastrider beastriderR=new beastrider(player1);
            board1[3]=beastriderR;
            knight knightR=new knight(player1);
            board1[4]=knightR;
            mage mageR=new mage(player1);
            board1[5]=mageR;
            dragon dragonR=new dragon(player1);
            board1[15]=dragonR;
            lavabeast_yeti lavabeast_yetiR=new lavabeast_yeti(player1);
            board1[1]=lavabeast_yetiR;
            flag flagB=new flag(player2);
            board1[70]=flagB;
            trap trapB1=new trap(player2);
            board1[64]=trapB1;
            trap trapB2=new trap(player2);
            board1[65]=trapB2;
            trap trapB3=new trap(player2);
            board1[71]=trapB3;
            slayer slayerB=new slayer(player2);
            board1[72]=slayerB;
            scout scoutB1=new scout(player2);
            board1[66]=scoutB1;
            scout scoutB2=new scout(player2);
            board1[69]=scoutB2;
            dwarf dwarfB1=new dwarf(player2);
            board1[73]=dwarfB1;
            dwarf dwarfB2=new dwarf(player2);
            board1[79]=dwarfB2;
            elf elfB=new elf(player2);
            board1[67]=elfB;
            sorceress sorceressB=new sorceress(player2);
            board1[77]=sorceressB;
            beastrider beastriderB=new beastrider(player2);
            board1[76]=beastriderB;
            knight knightB=new knight(player2);
            board1[75]=knightB;
            mage mageB=new mage(player2);
            board1[74]=mageB;
            dragon dragonB=new dragon(player2);
            board1[68]=dragonB;
            lavabeast_yeti lavabeast_yetiB=new lavabeast_yeti(player2);
            board1[78]=lavabeast_yetiB;
        }}

    /**
     * <b>constructor</b>: Constructs a new board
     * <b>postcondition:</b>a new board is constructed
     * @param player1
     * @param player2
     * @param reduced
     */
    public board(player player1, player player2, boolean reduced) {
        board1=new piece[80];
        boardinit(board1,player1,player2,reduced);}}
