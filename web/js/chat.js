/**
 * Created by Cfw on 2016/9/10.
 */
var webSocket;
var CLIENT_MSG = 1;
var SERVER_MSG = 2;
//info("<div class='alert alert-info' role='alert'>WebSocket is opening...</div>");
function webSocketConnect(){
    //webSocket = new WebSocket("ws://echo.websocket.org/echo");
    webSocket = new WebSocket("ws://localhost:8080/echo");
    webSocket.onopen = function(){
        while(true){
            if(webSocket.readyState === WebSocket.OPEN){
                var information = "Server connected!";
                var infoBar = "<div class='alert alert-info' role='alert'>"+ information +"</div>";
                info(infoBar);
                break;
            }

        }

    }

    webSocket.onmessage = function(evt){
        var infoBar = "<div class='alert alert-success' role='alert'>Receiving from Server! </div>";
        info(infoBar);
        log(evt.data,SERVER_MSG);
    }

    webSocket.onclose = function(evt){
        while(true){
            if(webSocket.readyState === WebSocket.CLOSED){
                var information = "Connection closed!";
                var infoBar = "<div class='alert alert-warning' role='alert'>"+ information +"</div>";
                info(infoBar);
                break;
            }
        }

    }

    webSocket.onerror = function(evt) {
        var infoBar = "<div class='alert alert-danger' role='alert'>WebSocket occurred an error!</div>";
        info(infoBar);
        log("Occurred an error!");
    }
}

function webSocketClose(){
    if(webSocket.readyState === WebSocket.OPEN){
        webSocket.close();
    }
}

function sendMessage(){
    var message = $("#contentBox").val();
    log(message,CLIENT_MSG);
    webSocket.send(message);
}

function info(infoBar){
    $("#information").animate({top:'-105px'},1000);
    $("#information").empty();
    $("#information").append(infoBar);
    $("#information").animate({top:'0px'},1000);
}

function log(message,type){
    if("undefined" != typeof(type)){
        if(type == CLIENT_MSG){
            message = "<span style='color:green'>Client:</span> " + message;
        }else if(type == SERVER_MSG){
            message = "<span style='color:darkred'>Server:</span> " + message;
        }
    }
    console.log(message);
    $("#messageBox").append(message+"<br/>");
}

webSocketConnect();