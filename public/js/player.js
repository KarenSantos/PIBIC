var tag = document.createElement('script');
tag.src = "//www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

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

	var videoIDs = [];
	var player, currentVideoId = 0;

	for (var i = 0; i < playlist.musicas.length; i++){
		videoIDs.push(playlist.musicas[i].link);
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
		event.target.loadVideoById(videoIDs[currentVideoId]);
	}

	function onPlayerStateChange(event) {
		if (event.data == YT.PlayerState.ENDED) {
			currentVideoId++;
			if (currentVideoId < videoIDs.length) {
				player.loadVideoById(videoIDs[currentVideoId]);
			}
		}
	}
	
	onYouTubeIframeAPIReady();

	// document.getElementById("video").innerHTML = "<iframe width='300'
	// height='59' " +
	// "src='//www.youtube.com/embed/" + data.musicas[0].link +
	// "?rel=0&amp;fs=0&amp;autoplay=1' allowfullscreen='false'
	// frameborder='0'></iframe>";

	document.getElementById("playlistPlayer").innerHTML = playlist.nome;
	document.getElementById("musicaAtual").innerHTML = '"'
			+ playlist.musicas[0].nome + '"' + "<br>"
			+ playlist.musicas[0].artista;
}

function closePlaylist() {
	document.getElementById("faixaPlayer").style.display = "none";
	// $('#faixaPlayer').style.display = "none";
}