function closeModal() {
	window.location.href = "/worldcrafters/home";
}

window.addEventListener('DOMContentLoaded', function() {
    // Aggiungi un listener per l'evento di cambio nei campi di input
    $('input[data-validation]').on('input', function () {
        let validationType = $(this).data('validation');
        let value = $(this).val().trim();
        let isValid = false;
        let password;

        // Aggiungi qui le tue regole di validazione per ogni campo
        switch (validationType) {
            case 'firstName':
                isValid = /^[A-Za-z\s]+$/.test(value); // Consente solo lettere e spazi
                break;
            case 'lastName':
                isValid = /^[A-Za-z\s]+$/.test(value); // Consente solo lettere e spazi
                break;
            case 'email':
                isValid = /^[^\s@]+@(?:[^\s@]+\.)+[^\s@]+$/.test(value); // Valida l'indirizzo email
                break;
            case 'password':
                // La password deve avere almeno 8 caratteri
                // Deve contenere almeno un numero e almeno un simbolo
                isValid = value.length >= 8 && /[A-Z]/.test(value) && /\d/.test(value) && /[!@#$%^&*]/.test(value);
                break;
            case 'confirmPassword':
                password = $('#password').val().trim();
                // Confronta la "Conferma Password" con la password
                isValid = value === password;
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
	  const firstNameInput = document.getElementById('firstName');
	  const lastNameInput = document.getElementById('lastName');
	  const passwordInput = document.getElementById('password');
	  const confirmPasswordInput = document.getElementById('confirmPassword');
	  const submitButton = document.getElementById('signup');
	  const errorMessageEmail = document.getElementById('errorMessageEmail');
	  const errorMessageFirstName = document.getElementById('errorMessageFirstName');
	  const errorMessageLastName = document.getElementById('errorMessageLastName');
	  const errorMessagePassword = document.getElementById('errorMessagePassword');
	  const errorMessageConfirmPassword = document.getElementById('errorMessageConfirmPassword');
	  const namePattern = /^[a-zA-Z ]+$/;
	  const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*?])[a-zA-Z0-9!@#$%^&*?]{8,}$/;
	
	  let allFieldsValid = false;
	
	  emailInput.addEventListener('focusout', validateEmail);
	  firstNameInput.addEventListener('focusout', validateFirstName);
	  lastNameInput.addEventListener('focusout', validateLastName);
	  passwordInput.addEventListener('focusout', validatePassword);
	  confirmPasswordInput.addEventListener('focusout', validateConfirmPassword);
	
	  function validateEmail() {
	    const emailPattern = /^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	    const emailValue = emailInput.value;
	
	    if (emailPattern.test(emailValue) || emailValue=='') {
	      errorMessageEmail.innerHTML = '';
	    } else {
	      errorMessageEmail.innerHTML = 'Indirizzo email non valido';
	    }
	
	    checkAllFieldsValid();
	  }
	
	  function validateFirstName() {
	    const firstNameValue = firstNameInput.value;
	
	    if (namePattern.test(firstNameValue) || firstNameValue=='') {
	      errorMessageFirstName.innerHTML = '';
	    } else {
	      errorMessageFirstName.innerHTML = 'Nome non valido, solo lettere e spazi';
	    }
	
	    checkAllFieldsValid();
	  }
	
	  function validateLastName() {
	    const lastNameValue = lastNameInput.value;
	
	    if (namePattern.test(lastNameValue) || lastNameValue=='') {
	      errorMessageLastName.innerHTML = '';
	    } else {
	      errorMessageLastName.innerHTML = 'Cognome non valido, solo lettere e spazi';
	    }
	
	    checkAllFieldsValid();
	  }
	
	  function validatePassword() {
	    const passwordValue = passwordInput.value;
	
	    if ((passwordValue.trim().length >= 8 && passwordPattern.test(passwordValue)) || passwordValue=='') {
	      errorMessagePassword.innerHTML = '';
	    } else {
	      errorMessagePassword.innerHTML = 'La password deve contenere almeno 8 caratteri, una maiuscola e un simbolo';
	    }
	
	    checkAllFieldsValid();
	  }
	  
	  function validateConfirmPassword() {
		  const passwordValue = passwordInput.value;
		  const confirmPasswordValue = confirmPasswordInput.value;
		
		  if (passwordValue === confirmPasswordValue || confirmPasswordValue=='') {
		    errorMessageConfirmPassword.innerHTML = '';
		  } else {
		    errorMessageConfirmPassword.innerHTML = 'Le password non coincidono';
		  }
		
		  checkAllFieldsValid();
		}
	
	  function checkAllFieldsValid() {
	    allFieldsValid =
	      emailInput.value.trim().length > 0 &&
	      firstNameInput.value.trim().length > 0 &&
	      lastNameInput.value.trim().length > 0 &&
	      passwordInput.value.trim().length >= 8;
	
	    submitButton.disabled = !allFieldsValid;
	  }
});


















