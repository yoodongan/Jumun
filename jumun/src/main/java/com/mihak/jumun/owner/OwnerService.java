package com.mihak.jumun.owner;

import com.mihak.jumun.entity.Owner;
import com.mihak.jumun.exception.AdminNotFoundException;
import com.mihak.jumun.owner.dto.SignupFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    public Owner save(SignupFormDto signupFormDto) {
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

    public Owner findById(String ownerId) {
        Optional<Owner> findOwner = ownerRepository.findByLoginId(ownerId);

        if (findOwner.isEmpty()) {
            throw new AdminNotFoundException("관리자를 찾을 수 없습니다.");
        }

        return findOwner.get();
    }
}
