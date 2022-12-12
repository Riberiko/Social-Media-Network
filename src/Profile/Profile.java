package Profile;

import ADTPackage.DictionaryInterface;
import ADTPackage.UnsortedLinkedDictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Profile
{
    private String name;
    private String id;
    private String pic;
    private boolean isOnline;
    private DictionaryInterface<String, Profile> friends;
    private List<Profile> friendsList;

    public Profile(String name, String id)
    {
        this(name, id, false);
    }

    public Profile(String name, String id, boolean isOnline)
    {
        this(name, id, isOnline, new UnsortedLinkedDictionary<String, Profile>());
    }

    public Profile(String name, String id, boolean isOnline, UnsortedLinkedDictionary<String, Profile> friends)
    {
        this.name = name;
        this.id = id;
        this.isOnline = isOnline;
        this.friends = friends;
        friendsList = new ArrayList<Profile>();

        Iterator<Profile> iter = friends.getValueIterator();
        while(iter.hasNext()){
            friendsList.add(iter.next());
        }
    }

    public String getName()
    {
        return name;
    }

    public String getId(){
        return id;
    }

    public List<Profile> getFriends()
    {
        return friendsList;
    }

    public List<String> getFriendsId(){
        List<String> temp = new ArrayList<>();
        for(Profile p : getFriends()){
            temp.add(p.getId());
        }
        return temp;
    }

    public String getPic()
    {
        return pic;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public void addFriend(String id, Profile profile){
        friends.add(id, profile);
        friendsList.add(profile);
    }

    public void removeFriend(String id, Profile profile){
        friends.remove(id);
        friendsList.remove(profile);
    }

    @Override
    public String toString(){
        String str = "";
        str += "Name : "+name +"\n\t@"+ id + "\n\tisOnline : " + isOnline + "\n\tFriends : " +getFriendsId()+"\n";
        return str;
    }
}
