$(function(){
    setCreateVideoType();
    setDeleteVideoType();
    setDeleteVideo();
});

function setCreateVideoType(){
    var typeBlock=$("select[name='type-of']");
    typeBlock.on("change",function(){
        if(typeBlock.val()==-1){
            $(".createVideoTypeBlock p:nth-of-type(3)").css("display","none");
            $(".createVideoTypeBlock p:nth-of-type(4)").css("display","none");
        }else{
            $(".createVideoTypeBlock p:nth-of-type(4)").css("display","block");
            $(".createVideoTypeBlock p:nth-of-type(3)").css("display","block");
        }
    })
    $(".btn-create-type").on("click",function(){
        var name=$("input[name='type-title']").val();
        var type=typeBlock.val();
        var description=$("textarea[name='type-description']").val();
        var addEvent=function(json){
            if(json.code=="SUCCESS"){
                alert("创建成功");
                window.location.href="/";
            }else{
                alert("创建失败，服务器出现问题，请联系管理员");
            }
        };
        if(type!=-1){
            console.log($("input[name='type-photo']")[0].files.length);
            if($("input[name='type-photo']")[0].files.length>0){
                sendFile2Server("/user/addTypePicture",$("input[name='type-photo']")[0].files[0],function(json){
                    var data={typeName:name,parentId:type,description:description,filePath:json.name};
                    sendData2Server("/user/addNewVideoType",data,addEvent);
                });
            }else{
                var data={typeName:name,parentId:type,description:description,filePath:null};
                sendData2Server("/user/addNewVideoType",data,addEvent);
            }
        }else{
            var data={typeName:name};
            sendData2Server("/user/addParentVideoType",data,addEvent);
        }
    });
}

function setDeleteVideoType(){
    var index=0;
    var selectArray={};
    $(".delete-type-select option").each(function(idx){
        selectArray[$(this).val()]=idx;
    });
    $(".delete-type-select").on("change",function(){
        $(".sub-type-block").eq(index).css("display","none");
        index=selectArray[$(this).val()];
        $(".sub-type-block").eq(index).css("display","block");
    });
    var deleteEvent=function(json){
        if(json.code=="SUCCESS"){
            alert("删除成功");
        }else{
            alert("删除失败");
        }
        window.location.reload();

    };
    //子类删除
    $(".sub-type-block p").each(function(){
        var id=$(this).find("span").eq(0).html();
        $(this).find("button").on("click",function(){
            var data={id:id,parentId:$(".delete-type-select").val()};
            sendData2Server("/user/deleteVideoType",data,deleteEvent);
        });
    });
    //父类删除
    $(".btn-del-parent-type").on("click",function(){
        var id=$(".delete-type-select").val();
        var data={id:id,parentId:-1};
        sendData2Server("/user/deleteVideoType",data,deleteEvent);
    })
}

function setDeleteVideo(){
    if($(".video-manage-block").length>0){
        $("h1 button").on("click",function(){
            window.location.href="/upload";
        })
        $(".video-manage-item").each(function(){
            var item=$(this);
            var id=item.find("span").eq(0).html();
            $(this).find("button").on("click",function(){
                sendData2Server("/user/deleteVideo",{videoId:id},function(json){
                    if(json.code=='SUCCESS'){
                        alert("删除成功");
                        item.remove();
                    }else{
                        alert("删除失败");
                    }
                })
            })
        });
        var preBtn=$(".video-manage-block .skip-block button:first-of-type");
        var nextBtn=$(".video-manage-block .skip-block button:last-of-type");
        var idx=parseInt($(".video-manage-block .skip-block span:first-of-type").html());
        var total=parseInt($(".video-manage-block .skip-block span:last-of-type").html());
        var input=$(".video-manage-block .skip-block input[type='number']");
        var inputBtn=$(".video-manage-block .skip-block button:nth-of-type(2)");
        setSKipButton(preBtn,nextBtn,input,inputBtn,idx,total,'/user/videoManage');
    }
}

function sendData2Server(url,data,done){
    $.ajax({
        url:url,
        data:JSON.stringify(data),
        type:"POST",
        dataType:"json",
        headers:{"Content-type":"application/json;charset=utf-8"}
    }).done(done);
};

function sendFile2Server(url,file,done){
    var formData=new FormData();
    formData.append("file",file);
    $.ajax({
        url:url,
        data:formData,
        processData: false,
        contentType: false,
        dataType:"json",
        type:"post"
    }).done(done);
}

function setSKipButton(preBtn,nextBtn,indexInput,indexBtn,index,total,url){
    if(index<=1)
        preBtn.attr("disabled",true);
    else
        preBtn.on("click",function(){
            skipEvent(index-1,total,url);
        });
    if(index>=total)
        nextBtn.attr("disabled",true);
    else
        nextBtn.on("click",function(){
            skipEvent(index+1,total,url);
        });
    indexBtn.on("click",function(){
        skipEvent(indexInput.val(),total,url);
    });
    indexInput.on("keydown",function(e){
        if(e.keyCode==13)
            skipEvent(indexInput.val(),total,url);
    })
}
function skipEvent(idx,total,url){
    if(idx<=0)
        window.location.href=url+"?idx=1";
    else if(idx>total)
        window.location.href=url+"?idx="+total;
    else
        window.location.href=url+"?idx="+idx;
}