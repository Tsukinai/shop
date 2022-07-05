<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>支付界面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/shopping-mall-index.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zhonglin.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zhongling2.js"></script>
</head>

<body style="position:relative;">


<!--header-->
<%@ include file="common/header.jsp" %>

<!--logo search erweima-->
<%@ include file="common/erweima.jsp" %>
    
    <!--内容开始-->
    <p>
        <img style="margin: 0 auto" src="${pageContext.request.contextPath}/wxpay/dopay">
    </p>
<!--底部一块-->
<%@ include file="common/bottom.jsp" %>
<script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.2.1.js"></script>
<script type="text/javascript">
    var goeasy = GoEasy.getInstance({
        host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BC-446a48ba056f4cb8929b1056ec944cdc" //替换为您的应用appkey
    });

    goeasy.connect({
        onSuccess: function () {  //连接成功
            console.log("GoEasy connect successfully.") //连接成功
        },
        onFailed: function (error) { //连接失败
            console.log("Failed to connect GoEasy, code:"+error.code+ ",error:"+error.content);
        },
        onProgress:function(attempts) { //连接或自动重连中
            console.log("GoEasy is connecting", attempts);
        }
    });

    goeasy.subscribe({
        channel: "WXPay",//替换为您自己的channel
        onMessage: function (message) {
            console.log("Channel:" + message.channel + " content:" + message.content);
            window.location.href="${pageContext.request.contextPath}/order/topaySuccess?oId=${oId}";
        },
        onSuccess: function () {
            console.log("Channel订阅成功。");
        },
        onFailed: function (error) {
            console.log("Channel订阅失败, 错误编码：" + error.code + " 错误信息：" + error.content)
        }
    });


</script>
</body>
</html>
