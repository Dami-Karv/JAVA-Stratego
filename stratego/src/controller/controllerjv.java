package controller;

import model.piece.*;

/**
 * Controller is the master of the game and controls all
 * the operations executed
 * @version 1.1
 * @author dami karv - csd4897
 */
public  class controllerjv {
    public player player1,player2,currentplayer;
    public board board2;
    public int turncounter;
    private boolean reducedarmy,gamestatus;

    /**
     * <b>Accessor:</b> returns the status string depending on
     * the winning team
     * <b>postcondition:</b> the game end string is returned
     * @return the game end message
     */
    public String getstatusstring(){
       if(player1.getstatus()==false){
           return "BLUE WON";}
       else if(player2.getstatus()==false){
           return "RED WON";}
       else{return "TIE";}}

    /**
     * <b>Transformer:</b> changes the current player to the opposite one
     * <b>postcondition:</b> current/opposite player are swapped
     */
    public void turnchange(){
        this.currentplayer=getcurrentplayer(false);
    turncounter++;}

    /**
     * <b>Observer:</b> returns the result of the battle (true means that the
     * attacker won and false that the defender won)
     * <b>postcondition:</b>
     * @param attack
     * @param def
     * @return result of the battle
     */
    public boolean battle(int attack,int def){
        currentplayer.totalattacks++;
        getcurrentplayer(false).totalattacks++;
        if(board2.getname(def)=="trap"){
            if(board2.getname(attack)!="dwarf"){
                return false;}
            else{
                currentplayer.attackswon++;
                return true;}}
        else{
            if(board2.getname(attack)=="slayer"){
                if(board2.getname(def)=="dragon"){
                    currentplayer.attackswon++;
                    return true;}
                else{return false;}}
                else{
                if(board2.getpiecerank(attack)>board2.getpiecerank(def)){
                    currentplayer.attackswon++;
                    return true;}
                else {return false;}}}}

    /**
     * <b>Accessor:</b> returns the current or opposing player depending on the input
     * (true = current player , false = opposing player)
     * <b>postcondition:</b> the current or opposing player is returned
     * @param choice
     * @return the current or opposing player
     */
    public player getcurrentplayer(boolean choice){
        if(choice==true){
            return this.currentplayer;}
        else{
            if(currentplayer==player1){
                return this.player2;}
            else {return this.player1;}}}

    /**
     * <b>Transformer:</b> sets the game status (false for over true for active)
     * <b>postcondition:</b>
     * @param gamestatus
     */
public void setgamestatus(boolean gamestatus){this.gamestatus=gamestatus;}

    /**
     * <b>Accessor:</b> returns the game status
     * <b>postcondition:</b>
     * @return the game status
     */
public boolean getgamestatus(){return this.gamestatus;}

    /**
     * <b>Accessor:</b> returns the attack win chance statistic
     * <b>precondition:</b>
     * <b>postcondition:</b>
     * @return the attack win chance statistic
     */
public int winchance(){return (currentplayer.getattackstat());}

    /**
     * <b>Accessor:</b> returns the number of dead slayers
     * <b>postcondition:</b>dead slayers are returned
     * @return number of dead slayers
     */
    public int getdeadslayers(){
        int deadslayers=1;
        for(int i=0;i<80;i++){
            if(board2.getplayer(i)==currentplayer) {
                if (board2.getpiece(i) instanceof slayer) {
                    deadslayers--;}}}
        return deadslayers;}

    /**
     * <b>Accessor:</b> returns the number of dead scouts
     * <b>postcondition:</b>dead scouts are returned
     * @return number of dead scouts
     */
    public int getdeadscouts(){
        int deadscouts;
        if(reducedarmy==true){
           deadscouts=2;}
        else{deadscouts=4;}
        for(int i=0;i<80;i++){
            if(board2.getplayer(i)==currentplayer) {
                if (board2.getpiece(i) instanceof scout) {
                   deadscouts--;}}}
              return deadscouts;}

    /**
     * <b>Accessor:</b> returns the number of dead dwarves
     * <b>postcondition:</b>dead dwarves are returned
     * @return number of dead dwarves
     */
    public int getdeaddwarves(){
        int deaddwarves;
        if(reducedarmy==true){
            deaddwarves=2;}
        else{deaddwarves=5;}
        for(int i=0;i<80;i++){
            if(board2.getplayer(i)==currentplayer) {
                if (board2.getpiece(i) instanceof dwarf) {
                    deaddwarves--;}}}
        return deaddwarves;}

    /**
     * <b>Accessor:</b> returns the number of dead elves
     * <b>postcondition:</b>dead elves are returned
     * @return number of dead elves
     */
    public int getdeadelves(){
        int deadelves;
        if(reducedarmy==true){
            deadelves=1;}
        else{deadelves=2;}
        for(int i=0;i<80;i++){
            if(board2.getplayer(i)==currentplayer) {
                if (board2.getpiece(i) instanceof elf) {
                    deadelves--;}}}
        return deadelves;}


    /**
     * <b>Accessor:</b> returns the number of dead yetis/beasts
     * <b>postcondition:</b>dead yetis/beasts are returned
     * @return number of dead yetis/beasts
     */
    public int getdeadyetis_beasts(){
        int deadyetis_beasts;
        if(reducedarmy==true){
            deadyetis_beasts=1;}
        else{deadyetis_beasts=2;}
        for(int i=0;i<80;i++){
            if(board2.getplayer(i)==currentplayer){
                if (board2.getpiece(i) instanceof lavabeast_yeti) {
                    deadyetis_beasts--;}}}
        return deadyetis_beasts;}

    /**
     * <b>Accessor:</b> returns the number of dead sorceresses
     * <b>postcondition:</b>dead sorceresses are returned
     * @return number of dead sorceresses
     */
    public int getdeadsorceress(){
        int deadsorceress;
        if(reducedarmy==true){deadsorceress=1;}
        else{deadsorceress=2;}
        for(int i=0;i<80;i++){
            if(board2.getplayer(i)==currentplayer) {
                if (board2.getpiece(i) instanceof sorceress) {
                    deadsorceress--;}}}
        return deadsorceress;}

    /**
     * <b>Accessor:</b> returns the number of dead beast riders
     * <b>postcondition:</b>dead beast riders are returned
     * @return number of dead beast riders
     */
    public int getdeadbeastriders(){
        int deadbeastriders;
        if(reducedarmy==true){deadbeastriders=1;}
        else{deadbeastriders=3;}
        for(int i=0;i<80;i++){
            if(board2.getplayer(i)==currentplayer) {
                if (board2.getpiece(i) instanceof beastrider) {
                    deadbeastriders--;}}}
        return deadbeastriders;}

    /**
     * <b>Accessor:</b> returns the number of dead knights
     * <b>postcondition:</b>dead knights are returned
     * @return number of dead knights
     */
    public int getdeadknights(){
        int deadknights;
        if(reducedarmy==true){deadknights=1;}
        else{deadknights=2;}
        for(int i=0;i<80;i++){
            if(board2.getplayer(i)==currentplayer) {
                if (board2.getpiece(i) instanceof knight) {
                    deadknights--;}}}
        return deadknights;}

    /**
     * <b>Accessor:</b> returns the number of dead mages
     * <b>postcondition:</b>dead mages are returned
     * @return number of dead mages
     */
    public int getdeadmages(){
        int deadmages=1;
        for(int i=0;i<80;i++){
            if(board2.getplayer(i)==currentplayer) {
                if (board2.getpiece(i) instanceof mage) {
                    deadmages--;}}}
        return deadmages;}

    /**
     * <b>Accessor:</b> returns the number of dead dragons
     * <b>postcondition:</b>dead dragons are returned
     * @return number of dead dragons
     */
    public int getdeaddragons(){
        int deaddragons=1;
        for(int i=0;i<80;i++){
            if(board2.getplayer(i)==currentplayer) {
                if (board2.getpiece(i) instanceof dragon) {
                    deaddragons--;}}}
        return deaddragons;}

    /**
     * <b>constructor</b>: Constructs a new controller
     * <b>postcondition:</b>a new controller has been created
     * @param reducedcopy
     */
    public controllerjv(boolean reducedcopy) {
       reducedarmy=reducedcopy;
       gamestatus = true;
       player1=  new player(true);
       player2 = new player(true);
       currentplayer=player1;
       board2 = new board(player1,player2,reducedarmy);
       turncounter=1;}}






























