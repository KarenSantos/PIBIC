/**
 * 
 */

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

function searchVideo(keyword){
	
	$.ajax({
		type : "GET",
		url : "/search/" + keyword,
		data : "",
		success : function(results, textStatus, jqXHR) {
			mostrarResults(results);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("deu erro");
		}
	});
	
}

function go_get(){
	 var base_url = 'http://www.youtube.com/embed?listType=search&list=';
	 var search_field = document.getElementById('yourtextfield').value;
	 var target_url = base_url + search_field;
	 var ifr = document.getElementById('youriframe');
	 
	 var result = simplejson.load(urllib.urlopen(target_url));
	 alert(result);

	 if (search_field != "") {
		 ifr.src = target_url ;
	 }
	 
	
	 return false ;
}

function allowDrop(ev) {
	ev.preventDefault();
}

function drag(ev) {
	ev.dataTransfer.setData("Text",ev.target.id);
}

function drop(ev) {
	ev.preventDefault();
	var data=ev.dataTransfer.getData("Text");
	alert(data);
	ev.target.appendChild(document.getElementById(data));
}


