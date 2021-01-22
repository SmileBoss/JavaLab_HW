<%@ page import="java.util.*" %>
<%@ page import="java.sql.SQLOutput" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" href="css/page-home-style.css">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta name="x-apple-disable-message-reformatting">
    <meta name="viewport" content="width=device-width, initial-scale=0.86, maximum-scale=3.0, minimum-scale=0.86">
</head>
<body>

<header class="side-header">
    <nav class="navbar-side"
    >

        <ul>

            <li class="active"><a href="/itis/HomePage">
                <div class="svg-space">
                    <svg version="1.1" x="0px" y="0px" viewBox="0 0 64 80"></svg>
                </div>
                Home</a>
            </li>
            <li><a href="/itis/Videos">
                <div class="svg-space">
                    <svg version="1.1" x="0px" y="0px" viewBox="0 0 100 125"></svg>
                </div>
                Videos</a>
            </li>
            <li><a href="/itis/Board">
                <div class="svg-space">
                    <svg version="1.1" x="0px" y="0px" viewBox="0 0 64 80" enable-background="new 0 0 64 64"></svg>
                </div>
                Bulletin board</a>
            </li>
            <li><a href="/itis/AboutUs">
                <div class="svg-space">
                    <svg version="1.1" x="0px" y="0px" viewBox="0 0 64 80" enable-background="new 0 0 64 64"></svg>
                </div>
                About Us</a>
            </li>

        </ul>
    </nav>
    <nav class="navbar-side" style="">
        <ul>

            <%
                String  href1 = "/itis/Login";
                String href2 = "/itis/Register";
                String text1 = "Login";
                String text2 = "Registration";

                Cookie[] cookies = request.getCookies();
                if (cookies != null){
                    for (Cookie cookie:cookies) {
                        if ("auth".equals(cookie.getName())){
                            href1 = "/itis/Logout";
                            href2 = "/itis/Profile";
                            text1 = "Logout";
                            text2 = "Profile";
                        }
                    }
                } else {
                    href1 = "/itis/Logout";
                    href2 = "/itis/Profile";
                    text1 = "Logout";
                    text2 = "Profile";
                }%>
            <li class="bottom_2"><a href="<%=href1%>">
                <div class="svg-space">
                    <svg version="1.1" x="0px" y="0px" viewBox="0 0 64 80" enable-background="new 0 0 64 64"></svg>
                </div>
                <%=text1%>
            </a>
            </li>
            <li class="bottom"><a href="<%=href2%>">
                <div class="svg-space">
                    <svg version="1.1" x="0px" y="0px" viewBox="0 0 64 80" enable-background="new 0 0 64 64"></svg>
                </div>
                <%=text2%>
            </a>
            </li>

        </ul>

    </nav>
</header>


<main class="main-wrapper">

    <header class="top-navbar">

        <div class="page-title">
            Home Page
        </div>


        <form class="searchbar">
            <input type="search" name="" placeholder="Search...">
            <a href="#" class="js-close-bar">
                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 54 67.5"
                     version="1.1" x="0px" y="0px">
                    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                        <g fill="#ffffff" fill-rule="nonzero">
                            <path d="M0.738461538,53.2615385 C1.10769231,53.6307692 1.56923077,53.8153846 2.07692308,53.8153846 C2.58461538,53.8153846 3.04615385,53.6307692 3.41538462,53.2615385 L27,29.6769231 L50.5846154,53.2615385 C50.9538462,53.6307692 51.4153846,53.8153846 51.9230769,53.8153846 C52.3846154,53.8153846 52.8923077,53.6307692 53.2615385,53.2615385 C54,52.5230769 54,51.3230769 53.2615385,50.5846154 L29.6769231,27 L53.2615385,3.41538462 C54,2.67692308 54,1.47692308 53.2615385,0.738461538 C52.5230769,-8.60422844e-16 51.3230769,-8.60422844e-16 50.5846154,0.738461538 L27,24.3230769 L3.41538462,0.738461538 C2.67692308,-2.77555756e-17 1.47692308,-2.77555756e-17 0.738461538,0.738461538 C-2.77555756e-17,1.47692308 -2.77555756e-17,2.67692308 0.738461538,3.41538462 L24.3230769,27 L0.738461538,50.5846154 C-2.77555756e-17,51.3230769 -2.77555756e-17,52.5230769 0.738461538,53.2615385 Z"/>
                        </g>
                    </g>
                </svg>
            </a>
        </form>

        <nav class="navbar-top">
            <ul>
                <li class="menu-toggle">
                    <a href="#">
                        <svg version="1.1" x="0px" y="0px" viewBox="0 0 100 125" enable-background="new 0 0 100 100"
                             xml:space="preserve"><g>
                            <g>
                                <path d="M4.667,82.982H95v-6.018H4.667V82.982z M4.667,52.895H95v-6.018H4.667V52.895z M4.667,19.798v6.018H95v-6.018H4.667z"/>
                            </g>
                        </g></svg>
                    </a>
                </li>
            </ul>
        </nav>

    </header>
    <section class="works smooth-in">

        <div class="work">
            <a href="/itis/Videos"><img src="images/video-block.jpg"></a>
            <a href="/itis/Videos">Videos</a>
        </div>
        <div class="work">
            <a href="/itis/Board"><img src="images/bulletin-board-block.jpg"></a>
            <a href="/itis/Board">Bulletin Board</a>
        </div>
        <div class="work">
            <a href="/itis/AboutUs"><img src="images/about-us-block.jpg"></a>
            <a href="/itis/AboutUs">About Us</a>
        </div>

    </section>
    <footer>
        <nav class="footer-nav">
            <ul>
                <li>
                    <a href="#"><i class="fab fa-facebook-square"></i></a>
                </li>
                <li>
                    <a href="#"><i class="fab fa-dribbble-square"></i></a>
                </li>
                <li>
                    <a href="#"><i class="fab fa-codepen"></i></a>
                </li>
                <li>
                    <a href="#"><i class="fab fa-github-square"></i></a>
                </li>
                <li>
                    <a href="#">nfska3@gmail.com</a>
                </li>
            </ul>
        </nav>
    </footer>
</main>


<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script type="text/javascript" src="js/side-bar-script.js"></script>
</body>
</html>