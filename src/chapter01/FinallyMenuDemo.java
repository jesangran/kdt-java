package chapter01;

public class FinallyMenuDemo {
    public void takeMenu(String menu) {
        if (menu == null || menu.equals("")) {
            throw new IllegalArgumentException("empty-menu");
        }
        System.out.println("menu=" + menu);
    }

    public void request(String menu) {
        try {
            takeMenu(menu);
        } catch (IllegalArgumentException ex) {
            System.out.println("reject=" + ex.getMessage());
        } finally { //물건을 샀던 사지않았던 종업원이 안녕히 가세요하고 하는거랑 같음 ㅋㅋ
            System.out.println("request=done");
        }
    }

    public static void main(String[] args) {
        FinallyMenuDemo app = new FinallyMenuDemo();
        app.request("");
        app.request("pork");
    }
}
