package com.kylecorry.bb1102.trash;

import java.util.Arrays;
import java.util.List;

public class TrashRepo {

    public List<String> getTrashTypes(){
        return Arrays.asList("aluminum","aerosol" , "carton","glass","tin can", "cardboard", "bottle", "can", "plastic","tray", "box", "cup", "container", "recycling", "beer", "liquor", "plate", "dish", "garbage", "trash",
                "plate", "utensil", "napkin" ,"plastic bag","foam","light bulb", "straw","cup","cap","diaper" );
    }

    public boolean isTrash(String type){
        return getTrashTypes().contains(type.toLowerCase());
    }

}
