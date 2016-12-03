/**
 * Created by Cfw on 2016/9/10.
 */
var webSocket;
var userName;
function webSocketConnect(){
    webSocket = new WebSocket("ws://localhost:8080/echo");
    webSocket.onopen = function(){
        while(true){
            if(webSocket.readyState === WebSocket.OPEN){
                var message = new Message(userName,1);
                sendMessage(message);
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
        var jsonMessage = eval("("+evt.data+")");
        var message = new Message(jsonMessage.userName,jsonMessage.type,jsonMessage.content,jsonMessage.count);
        log(message);
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
        console.log("Occurred an error!");
    }
}

function webSocketClose(){
    if(webSocket.readyState === WebSocket.OPEN){
        webSocket.close();
    }
}

function sendMessage(message){
    if(message == ""||"undefined"==typeof(message)){
        var content = $("#contentBox").val();
        message = new Message(userName,2,content);
    }
    $("#contentBox").val("");
    $("#contentBox").focus();
    webSocket.send(message.getJson());
}

function info(infoBar){
    $("#information").animate({top:'-105px'},1000);
    $("#information").empty();
    $("#information").append(infoBar);
    $("#information").animate({top:'0px'},1000);
}

function log(message){
    var element = "";
    if("undefined" != typeof(message.getType())){
        if(message.getType() == 1){
            element = "<span style='color:#ec971f'>"+message.getUserName()+"</span> 来到房间";
            $("#onlineNumber").text(message.getCount());
        }else if(message.getType() == 2){
            element = "<span style='color:#285e8e'>"+message.getUserName()+"</span>: " + message.getContent();
        }
    }
    console.log(message.getJson());
    $("#messageBox").append(element+"<br/>");
    $("#messageBox").getNiceScroll(0).scrollTop($("#messageBox").get(0).scrollHeight);

}
function register(){
    var name = prompt("请输入你的名字","");
    if(name != null && name != ""){
        userName = name;
        webSocketConnect();
    }
}
register();
