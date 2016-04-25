/**
 * Created by Ingunn on 15.04.2016.
 */

function initialize() {
    var mapProp = {
        center:new google.maps.LatLng(63.420297,10.411906),
        zoom:11,
        mapTypeId:google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
    var geocoder = new google.maps.Geocoder();
    var directionsService = new google.maps.DirectionsService;
    var directionsDisplay = new google.maps.DirectionsRenderer;


    setMarker(geocoder, map, address);
    document.getElementById('submit').addEventListener('click', function() {
        geocodeAddress(geocoder, map);
    });

    //E. C. Dahls gate 2, 7012 Trondheim
    var home=new google.maps.Marker({
        position: new google.maps.LatLng(63.428982, 10.390766),
        icon: "spag6.png",
    });

    var marker=new google.maps.Marker({
        position: new google.maps.LatLng(63.430297,10.411906),
        icon: "spag6.png",
    });

    home.setMap(map);
    marker.setMap(map);
    directionsDisplay.setMap(map);

    google.maps.event.addListener(marker,'click',function() {
        map.setZoom(14);
        map.setCenter(marker.getPosition());
    });

    google.maps.event.addListener(home,'click',function() {
        map.setZoom(14);
        map.setCenter(home.getPosition());
    });


    //Home button:
    var homeControlDiv = document.createElement('div');
    var homeControl = new HomeControl(homeControlDiv, map);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(homeControlDiv);

    //Calculate route: with button
    var onChangeHandler2 = function() {
        calculateAndDisplayRouteWithButton(directionsService, directionsDisplay);
    };

    document.getElementById('route').addEventListener('click', onChangeHandler2);

    //Calculate route: input start and end values
    var onChangeHandler = function() {
        calculateAndDisplayRoute(directionsService, directionsDisplay);
    };

    document.getElementById('submit2').addEventListener('click', onChangeHandler);
}

google.maps.event.addDomListener(window, 'load', initialize);