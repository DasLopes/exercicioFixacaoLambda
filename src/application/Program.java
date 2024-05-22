package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;
import util.ProductService; //ProductService ps = new ProductService();

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employee> employees = new ArrayList<>();        

        System.out.print("Enter pull file path: ");
        String path = sc.nextLine();
        System.out.print("Enter salary: ");
        Double salary = sc.nextDouble();
        System.out.println("Email of people whose salary is more than "
                + String.format("%.2f", salary)
                + ":");

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();

            while (line != null) {
                String[] vect = line.split(",");
                employees.add(new Employee(vect[0], vect[1], Double.parseDouble(vect[2])));
                line = br.readLine();
            }

            List<String> filterSalary = employees
                    .stream()
                    .filter(p -> p.getSalary() > salary)
                    .map(p -> p.getEmail()).sorted()
                    .collect(Collectors.toList());

            filterSalary.forEach(System.out::println);

            //Solução implementando classe ProductService, torna o código mais flexível
            /* ProductService ps = new ProductService();
             * System.out.print("Enter filter caractere: ")
             * char caracter = sc.next().charAt(0);
             * double sum = ps.filteredSum(employees, p -> p.getName().charAt(0) ==
             * caracter);
             */

            double sum = employees
                    .stream()
                    .filter(p -> p.getName()
                            .charAt(0) == 'M')
                    .map(p -> p.getSalary())
                    .reduce(0.0, (x, y) -> x + y);

            System.out.println("Sum of salary of people whose name starts with 'M': "
                    + String.format("%.2f", sum));

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}
