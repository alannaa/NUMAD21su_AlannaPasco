package neu.edu.madcourse.numad21su_alannapasco.AtYourService;

public class FlightInfo {

    private int price;
    private String date;
    private String from;
    private String to;

    public FlightInfo(int price, String date, String from, String to) {
        this.price = price;
        this.date = date;
        this.from = from;
        this.to = to;
    }

    public int getPrice() {
        return this.price;
    }

    public String getDate() {return this.date;}

    public String getFrom() {return this.from; }

    public String getTo() {return this.to; }

}
