(function (factory) {
    if (typeof exports === "object") {
        module.exports = factory()
    } else if (typeof define === "function" && define.amd) {
        define(factory)
    } else {
        var glob;
        try {
            glob = window
        } catch (e) {
            glob = self
        }
        glob.YUploader = factory()
    }
})(function (undefined) {

    function generateChunkMd5(option) {
        var chunk = option.chunk,
            spark = new SparkMD5(),
            fileReader = new FileReader();

        fileReader.readAsBinaryString(chunk);

        fileReader.onload = function (e) {
            if (e.target.readyState === FileReader.DONE) {
                spark.appendBinary(e.target.result);
                var chunkMd5 = spark.end();
                if (option && option.callback) {
                    console.log(chunkMd5);
                    option.callback(option, chunkMd5);
                }
            }
        };

        fileReader.onerror = function () {
            console.log("Generate md5 failed.");
        };
    }

    // 上传成功响应
    function uploadComplete(result) {
        var data = JSON.parse(result.target.responseText);
        if (data.success) {
            console.log("上传成功");
        } else {
            console.log("上传失败");
        }
    }

    // 上传失败
    function uploadFailed(result) {
        console.log(result);
        console.log("上传失败");
    }

    function YUploader() {
        // this.reset();
        return this;
    }

    // 开始分片上传
    var chunk_upload = function (data, chunkMd5) {
        var chunk = data.chunk,
            fileMd5 = data.fileMd5,
            chunkNum = data.chunkNum,
            url = data.url,
            form = new FormData(),
            xhr = new XMLHttpRequest();

        form.append('file', chunk, chunk.name);
        form.append('fileMd5', fileMd5);
        form.append('chunkMd5', chunkMd5);
        form.append('chunkNum', chunkNum);

        // post 方式，url 服务器请求地址，true 开启异步处理
        xhr.open("post", url, true);

        // 请求完成
        xhr.onload = uploadComplete;
        // 请求失败
        xhr.onerror = uploadFailed;

        xhr.send(form);
    };
    YUploader.prototype.upload = chunk_upload;


    // 开始计算md5;
    YUploader.prototype.start = function (params) {
        var option = {
            url: params.url,
            chunk: params.chunk,
            fileMd5: params.fileMd5,
            chunkNum: params.chunkNum,
            callback: chunk_upload
        };
        generateChunkMd5(option);
    };

    return YUploader;
});