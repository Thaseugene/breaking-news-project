$(document).ready(function() {
    $("#imageUrl").on("change", function() {
        $("#preview").attr("src", $(this).val());
    });
});

