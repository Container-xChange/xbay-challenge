package com.xbaychallenge.user;

import com.xbaychallenge.user.repository.UserEntity;
import com.xbaychallenge.user.repository.UserEntityBuilder;
import com.xbaychallenge.user.repository.UserRepository;
import com.xbaychallenge.user.web.CreateUserRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserEntity getUser(UUID userId) {
        return repository.findById(userId)
                         .orElseThrow(NullPointerException::new);
    }

    public UserEntity createUser(CreateUserRequest request) {
        UserEntity userEntity = buildEntity(request);
        return repository.save(userEntity);
    }

    private UserEntity buildEntity(CreateUserRequest request) {
        return UserEntityBuilder.builder()
                                .firstname(request.getFirstName())
                                .surname(request.getLastName())
                                .email(request.getEmail())
                                .build();
    }
}
