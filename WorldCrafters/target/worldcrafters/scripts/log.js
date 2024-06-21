// Funzione per chiudere la finestra modale
function closeModal() {
	window.location.href = "/worldcrafters/home";
}

//Invalidare la sessione lato client
function invalidateSession() {
	window.sessionStorage.clear();
}


//Controlli del form di login
window.addEventListener('DOMContentLoaded', function() {
    // Aggiungi un listener per l'evento di cambio nei campi di input
    $('input[data-validation]').on('input', function () {
        let validationType = $(this).data('validation');
        let value = $(this).val().trim();
        let isValid = false;

        // Aggiungi qui le tue regole di validazione per ogni campo
        switch (validationType) {
            case 'email':
                isValid = /^[^\s@]+@(?:[^\s@]+\.)+[^\s@]+$/.test(value); // Valida l'indirizzo email
                break;
            case 'password':
				isValid = value!=='';
				break;
            default:
                isValid = true; // Se non è specificato alcun tipo di validazione, è valido
        }

        // Aggiungi o rimuovi la classe 'error' in base alla validità
        if (isValid || value=='') {
            $(this).removeClass('error');
        } else {
            $(this).addClass('error');
        }
            
    });
});

//
window.addEventListener('DOMContentLoaded', function() {
	const emailInput = document.getElementById('email');
	const passwordInput = document.getElementById('password');
	const submitButton = document.getElementById('signin');
	const errorMessageEmail = document.getElementById('errorMessageEmail');
	
	emailInput.addEventListener('focusout', validateEmail);
	passwordInput.addEventListener('focusout', validatePassword);
	
	function validateEmail() {
		const emailPattern = /^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	  	const emailValue = emailInput.value;
	
	  	if(emailPattern.test(emailValue) || emailValue=='') {
			errorMessageEmail.innerHTML = '';
		} else {
			errorMessageEmail.innerHTML = 'Indirizzo email non valido';
		}
	  	  		
	  	if (emailPattern.test(emailValue)) {
			submitButton.disabled = false;
	  	} else {
	    	submitButton.disabled = true;
	  	}
	}
	
	//validatePassword utilizzata per riconoscere email anche dopo che la servlet ha impostato il value email
	function validatePassword() {
	  	const passwordValue = passwordInput.value;
		  		
	  	if (passwordValue!='') {
			submitButton.disabled = false;
	  	} else {
	    	submitButton.disabled = true;
	  	}
	}
	
});