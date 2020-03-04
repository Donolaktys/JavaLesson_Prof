package Task3;

import Task3.Fruit.Apple;
import Task3.Fruit.Orange;

public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        for (int i = 0; i < 10; i++) {
            appleBox.addFruitOnBox(new Apple());
        }

        Box<Apple> appleBox2 = new Box<>();
        for (int i = 0; i < 10; i++) {
            appleBox2.addFruitOnBox(new Apple());
        }

        Box<Orange> orangeBox = new Box<>(new Orange(), new Orange(), new Orange());

        System.out.println(appleBox.getWeight());
        System.out.println(appleBox2.getWeight());

        System.out.println(appleBox.compare(appleBox2));
        System.out.println(appleBox.compare(orangeBox));

        appleBox2.move(appleBox);

        System.out.println(appleBox.getWeight());
        System.out.println(appleBox2.getWeight());

    }
}

//        10.0
//        10.0
//        true
//        false
//        20.0
//        0.0
//
//        Process finished with exit code 0