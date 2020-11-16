package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Emergency;
import grupo2.proyectoTBD.models.User;
import grupo2.proyectoTBD.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserService {

    private final UserRepository UserRepository;
    private final Gson gson;

    UserService(UserRepository UserRepository){
        this.UserRepository = UserRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }


    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id){
        User user = UserRepository.getUser(id);
        return gson.toJson(user);
    }

    @GetMapping("/")
    public String getUsers(){
        List<User> users = UserRepository.getUsers();
        return gson.toJson(users);
    }
    @PostMapping("/")
    public void newUser(@RequestBody String request){
        User user = gson.fromJson(request,User.class);
        UserRepository.newUser(user);
    }

}
