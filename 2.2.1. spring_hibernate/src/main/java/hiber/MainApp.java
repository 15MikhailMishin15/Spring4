package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.model.Car;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      Car car1 = new Car("Toyota", 123);
      Car car2 = new Car("Honda", 456);
      Car car3 = new Car("Ford", 789);
      Car car4 = new Car("BMW", 101);

      userService.add(car1);
      userService.add(car2);
      userService.add(car3);
      userService.add(car4);

      userService.add(new User("User5", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User6", "Lastname2", "user2@mail.ru", car2));
      userService.add(new User("User7", "Lastname3", "user3@mail.ru", car3));
      userService.add(new User("User8", "Lastname4", "user4@mail.ru", car4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Модель машины = " + user.getCar().getModel());
            System.out.println("Серия машины = " + user.getCar().getSeries());
         }
         System.out.println();
      }

      // Тестирование метода getUserByCarModelAndSeries
      User user = userService.getUserByCarModelAndSeries("Toyota", 123);
      System.out.println("Пользователь, владеющий Toyota с серией 123: ");
      System.out.println("Id = " + user.getId());
      System.out.println("Имя = " + user.getFirstName());
      System.out.println("Фамилия = " + user.getLastName());
      System.out.println("Email = " + user.getEmail());

      context.close();
   }
}
