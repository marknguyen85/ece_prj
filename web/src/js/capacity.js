(function ($, appName) {
    /// <param name="appName">namespace of application.</param>
    'use strict';
    //get Capacity-------------------------------------------------------------------
    var getCapacity = () => {

        var url = '/api/capacity?uid=1'
        serviceInvoker.get(url, "", {
            error: function (response) {
                if (response.responseJSON && response.responseJSON.ErrorCode > 0) {
                  alert("error1")
                }
                else {
                  alert("error2")
                }
            },
            success: function (response) {
                // Common.storeToken('xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx');
                radarChart.data.datasets[0].data = response.data;
                radarChart.update();
                // alert(response.data)
                // Common.redirect('/index.html');
            }
        });

    }
    //get History ------------------------------------------------------------------
    var getHistory = (url) => {

        var url = url;
        serviceInvoker.get(url, "", {
            error: function (response) {
                if (response.responseJSON && response.responseJSON.ErrorCode > 0) {
                  alert("error1")
                }
                else {
                  alert("error2")
                }
            },
            success: function (response) {

              // alert(response.data.Coding);
                // Common.storeToken('xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx');
                lineChart.data.datasets[0].data = response.data.Coding;
                lineChart.data.datasets[1].data = response.data.IQ;
                lineChart.data.datasets[2].data = response.data.Database;
                lineChart.data.datasets[3].data = response.data.Attitude;
                lineChart.data.datasets[4].data = response.data.Language;
                lineChart.update();

                // Common.redirect('/history.html');
            }
        });

    }


    //init app
    appName.init = function(){
        // if (Common.checkAuthen()) {
        //     Common.redirect('/index.html');
        // }

        $(document).on("click", '#capacityId', function(e) {

          getCapacity();
          e.preventDefault();
        });
        $(document).on("click", '#historyId', function(e) {
          // alert("thuc")
          const url = '/api/history?uid=1&from=7&to=12';
          getHistory(url);
          // Common.redirect("/history.html");
          e.preventDefault();
        });

        $(document).on('click', '#searchHistory', function(e){
          const url = '/api/history?uid=1&from=7&to=12';
          getHistory(url);
          e.preventDefault();
        })
    };

}(jQuery, window.CapaModule = window.CapaModule || {}));
