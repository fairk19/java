//var socket = new WebSocket("ws://192.168.173.1:8083/play/");
//   if(socket.onerror){
//   }

var socket = new WebSocket("ws://localhost:8083/play/");
var sprites = [];

var command = {
    play:   "play",
    create: "create",
    move: "move",
    update: "update",
    shoot: "shoot",
    destroy: "destroy"
};

var objToCommand = {
    user: "user",
    newSprite: "newSprite",
    sprites: "sprites",
    map: "map"
};

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

var userSprite = {

     id: document.getElementById("userId").innerHTML,

     create: function(posX, posY){
        this.posX = posX;
        this.posY = posY;
     }
};

function Sprite(userId, posX, posY){
   this.userId = userId;
   this.posX = posX;
   this.posY = posY;
};

var map = {

    grid: {
        width:  0,
        height: 0,
        tile: {
            width:  0,
            height: 0
        }
    },

    width: function() {
        return this.grid.width * this.grid.tile.width;
    },

    height: function() {
        return this.grid.height * this.grid.tile.height;
    },

    create: function(width, height, tileWidth, tileHeight){

        this.grid.width = width;
        this.grid.height = height;
        this.grid.tile.width = tileWidth;
        this.grid.tile.height = tileHeight;

        console.log("Game map  created" + "\nwidth: " + this.width() + "\nheight: " + this.height() + "\n");
    }
};

var game = {

  load: function() {

        var message = jsonMessage.create(userSprite.id, command.play, objToCommand.user);
        socket.send(message);
        startButton.setAttribute('style', "display: none;");
  },
  start: function() {
        Crafty.init(map.width(), map.height());
        Crafty.background('rgb(87, 109, 20)');
    }
};

var startButton = document.getElementById("startGame");
startButton.addEventListener('click', game.load);

socket.onopen = function() {
  console.log("соединение установлено");
  console.log("Пользователь c id=" + userSprite.id + " вошел в игру!");
};

socket.onclose = function(event) {
  if (event.wasClean) {
    console.log('Соединение закрыто чисто');
  } else {
    console.log('Обрыв соединения');
  }
  console.log('Код: ' + event.code + ' причина: ' + event.reason);
};

socket.onmessage = function(event) {

  console.log("Получены данные " + event.data);

  var messageInput = jsonMessage.parser(event.data);

  if(messageInput.command == command.create)
  {
    if(messageInput.obj == objToCommand.map)
    {
        map.create(messageInput.value.width, messageInput.value.height, messageInput.value.tileWidth, messageInput.value.tileHeight);
        game.start();
    }
    if(messageInput.obj == objToCommand.user){

       userSprite.create(messageInput.value.posX, messageInput.value.posY);
       Crafty.scene('Loading');
    }

    if(messageInput.obj == objToCommand.sprites)
    {
        for(var i = 0; i < parseInt(messageInput.size); i++){
            var json = messageInput.value[i];
            if(json.userId != userSprite.id)
            {
                var sprite = new Sprite(json.userId, json.posX, json.posY);
                sprites.push(sprite);
                Crafty.e('EnemySprite').at(sprite.posX, sprite.posY);
            }
        }
      }
    }
  };

socket.onerror = function (error) {
    console.log("Ошибка " + error.message);
};

$text_css = { 'font-size': '24px', 'font-family': 'Arial', 'color': 'white', 'text-align': 'center' }