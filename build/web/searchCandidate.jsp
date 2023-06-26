<%-- 
    Document   : viewCandidate
    Created on : Jun 16, 2023, 1:27:26 PM
    Author     : flami
--%>

<%@page import="java.util.List"%>
<%@page import="Candidate.Candidate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Responesive Admin Dashboard | Redesign</title>
        <link rel="stylesheet" type="text/css" href="CSS/css.css">

    </head>

    <body>
        <header>
            <div class="toggle">
                <ion-icon name="menu-outline"></ion-icon>
            </div>
            <div class="logout">
                <ion-icon name="log-out-outline"></ion-icon>
            </div>
        </header>

        <div class="container">

            <div class="user">
                <div class="img">
                    <img src="Hinh-chan-dung-nam-dep.jpg">
                </div>
                <div class="infor">
                    <ul>
                        <li>
                            <a href="#">
                                <span class="icon"><ion-icon name="alert-circle-outline"></ion-icon></span>
                            </a>
                            <a href="#">
                                <span class="icon"><ion-icon name="people-outline"></ion-icon></span>
                            </a>
                            <a href="#">
                                <span class="icon"><ion-icon name="notifications-outline"></ion-icon></span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="navigation">
                <ul>
                    <li>
                        <a href="a.html" class="menu">
                            <span class="icon"><ion-icon name="file-tray-full-outline"></ion-icon></span>
                            <span class="title">Home Menu</span>
                        </a>
                    </li>
                    <li>
                        <a href="b.html">
                            <span class="icon"><ion-icon name="people-outline"></ion-icon></span>
                            <span class="title">Attendance</span>
                        </a>
                    </li>

                    <li class="dropdown-toggle">
                        <a href="#" onclick="toggleDropdown('information-dropdown')">
                            <span class="icon"><ion-icon name="file-tray-full-outline"></ion-icon></span>
                            <span class="title">View Information</span>
                        </a>
                        <div class="submenu" id="information-dropdown" style="display:none;">
                            <a href="Hao.html">
                                <div style="padding-left: 20px;">Thông tin 1</div>
                            </a>
                            <br>
                            <a href="#">
                                <div style="padding-left: 20px;">Thông tin 2</div>
                            </a>
                            <br>
                        </div>
                    </li>

                    <li class="dropdown-toggle">
                        <a href="#" onclick="toggleDropdown('overtime-dropdown')">
                            <span class="icon"><ion-icon name="calendar-outline"></ion-icon></span>
                            <span class="title">OverTime Report</span>
                        </a>
                        <div class="submenu" id="overtime-dropdown" style="display:none;">
                            <a href="cacloaidon.html">
                                <div style="padding-left: 20px;">tao don</div>
                            </a>
                            <br>
                            <a href="comfirm.html">
                                <div style="padding-left: 20px;">comfirm</div>
                            </a>
                            <br>
                        </div>
                    </li>

                    <li class="dropdown-toggle">
                        <a href="#" onclick="toggleDropdown('leave-dropdown')">
                            <span class="icon"><ion-icon name="calendar-number-outline"></ion-icon></span>
                            <span class="title">Leave Application</span>
                        </a>
                        <div class="submenu" id="leave-dropdown" style="display:none;">
                            <a href="#">
                                <div style="padding-left: 20px;">Create</div>
                            </a>
                            <br>
                            <a href="#">
                                <div style="padding-left: 20px;">Personal LeaveLog</div>
                            </a>
                            <br>
                            <a href="#">
                                <div style="padding-left: 20px;">Staff LeaveLog</div>
                            </a>
                            <br>
                            <a href="#">
                                <div style="padding-left: 20px;">HR Staff LeaveLog</div>
                            </a>
                        </div>
                    </li>
                    <li class="dropdown-toggle">
                        <a href="#" onclick="toggleDropdown('contract-dropdown')">
                            <span class="icon"><ion-icon name="newspaper-outline"></ion-icon></span>
                            <span class="title">Candidate</span>
                        </a>
                        <div class="submenu" id="contract-dropdown" style="display:none;">
                            <a href="#">
                                <div style="padding-left: 20px;">View Candidate</div>
                            </a>
                            <br>
                            <a href="#">
                                <div style="padding-left: 20px;">Contract 2</div>
                            </a>
                            <br>
                        </div>
                    </li>
                </ul>
            </div>


        </div>
        <!--main-->
        <div class="main">
            <div class="topbar">
                <h1>Wellcom back</h1>
                <p id="current-time"></p>
            </div>
            <!-- noi dung -->
            <%
                //            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                //            if (loginUser == null || !loginUser.getRoleID()) {
                //                response.sendRedirect("login.html");
                //                return;
                //            }
                String search = (String) request.getParameter("search");
                if (search == null) {
                    search = "";
                }
            %>

            <!--Logout-->
            <!--        <form action="MainController" method="POST">
                        <input type="submit" name="action" value="Logout"/>
                    </form>-->

            <!--Search-->
            <form action="MainController">
                <input placeholder="Name of staff" type="text" name="search" value="<%= search%>"/>
                <input type="hidden" name="action" value="Search Candidate"/>
                <input  type="submit" value="Search"/> 
            </form>

            <!--Print List-->    
            <%
                List<Candidate> listCandidate = (List<Candidate>) request.getAttribute("LIST_CANDIDATE");
                if ((listCandidate == null || listCandidate.isEmpty()) && !search.equals("")) {
            %>
            <h3>No record!</h3>
            <%
                }
                if (listCandidate != null) {
                    if (!listCandidate.isEmpty()) {
            %>
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>ID</th>
                        <th>Full Name</th>
                        <th>Gender</th>
                        <th>Date Of Birth</th>
                        <th>Phone Number</th>
                        <th>Address</th>
                        <th>Detail</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        for (Candidate candidate : listCandidate) {
                    %>       
                <form action="MainController">
                    <tr>
                        <td><%= count++%></td>
                        <td><%= candidate.getId()%></td>
                        <td><%= candidate.getFullName()%></td>
                        <td><%= candidate.getGender()%></td>
                        <td><%= candidate.getDateOfBrith()%></td>
                        <td><%= candidate.getPhoneNumber()%></td>
                        <td><%= candidate.getAddress()%></td>
                        <td>
                            <input type="submit" value="Show Detail"/>                            
                            <input type="hidden" name="action" value="Show Candidate Detail"/>
                            <input type="hidden" name="candidateID" value="<%= candidate.getId()%>"/>
                        </td>
                    </tr>                
                </form>

                <%
                    }
                %>

                </tbody>
            </table>

            <%
                    }
                }
            %>

            <Br></Br>
    </body>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <script>
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
                            // let list = document.querySelectorAll('.navigation li');
                            // function activeLink() {
                            //     list.forEach((item) =>
                            //         item.classList.remove('hovered'));
                            //     this.classList.add('hovered');
                            // }
                            // list.forEach((item) =>
                            //     item.addEventListener('mouseover', activeLink));      
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
                            // search
                            const tableHeaders = ['id', 'name', 'sex', 'phone', 'status'];
                            function searchEmployees() {
                                const input = document.getElementById("search-input").value.toLowerCase();
                                const attribute = document.getElementById("search-attribute").value;

                                const rows = document.querySelectorAll("table tbody tr");

                                for (let i = 0; i < rows.length; i++) {
                                    const row = rows[i];
                                    const cell = row.querySelector(`td:nth-child(${tableHeaders.indexOf(attribute) + 1})`);

                                    if (cell.textContent.toLowerCase().includes(input)) {
                                        row.style.display = "";
                                    } else {
                                        row.style.display = "none";
                                    }
                                }
                            }
                            function showDetails(row) {
                                // Lấy dữ liệu từ các cột của dòng được chọn
                                const id = row.cells[0].textContent;
                                const fullName = row.cells[1].textContent;
                                const gender = row.cells[2].textContent;
                                const phoneNumber = row.cells[3].textContent;
                                const status = row.cells[4].textContent;

                                // Tạo một dòng mới để hiển thị chi tiết nhân viên
                                const detailRow = row.parentNode.insertRow(row.rowIndex + 1);
                                const detailCell = detailRow.insertCell();

                                // Đặt số lượng cột cho dòng mới bằng số lượng cột trong dòng gốc và giá trị colspan là 6
                                detailCell.colSpan = 6;

                                // Thêm HTML vào ô chi tiết để hiển thị thông tin chi tiết của nhân viên
                                detailCell.innerHTML = `
            <table>
                <tr>
                    <th>Candidate ID</th>
                    <td>${id}</td>
                </tr>
                <tr>
                    <th>Full name</th>
                    <td>${fullName}</td>
                </tr>
                <tr>
                    <th>Gender</th>
                    <td>${gender}</td>
                </tr>
                <tr>
                    <th>Phone number</th>
                    <td>${phoneNumber}</td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td>${status}</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button>Create contract</button>
                        <button>Delete</button>
                    </td>
                </tr>
            </table>
        `;
                            }

    </script>


</script>

</html>