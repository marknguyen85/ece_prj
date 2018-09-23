/* eslint-disable object-curly-newline */
/* global Chart */
import $ from 'jquery'

/**
 * --------------------------------------------------------------------------
 * CoreUI Free Boostrap Admin Template (v2.0.0): main.js
 * Licensed under MIT (https://coreui.io/license)
 * --------------------------------------------------------------------------
 */

/* eslint-disable no-magic-numbers */
// random Numbers
const random = () => Math.round(Math.random() * 100)// eslint-disable-next-line no-unused-vars
const lineChart = new Chart($('#monthChartExam'), {
  type: 'line',
  data: {
    // labels : ['January', 'February', 'March', 'April', 'May', 'June'],
    datasets: [
      // {
      // backgroundColor: 'rgba(220, 220, 220, 0.2)',
      //   borderColor: 'green',
      //   pointBackgroundColor: 'green',
      //   pointBorderColor: '#fff'
      // }, {
      //
      //   backgroundColor: 'rgba(151, 187, 205, 0.2)',
      //   borderColor: 'black',
      //   pointBackgroundColor: 'black',
      //   pointBorderColor: '#fff'
      // },
      // {
      //
      //   backgroundColor: 'rgba(151, 187, 205, 0.2)',
      //   borderColor: 'red',
      //   pointBackgroundColor: 'red',
      //   pointBorderColor: '#fff'
      // },
      // {
      //
      //   backgroundColor: 'rgba(151, 187, 205, 0.2)',
      //   borderColor: 'blue',
      //   pointBackgroundColor: 'blue',
      //   pointBorderColor: '#fff'
      // },
      // {
      //
      //   backgroundColor: 'rgba(151, 187, 205, 0.2)',
      //   borderColor: 'yellow',
      //   pointBackgroundColor: 'yellow',
      //   pointBorderColor: '#fff'
      // }
    ]
  },
  options: {
    responsive: true
  }
})

// eslint-disable-next-line no-unused-vars
const barChart = new Chart($('#canvas-2'), {
  type: 'bar',
  data: {
    labels : ['Coding', 'Database', 'IQ', 'Attitude', 'Language'],
    datasets : [
      {
        label: 'Nguyễn Văn A',
        backgroundColor : 'rgba(220, 220, 220, 0.5)',
        borderColor : 'rgba(220, 220, 220, 0.8)',
        highlightFill: 'rgba(220, 220, 220, 0.75)',
        highlightStroke: 'rgba(220, 220, 220, 1)',
        data : [random(), random(), random(), random(), random()]
      },
      {
        label: 'Nguyễn Văn A',
        backgroundColor : 'rgba(151, 187, 205, 0.5)',
        borderColor : 'rgba(151, 187, 205, 0.8)',
        highlightFill : 'rgba(151, 187, 205, 0.75)',
        highlightStroke : 'rgba(151, 187, 205, 1)',
        data : [random(), random(), random(), random(), random()]
      }
    ]
  },
  options: {
    responsive: true
  }
})

// eslint-disable-next-line no-unused-vars
const doughnutChart = new Chart($('#canvas-3'), {
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
})// eslint-disable-next-line no-unused-vars
const radarChart = new Chart($('#canvasCapaci'), {
  type: 'radar',
  data: {
   datasets: [{
      label: ' ',
      backgroundColor: 'rgba(151, 187, 205, 0.2)',
      borderColor: 'black',
      pointBackgroundColor: 'black',
      pointBorderColor: '#fff',
      pointHighlightFill: '#fff',
      pointHighlightStroke: 'rgba(220, 220, 220, 1)'
    }]
  },
  options: {
    responsive: true
  }
})// eslint-disable-next-line no-unused-vars
const pieChart = new Chart($('#canvas-5'), {
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
})

// eslint-disable-next-line no-unused-vars
const polarAreaChart = new Chart($('#canvas-6'), {
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
})
const myRadarChart = new Chart($('#canvas-9'), {
  type: 'radar',
  data: {
    labels: ['Coding', 'Database', 'IQ', 'Attitude', 'Language'],
    datasets: [
      {
        label: 'Nguyễn Văn A',
        backgroundColor: 'rgba(220, 220, 220, 0.2)',
        borderColor: 'rgba(220, 220, 220, 1)',
        pointBackgroundColor: 'rgba(220, 220, 220, 1)',
        pointBorderColor: '#fff',
        pointHighlightFill: '#fff',
        pointHighlightStroke: 'rgba(220, 220, 220, 1)',
        data: []
      },
      {
        label: 'Nguyễn Văn B',
        backgroundColor: 'rgba(151, 187, 205, 0.2)',
        borderColor: 'rgba(151, 187, 205, 1)',
        pointBackgroundColor: 'rgba(151, 187, 205, 1)',
        pointBorderColor: '#fff',
        pointHighlightFill: '#fff',
        pointHighlightStroke: 'rgba(151, 187, 205, 1)',
        data: [28, 48, 40, 19, 96]
      }
    ]
  },
  options: {
    responsive: true
  }
})
