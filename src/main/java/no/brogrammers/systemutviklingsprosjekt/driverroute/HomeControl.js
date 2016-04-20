/**
 * Created by Ingunn on 15.04.2016.
 */

function HomeControl(controlDiv, map) {
    var home = new google.maps.LatLng(63.428982, 10.390766);

    controlDiv.style.padding = '5px';
    var controlUI = document.createElement('div');
    controlUI.style.backgroundColor = 'white';
    controlUI.style.border='1px solid';
    controlUI.style.cursor = 'pointer';
    controlUI.style.textAlign = 'center';
    controlUI.title = 'Set map to home';
    controlDiv.appendChild(controlUI);
    var controlText = document.createElement('div');
    controlText.style.fontFamily='Arial, sans-serif';
    controlText.style.fontSize='12px';
    controlText.style.paddingLeft = '4px';
    controlText.style.paddingRight = '4px';
    controlText.innerHTML = '<b>Home<b>'
    controlUI.appendChild(controlText);

    // Setup click-event listener: simply set the map to Kalvskinnet
    google.maps.event.addDomListener(controlUI, 'click', function() {
        map.setCenter(home)
    });


}