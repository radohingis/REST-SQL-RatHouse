package sk.kosickaakademia.hingis.rat_house.entity;

import sk.kosickaakademia.hingis.rat_house.enumerator.Gender;

public class Rat {

    private int id;
    private final String name;
    private final byte age;
    private final String color;
    private final Gender gender;
    private static final String HEX_COLOR_PATTERN = "^([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";

    public Rat(String name, byte age, String color, byte gender) {

        this.name = name;

        this.age = age;

        this.color = !color
                     .matches(HEX_COLOR_PATTERN)
                     ? "#FFFFFF"
                     : "#" + color;

        this.gender = gender == 0 ? Gender.male : Gender.female;
    }

    public Rat(int id, String name, byte age, String color, byte gender) {
        this(name, age, color, gender);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte getAge() {
        return age;
    }

    public String getColor() {
        return color;
    }

    public Gender getGender() {
        return gender;
    }

    public String stringify() {
        return getId() + " " + getName() + " " + getAge() + " " + getColor() + " " + getGender();
    }
}
