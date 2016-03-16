package no.brogrammers.systemutviklingsprosjekt.UserPackage;

/**
 * Created by Ingunn on 10.03.2016.
 */

import no.brogrammers.systemutviklingsprosjekt.Orders.*;

public class Driver extends EmployeeView {
    public Driver(int ID, String lastName, String firstName, int phoneNumber, String eMail, int dateOfEmployment) {
        super(ID, lastName, firstName, phoneNumber, eMail, dateOfEmployment);
    }
}
