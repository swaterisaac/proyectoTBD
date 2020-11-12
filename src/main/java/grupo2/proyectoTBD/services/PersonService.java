package grupo2.proyectoTBD.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import grupo2.proyectoTBD.repositories.PersonRepositoryImp;

@RestController
public class PersonService {

    private final PersonRepositoryImp PersonRepository;
    PersonService(PersonRepositoryImp PersonRepository){
        this.PersonRepository = PersonRepository;
    }


    @GetMapping("/Person/count")
    public String countPersons(){
        int total = PersonRepository.countPersons();
        return String.format("Tienes %s personas!!", total);
    }
}