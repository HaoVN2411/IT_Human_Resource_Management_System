<%-- 
    Document   : main
    Created on : Jun 22, 2023, 10:06:46 PM
    Author     : flami
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Responesive Admin Dashboard | Redesign</title>
        <link rel="stylesheet" type="text/css" href="CSS/main.css">
    </head>

    <body>
        <header>
            <div class="toggle">
                <ion-icon name="menu-outline"></ion-icon>
            </div>
            <!--            <div class="user-info">
                            <div class="user-options">
                                <a href="#">Đăng xuất</a>
                                <a href="#">Xem thông tin cá nhân</a>
                            </div>
                        </div>
                        <a href="#">
                            <div class="user">
                                <img src="imageCandidate/image_C1111.jpg">
                            </div>
                        </a>-->
            <div class="user-info">
                <div class="user-options">
                    <a href="#">Đăng xuất</a>
                    <a href="#">Xem thông tin cá nhân</a>
                </div>
            </div>
            <a href="#">
                <div class="user">
                    <img src="imageCandidate/image_C1111.jpg" alt="Hình ảnh người dùng">
                </div>
            </a>
            <div class="notification">
                <ion-icon name="notifications-circle-outline"></ion-icon>
            </div>


        </header>
        <div class="navigation">
            <ul>
                <li>
                    <a href="#" class="includeButton" data-url="searchCandidate.jsp">
                        <span class="icon"><ion-icon name="file-tray-full-outline"></ion-icon></span>
                        <span class="title">Home Menu</span>
                    </a>
                </li>
                <li>
                    <a href="#" class="includeButton" data-url="createCandidate.jsp">
                        <span class="icon"><ion-icon name="people-outline"></ion-icon></span>
                        <span class="title">Attendance</span>
                    </a>
                </li>

                <li class="dropdown-toggle">
                    <a href="#" onclick="toggleDropdown('information-dropdown')">
                        <span class="icon"><ion-icon name="file-tray-full-outline"></ion-icon></span>
                        <span class="title">View Information</span>
                        <span class="arrow"></span><ion-icon name="chevron-down-outline"></ion-icon></span>
                    </a>
                    <div class="submenu" id="information-dropdown" style="display:none;">
                        <a href="#">
                            <span class="icon"><ion-icon name="chevron-forward-circle-outline"></ion-icon></ion-icon></span>
                            <span class="title">User Profile</span>

                        </a>
                        <a href="#">
                            <span class="icon"><ion-icon name="chevron-forward-circle-outline"></ion-icon></ion-icon></span>
                            <div style="padding-left: 20px;">Staff Information</div>
                        </a>
                    </div>
                </li>

                <li class="dropdown-toggle">
                    <a href="#" onclick="toggleDropdown('overtime-dropdown')">
                        <span class="icon"><ion-icon name="calendar-outline"></ion-icon></span>
                        <span class="title">OverTime Report</span>
                        <span class="arrow"></span><ion-icon name="chevron-down-outline"></ion-icon></span>
                    </a>
                    <div class="submenu" id="overtime-dropdown" style="display:none;">
                        <a href="#">
                            <span class="icon"><ion-icon name="chevron-forward-circle-outline"></ion-icon></ion-icon></span>
                            <div style="padding-left: 20px;">Create</div>
                        </a>
                        <a href="#">
                            <span class="icon"><ion-icon name="chevron-forward-circle-outline"></ion-icon></ion-icon></span>
                            <div style="padding-left: 20px;">Personal Reports</div>
                        </a>
                        <a href="#" class="includeButton" data-url="staff_1.jsp">
                            <span class="icon"><ion-icon name="chevron-forward-circle-outline"></ion-icon></ion-icon></span>
                            <div style="padding-left: 20px;" >Staff Reports</div>
                        </a>
                    </div>
                </li>
                <li class="dropdown-toggle">
                    <a href="#" onclick="toggleDropdown('leave-dropdown')">
                        <span class="icon"><ion-icon name="calendar-number-outline"></ion-icon></span>
                        <span class="title">Leave Application</span>
                        <span class="arrow"></span><ion-icon name="chevron-down-outline"></ion-icon></span>
                    </a>
                    <div class="submenu" id="leave-dropdown" style="display:none;">
                        <a href="#" class="includeButton" data-url="printContract.jsp">
                            <span class="icon"><ion-icon name="chevron-forward-circle-outline"></ion-icon></ion-icon></span>
                            <div style="padding-left: 20px;">Create</div>
                        </a>
                        <a href="#">
                            <span class="icon"><ion-icon name="chevron-forward-circle-outline"></ion-icon></ion-icon></span>
                            <div style="padding-left: 20px;">Personal LeaveLog</div>
                        </a>
                        <a href="#">
                            <span class="icon"><ion-icon name="chevron-forward-circle-outline"></ion-icon></ion-icon></span>
                            <div style="padding-left: 20px;">Staff LeaveLog</div>
                        </a>
                    </div>
                </li>
                <li class="dropdown-toggle">
                    <a href="#" onclick="toggleDropdown('contract-dropdown')">
                        <span class="icon"><ion-icon name="newspaper-outline"></ion-icon></span>
                        <span class="title">Candidate</span>
                        <span class="arrow"></span><ion-icon name="chevron-down-outline"></ion-icon></span>
                    </a>
                    <div class="submenu" id="contract-dropdown" style="display:none;">
                        <a href="#" class="includeButton" data-url="">
                            <span class="icon"><ion-icon name="chevron-forward-circle-outline"></ion-icon></ion-icon></span>
                            <div style="padding-left: 20px;">Create</div>
                        </a>
                        <a href="#" class="includeButton" data-url="searchContract.jsp">
                            <span class="icon"><ion-icon name="chevron-forward-circle-outline"></ion-icon></ion-icon></span>
                            <div style="padding-left: 20px;">View candidate</div>
                        </a>
                    </div>
                </li>
            </ul>
        </div>
        <!--main-->
        <div class="main">
            <div class="topbar">
                <h1>Wellcom back</h1>
                <p id="current-time"></p>
            </div>
            <div id="includedContent"></div>
             <jsp:include page="${URL}" />
        </div>


        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <script src="JS/main.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <script>
                        $(document).ready(function () {
                            $(".includeButton").click(function () {
                                var url = $(this).data("url");
                                $.ajax({
                                    url: url,
                                    success: function (result) {
                                        $("#includedContent").html(result);
                                    }
                                });
                            });
                        });
                        function loadContent(url) {
                            $.ajax({
                                url: url,
                                success: function (result) {
                                    $(".main #includedContent").html(result);
                                }
                            });
                        }
//                        $(document).ready(function () {
//                            $(".includeButton").click(function () {
//                                var url = $(this).data("url");
//                                sessionStorage.setItem("currentPage", url); // Lưu trạng thái vào sessionStorage
//                                loadContent(url);
//                            });
//
//                            // Kiểm tra nếu có trạng thái được lưu trong sessionStorage
//                            var currentPage = sessionStorage.getItem("currentPage");
//                            if (currentPage) {
//                                loadContent(currentPage); // Tải nội dung từ trang đã lưu
//                            }
//                        });



//                        $(document).ready(function () {
//                            $(".includeButton").click(function () {
//                                var url = $(this).data("url");
//                                sessionStorage.setItem("currentPage", url); // Lưu trạng thái vào sessionStorage
//                                loadContent(url);
//                            });
//
//                            // Hàm tải nội dung từ URL đã lưu
//                            function loadContent(url) {
//                                $.ajax({
//                                    url: url,
//                                    success: function (result) {
//                                        $("#includedContent").html(result);
//                                    }
//                                });
//                            }
//
//                            // Kiểm tra nếu có trạng thái được lưu trong sessionStorage
//                            var currentPage = sessionStorage.getItem("currentPage");
//                            if (currentPage) {
//                                loadContent(currentPage); // Tải nội dung từ trang đã lưu
//                            }
//                        });



                        // Lắng nghe sự kiện click trên các thẻ <a> trong phần navigation
                        document.addEventListener("DOMContentLoaded", function () {
                            var navLinks = document.querySelectorAll(".navigation a");
                            for (var i = 0; i < navLinks.length; i++) {
                                navLinks[i].addEventListener("click", function () {
                                    // Xóa class "active" khỏi tất cả các thẻ <a> trong navigation
                                    for (var j = 0; j < navLinks.length; j++) {
                                        navLinks[j].classList.remove("active");
                                    }
                                    // Thêm class "active" cho thẻ <a> được nhấn
                                    this.classList.add("active");
                                });
                            }
                        });


                        // Lấy các phần tử cần sử dụng
                        var userWrapper = document.querySelector('.user');
                        var userOptions = document.querySelector('.user-options');

// Xử lý sự kiện hover
                        userWrapper.addEventListener('mouseover', function () {
                            userOptions.classList.add('active');
                        });

                        userWrapper.addEventListener('mouseout', function (e) {
                            // Kiểm tra nếu con trỏ chuột không nằm trên phần tử user-options
                            if (!userOptions.contains(e.relatedTarget)) {
                                userOptions.classList.remove('active');
                            }
                        });

                        userOptions.addEventListener('mouseout', function (e) {
                            // Kiểm tra nếu con trỏ chuột không nằm trên phần tử user hoặc user-options
                            if (!userWrapper.contains(e.relatedTarget)) {
                                userOptions.classList.remove('active');
                            }
                        });


// Lấy thời gian hiện tại
                        var currentTime = new Date();

// Định dạng ngày tháng và thời gian
                        var day = currentTime.getDate();
                        var month = currentTime.getMonth() + 1; // Tháng bắt đầu từ 0
                        var year = currentTime.getFullYear();
                        var hours = currentTime.getHours();
                        var minutes = currentTime.getMinutes();
                        var seconds = currentTime.getSeconds();

// Gán nội dung thời gian vào phần tử HTML
                        var timeString = "Ngày " + day + "/" + month + "/" + year + ", Lúc " + hours + ":" + minutes + ":" + seconds;
                        var currentTimeElement = document.getElementById("current-time");
                        currentTimeElement.innerHTML = timeString;

// Cập nhật thời gian sau mỗi giây
                        setInterval(function () {
                            // Lấy thời gian hiện tại
                            currentTime = new Date();

                            // Định dạng lại thời gian
                            day = currentTime.getDate();
                            month = currentTime.getMonth() + 1; // Tháng bắt đầu từ 0
                            year = currentTime.getFullYear();
                            hours = currentTime.getHours();
                            minutes = currentTime.getMinutes();
                            seconds = currentTime.getSeconds();

                            // Gán nội dung thời gian vào phần tử HTML
                            timeString = "Ngày " + day + "/" + month + "/" + year + ", Lúc " + hours + ":" + minutes + ":" + seconds;
                            currentTimeElement.innerHTML = timeString;
                        }, 1000); // Cập nhật sau mỗi giây (1000ms)
//Menutoggle    
                        let toggle = document.querySelector('.toggle');
                        let navigation = document.querySelector('.navigation');
                        let main = document.querySelector('.main');

                        toggle.onclick = function () {
                            navigation.classList.toggle('active');
                            main.classList.toggle('active');
                        }
//add hovered class in selected list item
                        let list = document.querySelectorAll('.navigation li');
                        function activeLink() {
                            list.forEach((item) =>
                                item.classList.remove('hovered'));
                            this.classList.add('hovered');
                        }
                        list.forEach((item) =>
                            item.addEventListener('mouseover', activeLink));
//Lấy đối tượng nút Logout
                        const logoutButton = document.querySelector(".logout");

//Gán sự kiện click cho nút Logout
                        logoutButton.addEventListener('click', () => {
                            const confirmLogout = confirm("Bạn có chắc muốn đăng xuất?"); //Hiển thị thông báo xác nhận

                            if (confirmLogout) {
                                // Nếu người dùng nhấn "OK" cho thông báo xác nhận, chuyển hướng người dùng tới trang khác.
                                window.location.replace("login.html"); //chuyển hướng người dùng tới trang Google
                            } else {
                                // Nếu người dùng nhấn "Cancel" cho thông báo xác nhận, không làm gì cả.
                                return;
                            }
                        });

// Lấy thông tin về các menu item có submenu
                        var itemsWithSubmenu = document.getElementsByClassName('has-submenu');

// Lặp qua các menu item và xác định sự kiện click để hiển thị hoặc ẩn submenu tương ứng
                        for (var i = 0; i < itemsWithSubmenu.length; i++) {
                            var item = itemsWithSubmenu[i];
                            item.addEventListener('click', function () {
                                var submenu = this.nextElementSibling;
                                if (submenu.classList.contains('active')) {
                                    submenu.classList.remove('active');
                                } else {
                                    // Ẩn tất cả submenu
                                    var submenus = document.querySelectorAll('.submenu.active');
                                    for (var i = 0; i < submenus.length; i++) {
                                        submenus[i].classList.remove('active');
                                    }

                                    // Hiển thị submenu của item được click
                                    submenu.classList.add('active');
                                }
                            });
                        }

                        function toggleDropdown(id) {
                            var dropdown = document.getElementById(id);
                            if (dropdown.style.display === "none") {
                                dropdown.style.display = "block";
                            } else {
                                dropdown.style.display = "none";
                            }
                        }

                        const importLink = document.querySelector('link[rel="import"]');
                        const content = importLink.import.querySelector('#childContent');
                        document.body.appendChild(content.cloneNode(true));

// ẩn hiện ở hình ảnh user
//function showUserInfo() {
//    var userOptions = document.querySelector('.user-options');
//    userOptions.style.display = 'block';
//}
//
//function hideUserInfo() {
//    var userOptions = document.querySelector('.user-options');
//    userOptions.style.display = 'none';
//}








        </script>
    </body>

</html>
