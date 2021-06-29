package neu.edu.madcourse.numad21su_alannapasco.AtYourService;

public class FlightInfo {

    public static final String INSTANCE_KEY = "INSTANCE_KEY";

    private final String price;
    private final String date;
    private final String from;
    private final String to;
    private final String leg; //ONE OF: "IN" OR "OUT"

    public FlightInfo(String price, String date, String from, String to, String leg) {
        this.price = price;
        this.date = date;
        this.from = from;
        this.to = to;
        this.leg = leg;
    }

    public String getPrice() {
        return this.price;
    }

    public String getDate() {return this.date;}

    public String getFrom() {return this.from; }

    public String getTo() {return this.to; }

    public String getLeg() {
        return this.leg;
    }

    static String reformatDateOutputString(String date) {
        if (date.length() < 10) {
            return date;
        }
        return date.substring(5,10) + "-" + date.substring(0,4);
    }

    static String reformatDateInputString(String date) {
        if (date.length() < 10) {
            return date;
        }
        return date.substring(6,10) + "-" + date.substring(0,5);
    }


}
