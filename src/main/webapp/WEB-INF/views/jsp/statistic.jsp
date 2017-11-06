<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31.10.2017 г.
  Time: 17:13 ч.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

        // Load the Visualization API and the corechart package.
        google.charts.load('current', {'packages':['corechart']});

        // Set a callback to run when the Google Visualization API is loaded.
        google.charts.setOnLoadCallback(drawChart);

        // Callback that creates and populates a data table,
        // instantiates the pie chart, passes in the data and
        // draws it.
        function drawChart() {

            // Create the data table.
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Topping');
            data.addColumn('number', 'Slices');
            data.addRows([
//                function(){

//                for(index = 0; index < 10;index++){
//                    if(index == 10){}
//                    ['Mushrooms', 3]
//                }
//            }

                ['${sessionScope.user.username}', 3],
                ['Onions', 1],
                ['Olives', 1],
                ['Zucchini', 1],
                ['Pepii', 4],
                ['kolio', 8],
                ['Pepperoni', 2],
                ['cheche', 1]
            ]);

            // Set chart options
            var options = {'title':'Category Activity',
                'width':500,
                'height':600};

            // Instantiate and draw our chart, passing in some options.
            var chart = new google.visualization.PieChart(document.getElementById('chart_divs'));
            chart.draw(data, options);
        }
    </script>
</head>

<body>
<!--Div that will hold the pie chart-->
<div id="chart_divs" style="width: 50%; float:left; height: 500px;"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Year', 'Sales', 'Expenses'],
            ['2013',  1000,      400],
            ['2014',  1170,      460],
            ['2015',  660,       1120],
            ['2016',  ${ 12 },      540]
        ]);

        var options = {
            title: 'Company Performance',
            hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
            vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_diva'));
        chart.draw(data, options);
    }
</script>
</head>
<body>
<div id="chart_diva" style="width: 50%; height: 500px; float:right;"></div>
</body>
</body>
</html>
















<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta charset="UTF-8">--%>
<%--</head>--%>
<%--<body>--%>

<%--<script src="static/jquery-2.2.3.min.js"></script>--%>
<%--<script src="static/Chart.min.js"></script>--%>

<%--<script>--%>
    <%--function chart(){--%>
        <%--// Get context with jQuery - using jQuery's .get() method.--%>
        <%--var salesChartCanvas = $("#salesChart").get(0).getContext("2d");--%>
        <%--// This will get the first returned node in the jQuery collection.--%>
        <%--var salesChart = new Chart(salesChartCanvas);--%>
        <%--var salesChartData = {--%>
            <%--labels: ["1","2","3","4","5","6","7","8"],--%>
            <%--datasets: [--%>
                <%--{--%>
                    <%--label: "likes",--%>
                    <%--fillColor: "rgba(63, 63, 191,0.5)",--%>
                    <%--strokeColor: "rgba(63, 63, 191,0.5)",--%>
                    <%--pointColor: "rgba(63, 63, 191,0.5)",--%>
                    <%--pointStrokeColor: "rgb(220,220,220)",--%>
                    <%--pointHighlightFill: "rgb(220,220,220)",--%>
                    <%--pointHighlightStroke: "rgb(220,220,220)",--%>
                    <%--data: [0,120,180,250,300,315,420,425]--%>
                <%--},--%>
                <%--{--%>
                    <%--label: "love",--%>
                    <%--fillColor: "rgba(239, 15, 48,0.5)",--%>
                    <%--strokeColor: "rgba(239, 15, 48,0.5)",--%>
                    <%--pointColor: "rgba(239, 15, 48,0.5)",--%>
                    <%--pointStrokeColor: "rgba(239, 15, 48,0.5)",--%>
                    <%--pointHighlightFill: "rgba(239, 15, 48,0.5)",--%>
                    <%--pointHighlightStroke: "rgba(239, 15, 48,0.5)",--%>
                    <%--data: [0,120,180,250,300,315,420,425]--%>
                <%--},--%>
                <%--{--%>
                    <%--label: "haha",--%>
                    <%--fillColor: "rgba(233, 243, 41,0.5)",--%>
                    <%--strokeColor: "rgba(233, 243, 41,0.5)",--%>
                    <%--pointColor: "rgba(233, 243, 41,0.5)",--%>
                    <%--pointStrokeColor: "rgba(233, 243, 41,0.5)",--%>
                    <%--pointHighlightFill: "rgba(233, 243, 41,0.5)",--%>
                    <%--pointHighlightStroke: "rgba(233, 243, 41,0.5)",--%>
                    <%--data: [0,20,50,700,100,115,170,100]--%>
                <%--},--%>
                <%--{--%>
                    <%--label: "wow",--%>
                    <%--fillColor: "rgba(48, 243, 41,0.5)",--%>
                    <%--strokeColor: "rgba(48, 243, 41,0.5)",--%>
                    <%--pointColor: "rgba(48, 243, 41,0.5)",--%>
                    <%--pointStrokeColor: "rgba(48, 243, 41,0.5)",--%>
                    <%--pointHighlightFill: "rgba(48, 243, 41,0.5)",--%>
                    <%--pointHighlightStroke: "rgba(48, 243, 41,0.5)",--%>
                    <%--data: [0,30,60,90,120,145,220,280]--%>
                <%--},--%>
                <%--{--%>
                    <%--label: "sad",--%>
                    <%--fillColor: "rgba(0, 0, 4,0.5)",--%>
                    <%--strokeColor: "rgba(0, 0, 4,0.5)",--%>
                    <%--pointColor: "rgba(0, 0, 4,0.5)",--%>
                    <%--pointStrokeColor: "rgba(0, 0, 4,0.5)",--%>
                    <%--pointHighlightFill: "rgba(0, 0, 4,0.5)",--%>
                    <%--pointHighlightStroke: "rgba(0, 0, 4,0.5)",--%>
                    <%--data: [0,10,20,25,40,545,50,60]--%>
                <%--},{--%>
                    <%--label: "angry",--%>
                    <%--fillColor: "rgba(84, 78, 78,0.5)",--%>
                    <%--strokeColor: "rgba(84, 78, 78,0.5)",--%>
                    <%--pointColor: "rgba(84, 78, 78,0.5)",--%>
                    <%--pointStrokeColor: "rgba(84, 78, 78,0.5)",--%>
                    <%--pointHighlightFill: "rgba(84, 78, 78,0.5)",--%>
                    <%--pointHighlightStroke: "rgba(84, 78, 78,0.5)",--%>
                    <%--data: [520,0,20,22,25,40,42,503]--%>
                <%--}--%>

            <%--]--%>
        <%--};--%>

        <%--var salesChartOptions = {--%>
            <%--//Boolean - If we should show the scale at all--%>
            <%--showScale: true,--%>
            <%--//Boolean - Whether grid lines are shown across the chart--%>
            <%--scaleShowGridLines: true,--%>
            <%--//String - Colour of the grid lines--%>
            <%--scaleGridLineColor: "rgba(0,0,0,.05)",--%>
            <%--//Number - Width of the grid lines--%>
            <%--scaleGridLineWidth: 1,--%>
            <%--//Boolean - Whether to show horizontal lines (except X axis)--%>
            <%--scaleShowHorizontalLines: true,--%>
            <%--//Boolean - Whether to show vertical lines (except Y axis)--%>
            <%--scaleShowVerticalLines: true,--%>
            <%--//Boolean - Whether the line is curved between points--%>
            <%--bezierCurve: true,--%>
            <%--//Number - Tension of the bezier curve between points--%>
            <%--bezierCurveTension: 0.3,--%>
            <%--//Boolean - Whether to show a dot for each point--%>
            <%--pointDot: true,--%>
            <%--//Number - Radius of each point dot in pixels--%>
            <%--pointDotRadius: 4,--%>
            <%--//Number - Pixel width of point dot stroke--%>
            <%--pointDotStrokeWidth: 1,--%>
            <%--//Number - amount extra to add to the radius to cater for hit detection outside the drawn point--%>
            <%--pointHitDetectionRadius: 20,--%>
            <%--//Boolean - Whether to show a stroke for datasets--%>
            <%--datasetStroke: true,--%>
            <%--//Number - Pixel width of dataset stroke--%>
            <%--datasetStrokeWidth: 2,--%>
            <%--//Boolean - Whether to fill the dataset with a color--%>
            <%--datasetFill: true,--%>
            <%--//String - A legend template--%>
            <%--legendTemplate: "<ul class=\"name.toLowerCase()-legend\"><% for (int i=0; i<8; i++){%><li><span style=\"background-color:black\"></span></li><%}%></ul>",--%>
            <%--//Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container--%>
            <%--maintainAspectRatio: true,--%>
            <%--//Boolean - whether to make the chart responsive to window resizing--%>
            <%--responsive: true--%>
        <%--};--%>
        <%--//Create the line chart--%>
        <%--salesChart.Line(salesChartData, salesChartOptions);--%>
    <%--}--%>
    <%--// document.getElementById("chart").innerHTML = chart();--%>
<%--</script>--%>

<%--<div class="row">--%>
    <%--<div class="col-md-12">--%>
        <%--<div class="box">--%>
            <%--<div class="box-header with-border">--%>
                <%--<h3 class="box-title">Statistics</h3>--%>
            <%--</div>--%>
            <%--<div class="box-body">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-md-8">--%>
                        <%--<p class="text-center">--%>
                            <%--<strong></strong>--%>
                        <%--</p>--%>
                        <%--<div class="chart" style="width: 800px;">--%>
                            <%--<canvas id="salesChart" style="height: 1000px;"></canvas>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>


<%--<script type="text/javascript">--%>
    <%--chart();--%>
<%--</script>--%>



</body>

</html>
</body>
