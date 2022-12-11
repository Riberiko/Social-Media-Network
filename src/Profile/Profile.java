package Profile;

import ADTPackage.DictionaryInterface;
import ADTPackage.UnsortedLinkedDictionary;

public class Profile
{
    private String name;
    private String pic;
    private boolean isOnline;
    private String id;
    private DictionaryInterface<String, Profile> friends;

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
    }

    public String getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }

    public DictionaryInterface<String, Profile> getFriends()
    {
        return friends;
    }

    public String getPic()
    {
        return pic;
    }
}
