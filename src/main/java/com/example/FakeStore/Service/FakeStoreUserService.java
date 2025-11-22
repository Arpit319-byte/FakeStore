package com.example.FakeStore.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.FakeStore.DTO.UserDTO;
import com.example.FakeStore.Gateway.IUserGateway;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FakeStoreUserService implements IUserService {

    private final IUserGateway iUserGateway;

    public FakeStoreUserService(IUserGateway _iUserGateway) {
        log.info("Adding the IUserGateway dependency for the FakeStoreUserService");
        this.iUserGateway = _iUserGateway;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Getting all users from the IUserGateway");
        return iUserGateway.getAllUsers();
    }

    @Override
    public UserDTO getUserById(Long id) {
        log.info("Getting user by id: {} from the IUserGateway", id);
        return iUserGateway.getUserById(id);
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        log.info("Adding user: {} to the IUserGateway", userDTO);
        return iUserGateway.addUser(userDTO);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        log.info("Updating user with id: {} in the IUserGateway", id);
        return iUserGateway.updateUser(id, userDTO);
    }

    @Override
    public Boolean deleteUserById(Long id) {
        log.info("Deleting user with id: {} from the IUserGateway", id);
        return iUserGateway.deleteUserById(id);
    }
}

