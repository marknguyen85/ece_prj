(function () {
    var serviceBase = Common.BASE_URL_API,
        getSvcUrl = function (method) {
            if (method.indexOf('http') === 0) {
                return method;
            }
            if (method[0] == "/") {
                return serviceBase + method;
            }
            return serviceBase + "/" + method;
        };

    window.serviceInvoker = (function () {
        var ajaxRequest = function (httpVerb, method, dataRequest, callbacks, paramName, keepOriginal) {
            var dataToSend = keepOriginal === true ? dataRequest : JSON.stringify(dataRequest);
            dataToSend = paramName ? paramName + '=' + dataToSend : dataToSend;
            
            return $.get(getSvcUrl(method), function(response){
                console.log(response);

                try {
                    if (!response) {
                        if (callbacks && callbacks.success) {
                            callbacks.error('error');
                        }
                    }
                    else {
                        if (callbacks && callbacks.success) {
                            callbacks.success(response);
                        }
                    }
                } catch (e) {
                    console.log(e);
                }
            }, 'json');

            // var options = {
            //     url: getSvcUrl(method),
            //     type: httpVerb,
            //     data: dataToSend,
            //     dataType: "json",
            //     contentType: "application/json",
            //     xhrFields: {
            //         withCredentials: true
            //     },
            //     headers: {
            //         '_token' : Common.getToken(),
            //         'Access-Control-Allow-Origin': '*'
            //     }
            // };

            // options.success = function (response) {
            //     try {
            //         if (callbacks && callbacks.success) {
            //             callbacks.success(response);
            //         }
            //     } catch (e) {
            //         console.log(e);
            //     }
            // };
            // options.error = function (response) {
            //     try {
            //         if (callbacks && callbacks.error) {
            //             callbacks.error(response);
            //         }
            //     } catch (e) {
            //         console.log(e);
            //     }
            // };
            // options.complete = function (response) {
            //     try {
            //         if (callbacks && callbacks.complete) {
            //             callbacks.complete(response);
            //         }
            //     } catch (e) {
            //         console.log(e);
            //     }
            // };
            // return $.ajax(options);
        },
        get = function (method, request, callback, paramName, keepOriginal) {
            /// <summary>Perform a get request to web api</summary>
            /// <param name="method" type="Object">Method of web api to call</param>
            /// <param name="request" type="Object">Json data to be sent along with the request</param>
            /// <param name="callback" type="Object">
            /// Callback object contains callback functions to be called when request is success, error.
            /// </param>

            return ajaxRequest('get', method, request, callback, paramName, keepOriginal);
        },
            post = function (method, request, callback, paramName, keepOriginal) {
                /// <summary>Perform a post request to web api</summary>
                /// <param name="method" type="Object">Method of web api to call</param>
                /// <param name="request" type="Object">Json data to be sent along with the request</param>
                /// <param name="callback" type="Object">
                /// Callback object contains callback functions to be called when request is success, error
                /// </param>

                return ajaxRequest('post', method, request, callback, paramName, keepOriginal);
            },
            remove = function (method, request, callback, paramName, keepOriginal) {
                /// <summary>Perform a post request to web api</summary>
                /// <param name="method" type="Object">Method of web api to call</param>
                /// <param name="request" type="Object">Json data to be sent along with the request</param>
                /// <param name="callback" type="Object">
                /// Callback object contains callback functions to be called when request is success, error
                /// </param>

                return ajaxRequest('delete', method, request, callback, paramName, keepOriginal);
            },
            put = function (method, request, callback, paramName, keepOriginal) {
                /// <summary>Perform a put request to web api</summary>
                /// <param name="method" type="Object">Method of web api to call</param>
                /// <param name="request" type="Object">Json data to be sent along with the request</param>
                /// <param name="callback" type="Object">
                /// Callback object contains callback functions to be called when request is success, error
                /// </param>

                return ajaxRequest('put', method, request, callback, paramName, keepOriginal);
            };
        return {
            get: get,
            post: post,
            put: put,
            remove: remove,
        };
    })();
})();
