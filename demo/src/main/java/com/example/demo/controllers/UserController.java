package com.example.demo.controllers;

import com.example.demo.databaseAccess.UserDao;
import com.example.demo.entity.Professor;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.factory.UserFactory;
import com.example.demo.factory.UserTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * This class controls the flow of information regarding the users.
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao){ this.userDao=userDao;
    }

    /**
     * Inserts a new User into the database.
     * @param  type the type of User to be created : "s" for Student , "p" for Professor and "a" for admin
     * @param  name the name of the User
     * @param  email the email of the User
     * @param  phoneNumber the phoneNumber of the User
     * @param  resgistrationNumber the registrationNumber of the User
     * @param  identificationNumber the identificationNumber of the User
     * @param  department the depratemnt of the User
     * @return      a String for success of failure
     * @see         User
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public boolean createUser(@RequestParam(name = "type",defaultValue = "s") String type,
                             @RequestParam(name = "first_name") String firstName,
                              @RequestParam(name = "last_name") String lastName,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "phone") String phoneNumber,
                             @RequestParam(name = "rn",defaultValue = "-") String resgistrationNumber,
                             @RequestParam(name = "in",defaultValue = "-") String identificationNumber,
                             @RequestParam(name = "dep",defaultValue = "-") String department) {
        User user = null;
        try {

                user = UserFactory.buildUser(UserTypes.getType(type));
                if(user == null)
                    return false;

            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);

            if(user instanceof Student){
                ((Student) user).setIdentificationNumber(identificationNumber);
                ((Student) user).setRegistrationNumber(resgistrationNumber);
            }
            else if(user instanceof Professor){
                ((Professor) user).setDepartment(department);
            }

            userDao.save(user);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param id The id of the user.
     * @return An User object.
     */
    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ResponseBody
    public User findUser(@RequestParam(name = "id") String id){
        try{
            Long userId = Long.parseLong(id);
            Optional<User> foundUser = userDao.findById(userId);
            return foundUser.get();
        }catch(Exception ex){
            return null;
        }
    }

    /**
     * Set only the parameters to be updated.
     * @param id The id of the user.
     * @param firstName The new first name.
     * @param lastName The new last name.
     * @param email The new email.
     * @param phoneNumber The new phone number.
     * @param resgistrationNumber The new registration number.
     * @param identificationNumber The new identification number.
     * @param department The new department.
     * @return The new updated User object.
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    @ResponseBody
    public User updateUser(@RequestParam(name = "id") String id,
                             @RequestParam(name = "first_name",defaultValue = "-") String firstName,
                             @RequestParam(name = "last_name",defaultValue = "-") String lastName,
                             @RequestParam(name = "email",defaultValue = "-") String email,
                             @RequestParam(name = "phone",defaultValue = "-") String phoneNumber,
                             @RequestParam(name = "rn",defaultValue = "-") String resgistrationNumber,
                             @RequestParam(name = "in",defaultValue = "-") String identificationNumber,
                             @RequestParam(name = "dep",defaultValue = "-") String department){
        try{
            Long userId = Long.parseLong(id);
            Optional<User> foundUser = userDao.findById(userId);
            User user = foundUser.get();
            if(!firstName.equals("-"))
                user.setFirstName(firstName);
            if(!lastName.equals("-"))
                user.setLastName(lastName);
            if(!email.equals("-"))
                user.setEmail(email);
            if(!phoneNumber.equals("-"))
                user.setPhoneNumber(phoneNumber);
            if(!resgistrationNumber.equals("-"))
                ((Student)user).setRegistrationNumber(resgistrationNumber);
            if(!identificationNumber.equals("-"))
                ((Student)user).setIdentificationNumber(identificationNumber);
            if(!department.equals("-"))
                ((Professor)user).setDepartment(department);
            userDao.save(user);
            return user;
        }catch(Exception ex){
            return null;
        }
    }

    /**
     *
     * @param id The id of the user to be deleted.
     * @return True if deleted, False otherwise.
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteUser(@RequestParam(name = "id") String id){
        try{
            Long userId = Long.parseLong(id);
            userDao.deleteById(userId);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    /**
     *
     * @param email The email of the user.
     * @return The user with the coresponding email.
     */
    @RequestMapping(value = "/findUserByEmail", method = RequestMethod.GET)
    @ResponseBody
    public User findUserByEmail(@RequestParam(name = "email") String email){
        try{
            return userDao.findByEmail(email);
        }catch(Exception ex){
            return null;
        }
    }

    /**
     *
     * @param email The email of the user to be deleted.
     * @return True if deleted, False otherwise.
     */
    @RequestMapping(value = "/deleteUserByEmail", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteUserByEmail(@RequestParam(name = "email") String email){
        try{
            User user = userDao.findByEmail(email);
            userDao.deleteById(user.getId());
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    /**
     * Set only the parameters to be updated.
     * @param oldEmail The current email of the user to be updated.
     * @param firstName The new first name.
     * @param lastName The new last name.
     * @param email The new email.
     * @param phoneNumber The new phone number.
     * @param resgistrationNumber The new registration number.
     * @param identificationNumber The new identification number.
     * @param department The new department.
     * @return The new updated User object.
     */
    @RequestMapping(value = "/updateUserByEmail", method = RequestMethod.PUT)
    @ResponseBody
    public User updateUserByEmail(@RequestParam(name = "oldEmail") String oldEmail,
                                  @RequestParam(name = "first_name",defaultValue = "-") String firstName,
                                  @RequestParam(name = "last_name",defaultValue = "-") String lastName,
                                     @RequestParam(name = "email",defaultValue = "-") String email,
                                     @RequestParam(name = "phone",defaultValue = "-") String phoneNumber,
                                     @RequestParam(name = "rn",defaultValue = "-") String resgistrationNumber,
                                     @RequestParam(name = "in",defaultValue = "-") String identificationNumber,
                                     @RequestParam(name = "dep",defaultValue = "-") String department){
        User user = null;
        try{
            user = userDao.findByEmail(oldEmail);
            if(!firstName.equals("-"))
                user.setFirstName(firstName);
            if(!lastName.equals("-"))
                user.setLastName(lastName);
            if(!email.equals("-"))
                user.setEmail(email);
            if(!phoneNumber.equals("-"))
                user.setPhoneNumber(phoneNumber);
            if(!resgistrationNumber.equals("-"))
                ((Student)user).setRegistrationNumber(resgistrationNumber);
            if(!identificationNumber.equals("-"))
                ((Student)user).setIdentificationNumber(identificationNumber);
            if(!department.equals("-"))
                ((Professor)user).setDepartment(department);
            userDao.save(user);
            return user;
        }catch(Exception ex){
            return null;
        }
    }

}
