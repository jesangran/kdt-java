package homework_02;

public class IntTop {
    public static void main(String[] args) {
        int top = Integer.MAX_VALUE;
        int wrap = top + 1;

        System.out.println("top="+top);
        System.out.println("wrap="+wrap);

        // top은 정수의 최대값, wrap은 정수의 최대값에 +1을 하기때문에 더이상 늘어나지 않음

    }
}
