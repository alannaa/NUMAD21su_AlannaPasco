package neu.edu.madcourse.numad21su_alannapasco.AtYourService;

public class FlightInfo {

    public static final String INSTANCE_KEY = "INSTANCE_KEY";

    private String price;
    private String date;
    private String from;
    private String to;

    public FlightInfo(String price, String date, String from, String to) {
        this.price = price;
        this.date = date;
        this.from = from;
        this.to = to;
    }

    public String getPrice() {
        return this.price;
    }

    public String getDate() {return this.date;}

    public String getFrom() {return this.from; }

    public String getTo() {return this.to; }

    static String generatePriceKey(int index){
        return INSTANCE_KEY + index + "pr";
    }

    static String generateDateKey(int index){
        return INSTANCE_KEY + index + "da";
    }

    static String generateFromKey(int index){
        return INSTANCE_KEY + index + "fr";
    }

    static String generateToKey(int index){
        return INSTANCE_KEY + index + "to";
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
