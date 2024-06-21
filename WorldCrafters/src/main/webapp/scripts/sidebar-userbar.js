// FUNZIONE PER SIDEBAR CATEGORIE
let sidebarOpen = false;
function toggleSidebar() {
    let sidebar = document.getElementById("sidebar");
    let body = document.getElementsByTagName("body")[0];

    if(!sidebarOpen) {
        sidebar.style.left = "0px";
        sidebar.style.transition = "left 600ms";
        sidebarOpen = true;
        body.classList.add("sidebar-open");
    }
    else {
        sidebar.style.left = "-400px";
        sidebar.style.transition = "left 600ms";
        sidebarOpen=false;
        body.classList.remove("sidebar-open");
    }  
}

// FUNZIONE PER USERBAR
let userbarOpen = false;
function toggleUserbar() {
    let userbar = document.getElementById("userbar");
    let body = document.getElementsByTagName("body")[0];

    if(!userbarOpen) {
        userbar.style.display = "block";
        userbar.style.opacity = "1";
        userbarOpen = true;
        body.classList.add("userbar-open");
    }
    else {
        userbar.style.opacity = "0";
        userbar.style.display = "none";
        userbarOpen=false;
        body.classList.remove("userbar-open");
    }  
}


// FUNZIONE CHE CHIUDE TUTTE LE SIDEBAR
function closeAll() {

    if(sidebarOpen) {
        toggleSidebar();
    }
    
    if(userbarOpen) {
        toggleUserbar();
    }
}

// FUNZIONE PER LA CREAZIONE DELLE PAGINE DEI PRODOTTI IN BASE ALLA CATEGORIA CLICCATA NELLA SIDEBAR
function categoryClick(category) {
	
    let url = 'products?category=' + encodeURIComponent(category);
    window.location.href = url;
}