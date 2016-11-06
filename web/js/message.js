/**
 * Created by Cfw on 2016/10/2.
 */
var Message = function(userNameParam,typeParam,contentParam,countParam){
    var userName = "";
    var type = "";
    var content = "";
    var count = "";

    if("undefined"!=typeof(userNameParam) && userNameParam != null){
        userName = userNameParam;
    }
    if("undefined"!=typeof(typeParam) && typeParam != null){
        type = typeParam;
    }
    if("undefined"!=typeof(contentParam) && contentParam != null){
        content = contentParam;
    }
    if("undefined"!=typeof(countParam) && countParam != null){
        count = countParam;
    }

    this.setUserName = function(userNameParam){
        userName = userNameParam;
    }
    this.getUserName = function(){
        return userName;
    }
    this.setType = function(typeParam){
        type = typeParam;
    }
    this.getType = function(){
        return type;
    }
    this.setContent = function(contentParam){
        content = contentParam;
    }
    this.getContent = function(){
        return content;
    }
    this.setCount = function(countParam){
        count = countParam;
    }
    this.getCount = function(){
        return count;
    }

    this.getJson = function(){
        return '{"type":"'+type+'","content":"'+content+'","userName":"'+userName+'"}';
    }
}

var parseJson = function(jsonMessageStr){
    var jsonMessage = eval("("+jsonMessageStr+")");
    var message = new Message(jsonMessage.userName,jsonMessage.type,jsonMessage.content);

    return message;

}

Message.prototype.parseJson = parseJson;




