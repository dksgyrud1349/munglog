<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>반려견 등록</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/button.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile/form.css">
</head>
<body>

<div class="container">

    <c:choose>
        <c:when test="${mode.name() eq 'CREATE'}">
            <h2>🐶 반려견 프로필 등록</h2>
            <form class="dog-form" method="post" action="${pageContext.request.contextPath}/users/dogs/create" enctype="multipart/form-data">

                <label>이미지</label>
                <input type="file" name="profileImgUrl" accept="image/*">

                <label>이름</label>
                <input type="text" name="dogNm">

                <label>견종</label>
                <input type="text" name="breed">

                <label>나이</label>
                <input type="number" name="age">

                <label>몸무게</label>
                <input type="number" name="weight">

                <button type="submit" class="btn-primary full" onclick="saveDog()">등록하기</button>
            </form>
        </c:when>
        <c:otherwise>
            <h2>🐶 반려견 프로필 수정</h2>
            <form class="dog-form" method="post" action="${pageContext.request.contextPath}/users/dogs/update" enctype="multipart/form-data">
                <input type="hidden" name="dogId" value="<c:out value='${proFile.dogId}'/>">
                <label>현재 이미지</label>

                <div class="current-image-box">
                    <img id="preview" src="${pageContext.request.contextPath}${proFile.profileImgUrl}" alt="${proFile.dogNm}">

                    <div class="image-name">
                        <c:out value="${proFile.dogNm}"/>
                    </div>
                </div>

                <input type="file" name="profileImgUrl" accept="image/*" onchange="changePreview(this)">

                <label>이름</label>
                <input type="text" name="dogNm" value="<c:out value='${proFile.dogNm}'/>">

                <label>견종</label>
                <input type="text" name="breed" value="<c:out value='${proFile.breed}'/>">

                <label>나이</label>
                <input type="number" name="age" value="<c:out value='${proFile.age}'/>">

                <label>몸무게</label>
                <input type="number" name="weight" value="<c:out value='${proFile.weight}'/>">

                <button type="submit" class="btn-primary full" onclick="saveDog()">수정하기</button>
            </form>
        </c:otherwise>
    </c:choose>

</div>
    <script src="${pageContext.request.contextPath}/js/dog/dog.js"></script>
</body>
</html>