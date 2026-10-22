package chapter01;

public class LunchReject {
    public void takeMenu(String name) throws IllegalArgumentException {
        if(name == null | name.equals("")){
            throw new IllegalArgumentException("empty-name");
        }
        System.out.println("name="+name);
    }

    public static void main(String[] args) {
        LunchReject app = new LunchReject();
        try {
            app.takeMenu(""); //IllegalArgumentException
        } catch (IllegalArgumentException ex) {
            System.out.println("rejec="+ex.getMessage());
        }
        app.takeMenu("pork");
    }
}
