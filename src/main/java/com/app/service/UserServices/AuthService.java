package com.app.service.UserServices;

import com.app.entity.User;

import com.app.entity.cars.Car;
import com.app.exception.EmailExist;
import com.app.exception.UserExist;
import com.app.payload.Athintication.LoginDto;
import com.app.payload.Athintication.Userdto;
import com.app.payload.CarsDTO.CarDto;
import com.app.repository.AthinticationRepository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final ModelMapper modelMapper;

    public AuthService(UserRepository userRepository, JWTService jwtService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
    }

    public Userdto adduser(Userdto userdto) {

        User user = convertDtoToEntity(userdto);
        // Check if username already exists
        Optional<User> existingUserByUsername = userRepository.findByUsername(user.getUsername());
        if (existingUserByUsername.isPresent()) {
            throw new UserExist("User already exists with username: " + user.getUsername());
        }

        // Check if email already exists
        Optional<User> existingUserByEmail = userRepository.findByEmailId(user.getEmailId());
        if (existingUserByEmail.isPresent()) {
            throw new EmailExist("Email already exists: " + user.getEmailId());
        }
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_USER");

        User savedUser = userRepository.save(user);


        return convertEntityToDto(savedUser);
    }

    public Userdto addContentManager(Userdto userdto) {

        User user = convertDtoToEntity(userdto);
        // Check if username already exists
        Optional<User> existingUserByUsername = userRepository.findByUsername(user.getUsername());
        if (existingUserByUsername.isPresent()) {
            throw new UserExist("User already exists with username: " + user.getUsername());
        }

        // Check if email already exists
        Optional<User> existingUserByEmail = userRepository.findByEmailId(user.getEmailId());
        if (existingUserByEmail.isPresent()) {
            throw new EmailExist("Email already exists: " + user.getEmailId());
        }
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_CONTENTMANAGER");

        User savedContentManager = userRepository.save(user);


        return  convertEntityToDto(savedContentManager);
    }

    public Userdto addBlogManager(Userdto userdto) {

        User user = convertDtoToEntity(userdto);

        // Check if username already exists
        Optional<User> existingUserByUsername = userRepository.findByUsername(user.getUsername());
        if (existingUserByUsername.isPresent()) {
            throw new UserExist("User already exists with username: " + user.getUsername());
        }

        // Check if email already exists
        Optional<User> existingUserByEmail = userRepository.findByEmailId(user.getEmailId());
        if (existingUserByEmail.isPresent()) {
            throw new EmailExist("Email already exists: " + user.getEmailId());
        }
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_BLOG");

        User savedBlogManager = userRepository.save(user);


        return  convertEntityToDto(savedBlogManager);
    }


    User convertDtoToEntity(Userdto userdto){

        User map = modelMapper.map(userdto, User.class);
        System.out.println(map);
        return map;
    }

    Userdto convertEntityToDto(User user){

        return modelMapper.map(user, Userdto.class);
    }

//    public Userdto convertToDto(User user) {
//        Userdto userDto = new Userdto();
//        userDto.setName(user.getName());
//        userDto.setPassword(user.getPassword());
//        userDto.setUsername(user.getUsername());
//        userDto.setEmailId(user.getEmailId());
//        return userDto;
//    }
    public String verfyLogin(LoginDto dto){
        Optional<User> opUser = userRepository.findByUsername(dto.getUsername());
        if (opUser.isPresent()){
            User user = opUser.get();
            if( BCrypt.checkpw(dto.getPassword(),user.getPassword())){
                 return jwtService.generateToken(user.getUsername());
            }
        }
        return null;
    }

    public List<Userdto> getAll(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort= sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo,pageSize, sort);
        Page<User> allList =userRepository.findAll(page);
        List<User> content = allList.getContent();
        return content.stream().map(user->modelMapper.map(user, Userdto.class)).collect(Collectors.toList());
    }

    public void deleteByCar(Long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
    }

    public void updateUser(Long id, Userdto userdto) {
        Optional<User> usersId = userRepository.findById(id);
        User user = usersId.get();
        user.setName(userdto.getName());
        user.setEmailId(userdto.getEmailId());
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setUsername(userdto.getUsername());
        user.setMobile(userdto.getMobile());
        userRepository.save(user);
    }
}
