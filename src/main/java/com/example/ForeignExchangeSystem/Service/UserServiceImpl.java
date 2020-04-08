package com.example.ForeignExchangeSystem.Service;

import com.example.ForeignExchangeSystem.model.User;
import com.example.ForeignExchangeSystem.model.Role;
import com.example.ForeignExchangeSystem.repository.RoleRepository;
import com.example.ForeignExchangeSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
@Transactional //jeżeli pierwszy zapis sie nie powiedzie to a drugi sie nie powiedzie to transakcja zostanie wycofana
public class UserServiceImpl implements UserService {


    @Qualifier("userRepository") //przydaje sie gdy spring nie wie ktorego beana użyć bo maja podobne nazwy
    @Autowired
    private UserRepository userRepository;
    @Qualifier("roleRepository")
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override //wrzucam nam te zmienna aemail do zapytania torzone przez repo :)
    public User findUserByMail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode((user.getPassword()))); //szyfruje haselko
        user.setActive(1); //aktywuje użytkonika czyli moze sie logowac do systemu

        Role role = roleRepository.findByRole("ROLE_ADMIN"); //odczytanie id roli
        user.setRoles(new HashSet<Role>(Arrays.asList(role)));
        userRepository.save(user); //zapisujemy usera do repo
    }
}
