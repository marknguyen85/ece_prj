var coords = [[23, 23], [34, 84], [34, 54], [56, 76], [103, 103]];

$(coords).each(function(i){
    var pos = this;
    var dot = $('<img src="../img/carrier_path/marker.png" />');
    dot.css({
        position: 'absolute',
        left: pos[0] + "px",
        top: pos[1] + "px"
    });
    $("#pointer_div").append(dot);
});​
