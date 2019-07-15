$(document).ready(function(){
    var physics=[];
    var art=[];
    var english=[];
    var list=$("body > div.content > div.course-wrapper > ul");
    var btn_phy=$("body > div.content > div.menu-wrapper > ul > li:nth-child(1)");
    var btn_art=$("body > div.content > div.menu-wrapper > ul > li:nth-child(2)");
    var btn_eng=$("body > div.content > div.menu-wrapper > ul > li:nth-child(3)");
    $.ajax({
        type:"get",
        url:"/maker/school/course/unselected",
        success:function (res) {
            physics=res.data[0];
            art=res.data[1];
            english=res.data[2];
            $(list).empty();
            $(physics).each(function (i,course) {
                $(list).append(
                    "<a href='javascript:void(0)'>"+
                    "<li class=\"course-item\">\n" +
                    "\t\t\t\t\t\t<div class=\"icon\">\n" +
                    "\t\t\t\t\t\t\t<img src=\"../../img/phy.jpg\">\n" +
                    "\t\t\t\t\t\t</div>\n" +
                    "\n" +
                    "\t\t\t\t\t\t<div class=\"school-course-info\">\n" +
                    "\t\t\t\t\t\t\t<div class=\"course-info\">\n" +
                    "\t\t\t\t\t\t\t\t<div class=\"courseName\">"+course.courseName+"</div>\n" +
                    "\t\t\t\t\t\t\t\t<div class=\"otherInfo descInfo\">\n" +
                    "\t\t\t\t\t\t\t\t\t<div class=\"period\">课时:&nbsp;&nbsp;<span>"+course.period+"</span></div>\n" +
                    "\t\t\t\t\t\t\t\t\t<div class=\"crowd\">适宜人群:&nbsp;&nbsp;<span>"+course.crowd+"</span></div>\n" +
                    "\t\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t<div class=\"desc\">\n" +
                    "\t\t\t\t\t\t\t\t<span>简介：</span><p>"+course.description+"</p>\n" +
                    "\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</li>"+
                    "</a>"
                )
            })
            $("body > div.content > div.course-wrapper > ul > a").each(function (i,a) {
                    bind($(this),physics[i]);
            })
        }
    });
    $(btn_phy).on("click",function () {
        clear();
        $(this).addClass("leftFocus");
        $(list).empty();
        $(physics).each(function (i,course) {
            $(list).append(
                "<a href='javascript:void(0)'>"+
                "<li class=\"course-item\">\n" +
                "\t\t\t\t\t\t<div class=\"icon\">\n" +
                "\t\t\t\t\t\t\t<img src=\"../../img/phy.jpg\">\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\n" +
                "\t\t\t\t\t\t<div class=\"school-course-info\">\n" +
                "\t\t\t\t\t\t\t<div class=\"course-info\">\n" +
                "\t\t\t\t\t\t\t\t<div class=\"courseName\">"+course.courseName+"</div>\n" +
                "\t\t\t\t\t\t\t\t<div class=\"otherInfo descInfo\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"period\">课时:&nbsp;&nbsp;<span>"+course.period+"</span></div>\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"crowd\">适宜人群:&nbsp;&nbsp;<span>"+course.crowd+"</span></div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t<div class=\"desc\">\n" +
                "\t\t\t\t\t\t\t\t<span>简介：</span><p>"+course.description+"</p>\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</li>"+
                "</a>"
            )
        });
        $("body > div.content > div.course-wrapper > ul > a").each(function (i,a) {
            bind($(this),physics[i]);
        })
    });
    $(btn_art).on("click",function () {
        clear();
        $(this).addClass("leftFocus");
        $(list).empty();
        $(art).each(function (i,course) {
            $(list).append(
                "<a href='javascript:void(0)'>"+
                "<li class=\"course-item\">\n" +
                "\t\t\t\t\t\t<div class=\"icon\">\n" +
                "\t\t\t\t\t\t\t<img src=\"../../img/phy.jpg\">\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\n" +
                "\t\t\t\t\t\t<div class=\"school-course-info\">\n" +
                "\t\t\t\t\t\t\t<div class=\"course-info\">\n" +
                "\t\t\t\t\t\t\t\t<div class=\"courseName\">"+course.courseName+"</div>\n" +
                "\t\t\t\t\t\t\t\t<div class=\"otherInfo descInfo\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"period\">课时:&nbsp;&nbsp;<span>"+course.period+"</span></div>\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"crowd\">适宜人群:&nbsp;&nbsp;<span>"+course.crowd+"</span></div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t<div class=\"desc\">\n" +
                "\t\t\t\t\t\t\t\t<span>简介：</span><p>"+course.description+"</p>\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</li>"+
                "</a>"
            )
        });
        $("body > div.content > div.course-wrapper > ul > a").each(function (i,a) {
            bind($(this),art[i]);
        })
    });
    $(btn_eng).on("click",function () {
        clear();
        $(this).addClass("leftFocus");
        $(english).each(function (i,course) {
            $(list).append(
                "<a href='javascript:void(0)'>"+
                "<li class=\"course-item\">\n" +
                "\t\t\t\t\t\t<div class=\"icon\">\n" +
                "\t\t\t\t\t\t\t<img src=\"../../img/phy.jpg\">\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\n" +
                "\t\t\t\t\t\t<div class=\"school-course-info\">\n" +
                "\t\t\t\t\t\t\t<div class=\"course-info\">\n" +
                "\t\t\t\t\t\t\t\t<div class=\"courseName\">"+course.courseName+"</div>\n" +
                "\t\t\t\t\t\t\t\t<div class=\"otherInfo descInfo\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"period\">课时:&nbsp;&nbsp;<span>"+course.period+"</span></div>\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"crowd\">适宜人群:&nbsp;&nbsp;<span>"+course.crowd+"</span></div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t<div class=\"desc\">\n" +
                "\t\t\t\t\t\t\t\t<span>简介：</span><p>"+course.description+"</p>\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</li>"+
                "</a>"
            )
        });
        $("body > div.content > div.course-wrapper > ul > a").each(function (i,a) {
            bind($(this),english[i]);
        })
    });
    $("body > div.footer > div:nth-child(1)").on("click",function () {
        window.location.href="schoolCourseSelect.html";
    })
    function clear() {
        $(btn_phy).removeClass("leftFocus");
        $(btn_art).removeClass("leftFocus");
        $(btn_eng).removeClass("leftFocus");
        $(list).empty();
    }
    //绑定点击跳转
    function bind(li,course) {
        $(li).on("click",function () {
            sessionStorage.setItem("course",JSON.stringify(course));
            window.location.href="index.html";
        })
    }

})