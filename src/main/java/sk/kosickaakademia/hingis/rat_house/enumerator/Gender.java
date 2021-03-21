package sk.kosickaakademia.hingis.rat_house.enumerator;

public enum Gender {
    
    male(0), female(1);

    private final int gender;


    Gender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }
}
