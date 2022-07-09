package com.cimspace.stockup_user.user;

import java.util.List;
import java.util.stream.Collectors;

import com.cimspace.stockup_user.UUIDgenerator.UUIDgenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService {

    private final UserRepository userRepository;
    private UUIDgenerator uuiDgenerator = new UUIDgenerator();

    private String UUIDstring = uuiDgenerator.uuid().toString();


    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    public UserDTO get(final String id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<UserDTO> getUsername(final String username){
       return userRepository.username(username).stream().map(user -> mapToDTO(user, new UserDTO())).collect(Collectors.toList());
    }

    public List<UserDTO> getEmail(final String email){
        return userRepository.email(email).stream().map(user -> mapToDTO(user, new UserDTO())).collect(Collectors.toList());
    }

    public String create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void update(final String id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final String id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setTelephone(user.getTelephone());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setId(UUIDstring);
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setTelephone(userDTO.getTelephone());
        user.setEmail(userDTO.getEmail());
        return user;
    }

}
