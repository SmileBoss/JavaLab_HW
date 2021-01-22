const $body = $(document.body);
const $btnMenu = $('.menu-toggle');
$btnMenu.click(function() {
    $body.toggleClass('menu-open');
});

const btnCloseBar = document.querySelector('.js-close-bar');
const btnOpenBar = document.querySelector('.js-open-bar');
const searchBar = document.querySelector('.searchbar');

btnOpenBar.addEventListener('click', () => {
    searchBar.classList.add('bar--is-visible');
});

btnCloseBar.addEventListener('click', () => {
    searchBar.classList.remove('bar--is-visible');
});