package com.example.taipeizoo.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "PageNameTitle")
    public String pageName;

    @ColumnInfo(name = "click_position")
    public int clickPosition;

    @ColumnInfo(name = "EnglishName")
    public String EnglishName;

    @ColumnInfo(name = "ChineseName")
    public String ChineseName;


    @Ignore
    public User(String pPageName, int position, String pEnglishName, String pChineseName) {
        pageName = pPageName;
        clickPosition = position;
        EnglishName = pEnglishName;
        ChineseName = pChineseName;
    }

    public User() {

    }
}
