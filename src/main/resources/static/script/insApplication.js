$(document).ready(function() {
$("body > ul").empty();
var list=[];
$.ajax({
    type:"get",
    url:"/maker/institution/application/list",
    success:function (res) {
        list=res.data;
        $(list).each(function (i,application) {
            var status=application.appStatus;
            if(status=="审核中"){
                $("body > ul").append(
                    "<a href='javascript:void(0)'>"+
                    "<li>\n" +
                    "\t\t\t<div class=\"school-time\">\n" +
                    "\t\t\t\t<div class=\"schoolName\">"+application.schoolName+"</div>\n" +
                    "\t\t\t\t<div class=\"line\"></div>\n" +
                    "\t\t\t\t<div class=\"courseName\">"+application.courseName+"</div>\n" +
                    "\t\t\t\t<div class=\"create\">"+application.createTime+"</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div class=\"time-border\">\n" +
                    "\t\t\t\t<div class=\"border\">最早开课时间</div>\n" +
                    "\t\t\t\t<div class=\"create\">"+application.leftBorder+"</div>\n" +
                    "\t\t\t\t<div class=\"border\">最晚开课时间</div>\n" +
                    "\t\t\t\t<div class=\"create\">"+application.rightBorder+"</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div class=\"app-status\">\n" +
                    "\t\t\t\t<div>"+application.appStatus+"</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t</li>"+
                    "</a>"
                )
            }else{
                $("body > ul").append(
                    "<li>\n" +
                    "\t\t\t<div class=\"school-time\">\n" +
                    "\t\t\t\t<div class=\"schoolName\">"+application.schoolName+"</div>\n" +
                    "\t\t\t\t<div class=\"line\"></div>\n" +
                    "\t\t\t\t<div class=\"courseName\">"+application.courseName+"</div>\n" +
                    "\t\t\t\t<div class=\"create\">"+application.createTime+"</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div class=\"time-border\">\n" +
                    "\t\t\t\t<div class=\"border\">最早开课时间</div>\n" +
                    "\t\t\t\t<div class=\"create\">"+application.leftBorder+"</div>\n" +
                    "\t\t\t\t<div class=\"border\">最晚开课时间</div>\n" +
                    "\t\t\t\t<div class=\"create\">"+application.rightBorder+"</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div class=\"app-status app-status-fail\">\n" +
                    "\t\t\t\t<div>"+application.appStatus+"</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t</li>"
                            )
            }

        });

        $("body > ul > a").on("click",function () {
            var index=$(this).index();
            sessionStorage.setItem("application",JSON.stringify(list[index]));
            window.location.href="insApprove.html";
        })
    }
});

});