var tag = document.createElement('script');
tag.src = "//www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
var player;

//caso o player seja chamado na pagina de playlists o display de detalhes de playlist precisa ser diminuido
function shortViewHeight(id){
	document.getElementById("playlistsView").style.height = "calc(100% - 130px)";
	openPlaylist(id);
}

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
	
//	alert(player == null);
//	player = null;
//	alert(player == null);
	
	document.getElementById("faixaPlayer").style.display = "block";
	document.getElementById("imagemPlaylist").innerHTML = "<img src='/assets/img/playImgs/" + playlist.imagem + "' width='100%' height='100%' alt='Não foi possível carregar imagem'>";
	document.getElementById("nomePlaylist").innerHTML = playlist.nome;
	document.getElementById("generos").innerHTML = "De \"" + playlist.primGenero + "\" à \"" + playlist.segGenero + "\"";

	var videoTitles = [];
	var videoIDs = [];
	var currentSong = 0;

	listSongs(playlist, videoTitles, videoIDs);
//	alert(videoTitles + "\n\n" + videoIDs + "\n\n" + currentSong)
	
	function onYouTubeIframeAPIReady() {
		player = new YT.Player('player', {
			height : '63',
			width : '300',
			events : {
				'onReady' : onPlayerReady,
				'onStateChange' : onPlayerStateChange
			}
		});
	}

	function onPlayerReady(event) {
		event.target.loadVideoById(videoIDs[currentSong]);
		document.getElementById("musicaAtual").innerHTML = '"' + videoTitles[currentSong] + '"';
	}

	function onPlayerStateChange(event) {
		if (event.data == YT.PlayerState.ENDED) {
			currentSong++;
			if (currentSong < videoIDs.length) {
				player.loadVideoById(videoIDs[currentSong]);
				document.getElementById("musicaAtual").innerHTML = '"' + videoTitles[currentSong] + '"';
			}
		}
	}
	
	if(player == null){
		onYouTubeIframeAPIReady();
	} else {
		player.loadVideoById(videoIDs[currentSong]);
		document.getElementById("musicaAtual").innerHTML = '"' + videoTitles[currentSong] + '"';
	}
}

//caso o player seja fechado na pagina de playlists o display de detalhes de playlist precisa voltar ao normal
function longViewHeight(){
	document.getElementById("playlistsView").style.height = "calc(100% - 60px)";
	closePlaylist();
}

function closePlaylist() {
	player.stopVideo();
	document.getElementById("faixaPlayer").style.display = "none";
}

function listSongs(playlist, titleList, idList){
	
	for (var i = 0; i < playlist.primPaisagem.length; i++){
		titleList.push(playlist.primPaisagem[i].nome);
		idList.push(playlist.primPaisagem[i].id);
	}
	
	titleList.push(playlist.transicao.nome);
	idList.push(playlist.transicao.id);
	
	for (var i = 0; i < playlist.segPaisagem.length; i++){
		titleList.push(playlist.segPaisagem[i].nome);
		idList.push(playlist.segPaisagem[i].id);
	}
}