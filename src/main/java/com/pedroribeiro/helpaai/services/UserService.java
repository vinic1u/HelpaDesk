package com.pedroribeiro.helpaai.services;

import com.pedroribeiro.helpaai.dtos.user.UserRequestDTO;
import com.pedroribeiro.helpaai.dtos.user.UserResponseDTO;
import com.pedroribeiro.helpaai.entities.Sector;
import com.pedroribeiro.helpaai.entities.User;
import com.pedroribeiro.helpaai.exceptions.ResourceNotFoundException;
import com.pedroribeiro.helpaai.repositories.SectorRepository;
import com.pedroribeiro.helpaai.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SectorRepository sectorRepository;

    public List<UserResponseDTO> findAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponseDTO::new).toList();
    }

    public UserResponseDTO findUserById(Integer id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario de ID: " +id+" não encontrado!"));
        return new UserResponseDTO(user);
    }

    public UserResponseDTO saveUser(UserRequestDTO dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());

        Sector sector = sectorRepository.findById(dto.getSectorID())
                .orElseThrow(()-> new ResourceNotFoundException("Setor de ID: "  + dto.getSectorID()  + " não encontrado!"));
        user.setSector(sector);

        userRepository.save(user);
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
}
