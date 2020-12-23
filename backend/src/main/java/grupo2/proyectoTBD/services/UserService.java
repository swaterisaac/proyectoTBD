package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Emergency;
import grupo2.proyectoTBD.models.Skill;
import grupo2.proyectoTBD.models.User;
import grupo2.proyectoTBD.models.Volunteer;
import grupo2.proyectoTBD.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> newUser(@RequestBody String request){
        User user = gson.fromJson(request,User.class);

        if(user != null){
            if(user.getRut() != null && user.getFirst_name() != null && user.getEmail() != null && user.getPassword() != null && user.getLast_name() != null && user.getPhone() != null) {
                user = UserRepository.newUser(user);
                return new ResponseEntity<>(
                        gson.toJson(user),
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> editUser(@PathVariable Long id,@RequestBody User request){
        User user = UserRepository.getUser(id);

        if(request != null && user != null){
            if(request.getRut() != null){
                user.setRut(request.getRut());
            }
            if(request.getFirst_name() != null){
                user.setFirst_name(request.getFirst_name());
            }
            if(request.getEmail() != null){
                user.setEmail(request.getEmail());
            }
            if(request.getPassword() != null){
                user.setPassword(request.getPassword());
            }
            if(request.getLast_name() != null){
                user.setLast_name(request.getLast_name());
            }
            if(request.getPhone() != null){
                user.setPhone(request.getPhone());
            }
            if(request.getAge() != null){
                user.setAge(request.getAge());
            }
            user = UserRepository.editUser(id,user);
            return new ResponseEntity<>(
                    gson.toJson(user),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id){
        if(UserRepository.deleteUser(id)){
            return new ResponseEntity<>(
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND);
    }

}
