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

    function generateMd5(option) {
        var file = option.file,
            spark = new SparkMD5(),
            fileReader = new FileReader();

        fileReader.readAsBinaryString(file);

        fileReader.onload = function (e) {
            if (e.target.readyState === FileReader.DONE) {
                spark.appendBinary(e.target.result);
                var md5 = spark.end();
                if (option && option.callback) {
                    console.log(md5);
                    option.callback(option, md5);
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
    var chunk_upload = function (data, md5) {
        var file = data.file,
            chunkNum = data.chunkNum,
            url = data.url,
            form = new FormData(),
            xhr = new XMLHttpRequest();

        form.append('file', file, file.name);
        form.append('chunkMd5', md5);
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
            file: params.file,
            chunkNum: params.chunkNum,
            callback: chunk_upload
        };
        generateMd5(option);
    };

    return YUploader;
});