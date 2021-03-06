package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.constant.RoleIdConstant;
import com.tunabel.perfumestorev1.constant.StatusRegisterUserEnum;
import com.tunabel.perfumestorev1.data.model.*;
import com.tunabel.perfumestorev1.data.repository.RoleRepository;
import com.tunabel.perfumestorev1.data.repository.UserRepository;
import com.tunabel.perfumestorev1.data.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getListAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public boolean updateUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int userId) {
        try {
            userRepository.delete(userId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public User findOne(int userId) {
        return userRepository.findOne(userId);
    }


    public StatusRegisterUserEnum registerNewUser(User user) {

        try {
            // check existed user
            if (findUserByUsername(user.getUsername()) != null) {
                return StatusRegisterUserEnum.Existed_Username;
            }

            if (findUserByEmail(user.getEmail()) != null) {
                return StatusRegisterUserEnum.Existed_Email;
            }

            // hash pass
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreatedDate(new Date());

            // save user
            userRepository.save(user);

            // insert new role
            UserRole userRole = new UserRole();
            userRole.setRoleId(RoleIdConstant.Role_User);
            userRole.setUserId(user.getId());

            userRoleRepository.save(userRole);

            return StatusRegisterUserEnum.Success;
        } catch (Exception ex) {
            ex.printStackTrace();
            return StatusRegisterUserEnum.Error_OnSystem;
        }
    }

    public List<Role> getListRole() {
        return StreamSupport
                .stream(roleRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public User findUserByEmail(String email) {
        return StreamSupport
                .stream(userRepository.findByEmail(email).spliterator(), false)
                .findFirst().orElse(null);
    }

    public User findUserByUsername(String username) {
        return StreamSupport
                .stream(userRepository.findByUsername(username).spliterator(), false)
                .findFirst().orElse(null);
    }

    public List<Role> getActiveListRole(int userId) {
        List<UserRole> listUserRoles = StreamSupport
                .stream(userRoleRepository.findRolesOfUser(userId).spliterator(), false).collect(Collectors.toList());

        return getListRole().stream().filter(role -> {
            return (listUserRoles.stream().filter(userRole -> userRole.getRoleId() == role.getId()).findFirst().orElse(null) != null);
        }).collect(Collectors.toList());
    }


    public Page<User> getUserListByNameContaining(Pageable pageable, String name) {
        return userRepository.getUserListByNameContaining(pageable, name);
    }

    public boolean switchStatus(int userId) {
        User user = findOne(userId);

        if (user != null) {
            int currStatus = user.getStatus();
            int newStatus = (currStatus == 0) ? 1 : 0;

            user.setCreatedDate(new Date());
            user.setStatus(newStatus);
            addNewUser(user);
            return true;
        }
        return false;
    }
}
