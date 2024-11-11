package store.domain;

import java.time.LocalDate;

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
}
