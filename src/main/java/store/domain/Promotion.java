package store.domain;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class Promotion {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int buy;
    private int get;

    public Promotion(String name, LocalDate startDate, LocalDate endDate, int buy, int get) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.buy = buy;
        this.get = get;
    }

    public String getName() {
        return name;
    }

    public ChronoLocalDate getStartDate() {
        return startDate;
    }

    public int getRequiredQuantity() {
        return buy;
    }

    public int getFreeQuantity() {
        return get;
    }

    public ChronoLocalDate getEndDate() {
        return endDate;
    }
}
