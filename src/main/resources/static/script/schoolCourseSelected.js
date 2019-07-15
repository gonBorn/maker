$(document).ready(function(){
    var continuing=[];
    var finished=[];
    var c_btn=$("body > div.content > div.menu-wrapper > ul > li:nth-child(1)");
    var f_btn=$("body > div.content > div.menu-wrapper > ul > li:nth-child(2)");
    var ul=$("body > div.content > div.course-wrapper > ul");
    $.ajax({
        type:"get",
        url:"/maker/school/course/selected",
        success:function (res) {
            continuing=res.data[0].courses;
            finished=res.data[1].courses;
            $(ul).empty();
            $(continuing).each(function (i,item) {
                $(ul).append(
                    "<li class=\"course-item\">\n" +
                    "\t\t\t\t\t<div class=\"icon\">\n" +
                    "\t\t\t\t\t\t<img src=\"../../img/phy.jpg\">\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t<div class=\"info\">\n" +
                    "\t\t\t\t\t\t<div class=\"courseName\">\n" +
                    "\t\t\t\t\t\t\t"+item.courseName+"\n" +
                    "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t<div class=\"type-period-num\">\n" +
                    "\t\t\t\t\t\t\t<span class=\"item\">类型：</span><span>"+item.courseType+"</span>\n" +
                    "\t\t\t\t\t\t\t&nbsp;&nbsp;\n" +
                    "\t\t\t\t\t\t\t<span class=\"item\">课时：</span><span>"+item.period+"</span>\n" +
                    "\t\t\t\t\t\t\t&nbsp;&nbsp;\n" +
                    "\t\t\t\t\t\t\t<span class=\"item\">报名：</span><span>"+item.stuNum+"人</span>\n" +
                    "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t<div class=\"otherInfo\">\n" +
                    "\t\t\t\t\t\t\t<span>"+item.startTime+"</span>\n" +
                    "\t\t\t\t\t\t\t<span class=\"item\">至</span>\n" +
                    "\t\t\t\t\t\t\t<span>"+item.endTime+"</span>\n" +
                    "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</div> \n" +
                    "\t\t\t\t\t\n" +
                    "\t\t\t\t</li>"
                );
            });
            


        },
        error:function () {
            alert("error");
        }
    });
    $(f_btn).on("click",function () {
        $(c_btn).removeClass("leftFocus");
        $(f_btn).addClass("leftFocus");
        $(ul).empty();
        $(finished).each(function (i,item) {
            $(ul).append(
                "<li class=\"course-item\">\n" +
                "\t\t\t\t\t<div class=\"icon\">\n" +
                "\t\t\t\t\t\t<img src=\"../../img/phy.jpg\">\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div class=\"info\">\n" +
                "\t\t\t\t\t\t<div class=\"courseName\">\n" +
                "\t\t\t\t\t\t\t"+item.courseName+"\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<div class=\"type-period-num\">\n" +
                "\t\t\t\t\t\t\t<span class=\"item\">类型：</span><span>"+item.courseType+"</span>\n" +
                "\t\t\t\t\t\t\t&nbsp;&nbsp;\n" +
                "\t\t\t\t\t\t\t<span class=\"item\">课时：</span><span>"+item.period+"</span>\n" +
                "\t\t\t\t\t\t\t&nbsp;&nbsp;\n" +
                "\t\t\t\t\t\t\t<span class=\"item\">报名：</span><span>"+item.stuNum+"人</span>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<div class=\"otherInfo\">\n" +
                "\t\t\t\t\t\t\t<span>"+item.startTime+"</span>\n" +
                "\t\t\t\t\t\t\t<span class=\"item\">至</span>\n" +
                "\t\t\t\t\t\t\t<span>"+item.endTime+"</span>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div> \n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t</li>"
            );
        })
    });
    $(c_btn).on("click",function () {
        $(f_btn).removeClass("leftFocus");
        $(c_btn).addClass("leftFocus");
        $(ul).empty();
        $(continuing).each(function (i,item) {
            $(ul).append(
                "<li class=\"course-item\">\n" +
                "\t\t\t\t\t<div class=\"icon\">\n" +
                "\t\t\t\t\t\t<img src=\"../../img/phy.jpg\">\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div class=\"info\">\n" +
                "\t\t\t\t\t\t<div class=\"courseName\">\n" +
                "\t\t\t\t\t\t\t" + item.courseName + "\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<div class=\"type-period-num\">\n" +
                "\t\t\t\t\t\t\t<span class=\"item\">类型：</span><span>" + item.courseType + "</span>\n" +
                "\t\t\t\t\t\t\t&nbsp;&nbsp;\n" +
                "\t\t\t\t\t\t\t<span class=\"item\">课时：</span><span>" + item.period + "</span>\n" +
                "\t\t\t\t\t\t\t&nbsp;&nbsp;\n" +
                "\t\t\t\t\t\t\t<span class=\"item\">报名：</span><span>" + item.stuNum + "人</span>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<div class=\"otherInfo\">\n" +
                "\t\t\t\t\t\t\t<span>" + item.startTime + "</span>\n" +
                "\t\t\t\t\t\t\t<span class=\"item\">至</span>\n" +
                "\t\t\t\t\t\t\t<span>" + item.endTime + "</span>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div> \n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t</li>"
            );
        })
    });
    $("body > div.footer > div:nth-child(2)").on("click",function () {
        window.location.href="schoolCourseUnselect.html";
    })
})