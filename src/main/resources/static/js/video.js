$(function(){
    var player=new Aliplayer({
        id:"J_prismPlayer",
        width:"800px",
        height:"500px",
        autoplay:false,
        source:$("#videoUrl").val(),
        controlBarVisibility: 'click',    /* 控制栏面板的实现方式, 为点击出现 */
        showBarTime: '10000',             /* 控制栏自动隐藏时间 10000ms */
    });
})