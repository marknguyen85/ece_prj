/* eslint-disable object-curly-newline */

/* global Chart */

/**
 * --------------------------------------------------------------------------
 * CoreUI Free Boostrap Admin Template (v2.0.0): main.js
 * Licensed under MIT (https://coreui.io/license)
 * --------------------------------------------------------------------------
 */

/* eslint-disable no-magic-numbers */
// random Numbers
var random = function random() {
  return Math.round(Math.random() * 100);
}; // eslint-disable-next-line no-unused-vars


var lineChart = new Chart($('#monthChartExam'), {
  type: 'line',
  data: {
    labels: ['January', 'February', 'March', 'April', 'May', 'June'],
    datasets: [{
      label: 'Codding',
      backgroundColor: 'rgba(220, 220, 220, 0.2)',
      borderColor: 'green',
      pointBackgroundColor: 'green',
      pointBorderColor: '#fff',
    }, {
      label: 'IQ',
      backgroundColor: 'rgba(151, 187, 205, 0.2)',
      borderColor: 'black',
      pointBackgroundColor: 'black',
      pointBorderColor: '#fff'
    }, {
      label: 'Database',
      backgroundColor: 'rgba(151, 187, 205, 0.2)',
      borderColor: 'red',
      pointBackgroundColor: 'red',
      pointBorderColor: '#fff'
    }, {
      label: 'Attitude',
      backgroundColor: 'rgba(151, 187, 205, 0.2)',
      borderColor: 'blue',
      pointBackgroundColor: 'blue',
      pointBorderColor: '#fff'
    }, {
      label: 'Manager',
      backgroundColor: 'rgba(151, 187, 205, 0.2)',
      borderColor: 'yellow',
      pointBackgroundColor: 'yellow',
      pointBorderColor: '#fff'
    }]
  },
  options: {
    responsive: true
  }
}); // eslint-disable-next-line no-unused-vars

var barChart = new Chart($('#canvas-2'), {
  type: 'bar',
  data: {
    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
    datasets: [{
      backgroundColor: 'rgba(220, 220, 220, 0.5)',
      borderColor: 'rgba(220, 220, 220, 0.8)',
      highlightFill: 'rgba(220, 220, 220, 0.75)',
      highlightStroke: 'rgba(220, 220, 220, 1)',
      data: [random(), random(), random(), random(), random(), random(), random()]
    }, {
      backgroundColor: 'rgba(151, 187, 205, 0.5)',
      borderColor: 'rgba(151, 187, 205, 0.8)',
      highlightFill: 'rgba(151, 187, 205, 0.75)',
      highlightStroke: 'rgba(151, 187, 205, 1)',
      data: [random(), random(), random(), random(), random(), random(), random()]
    }]
  },
  options: {
    responsive: true
  }
}); // eslint-disable-next-line no-unused-vars

var doughnutChart = new Chart($('#canvas-3'), {
  type: 'doughnut',
  data: {
    labels: ['Red', 'Green', 'Yellow'],
    datasets: [{
      data: [300, 50, 100],
      backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
      hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
    }]
  },
  options: {
    responsive: true
  }
}); // eslint-disable-next-line no-unused-vars

var radarChart = new Chart($('#canvasCapaci'), {
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
      data: [50, 60, 70, 60, 50]
    }]
  },
  options: {
    responsive: true
  }
}); // eslint-disable-next-line no-unused-vars

var pieChart = new Chart($('#canvas-5'), {
  type: 'pie',
  data: {
    labels: ['Red', 'Green', 'Yellow'],
    datasets: [{
      data: [300, 50, 100],
      backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
      hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
    }]
  },
  options: {
    responsive: true
  }
}); // eslint-disable-next-line no-unused-vars

var polarAreaChart = new Chart($('#canvas-6'), {
  type: 'polarArea',
  data: {
    labels: ['Red', 'Green', 'Yellow', 'Grey', 'Blue'],
    datasets: [{
      data: [11, 16, 7, 3, 14],
      backgroundColor: ['#FF6384', '#4BC0C0', '#FFCE56', '#E7E9ED', '#36A2EB']
    }]
  },
  options: {
    responsive: true
  }
});
//# sourceMappingURL=charts.js.map
