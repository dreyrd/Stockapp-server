package br.com.ifsp.StockApp.controller;

import br.com.ifsp.StockApp.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<UserDataResponse> createUser(@RequestBody UserDataCreation userDataCreation,
                                                       UriComponentsBuilder uriComponentsBuilder) {

        var newUser = new User(userDataCreation);

        newUser.setPassword(passwordEncoder.encode(userDataCreation.password()));

        repository.save(newUser);

        var uri = uriComponentsBuilder.path("/user/{id}")
                .buildAndExpand(newUser.getUserId())
                .toUri();

        return ResponseEntity.created(uri).body(new UserDataResponse(newUser));
    }

    @GetMapping
    public ResponseEntity<Page<UserDataResponse>> listUsers(Pageable pageable) {
        var page = repository.findAllByEnableTrue(pageable)
                .map(UserDataResponse::new);
        return ResponseEntity.ok(page);
    }
}
