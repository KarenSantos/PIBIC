/**
 * 
 */
function closePlaylist() {
	document.getElementById("faixaPlayer").style.display = "none";
}

function openPlaylist(id) {
	
	document.getElementById("faixaPlayer").style.display = "block";
	alert("playlist id: " + id);
	
	$.ajax({
		  type: "GET",
		  url: "/getPlaylist/" + id,
		  data: "",
		  success: function(data, textStatus, jqXHR){
//		        window.location = "/";
			  console.log(" testando 123");
			  console.log(data.nome);
		  },
		  error: function(XMLHttpRequest, textStatus, errorThrown) {
			  var ind = XMLHttpRequest.responseText.indexOf("Exception:");
			  var res = XMLHttpRequest.responseText.substring(ind +11 , ind + 500);
			  var ind2 = res.indexOf("]");
			  var res2 = res.substring(0, ind2);
		      alert(res2);
		  }
	});
	
}

//// Script para tocar o video sem ele aparecer. iFrame tem tamanho zero
//					
//var loadLinkH = document.getElementById("playlist"); // armazena o id do link
//loadLinkH.onclick = loadFrame; // chama a função loadFrame quando clica no link
//					
//function loadFrame(e) { // função para criar o iFrame
//	var containerHiden = document.getElementById("frameHiden"); // armazena o id da div onde vai aparecer o iFrame
//	var containerFaixa = document.getElementById("player");
//	e = e || window.event; 
//						
//	//adiciona o iFrame na div
//	containerHiden.innerHTML = "<iframe width='0' height='0' src=\"" + loadLinkH.href + "\" frameborder='0' allowfullscreen >";
//	document.getElementById("faixaPlayer").style.display = "block";
//	//containerFaixa.innerHTML = "<div id='faixaPlayer'></div>"; 
//	
//	//chama a função stopEvent
//	stopEvent( e ); 
//}
//
////A funcao stopEvent abaixo inpede a ação primaria do tag <a>.
////Assim o link funciona mesmo se o Javascript estiver desativado.
//
//function stopEvent( e ) {
//	e = e || window.event; 
//	
//	e.cancelBubble = true; 
//	e.returnValue = false; 
//
//	if( e.stopPropagation ) { 
//	 e.stopPropagation(); 
//	 e.preventDefault(); 
//	} 
//	return false; 
//} 