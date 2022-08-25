package com.umutates.izinmodule.mapper;

import com.umutates.izinmodule.dto.CreateEmployeeRequest;
import com.umutates.izinmodule.dto.Employee;
import com.umutates.izinmodule.model.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    EmployeeEntity createRequestToEmployeeEntity(CreateEmployeeRequest source);

    @Mapping(source = "numberOfLeaves", target = "leavesAmount")
    Employee employeeEntityToEmployee(EmployeeEntity source);
}
