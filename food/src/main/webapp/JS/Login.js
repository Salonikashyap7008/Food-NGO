


const container = document.getElementById('container');
const loader = document.getElementById('loader');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});



container.style.display = 'none';
setTimeout(() => {
container.style.display = 'block';
loader.style.display = 'none';
},100)

