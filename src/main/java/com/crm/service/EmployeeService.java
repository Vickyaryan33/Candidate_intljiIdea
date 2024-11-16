package com.crm.service;

import com.crm.entity.Employee;
import com.crm.exception.ResourceNotFound;
import com.crm.payload.EmployeeDto;
import com.crm.repostory.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private ModelMapper modelMapper;
    private EmployeeRepository employeeRepository;
    public EmployeeService(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto addEmployee(EmployeeDto Dto){
       Employee employee= mapToEntity(Dto);
        Employee emp= employeeRepository.save(employee);
       EmployeeDto employeeDto= mapToDto(emp);
     //   employeeDto.setStartDate(new Date());
        return employeeDto;

    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }



    public EmployeeDto updateEmployee(long id, EmployeeDto dto) {
     Employee employee= mapToEntity(dto);
     employee.setId(id);
     Employee updatedEmployee=employeeRepository.save(employee);
      EmployeeDto employeeDto=mapToDto( updatedEmployee);
      return employeeDto;
    }



    public List<EmployeeDto> getAllEmployees( int pageNo, int pageSize) {
    //    List<Employee> employees= employeeRepository.findAll();
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Employee> all = employeeRepository.findAll(pageable);
        List<Employee> employees = all.getContent();

        List<EmployeeDto> dto=
                employees.stream().map(e->mapToDto(e)).collect(Collectors.toList());
        return dto;

    }

    public EmployeeDto getEmployeeById(long empId) {
     Employee employee = employeeRepository.findById(empId).orElseThrow(
             //supplier functional interface uses here
             // supplier functional interface is only give output not taken any input

             () -> new ResourceNotFound("Employee not found with id: " + empId)
     );
      EmployeeDto dto= mapToDto(employee);
      return dto;


    }




    // Entity object copy  to dto object relationship
    EmployeeDto mapToDto(Employee employee){
      EmployeeDto employeeDto=  modelMapper.map(employee, EmployeeDto.class);

//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setId(employee.getId());
//        employeeDto.setName(employee.getName());
//        employeeDto.setMobile(employee.getMobile());
//        employeeDto.setEmailId(employee.getEmailId());
        return employeeDto;
    }

    // Dto object copy to entity object relationship
    Employee mapToEntity(EmployeeDto employeeDto){
        Employee employee = modelMapper.map(employeeDto, Employee.class);
//        Employee employee = new Employee();
//       employee.setId(employeeDto.getId());
//        employee.setName(employeeDto.getName());
//        employee.setMobile(employeeDto.getMobile());
//        employee.setEmailId(employeeDto.getEmailId());
        return employee;
    }

}
