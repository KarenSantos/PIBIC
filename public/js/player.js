var tag = document.createElement('script');
tag.src = "//www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
var player;

function openPlaylist(id) {

	$.ajax({
		type : "GET",
		url : "/getPlaylist/" + id,
		data : "",
		success : function(playlist, textStatus, jqXHR) {
			playlistStart(playlist);
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});

}

function playlistStart(playlist) {
	
	document.getElementById("faixaPlayer").style.display = "block";
	document.getElementById("nomePlaylist").innerHTML = playlist.nome;

	var musicasPlaylist = [];
	var videoIDs = [];
	var currentSong = 0;

	for (var i = 0; i < playlist.musicas.length; i++){
		musicasPlaylist.push(playlist.musicas[i]);
		videoIDs.push(playlist.musicas[i].id);
	}
	
	function onYouTubeIframeAPIReady() {
		player = new YT.Player('player', {
			height : '59',
			width : '300',
			events : {
				'onReady' : onPlayerReady,
				'onStateChange' : onPlayerStateChange
			}
		});
	}

	function onPlayerReady(event) {
		event.target.loadVideoById(videoIDs[currentSong]);
		
		document.getElementById("musicaAtual").innerHTML = '"' + musicasPlaylist[currentSong].nome + '"';
		document.getElementById("artistaAtual").innerHTML = musicasPlaylist[currentSong].artista;
		document.getElementById("proxima").innerHTML = "Próxima: " + musicasPlaylist[currentSong+1].nome;
	}

	function onPlayerStateChange(event) {
		if (event.data == YT.PlayerState.ENDED) {
			currentSong++;
			if (currentSong < videoIDs.length) {
				player.loadVideoById(videoIDs[currentSong]);
				
				document.getElementById("musicaAtual").innerHTML = '"' + musicasPlaylist[currentSong].nome + '"';
				document.getElementById("artistaAtual").innerHTML = musicasPlaylist[currentSong].artista;
				
				if (currentSong++ < videoIDs.length) {
					document.getElementById("proxima").innerHTML = "Próxima: " + musicasPlaylist[currentSong+1].nome;
				}
				
			}
		}
	}
	
	if(player == null){
		onYouTubeIframeAPIReady();
	} else {
		player.loadVideoById(videoIDs[currentSong]);
		document.getElementById("musicaAtual").innerHTML = '"' + musicasPlaylist[currentSong].nome + '"';
		document.getElementById("artistaAtual").innerHTML = musicasPlaylist[currentSong].artista;
		document.getElementById("proxima").innerHTML = "Próxima: " + musicasPlaylist[currentSong+1].nome;
	}
	
}

function closePlaylist() {
	player.stopVideo();

	if (player == null){
		alert("conseguiu fazer player nulo");
	}
	document.getElementById("faixaPlayer").style.display = "none";
	// $('#faixaPlayer').style.display = "none";
}

function murderEvent(evt) {
	 evt.cancel=true;
	 evt.returnValue=false;
	 evt.cancelBubble=true;
	 if (evt.stopPropagation) evt.stopPropagation();
	 if (evt.preventDefault) evt.preventDefault();
	 return false;
}