package com.pedroribeiro.helpaai.services;

import com.pedroribeiro.helpaai.dtos.auth.AuthencationLoginDTO;
import com.pedroribeiro.helpaai.dtos.auth.AuthencationRegisterDTO;
import com.pedroribeiro.helpaai.dtos.auth.LoginResponseDTO;
import com.pedroribeiro.helpaai.dtos.user.UserRequestDTO;
import com.pedroribeiro.helpaai.dtos.user.UserResponseDTO;
import com.pedroribeiro.helpaai.entities.Sector;
import com.pedroribeiro.helpaai.entities.User;
import com.pedroribeiro.helpaai.exceptions.AlreadyRegisteredException;
import com.pedroribeiro.helpaai.exceptions.ResourceNotFoundException;
import com.pedroribeiro.helpaai.infra.TokenService;
import com.pedroribeiro.helpaai.repositories.SectorRepository;
import com.pedroribeiro.helpaai.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class    UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public List<UserResponseDTO> findAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponseDTO::new).toList();
    }

    public UserResponseDTO findUserById(Integer id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario de ID: " +id+" não encontrado!"));
        return new UserResponseDTO(user);
    }

    public UserResponseDTO updateUser(Integer id,UserRequestDTO dto){
        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario de ID: " +id+" não encontrado!"));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());

        Sector sector = sectorRepository.findById(dto.getSectorID())
                .orElseThrow(()-> new ResourceNotFoundException("Setor de ID: "  + dto.getSectorID()  + " não encontrado!"));
        user.setSector(sector);
        userRepository.save(user);
        return new UserResponseDTO(user);
    }

    public String registerUser(AuthencationRegisterDTO dto){
        if(userRepository.findByEmail(dto.getEmail()) != null) throw new AlreadyRegisteredException("User already Registered");

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(encryptedPassword);
        user.setRole(dto.getRole());
        user.setName(dto.getName());
        user.setPhone(dto.getPhone());
        this.userRepository.save(user);
        return "User registered successfully";
    }

    public LoginResponseDTO loginUser(AuthencationLoginDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }
}
