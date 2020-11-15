package grupo2.proyectoTBD.services;

import grupo2.proyectoTBD.models.Emergency;
import grupo2.proyectoTBD.models.User;
import grupo2.proyectoTBD.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserService {

    private final UserRepository UserRepository;

    UserService(UserRepository UserRepository){
        this.UserRepository = UserRepository;
    }


    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        User user = UserRepository.getUser(id);
        return user;
    }
}
