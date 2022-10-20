package com.mihak.jumun.owner.service;

import com.mihak.jumun.owner.entity.Owner;
import com.mihak.jumun.owner.exception.OwnerNotFoundException;
import com.mihak.jumun.owner.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerSecurityService implements UserDetailsService {

    private final OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Owner> loginOwner = ownerRepository.findByLoginId(username);

        if (loginOwner.isEmpty()) {
            throw new OwnerNotFoundException("관리자를 찾을 수 없습니다.");
        }

        Owner owner = loginOwner.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));

        return new User(owner.getLoginId(), owner.getPassword(), authorities);
    }
}
