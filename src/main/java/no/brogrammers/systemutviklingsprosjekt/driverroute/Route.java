package no.brogrammers.systemutviklingsprosjekt.driverroute;

import com.google.maps.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import com.google.maps.

/**
 * Created by Ingunn on 14.04.2016.
 */
public class Route {
    final String endJavascript = "var i;\n" +
            "\n" +
            "var eks2 = [];\n" +
            "for(i=0; i<trheim.length; i++) {\n" +
            "    eks2.push({\n" +
            "        location: trheim[i],\n" +
            "        stopover: true\n" +
            "    });\n" +
            "}\n" +
            "\n" +
            "var home = new google.maps.LatLng(63.428982, 10.390766);\n" +
            "\n" +
            "function calculateAndDisplayRouteWithButton(directionsService, directionsDisplay) {\n" +
            "    directionsService.route({\n" +
            "        origin: home,\n" +
            "        destination: home,\n" +
            "        waypoints: eks2,\n" +
            "\n" +
            "        optimizeWaypoints: true,\n" +
            "        unitSystem: google.maps.UnitSystem.METRIC,\n" +
            "        travelMode: google.maps.TravelMode.DRIVING,\n" +
            "        drivingOptions: {\n" +
            "            departureTime: new Date(Date.now()),\n" +
            "            trafficModel: \"optimistic\"\n" +
            "        }\n" +
            "    }, function(response, status) {\n" +
            "        if (status === google.maps.DirectionsStatus.OK) {\n" +
            "            directionsDisplay.setDirections(response);\n" +
            "        } else {\n" +
            "            window.alert('Directions request failed due to ' + status);\n" +
            "        }\n" +
            "    });\n" +
            "}";

    private ArrayList<String> deliveries = new ArrayList<String>();

    public Route(ArrayList<String> deliveries) {
        this.deliveries = deliveries;
        generateJs();
        //System.out.println(getClass().getResource("/login_background.png").getFile());
    }

    private void generateJs() {
        String output = "var trheim = new Array();\n";
        for(int i = 0; i < deliveries.size(); i++) {
            output += "trheim[" + i + "] = \"" + deliveries.get(i) + "\";\n";
        }
        /*+
                "trheim[0] = \"olav tryggvasons gate 24, 7011\";\n" +
                "trheim[1] = \"sverdrupsvei 33, 7020\";\n" +
                "trheim[2] = \"olav tryggvasons gate 40, 7011\";\n" +
                "trheim[3] = \"munkegata 34, 7011\";";*/

        output += "\n" + endJavascript;
        try {
            FileWriter fileWriter = new FileWriter("C:\\SystemutviklingsProsjekt\\Driver map\\DriverRoute.js");//"C:\\Users\\Knut\\Documents\\GitHub\\Systemutviklingsprosjekt\\src\\main\\resources\\Driver map\\DriverRoute.js");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(output);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
