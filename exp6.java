// easy
import java.util.*;

class Employee {
    String name;
    int age;
    double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " | Age: " + age + " | Salary: $" + salary;
    }
}

public class exp6 {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 30, 60000));
        employees.add(new Employee("Bob", 25, 50000));
        employees.add(new Employee("Charlie", 35, 70000));

        // Sorting by Salary using Lambda Expression
        employees.sort((e1, e2) -> Double.compare(e1.salary, e2.salary));

        // Printing sorted employees
        System.out.println("Sorted Employees by Salary:");
        employees.forEach(System.out::println);
    }
}


// Medium 
import java.util.*;
import java.util.stream.Collectors;

class Student {
    String name;
    double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return name + " | Marks: " + marks;
    }
}

public class exp6 {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 85.5),
            new Student("Bob", 72.0),
            new Student("Charlie", 78.2),
            new Student("David", 90.0),
            new Student("Eve", 65.4)
        );

        // Filtering students scoring above 75% and sorting by marks
        List<Student> topStudents = students.stream()
            .filter(s -> s.marks > 75)
            .sorted((s1, s2) -> Double.compare(s2.marks, s1.marks))
            .collect(Collectors.toList());

        // Displaying the students
        System.out.println("Students Scoring Above 75% (Sorted by Marks):");
        topStudents.forEach(System.out::println);
    }
}




// hard

import java.util.*;
import java.util.stream.Collectors;

class Product {
    String name;
    String category;
    double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " | Category: " + category + " | Price: $" + price;
    }
}

public class exp6 {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1200.0),
            new Product("Smartphone", "Electronics", 800.0),
            new Product("TV", "Electronics", 1500.0),
            new Product("Shirt", "Clothing", 50.0),
            new Product("Jeans", "Clothing", 80.0),
            new Product("Refrigerator", "Appliances", 1000.0),
            new Product("Washing Machine", "Appliances", 700.0),
            new Product("Sneakers", "Footwear", 120.0)
        );

        // Grouping products by category
        Map<String, List<Product>> groupedByCategory = products.stream()
            .collect(Collectors.groupingBy(p -> p.category));

        // Finding the most expensive product in each category
        Map<String, Product> mostExpensiveByCategory = products.stream()
            .collect(Collectors.toMap(
                p -> p.category, // Key: Category
                p -> p, // Value: Product
                (p1, p2) -> p1.price > p2.price ? p1 : p2 
            ));
        double averagePrice = products.stream()
            .mapToDouble(p -> p.price)
            .average()
            .orElse(0.0);

        // Display Results
        System.out.println("Products Grouped by Category:");
        groupedByCategory.forEach((category, prodList) -> {
            System.out.println(category + ": " + prodList);
        });

        System.out.println("\nMost Expensive Product in Each Category:");
        mostExpensiveByCategory.forEach((category, product) -> {
            System.out.println(category + ": " + product);
        });

        System.out.println("\nAverage Price of All Products: $" + averagePrice);
    }
}
