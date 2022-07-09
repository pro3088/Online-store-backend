package com.cimspace.stockup_user.user_address;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/userAddress", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserAddressController {

    private final UserAddressService userAddressService;

    public UserAddressController(final UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }

    @GetMapping
    public ResponseEntity<List<UserAddressDTO>> getAllUserAddresss() {
        return ResponseEntity.ok(userAddressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAddressDTO> getUserAddress(@PathVariable final String id) {
        return ResponseEntity.ok(userAddressService.get(id));
    }

    @PostMapping
    public ResponseEntity<String> createUserAddress(
            @RequestBody @Valid final UserAddressDTO userAddressDTO) {
        return new ResponseEntity<>(userAddressService.create(userAddressDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserAddress(@PathVariable final String id,
            @RequestBody @Valid final UserAddressDTO userAddressDTO) {
        userAddressService.update(id, userAddressDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAddress(@PathVariable final String id) {
        userAddressService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
