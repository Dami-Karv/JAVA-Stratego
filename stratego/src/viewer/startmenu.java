package viewer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * class that creates a start menu
 * so the player can select extra gamemodes
 * or start the game
 * @version 1.1
 * @author dami karv - csd4897
 */

public class startmenu extends JFrame{
        private JPanel mainMenuPanel;
        private JButton startgamebutton,nostepbutton,reducedbutton;
        private JLabel backgroundimage;
        private Dimension buttonResolution;
        public boolean nostepcheck,reducedcheck;


    /**
     * <b>constructor</b>: Constructs a new start menu
     * <b>postcondition:</b>a new start menu is constructed
     */
        public startmenu() {
            buildmenu();
            this.nostepbutton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    nostepcheck=true;
                }});
            this.reducedbutton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    reducedcheck=true;
                }});
            this.startgamebutton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewerjv viewerjv = new viewerjv(nostepcheck,reducedcheck);
                    dispose();}});
            this.validate();}

    /**
     * <b>Transformer</b> builds the start menu
     * <b>postcondition:</b>start menu is built and displayed
     */
        private void buildmenu() {
            this.setTitle("STRATEGO");
            this.setSize(960, 560);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.buttonResolution = new Dimension(128, 49);
            this.setVisible(true);
            this.mainMenuPanel = new JPanel();
            this.mainMenuPanel.setLocation(260, 260);
            this.mainMenuPanel.setOpaque(false);
            this.mainMenuPanel.setSize(buttonResolution.width + 5, (buttonResolution.height + 30) * 3);
            //button creating
            this.startgamebutton = new JButton();
            this.addbutton(this.startgamebutton, "/images/other/startbutton.png", new Point(430,260));
            this.reducedbutton = new JButton();
            this.addbutton(this.reducedbutton, "/images/other/reducedarmy.png", new Point(430, 310));
            this.nostepbutton = new JButton();
            this.addbutton(this.nostepbutton, "/images/other/nostepback.png", new Point(430, 360));
            this.mainMenuPanel.setLayout(new BorderLayout());
            this.add(mainMenuPanel);
            //add background image
            this.backgroundimage = new JLabel();
            this.backgroundimage.setIcon(new ImageIcon(getClass().getResource("/images/other/startmenu.png")));
            this.setPreferredSize(new Dimension(960, 540));
            this.backgroundimage.setSize(this.getSize());
            this.add(this.backgroundimage);}


    /**
     * <b>Transformer</b> adds a button to the JPanel
     * <b>postcondition:</b> the button is added to the JPanel
     */
        private void addbutton(JButton m_Button, String i_ImageSrc, Point i_Location) {
            m_Button.setIcon(new ImageIcon(getClass().getResource(i_ImageSrc)));
            m_Button.setSize(buttonResolution);
            m_Button.setVisible(true);
            m_Button.setLocation(i_Location);
            this.add(m_Button);}}
