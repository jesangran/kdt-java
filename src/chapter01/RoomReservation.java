package chapter01;

public class RoomReservation {

    public static void main(String[] args) {
        RoomReservation app = new RoomReservation();
        try {
            app.reserve("kim", ""); //err
        } catch (IllegalArgumentException ex) {
            System.out.println("reject="+ex.getMessage());
        }

        app.reserve("kim", "b201");
        app.reserve("", "b202"); //err

    }

    public void reserve(String name, String room) {
        if(name == null || name.equals("")) { //name.isEmpty()
            throw new IllegalArgumentException("empty-name");
        }

        if(room == null || room.equals("")) { ////room.isEmpty()
            throw new IllegalArgumentException("empty-room");
        }

        System.out.println("reserved="+name+","+room);
    }

}
