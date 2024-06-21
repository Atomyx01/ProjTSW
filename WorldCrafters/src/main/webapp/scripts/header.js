document.addEventListener("DOMContentLoaded", function() {
	
	const searchInput = document.getElementById('searchInput');
	const searchButton = document.getElementById('searchButton');
	const searchResults = document.getElementById('searchResults');
	const maxResults = 10; // Imposta il numero massimo di risultati da mostrare

 	searchButton.addEventListener('click', performSearch);
  	searchInput.addEventListener('input', updateSearchResults);
  	document.addEventListener('click', hideSearchResults);
  	searchInput.addEventListener('keydown', function (event) {
  		if (event.key === 'Enter') {
			performSearch();
  		}
	});

  	function performSearch() {
		const searchText = searchInput.value.toLowerCase();
	  
	  	if (searchText.trim() === '') {
	    	searchResults.style.display = 'none';
	    	return;
	  	}
	  	const url = `/worldcrafters/products?search=${encodeURIComponent(searchText)}`;
	  	console.log(url);

	  	// Reindirizza alla pagina di ricerca
	  	window.location.href = url;
	}


	function updateSearchResults() {
	  	const searchText = searchInput.value.toLowerCase();
	
	  	if (searchText.trim() === '') {
	    	searchResults.style.display = 'none';
	    	return;
	    }
	
	  	fetch(`/worldcrafters/api/search?q=${encodeURIComponent(searchText)}`)
	    .then(response => {
	    	if (!response.ok) {
	        	throw new Error('Errore nella richiesta');
	      	}
	      	return response.json();
	    })
	    .then(data => {
	      	const filteredResults = data.filter(item => typeof item.name === 'string' && item.name.toLowerCase().includes(searchText));
	      	const limitedResults = filteredResults.slice(0, maxResults);
	      	displayResults(limitedResults);
	    })
	    .catch(error => {
	      console.error('Errore durante la richiesta dei dati:', error);
	    });
	}


	function displayResults(results) {
	  	if (results.length > 0 && searchInput.value.trim() !== '') {
	    	searchResults.innerHTML = '';
	    	results.forEach(result => {
		      	const li = document.createElement('li');
		      	li.textContent = result.name; // Mostra solo il campo "name" dell'oggetto JSON
		      	li.onclick = () => {
		        	location.href = `/worldcrafters/product?id=${result.id}`;
				};
	      		searchResults.appendChild(li);
	    	});
		searchResults.style.display = 'block';
	  	} else {
	    	searchResults.style.display = 'none';
	  	}
	}

  	function hideSearchResults(event) {
    	if (event.target !== searchInput && event.target !== searchButton) {
        	searchResults.style.display = 'none';
    	}
  	}
});