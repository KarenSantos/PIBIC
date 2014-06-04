/**
 * 
 */
function selectPlaylist(id) {

	$.ajax({
		type : "GET",
		url : "/getPlaylist/" + id,
		data : "",
		success : function(playlist, textStatus, jqXHR) {
			showPlaylist(playlist);
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}

function showPlaylist(playlist){
	
	document.getElementById("nomePlaylistView").innerHTML = playlist.nome;
	document.getElementById("imagemPlaylistView").innerHTML = "<img src='/assets/img/playImgs/" + playlist.imagem + "' alt='Não foi possível carregar imagem'>";
	
	document.getElementById("songs1").innerHTML = "";
	for (var mus1 in playlist.primPaisagem){
		document.getElementById("songs1").innerHTML += "<li>" + playlist.primPaisagem[mus1].nome + "</li>";
	}
	
	document.getElementById("songs2").innerHTML = "<li>" + playlist.transicao.nome + "</li>";
	
	document.getElementById("songs3").innerHTML = "";
	for (var mus3 in playlist.segPaisagem){
		document.getElementById("songs3").innerHTML += "<li>" + playlist.segPaisagem[mus3].nome + "</li>";
	}
	
}