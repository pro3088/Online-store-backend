package com.cimspace.stockup_user.user_address;

import com.cimspace.stockup_user.UUIDgenerator.UUIDgenerator;
import com.cimspace.stockup_user.user.User;
import com.cimspace.stockup_user.user.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserAddressService {

    private final UserAddressRepository userAddressRepository;
    private final UserRepository userRepository;
    private UUIDgenerator uuiDgenerator= new UUIDgenerator();

    private String UUIDstring = uuiDgenerator.uuid().toString();

    public UserAddressService(final UserAddressRepository userAddressRepository,
            final UserRepository userRepository) {
        this.userAddressRepository = userAddressRepository;
        this.userRepository = userRepository;
    }

    public List<UserAddressDTO> findAll() {
        return userAddressRepository.findAll()
                .stream()
                .map(userAddress -> mapToDTO(userAddress, new UserAddressDTO()))
                .collect(Collectors.toList());
    }

    public UserAddressDTO get(final String id) {
        return userAddressRepository.findById(id)
                .map(userAddress -> mapToDTO(userAddress, new UserAddressDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(final UserAddressDTO userAddressDTO) {
        final UserAddress userAddress = new UserAddress();
        mapToEntity(userAddressDTO, userAddress);
        return userAddressRepository.save(userAddress).getId();
    }

    public void update(final String id, final UserAddressDTO userAddressDTO) {
        final UserAddress userAddress = userAddressRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(userAddressDTO, userAddress);
        userAddressRepository.save(userAddress);
    }

    public void delete(final String id) {
        userAddressRepository.deleteById(id);
    }

    private UserAddressDTO mapToDTO(final UserAddress userAddress,
            final UserAddressDTO userAddressDTO) {
        userAddressDTO.setId(userAddress.getId());
        userAddressDTO.setAddressline1(userAddress.getAddressline1());
        userAddressDTO.setAddressline2(userAddress.getAddressline2());
        userAddressDTO.setTelephone(userAddress.getTelephone());
        userAddressDTO.setUserId(userAddress.getUserId() == null ? null : userAddress.getUserId().getId());
        return userAddressDTO;
    }

    private UserAddress mapToEntity(final UserAddressDTO userAddressDTO,
            final UserAddress userAddress) {
        userAddress.setId(UUIDstring);
        userAddress.setAddressline1(userAddressDTO.getAddressline1());
        userAddress.setAddressline2(userAddressDTO.getAddressline2());
        userAddress.setTelephone(userAddressDTO.getTelephone());
        if (userAddressDTO.getUserId() != null && (userAddress.getUserId() == null || !userAddress.getUserId().getId().equals(userAddressDTO.getUserId()))) {
            final User userId = userRepository.findById(userAddressDTO.getUserId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "userId not found"));
            userAddress.setUserId(userId);
        }
        return userAddress;
    }

}
