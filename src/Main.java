import GUI.FriendsFriends.Friends;
import GUI.Main.MainFrame;
import GUI.Users.UserFrame;
import Profile.Profile;
import Profile.ProfileManager;

public class Main
{

    //Delete not working after add

    public static void main(String[] args)
    {
        ProfileManager manager = new ProfileManager();
        MainFrame main = new MainFrame(manager);
        manager.setAllUserPanel(main.users);

        manager.addUser(new Profile("alex.png", "hi","First"));
        manager.addUser(new Profile("alex.png", "jk","Second"));
        manager.addUser(new Profile("alex.png", "h","Third"));

        //System.out.println(manager);
    }

}
