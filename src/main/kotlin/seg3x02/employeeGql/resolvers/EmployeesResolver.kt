package seg3x02.employeeGql.resolvers

import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.stereotype.Controller
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeesRepository
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput
import java.util.UUID

@Controller
class EmployeesResolver(
    private val employeesRepository: EmployeesRepository
) {

    // Query to get all employees
    @QueryMapping
    fun employees(): List<Employee> {
        return employeesRepository.findAll()
    }

    // Mutation to add a new employee
    @MutationMapping
    fun newEmployee(@Argument("createEmployeeInput") input: CreateEmployeeInput): Employee {
        // Validate the input before proceeding
        if (input.name != null &&
            input.dateOfBirth != null &&
            input.city != null &&
            input.salary != null &&
            input.gender != null &&
            input.email != null) {

            val employee = Employee(
                name = input.name,
                dateOfBirth = input.dateOfBirth,
                city = input.city,
                salary = input.salary,
                gender = input.gender,
                email = input.email
            )

            employeesRepository.save(employee) // Save the new employee to the repository
            return employee
        } else {
            throw Exception("Invalid input")
        }
    }
}
