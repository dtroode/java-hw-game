package com.company.equipment.weapons;

import com.company.equipment.Equipment;

// Класс оружия. Имеет: название, показатель атаки, цену, требуемый уровень
// Имплементирует интерфейс, определяющий вывод информации
public class Weapon extends Equipment {
    // Поля класса оружия
    private int damage;
    private int reqLevel;

    // Конструктор оружия
    public Weapon(String title, int price, int damage, int reqLevel) {
        this.type = "Оружие";
        this.title = title;
        this.price = price;
        this.damage = damage;
        this.reqLevel = reqLevel;
    }

    // Методы, дающие доступ к полям
    public int getDamage() {
        return damage;
    }
    public int getReqLevel() {
        return reqLevel;
    }

    // Метод, который сообщает, может ли персонаж использовать оружие
    // На вход получает переменную level с уровнем персонажа
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
                "Атака: " + this.getDamage() +
                ", цена: " + this.getPrice() +
                ", требуемый уровень: " + this.getReqLevel() + ".");
    }
}
