// FOOTER
// Creazione carousel
document.addEventListener("DOMContentLoaded", function() {
    let elem = document.querySelector('.main-carousel');
	new Flickity( elem, {
		// options
		cellAlign: 'center',
		contain: true,
		wrapAround: true
	});
});