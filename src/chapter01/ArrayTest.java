package chapter01;

public class ArrayTest {
    public static void main(String[] args) {

        /*String[] titles2 = new String[3]; //선언한 갯수대로 담을 수 있음
        String[] titles = {"closed","exam","holiday"}; // 0 1 2

        System.out.println(titles[2]); // index 2번 출력 : holiday
        System.out.println(titles); // 주소 출력
        System.out.println(titles.length); // 길이 출력 lengtg() > X

        String[] titles3 = new String[3]; // 선언만 하고 데이터는 x , null 출력

        //메모리, 객체*/

        int[] titles = {1,2,3};
        int[] titles2 = titles; // 객체주소 공유 지양해주세요.

        titles2[0] = 99;

        System.out.println(titles[0]); // 동일한 주소를 참조하고 있기때문에 99로 출력

        int[] clone = titles.clone(); // 배열 전체 복사 (1차원) : 얕은 복사
        System.out.println(clone[0]); //99

        int[][] ints = new int[3][2]; //전역변수
        //2차원 배열 메모리 3개에 각각 2개씩!

        for (int i = 0; i < ints.length; i++){ //지역변수
            for(int j = 0; j < ints[i].length; j++){

                System.out.println(i+"="+ints[i][j]);
            }
        }

        int[][] data = {{1,2},{3,4}}; //clone으로 복사 시 바깥쪽 {} 만 복사 됨
        int[][] datacopy = new int[data.length][];

        for (int i = 0; i < data.length; i++){
            datacopy[i] = data[i].clone();
        } // 깊은 복사
    }
}
