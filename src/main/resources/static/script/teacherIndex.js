$(document).ready(function () {
    var courses = [];
    $.ajax({
        type: "get",
        url: "/maker/teacher/course/list",
        success: function (data) {
            $("body > ul").empty();
            courses = data.data;
            $(courses).each(function (i, course) {
                $("body > ul").append(
                    "<a href='javascript:void(0)'>\n" +
                    "\t\t<li>\n" +
                    "\t\t\t<div class=\"school-time\">\n" +
                    "\t\t\t\t<div class=\"schoolName\">" + course.schoolName + "</div>\n" +
                    "\t\t\t\t<div class=\"line\"></div>\n" +
                    "\t\t\t\t<div class=\"courseName\">" + course.courseName + "</div>\n" +
                    "\t\t\t\t<div class=\"create end\">结课：" + course.endTime + "</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div class=\"tea\">\n" +
                    "\t\t\t\t<div class=\"tea-item\">上课地点：<span>" + course.address + "</span></div>\n" +
                    "\t\t\t\t<div class=\"tea-item\">上课人数：<span>" + course.stuNum + "</span></div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div class=\"period\">\n" +
                    "\t\t\t\t<span class=\"current\">" + course.currentWeeek + "</span>\n" +
                    "\t\t\t\t<span class=\"divide\">/</span>\n" +
                    "\t\t\t\t<span class=\"total\">" + course.period + "</span>\n" +
                    "\t\t\t\t<div class=\"weekday\">" + course.weekday + "</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t</li>\n" +
                    "\t\t</a>"
                )
            });
            $("body > ul > a").on("click", function () {
                var index = $(this).index();
                sessionStorage.setItem("course", JSON.stringify(courses[index]));
                window.location.href = "stuNameList.html";
            })
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR.responseText);
            console.log(textStatus);
            console.log(errorThrown);
        }
    })
})