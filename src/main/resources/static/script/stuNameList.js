$(document).ready(function () {
    var students = [];
    var course=JSON.parse(sessionStorage.getItem("course"));
    var ul=$("body > ul");
    $(ul).empty();
    $.ajax({
        type:"get",
        url:"/maker/teacher/course/list/stuName",
        data:{
            schoolId:course.schoolId,
            courseId:course.courseId
        },
        success:function (data) {
            students=data.data;
            $(students).each(function (i,student) {
                $(ul).append(
                    "<a href='javascript:void(0)'>\n" +
                    "\t\t<li>\n" +
                    "\t\t\t<div class=\"stuName\">"+student.stuName+"</div>\n" +
                    "\t\t\t<div class=\"evaTime\">\n" +
                    "\t\t\t\t<div>上次评价时间：</div>\n" +
                    "\t\t\t\t"+student.evaTime+"\n" +
                    "\t\t\t</div>\n" +
                    "\t\t</li>\n" +
                    "\t\t</a>"
                )
            });
            $("body > ul > a").on("click",function () {
                var index=$(this).index();
                sessionStorage.setItem("student",JSON.stringify(students[index]));
                window.location.href="TeaNamedetail.html";
            })
        }
    })
})