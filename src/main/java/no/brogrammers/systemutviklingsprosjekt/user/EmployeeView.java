package no.brogrammers.systemutviklingsprosjekt.user;

import no.brogrammers.systemutviklingsprosjekt.order.*;
import no.brogrammers.systemutviklingsprosjekt.recipe.*;

/**
 * Created by Ingunn on 10.03.2016.
 * Employees that can view information in the database (Manager, cashier, driver, cook)
 */
public abstract class EmployeeView extends User {
    public EmployeeView(int ID, String lastName, String firstName, int phoneNumber, String eMail, java.sql.Date dateOfEmployment, String username, String password) {
        super(ID, lastName, firstName, phoneNumber, eMail, dateOfEmployment, username, password);
    }

    public ViewOrderInterface newManageOrderView() {
        return new ManageOrder();
    }

    public ViewUserInterface newManageUserView() {
        return new ManageUser();
    }

    public ViewRecipeInterface newManageRecipeView() {
        return new ManageRecipe();
    }
}