$(document).ready(function(){
    var start;
    var end;
    var desc;
    var course=JSON.parse(sessionStorage.getItem("course"));
    $("body > div.footer > div:nth-child(2)").on("click",function () {
        window.location.href="schoolCourseUnselect.html";
    });
    $("body > div.footer > div:nth-child(1)").on("click",function () {
        start=$("#startTime").val();
        end=$("#endTime").val();
        desc=$("#otherRequest").val();
        $.ajax({
            type:"post",
            url:"/maker/school/application/add",
            data:{
                startTimeLeftBorder:start,
                startTimeRightBorder:end,
                otherRequest:desc,
                courseId:course.courseId
            },
            success:function (data) {
                alert("成功");
                window.location.href="schoolCourseSelect.html";
            },
            error:function (e) {
                alert("失败，网络错误");
            }
        })
    })
})