package bg.tu_varna.sit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker {
    private List<Expense> expenses;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public double calculateMonthlyExpenditure(int month, int year) {
        double totalExpenditure = 0.0;
        for (Expense expense : expenses) {
            if (expense.getDate().getMonthValue() == month && expense.getDate().getYear() == year) {
                totalExpenditure += expense.getAmount();
            }
        }
        return totalExpenditure;
    }

    public List<Expense> getExpensesByCategory(String category) {
        List<Expense> expenseByCategory = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                expenseByCategory.add(expense);
            }
        }
        return expenseByCategory;
    }

    public String generateReportByCategory(String category) {
        StringBuilder reportBuilder = new StringBuilder();
        double totalAmount = 0.0;

        reportBuilder.append("Report for category '").append(category).append("':\n");
        reportBuilder.append("----------------------------------------\n");

        List<Expense> categoryExpenses = getExpensesByCategory(category);
        for (Expense expense : categoryExpenses) {
            LocalDate expenseDate = expense.getDate();

            reportBuilder.append("Amount: $").append(expense.getAmount()).append(", ")
                    .append("Date: ").append(expenseDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .append("\n");

            totalAmount += expense.getAmount();
        }

        reportBuilder.append("----------------------------------------\n");
        reportBuilder.append("Total: $").append(totalAmount);

        return reportBuilder.toString();
    }
}
