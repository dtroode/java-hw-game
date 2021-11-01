package com.company.equipment.armour;

import com.company.equipment.Equipment;

public class Armour extends Equipment {
    private int reqLevel;
    private int level;
    private int health;

    // Конструктор оружия
    public Armour(String title, int price, int level, int health, int reqLevel) {
        this.type = "Броня";
        this.title = title;
        this.price = price;
        this.level = level;
        this.health = health;
        this.reqLevel = reqLevel;
    }

    public int getReqLevel() { return reqLevel; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public boolean willBeUsedBy(int level) {
        if (level >= this.reqLevel) {
            return true;
        } else {
            return false;
        }
    }

    // Реализация метода из интерфейса, вывод всей информации
    public void printAllInf() {
        System.out.println(color +
                "Оружие: " + title +
                resetColor + " | " +
                "Уровень: " + this.getLevel() +
                ", защита: " + this.getHealth() +
                ", цена: " + this.getPrice() +
                ", требуемый уровень: " + this.getReqLevel() + ".");
    }
}
