<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="it">
<head>
    <title>WorldCrafters</title>
    <link rel="stylesheet" type="text/css" href="styles/sidebar.css">
    <script src="scripts/sidebar-userbar.js"></script>
</head>
<body>
	
	<div id="sidebar">
        
	    <i onclick="toggleSidebar()" class="fa fa-times" aria-hidden="true"></i>
	    <h1>WorldCrafters</h1>
        
        <ul>
        	<li class="category"><button onclick="categoryClick('arte')">Arte </button><i class="fa fa-chevron-down" aria-hidden="true"></i></li>
				<li class="subcategory"><button onclick="categoryClick('fotografia')">Fotografia</button></li>
				<li class="subcategory"><button onclick="categoryClick('pittura')">Pittura</button></li>
				<li class="subcategory"><button onclick="categoryClick('scultura')">Scultura</button></li>
				<li class="subcategory"><button onclick="categoryClick('vetro')">Vetro<br><br></button></li>
				
			<li class="category"><button onclick="categoryClick('abbigliamento')">Abbigliamento </button><i class="fa fa-chevron-down" aria-hidden="true"></i></li>
				<li class="subcategory"><button onclick="categoryClick('abbigliamento_uomo')">Uomo</button></li>
				<li class="subcategory"><button onclick="categoryClick('abbigliamento_donna')">Donna</button></li>
				<li class="subcategory"><button onclick="categoryClick('abbigliamento_bambino')">Bambino</button></li>
				<li class="subcategory"><button onclick="categoryClick('borse')">Borse<br><br></button></li>
			
			<li class="category"><button onclick="categoryClick('gioielli')">Gioielli </button><i class="fa fa-chevron-down" aria-hidden="true"></i></li>
				<li class="subcategory"><button onclick="categoryClick('anelli')">Anelli</button></li>
				<li class="subcategory"><button onclick="categoryClick('bracciali')">Bracciali</button></li>
				<li class="subcategory"><button onclick="categoryClick('collane')">Collane</button></li>
				<li class="subcategory"><button onclick="categoryClick('orecchini')">Orecchini<br><br></button></li>
			
			<li class="category"><button onclick="categoryClick('intrattenimento')">Intrattenimento </button><i class="fa fa-chevron-down" aria-hidden="true"></i></li>
				<li class="subcategory"><button onclick="categoryClick('cinema')">Cinema</button></li>
				<li class="subcategory"><button onclick="categoryClick('libri')">Libri</button></li>
				<li class="subcategory"><button onclick="categoryClick('musica')">Musica</button></li>
				<li class="subcategory"><button onclick="categoryClick('giochi')">Giochi e giocattoli<br><br></button></li>
			
			<li class="category"><button onclick="categoryClick('accessori')">Accessori<br><br></button></li>
			<li class="category"><button onclick="categoryClick('casa')">Casa e arredi<br><br></button></li>
			<li class="category"><button onclick="categoryClick('collezione')">Oggetti da collezione<br><br></button></li>
			<li class="category"><button onclick="categoryClick('elettronica')">Elettronica e accessori<br><br></button></li>
			<li class="category"><button onclick="categoryClick('animali_domestici')">Animali domestici<br><br></button></li>
			<li class="category"><button onclick="categoryClick('vintage')">Vintage<br><br></button></li>

        </ul>
    </div>
    
</body>
</html>