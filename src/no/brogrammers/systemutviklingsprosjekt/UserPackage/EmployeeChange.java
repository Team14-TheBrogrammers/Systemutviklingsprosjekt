/**
 * Created by Ingunn on 10.03.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.UserPackage;
import no.brogrammers.systemutviklingsprosjekt.Orders.*;

public abstract class EmployeeChange extends EmployeeView {
    public EmployeeChange(int ID, String lastName, String firstName, int phoneNumber, String eMail, int dateOfEmployment) {
        super(ID, lastName, firstName, phoneNumber, eMail, dateOfEmployment);
    }

    /*public ChangeOrderInterface newManageOrderChange() {
        return new ManageOrder("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/ingunsu?user=ingunsu&password=knrdoB4w", "C:\\SystemutviklingsProsjekt\\errorLog.txt");
    }*/
}
