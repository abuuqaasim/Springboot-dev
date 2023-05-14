package payroll;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeRepository empRepo;

    EmployeeController(EmployeeRepository repo){
        this.empRepo = repo;
    }

    @GetMapping("/employees")
    List<Employee2> findAll(){
        return this.empRepo.findAll();
    }

    @PostMapping("/employees")
    Employee2 AddEmployee(@RequestBody Employee2 newEmployee){
         return this.empRepo.save(newEmployee);
    }

    @GetMapping("/employees/{id}")
    Employee2 findOneEmployee(@PathVariable Long id){

        return empRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

   /** @PutMapping("/employees/{id}")
    Employee replacEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){
        return this.empRepo.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return empRepo.save();
        }).orElseGet(() -> {
            newEmployee.setId(id);
            return empRepo.save(newEmployee);
        });
    }
    */

    @PutMapping("/employees/{id}")
    Employee2 changeEmployee(@RequestBody Employee2 sub, @PathVariable Long id){

        Employee2 removedEmployee = empRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        removedEmployee.setName(sub.getName());
        removedEmployee.setRole(sub.getRole());
        return empRepo.save(removedEmployee);

    }

    @DeleteMapping("/employees/{id}")
    void  deleteEmployee(@PathVariable Long id){
         empRepo.deleteById(id);
    }

    
}
