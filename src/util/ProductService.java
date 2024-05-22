package util;

import entities.Employee;
import java.util.List;
import java.util.function.Predicate;

public class ProductService {

    public double filteredSum(List<Employee> emp, Predicate<Employee> criteria) {
        double sum = 0.0;
        for (Employee e : emp) {
            if(criteria.test(e)) sum += e.getSalary();            
        }
        return sum;
    }
}
