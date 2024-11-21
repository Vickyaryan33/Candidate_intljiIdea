package com.crm.controller;

import com.crm.entity.Employee;
import com.crm.payload.EmployeeDto;
import com.crm.service.EmployeeService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {


    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

     // link to add employee
     //http://localhost:8181/api/v1/employee/add

   @PostMapping ("/add")
   public ResponseEntity<?> addEmployee(  // return type is multiple so i am use <?>
           //json content convert to java object
            // json content to java object
           @Valid @RequestBody EmployeeDto dto ,
           BindingResult result
           ){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
       // save employee to database
      EmployeeDto employeeDto=  employeeService.addEmployee(dto);
       return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
}
//http://localhost:8181/api/v1/student/delete?id=1
@DeleteMapping("/delete")
  public ResponseEntity<String> deleteEmployee(
          @RequestParam long id
  ){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);

  }

  //http://localhost:8181/api/v1/employee/update?id=1
  @PutMapping("/update")
  public ResponseEntity<EmployeeDto> updateEmployee(
          @RequestParam long id,
           @RequestBody EmployeeDto dto){
       EmployeeDto employeeDto= employeeService.updateEmployee(id,dto);
        return  new ResponseEntity<>(employeeDto,HttpStatus.OK);
  }
  //http://localhost:8181/api/v1/employee/getall?pageNo=0&pageSize=5
    @GetMapping("/getall")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize
    ){
       List<EmployeeDto> employeeDto= employeeService.getAllEmployees( pageNo, pageSize);
       return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

   //http://localhost:8181/api/v1/employee/employeeId/1
@GetMapping("/employeeId/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long id){

       EmployeeDto employeeDto=  employeeService.getEmployeeById(id);
       return new ResponseEntity<>(employeeDto, HttpStatus.OK);
   }
}

