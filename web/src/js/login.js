(function ($, appName) {
    /// <param name="appName">namespace of application.</param>
    'use strict';

    appName.init = function(){
        $(document).on("click", '#lg-login', function() {
            alert('ok');
            location.href = '/index.html';
         });
    };

}(jQuery, window.LoginModule = window.LoginModule || {}));