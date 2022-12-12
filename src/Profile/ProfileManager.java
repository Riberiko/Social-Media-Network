package Profile;

import ADTPackage.DictionaryInterface;
import ADTPackage.QueueInterface;
import ADTPackage.UnsortedLinkedDictionary;
import GraphPackage.DirectedGraph;
import GraphPackage.UndirectedGraph;

import java.util.Iterator;

public class ProfileManager {


    private UndirectedGraph<Profile> friendNetwork;
    private DirectedGraph<Profile> followerNetwork;
    private DictionaryInterface<String, Profile> allUsers;

    public ProfileManager()
    {
        friendNetwork = new UndirectedGraph<Profile>();
        allUsers = new UnsortedLinkedDictionary<String, Profile>();
        followerNetwork = new DirectedGraph<Profile>();
    }

    public void addUser(Profile profile)
    {
        friendNetwork.addVertex(profile);
        followerNetwork.addVertex(profile);
        allUsers.add(profile.getId(), profile);
    }

    public void makeFriends(String user1, String user2)
    {
        friendNetwork.addEdge(allUsers.getValue(user1), allUsers.getValue(user2));
    }

    public void follow(String user1, String user2)
    {
        followerNetwork.addEdge(allUsers.getValue(user1), allUsers.getValue(user2));
    }

    public void modifyUserName(String id, String name)
    {
        allUsers.getValue(id).setName(name);
    }

    public void modifyUserStatus(String id, boolean status)
    {
        allUsers.getValue(id).setOnline(status);
    }

    public void unFollow(String yourID, String userID)
    {
    }

    @Override
    public String toString()
    {
        String str = "\n";

        Iterator<Profile> iter = allUsers.getValueIterator();
        while(iter.hasNext()){
            Profile current = iter.next();
            str += "User : " + current.getName() + " Id : @" + current.getId() + "\n\tFriends : ";
             QueueInterface<Profile> friend = friendNetwork.getDepthFirstTraversal(current);
             friend.dequeue();  //skips self
             while(!friend.isEmpty()){
                 str += "\n\t\t" + friend.dequeue().getId();
             }
             QueueInterface<Profile> follow =  followerNetwork.getDepthFirstTraversal(current);
             str += "\n\tFollowing : ";
             follow.dequeue();  //skips self
             while(!follow.isEmpty()){
                 str += "\n\t\t" + follow.dequeue().getId();
             }
             str += "\n\n";
        }

        return str;
    }
}
