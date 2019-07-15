$(document).ready(function(){
  var selector=1;
  $("#institution").click(function(){
    $(".role-btn").removeClass("focus-btn");
    $("#institution").addClass("focus-btn");
    selector=1
  });
  $("#teacher").click(function(){
    $(".role-btn").removeClass("focus-btn");
    $("#teacher").addClass("focus-btn");
    selector=4
  });
  $("#student").click(function(){
    $(".role-btn").removeClass("focus-btn");
    $("#student").addClass("focus-btn");
    selector=3
  });
  $("#school").click(function(){
    $(".role-btn").removeClass("focus-btn");
    $("#school").addClass("focus-btn");
    selector=2
  });
  // $(".role-btn").each(function (i,item) {
  //   $(item).on('click',function () {
  //
  //     $(".role-btn").each(function (i,item) {
  //       var _class=$(item).attr("class");
  //       if(_class.length>8)selector=$(item).text()
  //       console.log(selector)
  //     })
  //   })
  // })
  $("#form-login > div:nth-child(4) > button").on('click',function () {
    var userId=$("#usrId").val();
    var password=$("#password").val();
    console.log(userId);
    console.log(selector);
    switch (selector) {
      case 1:
        $.ajax({
          type:"get",
          url:"/maker/insLogin",
          data:{
            insId:userId
          },
          success:function (data) {
            console.log(data);
            if(data){
              window.location.href="http://localhost:8080/maker/static/html/institution/ManagerIndex.html"
            }else alert("fail");
          },
          error:function (e) {
            alert(e);
          }
        });
        break;
      case 2:
        $.ajax({
          type:"get",
          url:"/maker/schoolLogin",
          data:{
            schoolId:userId
          },
          success:function (data) {
            if(data){
              window.location.href="http://localhost:8080/maker/static/html/school/schoolCourseSelect.html"
            }else alert("fail");
          },
          error:function (e) {
            alert(e);
          }
        });
        break;
      case 3:
        $.ajax({
          type:"get",
          url:"/maker/studentLogin",
          data:{
            studentId:userId
          },
          success:function (data) {
            if(data){
              window.location.href="http://localhost:8080/maker/static/html/stu/stuCourseSelect.html"
            }else alert("fail");
          },
          error:function (e) {
            alert(e);
          }
        });
        break;
      case 4:
        $.ajax({
          type:"get",
          url:"/maker/teacherLogin",
          data:{
            teacherId:userId
          },
          success:function (data) {
            if(data){
              window.location.href="http://localhost:8080/maker/static/html/teacher/teacherIndex.html"
            }else alert("fail");
          },
          error:function (e) {
            alert(e);
          }
        });
        break;
    }
  })
});