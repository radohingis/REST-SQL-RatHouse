package sk.kosickaakademia.hingis.rat_house.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/pet")
    public ResponseEntity petRat(@RequestParam(value = "id") int id) {

        if(id < 0) return ResponseEntity.badRequest().body("400 WHOPS, BAD REQUEST");

        Rat temp = new SQL().getRatById(id);
        String body = new Gson().toJson(temp);
        return ResponseEntity.ok(body);

    }

    @DeleteMapping("/kill")
    public ResponseEntity killThatRat(@RequestParam(value = "id") int id) {

        if(id < 0) return ResponseEntity.badRequest().body("400 WHOPS, BAD REQUEST");

        new SQL().killThatRat(id);

        return ResponseEntity.ok("RAT KILLED, LET'S CATCH NEW ONE");

    }

    @PutMapping("/feed")
    public ResponseEntity feedRat(@RequestParam(value="id") int id) {

        if(id < 0) return ResponseEntity.badRequest().body("400 WHOPS, BAD REQUEST");

        boolean ratFed = new SQL().feedRat(id);

        if (ratFed) return ResponseEntity.ok("Rat fed");
        else return ResponseEntity.ok("Whoops your rat is probably dead");
    }

}
