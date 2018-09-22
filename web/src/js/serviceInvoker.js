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
        var createCORSRequest = (method, url) => {
            var xhr = new XMLHttpRequest();
            if ("withCredentials" in xhr) {
                // Check if the XMLHttpRequest object has a "withCredentials" property.
                // "withCredentials" only exists on XMLHTTPRequest2 objects.
                xhr.open(method, url, true);
        
            } else if (typeof XDomainRequest != "undefined") {
                // Otherwise, check if XDomainRequest.
                // XDomainRequest only exists in IE, and is IE's way of making CORS requests.
                xhr = new XDomainRequest();
                xhr.open(method, url);
            } else {
                // Otherwise, CORS is not supported by the browser.
                xhr = null;
            }
            return xhr;
        }
      
        var ajaxRequest = function (httpVerb, method, dataRequest, callbacks, paramName, keepOriginal) {
            var dataToSend = keepOriginal === true ? dataRequest : JSON.stringify(dataRequest);
            dataToSend = paramName ? paramName + '=' + dataToSend : dataToSend;
            
            var xhr = createCORSRequest(httpVerb, getSvcUrl(method));
            if (!xhr) {
                throw new Error('CORS not supported');
            }
            else {
                // Response handlers.
                xhr.onload = function() {
                    try {
                        var jsonData = JSON.parse(xhr.response);
                        console.log('=========success', xhr.response);

                        if (callbacks && callbacks.success) {
                            callbacks.success(jsonData);
                        }
                    } catch (e) {
                        console.log(e);
                    }
                };

                xhr.onerror = function() {
                    try {
                        if (callbacks && callbacks.error) {
                            callbacks.error(xhr);
                        }
                    } catch (e) {
                        console.log(e);
                    }
                    console.log('Woops, there was an error making the request.', xhr);
                };

                xhr.send(dataToSend);
            }
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
