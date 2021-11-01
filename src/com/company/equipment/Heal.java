package com.company.equipment;

import com.company.base.Base;

public class Heal extends Equipment {
    private int heal;

    public Heal(String title, int size, int price, int heal) {
        this.type = "Аптечка";
        this.title = title;
        this.price = price;
        this.heal = heal;
    }

    public int getHeal() { return heal; }

    public void printAllInf() {
        System.out.println(color +
                "Аптечка: " + title +
                Base.resetColor + " | " +
                "Лечит: " + this.getHeal() +
                ", цена: " + this.getPrice() + ".");
    }
}
