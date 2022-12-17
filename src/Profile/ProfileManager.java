package Profile;

import ADTPackage.DictionaryInterface;
import ADTPackage.QueueInterface;
import ADTPackage.UnsortedLinkedDictionary;
import GUI.Users.UserFrame;
import GraphPackage.UndirectedGraph;
import Profile.UI.UI;

import java.util.Iterator;

public class ProfileManager {


    public DictionaryInterface<String, Profile> allUsers;
    public DictionaryInterface<String, UndirectedGraph<Profile>> friendNetwork;
    private UserFrame allUserPanel;

    public ProfileManager()
    {
        friendNetwork = new UnsortedLinkedDictionary<>();
        allUsers = new UnsortedLinkedDictionary<String, Profile>();
    }

    public void addUser(Profile profile)
    {
        if(!validID(profile.getId())) return;
        friendNetwork.add(profile.getId(), new UndirectedGraph<Profile>());

        Iterator<UndirectedGraph<Profile>> iter = friendNetwork.getValueIterator();
        while(iter.hasNext()){
            UndirectedGraph<Profile> cur = iter.next();
            cur.addVertex(profile);     //ads this to all the networks
            Iterator<Profile> temp = allUsers.getValueIterator();
            while(temp.hasNext()){  //adds all the pre existing users
                cur.addVertex(temp.next());
            }
        }
        allUsers.add(profile.getId(), profile);

        if(allUserPanel != null){
            allUserPanel.addUI(new UI(profile.getPic(), profile.getId(), profile.getName(), allUserPanel.main));
        }

    }

    public Profile getProfile(String id){
        return allUsers.getValue(id);
    }

    public UndirectedGraph<Profile> getFriends(String id){
        return friendNetwork.getValue(id);
    }

    public void setAllUserPanel(UserFrame allUserPanel){
        this.allUserPanel = allUserPanel;
    }

    public void makeFriends(String user1, String user2)
    {
        if(allUsers.getValue(user1) == null || allUsers.getValue(user2) == null) return;
        friendNetwork.getValue(user1).addEdge(allUsers.getValue(user1), allUsers.getValue(user2));
        friendNetwork.getValue(user2).addEdge(allUsers.getValue(user1), allUsers.getValue(user2));
    }

    public void modifyUserName(String id, String name)
    {
        Profile temp = allUsers.getValue(id);
        if(temp != null) temp.setName(name);
    }

    public void modifyUserPic(String id, String pic){
        Profile temp = allUsers.getValue(id);
        if(temp != null) temp.setPic(pic);
    }

    public boolean validID(String id){
        Iterator<String> iter = allUsers.getKeyIterator();
        while (iter.hasNext()){
            if(id.equals(iter.next())) return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        String str = "\n";

        Iterator<Profile> iter = allUsers.getValueIterator();
        while(iter.hasNext()){
            Profile current = iter.next();
            str += "User : " + current.getName() + " Id : @" + current.getId() + "\n\tFriends : ";
             QueueInterface<Profile> friend = friendNetwork.getValue(current.getId()).getDepthFirstTraversal(current);
             friend.dequeue();  //skips self
             while(!friend.isEmpty()){
                 str += "\n\t\t@" + friend.dequeue().getId();
             }
             str += "\n\n";
        }

        return str;
    }
}
