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

$("#keyword").keyup(function(event){
    if(event.keyCode == 13){
        $("#doSearch").click();
    }
});

function requisitarVideo() {
	
	var link = document.getElementById("link").value;
	if (link == ""){
		mostrarErro("Insira um link.");
	
	} else if (!isValidURL(link)){
		mostrarErro("Insira um link de vídeo do YouTube válido.")
	} else {
		
		var musicGoTo = RadioHab();
		if (musicGoTo == null){
			mostrarErro("Selecione para onde vai a música adicionada.");
		
		} else {
			var APIKey = "AIzaSyDrBArU_WZxTHmQc62_YdktmhQsFZ4PnAg";
			var videoId = link.split("=")[1].split("&")[0];

			if (isAlreadyAdded(videoId)){
				mostrarErro("Este vídeo já foi adicionado.");
			} else {

				$.ajax({
					type : "GET",
					url : "https://www.googleapis.com/youtube/v3/videos?part=snippet&id=" + videoId + "&key=" + APIKey,
					datatype : "application/json",
					success : function(data) {
						
						var videoTitle = data.items[0]['snippet']['title'];
						
						if (musicGoTo == "2"){
							if(numMusT > 0){
								var trocar = confirm("Você já adicionou uma música de transição, deseja substituí-la?");
								if (trocar){
									enviarMusicaProjeto(musicGoTo, videoTitle, videoId);
								}
	
							} else {
								enviarMusicaProjeto(musicGoTo, videoTitle, videoId);
							}
	
						} else  {
							enviarMusicaProjeto(musicGoTo, videoTitle, videoId);
						}
						
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						mostrarErro("O link inserido não corresponde a um vídeo do youtube. Insira um link válido.");
					}
				});
			}
			document.getElementById("link").value = ""; //limpando o text input do link
		}
	}
}

function isValidURL(url){
    var RegExp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;

    if(RegExp.test(url)){
        return true;
    }else{
        return false;
    }
} 

function RadioHab() {
	var musicGoTo = null;
	var option = document.goTo.radioGoTo;

	for(var i=0; i < option.length; i++) {
		if(option[i].checked) {
			musicGoTo = option[i].value;
			option[i].checked = false;
			break;
		}
	}
	return musicGoTo;
}

function allowDrop(ev){ 
	ev.preventDefault(); 
}

function drag(ev) {
	ev.dataTransfer.setData("Text",ev.target.id);
}

function drop(ev){ 
	ev.preventDefault(); 

	var data = ev.dataTransfer.getData("Text"); 

	alert("link: " + data + "\n enviado para: ");
	
//	ev.target.appendChild(document.getElementById(data));
	
}

function enviarMusicaProjeto(lugar, nome, id){
	$.ajax({
		type : "GET",
		url : "/add_" + lugar + "/" + nome + "/" + id,
		data : "",
		success : function() {
			adicionarMusica(lugar, nome, id);
			fecharErro();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			mostrarErro("Este vídeo apresentou problemas, melhor tentar outro.");
		}
	});
}

function adicionarMusica(lugar, nome, id){
	if (lugar == "1"){
		numMusP1++;
		var musica = "musica" + numMusP1;
		paisagem1[musica] = [nome, id];

	} else if (lugar == "2") {
		numMusT++;
		transicao = [nome, id];

	} else if (lugar == "3") {
		numMusP2++;
		var musica = "musica" + numMusP2;
		paisagem2[musica] = [nome, id];
	}
	exibirMusicas();
}

function exibirMusicas() {
	document.getElementById("p1music").innerHTML = "";
	for (var p1mus in paisagem1){
		document.getElementById("p1music").innerHTML += "<button type='button'>" + paisagem1[p1mus][0]
		+ " &nbsp;&nbsp;<a class='glyphicon glyphicon-remove remover' onclick=\"removerMusicaProjeto('" + paisagem1[p1mus][1] + "')\"></a></button><br>";
	}
	
	document.getElementById("tmusic").innerHTML = "";
	if (transicao.length > 0){
		document.getElementById("tmusic").innerHTML = "<button type='button'>" + transicao[0]
		+ " &nbsp;&nbsp;<a class='glyphicon glyphicon-remove remover' onclick=\"removerMusicaProjeto('" + transicao[1] + "')\"></a></button><br>";
	}
	
	document.getElementById("p2music").innerHTML = "";
	for (var p2mus in paisagem2){
		document.getElementById("p2music").innerHTML += "<button type='button'>" + paisagem2[p2mus][0]
		+ " &nbsp;&nbsp;<a class='glyphicon glyphicon-remove remover' onclick=\"removerMusicaProjeto('" + paisagem2[p2mus][1] + "')\"></a></button><br>";
	}
		
}

function removerMusicaProjeto(id) {
	$.ajax({
		type : "GET",
		url : "/remove/" + id,
		data : "",
		success : function() {
			removerMusica(id);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			mostrarErro("Ocorreu um erro, tente mais tarde.");
		}
	});
}

function removerMusica(id){
	
	
	if(transicao[1] == id){
		transicao = [];
		numMusT = 0;
		exibirMusicas();
		return true;
	}
	var musTemp = {};
	var numTemp = 0;

	for (var p1mus in paisagem1){
		if(paisagem1[p1mus][1] == id){
			for (var p1 in paisagem1){
				if(paisagem1[p1][1] != id){
					numTemp++;
					var musica = "musica" + numTemp;
					musTemp[musica] = [paisagem1[p1][0], paisagem1[p1][1]];
				}
			}
			paisagem1 = clone(musTemp);
			numMusP1--;
			exibirMusicas();
			return true;
		}
	}
		
	for (var p2mus in paisagem2){
		if(paisagem2[p2mus][1] == id){
			for (var p2 in paisagem2){
				if(paisagem2[p2][1] != id){
					numTemp++;
					var musica = "musica" + numTemp;
					musTemp[musica] = [paisagem2[p2][0], paisagem2[p2][1]];
				}
			}
			paisagem2 = clone(musTemp);
			numMusP2--;
			exibirMusicas();
			return true;
		}
	}
}

function isAlreadyAdded(id){
	for (var p1mus in paisagem1){
		if (paisagem1[p1mus][1] === id){
			return true;
		}
	}
		
	if (transicao[1] === id){
		return true;
	}
	
	for (var p2mus in paisagem2){
		if (paisagem2[p2mus][1] === id){
			return true;
		}
	}
	return false;
}

function mostrarErro(erro){
	document.getElementById("errorText").innerHTML = erro + " &nbsp;&nbsp;";
	document.getElementById("error").style.display = "block";
}

function fecharErro(){
	document.getElementById("errorText").innerHTML = "";
	document.getElementById("error").style.display = "none";
	document.getElementById("error2").style.display = "none";
	
}

function fecharSuccess(){
	document.getElementById("success").style.display = "none";
}

function proximo(){
	
	if (numMusP1 < 3 || numMusP2 < 3) {
		mostrarErro("Cada paisagem deve ter no mínimo 3 músicas.");
	} else if (numMusT == 0){
		mostrarErro("Adicione uma música de transição.");
	} else {
		
		document.getElementById('searchiframe').src = "";
		
		document.getElementById("genero1").style.display = "block";
		document.getElementById("genero2").style.display = "block";
		document.getElementById("info").style.display = "block";
		
		document.getElementById("tempSongs").style.display = "none";
		document.getElementById("botao1").style.display = "none";
		document.getElementById("searchForm").style.display = "none";
		document.getElementById("searchOptions").style.display = "none";
	}
}

function voltar(){
	document.getElementById("genero1").style.display = "none";
	document.getElementById("genero2").style.display = "none";
	document.getElementById("info").style.display = "none";
	
	document.getElementById("tempSongs").style.display = "block";
	document.getElementById("botao1").style.display = "block";
	document.getElementById("searchForm").style.display = "block";
	document.getElementById("searchOptions").style.display = "block";
}

function clone(obj) {
	// Handle the 3 simple types, and null or undefined
    if (null == obj || "object" != typeof obj) return obj;

    // Handle Date
    if (obj instanceof Date) {
        var copy = new Date();
        copy.setTime(obj.getTime());
        return copy;
    }

    // Handle Array
    if (obj instanceof Array) {
        var copy = [];
        for (var i = 0, len = obj.length; i < len; i++) {
            copy[i] = clone(obj[i]);
        }
        return copy;
    }

    // Handle Object
    if (obj instanceof Object) {
        var copy = {};
        for (var attr in obj) {
            if (obj.hasOwnProperty(attr)) copy[attr] = clone(obj[attr]);
        }
        return copy;
    }
    throw new Error("Unable to copy obj! Its type isn't supported.");
}