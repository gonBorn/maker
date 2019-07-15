$(document).ready(function() {
    var stuId;
    var physics=[];
    var art=[];
    var english=[];
    $.ajax({
        type: "get",
        url: "/maker/getStu",
        success: function (data) {
            if (data !== null) stuId = data.studentId;
            else {
                window.location.href = "http://localhost:8080/maker/static/html/login2.html";
                alert("请先登录")
            }
            console.log(stuId);
            $.ajax({
                type: "get",
                url: "/maker/stu/course/unselected",
                data: {
                    studentId: stuId
                },
                success: function (res) {
                    var data = res.data;
                    physics = data[0].stuUnselectedCourseVOS;
                    art = data[1].stuUnselectedCourseVOS;
                    english = data[2].stuUnselectedCourseVOS;
                    $("body > div.content > div.course-wrapper > ul").empty();
                    if (physics.length===0) {
                        $("body > div.content > div.course-wrapper > ul").append("<div>此类目下暂无课程</div>");
                    }else {
                        $(physics).each(function (i,item) {
                            $("body > div.content > div.course-wrapper > ul").append(
                                "<li class=\"course-item\">\n" +
                                "\t\t\t\t\t<div class=\"icon\">\n" +
                                "\t\t\t\t\t\t<img src=\"../../img/phy.jpg\">\n" +
                                "\t\t\t\t\t</div>\n" +
                                "\t\t\t\t\t<div class=\"course-info\">\n" +
                                "\t\t\t\t\t\t<div class=\"courseName\">"+item.courseName+"</div>\n" +
                                "\t\t\t\t\t\t<div class=\"otherInfo\">\n" +
                                "\t\t\t\t\t\t\t<div class=\"otherInfo\">\n" +
                                "\t\t\t\t\t\t\t\t<span>"+item.startTime+"</span>\n" +
                                "\t\t\t\t\t\t\t\t<span>至</span>\n" +
                                "\t\t\t\t\t\t\t\t<span>"+item.endTime+"</span>\n" +
                                "\t\t\t\t\t\t\t</div>\n" +
                                "\t\t\t\t\t\t\t<div class=\"otherInfo\">\n" +
                                "\t\t\t\t\t\t\t\t<span>"+item.teacherName+"</span>\n" +
                                "\t\t\t\t\t\t\t\t<span>"+item.weekday+"</span>\n" +
                                "\t\t\t\t\t\t\t</div>\n" +
                                "\t\t\t\t\t\t</div>\n" +
                                "\t\t\t\t\t</div>\n" +
                                "\t\t\t\t\t<div class=\"select-btn\">\n" +
                                "\t\t\t\t\t\t<button>选课</button>\n" +
                                "\t\t\t\t\t</div>\n" +
                                "\t\t\t\t</li>"
                            );

                        })
                    }

                },
                error: function (e) {
                    console.error(e.text());
                }
            })
        },
        error: function (e) {
            console.error(e.text());
        }
    });
    
    $("body > div.content > div.menu-wrapper > ul > li:nth-child(2)").on("click",function () {
        $("body > div.content > div.menu-wrapper > ul li").each(function (i,li) {
            if($(li).attr("class").length>10)$(li).removeClass("leftFocus");
        });
        $(this).addClass("leftFocus");
        $("body > div.content > div.course-wrapper > ul").empty();
        if (art.length===0) {
            $("body > div.content > div.course-wrapper > ul").append("<div>此类目下暂无课程</div>");
        }else{
            $(art).each(function (i,item) {
                $("body > div.content > div.course-wrapper > ul").append(
                    "<li class=\"course-item\">\n" +
                    "\t\t\t\t\t<div class=\"icon\">\n" +
                    "\t\t\t\t\t\t<img src=\"../../img/phy.jpg\">\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t<div class=\"course-info\">\n" +
                    "\t\t\t\t\t\t<div class=\"courseName\">"+item.courseName+"</div>\n" +
                    "\t\t\t\t\t\t<div class=\"otherInfo\">\n" +
                    "\t\t\t\t\t\t\t<div class=\"otherInfo\">\n" +
                    "\t\t\t\t\t\t\t\t<span>"+item.startTime+"</span>\n" +
                    "\t\t\t\t\t\t\t\t<span>至</span>\n" +
                    "\t\t\t\t\t\t\t\t<span>"+item.endTime+"</span>\n" +
                    "\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t<div class=\"otherInfo\">\n" +
                    "\t\t\t\t\t\t\t\t<span>"+item.teacherName+"</span>\n" +
                    "\t\t\t\t\t\t\t\t<span>"+item.weekday+"</span>\n" +
                    "\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t<div class=\"select-btn\">\n" +
                    "\t\t\t\t\t\t<button>选课</button>\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t</li>"
                );
            })
        }

    });

    $("body > div.content > div.menu-wrapper > ul > li:nth-child(3)").on("click",function () {
        $("body > div.content > div.menu-wrapper > ul li").each(function (i,li) {
            if($(li).attr("class").length>10)$(li).removeClass("leftFocus");
        });
        $(this).addClass("leftFocus");
        $("body > div.content > div.course-wrapper > ul").empty();
        if (english.length===0) {
            $("body > div.content > div.course-wrapper > ul").append("<div>此类目下暂无课程</div>");
        }else{
            $(english).each(function (i,item) {
                $("body > div.content > div.course-wrapper > ul").append(
                    "<li class=\"course-item\">\n" +
                    "\t\t\t\t\t<div class=\"icon\">\n" +
                    "\t\t\t\t\t\t<img src=\"../../img/phy.jpg\">\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t<div class=\"course-info\">\n" +
                    "\t\t\t\t\t\t<div class=\"courseName\">"+item.courseName+"</div>\n" +
                    "\t\t\t\t\t\t<div class=\"otherInfo\">\n" +
                    "\t\t\t\t\t\t\t<div class=\"otherInfo\">\n" +
                    "\t\t\t\t\t\t\t\t<span>"+item.startTime+"</span>\n" +
                    "\t\t\t\t\t\t\t\t<span>至</span>\n" +
                    "\t\t\t\t\t\t\t\t<span>"+item.endTime+"</span>\n" +
                    "\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t<div class=\"otherInfo\">\n" +
                    "\t\t\t\t\t\t\t\t<span>"+item.teacherName+"</span>\n" +
                    "\t\t\t\t\t\t\t\t<span>"+item.weekday+"</span>\n" +
                    "\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t<div class=\"select-btn\">\n" +
                    "\t\t\t\t\t\t<button>选课</button>\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t</li>"
                );
            })
        }

    });

    $("body > div.content > div.menu-wrapper > ul > li:nth-child(1)").on("click",function () {
        $("body > div.content > div.menu-wrapper > ul li").each(function (i,li) {
            if($(li).attr("class").length>10)$(li).removeClass("leftFocus");
        });
        $(this).addClass("leftFocus");
        $("body > div.content > div.course-wrapper > ul").empty();
        if (physics.length===0) {
            $("body > div.content > div.course-wrapper > ul").append("<div>此类目下暂无课程</div>");
        }else {
            $(physics).each(function (i,item) {
                $("body > div.content > div.course-wrapper > ul").append(
                    "<li class='javascript:void(0)'>\n" +
                    "\t\t\t\t\t<div class=\"icon\">\n" +
                    "\t\t\t\t\t\t<img src=\"../../img/phy.jpg\">\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t<div class=\"course-info\">\n" +
                    "\t\t\t\t\t\t<div class=\"courseName\">"+item.courseName+"</div>\n" +
                    "\t\t\t\t\t\t<div class=\"otherInfo\">\n" +
                    "\t\t\t\t\t\t\t<div class=\"otherInfo\">\n" +
                    "\t\t\t\t\t\t\t\t<span>"+item.startTime+"</span>\n" +
                    "\t\t\t\t\t\t\t\t<span>至</span>\n" +
                    "\t\t\t\t\t\t\t\t<span>"+item.endTime+"</span>\n" +
                    "\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t<div class=\"otherInfo\">\n" +
                    "\t\t\t\t\t\t\t\t<span>"+item.teacherName+"</span>\n" +
                    "\t\t\t\t\t\t\t\t<span>"+item.weekday+"</span>\n" +
                    "\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t<div class=\"select-btn\">\n" +
                    "\t\t\t\t\t\t<button>选课</button>\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t</li>"
                );
            })
        }

    });

    $("body > div.footer > div:nth-child(1)").on("click",function () {
        window.location.href="http://localhost:8080/maker/static/html/stu/stuCourseSelect.html";
    })



})
