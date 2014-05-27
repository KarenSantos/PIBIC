/**
 * 
 */
function selectPlaylist(id) {

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