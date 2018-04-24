package com.cayhualla.practicacalificado_6.repositories;

import com.cayhualla.practicacalificado_6.models.User;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Alumno on 20/04/2018.
 */

public class UserRepository {

    public static List<User> list(){
        List<User> users = SugarRecord.listAll(User.class);
        return users;
    }

   /* public static User read(Long id,String username, String password){
        User user = SugarRecord.findById(User.class, id);
        if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)){
            return user;
        }
        return user;

    }*/

    public static User login(String username, String password){
        for (User user :  list()){
            if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public static User getUser(String username){
        for (User user : list()){
            if(user.getUsername().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    }


    public static void create(String email,String fullname , String username , String password){
        User user = new User(email,fullname, username, password);
        SugarRecord.save(user);
    }
    public static void update(String fullname, String email, String password, Long id){
        User user = SugarRecord.findById(User.class, id);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        SugarRecord.save(user);
    }
    public static void delete(Long id){
        User user = SugarRecord.findById(User.class, id);
        SugarRecord.delete(user);
    }
}
