(function ($, appName) {
    /// <param name="appName">namespace of application.</param>
    'use strict';



    //get History ------------------------------------------------------------------
    var getHistory = () => {

        var url = '/api/history?uid=1&from=7&to=12'
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
                lineChart.update();
                // radarChart.update();
                // alert(response.data)
                // Common.redirect('/history.html');
            }
        });

    }


    //init app
    appName.init = function(){

        $(document).on("click", '#historyId', function(e) {
          alert("thuc")
          getHistory();
          // Common.redirect("/history.html");
          // e.preventDefault();
        });
    };

}(jQuery, window.HistoryModule = window.HistoryModule || {}));
