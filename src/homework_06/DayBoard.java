package homework_06;

public class DayBoard {
    public static void main(String[] args) {

        String[] days = { "closed", "exam", "holiday" };
        for(int i = 0; i < days.length; i++){
            System.out.println(i + "=" + days[i]);
        }
        System.out.println("last="+days[days.length-1]);
    }
}
