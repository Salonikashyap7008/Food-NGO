let menu = document.querySelector('#menu-icon');
let navbar = document.querySelector('.navbar');

menu.onclick = () => {
    menu.classList.toggle('bx-x');
    navbar.classList.toggle('open');
}
let slider = document.querySelector('.slider .list');
let items = document.querySelectorAll('.slider .list .item');
let next = document.getElementById('next');
let prev = document.getElementById('prev');
let dots = document.querySelectorAll('.slider .dots li');

let lengthItems = items.length - 1;
let active = 0;
next.onclick = function(){
    active = active + 1 <= lengthItems ? active + 1 : 0;
    reloadSlider();
}
prev.onclick = function(){
    active = active - 1 >= 0 ? active - 1 : lengthItems;
    reloadSlider();
}
let refreshInterval = setInterval(()=> {next.click()}, 3000);
function reloadSlider(){
    slider.style.left = -items[active].offsetLeft + 'px';
    // 
    let last_active_dot = document.querySelector('.slider .dots li.active');
    last_active_dot.classList.remove('active');
    dots[active].classList.add('active');

    clearInterval(refreshInterval);
    refreshInterval = setInterval(()=> {next.click()}, 3000);

    
}

dots.forEach((li, key) => {
    li.addEventListener('click', ()=>{
         active = key;
         reloadSlider();
    })
})
window.onresize = function(event) {
    reloadSlider();
};
// catlouge
const card = document.querySelector(".cardcontainer");
const form = document.querySelector(".form");
let mouseDownAt = 0;
let left = 0;
card.onmousedown = (e) => {
    mouseDownAt = e.clientX;
    console.log(mouseDownAt);
};
card.onmouseup = () => {
    mouseDownAt = 0;  
    card.style.userSelect = 'unset';
    card.style.cursor = 'unset';
    form.style.pointerEvents = 'unset';
    form.classList.remove('left');
    form.classList.remove('right');
}
card.onmousemove = e => {
    if(mouseDownAt == 0) return;
    card.style.userSelect = 'none';
    card.style.cursor = 'grab';
    form.style.pointerEvents = 'none';
    
    if(e.clientX > mouseDownAt){
        form.classList.add('left');
        form.classList.remove('right');
    }else if(e.clientX < mouseDownAt){
        form.classList.remove('left');
        form.classList.add('right');
    }
    // increase or decrease the speed 
    //by changing the value of 'speed'
    let speed = 3;
    let leftTemporary = left + ((e.clientX - mouseDownAt) / speed);
    let leftLimit = form.offsetWidth - card.offsetWidth / 2;

    
    if(leftTemporary < 0 && Math.abs(leftTemporary) < leftLimit){
        form.style.setProperty('--left', left + 'px');
        left = leftTemporary;
        mouseDownAt = e.clientX;
    }

}
//Footer
// Replace 'YOUR_API_KEY' with your actual Google Maps API key
const apiKey = 'YOUR_API_KEY';

function initMap() {
    const location = { lat: 37.7749, lng: -122.4194 }; // Change to your desired coordinates
    const map = new google.maps.Map(document.getElementById('map'), {
        center: location,
        zoom: 15, // Adjust the zoom level as needed
        disableDefaultUI: true, // Disabling default UI for a cleaner look
    });

    const marker = new google.maps.Marker({
        position: location,
        map: map,
        title: 'Your Location',
    });
}

// Load the Google Maps API with your API key
function loadScript() {
    const script = document.createElement('script');
    script.src = `https://maps.googleapis.com/maps/api/js?key=${apiKey}&callback=initMap`;
    script.defer = true;
    script.async = true;
    document.head.appendChild(script);
}

// Load the map when the page has fully loaded
window.addEventListener('load', loadScript);
