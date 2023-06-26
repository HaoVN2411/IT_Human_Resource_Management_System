<%-- 
    Document   : showDetailContract
    Created on : Jun 23, 2023, 12:43:13 PM
    Author     : flami
--%>

<%@page import="Candidate.Helper"%>
<%@page import="Contract.TemporaryContract"%>
<%@page import="Candidate.Candidate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title></title>
        <!-- <link rel="stylesheet" type="text/css" href="style.css"> -->
        <style>
            @media print {
                body {
                    width: 794px;
                    height: 1123px;
                }
            }

            .container {
                max-width: 794px;
                padding: 20px;
                font-family: Arial, sans-serif;
            }

            p {
                font-size: 0.7em;
                text-align: left;
            }

            h1 {
                text-align: center;
            }


            .terms p {
                padding-left: 60px;
            }

            .company p {
                padding-left: 60px;
            }

            ol {
                margin: 0;
                padding: 0 0 0 20px;

            }
        </style>
    </head>

    <body>
        <%
            Candidate candidate = (Candidate) request.getAttribute("CANDIDATE");
            TemporaryContract tempContract = (TemporaryContract) request.getAttribute("TEMPORARY_CONTRACT");

            if (candidate != null && tempContract != null) {

        %>
        <div class="container" style=" text-align: center;">
            <h4>CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM</h4>
            <h4>Độc lập - Tự do - Hạnh phúc</h4>
            <h4>--o0o--
            </h4>
            <h1 style="color: rgb(3, 3, 165);">HỢP ĐỒNG LAO ĐỘNG</h1>
            <p style="font-size: 110%; text-align: center;"><strong>(BAN HÀNH THEO THÔNG TƯ SỐ 21/2003/TT-BLĐTBXH NGÀY 22
                    THÁNG 9 NĂM 2003 <br>
                    CỦA BỘ LĐ-TBXH)</strong>
            </p>
            <div class="company">
                <p>Căn cứ Bộ Luật Lao động hiện hành;
                </p>
                <p>Căn cứ Nghị định số 44/2013/NĐ-CP ngày 10 tháng 5 năm 2013 của Chính phủ quy định chi tiết và hướng dẫn
                    thi hành một số điều của Bộ Luật Lao động về hợp đồng lao động;

                </p>
                <p>Căn cứ các văn bản Luật và văn bản dưới Luật của pháp luật Lao động Việt Nam về lao động;
                </p>
                <p>Căn cứ các văn bản nội bộ của Công ty;
                </p>
                <p>Căn cứ nhu cầu sử dụng nhân lực tại Công ty;
                </p>
                <p>Căn cứ nhu cầu và thoả thuận tự nguyện của hai Bên.
                </p>
            </div>
            <p style="padding-left: 40px;">Chúng tôi, gồm:</p>
            <div class="employee" style="padding-left: 40px;">
                <h4 style="text-align: left; color: rgb(3, 3, 165);">NGƯỜI SỬ DỤNG LAO ĐỘNG
                </h4>
                <p style="display: flex;">
                    <span>Ông</span>
                    <span style="position: absolute; left: 200px;">: Trương Gia Bình</span>
                    <span style="position: absolute; left: 400px;">Quốc tịch: Việt Nam
                    </span>
                </p>

                <p style="display: flex;">
                    <span>Chức vụ
                    </span>
                    <span style="position: absolute; left: 200px;">: Giám đốc</span>
                </p>
                <p style="display: flex;">
                    <span>Đại diện cho
                    </span>
                    <span style="position: absolute; left: 200px;">: Công ty Trách Nhiệm Hữu Hạn 6 Thành Viên
                    </span>
                </p>
                <p style="display: flex;">
                    <span>Điện thoại
                    </span>
                    <span style="position: absolute; left: 200px;">: 070321351
                    </span>
                </p>
                <p style="display: flex;">
                    <span>Địa chỉ
                    </span>
                    <span style="position: absolute; left: 200px;">: Trái Đất
                    </span>
                </p>
            </div>
            <div class="employee" style="padding-left: 40px;">
                <h4 style="text-align: left; color: rgb(3, 3, 165);">NGƯỜI LAO ĐỘNG
                </h4>
                <p style="display: flex;">
                    <%
                        String gender;
                        if(candidate.getGender().equals("male")){
                            gender = "Ông";
                        }else if(candidate.getGender().equals("female")){
                                        gender = "Bà";
                        }else{
                            gender="Ông/Bà";
                        }
                    %>
                    <span><%=gender  %></span>
                    <span style="position: absolute; left: 200px;">: <%=candidate.getFullName()%></span>

                    <span style="position: absolute; left: 400px;">Quốc tịch: <%=candidate.getNationality()%>
                    </span>
                </p>
                <p style="display: flex;">
                    <span>Sinh ngày</span>
                    <span style="position: absolute; left: 200px;">: <%=Helper.convertToVietnameseDate(candidate.getDateOfBrith()) %></span>
                </p>
                <p style="display: flex;">
                    <span>Nghề nghiệp
                    </span>
                    <span style="position: absolute; left: 200px;">: Lập trình viên </span>
                </p>
                <p style="display: flex;">
                    <span>Địa chỉ thường trú
                    </span>
                    <span style="position: absolute; left: 200px;">: <%=candidate.getAddress()%>
                    </span>
                </p>
                <p style="display: flex;">
                    <span>Số CCCCD
                    </span>
                    <span style="position: absolute; left: 200px;">: <%=candidate.getHumanId()%>
                </p>
                <p>Cùng thỏa thuận ký kết hợp đồng lao động và cam kết tự nguyện làm đúng những điều khoản sau đây: </p>
            </div>
            <div class="terms">
                <h4 style="text-align: left; color: rgb(3, 3, 165); padding-left: 40px;">Điều 1: Thời hạn và công việc hợp
                    đồng
                </h4>
                <p>Thời hạn của hợp đồng lao động: không có
                </p>
                <p>Từ <%= Helper.convertToVietnameseDate(tempContract.getStartDate())%>
                </p>
                <p>Địa điểm làm việc: Tại trụ sở chính của công ty hoặc các chi nhánh của công ty theo sự phân công của Ban
                    giám đốc
                </p>
                <p>Chức danh chuyên môn: Lập trình viên
                </p>
                <p>Bộ phận: Kỹ thuật
                </p>
                <p>Công việc phải làm:Thực hiện công việc theo đúng chức danh chuyên môn (như đã quy định tại Điều 1 của văn
                    bản này và bản mô tả công việc quy định của Công ty), dưới sự quản lý, điều hành trực tiếp của Trưởng bộ
                    phận Kỹ thuật và những công việc khác theo quyết định bổ nhiệm, điều động, luân chuyển cán bộ (theo chức
                    vụ)
                    trên cơ sở hồ sơ giao việc kèm theo quyết định bổ nhiệm (nếu có) của Ban Giám đốc Công ty.</p>
            </div>
            <div class="terms">
                <h4 style="text-align: left; color: rgb(3, 3, 165); padding-left: 40px;">Điều 2: Chế độ làm việc
                </h4>
                <p>Thời gian làm việc: 8 giờ / ngày
                </p>
                <p>Thời gian làm việc trong tuần: 5 ngày / tuần, từ thứ 2 đến thứ 6 và không quá 44 giờ / tuần. Thời gian
                    làm việc được quy định rõ trong nội quy Công ty
                </p>
                <p style="padding-left: 80px;">Tùy theo nhu cầu công việc mà người sử dụng lao động thỏa thuận với người lao
                    động làm thêm giờ. Đối với
                    trường hợp đặc biệt theo quy định của pháp luật lao động thì người sử dụng lao động có quyền điều động
                    người lao động đi làm thêm giờ để giải quyết những công việc cấp bách, không thể trì hoãn. Tiền lương
                    làm thêm giờ được chi trả theo quy định của Công ty và thỏa ước lao động tập thể nhưng không trái với
                    quy định của pháp luật lao động
                </p>
                <p>Được Công ty cho phép sử dụng những dụng cụ làm việc gồm:
                </p>
                <p style="padding-left: 80px;">Bàn, ghế làm việc
                </p>
                <p style="padding-left: 80px;">Máy tính </p>
                <p style="padding-left: 80px;">Trang phục theo quy định của Công ty </p>
                <p style="padding-left: 80px;">Các công cụ, dụng cụ khác theo quy định của Công ty</p>
            </div>

            <div class="terms">
                <h4 style="text-align: left; color: rgb(3, 3, 165);padding-left: 40px;">Điều 3: Nghĩa vụ và quyền lợi của
                    người lao động
                </h4>
                <p style="display: flex;padding-left: 40px;">
                    <span><strong>1.</strong>
                    </span>
                    <span style="position: absolute; left: 88px;"><strong>Quyền lợi:</strong>
                    </span>
                </p>
                <p>Phương tiện đi lại, làm việc: Người lao động tự túc phương tiện đi lại
                <p>Mức lương: <%=(int)tempContract.getSalary()%> VND
                </p>
                <p>Phụ cấp (nếu có): Được trả theo quy định trong quy chế lương (về điều kiện hưởng phụ cấp, mức phụ cấp,
                    thời điểm trả phụ cấp)
                </p>
                <p>Các khoản trợ cấp bổ sung khác :
                </p>
                <p style="padding-left: 80px;">Tiền cơm trưa: 25,000 x 22 ngày / tháng = 550,000 VND
                </p>
                <p style="padding-left: 80px;">Tiền nhà ở: 1,000,000 VND / tháng
                </p>
                <p style="padding-left: 80px;">Tiền xăng xe: 300,000 VND / tháng </p>
                <p style="padding-left: 80px;">Tiền điện thoại: 200,000 VND / tháng </p>
                <p>Thưởng hiệu quả công việc: Dựa vào đánh giá của Ban Giám đốc và Trưởng bộ phận Kỹ thuật. Mức thưởng hiệu
                    quả công việc được quy định trong Quy chế lương thưởng và các văn bản của Công ty
                </p>
                <p>Phương thức trả lương: Trả bằng tiền mặt / hoặc trả theo tài khoản tùy từng thời điểm
                </p>
                <p>Được trả lương từ ngày 01 đến ngày 05 của tháng kế tiếp
                </p>
                <p>Tiền thưởng khác: Được chi trả theo quy định trong quy chế trả lương lưởng của Công ty về điều kiện
                    thưởng, mức thưởng, thời điểm thưởng…
                </p>
                <p>Chế độ nâng lương: Theo quy định của Quy chế lương thưởng tại Công ty
                </p>
                <p>Được trang bị bảo hộ lao động: Theo quy định của Công ty</p>
                <p>Chế độ nghỉ ngơi (nghỉ hàng tuần, phép năm, lễ tết...): Theo quy định của Nhà nước, Thoả ước lao động và
                    Nội quy lao động của Công ty</p>
                <p>Bảo hiểm xã hội, Bảo hiểm y tế và Bảo hiểm thất nghiệp: Được trích nộp từ mức lương thang bảng lương của
                    người lao động (theo quy định ở khoản 1 điều 3 của hợp đồng này) với tỷ lệ % tương ứng theo quy định
                    trong các văn bản pháp luật lao động hiện hành</p>
                <p>Chế độ đào tạo: Theo quy định trong quy chế nội bộ của Công ty và quyết định của Giám đốc</p>
                <p>Quyền lợi khác: Có quyền đề xuất, yêu cầu thay đổi điều kiện lao động để phù hợp với công việc được giao
                </p>
                <p>Những thỏa thuận khác: Theo quy định nội bộ của Công ty và phụ lục hợp đồng lao động (nếu có)</p>
                <p style="display: flex;padding-left: 40px;">
                    <span><strong>2.</strong>
                    </span>
                    <span style="position: absolute; left: 88px;"><strong>Nghĩa vụ:</strong>
                    </span>
                </p>
                <p>Hoàn thành những công việc đã cam kết trong hợp đồng lao động và hồ sơ giao việc</p>
                <p>Chấp hành mệnh lệnh điều hành, phân công lao động của người sử dụng lao động</p>
                <p>Chấp hành Nội quy lao động, an toàn lao động, bảo mật thông tin và các quy định trong các quy chế nội
                    bộ của Công ty và các cam kết khác giữa các bên (nếu có)</p>
                <p>Không được tiết lộ bí mật thông tin hoặc có những hành vi khác có thể gây thiệt hại đến tài sản và uy
                    tín của Công ty</p>
                <p>Bồi thường vi phạm vật chất: Người lao động phải bồi thường trách nhiệm vật chất theo quy định của pháp
                    luật lao động, theo các quy chế nội bộ của Công ty (như: Nội quy lao động, ...) và theo các hợp đồng
                    hoặc cam kết đã ký với Công ty</p>
                <p>Người lao động sẽ bị Công ty đơn phương chấm dứt hợp đồng trước thời hạn khi thường xuyên không hoàn
                    thành công việc theo thỏa thuận tại Điều 1 của hợp đồng này. Mức độ hoàn thành hoặc không hoàn thành
                    công việc và cách thức đánh giá được áp dụng theo quy định nội bộ của Công ty và được Thỏa ước tập thể
                    của Công ty công nhận</p>
                <p>Trường hợp người lao động muốn chấm dứt hợp đồng lao động: Phải đảm bảo quy định về nghĩa vụ báo trước.
                    Bàn giao, hướng dẫn công việc đến thời điểm chấm dứt hợp đồng. Người lao động sẽ không được giải quyết
                    chế độ, lương,… nếu không thực hiện theo điều khoản chấm dứt hợp đồng trên</p>

                <p>Người lao động có trách nhiệm nộp đầy đủ hồ sơ cho phòng nhân sự, bao gồm:</p>
                <p style="padding-left: 80px;">Bằng tốt nghiệp chuyên ngành đào tạo (Bản sao)</p>
                <p style="padding-left: 80px;">Giấy chứng nhận sức khỏe (Bản gốc do bệnh viện cấp Quận, Huyện trở lên, có
                    giá trị trong vòng 6 tháng)
                </p>
                <p style="padding-left: 80px;">Các văn bằng, chứng chỉ chuyên môn khác (nếu có)</p>
                <p style="padding-left: 80px;">CMND /CCCD (Bản sao)</p>
                <p style="padding-left: 80px;">Ảnh 4 x 6 (số lượng 02)</p>
                <p style="padding-left: 80px;">Các bằng chứng ghi nhận quá trình công tác (nếu có)</p>

                <p>Nếu người lao động cung cấp thông tin sai lệch trong hồ sơ, hồ sơ do chủ quan và khách quan khai không
                    trung thực, Công ty có quyền đơn phương chấm dứt Hợp đồng lao động mà không cần thông báo trước hoặc bồi
                    thường.</p>
            </div>
            <div class="terms">
                <h4 style="text-align: left; color: rgb(3, 3, 165);padding-left: 40px;">Điều 4: Nghĩa vụ và quyền hạn của
                    người sử dụng lao động
                </h4>
                <p style="display: flex;padding-left: 40px;">
                    <span><strong>1.</strong>
                    </span>
                    <span style="position: absolute; left: 88px;"><strong>Nghĩa vụ</strong>
                    </span>
                </p>
                <p>Bảo đảm việc làm và thực hiện đầy đủ những điều đã cam kết trong hợp đồng lao động </p>
                <p>Thanh toán đầy đủ, đúng thời hạn các chế độ và quyền lợi cho người lao động theo Hợp đồng lao động, Thỏa
                    ước lao động tập thể</p>
                <p style="display: flex;padding-left: 40px;">
                    <span><strong>2.</strong>
                    </span>
                    <span style="position: absolute; left: 88px;"><strong>Quyền hạn</strong>
                    </span>
                </p>
                <p>Điều hành người lao động hoàn thành công việc theo hợp đồng (bố trí, điều chuyển, tạm ngừng việc,…) theo
                    quy định tại Bộ luật lao động và những thỏa thuận trong hợp đồng lao động này</p>
                <p>Tạm hoãn, chấm dứt hợp đồng lao động, kỷ luật người lao động theo quy định của pháp luật, Thỏa ước lao
                    động tập thể và Nội quy lao động của doanh nghiệp
                </p>
            </div>
            <div class="terms">
                <h4 style="text-align: left; color: rgb(3, 3, 165);padding-left: 40px;">Điều 5: Điều khoản thi hành</h4>
                <p>Những vấn đề khác về quyền và nghĩa vụ của người lao động và người sử dụng lao động nếu không được
                    ghi trong hợp đồng lao động này thì đương nhiên được áp dụng theo các quy định tương ứng trong các
                    văn bản nội bộ của Công ty và trong các văn bản pháp luật lao động tại thời điểm thi hành các quyền
                    và nghĩa vụ đó</p>

                <p>Hợp đồng lao động này có thể được sửa đổi, bổ sung hoặc hủy bỏ một số điều khoản hoặc hủy bỏ toàn bộ
                    hợp đồng theo thỏa thuận giữa các Bên hoặc theo các quyết định hợp pháp của người sử dụng lao động
                    hoặc theo các quyết định của cơ quan nhà nước có thẩm quyền (nếu phát sinh trong quá trình thực hiện
                    hợp đồng lao động)</p>

                <p>Người lao động có thể được bổ nhiệm, luân chuyển, hoặc điều động giữ các chức vụ (nếu có) theo quy
                    định nội bộ của Công ty và theo yêu cầu của hoạt động kinh doanh. Khi đó, người sử dụng lao động sẽ
                    ban hành Quyết định bổ nhiệm, luân chuyển, hoặc điều động cán bộ trong từng thời điểm cụ thể (trong
                    đó xác định rõ chức vụ của người lao động, các quyền và nghĩa vụ tương ứng với chức vụ được bổ
                    nhiệm, địa điểm làm việc,…)</p>

                <p>Nếu bị bãi nhiệm bởi Quyết định của người sử dụng lao động (theo các quy chế nội bộ của Công ty, hoặc
                    theo yêu cầu hoạt động kinh doanh của Công ty), người lao động sẽ không giữ chức vụ đã được bổ
                    nhiệm, nhưng vẫn có thể tiếp tục thực hiện công việc theo chức danh chuyên môn đã ký trong hợp đồng
                    lao động</p>

                <p>Hợp đồng lao động sẽ chấm dứt theo quy định của Pháp luật lao động, Thoả ước lao động và Nội quy lao
                    động của Công ty. Khi chấm dứt hợp đồng lao động, các Bên sẽ tiến hành lập biên bản thanh lý hợp
                    đồng và các thủ tục cần thiết khác theo quy định về chấm dứt hợp đồng lao động. Sau khi ký biên bản
                    thanh lý hợp đồng, quyền và nghĩa vụ của các Bên theo quy định tại hợp đồng này sẽ đương nhiên chấm
                    dứt</p>
                <p>Khi hai Bên ký kết phụ lục hợp đồng lao động thì nội dung của phụ lục hợp đồng lao động cũng có giá trị
                    như các nội dung của bản hợp đồng lao động này. Trường hợp nội dung của phụ lục hợp đồng lao động khác
                    với nội dung của bản hợp đồng lao động này thì văn bản nào được ký tại thời điểm sau sẽ có giá trị pháp
                    lý và đương nhiên thay thế cho điều khoản có nội dung tương tự đã ký trong văn bản trước đó
                </p>
                <p>Mọi thỏa thuận trong các hợp đồng lao động khác trước đây, trái với thỏa thuận trong hợp đồng lao động
                    này đều đương nhiên hết hiệu lực</p>
                <p>Hợp đồng lao động này được lập thành hai bản, có chữ ký của người lao động và người sử dụng lao động. Các
                    bản có giá trị pháp lý như nhau. Được giao cho người lao động và người sử dụng lao động, mỗi Bên giữ một
                    bản. Có hiệu lực từ <%=Helper.convertToVietnameseDate(tempContract.getStartDate()) %></p>
                <p>Hợp đồng này do các Bên hoàn toàn tự nguyện thỏa thuận và cùng ký kết trong tình trạng tinh thần tỉnh
                    táo, không bị lừa dối hay ép buộc, nhằm đảm bảo có lợi nhất cho lợi ích của mỗi Bên./.</p>
                <p style="display: flex;padding-left: 40px; font-size: 80%;">
                    <span style="position: absolute; left: 200px;"><strong>NGƯỜI LAO ĐỘNG</strong>
                    </span>
                    <span style="position: absolute; left: 500px;"><strong>NGƯỜI SỬ DỤNG LAO ĐỘNG</strong>
                    </span>
                </p>
                <br>
                <p style="display: flex;padding-left: 40px;">
                    <span style="position: absolute; left: 200px;">(Ký và ghi rõ họ tên)
                    </span>
                    <span style="position: absolute; left: 550px;">(Ký và ghi rõ họ tên)
                    </span>
                </p>
            </div>
            <%
                }
            %>
    </body>

</html>