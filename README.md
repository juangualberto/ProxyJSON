# ProxyJSON
This simple Servlet implements a proxy that serves as JSON the RSS or ATOM 
news channel that you indicate in the URL parameter.


*This is not a JSONP*.

There is a *simple filter* to avoid the CORS matter. 

## How to use it: Client side

In order to connect to this server, you may use a jQuery call just like this one:

'''javascript
 $.ajax({
        url: 'http://YOURSERVER:8080/YOURSERVICE/RssServlet?url=' + urlChannel,
        dataType: "json",
        data: {
            format: "json",
        },
        success: function(response) {
            // HERE GOES YOUR PROCESSING CODE
        }
});
'''
Where YOURSERVER could be localhost or the server name where the service is hosted 
and YOURSERVICE is the name you used in the project as service name.

By using the GET method you must indicate the URL where the RSS or ATOM channel is. 
This will be the *urlChannel* variable. 
 
## TODO
1. Filter whether the XML you are trying to use whith the proxy is a real RSS or ATOM channel 
(right now the service will convert any XML you ask it for).
2. Create the POM file.

