         function closePreview(){
    $('.image-preview').popover('hide'); 
}

$(document).ready(function(){
    // Set the close button
    var closebtn = $('<button/>', {
        type:"button",
        text: 'x',
        id: 'close-preview',
        style: 'font-size: initial;',
    });
    closebtn.attr("class","close pull-right");
    closebtn.attr("onclick","closePreview();");
    // Set the popover default content
    $('.image-preview').popover({
        trigger:'manual',
        html:true,
        title: "<strong style='color:black;'>Vista Previa</strong>"+$(closebtn)[0].outerHTML,
        content: 'Loading...',
        placement:'bottom'
    });
    // Set the clear onclick function
    $('.image-preview-clear').click(function(){
        $('.image-preview').popover('hide');
        $('.image-preview-filename').val("");
        $('.image-preview-clear').hide();
        $('.image-preview-input input:file').val("");
        $(".image-preview-input-title").text("Subir foto"); 
    });
});

$(function() {
    $(".image-preview-input input:file").change(function (){
        // Create the preview image 
        var img = $('<img/>', {
            id: 'dynamic',
            width:250,
            height:230
        });      
        var file = this.files[0];
        var reader = new FileReader();
        reader.onload = function (e) {
            $(".image-preview-input-title").text("Cambiar");
            $(".image-preview-clear").show();
            $(".image-preview-filename").val(file.name);            
            // Set preview image into the popover data-content
            img.attr('src', e.target.result);
            $(".image-preview").attr("data-content",$(img)[0].outerHTML).popover("show");
        }        
        reader.readAsDataURL(file);
    });  
});


            
