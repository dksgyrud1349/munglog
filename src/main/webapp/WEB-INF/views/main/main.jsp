<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>멍로그</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main/main.css">
    </head>

    <body>
        <!-- HEADER -->
        <header class="header">
            <div class="logo">🐶 멍로그</div>

            <nav class="nav">
                <a href="#">기능</a>
                <a href="#">통계</a>
                <a href="#">로그인</a>
            </nav>

            <button class="menu-btn" onclick="toggleMenu()">☰</button>
        </header>

        <!-- MOBILE MENU -->
        <div class="mobile-menu" id="mobileMenu">
            <a href="#">기능</a>
            <a href="#">통계</a>
            <a href="#">로그인</a>
        </div>

        <!-- HERO -->
        <section class="hero">
            <div class="hero-text">
                <h1>반려견 산책을<br/>데이터로 기록하다</h1>
                <p>산책 거리, 시간, 경로까지 한눈에 관리하세요.</p>

                <div class="cta">
                    <button class="primary">시작하기</button>
                    <button class="secondary">로그인</button>
                    <button class="secondary" onclick="location.href='${pageContext.request.contextPath}/users/dogs/list'">내 반려견 목록</button>
                </div>
            </div>

            <div class="hero-image">
                🐕‍🦺
            </div>
        </section>

        <!-- FEATURES -->
        <section class="features">
            <div class="card">
                <h3>🐾 산책 기록</h3>
                <p>GPS 기반으로 자동 기록</p>
            </div>

            <div class="card">
                <h3>📊 통계 분석</h3>
                <p>주간/월간 활동 분석</p>
            </div>

            <div class="card">
                <h3>📍 경로 저장</h3>
                <p>자주 가는 산책 코스 저장</p>
            </div>
        </section>

        <!-- FOOTER -->
        <footer class="footer">
            © 2026 MungLog. All rights reserved.
        </footer>
        <script src="${pageContext.request.contextPath}/js/main/main.js"></script>
    </body>
</html>