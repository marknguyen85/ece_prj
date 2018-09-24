
var myID = Common.currentUser().id;
var myData = null;
var mySkill = null;
var dataCompare = null;
var allData = null;
(function ($, appName) {
    /// <param name="appName">namespace of application.</param>
    'use strict';
    //get ranking
    var getRank = () => {
        var url = '/user/technique?user_id='+ myID;
        serviceInvoker.get(url, {}, {
            error: function (response) {
                if (response.responseJSON && response.responseJSON.ErrorCode > 0) {
                }
                else {
                }
            },
            success: function (response) {
              allData = response.data.employees;
              mySkill = response.data.skills;
              $("#myTableRankHead").html("");
              addHead(mySkill);
              $("#myTableRankBody").html("");
              for (var i = 0; i < allData.length; i++) {
                if(myID == allData[i].employee_id){
                  myData = allData[i];
                }
                  addRow(allData[i]);
              }
                // Common.storeToken('xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx');
                // Common.redirect('/index.html');
            }
        });
    }

    //add table
    function addRow(rowData){
      var row = $("<tr id='Rank"+rowData.rank+"' />")
        $("#myTableRankBody").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
        row.append($("<td>" + rowData.rank + "</td>"));
        // row.append($("<td>" + rowData.employee_id + "</td>"));
        row.append($("<td>" + rowData.employee_name + "</td>"));
        for (var i = 0; i < rowData.skills.length; i++) {
          row.append($("<td>" + rowData.skills[i].skill_point + "</td>"));
        }
    }

    //add table
    function addHead(headData){
      var row = $("<tr />")
        $("#myTableRankHead").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
        row.append($("<td>" + "Xếp hạng" + "</td>"));
        // row.append($("<td>" + "EmployeeID" + "</td>"));
        row.append($("<td>" + "Tên Nhân viên" + "</td>"));
        for (var i = 0; i < headData.length; i++) {
          row.append($("<td>" + headData[i].skill_name + "</td>"));
        }
    }

    //add progress bar
    function addProgressBar(name,myProgress,yourProgress){
      // $("#myProgressBar").html("");
      // var row = $(' <div class="progress-group mb-4" />');
      var  row = ($('<div class="progress-group mb-4"> '+
          ' <div class="progress-group-prepend"> ' +
          '  <span class="progress-group-text">'+ name +'</span>' +
          ' </div> ' +
        '  <div class="progress-group-bars"> ' +
          '  <div class="progress progress-xs"> ' +
            '  <div id="mycoding" class="progress-bar bg-info" role="progressbar" style="width: '+myProgress+'%" aria-valuenow="34" aria-valuemin="0" aria-valuemax="100"></div> ' +
          '  </div> ' +
          '  <div class="progress progress-xs"> ' +
            '  <div id="yourcoding" class="progress-bar bg-danger" role="progressbar" style="width: '+yourProgress+'%" aria-valuenow="78" aria-valuemin="0" aria-valuemax="100"></div> ' +
          '  </div> ' +
        '  </div> ' +
      '  </div>'));
      $("#myProgressBar").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
    }

    //-----------------------------------------------------------------------------//

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

    var initData = (e) => {
      // getRank();
      // alert("Sdasd");
      // addTable();
      var data ={
        skill:[
          {
            skill_name:'Java',
            skill_id:'1',
          },
          {
            skill_name:'DB',
            skill_id:'2',
          },
          {
            skill_name:'Language',
            skill_id:'3',
          }
        ],
        employee:[
          {
            employee_id:1,
            employee_rank:1,
            employee_name:'Nguyen Huy Van',
            skill:[
              {
                skill_name:'Java',
                skill_point:80
              },
              {
                skill_name:'BD',
                skill_point:70
              },
              {
                skill_name:'Language',
                skill_point:50
              }
            ]
          },
          {
            employee_id:2,
            employee_rank:2,
            employee_name:'Nguyen Van Thuan',
            skill:[
              {
                skill_name:'Java',
                skill_point:50
              },
              {
                skill_name:'BD',
                skill_point:50
              },
              {
                skill_name:'Language',
                skill_point:80
              }
            ]
          },
          {
            employee_id:3,
            employee_rank:3,
            employee_name:'Vi Van Thuc',
            skill:[
              {
                skill_name:'Java',
                skill_point:20
              },
              {
                skill_name:'BD',
                skill_point:25
              },
              {
                skill_name:'Language',
                skill_point:90
              }
            ]
          }
        ]
      }
      getRank();
      // allData = data.employee;
      // mySkill = data.skill;
      // $("#myTableRankHead").html("");
      // addHead(mySkill);
      // $("#myTableRankBody").html("");
      // for (var i = 0; i < allData.length; i++) {
      //   if(myID == allData[i].employee_id){
      //     myData = allData[i];
      //   }
      //     addRow(allData[i]);
      // }
    }

    //-----------------------------------------------------------------------------//

    function setChartData(myData, secondData) {
      //set radarChart
      var labels = [];
      var myPointAverage = 0;
      var yourPointAverage = 0;
      for (var i = 0; i < mySkill.length; i++) {
        labels[i] = mySkill[i].skill_name;
      }
      myRadarChart.data.labels = labels;
      myRadarChart.data.datasets[0].label = myData.employee_name;
      var myDatas = [];
      for (var i = 0; i < myData.skills.length; i++) {
        myDatas[i] = myData.skills[i].skill_point;
        myPointAverage += myData.skills[i].skill_point;
      }
      myPointAverage = myPointAverage/myData.skills.length;
      myPointAverage = myPointAverage.toFixed(2);
      myRadarChart.data.datasets[0].data = myDatas;
      myRadarChart.data.datasets[1].label = secondData.employee_name;
      var yourDatas = [];
      for (var i = 0; i < secondData.skills.length; i++) {
        yourDatas[i] = secondData.skills[i].skill_point;
        yourPointAverage +=  secondData.skills[i].skill_point;
      }
      yourPointAverage = yourPointAverage/secondData.skills.length;
      yourPointAverage = yourPointAverage.toFixed(2);
      myRadarChart.data.datasets[1].data = yourDatas;
      //set barChart
      barChart.data.labels = labels;
      barChart.data.datasets[0].label = myData.employee_name;
      barChart.data.datasets[0].data = myDatas;
      barChart.data.datasets[1].label = secondData.employee_name;
      barChart.data.datasets[1].data = yourDatas;
      myRadarChart.update();
      barChart.update();
      //set rowChart
      $("#myProgressBar").html("");
      for (var i = 0; i < mySkill.length; i++) {

        addProgressBar(mySkill[i].skill_name,myData.skills[i].skill_point,secondData.skills[i].skill_point);
      }
      $('#mySmallName').text(myData.employee_name);
      $('#myPointAverage').text(myPointAverage + "%");
      $('#yourSmallName').text(secondData.employee_name);
      $('#yourPointAverage').text(yourPointAverage + "%");
    }

    appName.reloadData = () => {
      $('#formSoSanh').hide();
      initData();
    }

    appName.init = function(){
        if (!Common.checkAuthen()) {
            Common.redirect('/login.html');
        }
        $(document).ready(function () {
          $('#myTableRankBody').on('click', 'tr', function (event) {

              var getID= $(this).attr('id');
              // alert("getID: "+ getID);
              if(myData != null){
                if('Rank'+myData.rank == getID){
                  // alert("dont click that field!");
                }else{
                  $('#formSoSanh').show();
                  for (var i = 0; i < allData.length; i++) {
                    if('Rank'+ allData[i].rank == getID){
                      dataCompare = allData[i];
                      break;
                    }
                  }
                  // alert("myData: "+myData.employee_id + "dataCompare: "+ dataCompare.employee_id);
                  setChartData(myData,dataCompare);

                  location.href = '/index.html#formSoSanh'
                }
              }else{
                alert("mydata == null");
              }
              // alert($(this).attr('id')); //trying to alert id of the clicked row

          });
        });

         //on button search clicked
        $(document).on("click", '#mySearchBtn', function(e) {
            e.preventDefault();
            searchEmployee();
        });

        $('#formSoSanh').hide();
    };

}(jQuery, window.sosanhModule = window.sosanhModule || {}));
//-----------------------------------------------------------------------------//
