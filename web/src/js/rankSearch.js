// data: [
//   {
//     name: nguyen van a,
//     coding: 56%,
//     database: 12%,
//     iq: 1%,
//     attitute: 2%,
//     language: 3%
//   },
//   {
//     name: nguyen van b,
//     coding: 56%,
//     database: 12%,
//     iq: 1%,
//     attitute: 2%,
//     language: 3%
//   }
// ]
// require("charts.js");
(function ($, appName) {
    /// <param name="appName">namespace of application.</param>
    'use strict';
    var getRank = () => {
        var url = '/getRanking?id=2'
        serviceInvoker.get(url, {}, {
            error: function (response) {
                if (response.responseJSON && response.responseJSON.ErrorCode > 0) {
                }
                else {
                }
            },
            success: function (response) {
                Common.storeToken('xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx');
                Common.redirect('/index.html');
            }
        });
    }
    $.getJSON(url , function(data) {
    var tbl_body = document.createElement("tbody");
    var odd_even = false;
    $.each(data, function() {
        var tbl_row = tbl_body.insertRow();
        tbl_row.className = odd_even ? "odd" : "even";
        $.each(this, function(k , v) {
            var cell = tbl_row.insertCell();
            cell.appendChild(document.createTextNode(v.toString()));
        })
        odd_even = !odd_even;
    })
    $("#target_table_id").appendChild(tbl_body);
  });
