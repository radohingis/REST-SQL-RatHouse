package sk.kosickaakademia.hingis.rat_house.enumerator;

public enum Gender {

    MALE((byte) 0), FEMALE((byte) 1);

    private final byte gender;

    Gender(byte gender) {
        this.gender = gender;
    }

    public byte getGender() {
        return gender;
    }
}
