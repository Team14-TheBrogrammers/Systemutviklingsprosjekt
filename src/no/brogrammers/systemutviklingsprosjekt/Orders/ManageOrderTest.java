/**
 * Created by Ingunn on 10.03.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.Orders;
import no.brogrammers.systemutviklingsprosjekt.UserPackage.*;

public class ManageOrderTest {
    public static void main(String[] args) {

        Driver driver = new Driver(1, "hei", "hei", 123, "hehe", 12423543);
        ViewOrderInterface evi = driver.newManageOrder();
        System.out.println(evi.deliveriesToday());
        //evi.deleteCustomer();


        //deliveriesToday;

        //ChangeOrderInterface eci1 = new Manager(1, "hei", "hei", 123, "hehe", 12423543);
        //ManageOrder mo = new ManageOrder();



       /* public class Rovdyrfabrikk {
            public SkandinaviskeRovdyr nyBinne(int ankommetDato, String adresse, String navn, int fDato, int kull) {
                return new Hunnindivid("Brunbjørn", "Ursus arctos", "Ursidae", ankommetDato, adresse, navn, fDato, true, kull);
            }

            Test:
        Rovdyrfabrikk rov = new Rovdyrfabrikk();
        //int ankommetDato, String adresse, String navn, int fDato
        SkandinaviskeRovdyr r1 = rov.nyBinne(20091010, "bur1", "Ingunn", 20081010, 2);*/
    }
}
