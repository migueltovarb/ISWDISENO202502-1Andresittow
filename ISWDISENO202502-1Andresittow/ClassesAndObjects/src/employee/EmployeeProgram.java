package employee;

public class EmployeeProgram {
    public static void main(String[] args) {
        Employee employee = new Employee(908589, "Edison", "Chacua", 3200000);
        System.out.println(employee);
        employee.setSalary(4000000);
        System.out.println(employee);
        System.out.println(employee.getAnnualSalary());
    }
}
