/**
 * Created by Ingunn on 10.03.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.UserPackage;
import no.brogrammers.systemutviklingsprosjekt.Orders.*;

public abstract class EmployeeChange extends EmployeeView {
    public EmployeeChange(int ID, String lastName, String firstName, int phoneNumber, String eMail, int dateOfEmployment) {
        super(ID, lastName, firstName, phoneNumber, eMail, dateOfEmployment);
    }

    //Lag ny employee change interface
}
