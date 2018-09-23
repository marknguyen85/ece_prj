(function ($, appName) {
    /// <param name="appName">namespace of application.</param>
    'use strict';

    var login = () => {
        var formData = {
            data: {
                username: $('#username').val(),
                password: $('#password').val()
            }
        }

        var url = '/user/authorize';
        serviceInvoker.post(url, formData, {
            error: function(response){
                alert('login error');
            },
            success: function(response){
                Common.storeToken(response.data);
                Common.redirect('/index.html');
            }
        })

        return false;
    }

    appName.init = function(){
        if (Common.checkAuthen()) {
            Common.redirect('/index.html');
        }

        $(document).on("click", '#lg-login', function(evt) {
            evt.preventDefault();
            login();
         });
    };

}(jQuery, window.LoginModule = window.LoginModule || {}));
