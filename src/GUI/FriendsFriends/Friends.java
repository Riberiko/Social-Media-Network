package GUI.FriendsFriends;

import ADTPackage.QueueInterface;
import GUI.Main.MainFrame;
import Profile.Profile;
import Profile.UI.UI;

import javax.swing.*;
import java.awt.*;

public class Friends extends JFrame {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 600;
    private Screen screen;
    private JPanel screenPanel;
    private MainFrame main;
    public String id;


    public Friends(MainFrame main, String id){
        setTitle("Viewing @"+id+" Friends");
        this.id = id;
        this.main = main;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        screenPanel = new JPanel();
        screen = new Screen(screenPanel, main);
        add(screen);
        pack();
        setLocation(main.getX() + main.getWidth(), main.getY());
        setVisible(true);
    }

    private class Screen extends JScrollPane{

        Screen(JPanel screen, MainFrame main){
            super(screen);
            screen.setPreferredSize(new Dimension(Friends.WIDTH, Friends.HEIGHT));
            pack();

            QueueInterface<Profile> temp = main.manager.getFriends(id).getDepthFirstTraversal(main.manager.allUsers.getValue(id));
            temp.dequeue();
            while(!temp.isEmpty()){
                Profile current = temp.dequeue();
                screen.add(new UI(current.getPic(), current.getId(), current.getName(), main));
            }
        }


    }
}
