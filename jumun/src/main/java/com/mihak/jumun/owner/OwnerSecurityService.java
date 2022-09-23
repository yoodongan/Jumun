package com.mihak.jumun.owner;

import com.mihak.jumun.entity.Owner;
import com.mihak.jumun.exception.AdminNotFoundException;
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

        Optional<Owner> loginOwner = ownerRepository.findByloginId(username);

        if (loginOwner.isEmpty()) {
            throw new AdminNotFoundException("관리자를 찾을 수 없습니다.");
        }

        Owner owner = loginOwner.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));

        return new User(owner.getLoginId(), owner.getPassword(), authorities);
    }
}
