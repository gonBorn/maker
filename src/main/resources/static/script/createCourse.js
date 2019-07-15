$(document).ready(function() {
$("body > div.ui-page.ui-page-theme-a.ui-page-active > div.ui-content > a").on("click",function () {
    var name=$("#fname").val();
    var lessonTime=$("#lessontime").val();
    var str_type=$("#courseId-button > span").text();
    var type=0;
    var crowd=$("#crowdId-button > span").text();
    var desc=$("#details").val();
    switch (str_type) {
        case "物理":
            type=1;
        case "艺术":
            type=2;
        case "英语":
            type=3;
        case "文学":
            type=4;
        case "计算机":
            type=5;
    }
    $.ajax({
        type:"post",
        url:"/maker/institution/course/add",
        data:{
            courseName:name,
            courseType:type,
            description:desc,
            crowd:crowd,
            period:lessonTime
        },
        success:function (courseId) {
            alert("success")
            window.location.href="http://localhost:8080/maker/static/html/institution/ManagerIndex.html";
        },
        error:function (e) {
            alert(e.text())
        }
    })
})
})