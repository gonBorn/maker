$(document).ready(function(){
    var application=JSON.parse(sessionStorage.getItem("application"));
        $("body > ul").empty().append(
        "<li>\n" +
        "\t\t<div class=\"school-time\">\n" +
        "\t\t\t<div class=\"schoolName\">"+application.schoolName+"</div>\n" +
        "\t\t\t<div class=\"line\"></div>\n" +
        "\t\t\t<div class=\"courseName\">"+application.courseName+"</div>\n" +
        "\t\t\t<div class=\"create\">"+application.createTime+"</div>\n" +
        "\t\t</div>\n" +
        "\t\t<div class=\"time-border\">\n" +
        "\t\t\t<div class=\"border\">最早开课时间</div>\n" +
        "\t\t\t<div class=\"create\">"+application.leftBorder+"</div>\n" +
        "\t\t\t<div class=\"border\">最晚开课时间</div>\n" +
        "\t\t\t<div class=\"create\">"+application.rightBorder+"</div>\n" +
        "\t\t</div>\n" +
        "\t\t<div class=\"app-status\">\n" +
        "\t\t\t<div>"+application.appStatus+"</div>\n" +
        "\t\t</div>\n" +
        "\t</li>"
    )
    var teachers=[];
    $.ajax({
        type:"get",
        url:"/maker/teachers",
        success:function (data) {
            teachers=data;
            $("#teacher > select").empty();
            $(teachers).each(function (i,teacher) {
                $("#teacher > select").append(
                    "<option>"+teacher.teacherName+"</option>");
            });
            $("body > div.footer > div:nth-child(1)").click(function () {
                var start=$("#time > input").val();
                var addr=$("#address > input[type=text]").val();
                var teacher=$("#teacher > select option:selected").text();
                var i=$("#teacher > select option:selected").index();
                var teacherId=teachers[i].teacherId;
                $.ajax({
                    type:"post",
                    url:"/maker/institution/application/details",
                    data:{
                        startTime:start,
                        classroomAddress:addr,
                        teacherId:teacherId,
                        applicationId:application.applicationId
                    },
                    success:function (data) {
                        alert("成功");
                        window.location.href="insApplication.html";
                    }
                })
            });


        }
    });
    $("body > div.footer > div:nth-child(2)").click(function () {
        window.location.href="insApplication.html";
    })

})