package data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Courier {
    private String login;
    private String password;
    private String firstName;

    public static Courier returnRandomCourier () {
        Faker faker = new Faker();
        return new Courier(faker.name().username(), "123456", faker.name().firstName());
    }
}
