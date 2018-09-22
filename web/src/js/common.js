(function ($, appName) {
    /// <param name="appName">namespace of application.</param>
    'use strict';
    appName.BASE_URL_API = 'http://localhost:5000'

    appName.init = function(){

    };

    var getToken = () => {
        var token = localStorage.getItem('_token');
        if (token == 'undefined' || token == '') {
            return ''
        }

        return token;
    }

    appName.storeToken = (token) => {
        localStorage.setItem('_token', token);
    };


    appName.removeToken = () => {
        localStorage.removeItem('_token');
    };


    appName.getToken = () => {
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

}(jQuery, window.Common = window.Common || {}));
