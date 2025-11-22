package com.example.FakeStore.Service;

import java.util.List;
import com.example.FakeStore.DTO.UserDTO;

public interface IUserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO addUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    Boolean deleteUserById(Long id);
}

