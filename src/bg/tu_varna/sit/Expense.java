package bg.tu_varna.sit;

import java.time.LocalDate;
import java.util.Date;

public class Expense {
    private double amount;
    private String category;
    private LocalDate date;

    public Expense(double amount, String category, LocalDate date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expense" +
                "amount: " + amount +
                ", category: '" + category + '\'' +
                ", date: " + date;
    }
}