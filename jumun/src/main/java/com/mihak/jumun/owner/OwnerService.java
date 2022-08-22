package com.mihak.jumun.owner;

import com.mihak.jumun.entity.Owner;
import com.mihak.jumun.owner.dto.form.SignupFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    public Owner signup(SignupFormDto signupFormDto) {
        Owner owner = Owner.builder()
                .loginId(signupFormDto.getLoginId())
                .password(passwordEncoder.encode(signupFormDto.getPassword1()))
                .name(signupFormDto.getName())
                .phoneNumber(signupFormDto.getPhoneNumber())
                .signupAt(LocalDateTime.now())
                .agree(signupFormDto.getAgree().equals("true"))
                .build();

        return ownerRepository.save(owner);
    }
}
