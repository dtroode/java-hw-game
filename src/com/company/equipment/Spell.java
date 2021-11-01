package com.company.equipment;

import com.company.elements.Element;

public class Spell<E extends Element> extends Equipment {
    private E element;

    public Spell(String title, int size, int price) {
        this.title = title;
        this.price = price;
    }

    public int getDamage() { return element.getDamage(); }

    public void printAllInf() {
        System.out.println(color +
                "Заклинание: " + title +
                resetColor + " | " +
                "Урон: " + this.getDamage() +
                ", цена: " + this.getPrice() + ".");
    }
}
