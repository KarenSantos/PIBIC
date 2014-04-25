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

		// $("#searchOptions").style.display = "block";
		document.getElementById("searchOptions").style.display = "block";
	 }
}

function adicionarMusica() {
	var musicGoTo = RadioHab();
	
	if (musicGoTo == null){
		alert("Selecione para onde vai a música adicionada.");
	} else {
		
		var APIKey = "AIzaSyDrBArU_WZxTHmQc62_YdktmhQsFZ4PnAg";
		
		var link = document.getElementById("link").value;
		var videoId = link.split("=")[1].split("&")[0];
		
		$.ajax({
			type : "GET",
			url : "https://www.googleapis.com/youtube/v3/videos?part=snippet&id=" + videoId + "&key=" + APIKey,
			datatype : "application/json",
			success : function(data) {
				alert("chegou no sucess");
				
				alert(data.items[0]);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("Ocorreu um erro. Tente mais tarde.");
			}
		});
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

function clearSearch() {
	document.getElementById('keyword').value = "";
	document.getElementById('searchiframe').src = "";
	document.getElementById("searchOptions").style.display = "none";
}

$("#keyword").keyup(function(event){
    if(event.keyCode == 13){
        $("#doSearch").click();
    }
});