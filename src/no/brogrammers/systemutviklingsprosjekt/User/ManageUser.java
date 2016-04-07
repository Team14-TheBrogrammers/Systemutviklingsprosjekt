package no.brogrammers.systemutviklingsprosjekt.User;

/**
 * Created by Ingunn on 01.04.2016.
 */
public class ManageUser implements UserInterface {

    public boolean regManager() {
        //legg til i database
        return true;
    }

    public boolean regCashier() {
        return true;
    }

    public boolean regCook() {
        return true;
    }

    public boolean regDriver() {
        return true;
    }

    public boolean deleteUser() {
        return true;
    }

    public boolean changeUser() {
        return true;
    }

    public User viewUser() {
        return null;
    }
    public boolean regManager(int id, String lastName, String firstName, int phone, String mail, String doe) {
        Manager manager = new Manager(id, lastName, firstName, phone, mail, doe);
        // if (add til database)
        return true;
    }

    public boolean regCashier(int id, String lastName, String firstName, int phone, String mail, String doe) {
        Cashier cashier = new Cashier(id, lastName, firstName, phone, mail, doe);
        return true;
    }

    public boolean regCook(int id, String lastName, String firstName, int phone, String mail, String doe) {
        Cook cook = new Cook(id, lastName, firstName, phone, mail, doe);
        return true;
    }

    public boolean regDriver(int id, String lastName, String firstName, int phone, String mail, String doe) {
        Driver driver = new Driver(id, lastName, firstName, phone, mail, doe);
        return true;
    }

    public boolean deleteUser(int id) {
        //sjekk om bruker eksisterer
        return false;
    }

    public boolean changeLastName(int id, String lastName) {
        return false;
    }

    public boolean changeFirstName(int id, String firstName) {
        return false;
    }

    public boolean changePhone(int id, int phone) {
        return false;
    }

    public boolean changeMail(int id, String mail) {
        return false;
    }


    public User viewUser(int id) { //id
        return null;
    }

    public User viewUser(String lastName) { //last name
        return null;
    }
}
