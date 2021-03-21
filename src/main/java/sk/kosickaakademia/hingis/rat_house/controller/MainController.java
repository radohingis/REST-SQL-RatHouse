package sk.kosickaakademia.hingis.rat_house.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sk.kosickaakademia.hingis.rat_house.database.SQL;
import sk.kosickaakademia.hingis.rat_house.entity.Rat;

@RestController
public class MainController {

    @PostMapping("/catch")
    public ResponseEntity<String> catchRat(@RequestBody String data) {

        Rat rat = new Gson().fromJson(data, Rat.class);

                        if(rat == null
                        || rat.getName().equals("")
                        || rat.getAge() < 0
                        || rat.getAge() > 9
                        || !rat.getColor().matches("^([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$")
                        )  return ResponseEntity.badRequest().body("400 WHOPS, BAD REQUEST");

                String name = rat.getName();
                byte age = rat.getAge();
                byte gender = (byte) rat.getGender().getGender();
                String color = rat.getColor();

                Rat temp = new Rat(name, age, color, gender);

                new SQL().insertRat(temp);

                return ResponseEntity.ok().body("200 YAY WE CATCHED A RAT, LETS PET IT");
    }

}
