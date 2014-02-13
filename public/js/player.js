/**
 * 
 */

// Script para tocar o video sem ele aparecer. iFrame tem tamanho zero
// e aparece uma menssagem indicando que o iFrame foi chamado com sucesso.
					
var loadLinkH = document.getElementById("playlist"); // armazena o id do link
loadLinkH.onclick = loadFrame; // chama a função loadFrame quando clica no link
					
function loadFrame(e) { // função para criar o iFrame
	var containerHiden = document.getElementById("frameHiden"); // armazena o id da div onde vai aparecer o iFrame
	var containerFaixa = document.getElementById("player");
	e = e || window.event; 
						
	//adiciona o iFrame na div
	containerHiden.innerHTML = "<iframe width='0' height='0' src=\"" + loadLinkH.href + "\" frameborder='0' allowfullscreen >";
	document.getElementById("faixaPlayer").style.display = "block";
	//containerFaixa.innerHTML = "<div id='faixaPlayer'></div>"; 


	
	//chama a função stopEvent
	stopEvent( e ); 
}

//A funcao stopEvent abaixo inpede a ação primaria do tag <a>.
//Assim o link funciona mesmo se o Javascript estiver desativado.

function stopEvent( e ) {
	e = e || window.event; 
	
	e.cancelBubble = true; 
	e.returnValue = false; 

	if( e.stopPropagation ) { 
	 e.stopPropagation(); 
	 e.preventDefault(); 
	} 
	return false; 
} 

function barra(playlist){
	return "<div id='faixaPlayer'>" 
}