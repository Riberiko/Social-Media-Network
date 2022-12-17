package GUI.Main;

import ADTPackage.QueueInterface;
import GUI.FriendsFriends.Friends;
import GUI.Users.UserFrame;
import GraphPackage.UndirectedGraph;
import Profile.Profile;
import Profile.ProfileManager;
import Profile.UI.UI;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

public class MainFrame extends JFrame {

    private static final String name = "Social-Media-Network Main";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    public UserFrame users;
    public Friends friends;
    public MainFriend mainFriend;
    public ProfileManager manager;
    public Profile currentUser;
    public MainPanel mainPanel;

    public MainFrame(ProfileManager manager){
        this.manager = manager;
        setResizable(false);
        setTitle(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        mainFriend = new MainFriend(this, new JPanel());
        mainPanel = new MainPanel(this);
        add(mainPanel, BorderLayout.NORTH);
        add(new ButtonPanel(this), BorderLayout.SOUTH);
        add(mainFriend, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        users = new UserFrame(this);
        setVisible(true);
    }

    private class MainPanel extends JPanel{

        public JLabel icon;
        public JLabel current;
        public JButton makeFriends;
        public JButton removeFriend;

        public MainPanel(MainFrame main){
            setPreferredSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
            setLayout(new GridLayout(3,2));

            icon = new JLabel(new ImageIcon(), SwingConstants.CENTER);
            icon.setBorder(new StrokeBorder(new BasicStroke(2)));
            current = new JLabel("Please Sign in", SwingConstants.CENTER);
            makeFriends = new JButton("Click here to add friend");
            removeFriend = new JButton("Click here to remove friend");

            makeFriends.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String user2 = "";
                    boolean failed = false;
                    do{
                        if(failed) JOptionPane.showMessageDialog(main, "The user was not found");
                        user2 = JOptionPane.showInputDialog("Enter the users id that u want to be friends  (not u)\nDo not include the @");
                        if(user2 == null) return;
                        failed = true;
                    }while(currentUser.getId().equals(user2) || manager.validID(user2) );
                    manager.makeFriends(currentUser.getId(), user2);

                    Profile user = main.manager.allUsers.getValue(user2);

                    main.mainFriend.addUI(new UI(user.getPic(), user.getId(),user.getName(), main));
                    main.mainFriend.revalidate();
                    main.repaint();

                    if(main.users != null){
                        main.users.revalidate();
                        main.users.repaint();
                    }

                    if(main.friends == null) return;
                    String oldId = main.friends.id;
                    main.friends.dispose();
                    main.friends = new Friends(main, oldId);
                }
            });

            removeFriend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String id = "";
                    boolean failed = false;
                    boolean found = false;
                    do{
                        if(failed) JOptionPane.showMessageDialog(main, "That was not a friend of yours");
                        id = JOptionPane.showInputDialog("Friends id you wish to remove");
                        if(id == null) return;

                        //step1 finds the user exsists
                        QueueInterface<Profile> temp = main.manager.getFriends(currentUser.getId()).getDepthFirstTraversal(currentUser);
                        temp.dequeue(); //skips self
                        while(!temp.isEmpty()){
                            if(temp.dequeue().getId().equals(id)) found = true;
                        }

                    }while (!found);

                    //step 2 create new network
                    UndirectedGraph<Profile> newGraph = new UndirectedGraph<>();
                    Iterator<Profile> profIter = main.manager.allUsers.getValueIterator();
                    while(profIter.hasNext())newGraph.addVertex(profIter.next());   //adds all users back into network

                    //step3 reconnect all friends
                    QueueInterface<Profile> friends = main.manager.getFriends(currentUser.getId()).getDepthFirstTraversal(currentUser);
                    friends.dequeue();  //skips first
                    main.mainFriend.clear();
                    while(!friends.isEmpty()) {
                        Profile curr = friends.dequeue();
                        if(!curr.getId().equals(id)){
                            newGraph.addEdge(currentUser, curr);    //will not connect the one we wish to
                            main.mainFriend.addUI(new UI(curr.getPic(), curr.getId(), curr.getName(), main));
                        }
                    }
                    main.manager.friendNetwork.add(currentUser.getId(), newGraph);  //replaces old graph

                    //step 4 create new network
                    newGraph = new UndirectedGraph<>();
                    profIter = main.manager.allUsers.getValueIterator();
                    while(profIter.hasNext())newGraph.addVertex(profIter.next());   //adds all users back into network

                    //step 5 reconnect all friends
                    friends = main.manager.getFriends(currentUser.getId()).getDepthFirstTraversal(main.manager.allUsers.getValue(id));
                    friends.dequeue();  //skips first
                    while(!friends.isEmpty()) {
                        Profile curr = friends.dequeue();
                        if(!curr.getId().equals(currentUser)) newGraph.addEdge(currentUser, curr);    //will not connect the one we wish to remove
                    }

                    main.manager.friendNetwork.add(id, newGraph);   //replaces old graph

                    if(main.friends != null){
                        String oldId = main.friends.id;
                        main.friends.dispose();
                        main.friends = new Friends(main, oldId);
                    }

                    mainFriend.remove(id);
                    mainFriend.revalidate();
                    mainFriend.repaint();


                }
            });


            add(icon);
            add(current);
            add(makeFriends);
            add(removeFriend);
            add(new JLabel("Your Friends List Below", SwingConstants.CENTER));
            makeFriends.setEnabled(false);
            removeFriend.setEnabled(false);
        }


    }

    private class ButtonPanel extends JPanel{
        private JButton allUsers;
        private JButton addUser;
        private JButton modify;
        private JButton delete;
        private JButton login;
        private JButton logout;

        public ButtonPanel(MainFrame main){
            allUsers = new JButton("All Users");
            modify = new JButton("Modify");
            addUser = new JButton("Add User");
            delete = new JButton("Delete");
            login = new JButton("Log in");
            logout = new JButton("Log out");
            setBackground(Color.BLACK);
            setPreferredSize(new Dimension(550,50));
            setLayout(new FlowLayout());

            allUsers.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    users.dispose();
                    users = new UserFrame(main);
                }
            });

            addUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newID = "";
                    Boolean failed = false;
                    do {
                        if(failed) JOptionPane.showMessageDialog(main,"The id must be unique, and can not be empty");
                        newID = JOptionPane.showInputDialog("Desired ID? : ");
                        if(newID == null) return;
                        failed = true;
                    }while (newID.isEmpty() || !manager.validID(newID));


                    failed = false;
                    String newName = "";
                    do {
                        if(failed) JOptionPane.showMessageDialog(main,"Name must be grater than 0 and can not exceed 14 characters");
                        newName = JOptionPane.showInputDialog("Desired Name : ");
                        if(newName == null) return;
                        failed = true;
                    }while(newName.isEmpty() || newName.length() > 14 || newName.length() <= 0);

                    failed = false;
                    String newIcon = "";
                    do{
                        if(failed) JOptionPane.showMessageDialog(main, "");
                        newIcon = JOptionPane.showInputDialog("Pick profile imag\nAlex, Creeper, or Enderman");
                        if(newIcon == null) return;
                        failed = true;
                    }while(newIcon.isEmpty() || (!newIcon.toLowerCase().equals("alex") && !newIcon.toLowerCase().equals("creeper") && !newIcon.toLowerCase().equals("enderman")) );

                    newIcon = newIcon.toLowerCase();

                    String ending = ".png";
                    if(newIcon.equals("creeper")) ending = ".jpg";


                    manager.addUser(new Profile(newIcon+ending, newID, newName));
                    main.mainFriend.revalidate();
                    main.mainFriend.repaint();

                }
            });

            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String temp = "";
                    boolean failed = false;
                    do{
                        if(failed) JOptionPane.showMessageDialog(main, "User not found");
                        temp = JOptionPane.showInputDialog("Enter Valid user Id to log in");
                        if(temp == null) return;
                        failed = true;
                    }while (manager.validID(temp));

                    currentUser = manager.getProfile(temp);
                    currentUser.setOnline(true);
                    delete.setEnabled(true);
                    modify.setEnabled(true);
                    logout.setEnabled(true);
                    login.setEnabled(false);
                    main.mainPanel.makeFriends.setEnabled(true);
                    main.mainPanel.removeFriend.setEnabled(true);
                    main.mainPanel.icon.setIcon(new ImageIcon("res/"+currentUser.getPic()));
                    main.mainPanel.current.setText("<html>Current user : "+currentUser.getId() +"<br>Name : "+currentUser.getName()+"</html>");

                    UndirectedGraph<Profile> freinds = manager.getFriends(currentUser.getId());
                    QueueInterface<Profile> q = freinds.getDepthFirstTraversal(currentUser);
                    q.dequeue();
                    while (!q.isEmpty()) {
                        Profile cur = q.dequeue();
                        System.out.println(cur.getPic());
                        mainFriend.addUI(new UI(cur.getPic(), cur.getId(), cur.getName(), main));
                    }
                }
            });

            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    delete.setEnabled(false);
                    logout.setEnabled(false);
                    login.setEnabled(true);
                    modify.setEnabled(false);

                    //step 1
                    main.manager.allUsers.remove(currentUser.getId());
                    main.manager.friendNetwork.remove(currentUser.getId());

                    UndirectedGraph<Profile> newGraph;

                    //step 2
                    Iterator<String> userIter = main.manager.allUsers.getKeyIterator();
                    while(userIter.hasNext()){
                        newGraph = new UndirectedGraph<Profile>();
                        Iterator<Profile> profIter = main.manager.allUsers.getValueIterator();
                        while(profIter.hasNext()){
                            newGraph.addVertex(profIter.next());
                        }
                        Profile curr = main.manager.getProfile(userIter.next());
                        UndirectedGraph<Profile> oldgraph = main.manager.getFriends(curr.getId());
                        QueueInterface<Profile> oldQ = oldgraph.getDepthFirstTraversal(curr);
                        while(!oldQ.isEmpty()){
                            newGraph.addEdge(curr, oldQ.dequeue());
                        }
                        main.manager.friendNetwork.add(curr.getId(), newGraph); //should replace the old graph

                    }

                    //step 3
                    if( main.friends != null && main.friends.id.equals(currentUser.getId())) main.friends.dispose();
                    else{
                        if(main.friends != null) {
                            String oldId = main.friends.id;
                            main.friends.dispose();
                            main.friends = new Friends(main, oldId);
                        }
                    }

                    //step 4
                    main.users.deleteUI(currentUser.getId());

                    main.mainPanel.makeFriends.setEnabled(false);
                    main.mainPanel.removeFriend.setEnabled(false);
                    main.mainPanel.current.setText("User was Deleted");
                    main.mainPanel.icon.setIcon(new ImageIcon());
                    main.mainFriend.clear();
                    currentUser = null;
                }
            });

            logout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentUser.setOnline(false);
                    currentUser = null;
                    delete.setEnabled(false);
                    logout.setEnabled(false);
                    login.setEnabled(true);
                    modify.setEnabled(false);

                    main.mainPanel.icon.setIcon(new ImageIcon());
                    main.mainPanel.current.setText("Please sign in");
                    main.mainPanel.removeFriend.setEnabled(false);
                    main.mainPanel.makeFriends.setEnabled(false);
                    main.mainFriend.clear();
                }
            });

            modify.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newName = "";
                    do{
                        newName = JOptionPane.showInputDialog(main, "Enter a new name or cancel to not change name\nName must be more than 0 characters and less than 14");
                        if (newName == null) break;
                    }while (name.isEmpty() || newName.length() > 14 || newName.length() <= 0);
                    if(newName != null)main.manager.modifyUserName(currentUser.getId(), newName);

                    String newIcon = "";
                    do{
                        newIcon = JOptionPane.showInputDialog(main, "change profile pic to Alex, Enderman or creeper or cancel to not change");
                    }while (newIcon.isEmpty() || (!newIcon.toLowerCase().equals("alex") && !newIcon.toLowerCase().equals("creeper") && !newIcon.toLowerCase().equals("enderman")) );
                    newIcon = newIcon.toLowerCase();
                    String ending = ".png";
                    if(newIcon.equals("creeper")) ending = ".jpg";
                    if(newIcon != null) main.manager.modifyUserPic(currentUser.getId(), newIcon+ending);
                    main.users.updateUser(currentUser.getId(), newName, newIcon+ending);
                    main.mainPanel.icon.setIcon(new ImageIcon("res/"+currentUser.getPic()));
                    main.mainPanel.current.setText("<html>Current user : "+currentUser.getId() +"<br>Name : "+currentUser.getName()+"</html>");

                    if(main.friends != null){
                        String oldId = main.friends.id;
                        main.friends.dispose();
                        main.friends = new Friends(main, oldId);
                    }

                }
            });

            modify.setEnabled(false);
            delete.setEnabled(false);
            logout.setEnabled(false);

            add(allUsers);
            add(addUser);
            add(modify);
            add(delete);
            add(login);
            add(logout);
        }
    }

    public class MainFriend extends JScrollPane{

        public JPanel screen;
        private int  WIDTH = 500;
        private int  HEIGHT = 130;
        private LinkedList<UI> friendsUI;

        public MainFriend(MainFrame main, JPanel panel){
            super(panel);
            screen = panel;
            friendsUI = new LinkedList<>();
            panel.setLayout(new GridLayout(1,0));
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
        }

        public void addUI(UI profile){
            screen.add(profile);
            friendsUI.add(profile);
        }

        public void  remove(String id){
            int counter = 0;
            for(UI u : friendsUI){
                if(u.id.equals(id)) break;
                counter += 1;
            }
            screen.remove(friendsUI.get(counter));
        }

        public void clear(){
            screen.removeAll();
            screen.revalidate();
            screen.repaint();
        }

        public void refresh() {
            screen.revalidate();
            screen.repaint();
        }
    }


}
