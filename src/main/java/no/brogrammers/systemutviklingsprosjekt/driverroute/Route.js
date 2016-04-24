/**
 * Created by Ingunn on 15.04.2016.
 */


/*var file = "file:///C:/Users/Ingunn/Documents/GitHub/Systemutviklingsprosjekt/src/main/java/no/brogrammers/systemutviklingsprosjekt/driverroute/File.txt";

var reader = new FileReader();
reader.onload = function(e) {
    var text = reader.result;
}

var teksst = reader.readAsText(file, encoding);
*/


var i;

var trheim = new Array();
trheim[0] = "olav tryggvasons gate 24, 7011";
trheim[1] = "sverdrupsvei 33, 7020";
trheim[2] = "olav tryggvasons gate 40, 7011";
trheim[3] = "munkegata 34, 7011";
//trheim[4] = teksst;

var eks2 = [];
for(i=0; i<trheim.length; i++) {
    eks2.push({
        location: trheim[i],
        stopover: true
    });
}

var home = new google.maps.LatLng(63.428982, 10.390766);

function calculateAndDisplayRouteWithButton(directionsService, directionsDisplay) {
    directionsService.route({
        origin: home,
        destination: home,
        waypoints: eks2,

        optimizeWaypoints: true,
        unitSystem: google.maps.UnitSystem.METRIC,
        travelMode: google.maps.TravelMode.DRIVING,
        drivingOptions: {
            departureTime: new Date(Date.now()),
            trafficModel: "optimistic"
        }
    }, function(response, status) {
        if (status === google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        } else {
            window.alert('Directions request failed due to ' + status);
        }
    });
}

