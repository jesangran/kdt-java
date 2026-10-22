package guest;

import java.util.ArrayList;

public class GuestRepository {

    private ArrayList<Guest> guests = new ArrayList<>();
    private int nextNumber = 1;

    public int nextId(){
        int id = nextNumber;
        nextNumber = nextNumber + 1;
        return id;
    }

    public void save(Guest guest){
        guests.add(guest);
    }

    public Guest findById(int id){
        for (Guest guest : guests){
            if(guest.getId() == id){
                return guest;
            }
        }

        throw new IllegalArgumentException("명단에 없습니다. 번호: "+id);
    }

    public ArrayList<Guest> findAll(){
        ArrayList<Guest> result = new ArrayList<>();
        for (Guest guest : guests) {
            result.add(guest);
        }

        return result;
    }
}
