import Profile.Profile;
import Profile.ProfileManager;

public class Main
{

    public static void main(String[] args)
    {
        ProfileManager manager = new ProfileManager();

        manager.addUser( new Profile("Riberiko Niyomwungere", "its.riko", true));
        manager.addUser(new Profile("Athanase Nishemezwe","thenish17", false));
        manager.addUser(new Profile("Cesariya Hatangima", "theces17", false));

        manager.makeFriends("its.riko", "theces17");
        manager.follow("its.riko", "thenish17");

        manager.modifyUserName("its.riko", "Riko");

        System.out.println(manager);
    }

}
