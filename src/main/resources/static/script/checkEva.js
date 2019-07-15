$(document).ready(function(){
    var course=JSON.parse(sessionStorage.getItem("course"));
    $.ajax({
        type: "get",
        url: "/maker/stu/course/selected/eva",
        data:{
            courseId:course.courseId
        },
        success: function (res) {
            $("body > div.content > div > div.courseDetail").text(res.data.courseName);
            var myAverage=res.data.myAverage;
            var classAverage=res.data.classAverage;
            var allMyEvaluation=res.data.allMyEvaluation;
            $("body > div.content > table > tbody").empty();
            $(allMyEvaluation).each(function (i,eva) {
                $("body > div.content > table > tbody").append(
                    "<tr>\n" +
                    "\t\t\t  \t<td>"+eva.evaluationTime+"</td>\n" +
                    "\t\t\t  \t<td>"+eva.practical+"</td>\n" +
                    "\t\t\t  \t<td>"+eva.positive+"</td>\n" +
                    "\t\t\t  \t<td>"+eva.teamwork+"</td>\n" +
                    "\t\t\t  \t<td>"+eva.creative+"</td>\n" +
                    "\t\t\t  \t<td>"+eva.leadership+"</td>\n" +
                    "\t\t\t</tr>"
                )
            });

            var teacherChart = echarts.init(document.getElementById('teacher'));
            var option = {
                title: {
                    text: '教师评价雷达图',
                    left:'center',
                    // textStyle: {
                    // 	fontSize:14
                    // }

                },
                legend: {
                    data: ['我的水准', '平均水准'],
                    right:'right'
                },
                radar: {
                    // shape: 'circle',
                    name: {
                        textStyle: {
                            color: '#fff',
                            backgroundColor: '#999',
                            borderRadius: 3,
                            padding: [3, 5]
                        }
                    },
                    indicator: [
                        { name: '创新', max: 5},
                        { name: '积极', max: 5},
                        { name: '动手', max: 5},
                        { name: '协作', max: 5},
                        { name: '领导', max: 5}
                    ]
                },
                series: [{
                    name: '我的水准 vs 平均水准',
                    type: 'radar',
                    // areaStyle: {normal: {}},
                    data : [
                        {
                            value : [myAverage.creative,myAverage.positive,myAverage.practical,myAverage.teamwork,myAverage.leadership],
                            name : '我的水准'
                        },
                        {
                            value : [classAverage.creative,classAverage.positive,classAverage.practical,classAverage.teamwork,classAverage.leadership],
                            name : '平均水准'
                        }
                    ]
                }]
            };
            teacherChart.setOption(option);
        }
    });
})