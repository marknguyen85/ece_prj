(function ($, appName) {
    /// <param name="appName">namespace of application.</param>
    'use strict';
    appName.BASE_URL_API = 'http://13.251.131.35:8080/api'
    // appName.BASE_URL_API = '192.168.1.145:8080/api'

    appName.init = function(){

    };

    var getToken = () => {
        var token = localStorage.getItem('_token');
        if (token == 'undefined' || token == '') {
            return {};
        }

        var dataJson = JSON.parse(token);
        return dataJson;
    }

    appName.storeToken = (token) => {
        var dataJson = JSON.stringify(token);
        localStorage.setItem('_token', dataJson);
    };


    appName.removeToken = () => {
        localStorage.removeItem('_token');
    };


    appName.getToken = () => {
        return getToken();
    };

    appName.currentUser = () => {
        return getToken();
    };

    appName.checkAuthen = () => {
        var token = getToken();
        if (token) {
            return true;
        }

        return false;
    };

    appName.redirect = (url) => {
        if (url[0] != '/'){
            url = '/' + url;
        }

        location.href = url;
    };

    appName.loadAccountInfo = () => {
        $.get('/userinfo.html', function(html){
            var accInfo = getToken();
            $('#account-info').html(html);
            $('.header-account').html(accInfo.name || 'Khách');
        });
    }

    appName.loadSidebar = () => {
        $.get('/menu.html', function(html){
            $('.sidebar').html(html);
        });
    }

}(jQuery, window.Common = window.Common || {}));
