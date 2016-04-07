/**
 * Created by Ingunn on 01.04.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.User;

public class UserFactory {

    public UserInterface managerUser(Manager manager) {
        return manager.newManageUser();
    }
}
