<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/profile-style.css">
    <title>Profile</title>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta name="x-apple-disable-message-reformatting">
    <meta name="viewport" content="width=device-width, initial-scale=0.86, maximum-scale=3.0, minimum-scale=0.86">
</head>
<body>
<div class="profile-container">
    <div class="container">
        <div class="profile-small">
            <div class="header">
            </div>
            <div class="profile-image">

                <img src="/images/user_icon.png" class="icon_update"/>
            </div>
            <div class="text">
                <div class="gg">
                    <h3>Hello, ${nickname}</h3>
                    <a href="#" class="setting" id="MyBtn"><i class="fa fa-cog" aria-hidden="true"> Edit profile</i></a>
                </div>
                <h4>Email: <p>${email}</p></h4>
            </div>
            <div class="table">
                <div class="row">
                    <div class="item video_add" data-toggle="modal" data-target="#exampleModal">
                        <i class="fa fa-film"></i>
                        <div class="comment bottom">Add Video</div>
                    </div>

                    <div class="item1 advert_add">
                        <i class="fa fa-comment"></i>
                        <div class="comment bottom">My Ads</div>
                    </div>

                    <a href="/itis/HomePage">
                    <div class="item">
                        <i class="fa fa-home" aria-hidden="true"></i>
                        <div class="comment bottom">Home</div>
                    </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="page-wrapper">
    <div class="modal modal_animation">
        <div class="modal_content">
            <div class="modal_content__header">
                <span class="modal_close">&times;</span>
            </div>
            <div class="container">
                <form action="Update" id="form-update" method="post">
                    <h1>Setting</h1>
                    <i>Email Address: </i>
                    <input class="email" type="email" name="email" value="${email}"/>
                    <i>Nickname: </i>
                    <input class="name" type="text" name="nickname" value="${nickname}"/>

                    <a href="#" class="edit_pass" id="e_pass_btn"><i class="fa fa-cog" aria-hidden="true"> Edit
                        Password</i></a>
                    <div id="edit-password">
                        <input class="password" type="password" name="pass" id="pass" placeholder="Old Password"/>
                        <input class="password" type="password" name="newPass" placeholder="New Password"/>
                    </div>
                    <button class="submit" type="submit" form="form-update">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>


<%--div addvideo--%>

<div class="win_video">
    <div class="container">
        <div class="profile-small">
            <form id="video_form" action="/itis/AddVideo" method="post" accept-charset="UTF-8">
                <h1>Add Video</h1>
                <i>Video Url: </i>
                <input type="text" name="url">
                <i>Title: </i>
                <input type="text" name="name">
                <i>Icon Url: </i>
                <input type="text" name="icon">
                <i>Mini Description: </i>
                <input type="text" name="miniDesc">
                <i>Description: </i>
                <input type="text" name="desc">
                <button type="submit" form="video_form"> Add Video</button>
            </form>
        </div>
    </div>
</div>

<%--div updateIcon--%>
<div class="win_icon">
    <div class="container">
        <div class="profile-small">
            <form id="icon_form" action="/itis/UpdateIcon" method="post" accept-charset="UTF-8">
                <h1>Update Icon</h1>
                <i>Url Icon: </i>
                <input type="text" name="url">
                <button type="submit" form="icon_form"> Submit</button>
            </form>
        </div>
    </div>
</div>

<div class="win_advert">
    <div class="container">
        <div class="profile-small">
            <form id="advert_form" action="/itis/AdvertAdd" method="post" accept-charset="UTF-8">
                <h1>Add Advert</h1>
                <i>Title: </i>
                <input type="text" name="name">
                <i>Text: </i>
                <input type="text" name="description">
                <button type="submit" form="advert_form">Add Advert</button>
            </form>
        </div>
    </div>
</div>

<script src="js/modal-win-profile.js"></script>
</body>
</html>
