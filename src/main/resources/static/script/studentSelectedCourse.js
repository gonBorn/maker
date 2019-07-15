$(document).ready(function(){
var stuId;
var continue_courses=[];
var finished_courses=[];
$.ajax({
    type:"get",
    url:"/maker/getStu",
    success:function (data) {
        if(data!==null)stuId=data.studentId;
        else {window.location.href="http://localhost:8080/maker/static/html/login2.html";
        alert("请先登录")}
        console.log(stuId)
        $.ajax({
            type:"get",
            url:"/maker/stu/course/selected",
            data:{
                studentId:stuId
            },
            success:function (res) {
                $("body > div.content > div.course-wrapper > ul").empty();
                var data=res.data;
                continue_courses=data[0].courses;
                finished_courses=data[1].courses;
                if (data[0].courses.length==0) {
                    ("body > div.content > div.course-wrapper > ul").append("<div>此类目下暂无课程</div>");
                }else{
                    $(continue_courses).each(function (i,item) {
                        $("body > div.content > div.course-wrapper > ul").append(
                            "<a href='javascript:void(0)'>"+
                            "<li class=\"course-item\">\n" +
                            "\t\t\t\t\t<div class=\"icon\">\n" +
                            "\t\t\t\t\t\t<img src=\"../../img/phy.jpg\">\n" +
                            "\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t<div class=\"course-info\">\n" +
                            "\t\t\t\t\t\t<div class=\"courseName\">"+item.courseName+"</div>\n" +
                            "\t\t\t\t\t\t<div class=\"otherInfo\">"+item.teacherName+"</div>\n" +
                            "\t\t\t\t\t\t<div class=\"otherInfo\">"+item.address+"</div>\n" +
                            "\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t<div class=\"period\">\n" +
                            "\t\t\t\t\t\t<span class=\"current\">"+item.currentWeek+"</span>\n" +
                            "\t\t\t\t\t\t<span class=\"divide\">/</span>\n" +
                            "\t\t\t\t\t\t<span class=\"total\">"+item.totalWeek+"</span>\n" +
                            "\t\t\t\t\t\t<div class=\"weekday\">"+item.day+"</div>\n" +
                            "\t\t\t\t\t</div>\n" +
                            "\t\t\t\t</li>"+
                            "</a>"
                        )
                    })
                }
                $("body > div.content > div.course-wrapper > ul").find("a").each(function (i,a) {
                    bind(a,i,false);
                })
            }
        });
    },
    // error:function (e) {
    //     console.error(e.text());
    // }
});

$("li.menu-item:nth-child(2)").on("click",function () {
    $("body > div.content > div.menu-wrapper > ul > li:nth-child(1)").removeClass("leftFocus");
    $("body > div.content > div.menu-wrapper > ul > li:nth-child(2)").addClass("leftFocus");
    $("body > div.content > div.course-wrapper > ul").empty();
    if (finished_courses.length===0) {
        ("body > div.content > div.course-wrapper > ul").append("<div>此类目下暂无课程</div>");
    }else {
        $(finished_courses).each(function (i,item) {
            $("body > div.content > div.course-wrapper > ul").append(
                "<a href='javascript:void(0)'>"+
                "<li class=\"course-item\">\n" +
                "\t\t\t\t\t<div class=\"icon\">\n" +
                "\t\t\t\t\t\t<img src=\"../../img/phy.jpg\">\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div class=\"course-info\">\n" +
                "\t\t\t\t\t\t<div class=\"courseName\">"+item.courseName+"</div>\n" +
                "\t\t\t\t\t\t<div class=\"otherInfo\">"+item.teacherName+"</div>\n" +
                "\t\t\t\t\t\t<div class=\"otherInfo\">"+item.address+"</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div class=\"period\">\n" +
                "\t\t\t\t\t\t<span class=\"current\">"+item.currentWeek+"</span>\n" +
                "\t\t\t\t\t\t<span class=\"divide\">/</span>\n" +
                "\t\t\t\t\t\t<span class=\"total\">"+item.totalWeek+"</span>\n" +
                "\t\t\t\t\t\t<div class=\"weekday\">"+item.day+"</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</li>"+
                "</a>"
            )
        })
    }
    $("body > div.content > div.course-wrapper > ul").find("a").each(function (i,a) {
        bind(a,i,true);
    })
    
});
$("li.menu-item:nth-child(1)").on("click",function () {
    $("body > div.content > div.menu-wrapper > ul > li:nth-child(2)").removeClass("leftFocus");
    $("body > div.content > div.menu-wrapper > ul > li:first-child").addClass("leftFocus");
    $("body > div.content > div.course-wrapper > ul").empty();
    if (continue_courses.length===0) {
        ("body > div.content > div.course-wrapper > ul").append("<div>此类目下暂无课程</div>");
    }else {
        $(continue_courses).each(function (i,item) {
            $("body > div.content > div.course-wrapper > ul").append(
                "<a href='javascript:void(0)'>"+
                "<li class=\"course-item\">\n" +
                "\t\t\t\t\t<div class=\"icon\">\n" +
                "\t\t\t\t\t\t<img src=\"../../img/phy.jpg\">\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div class=\"course-info\">\n" +
                "\t\t\t\t\t\t<div class=\"courseName\">"+item.courseName+"</div>\n" +
                "\t\t\t\t\t\t<div class=\"otherInfo\">"+item.teacherName+"</div>\n" +
                "\t\t\t\t\t\t<div class=\"otherInfo\">"+item.address+"</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div class=\"period\">\n" +
                "\t\t\t\t\t\t<span class=\"current\">"+item.currentWeek+"</span>\n" +
                "\t\t\t\t\t\t<span class=\"divide\">/</span>\n" +
                "\t\t\t\t\t\t<span class=\"total\">"+item.totalWeek+"</span>\n" +
                "\t\t\t\t\t\t<div class=\"weekday\">"+item.day+"</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</li>"+
                "</a>"
            )
        })
    }
    $("body > div.content > div.course-wrapper > ul").find("a").each(function (i,a) {
        bind(a,i,false);
    })
});

$("body > div.footer > div:nth-child(2)").on("click",function () {
    window.location.href="stuCourseUnselect.html"
});

function bind(ele,index,finished) {
    $(ele).on("click",function () {
        var course;
        if(!finished)course=continue_courses[index];
        else course=finished_courses[index];
        sessionStorage.setItem("course",JSON.stringify(course));
        window.location.href="checkEva.html";
    })
}


});