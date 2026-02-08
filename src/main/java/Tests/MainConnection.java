package Tests;

import Entites.User;
import Services.ServiceUser;
import Utils.MyBD;

public class MainConnection {

    public static void main(String[] args) {

        // 1️⃣ Test connexion DB
        MyBD myBD = MyBD.getInstance();

        // 2️⃣ Création Service
        ServiceUser su = new ServiceUser();

        // 3️⃣ Création Users (TEST INSERT)
        User u1 = new User(
                "Yahya",
                "Amine",
                "yahya@gmail.com",
                "ADMIN",
                22123456,
                "1234",
                "Tunis"
        );

        User u2 = new User(
                "Ben",
                "Ali",
                "benali@gmail.com",
                "USER",
                99111222,
                "abcd",
                "Ariana"
        );
        User u3 = new User(
                "manai",
                "amal",
                "manaiamal@gmail.com",
                "USER",
                65459678,
                "abcd",
                "Ariana"
        );

        // 4️⃣ Ajouter users
      //  su.ajouter(u1);
        //su.ajouter(u2);
            //su.ajouter(u3);

        // 5️⃣ Afficher tous les users
        System.out.println("===== ALL USERS =====");
        for (User u : su.getAll()) {
            System.out.println(u);
        }

        // 6️⃣ Test AUTH
        System.out.println("===== AUTH TEST =====");
        User logged = su.authenticate("yahya@gmail.com", "1234");
        if (logged != null) {
            System.out.println("Login success : " + logged.getNom_user());
        } else {
            System.out.println("Login failed");
        }

        // 7️⃣ Test GET BY ID
        System.out.println("===== GET BY ID =====");
        User u = su.getOneByID(1);
        if (u != null) {
            System.out.println(u);
        }

        // 8️⃣ Test UPDATE
        if (u != null) {
            u.setAdresse_user("Sfax");
            su.modifier(u);
        }

        // 9️⃣ Test DELETE (⚠️ change id)
        su.supprimer(4);
    }
}
