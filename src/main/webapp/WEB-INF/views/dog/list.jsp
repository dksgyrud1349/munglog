<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>반려견 목록</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/button.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile/list.css">
    </head>
    <body>

    <div class="container">
        <div class="header">
            <h2>🐶 내 반려견 목록</h2>
            <button class="btn-primary" onclick="dogCreateForm()">+ 등록</button>
        </div>
        <c:choose>
            <c:when test="${not empty dogList}">
                <div class="dog-list">
                    <c:forEach var="profile" items="${dogList}" varStatus="status">
                        <div class="dog-card">
                            <div class="dog-avatar">
                                <img src="${profile.profileImgUrl}" alt="${profile.dogNm}">
                            </div>
                            <div class="dog-info">
                                <div class="name"><c:out value="${profile.dogNm}"/></div>
                                <div class="meta"><c:out value="${profile.breed}"/> · <c:out value="${profile.age}"/>살 · <c:out value="${profile.weight}"/>kg</div>
                            </div>

                            <div class="actions">
                                <button class="btn-small" onclick="dogEditForm('${profile.dogId}')">수정</button>
                                <button class="btn-small danger" onclick="deleteDog('${profile.dogId}')">삭제</button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <p style="text-align:center">등록된 반려견 정보가 없습니다.</p>
            </c:otherwise>
        </c:choose>
    </div>
    <script>
        const contextPath = "${pageContext.request.contextPath}";
        <c:if test="${not empty message}">
            alert("${message}");
        </c:if>
    </script>
    <script src="${pageContext.request.contextPath}/js/dog/dog.js"></script>
</body>
</html>