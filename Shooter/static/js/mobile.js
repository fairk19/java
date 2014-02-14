var socket = new WebSocket("ws://localhost:8083/play/");
var startButton = document.getElementById("startGame");
var sprites = [];

var command = {
    play: "play",
    create: "create",
    move: "move",
    update: "update"
}

var jsonMessage = {

        parser: function(message){
            var obj = JSON.parse(message);
            return obj;
        },

        create: function(from, command, obj, value){
            var message = {
                from: from,
                command: command,
                obj: obj,
                value: value
            }
            var messageToString = JSON.stringify(message);
            return messageToString;
        }
}

var user = {
     id: document.getElementById("userToken").innerHTML
};

function Sprite(userId, posX, posY)
{
    this.userId = userId;
    this.posX = posX;
    this.posY = posY;
}

var game = {

  start: function() {
        var message = jsonMessage.create(user.id, command.play, obj.user);
        socket.send(message);
        startButton.setAttribute('hidden', true);
  }
}

startButton.addEventListener('click', game.start, false);
socket.onopen = function() {
  console.log("соединение установленно");
}

socket.onclose = function(event) {
  if (event.wasClean) {
    console.log('Соединение закрыто чисто');
  } else {
    console.log('Обрыв соединения');
  }
  console.log('Код: ' + event.code + ' причина: ' + event.reason);
}

socket.onmessage = function(event) {

  console.log("Получены данные " + event.data);
  var obj = jsonMessage.parser(event.data);
  if(obj.command == command.create)
  {
    var sprite = new Sprite(obj.from, obj.value.posX, obj.value.posY);
    sprites.push(sprite);
    console.log("created sprite userId: "+ sprites[0].userId + "\nPosX: " + sprites[0].posX + "\nPosY: " + sprites[0].posY);
  }
  if(obj.command == command.update)
  {
    //code here
  }
}

socket.onerror = function(error) {
  console.log("Ошибка " + error.message);
}