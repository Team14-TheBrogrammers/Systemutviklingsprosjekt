/**
 * Created by Ingunn on 10.03.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.user;
import no.brogrammers.systemutviklingsprosjekt.order.*;
import no.brogrammers.systemutviklingsprosjekt.recipe.*;

public abstract class EmployeeChange extends EmployeeView {
    public EmployeeChange(int ID, String lastName, String firstName, int phoneNumber, String eMail, java.sql.Date dateOfEmployment, String username, String password) {
        super(ID, lastName, firstName, phoneNumber, eMail, dateOfEmployment, username, password);
    }

    public ChangeOrderInterface newManageOrderChange() {
        return new ManageOrder(); //("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/ingunsu?user=ingunsu&password=knrdoB4w", "C:\\SystemutviklingsProsjekt\\errorLog.txt");
    }

    public ChangeRecipeInterface newManageRecipeChange() {
        return new ManageRecipe();
    }
}
