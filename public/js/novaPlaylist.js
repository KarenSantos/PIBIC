/**
 * 
 */

var paisagem1 = {};
var paisagem2 = {};
var transicao = [];
var numMusP1 = 0;
var numMusP2 = 0;
var numMusT = 0;

function skip(){
	document.getElementById("bskip").style.display = "none";
	document.getElementById("bnext").style.display = "none";
	document.getElementById("bcriar").style.display = "block";
}

function goHome(){
	alert("chegou na funcao");
	$.ajax({
		type : "GET",
		url : "/",
		data : "",
		success : function() {
			window.location = "/" + novoPeriodo;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("deu erro");
		}
	});
}

function go_get() {
	 var base_url = 'http://www.youtube.com/embed?listType=search&list=';
	 var search_field = document.getElementById('keyword').value;
	 var target_url = base_url + search_field;
	 var ifr = document.getElementById('searchiframe');
	 
	 if (search_field != "") {
		 ifr.src = target_url ;

		document.getElementById("searchOptions").style.display = "block";
	 }
}

function adicionarMusica() {
	
	var link = document.getElementById("link").value;
	if (link == ""){
		alert("Insira um link válido.");
	
	} else {
		var musicGoTo = RadioHab();
		if (musicGoTo == null){
			alert("Selecione para onde vai a música adicionada.");
		
		} else {
			
			var APIKey = "AIzaSyDrBArU_WZxTHmQc62_YdktmhQsFZ4PnAg";
			var videoId = link.split("=")[1].split("&")[0];
			
			$.ajax({
				type : "GET",
				url : "https://www.googleapis.com/youtube/v3/videos?part=snippet&id=" + videoId + "&key=" + APIKey,
				datatype : "application/json",
				success : function(data) {
					
					var videoTitle = data.items[0]['snippet']['title'];
					
					if (musicGoTo == "1"){
						numMusP1++;
						var musica = "musica" + numMusP1;
						paisagem1[musica] = [videoTitle, videoId];
					
					} else if (musicGoTo == "2") {
						
						if(numMusT > 0){
							var trocar = confirm("Você já adicionou uma música de transição, deseja substituí-la?");
							if (trocar){
								transicao = [videoTitle, videoId];
							}
							
						} else {
							numMusT++;
							transicao = [videoTitle, videoId];
						}
						
					
					} else {
						numMusP2++;
						var musica = "musica" + numMusP2;
						paisagem2[musica] = [videoTitle, videoId];
					}
					
					document.getElementById("link").value = ""; //limpando o text input do link
					exibirMusicas();
									
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("Ocorreu um erro. Tente mais tarde.");
				}
			});
		}
	}
}

function RadioHab() {
	var musicGoTo = null;
	var option = document.goTo.radioGoTo;

	for(var i=0; i < option.length; i++) {
		if(option[i].checked) {
			musicGoTo = option[i].value;
			break;
		}
	}
	return musicGoTo;
}

function allowDrop(ev){ 
	ev.preventDefault(); 
}

function drop(ev, goTo){ 
	ev.preventDefault(); 

	var data = ev.dataTransfer.getData("Text"); 

	alert("link: " + data + "\n enviado para: " + goTo.id);
	
}

function exibirMusicas() {
	
	document.getElementById("p1music").innerHTML = "";
	for (var p1mus in paisagem1){
		document.getElementById("p1music").innerHTML += "<button type='button'>" + paisagem1[p1mus][0] + "</button>";
	}
	
	if (transicao.length > 0){
		document.getElementById("tmusic").innerHTML = "<button type='button'>" + transicao[0] + "</button>";
	}
	
	document.getElementById("p2music").innerHTML = "";
	for (var p2mus in paisagem2){
		document.getElementById("p2music").innerHTML += "<button type='button'>" + paisagem2[p2mus][0] + "</button>";
	}
		
}

function proximo(){
	
	alert("entrou no proximo");

	var genero1 = document.getElementById("genero1").value;
	var genero2 = document.getElementById("genero2").value;
	var nome = document.getElementById("playlistName").value;
	
	//fazer o upload da imagem
	var imagem = document.getElementById("playlistImg").value;;
	
	var playlistInfo = [nome, imagem, genero1, genero2];
	
	$.ajax({
		type : "POST",
		url : "/nova/" + playlistInfo,
		data : "",
		success : function() {
			window.location = "/";
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Não foi possível salvar sua playlist no momento, favor tentar mais tarde.");
		}
	});
	
//	if (numMusP1 >= 3 && numMusP2 >=3){
//		if (numMusT == 1){
//			alert("ok vc pode ir");
//		} else {
//			alert("Insira uma música de transição entre as paisagens.")
//		}
//	} else {
//		alert("Suas paisagens devem ter pelo menos 3 músicas.")
//	}
	
	
}

$("#keyword").keyup(function(event){
    if(event.keyCode == 13){
        $("#doSearch").click();
    }
});