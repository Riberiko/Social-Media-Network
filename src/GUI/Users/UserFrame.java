package GUI.Users;

import GUI.Main.MainFrame;
import Profile.Profile;
import Profile.UI.UI;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class UserFrame extends JFrame {

    private static final String name = "ALl Users";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 600;
    public Screen screen;
    public MainFrame main;
    private LinkedList<UI> allUI;

    public UserFrame(MainFrame main){
        this.main = main;
        main.users = this;  //dont delete this tells main what new user frame we are using
        allUI = new LinkedList<>();
        setResizable(false);
        setTitle(name);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(main.getX()- WIDTH - 15, main.getY());
        screen = new Screen(new JPanel(), main);
        add(screen);
        pack();
        setVisible(true);

        if (main.manager.allUsers.getSize() != allUI.size()) {
            Iterator<Profile> iter = main.manager.allUsers.getValueIterator();
            while (iter.hasNext()) {
                Profile cur = iter.next();
                addUI(new UI(cur.getPic(), cur.getId(), cur.getName(), main));
            }
        }
    }

    public void addUI(UI profile) {
        main.users.screen.screen.add(profile);
        main.users.screen.screen.revalidate();
        main.users.allUI.add(profile);
    }
    public void deleteUI(String id){
        main.users.screen.screen.removeAll();
        Iterator<Profile> iter = main.manager.allUsers.getValueIterator();
        while (iter.hasNext()) {
            Profile cur = iter.next();
            addUI(new UI(cur.getPic(), cur.getId(), cur.getName(), main));
        }
        main.users.screen.screen.validate();
        main.users.screen.screen.repaint();
    }

    public void updateUser(String id, String name, String pic){
        int count = 0;
        for(UI u : allUI){
            if(u.id.equals(id)){
                break;
            }
            count += 1;
        }
        UI user = allUI.get(count);
        if(name != null) {user.info.setText(user.createHtmlInfo(id, name));}
        if(pic != null) user.icon.setIcon(new ImageIcon("res/"+pic));
        user.revalidate();
        user.repaint();


    }

    public UI getUI(String str){

        for(UI i : allUI){
            if(i.id.equals(str)) return i;
        }
        return null;
    }

    private class Screen extends JScrollPane{

        private JPanel screen;

        public Screen(JPanel screen, MainFrame main){
            super(screen);
            this.screen = screen;
            setPreferredSize(new Dimension(UserFrame.WIDTH, UserFrame.HEIGHT));
            screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));

            pack();
        }
    }

}
