(function ($, appName) {
    /// <param name="appName">namespace of application.</param>
    'use strict';
    var login = () => {
        var formData = {
            username: $('#username').val(),
            password: $('#password').val(),
        }
        var url = '/post'
        serviceInvoker.post(url, formData, {
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
        if (Common.checkAuthen()) {
            Common.redirect('/index.html');
        }

        $(document).on("click", '#lg-login', function() {
            login();
         });
    };

}(jQuery, window.LoginModule = window.LoginModule || {}));