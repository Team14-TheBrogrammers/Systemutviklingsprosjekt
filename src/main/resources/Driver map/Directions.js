/**
 * Created by Ingunn on 16.04.2016.
 */


var listOfDestinations = new Array();
listOfDestinations[0] = new google.maps.LatLng(39.105219,-84.510127); /* arnold's */
listOfDestinations[1] = new google.maps.LatLng(39.108751,-84.514561); /* the senate */
listOfDestinations[2] = new google.maps.LatLng(39.095139,-84.494026); /* hofbrauhaus */
listOfDestinations[3] = new google.maps.LatLng(39.109417,-84.514861); /* Lavomatic */

var i;

var eks = new Array();
eks[0] = "Bergen";
eks[1] = "Stavanger";
eks[2] = "Fredrikstad";
eks[3] = "Hovden";
eks[4] = "London";


var trheim = new Array();
trheim[0] = "olav tryggvasons gate 24, 7011";
trheim[1] = "sverdrupsvei 33, 7020";
trheim[2] = "olav tryggvasons gate 40, 7011";
trheim[3] = "munkegata 34, 7011";

var eks2 = [];
for(i=0; i<trheim.length; i++) {
    eks2.push({
        location: trheim[i],
        stopover: true
    });
}


function calculateAndDisplayRoute(directionsService, directionsDisplay) {
    directionsService.route({
        origin: document.getElementById('start').value,
        destination: document.getElementById('end').value,
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