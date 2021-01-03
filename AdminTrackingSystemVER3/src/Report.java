public abstract class Report {
    private String title;

    public Report() {
    }

    public Report(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract void exportCSV();

    public abstract void display();
}
