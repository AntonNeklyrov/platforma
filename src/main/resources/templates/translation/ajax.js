$(document).ready(function() {
    $("#leagueId").change(function() {
        sendAjaxRequest();
    });
});


function sendAjaxRequest() {
    var leagueId = $("#leagueId").val();
    $.get( "/platforma/translations/teams" + leagueId, function( data ) {
        $("homeTeam").empty();
        data.forEach(function(item, i) {
            var option = "<option value = " + item + ">" + item +  "</option>";
            $("#homeTeam").append(option);
        });
    });
}