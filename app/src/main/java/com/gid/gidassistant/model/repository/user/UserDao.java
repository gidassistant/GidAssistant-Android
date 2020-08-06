package com.gid.gidassistant.model.repository.user;

import androidx.room.*;

import com.gid.gidassistant.model.entities.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE id=:userId")
    User getUser(int userId);

    @Insert
    void insert(User user);
    @Update
    void update(User user);
    @Delete
    void delete(User user);

}
