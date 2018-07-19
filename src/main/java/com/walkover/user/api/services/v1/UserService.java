/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walkover.user.api.services.v1;

import com.walkover.user.api.dao.model.User;
import com.walkover.user.api.dao.repository.UserRepository;
import com.walkover.user.api.exception.AlreadyExistException;
import com.walkover.user.api.exception.InvalidRequestException;
import com.walkover.user.api.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author Akash Deep Gupta
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(rollbackFor = {Throwable.class})
    public User create(User user) throws AlreadyExistException, InvalidRequestException, ConstraintViolationException {
        if (doesEmailIdExist(user.getEmailId())) {
            throw new AlreadyExistException("User with given email id already exist.");
        }
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Transactional(rollbackFor = {Throwable.class})
    public User update(User user) throws NotFoundException {
        if (!doesEmailIdExist(user.getEmailId())) {
            throw new NotFoundException("User with given email id does not exist.");
        }

        User oldUser = userRepository.findByEmailId(user.getEmailId()).get();
        oldUser.setEmailId(user.getEmailId());
        oldUser.setName(user.getName());
        userRepository.save(oldUser);
        return oldUser;
    }

    @Transactional
    public void deleteUser(User user) throws NotFoundException {
        if (!doesEmailIdExist(user.getEmailId())) {
            throw new NotFoundException("User with given email id does not exist.");
        }
        userRepository.delete(user);
    }

    @Transactional
    public User findUser(String email) throws NotFoundException {
        if (!doesEmailIdExist(email)) {
            throw new NotFoundException("User with given email id does not exist.");
        }
        return userRepository.findByEmailId(email).get();
    }
    @Transactional
    public User findUserById(long id) throws NotFoundException {
     //   if (!doesEmailIdExist(email)) {
       //     throw new NotFoundException("User with given email id does not exist.");
       // }
        return userRepository.findById(id).get();
    }


    @Transactional
    public List<User> findAllUser() {
        return (List) userRepository.findAll();
    }

    public boolean doesEmailIdExist(String emailId) {
        return userRepository.doesEmailIdExist(emailId);
    }

}
