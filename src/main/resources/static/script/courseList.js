$(document).ready(function() {
    $("#mainpage > div.ui-panel-wrapper > div.ui-content.jqm-content > ul").empty();
    var list=[];
    var schools=new Array();
    $.ajax({
        type:"get",
        url:"/maker/institution/course/list",
        success:function (res) {
            console.log(res);
            if(res === null){
                alert("请先登录");
                window.location.href="http://localhost:8080/maker/login.html";
            }
            list=res.data;
            for(var i=0;i<list.length;i++)schools[i]=new Array();
            $(list).each(function (i,course) {
                $("#mainpage > div.ui-panel-wrapper > div.ui-content.jqm-content > ul").append(
                    "<li class=\"ui-first-child\">\n" +
                    "\t\t        <a href=\"#revealPanel\" class=\"ui-btn ui-btn-icon-right ui-icon-carat-r\">\n" +
                    "\t\t        <h2>"+course.courseName+"</h2>\n" +
                    "\t\t        <p>"+course.description+"</p>\n" +
                    "\t\t\t\t<p>\n" +
                    "\n" +
                    "\t\t\t\t\t<b>热度：</b>&nbsp;<span>"+course.stuNum+"</span>人\n" +
                    "\t\t\t\t\t&nbsp;&nbsp;\n" +
                    "\n" +
                    "\t\t\t\t\t<b>学校：</b>&nbsp;<span>"+course.schoolNum+"</span>所\n" +
                    "\t\t\t\t\t&nbsp;&nbsp;\n" +
                    "\n" +
                    "\t\t\t\t\t<b>类别：</b>&nbsp;<span>"+course.courseType+"</span>\n" +
                    "\t\t\t\t</p>\n" +
                    "\t\t        </a>\n" +
                    "\t\t      </li>"
                );
                $("#revealPanel > div > ul").empty();
                schools[i]=course.schoolsVOS;

            });
            $("#mainpage > div.ui-panel-wrapper > div.ui-content.jqm-content > ul > li").each(function (i,item) {
                $(this).click(function () {
                    $("#revealPanel > div > ul").empty();
                    var index=$(this).index();
                    var school=schools[index];
                    $(school).each(function (i,school) {
                        $("#revealPanel > div > ul").append(
                            "<li data-role=\"list-divider\" role=\"heading\" class=\"ui-li-divider ui-bar-inherit ui-first-child\">\n" +
                            "\t\t          "+school.schoolName+"\n" +
                            "\t\t          <span style='float: right'><b>"+school.stuNum+"</b>人</span>\n" +
                            "\t\t        </li>"
                        )
                    })
                })
            })

        },
        error:function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR.responseText )
            console.log(textStatus);
            console.log(errorThrown);
        }
    });


})