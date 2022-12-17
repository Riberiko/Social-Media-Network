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

    public Profile(String name, String id)
    {
        this(name, id, false);
    }

    public Profile(String pic, String id, String name){
        this(name, id);
        this.pic = pic;
    }

    public Profile(String name, String id, boolean isOnline)
    {
        this.name = name;
        this.id = id;
        this.isOnline = isOnline;
    }

    public String getName()
    {
        return name;
    }

    public String getId(){
        return id;
    }

    public String getPic()
    {
        return pic;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPic(String pic){this.pic = pic;}

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
