package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car01 = new Car("Delica", 111);
        Car car02 = new Car("LandCruiser", 150);
        Car car03 = new Car("Wraith", 1);
        Car car04 = new Car("Santa Fe", 25);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", car01));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", car02));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", car03));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", car04));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        System.out.println(userService.getUserByCar("LandCruiser",150));

        context.close();
    }
}
