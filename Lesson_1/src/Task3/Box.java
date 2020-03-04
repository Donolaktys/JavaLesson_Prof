package Task3;

import Task3.Fruit.Fruit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box<T extends Fruit> {

    private List<T> fruitBox;

    // конструктор
    public Box() {
        this.fruitBox = new ArrayList<>();
    }

    public Box(T... fruits) {
        this.fruitBox = new ArrayList<>(Arrays.asList(fruits));
    }

    // вес коробки
    public Float getWeight() {
        Float weightBox = 0.0f;
        for (T t :
                fruitBox) {
            weightBox += t.getFruitWeight();
        }
        return weightBox;
    }

    // сравнение веса текущей коробки c secondBox
    public boolean compare(Box<? extends Fruit> secondBox) {
        return Math.abs(this.getWeight() - secondBox.getWeight()) < 0.0001;
    }

    public void addFruitOnBox(T fruit) {
        fruitBox.add(fruit);
    }

    public void move(Box<T> moveTo) {
        if (moveTo == this) {
            return;
        }

        moveTo.fruitBox.addAll(this.fruitBox);
        this.fruitBox.clear();
    }

}
