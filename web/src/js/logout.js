(function ($, appName) {
    /// <param name="appName">namespace of application.</param>
    'use strict';
    appName.init = function(){
        Common.removeToken();
        Common.redirect('/login.html');
    };

}(jQuery, window.LogoutModule = window.LogoutModule || {}));