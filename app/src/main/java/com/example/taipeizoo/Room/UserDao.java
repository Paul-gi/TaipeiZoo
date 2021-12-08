package com.example.taipeizoo.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * from user")
    List<User> getUserList();

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<User> loadAllByIds(int[] userIds);
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

    @Query("SELECT * FROM user WHERE PageNameTitle in(:type) AND click_position in(:positionID) AND EnglishName in(:EnglishName) AND ChineseName in(:ChineseName) ")
    User findByName(String type, int positionID, String EnglishName, String ChineseName);



    @Insert
    void insertUser(User users);
//    @Delete
//    void delete(User user);
}
