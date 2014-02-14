Crafty.scene('Loading', function(){
    Crafty.e('2D, DOM, Text')
        .text('Loading please wait...')
        .attr({ x: 0, y: map.height()/2 - 24, w: map.width() })
        .css($text_css);

    Crafty.load([
        '/img/16x16_forest.gif',
        '/img/hunter.png',
        '/img/enemy.png'
    ], function(){

        Crafty.sprite(16, '/img/16x16_forest.gif', {
            spr_tree:    [0, 0],
            spr_bush:    [1, 0],
            spr_village: [0, 1]
        });

        Crafty.sprite(16, '/img/hunter.png', {
            spr_player:  [0, 2]
        }, 0, 2);

        Crafty.sprite(16, '/img/enemy.png', {
            spr_enemy:  [0, 0]
        }, 0, 2);

        Crafty.scene('Game');
    })
});

Crafty.scene('Game', function() {

    this.occupied = new Array(map.grid.width);
    for (var i = 0; i < map.grid.width; i++) {
        this.occupied[i] = new Array(map.grid.height);
        for (var y = 0; y < map.grid.height; y++) {
            this.occupied[i][y] = false;
        }
    }

    this.player = Crafty.e('UserSprite').at(userSprite.posX, userSprite.posY);
    this.player.idSet(userSprite.id);

    for(var i = 0; i < sprites.length; i++)
    {
        console.log("Enemy posX: " + sprites[i].posX + " posY: " + sprites[i].posY + "\n");
        var enemy = Crafty.e('EnemySprite').at(sprites[i].posX, sprites[i].posY);
        enemy.idSet(sprites[i].userId);
        this.enemies = new Array();
        this.enemies.push(enemy);
    }
//    this.occupied[this.player.at().x][this.player.at().y] = true;
    console.log("Игрок с id: " + this.player.id + " posX: " + userSprite.posX + " posY: " + userSprite.posY + " был создан");

    for (var x = 0; x < map.grid.width; x++) {
        for (var y = 0; y < map.grid.height; y++) {
            var atEdge = x == 0 || x == map.grid.width - 1 || y == 0 || y == map.grid.height - 1;

            if (atEdge) {
                Crafty.e('Tree').at(x, y)
                this.occupied[x][y] = true;
            }
//           else if (Math.random() < 0.06 && !this.occupied[x][y]) {
//
//                Crafty.e('Bush').at(x, y)
//                this.occupied[x][y] = true;
//            }
        }
    }

});

Crafty.scene('Victory', function() {

    Crafty.e('2D, DOM, Text')
        .text('All villages visited!')
        .attr({ x: 0, y: map.height()/2 - 24, w: map.width() })
        .css($text_css);

    var delay = true;
    setTimeout(function() { delay = false; }, 5000);
    this.restart_game = Crafty.bind('KeyDown', function() {
        if (!delay) {
            Crafty.scene('Game');
        }
    });
}, function() {
    this.unbind('KeyDown', this.restart_game);
});