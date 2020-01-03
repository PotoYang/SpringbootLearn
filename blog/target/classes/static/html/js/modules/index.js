// 扩展一个 index 模块
layui.define(function (exports) {
    var hello = {
        hello: function (str) {
            alert('Hello ' + (str || 'index'));
            con(str || 'index');
        }
    };

    function con(str) {
        console.log(str || 'index');
    }

    exports('index', hello);
});