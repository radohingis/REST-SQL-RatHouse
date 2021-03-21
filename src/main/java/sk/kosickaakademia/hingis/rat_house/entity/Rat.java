package sk.kosickaakademia.hingis.rat_house.entity;

import sk.kosickaakademia.hingis.rat_house.enumerator.Gender;

public class Rat {

    private int id;
    private String name;
    private byte age;
    private String color;
    private Gender gender;
    private static final String HEX_COLOR_PATTERN = "^([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";

    public Rat(String name, byte age, String color, byte gender) {

        this.name = name;

        this.age = age;

        this.color = !color
                     .matches(HEX_COLOR_PATTERN)
                     ? "FFFFFF"
                     : "#" + color;

        this.gender = gender == 0
                      ? Gender.MALE
                      : gender == 1
                      ? Gender.FEMALE
                      : null;
    }

    public Rat(int id, String name, byte age, String color, byte gender) {
        this(name, age, color, gender);
        this.id = id;
    }
}
