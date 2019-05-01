<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>login test</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="ajax">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript">
        function login() {
            $.ajax({
            //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                // content-type: "application/json",
                url: "http://localhost:8080/auth" ,//url
                data: $('#form1').serialize(),
                contentType:"application/x-www-form-urlencoded",//(可以)
                success : function (result) {
                    console.log("success");
                    console.log(result);//打印服务端返回的数据(调试用)
                    if (result.resultCode == 200) {
                        alert("SUCCESS");
                    }
                    ;
                },
                error : function(res) {
                  console.log(res);
                  console.log("异常");
                    alert("异常！");
                    if (res.resultCode == 200) {
                        console.log("SUCCESS");
                    }
                }
            });
        }
    </script>
</head>
<body>
<div id="form-div">
    <form id="form1" onsubmit="return false" action="##" method="post">
        <p>用户名：<input name="userName" type="text" id="txtUserName" tabindex="1" size="15" value="admin"/></p>
        <p>密　码：<input name="password" type="password" id="TextBox2" tabindex="2" size="16" value="admin"/></p>
        <p><input type="buton" value="登录" onclick="login()">&nbsp;<input type="reset" value="重置"></p>
    </form>
</div>
</body>
</html>




<!-- auth/edu/all 连接成功，origin：null               -->
<!-- <html>
<head>
<script src="jquery-3.3.1.js"></script>
<script type="text/javascript">
function loadXMLDoc()
{
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("POST","http://localhost:8080/auth/edu/all",true);
xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
xmlhttp.send("username=admin&password=admin");
}
</script>
</head>
<body>

<h2>AJAX</h2>
<button type="button" onclick="loadXMLDoc()">请求数据</button>
<div id="myDiv"></div>
 
</body>
</html>
 -->