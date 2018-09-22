/* eslint-disable object-curly-newline */
/* global Chart */
import $ from 'jquery'

/* eslint-disable no-magic-numbers */
// random Numbers
const random = () => Math.round(Math.random() * 100)



const radarChartCapa = new Chart($('#abc'), {
  type: 'radar',
  data: {
    labels: ['Chuyên môn', 'IQ', 'Chuyên cần', 'Thái độ', 'Ngoại ngữ'],
    datasets: [{
      label: 'Nguyễn Văn A',
      backgroundColor: 'rgba(151, 187, 205, 0.2)',
      borderColor: 'black',
      pointBackgroundColor: 'black',
      pointBorderColor: '#fff',
      pointHighlightFill: '#fff',
      pointHighlightStroke: 'rgba(220, 220, 220, 1)',
      data: [50, 60, 70, 60, 50];
    }]
  },
  options: {
    responsive: true
  }
})
$(document).ready(function(){


  // radarChartCapa.data.datasets[0].data = [100, 90, 81, 56, 96];
  // radarChartCapa.update();
  lineChartExam.data.labels = ['January', 'February', 'March', 'April', 'May', 'June'];
  lineChartExam.data.datasets[0].data = [random(), random(), random(), random(), random(), random(), random()];
  lineChartExam.data.datasets[1].data = [random(), random(), random(), random(), random(), random(), random()];
  lineChartExam.data.datasets[2].data = [random(), random(), random(), random(), random(), random(), random()];
  lineChartExam.data.datasets[3].data = [random(), random(), random(), random(), random(), random(), random()];
  lineChartExam.data.datasets[4].data = [random(), random(), random(), random(), random(), random(), random()];
  lineChartExam.update();
  $("#capacityId").click(function(e){
    var char1 = new Chart()

    // e.preventDefault();
  });
});
const lineChartExam = new Chart($('#monthChartExam'), {
  type: 'line',
  data: {
    datasets: [{
      label: 'Codding',
      backgroundColor: 'rgba(220, 220, 220, 0.2)',
      borderColor: 'green',
      pointBackgroundColor: 'green',
      pointBorderColor: '#fff',
      // data: [random(), random(), random(), random(), random(), random(), random()]
    }, {
      label: 'IQ',
      backgroundColor: 'rgba(151, 187, 205, 0.2)',
      borderColor: 'black',
      pointBackgroundColor: 'black',
      pointBorderColor: '#fff',
      // data: [random(), random(), random(), random(), random(), random(), random()]
    },
    {
      label: 'Database',
      backgroundColor: 'rgba(151, 187, 205, 0.2)',
      borderColor: 'red',
      pointBackgroundColor: 'red',
      pointBorderColor: '#fff',
      // data: [random(), random(), random(), random(), random(), random(), random()]
    },
    {
      label: 'Attitude',
      backgroundColor: 'rgba(151, 187, 205, 0.2)',
      borderColor: 'blue',
      pointBackgroundColor: 'blue',
      pointBorderColor: '#fff',
      // data: [random(), random(), random(), random(), random(), random(), random()]
    }
    ,
    {
      label: 'Manager',
      backgroundColor: 'rgba(151, 187, 205, 0.2)',
      borderColor: 'yellow',
      pointBackgroundColor: 'yellow',
      pointBorderColor: '#fff',
      // data: [random(), random(), random(), random(), random(), random(), random()]
    }
  ]
  },
  options: {
    responsive: true
  }
});
