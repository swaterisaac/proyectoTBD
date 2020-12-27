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

    @GetMapping({"","/"})
    public String getUsers(){
        List<User> users = UserRepository.getUsers();
        return gson.toJson(users);
    }
    @PostMapping({"","/"})
    public ResponseEntity<String> newUser(@RequestBody String request){
        User user = gson.fromJson(request,User.class);

        if(user != null){
            if(user.getNombre() != null && user.getApellido() != null && user.getEmail() != null && user.getSexo() != null) {
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
            if(request.getNombre() != null){
                user.setNombre(request.getNombre());
            }
            if(request.getApellido() != null){
                user.setApellido(request.getApellido());
            }
            if(request.getEmail() != null){
                user.setEmail(request.getEmail());
            }
            if(request.getSexo() != null){
                user.setSexo(request.getSexo());
            }
            if(request.getLongitude() != null){
                user.setLongitude(request.getLongitude());
            }
            if(request.getLatitude() != null){
                user.setLatitude(request.getLatitude());
            }
            if(request.getAge() != null){
                user.setAge(request.getAge());
            }
            if(request.getPassword() != null){
                user.setPassword(request.getPassword());
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
