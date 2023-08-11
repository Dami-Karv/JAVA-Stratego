package viewer;
import controller.board;
import controller.controllerjv;
import controller.player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;
import model.piece.flag;
import model.piece.immovablepiece;
import model.piece.scout;
import model.piece.trap;

public class viewerjv extends JFrame{
    private boolean iconSelected;
    public JButton selectedButton;
    controllerjv controller;
    CardListener cl;
    public JFrame mainframe,extframe=new JFrame();
    JPanel sidepanel,mainpanel,gamemodepanel,gamepanel,statspanel,deadpanel;
    public boolean nostepcopy,reducedcopy;
    public JButton[] butt=new JButton[80];
    public int check,check2,row=0;

    /**
     * <b>Accessor:</b> returns the appropriate piece icon or the team hidden icon for blue/red and index i
     * <b>postcondition:</b> image with index i returned
     * @param board2
     * @param i
     * @param currentplayer
     * @param player1
     * @param player2
     * @return the appropriate piece icon or the team hidden icon for blue/red
     */
    private ImageIcon getImage(board board2, int i, player currentplayer,player player1,player player2) {
        if(currentplayer==player1){
           if(board2.getplayer(i)==player1){
        try {return new ImageIcon(ImageIO.read(getClass().getResource("/images/"+board2.getname(i)+"R.png")).getScaledInstance(90, 70, Image.SCALE_SMOOTH));
        } catch (IOException ex) {}return null;}
          else{
            try {return new ImageIcon(ImageIO.read(getClass().getResource("/images/BlueHidden.png")).getScaledInstance(90, 70, Image.SCALE_SMOOTH));
            } catch (IOException ex) {return null;}}}
        else if(currentplayer==player2){
            if(board2.getplayer(i)==player1){
                try {return new ImageIcon(ImageIO.read(getClass().getResource("/images/RedHidden.png")).getScaledInstance(90, 70, Image.SCALE_SMOOTH));
                } catch (IOException ex) {}return null;}
            else{
                try {return new ImageIcon(ImageIO.read(getClass().getResource("/images/"+board2.getname(i)+"B.png")).getScaledInstance(90, 70, Image.SCALE_SMOOTH));
                } catch (IOException ex) {}return null;}}
         else{return null;}}

    /**
     * <b>Transformer</b> creates a sidepanel and attaches it to the right
     * of the mainpanel
     * <b>postcondition:</b> the sidepanel is created and attached
     */
public void sidepanelmake(){
        if(row!=0){
            mainpanel.remove(sidepanel);
            sidepanel.remove(gamemodepanel);
            sidepanel.remove(statspanel);
            sidepanel.remove(deadpanel);}
    sidepanel.setPreferredSize(new Dimension(150, 575));
    gamemodepanel = new JPanel();
    final JCheckBox checkbox1 = new JCheckBox("nostepcheck");
    final JCheckBox checkbox2 = new JCheckBox("reducedcheck");
    if(nostepcopy){checkbox1.setSelected(true);}
    else{checkbox1.setSelected(false);}
    if(reducedcopy){checkbox2.setSelected(true);}
    else{checkbox2.setSelected(false);}
    gamemodepanel.setLayout(new BoxLayout(gamemodepanel,BoxLayout.Y_AXIS));
    JLabel title = new JLabel("Active Rules");
    title.setFont(new Font("Arial", Font.PLAIN, 20));
    gamemodepanel.add(title, BorderLayout.PAGE_START);
    gamemodepanel.add(checkbox1);
    gamemodepanel.add(checkbox2);
    JLabel space1 = new JLabel("");
    space1.setBorder(new EmptyBorder(0, 0, 25, 0));
    gamemodepanel.add(space1, BorderLayout.PAGE_START);
    sidepanel.add(gamemodepanel);
    sidepanel.setBorder(BorderFactory.createLineBorder(Color.black));
    statspanel = new JPanel();
    JLabel label3 = new JLabel("Win chance: "+controller.winchance()+"%");
    String playstr;
    if(controller.getcurrentplayer(true)==controller.player1){playstr="Player 1";}
    else{playstr="Player 2";}
    JLabel label4 = new JLabel("Turn: "+controller.turncounter);
    JLabel label5 = new JLabel(playstr+" turn");
    statspanel.setLayout(new BoxLayout(statspanel,BoxLayout.Y_AXIS));
    JLabel title2 = new JLabel("Game Stats");
    title2.setFont(new Font("Arial", Font.PLAIN, 20));
    statspanel.add(title2, BorderLayout.PAGE_START);
    statspanel.add(label5);
    statspanel.add(label3);
    statspanel.add(label4);
    JLabel space2 = new JLabel("");
    space2.setBorder(new EmptyBorder(0, 0, 25, 0));
    statspanel.add(space2, BorderLayout.PAGE_START);
    sidepanel.add(statspanel);
    sidepanel.setBorder(BorderFactory.createLineBorder(Color.black));
    deadpanel = new JPanel();
    JLabel label6 = new JLabel("Dead Slayers "+controller.getdeadslayers());
    JLabel label7 = new JLabel("Dead Scouts "+controller.getdeadscouts());
    JLabel label8 = new JLabel("Dead Dwarves "+controller.getdeaddwarves());
    JLabel label9 = new JLabel("Dead Elves "+controller.getdeadelves());
    JLabel label10 = new JLabel("Dead Yetis/Beasts "+controller.getdeadyetis_beasts());
    JLabel label11= new JLabel("Dead Sorceresses "+controller.getdeadsorceress());
    JLabel label12 = new JLabel("Dead Beast riders "+controller.getdeadbeastriders());
    JLabel label13= new JLabel("Dead Knights "+controller.getdeadknights());
    JLabel label14= new JLabel("Dead Mages "+controller.getdeadmages());
    JLabel label15= new JLabel("Dead Dragons "+controller.getdeaddragons());
    deadpanel.setLayout(new BoxLayout(deadpanel,BoxLayout.Y_AXIS));
    JLabel title3 = new JLabel("Dead pieces");
    title3.setFont(new Font("Arial", Font.PLAIN, 20));
    deadpanel.add(title3, BorderLayout.PAGE_START);
    deadpanel.add(label6);
    deadpanel.add(label7);
    deadpanel.add(label8);
    deadpanel.add(label9);
    deadpanel.add(label10);
    deadpanel.add(label11);
    deadpanel.add(label12);
    deadpanel.add(label13);
    deadpanel.add(label14);
    deadpanel.add(label15);
    sidepanel.add(deadpanel);
    sidepanel.setBorder(BorderFactory.createLineBorder(Color.black));
        mainpanel.add(sidepanel);
        row++;}

    /**
     * <b>constructor</b>: Constructs a new viewer
     * <b>precondition:</b>
     * <b>postcondition:</b>
     * @param nostepcheck
     * @param reducedcheck
     */
    public viewerjv(boolean nostepcheck,boolean reducedcheck){
        controller = new controllerjv(reducedcheck);
        nostepcopy=nostepcheck;
        reducedcopy=reducedcheck;
        this.setTitle("STRATEGO");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardListener cl = new CardListener();
        mainframe = new JFrame("STRATEGO");
        mainpanel = new JPanel(new GridBagLayout());
        gamepanel = new JPanel(new GridLayout(8,10));
        sidepanel = new JPanel();
        butmake(controller,cl,gamepanel,false);
        mainpanel.add(gamepanel);
        sidepanelmake();
        mainframe.getContentPane().add(mainpanel);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setSize(1400, 900);
        mainframe.setVisible(true);
        mainframe.pack();
        mainframe.setLocationRelativeTo(null);
        iconSelected = false;}

    /**
     * <b>Transformer</b> creates the exit menu in case the game has ended
     * <b>precondition:</b> the game is finished
     * <b>postcondition:</b> the exit menu appears
     */
    public void exitmenu(){
            JButton exit = new JButton("exit");
            JButton restart = new JButton("restart");
            exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }});
            restart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainframe.dispose();
                    startmenu startmenu = new startmenu();
                    extframe.dispose();}});
            JPanel panel = new JPanel();
            panel.add(restart);
            panel.add(exit);
            extframe = new JFrame(controller.getstatusstring());
            extframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            extframe.add(panel);
            extframe.pack();
            extframe.setBounds(700, 450, 400, 75);
            extframe.setVisible(true);}

    /**
     * <b>Transformer</b> creates a buttonand attaches it to the panel
     * <b>postcondition:</b> a button is created and attached to the panel
     * @param controller
     * @param cl
     * @param gamepanel
     * @param flag
     */
    public void butmake(controllerjv controller,CardListener cl,JPanel gamepanel,boolean flag){
        int i;
        for (i = 0; i < 80; i++) {
           if(butt[i]==null&& !flag){
               butt[i] = new JButton();}
            if(controller.board2.getplayer(i) !=null){
                butt[i].setIcon(getImage(controller.board2,i, controller.getcurrentplayer(true),controller.player1,controller.player2));
                butt[i].setName(controller.board2.getname(i));
                butt[i].setBorder(BorderFactory.createLineBorder(Color.black));
                butt[i].addMouseListener(cl);}
            else if(i == 32|| i == 33 || i == 36 || i == 37|| i == 42 || i == 43  || i == 46 || i == 47){
                butt[i].setBackground(Color.BLACK);
                butt[i].setBorder(BorderFactory.createLineBorder(Color.black));}
            else{
                butt[i].setIcon(null);
                butt[i].addMouseListener(cl);
                butt[i].setBorder(BorderFactory.createLineBorder(Color.black));}
            gamepanel.add(butt[i]);}}

    /**
     * <b>Transformer</b> deletes JButton given
     * <b>postcondition:</b> JButton is deleted
     * @param button
     */
    public void butdelete(JButton button){
        button.setIcon(null);
        button.setBorder(BorderFactory.createLineBorder(Color.black));
        button.setName(null);}

    //a class used for mouse click cases
    public class CardListener implements MouseListener {
        /**
         * <b>Transformer</b> this function gets the button you clicked on the board
         * and performs the respective actions
         * @param e
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton but = ((JButton) e.getSource());
            if(controller.board2.getplayer(check)==controller.currentplayer&&iconSelected && !but.equals(selectedButton)&& !(controller.board2.getpiece(check) instanceof immovablepiece)){
                for(int i=0;i<80;i++){
                    if(butt[i]==but){check2=i;}}
                if(nostepcopy==false){
                if(butt[check2].getIcon()==null){
                //metakinhsh se adeio
                if(controller.board2.getplayer(check2)!=controller.currentplayer) {
                    if (controller.board2.getpiece(check) instanceof scout) {
                        boolean test1=false,test2=false,test3=true,test4=true,test5=false;
                            if(check2/10==check/10){test1=true;}
                            if(check2%10==check%10){test2=true;}
                            if(test1){
                                if(check<check2){
                            for(int c=check;c<check2;c++){
                                if(controller.board2.getpiece(c)!=null&&c!=check){
                                    test3=false;}}}
                                else{
                                for(int c=check2;c<check;c++){
                                    if(controller.board2.getpiece(c)!=null&&c!=check2){
                                        test3=false;}}}
                            test4=false;}
                            if(test2){
                                if(check<check2){
                                for(int c=check;c<check2;c=c+10){
                                    if(controller.board2.getpiece(c)!=null&&c!=check){
                                        test4=false;}}}
                                else{
                                for(int c=check2;c<check;c=c+10){
                                    if(controller.board2.getpiece(c)!=null&&c!=check2){
                                        test4=false;}}}
                            test3=false;}
                            if((test3 && test1 && !test4 && !test2)^(!test3 && !test1 && test4 && test2)){
                    but.setIcon(selectedButton.getIcon());
                    but.setName(controller.board2.getname(check));
                   butdelete(selectedButton);
                        butt[check2].setIcon(butt[check].getIcon());
                        butdelete(butt[check]);
                        controller.board2.setpiece(check2, controller.board2.getpiece(check));
                        controller.board2.nullpiece(check);
                        iconSelected = false;
                        controller.turnchange();
                                sidepanelmake();}}
                    else{
                        if(check2-10==check||check2-1==check||check2+10==check||check2+1==check){
                        but.setIcon(selectedButton.getIcon());
                        but.setName(controller.board2.getname(check));
                            butdelete(selectedButton);
                            butt[check2].setIcon(butt[check].getIcon());
                            butdelete(butt[check]);
                            controller.board2.setpiece(check2, controller.board2.getpiece(check));
                            controller.board2.nullpiece(check);
                            iconSelected = false;
                            controller.turnchange();
                            sidepanelmake();}}}}
                //idio pioni
                else if(controller.board2.getname(check)==controller.board2.getname(check2)){
                    if(controller.board2.getplayer(check2)!=controller.currentplayer) {
                        if (controller.board2.getpiece(check) instanceof scout) {
                            boolean test1=false,test2=false,test3=true,test4=true;
                            if(check2/10==check/10){test1=true;}
                            if(check2%10==check%10){test2=true;}
                            if(test1==true){
                                if(check<check2){
                                    for(int c=check;c<check2;c++){
                                        if(controller.board2.getpiece(c)!=null&&c!=check){
                                            test3=false;}}}
                                else{
                                    for(int c=check2;c<check;c++){
                                        if(controller.board2.getpiece(c)!=null&&c!=check2){
                                            test3=false;}}}
                                test4=false;}
                            if(test2==true){
                                if(check<check2){
                                    for(int c=check;c<check2;c=c+10){
                                        if(controller.board2.getpiece(c)!=null&&c!=check){
                                            test4=false;}}}
                                else{
                                    for(int c=check2;c<check;c=c+10){
                                        if(controller.board2.getpiece(c)!=null&&c!=check2){
                                            test4=false;}}}
                                test3=false;}
                            if((test3 && test1 && !test4 && !test2)^(!test3 && !test1 && test4 && test2)){
                                but.setIcon(selectedButton.getIcon());
                                but.setName(controller.board2.getname(check));
                                butdelete(selectedButton);
                                butdelete(butt[check2]);
                                butdelete(butt[check]);
                                controller.board2.nullpiece(check2);
                                controller.board2.nullpiece(check);
                                iconSelected = false;
                                controller.turnchange();
                                sidepanelmake();}}
                        else{
                            if(check2-10==check||check2-1==check||check2+10==check||check2+1==check){
                                but.setIcon(selectedButton.getIcon());
                                but.setName(controller.board2.getname(check));
                                butdelete(selectedButton);
                                butdelete(butt[check2]);
                                butdelete(butt[check]);
                                controller.board2.nullpiece(check2);
                                controller.board2.nullpiece(check);
                                iconSelected = false;
                                controller.turnchange();
                                sidepanelmake();}}}}
                 // kanonikh maxh
                else{
                    if(controller.board2.getplayer(check2)!=controller.currentplayer) {
                        if (controller.board2.getpiece(check) instanceof scout) {
                            boolean test1=false,test2=false,test3=true,test4=true,test5=false;
                            if(check2/10==check/10){test1=true;}
                            if(check2%10==check%10){test2=true;}
                            if(test1){
                                if(check<check2){
                                    for(int c=check;c<check2;c++){
                                        if(controller.board2.getpiece(c)!=null&&c!=check){
                                            test3=false;}}}
                                else{
                                    for(int c=check2;c<check;c++){
                                        if(controller.board2.getpiece(c)!=null&&c!=check2){
                                            test3=false;}}}
                                test4=false;}
                            if(test2==true){
                                if(check<check2){
                                    for(int c=check;c<check2;c=c+10){
                                        if(controller.board2.getpiece(c)!=null&&c!=check){
                                            test4=false;}}}
                                else{
                                    for(int c=check2;c<check;c=c+10){
                                        if(controller.board2.getpiece(c)!=null&&c!=check2){
                                            test4=false;}}}
                                test3=false;}
                            if((test3==true&&test1==true&&test4==false&&test2==false)^(test3==false&&test1==false&& test4 &&test2==true)){
                                if (controller.board2.getpiece(check2) instanceof flag) {
                                    (controller.getcurrentplayer(false)).setstatus(false);
                                    but.setIcon(selectedButton.getIcon());
                                    but.setName(controller.board2.getname(check));
                                    butdelete(selectedButton);
                                    butt[check2].setIcon(butt[check].getIcon());
                                    butdelete(butt[check]);
                                    controller.board2.setpiece(check2, controller.board2.getpiece(check));}
                                else{
                                if(controller.battle(check,check2)==true){
                                but.setIcon(selectedButton.getIcon());
                                but.setName(controller.board2.getname(check));
                                butdelete(selectedButton);
                                butt[check2].setIcon(butt[check].getIcon());
                                butdelete(butt[check]);
                                controller.board2.setpiece(check2, controller.board2.getpiece(check));
                                controller.board2.nullpiece(check);}
                                else if(!controller.battle(check, check2)){
                                butdelete(butt[check]);
                                controller.board2.nullpiece(check);}}
                                iconSelected = false;
                                if (!controller.player2.getstatus() || !controller.player1.getstatus()) {
                                    controller.setgamestatus(false);}
                                //loop that checks possibility of a tie
                                for(int i=0;i<80;i++){
                                    int flagcounter=0;
                                    int othercounter=0;
                                    if(controller.board2.getpiece(i)instanceof flag){
                                        flagcounter++;}
                                    else if(controller.board2.getpiece(i)!=null&&!(controller.board2.getpiece(i)instanceof trap)){
                                        othercounter++;}
                                    if(othercounter==0&&flagcounter==2){
                                        controller.setgamestatus(false);}}
                                if (!controller.getgamestatus()) {exitmenu();}
                                controller.turnchange();
                                sidepanelmake();}}
                        else{
                            if(check2-10==check||check2-1==check||check2+10==check||check2+1==check){
                                if(controller.battle(check,check2)==true){
                                    but.setIcon(selectedButton.getIcon());
                                    but.setName(controller.board2.getname(check));
                                    butdelete(selectedButton);
                                    butt[check2].setIcon(butt[check].getIcon());
                                    butdelete(butt[check]);
                                    controller.board2.setpiece(check2, controller.board2.getpiece(check));
                                    controller.board2.nullpiece(check);}
                                else if(controller.battle(check,check2)==false){
                                    butdelete(butt[check]);
                                    controller.board2.nullpiece(check);}
                                iconSelected = false;
                                if (controller.board2.getpiece(check2) instanceof flag) {
                                    (controller.getcurrentplayer(false)).setstatus(false);}
                                if (controller.player2.getstatus() == false || controller.player1.getstatus() == false) {
                                    controller.setgamestatus(false);}
                                //loop that checks possibility of a tie
                                for(int i=0;i<80;i++){
                                    int flagcounter=0;
                                    int othercounter=0;
                                    if(controller.board2.getpiece(i)instanceof flag){
                                        flagcounter++;}
                                    else if(controller.board2.getpiece(i)!=null&&!(controller.board2.getpiece(i)instanceof trap)){
                                        othercounter++;}
                                    if(othercounter==0&&flagcounter==2){
                                        controller.setgamestatus(false);}}
                                if (!controller.getgamestatus()) {
                                    exitmenu();}
                                controller.turnchange();
                                sidepanelmake();}}}}}
                else{
                    if(controller.getcurrentplayer(true)==controller.player1){
                        if(butt[check2].getIcon()==null){
                            //metakinhsh se adeio
                            if(controller.board2.getplayer(check2)!=controller.currentplayer) {
                                if (controller.board2.getpiece(check) instanceof scout) {
                                    boolean test1=false,test2=false,test3=true,test4=true;
                                    if(check2/10==check/10){test1=true;}
                                    if(check2%10==check%10){test2=true;}
                                    if(test1==true){
                                        if(check<check2){
                                            for(int c=check;c<check2;c++){
                                                if(controller.board2.getpiece(c)!=null&&c!=check){
                                                    test3=false;}}}
                                        else{
                                            for(int c=check2;c<check;c++){
                                                if(controller.board2.getpiece(c)!=null&&c!=check2){
                                                    test3=false;}}}
                                        test4=false;}
                                    if(test2==true){
                                        if(check<check2){
                                            for(int c=check2;c<check;c=c+10){
                                                if(controller.board2.getpiece(c)!=null&&c!=check2){
                                                    test4=false;}}}
                                        else{test4=false;}
                                        test3=false;}
                                    if((test3==true&&test1==true&&test4==false&&test2==false)^(test3==false&&test1==false&&test4==true&&test2==true)){
                                        but.setIcon(selectedButton.getIcon());
                                        but.setName(controller.board2.getname(check));
                                        butdelete(selectedButton);
                                        butt[check2].setIcon(butt[check].getIcon());
                                        butdelete(butt[check]);
                                        controller.board2.setpiece(check2, controller.board2.getpiece(check));
                                        controller.board2.nullpiece(check);
                                        iconSelected = false;
                                        controller.turnchange();
                                        sidepanelmake();}}
                                else{
                                    if(check2-10==check||check2-1==check||check2+1==check){
                                        but.setIcon(selectedButton.getIcon());
                                        but.setName(controller.board2.getname(check));
                                        butdelete(selectedButton);
                                        butt[check2].setIcon(butt[check].getIcon());
                                        butdelete(butt[check]);
                                        controller.board2.setpiece(check2, controller.board2.getpiece(check));
                                        controller.board2.nullpiece(check);
                                        iconSelected = false;
                                        controller.turnchange();
                                        sidepanelmake();}}}}
                        //idio pioni
                        else if(controller.board2.getname(check)==controller.board2.getname(check2)){
                            if(controller.board2.getplayer(check2)!=controller.currentplayer) {
                                if (controller.board2.getpiece(check) instanceof scout) {
                                    boolean test1=false,test2=false,test3=true,test4=true,test5=false;
                                    if(check2/10==check/10){test1=true;}
                                    if(check2%10==check%10){test2=true;}
                                    if(test1==true){
                                        if(check<check2){
                                            for(int c=check;c<check2;c++){
                                                if(controller.board2.getpiece(c)!=null&&c!=check){
                                                    test3=false;}}}
                                        else{
                                            for(int c=check2;c<check;c++){
                                                if(controller.board2.getpiece(c)!=null&&c!=check2){
                                                    test3=false;}}}
                                        test4=false;}
                                    if(test2==true){
                                        if(check>check2){
                                            for(int c=check2;c<check;c=c+10){
                                                if(controller.board2.getpiece(c)!=null&&c!=check2){
                                                    test4=false;}}}
                                        else{test4=false;}
                                        test3=false;}
                                    if((test3==true&&test1==true&&test4==false&&test2==false)^(test3==false&&test1==false&&test4==true&&test2==true)){
                                        but.setIcon(selectedButton.getIcon());
                                        but.setName(controller.board2.getname(check));
                                        butdelete(selectedButton);
                                        butdelete(butt[check2]);
                                        butdelete(butt[check]);
                                        controller.board2.nullpiece(check2);
                                        controller.board2.nullpiece(check);
                                        iconSelected = false;
                                        controller.turnchange();
                                        sidepanelmake();}}
                                else{
                                    if(check2-10==check||check2-1==check||check2+1==check){
                                        but.setIcon(selectedButton.getIcon());
                                        but.setName(controller.board2.getname(check));
                                        butdelete(selectedButton);
                                        butdelete(butt[check2]);
                                        butdelete(butt[check]);
                                        controller.board2.nullpiece(check2);
                                        controller.board2.nullpiece(check);
                                        iconSelected = false;
                                        controller.turnchange();
                                        sidepanelmake();}}}}
                        else{
                            if(controller.board2.getplayer(check2)!=controller.currentplayer) {
                                if (controller.board2.getpiece(check) instanceof scout) {
                                    boolean test1=false,test2=false,test3=true,test4=true;
                                    if(check2/10==check/10){test1=true;}
                                    if(check2%10==check%10){test2=true;}
                                    if(test1==true){
                                        if(check<check2){
                                            for(int c=check;c<check2;c++){
                                                if(controller.board2.getpiece(c)!=null&&c!=check){
                                                    test3=false;}}}
                                        else{
                                            for(int c=check2;c<check;c++){
                                                if(controller.board2.getpiece(c)!=null&&c!=check2){
                                                    test3=false;}}}
                                        test4=false;}
                                    if(test2){
                                        if(check<check2){
                                            for(int c=check2;c<check;c=c+10){
                                                if(controller.board2.getpiece(c)!=null&&c!=check2){
                                                    test4=false;}}}
                                        else{test4=false;}
                                        test3=false;}
                                    if((test3 && test1 && !test4 && !test2)^(!test3 && !test1 && test4 && test2)){
                                        if (controller.board2.getpiece(check2) instanceof flag) {
                                            (controller.getcurrentplayer(false)).setstatus(false);
                                            but.setIcon(selectedButton.getIcon());
                                            but.setName(controller.board2.getname(check));
                                            butdelete(selectedButton);
                                            butt[check2].setIcon(butt[check].getIcon());
                                            butdelete(butt[check]);
                                            controller.board2.setpiece(check2, controller.board2.getpiece(check));}
                                        else{
                                            if(controller.battle(check,check2)==true){
                                                but.setIcon(selectedButton.getIcon());
                                                but.setName(controller.board2.getname(check));
                                                butdelete(selectedButton);
                                                butt[check2].setIcon(butt[check].getIcon());
                                                butdelete(butt[check]);
                                                controller.board2.setpiece(check2, controller.board2.getpiece(check));
                                                controller.board2.nullpiece(check);}
                                            else if(controller.battle(check,check2)==false){
                                                butdelete(butt[check]);
                                                controller.board2.nullpiece(check);}}
                                        iconSelected = false;
                                        if (controller.player2.getstatus() == false || controller.player1.getstatus() == false) {
                                            controller.setgamestatus(false);}
                                        //loop that checks possibility of a tie
                                        for(int i=0;i<80;i++){
                                            int flagcounter=0;
                                            int othercounter=0;
                                            if(controller.board2.getpiece(i)instanceof flag){
                                                flagcounter++;}
                                            else if(controller.board2.getpiece(i)!=null&&!(controller.board2.getpiece(i)instanceof trap)){
                                                othercounter++;}
                                            if(othercounter==0&&flagcounter==2){
                                                controller.setgamestatus(false);}}
                                        if (!controller.getgamestatus()) {
                                            exitmenu();}
                                        controller.turnchange();
                                        sidepanelmake();}}
                                else{
                                    if(check2-10==check||check2-1==check||check2+1==check){
                                        if(controller.battle(check,check2)==true){
                                            but.setIcon(selectedButton.getIcon());
                                            but.setName(controller.board2.getname(check));
                                            butdelete(selectedButton);
                                            butt[check2].setIcon(butt[check].getIcon());
                                            butdelete(butt[check]);
                                            controller.board2.setpiece(check2, controller.board2.getpiece(check));
                                            controller.board2.nullpiece(check);}
                                        else if(controller.battle(check,check2)==false){
                                            butdelete(butt[check]);
                                            controller.board2.nullpiece(check);}
                                        iconSelected = false;
                                        if (controller.board2.getpiece(check2) instanceof flag) {
                                            (controller.getcurrentplayer(false)).setstatus(false);}
                                        if (controller.player2.getstatus() == false || controller.player1.getstatus() == false) {
                                            controller.setgamestatus(false);}
                                        //loop that checks possibility of a tie
                                        for(int i=0;i<80;i++){
                                            int flagcounter=0;
                                            int othercounter=0;
                                            if(controller.board2.getpiece(i)instanceof flag){
                                                flagcounter++;}
                                            else if(controller.board2.getpiece(i)!=null&&!(controller.board2.getpiece(i)instanceof trap)){
                                                othercounter++;}
                                            if(othercounter==0&&flagcounter==2){
                                                controller.setgamestatus(false);}}
                                        if (!controller.getgamestatus()) {
                                            exitmenu();}
                                        controller.turnchange();
                                        sidepanelmake();}}}}}
                        else{
                            if(butt[check2].getIcon()==null){
                                //metakinhsh se adeio
                                if(controller.board2.getplayer(check2)!=controller.currentplayer) {
                                    if (controller.board2.getpiece(check) instanceof scout) {
                                        boolean test1=false,test2=false,test3=true,test4=true;
                                        if(check2/10==check/10){test1=true;}
                                        if(check2%10==check%10){test2=true;}
                                        if(test1==true){
                                            if(check<check2){
                                                for(int c=check;c<check2;c++){
                                                    if(controller.board2.getpiece(c)!=null&&c!=check){
                                                        test3=false;}}}
                                            else{
                                                for(int c=check2;c<check;c++){
                                                    if(controller.board2.getpiece(c)!=null&&c!=check2){
                                                        test3=false;}}}
                                            test4=false;}
                                        if(test2==true){
                                            if(check>check2){
                                                for(int c=check;c<check2;c=c+10){
                                                    if(controller.board2.getpiece(c)!=null&&c!=check){
                                                        test4=false;}}}
                                            else{test4=false;}
                                            test3=false;}
                                        if((test3==true&&test1==true&&test4==false&&test2==false)^(test3==false&&test1==false&&test4==true&&test2==true)){
                                            but.setIcon(selectedButton.getIcon());
                                            but.setName(controller.board2.getname(check));
                                            butdelete(selectedButton);
                                            butt[check2].setIcon(butt[check].getIcon());
                                            butdelete(butt[check]);
                                            controller.board2.setpiece(check2, controller.board2.getpiece(check));
                                            controller.board2.nullpiece(check);
                                            iconSelected = false;
                                            controller.turnchange();
                                            sidepanelmake();}}
                                    else{
                                        if(check2-1==check||check2+10==check||check2+1==check){
                                            but.setIcon(selectedButton.getIcon());
                                            but.setName(controller.board2.getname(check));
                                            butdelete(selectedButton);
                                            butt[check2].setIcon(butt[check].getIcon());
                                            butdelete(butt[check]);
                                            controller.board2.setpiece(check2, controller.board2.getpiece(check));
                                            controller.board2.nullpiece(check);
                                            iconSelected = false;
                                            controller.turnchange();
                                            sidepanelmake();}}}}
                            //idio pioni
                            else if(controller.board2.getname(check)==controller.board2.getname(check2)){
                                if(controller.board2.getplayer(check2)!=controller.currentplayer) {
                                    if (controller.board2.getpiece(check) instanceof scout) {
                                        boolean test1=false,test2=false,test3=true,test4=true;
                                        if(check2/10==check/10){test1=true;}
                                        if(check2%10==check%10){test2=true;}
                                        if(test1==true){
                                            if(check<check2){
                                                for(int c=check;c<check2;c++){
                                                    if(controller.board2.getpiece(c)!=null&&c!=check){
                                                        test3=false;}}}
                                            else{
                                                for(int c=check2;c<check;c++){
                                                    if(controller.board2.getpiece(c)!=null&&c!=check2){
                                                        test3=false;}}}
                                            test4=false;}
                                        if(test2==true){
                                            if(check>check2){
                                                for(int c=check;c<check2;c=c+10){
                                                    if(controller.board2.getpiece(c)!=null&&c!=check){
                                                        test4=false;}}}
                                            else{test4=false;}
                                            test3=false;}
                                        if((test3==true&&test1==true&&test4==false&&test2==false)^(test3==false&&test1==false&&test4==true&&test2==true)){
                                            but.setIcon(selectedButton.getIcon());
                                            but.setName(controller.board2.getname(check));
                                            butdelete(selectedButton);
                                            butdelete(butt[check2]);
                                            butdelete(butt[check]);
                                            controller.board2.nullpiece(check2);
                                            controller.board2.nullpiece(check);
                                            iconSelected = false;
                                            controller.turnchange();
                                            sidepanelmake();}}
                                    else{
                                        if(check2-1==check||check2+10==check||check2+1==check){
                                            but.setIcon(selectedButton.getIcon());
                                            but.setName(controller.board2.getname(check));
                                            butdelete(selectedButton);
                                            butdelete(butt[check2]);
                                            butdelete(butt[check]);
                                            controller.board2.nullpiece(check2);
                                            controller.board2.nullpiece(check);
                                            iconSelected = false;
                                            controller.turnchange();
                                            sidepanelmake();}}}}
                            // kanonikh maxh
                            else{
                                if(controller.board2.getplayer(check2)!=controller.currentplayer) {
                                    if (controller.board2.getpiece(check) instanceof scout){
                                        boolean test1=false,test2=false,test3=true,test4=true;
                                        if(check2/10==check/10){test1=true;}
                                        if(check2%10==check%10){test2=true;}
                                        if(test1==true){
                                            if(check<check2){
                                                for(int c=check;c<check2;c++){
                                                    if(controller.board2.getpiece(c)!=null&&c!=check){
                                                        test3=false;}}}
                                            else{
                                                for(int c=check2;c<check;c++){
                                                    if(controller.board2.getpiece(c)!=null&&c!=check2){
                                                        test3=false;}}}
                                            test4=false;}
                                        if(test2==true){
                                            if(check>check2){
                                                for(int c=check;c<check2;c=c+10){
                                                    if(controller.board2.getpiece(c)!=null&&c!=check){
                                                        test4=false;}}}
                                            else{test4=false;}
                                            test3=false;}
                                        if((test3==true&&test1==true&&test4==false&&test2==false)^(test3==false&&test1==false&&test4==true&&test2==true)){
                                            if (controller.board2.getpiece(check2) instanceof flag) {
                                                (controller.getcurrentplayer(false)).setstatus(false);
                                                but.setIcon(selectedButton.getIcon());
                                                but.setName(controller.board2.getname(check));
                                                butdelete(selectedButton);
                                                butt[check2].setIcon(butt[check].getIcon());
                                                butdelete(butt[check]);
                                                controller.board2.setpiece(check2, controller.board2.getpiece(check));}
                                            else{
                                                if(controller.battle(check,check2)==true){
                                                    but.setIcon(selectedButton.getIcon());
                                                    but.setName(controller.board2.getname(check));
                                                    butdelete(selectedButton);
                                                    butt[check2].setIcon(butt[check].getIcon());
                                                    butdelete(butt[check]);
                                                    controller.board2.setpiece(check2, controller.board2.getpiece(check));
                                                    controller.board2.nullpiece(check);}
                                                else if(controller.battle(check,check2)==false){
                                                    butdelete(butt[check]);
                                                    controller.board2.nullpiece(check);}}
                                            iconSelected = false;
                                            if (controller.player2.getstatus() == false || controller.player1.getstatus() == false) {
                                                controller.setgamestatus(false);}
                                            //loop that checks possibility of a tie
                                            for(int i=0;i<80;i++){
                                                int flagcounter=0;
                                                int othercounter=0;
                                                if(controller.board2.getpiece(i)instanceof flag){
                                                    flagcounter++;}
                                                else if(controller.board2.getpiece(i)!=null&&!(controller.board2.getpiece(i)instanceof trap)){
                                                    othercounter++;}
                                                if(othercounter==0&&flagcounter==2){
                                                    controller.setgamestatus(false);}}
                                            if (!controller.getgamestatus()) {
                                                exitmenu();}
                                            controller.turnchange();
                                            sidepanelmake();}}
                                    else{
                                        if(check2-1==check||check2+10==check||check2+1==check){
                                            if(controller.battle(check,check2)==true){
                                                but.setIcon(selectedButton.getIcon());
                                                but.setName(controller.board2.getname(check));
                                                butdelete(selectedButton);
                                                butt[check2].setIcon(butt[check].getIcon());
                                                butdelete(butt[check]);
                                                controller.board2.setpiece(check2, controller.board2.getpiece(check));
                                                controller.board2.nullpiece(check);}
                                            else if(controller.battle(check,check2)==false){
                                                butdelete(butt[check]);
                                                controller.board2.nullpiece(check);}
                                            iconSelected = false;
                                            if (controller.board2.getpiece(check2) instanceof flag) {
                                                (controller.getcurrentplayer(false)).setstatus(false);}
                                            if (controller.player2.getstatus() == false || controller.player1.getstatus() == false) {
                                                controller.setgamestatus(false);}
                                            //loop that checks possibility of a tie
                                            for(int i=0;i<80;i++){
                                                int flagcounter=0;
                                                int othercounter=0;
                                                if(controller.board2.getpiece(i)instanceof flag){
                                                    flagcounter++;}
                                                else if(controller.board2.getpiece(i)!=null&&!(controller.board2.getpiece(i)instanceof trap)){
                                                    othercounter++;}
                                                if(othercounter==0&&flagcounter==2){
                                                    controller.setgamestatus(false);}}
                                            if (!controller.getgamestatus()) {
                                                exitmenu();}
                                            controller.turnchange();
                                            sidepanelmake();}}}}}}
                but.setBorder(BorderFactory.createLineBorder(Color.black));
                iconSelected = false;
                butmake(controller,cl,gamepanel,true);}
            else if(!iconSelected && but.getName()!=null){
                iconSelected=true;
                selectedButton=but;
                for(int i=0;i<80;i++){
                    if(butt[i]==selectedButton){check=i;}}
                but.setBorder(BorderFactory.createLineBorder(Color.YELLOW,1));}
            else if (iconSelected) {
                     but.setBorder(BorderFactory.createLineBorder(Color.black));
                   iconSelected = false;}}

        //not used mouselistener functions
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}}}

































