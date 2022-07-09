package com.cimspace.stockup_user.user_payment;

import com.cimspace.stockup_user.UUIDgenerator.UUIDgenerator;
import com.cimspace.stockup_user.user.User;
import com.cimspace.stockup_user.user.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserPaymentService {

    private final UserPaymentRepository userPaymentRepository;
    private final UserRepository userRepository;

    private UUIDgenerator uuiDgenerator= new UUIDgenerator();

    private String UUIDstring = uuiDgenerator.uuid().toString();

    public UserPaymentService(final UserPaymentRepository userPaymentRepository,
            final UserRepository userRepository) {
        this.userPaymentRepository = userPaymentRepository;
        this.userRepository = userRepository;
    }

    public List<UserPaymentDTO> findAll() {
        return userPaymentRepository.findAll()
                .stream()
                .map(userPayment -> mapToDTO(userPayment, new UserPaymentDTO()))
                .collect(Collectors.toList());
    }

    public UserPaymentDTO get(final String id) {
        return userPaymentRepository.findById(id)
                .map(userPayment -> mapToDTO(userPayment, new UserPaymentDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(final UserPaymentDTO userPaymentDTO) {
        final UserPayment userPayment = new UserPayment();
        mapToEntity(userPaymentDTO, userPayment);
        return userPaymentRepository.save(userPayment).getId();
    }

    public void update(final String id, final UserPaymentDTO userPaymentDTO) {
        final UserPayment userPayment = userPaymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(userPaymentDTO, userPayment);
        userPaymentRepository.save(userPayment);
    }

    public void delete(final String id) {
        userPaymentRepository.deleteById(id);
    }

    private UserPaymentDTO mapToDTO(final UserPayment userPayment,
            final UserPaymentDTO userPaymentDTO) {
        userPaymentDTO.setId(userPayment.getId());
        userPaymentDTO.setPaymentType(userPayment.getPaymentType());
        userPaymentDTO.setAccountno(userPayment.getAccountno());
        userPaymentDTO.setUserId(userPayment.getUserId() == null ? null : userPayment.getUserId().getId());
        return userPaymentDTO;
    }

    private UserPayment mapToEntity(final UserPaymentDTO userPaymentDTO,
            final UserPayment userPayment) {
        userPayment.setId(UUIDstring);
        userPayment.setPaymentType(userPaymentDTO.getPaymentType());
        userPayment.setAccountno(userPaymentDTO.getAccountno());
        if (userPaymentDTO.getUserId() != null && (userPayment.getUserId() == null || !userPayment.getUserId().getId().equals(userPaymentDTO.getUserId()))) {
            final User userId = userRepository.findById(userPaymentDTO.getUserId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "userId not found"));
            userPayment.setUserId(userId);
        }
        return userPayment;
    }

}
