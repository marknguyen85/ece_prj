(function ($, appName) {
    /// <param name="appName">namespace of application.</param>
    'use strict';
    //get Capacity-------------------------------------------------------------------
    var getCapacity = (url) => {


        serviceInvoker.get(url, "", {
            error: function (response) {
                if (response.responseJSON && response.responseJSON.ErrorCode > 0) {
                  console.log(response.error());
                }
                else {
                  console.log("error");
                }
            },
            success: function (response) {
                // Common.storeToken('xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx');

                let data = [];
                for(let i = 0; i < response.data.skills.length; i++){
                  data.push(response.data.skills[i].skill_point)
                }
                console.log("data"+data);
               let dataName = [];
               for(let i = 0; i < response.data.skills.length; i++){
                 dataName.push(response.data.skills[i].skill_name);
               }
               console.log("dataName thuc: "+dataName);
                radarChart.data.datasets[0].data = data;
                radarChart.data.labels = dataName;
                radarChart.data.datasets[0].label = response.data.employee_name;

                radarChart.update();
                // Common.redirect('/index.html');
            }
        });

    }

    //get Random myColor
    var getRandomColor = () =>{
      let letters = '0123456789ABCDEF';
      let color = '#';
      for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
      }
      return color;
    }
    //get History ------------------------------------------------------------------
    var getHistory = (url) => {


        serviceInvoker.get(url, "", {
            error: function (response) {
                if (response.responseJSON && response.responseJSON.ErrorCode > 0) {
                  console.log("error1");
                }
                else {
                  console.log("error 2");
                }
            },
            success: function (response) {

              for(let i = 0; i <  response.data.skills.length; i++){
                let myColor = getRandomColor();
                lineChart.data.datasets[i] = {
                  backgroundColor: 'rgba(220, 220, 220, 0.2)',
                  borderColor: myColor,
                  pointBackgroundColor: myColor,
                  pointBorderColor: '#fff'
                }
              }
              //labels
              let labels = [];

              for(let i = 0; i <  response.data.skills[0].skillForMonth.length; i++){
                labels.push(response.data.skills[0].skillForMonth[i].month);
              }
              lineChart.data.labels = labels;
              console.log("labels: thuc === "+labels);

              //labelName eg: Java
              let labelName = [];
              if(labelName.length === 0)
              for(let ind = 0; ind < response.data.skills.length; ind++){
                labelName.push(response.data.skills[ind].skill_name);
              }

              for(let i = 0; i < labelName.length; i++){
                lineChart.data.datasets[i].label = labelName[i];
              }
              console.log("lableName thuc thuc: "+labelName);

              //datasets
              let dataSetDT = [];
              for(let ind = 0; ind < response.data.skills.length; ind++){
                let dataTail = [];
                for(let j = 0; j < response.data.skills[ind].skillForMonth.length; j++){
                  dataTail.push(response.data.skills[ind].skillForMonth[j].point)
                }
                dataSetDT.push({data: dataTail});
              }

              console.log("dataset: "+dataSetDT);

              for(let i = 0; i < dataSetDT.length; i++){
                lineChart.data.datasets[i].data = dataSetDT[i].data;
                console.log("DataDT: "+dataSetDT[i].data);
              }

              lineChart.update();
            }
        });

    }



    //init app
    appName.init = function(){
        // if (Common.checkAuthen()) {
        //     Common.redirect('/index.html');
        // }
        $(document).ready(function(e){
          let uid = Common.currentUser().id;
          var url = '/api/user/capacity?user_id='+uid;
          getCapacity(url); 
          e.preventDefault();
        })
        $(document).on("click", '#capacityId', function(e) {
          let uid = Common.currentUser().id;
          var url = '/api/user/capacity?user_id='+uid;
          getCapacity(url);
          e.preventDefault();
        });
        $(document).on("click", '#historyDefaultId', function(e) {

          let currentDate = new Date().toLocaleDateString();
          let beforeDate = new Date();
              beforeDate.toLocaleDateString();
              beforeDate.setMonth(beforeDate.getMonth() - 6);
          let bBeforeDate = beforeDate.toLocaleDateString();
          let uid = Common.currentUser().id;

          let tailUrl = 'user_id='+uid+'&'+'from='+bBeforeDate+'&'+'to='+currentDate;
          const url = '/api/user/history?'+tailUrl;
          getHistory(url);


          // Common.redirect("/history.html");
          // e.preventDefault();
        });

        $(document).on('click', '#searchHistory', function(e){
          //uid
          let uid = Common.currentUser().id;
          //from
          let from1 = $("#fromDateRank").val();
          let from2 = from1.split("-");
          let from = from2[2]+'/'+from2[1]+'/'+from2[0];
          //to
          let to1 = $("#toDateRank").val();
          let to2 = to1.split("-");
          let to = to2[2]+'/'+to2[1]+'/'+to2[0];
          //tail Url
          let tailUrl = 'user_id='+uid+'&'+'from='+from+'&'+'to='+to;
          //url
          const url = '/api/history?'+tailUrl;
          //get history
          getHistory(url);
          e.preventDefault();
        })
    };

}(jQuery, window.CapaModule = window.CapaModule || {}));
