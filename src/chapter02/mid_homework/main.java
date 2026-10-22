package chapter02.mid_homework;

public class main {
    public static void main(String[] args) {
        try {
            Animal[] zoo = {
                    new Dog("뽀삐", 3),
                    new Cat("나비", 1),
                    new Dog("우리", 9),
                    new Cat("케빈", 13),
                    new Dog("아롱이", -5)
            };

            for(Animal a : zoo) {
                System.out.println(a.getName() + " > " + a.move());
            }
        }catch (IllegalArgumentException e){
            System.out.println("예외발생 "+e.getMessage());
        }finally {
            System.out.println("총 등록된 동물 수: " + Animal.getCount());
            System.out.println("동물 등록 시도 종료");
        }




    }
}
