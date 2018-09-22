
var myID = 2;
var myData = null;
var dataCompare = null;
var allData = null;
(function ($, appName) {
    /// <param name="appName">namespace of application.</param>
    'use strict';
    //get ranking
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
    //get search nhan vien result
    var searchEmployee = () => {
        // var formData = {
        //     page: 1,
        //     key: $('#mySearchBar').val(),
        // }
        var keySearch = $('#mySearchBar').val();
        var url = 'https://13.251.131.35:8080/api/user/search?'+'page=1'+'&'+'key='+keySearch;
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

    appName.init = function(){
        // if (Common.checkAuthen()) {
        //     Common.redirect('/index.html');
        // }

        //on a tag so sanh clicked
        $(document).on("click", '#mySoSanh', function(e) {
            e.preventDefault();
            // getRank();
            // alert("Sdasd");
            // addTable();
            var data =
              [
                {
                  'Id':1,
                  'Rank': 1,
                  'Name':'Nguyễn Huy Văn',
                  'Coding': 80,
                  'Database': 70,
                  'IQ':90,
                  'Attitude': 13,
                  'Language': 34
                },{
                  'Id':2,
                  'Rank': 2,
                  'Name':'Vi Văn Thức',
                  'Coding': 15,
                  'Database': 20,
                  'IQ':40,
                  'Attitude': 90,
                  'Language': 74
                },
                {
                  'Id':3,
                  'Rank': 3,
                  'Name':'Nguyễn Văn Thuần',
                  'Coding': 80,
                  'Database': 70,
                  'IQ':90,
                  'Attitude': 10,
                  'Language': 40
                }
              ]
            ;
            allData = data;
              for (var i = 0; i < data.length; i++) {
                if(myID == data[i].Id){
                  myData = data[i];
                }
                  addRow(data[i]);
              }


         });
         //on button search clicked
         $(document).on("click", '#mySearchBtn', function(e) {
             e.preventDefault();
             searchEmployee();
          });
    };

}(jQuery, window.sosanhModule = window.sosanhModule || {}));
//-----------------------------------------------------------------------------//
//add table
function addRow(rowData){
  var row = $("<tr id='Rank"+rowData.Rank+"' />")
    $("#myTableRankBody").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
    row.append($("<td>" + rowData.Rank + "</td>"));
    row.append($("<td>" + rowData.Name + "</td>"));
    row.append($("<td>" + rowData.Coding + "</td>"));
    row.append($("<td>" + rowData.Database + "</td>"));
    row.append($("<td>" + rowData.IQ + "</td>"));
    row.append($("<td>" + rowData.Attitude + "</td>"));
    row.append($("<td>" + rowData.Language + "</td>"));
}

//-----------------------------------------------------------------------------//

$(document).ready(function () {
     $('#myTableRankBody').on('click', 'tr', function (event) {
          $('#formSoSanh').show();
          var getID= $(this).attr('id');
          if(myData != null){
            if('Rank'+myData.Rank == getID){
              alert("dont click that field!");
            }else{
              for (var i = 0; i < allData.length; i++) {
                if('Rank'+ allData[i].Rank == getID){
                  dataCompare = allData[i];
                  break;
                }
              }
              setChartData(myData,dataCompare);
            }
          }
          // alert($(this).attr('id')); //trying to alert id of the clicked row

     });
 });
 //-----------------------------------------------------------------------------//

 function setChartData(myData, secondData) {
   //set radarChart
   myRadarChart.data.datasets[0].label = myData.Name;
   myRadarChart.data.datasets[0].data = [myData.Coding, myData.Database, myData.IQ, myData.Attitude, myData.Language];
   myRadarChart.data.datasets[1].label = secondData.Name;
   myRadarChart.data.datasets[1].data = [secondData.Coding, secondData.Database, secondData.IQ, secondData.Attitude, secondData.Language];
   //set barChart
   barChart.data.datasets[0].label = myData.Name;
   barChart.data.datasets[0].data = [myData.Coding, myData.Database, myData.IQ, myData.Attitude, myData.Language];
   barChart.data.datasets[1].label = secondData.Name;
   barChart.data.datasets[1].data = [secondData.Coding, secondData.Database, secondData.IQ, secondData.Attitude, secondData.Language];
   myRadarChart.update();
   barChart.update();
   //set rowChart
   $('#mySmallName').text(myData.Name);
   var myAverage = (myData.Coding + myData.Database +myData.IQ + myData.Attitude +myData.Language)/5;
   $('#myPointAverage').text(myAverage + "%");
   $('#mycoding').css("width",myData.Coding+"%");
   $('#mydatabase').css("width",myData.Database+"%");
   $('#myiq').css("width",myData.IQ+"%");
   $('#myattitude').css("width",myData.Attitude+"%");
   $('#mylanguage').css("width",myData.Language+"%");
   $('#yourSmallName').text(secondData.Name);
   var yourAverage = (secondData.Coding + secondData.Database +secondData.IQ + secondData.Attitude +secondData.Language)/5;
   $('#yourPointAverage').text(yourAverage + "%");
   $('#yourcoding').css("width",secondData.Coding+"%");
   $('#yourdatabase').css("width",secondData.Database+"%");
   $('#youriq').css("width",secondData.IQ+"%");
   $('#yourattitude').css("width",secondData.Attitude+"%");
   $('#yourlanguage').css("width",secondData.Language+"%");
 }
