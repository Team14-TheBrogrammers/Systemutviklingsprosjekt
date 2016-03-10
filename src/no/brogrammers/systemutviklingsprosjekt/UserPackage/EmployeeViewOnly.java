package no.brogrammers.systemutviklingsprosjekt.UserPackage;

/**
 * Created by Ingunn on 10.03.2016.
 */
public abstract class EmployeeViewOnly extends User {
    public EmployeeViewOnly(int ID, String lastName, String firstName, int phoneNumber, String eMail, int dateOfEmployment) {
        super(ID, lastName, firstName, phoneNumber, eMail, dateOfEmployment);
    }


}
