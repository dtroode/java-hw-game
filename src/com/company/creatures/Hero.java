package com.company.creatures;

import com.company.equipment.Equipment;
import com.company.equipment.Spell;
import com.company.equipment.armour.Armour;
import com.company.equipment.weapons.Weapon;

import java.util.ArrayList;

// Класс персонажа. Имеет: имя, здоровье, опыт, уровень, показатели атаки и защиты
// Наследуется от класса персоны и имплементирует интерфейс, определяющий вывод информации
public class Hero<S1 extends Spell<?>, S2 extends Spell<?>, S3 extends Spell<?>> extends Creature {
    // Поля класса персонажа
    private int xp;
    private int level;
    private int money;
    private S1 firstSpell;
    private S2 secondSpell;
    private S3 thirdSpell;

    private ArrayList<Equipment> equipment = new ArrayList<>();

    public Hero(String title, String name,
                int health, int xp, int level, int money, int attack,
                Weapon weapon, Armour armour) {
        this.type = "Персонаж";
        this.title = title;
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.xp = xp;
        this.level = level;
        this.money = money;

        setX(3);
        setY(1);

        setWeapon(weapon);
        setArmour(armour);
    }

    // Методы класса персонажа, которые возвращают поля
    public synchronized int getXp() { return xp; }
    public synchronized int getLevel() {
        return level;
    }
    public synchronized int getMoney() { return money; }

    public int firstSpellDamage() {
        return firstSpell.getDamage();
    }
    public int secondSpellDamage() {
        return secondSpell.getDamage();
    }
    public int thirdSpellDamage() {
        return thirdSpell.getDamage();
    }

    public synchronized void setWeapon(Weapon weapon) {
        if (willUseThisEquipment(weapon.getReqLevel())) {
            this.weapon = weapon;
        } else {
            System.out.println("Игрок не может использовать данное оружие в силу уровня, слот остаётся пустым.");
        }
    }
    public synchronized void setArmour(Armour armour) {
        if (willUseThisEquipment(armour.getReqLevel())) {
            this.armour = armour;
        } else {
            System.out.println("Игрок не может использовать данную броню в силу уровня, слот остаётся пустым.");
        }
    }

    public void addEquipment(Equipment equipment) {
        if (this.money < equipment.getPrice()) {
            System.out.println("Недостаточно денег");
            return;
        }
        this.equipment.add(equipment);
    }

    // Метод, который сообщает, может ли персонаж использовать оружие
    // На вход получает переменную reqLevel с требуемым оружием уровнем
    public boolean willUseThisEquipment(int reqLevel) {
        if (this.level >= reqLevel) {
            return true;
        } else {
            return false;
        }
    }

    // Реализация метода из интерфейса, вывод всей информации
    public void printAllInf() {
        System.out.println(color +
                "Персонаж: " + title + ", " + this.getName() +
                resetColor + " | " +
                "Здоровье: " + this.getHealth() +
                ", опыт: " + this.getXp() +
                ", уровень: " + this.getLevel() +
                ", атака: " + this.getAttack() + ".");
    }
}
