package Profile.UI;

import GUI.FriendsFriends.Friends;
import GUI.Main.MainFrame;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UI extends JPanel implements MouseListener {

    public JLabel icon;
    public JLabel info;
    public String id;

    private MainFrame main;

    public UI(String icon, String username, String name, MainFrame main){
        this.main = main;
        id = username;
        setPreferredSize(new Dimension(250, 100));
        this.icon = new JLabel(new ImageIcon("res/"+icon));

        this.info = new JLabel(createHtmlInfo(username, name));

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.icon.setBorder(new StrokeBorder(new BasicStroke(1)));
        this.icon.setPreferredSize(new Dimension(95,75));
        this.icon.setMaximumSize(new Dimension(140,95));
        this.icon.setBackground(Color.black);

        add(this.icon, CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(5,0)));
        add(this.info, CENTER_ALIGNMENT);

        setBorder(new StrokeBorder(new BasicStroke(3)));
        addMouseListener(this);
    }

    public String createHtmlInfo(String username, String name){
        return "<html>@"+username+"</h1><br><h2>"+name+"</h2></html>";

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(main.friends != null) main.friends.dispose();
        main.friends = new Friends(main, id);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setBackground(Color.BLACK);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setBackground(Color.GREEN);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(Color.GREEN);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(Color.white);
    }
}
