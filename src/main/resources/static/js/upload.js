var currentIndex=0;
var data={};
$(function(){
    setVideoType();
    $('.upload-confirm').on("click",function(){
        $(".upload-confirm").attr("disabled",true);
        $(".progress-block").css("display","block");
        var file=$("input[name='videoFile']")[0].files[0];
        if(file!=undefined)
            sendVideoDataToServer("/upload/file",$("input[name='videoName']").val(),
                $("textarea[name='videoDescribe']").val(),data[currentIndex].find("option:selected").val(),file,function(json){
                console.log(json);
                var userData='{"Vod":{"StorageLocation":"","UserData":{"IsShowWaterMark":"false","Priority":"7"}}}';
                var uploader=new AliyunUpload.Vod({
                        partSize:1048576,//每次上传文件的大小
                        parallel:5,//上传线程的并行数量
                        retryCount:3,//上传失败时重试次数
                        retryDuration:2,//重试时间间隔,以分钟作为单位
                        'onUploadstarted':function(uploadInfo){
                            uploader.setUploadAuthAndAddress(uploadInfo,
                                json.uploadAuth,json.uploadAddress, json.videoId);
                        },
                        // 文件上传成功
                        'onUploadSucceed': function (uploadInfo) {
                            console.log("onUploadSucceed: " + uploadInfo.file.name + ", endpoint:" + uploadInfo.endpoint + ", bucket:" + uploadInfo.bucket + ", object:" + uploadInfo.object);
                            var video={"videoId":json.videoId,"name":$("input[name='videoName']").val(),
                                "describe":$("textarea[name='videoDescribe']").val(),
                                "videoType":{id:data[currentIndex].find("option:selected").val()}};
                            sendDataToServer("/upload/write",video,function(json){
                                alert(json.text);
                                if(json.code=="SUCCESS")
                                    window.location.reload();
                            })
                        },
                        // 文件上传失败
                        'onUploadFailed': function (uploadInfo, code, message) {
                            console.log("onUploadFailed: file:" + uploadInfo.file.name + ",code:" + code + ", message:" + message);
                        },
                        // 文件上传进度，单位：字节
                        'onUploadProgress': function (uploadInfo, totalSize, loadedPercent) {
                            console.log("onUploadProgress:file:" + uploadInfo.file.name + ", fileSize:" + totalSize + ", percent:" + Math.ceil(loadedPercent * 100) + "%");
                            $(".progress").css("width",Math.ceil(loadedPercent * 100)+"%");
                            $(".progress-text").html(Math.ceil(loadedPercent * 100)+"%");
                        },
                        // 上传凭证超时
                        'onUploadTokenExpired': function (uploadInfo) {
                            console.log("onUploadTokenExpired");
                            //上传方式1  实现时，根据uploadInfo.videoId调用刷新视频上传凭证接口重新获取UploadAuth
                            //uploader.resumeUploadWithAuth(uploadAuth);
                        },
                        //全部文件上传结束
                        'onUploadEnd':function(uploadInfo){
                            console.log("onUploadEnd: uploaded all the files");
                        }
                    })
                uploader.addFile(file,null,null,null,userData);
                uploader.startUpload();
            });
    })
})
function setVideoType(){
    $.each($(".father-type option"),function(idx,elem){
        data[$(elem).val()]=$(".sub-type").eq(idx);
    });
    currentIndex=$(".father-type option:selected").val();
    $(".father-type").on("change",function(){
        data[currentIndex].css("display","none");
        currentIndex=$(".father-type option:selected").val();
        data[currentIndex].css("display","inline-block");
    })
}
function sendVideoDataToServer(url,title,describe,type,file,done){
    var formData=new FormData();
    formData.append("fileName",file.name);
    formData.append("title",title);
    formData.append("describe",describe);
    formData.append("type",type)
    $.ajax({
        url:url,
        data:formData,
        processData: false,
        contentType: false,
        dataType:"json",
        type:"post",
    }).done(done)
}
function sendDataToServer(url,send_data,done){
    $.ajax({
        url:url,
        dataType:"json",
        data:JSON.stringify(send_data),
        headers:{'Content-type':'application/json;charset=utf-8'},
        type:"post"
    }).done(done).fail(function(xhr,status,errorThrown){
        alert("there is something wrong with server");
    });
}