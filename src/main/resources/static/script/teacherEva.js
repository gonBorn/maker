$(document).ready(function () {
    var student = JSON.parse(sessionStorage.getItem("student"));
    var course = JSON.parse(sessionStorage.getItem("course"));
    var scores = [];
    $("#detail > div.ui-header.ui-bar-inherit > h1").text(student.stuName);
    $("#detail > div.ui-content > form > div:nth-child(6) > fieldset > div.ui-block-a > div > input[type=submit]").on("click", function () {
        for (var i = 0; i < 5; ++i) {
            var list = $("#detail > div.ui-content > form > div:nth-child(" + (i + 1) + ") > fieldset > div.ui-controlgroup-controls");
            var labels = $(list).find("label.ui-btn");
            $(labels).each(function (index, label) {
                if ($(label).hasClass("ui-btn-active")) scores[i] = parseInt($(label).text());
            })
        }
        console.log(scores);
        $.ajax({
            type: "post",
            url: "/maker/teacher/course/list/stuName/eva",
            data: {
                studentId: student.studentId,
                courseId: course.courseId,
                practical:scores[0],
                positive:scores[1],
                leadership:scores[2],
                teamwork:scores[3],
                creative:scores[4]
            },
            success: function (data) {
                alert("成功！");
                window.location.href="stuNameList.html";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR.responseText);
                console.log(textStatus);
                console.log(errorThrown);
            }

        })
    })

})