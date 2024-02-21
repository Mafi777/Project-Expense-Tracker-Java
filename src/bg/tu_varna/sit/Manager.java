package bg.tu_varna.sit;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Manager {
    private ExpenseTracker expenseTracker;
    private Scanner scanner;

    public Manager() {
        expenseTracker = new ExpenseTracker();
        scanner = new Scanner(System.in);
    }

    public void run() {
        String command;
        boolean exit = false;

        while (!exit) {
            System.out.println("Enter a command: ");
            command = scanner.nextLine();
            String[] commandParts = command.split(" ");
            String operation = commandParts[0].toLowerCase();

            switch (operation) {
                case "add":
                    addExpense(commandParts);
                    break;
                case "calculate":
                    calculateMonthlyExpenditure(commandParts);
                    break;
                case "report":
                    generateReportByCategory(commandParts[1], commandParts);
                    break;
                case "get":
                    getExpensesByCategory(commandParts);
                    break;
                case "exit":
                    exit = true;
                    break;
                case "help":
                    printHelp();
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("add <amount> <category> <date> - Add a new expense");
        System.out.println("calculate <month> <year> - Calculate monthly expenditure");
        System.out.println("report monthly <month> <year> - Report monthly");
        System.out.println("report category <category>  - Report by category");
        System.out.println("get <category> - Get expenses by category");
        System.out.println("help - Display available commands");
        System.out.println("exit - Exit the program");
    }

    public void addExpense(String[] commandParts) {
        if (commandParts.length < 4) {
            System.out.println("Error: Please provide amount, category, and date.");
        } else {
            try {
                double amount = Double.parseDouble(commandParts[1]);
                String category = commandParts[2];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date = LocalDate.parse(commandParts[3], formatter);
                Expense expense = new Expense(amount, category, date);
                expenseTracker.addExpense(expense);
                System.out.println("Expense added successfully.");
            } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("Error: Invalid input format. Please try again.");
            }
        }

    }

    public void calculateMonthlyExpenditure(String[] commandParts) {
        if (commandParts.length < 3) {
            System.out.println("Error: Please provide month and year.");
        } else {
            try {
                int month = Integer.parseInt(commandParts[1]);
                int year = Integer.parseInt(commandParts[2]);

                double monthlyExpenditure = expenseTracker.calculateMonthlyExpenditure(month, year);
                System.out.println("Total expenditure for " + month + "/" + year + ": €" + monthlyExpenditure);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input format. Please try again later.");
            }
        }
    }

    public void getExpensesByCategory(String[] commandParts) {
        if (commandParts.length < 2) {
            System.out.println("Error: Please provide a category.");
        } else {
            String category = commandParts[1];
            List<Expense> expenses = expenseTracker.getExpensesByCategory(category);
            System.out.println(category + "expenses:");
            for (Expense expense : expenses) {
                System.out.println("Amount:  €" + expense.getAmount() + ", Date: " + expense.getDate());
            }
        }
    }
    public void generateReportByCategory(String reportType, String[] commandParts) {
        switch (reportType) {
            case "monthly":
                if (commandParts.length < 4) {
                    System.out.println("Error: Please provide month and year.");
                } else {
                    try {
                        int month = Integer.parseInt(commandParts[2]);
                        int year = Integer.parseInt(commandParts[3]);
                        double monthlyExpenditure = expenseTracker.calculateMonthlyExpenditure(month, year);
                        System.out.println("Total expenditure for " + month + "/" + year + ": $" + monthlyExpenditure);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid input format. Please try again later.");
                    }
                }
                break;
            case "category":
                if (commandParts.length < 2) {
                    System.out.println("Error: Please provide a category.");
                } else {
                    String category = commandParts[2];
                    String report = expenseTracker.generateReportByCategory(category);
                    System.out.println(report);
                }
                break;
            case "exit":
                if (commandParts[0].equals("exit")){
                    break;
                }
            default:
                System.out.println("Invalid report type. Please try again.");
                break;
        }
    }
}