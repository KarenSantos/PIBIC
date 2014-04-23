/**
 * 
 */

var paisagem1;
var paisagem2;
var transicao;

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
	 }
	 
	 document.getElementById("opcaoGoTo").style.display = "block";
	 
	 return false ;
}

function adicionarMusica() {
	RadioHab();
	
	// tentar pegar a URL do video atual
//	var link = document.getElementById('searchiframe').getVideoUrl()
//	alert(link);
	
	alert("Text copied was: " + window.getSelection());
	
}

function RadioHab() {
	var musicGoTo;
	var option = document.goTo.radioGoTo;

	for(var i=0; i < option.length; i++) {
		if(option[i].checked) {
			musicGoTo = option[i].value;
			break;
		}
	}
	alert(musicGoTo);
}

function allowDrop(ev){ 
	ev.preventDefault(); 
}

function drop(ev, goTo){ 
	ev.preventDefault(); 

	var data = ev.dataTransfer.getData("Text"); 

	alert("link: " + data + "\n enviado para: " + goTo.id);
	
} 