 package application;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

import java.text.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.printf("%nEnter department's name: ");
        String departmentName = sc.next();
        System.out.println("Enter worker data:");
        System.out.print("Name: ");
        sc.nextLine();
        String workerName = sc.nextLine();
        System.out.print("Level: ");
        String workerLevel = sc.next();
        System.out.print("Base salary: ");
        Double baseSalary = sc.nextDouble();

        Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));

        System.out.print("How many contracts to this worker? ");
        Integer n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.printf("%nEnter contract #"+(i+1)+ " data:");
            System.out.print("Date (DD/MM/YYYY): ");
            LocalDate contractDate = LocalDate.parse(sc.next(),sdf);
            System.out.print("Value per hour: ");
            Double valuePerHour = sc.nextDouble();
            System.out.print("Duration (hours): ");
            Integer hours = sc.nextInt();

            HourContract contract = new HourContract(contractDate , valuePerHour, hours );
            worker.addContract(contract);
        }

        System.out.printf("%nEnter month and year to calculate income (MM/YYYY): ");
        String monthAndYear = sc.next();
        int month = Integer.parseInt(monthAndYear.substring(0,2));
        int year = Integer.parseInt(monthAndYear.substring(3));
        System.out.println("Name: " +worker.getName());
        System.out.println("Department: " +worker.getDepartment().getName());
        System.out.println("Income for " +monthAndYear+ ": " +String.format("%.2f",worker.income(year,month)));



        System.out.println();
        sc.close();
    }
}
