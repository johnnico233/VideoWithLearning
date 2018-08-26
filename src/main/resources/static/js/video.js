$(function(){
    var list=[{
        "mode": 1,
        "text": "哈哈",
        "stime": 1000,
        "size": 25,
        "color": 0xffffff
    }];
    var player=new Aliplayer({
        id:"J_prismPlayer",
        width:"800px",
        height:"500px",
        autoplay:false,
        source:$("#videoUrl").val(),
        controlBarVisibility: 'click',    /* 控制栏面板的实现方式, 为点击出现 */
        showBarTime: '10000',             /* 控制栏自动隐藏时间 10000ms */
        components: [{
            name: 'AliplayerDanmuComponent',
            type: AliPlayerComponent.AliplayerDanmuComponent,
            args: [list]
        }]
    });
    $(".danmu-input-enter").on('click',function(){
        console.log("click me!");
    })
})