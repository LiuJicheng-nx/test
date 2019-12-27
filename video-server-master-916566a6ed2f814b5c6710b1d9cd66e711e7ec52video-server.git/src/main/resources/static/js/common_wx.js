


var BASE_URL_1 = 'http://admin.guozhijian.net';


var BASE_URL_2 = 'http://172.20.10.2';



var BASE_URL = BASE_URL_1;



// 控制台打印数据

function log(n){

	console.log(JSON.stringify(n));

}



//默认toast

function toast(msg){
    // var toastFn = new auiToast();
    // toastFn.custom({
    //     title: msg,
    //     html:'',
    //     duration:2000
    // });
	alert(msg);

}

//去掉焦点
function blurInput(){
	$('input,textarea,select').trigger('blur');
}

// 将js对象转成url jquery实现
// var obj={
//     id:1,
//     name:"张三",
//     age:10
// };
// console.log(parseParam(obj))
// $("#btn-a").click(function(){
//     window.location.href="2.html?"+parseParam(obj);
// });
function parseParam(paramObj, key){
    var paramStr="";
    if(paramObj instanceof String||paramObj instanceof Number||paramObj instanceof Boolean){
      paramStr+="&"+key+"="+encodeURIComponent(paramObj);
    }else{
      $.each(paramObj,function(i){
        var k=key==null?i:key+(paramObj instanceof Array?"["+i+"]":"."+i);
        paramStr+='&'+parseParam(this, k);
      });
    }
    return paramStr.substr(1);
  };

//根据参数名称获取url参数
function getUrlParamValue(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURIComponent(r[2]);
    return null;
}

//获取url参数封装成对象  var obj=GetRequest();
function GetRequest() {

  var url = location.search; //获取url中"?"符后的字串
   var theRequest = new Object();
   if (url.indexOf("?") != -1) {
      var str = url.substr(1);
      strs = str.split("&");
      for(var i = 0; i < strs.length; i ++) {
         theRequest[strs[i].split("=")[0]]=decodeURIComponent((strs[i].split("=")[1]));
      }
   }
   return theRequest;
}
