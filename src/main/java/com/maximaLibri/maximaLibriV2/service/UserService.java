package com.maximaLibri.maximaLibriV2.service;

import com.maximaLibri.maximaLibriV2.dto.IBookReview;
import com.maximaLibri.maximaLibriV2.dto.UserRegistrationDto;
import com.maximaLibri.maximaLibriV2.model.*;
import com.maximaLibri.maximaLibriV2.repository.BookRatingRepository;
import com.maximaLibri.maximaLibriV2.repository.ReviewRepository;
import com.maximaLibri.maximaLibriV2.repository.RoleRepository;
import com.maximaLibri.maximaLibriV2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BookRatingRepository bookRatingRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ReviewRepository reviewRepository;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        user.setRoles(Collections.singletonList(roleRepository.findByName(RoleName.ROLE_USER)));
        return userRepository.save(user);
    }

    /** extrage si salveaza userul din formatul datelor din register */
    public User save(UserRegistrationDto registration){
        User user = new User();
        user.setUsername(registration.getUsername());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByName(RoleName.ROLE_USER)));
        return userRepository.save(user);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    /** return user with given email; method necesary for spring security */
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    /** transforms the role entity into the collection of strings; necesary for spring security */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toList());
    }

    /** intoarce istoricul voturilor unui user identificat prin id */
    public List<BookRating> getHistory(Long userId) {
        return bookRatingRepository.findRatingsByUserId(userId);
    }

    /** inregistreaza un vot in baza de date */
    public void rateBook(String isbn, Long userId, Integer rating) {
        BookRating bookRating = new BookRating();
        BookRatingId bookRatingId = new BookRatingId();
        bookRatingId.setIsbn(isbn);
        bookRatingId.setUserId(userId);
        bookRating.setBookRatingId(bookRatingId);
        bookRating.setBookRating(rating);
        bookRatingRepository.save(bookRating);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void disableUserById(Long id) {
        User user = findById(id);
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Transactional
    public void enableUserById(Long id) {
        User user = findById(id);
        user.setEnabled(true);
        userRepository.save(user);
    }

    /** intoarce istoricul review-urilor unui user identificat prin id */
    public List<IBookReview> getReviewsByUser(Long id) {
        return reviewRepository.findReviewsByUser(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
