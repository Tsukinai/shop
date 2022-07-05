<%--
  Created by IntelliJ IDEA.
  User: dark
  Date: 2022/6/2
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="zl-header">
    <div class="zl-hd w1200">
        <p class="hd-p1 f-l">

            <c:if test="${!empty user}">
                Hi!您好 [${user.username}] ，欢迎来到撒欢购</a>
            </c:if>
            <c:if test="${empty user}">
                Hi!您好，欢迎来到撒欢购，请登录 <a href="http://localhost:7070/SSM-EC/user/tologin">【登录】</a>
            </c:if>


        </p>
        <p class="hd-p2 f-r">
            <a href="${pageContext.request.contextPath}/user/toregister">免费注册</a><span>|</span>
            <a href="${pageContext.request.contextPath}/index/toindex">返回首页 </a><span>|</span>
            <a href="${pageContext.request.contextPath}/cart/docart">我的购物车</a><span>|</span>
            <a href="${pageContext.request.contextPath}/order/toMyOrder">我的订单</a>
        </p>
        <div style="clear:both;"></div>
    </div>
</div>