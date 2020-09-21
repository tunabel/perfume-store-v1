$(function () {
    'use strict';

    let revenueByMonthCurrentYearData = [];
    let revenueByMonthCurrentYearLabels = [];

    let revenueByDayCurrMonthData = [];
    let revenueByDayCurrMonthLabels = [];

    let bestsellersCurrMonthData = [];
    let bestsellersCurrMonthLabels = [];

    let bestsellersCurrYearData = [];
    let bestsellersCurrYearLabels = [];

    let bestsellersBrandCurrYearData = [];
    let bestsellersBrandCurrYearLabels = [];

    let userRegisCurrYearData = [];
    let userRegisCurrYearLabels = [];

    function init() {
        getRevenueByMonthCurrentYear();
        getRevenueByDayCurrentMonth();
        getBestsellersOfCurrentMonth();
        getBestsellersOfCurrentYear();
        getBestsellersBrandOfCurrentYear();
        getUserRegisCurrentYear();
    }

    function getRevenueByMonthCurrentYear() {
        var linkGet = "/api/chart/order/monthly-revenue";
        axios.get(linkGet).then(function (res) {
            if (res.data.successful) {
                var data_get = res.data.data;
                for (var i = 0; i < data_get.length; i++) {
                    revenueByMonthCurrentYearData.push(data_get[i].value);
                    revenueByMonthCurrentYearLabels.push(data_get[i].label);
                }

                if ($("#revenue-by-month").length) {
                    var barChartCanvas = $("#revenue-by-month").get(0).getContext("2d");
                    // This will get the first returned node in the jQuery collection.
                    var barChart = new Chart(barChartCanvas, {
                        type: 'bar',
                        data: monthlyRevenueData,
                        options: monthlyRevenueOptions
                    });
                }
            }
        }, function (err) {
            console.log(err);
        });
    }

    function getRevenueByDayCurrentMonth() {
        var linkGet = "/api/chart/order/daily-revenue/0";
        axios.get(linkGet).then(function (res) {
            if (res.data.successful) {
                var data_get = res.data.data;
                for (var i = 0; i < data_get.length; i++) {
                    revenueByDayCurrMonthData.push(data_get[i].value);
                    revenueByDayCurrMonthLabels.push(data_get[i].label);
                }

                if ($("#revenue-by-month").length) {
                    var barChartCanvas = $("#revenue-by-day").get(0).getContext("2d");
                    // This will get the first returned node in the jQuery collection.
                    var barChart = new Chart(barChartCanvas, {
                        type: 'bar',
                        data: dailyRevenueData,
                        options: monthlyRevenueOptions
                    });
                }
            }
        }, function (err) {
            console.log(err);
        });
    }

    function getBestsellersOfCurrentMonth() {
        var linkGet = "/api/chart/product/bestseller-month";
        axios.get(linkGet).then(function (res) {
            if (res.data.successful) {
                var data_get = res.data.data;
                for (var i = 0; i < data_get.length; i++) {
                    bestsellersCurrMonthData.push(data_get[i].value);
                    bestsellersCurrMonthLabels.push(data_get[i].label);
                }

                if ($("#bestseller-of-month").length) {
                    var pieChartCanvas = $("#bestseller-of-month").get(0).getContext("2d");
                    // This will get the first returned node in the jQuery collection.
                    var pieChart = new Chart(pieChartCanvas, {
                        type: 'pie',
                        data: bestsellersMonthData,
                        options: doughnutPieOptions
                    });
                }
            }
        }, function (err) {
            console.log(err);
        });
    }

    function getBestsellersOfCurrentYear() {
        var linkGet = "/api/chart/product/bestseller-year";
        axios.get(linkGet).then(function (res) {
            if (res.data.successful) {
                var data_get = res.data.data;
                for (var i = 0; i < data_get.length; i++) {
                    bestsellersCurrYearData.push(data_get[i].value);
                    bestsellersCurrYearLabels.push(data_get[i].label);
                }

                if ($("#bestseller-of-year").length) {
                    var pieChartCanvas = $("#bestseller-of-year").get(0).getContext("2d");
                    // This will get the first returned node in the jQuery collection.
                    var pieChart = new Chart(pieChartCanvas, {
                        type: 'pie',
                        data: bestsellersYearData,
                        options: doughnutPieOptions
                    });
                }
            }
        }, function (err) {
            console.log(err);
        });
    }

    function getBestsellersBrandOfCurrentYear() {
        var linkGet = "/api/chart/brand/bestseller-year";
        axios.get(linkGet).then(function (res) {
            if (res.data.successful) {
                var data_get = res.data.data;
                for (var i = 0; i < data_get.length; i++) {
                    bestsellersBrandCurrYearData.push(data_get[i].value);
                    bestsellersBrandCurrYearLabels.push(data_get[i].label);
                }

                if ($("#bestseller-brand").length) {
                    var pieChartCanvas = $("#bestseller-brand").get(0).getContext("2d");
                    // This will get the first returned node in the jQuery collection.
                    var pieChart = new Chart(pieChartCanvas, {
                        type: 'pie',
                        data: bestsellersBrandYearData,
                        options: doughnutPieOptions
                    });
                }
            }
        }, function (err) {
            console.log(err);
        });
    }

    function getUserRegisCurrentYear() {
        var linkGet = "/api/chart/user/monthly-registration";
        axios.get(linkGet).then(function (res) {
            if (res.data.successful) {
                var data_get = res.data.data;
                for (var i = 0; i < data_get.length; i++) {
                    userRegisCurrYearData.push(data_get[i].value);
                    userRegisCurrYearLabels.push(data_get[i].label);
                }

                if ($("#user-registration").length) {
                    var barChartCanvas = $("#user-registration").get(0).getContext("2d");
                    // This will get the first returned node in the jQuery collection.
                    var barChart = new Chart(barChartCanvas, {
                        type: 'bar',
                        data: userRegisData,
                        options: userRegisOptions
                    });
                }
            }
        }, function (err) {
            console.log(err);
        });
    }

    init();

    $("#revenue-month-select").on('change', function () {
        let selectedMonth = $("#revenue-month-select").val();

        revenueByDayCurrMonthData = [];
        revenueByDayCurrMonthLabels = [];


        var linkGet = "/api/chart/order/daily-revenue/";
        axios.get(linkGet + selectedMonth).then(function (res) {
            if (res.data.successful) {
                var data_get = res.data.data;
                for (var i = 0; i < data_get.length; i++) {
                    revenueByDayCurrMonthData.push(data_get[i].value);
                    revenueByDayCurrMonthLabels.push(data_get[i].label);
                }
                let dailyRevenueData2 = {
                    labels: revenueByDayCurrMonthLabels,
                    datasets: [{
                        label: 'Revenue in VND',
                        data: revenueByDayCurrMonthData,
                        borderWidth: 1,
                        fill: false
                    }]
                };
                var barChartCanvas = $("#revenue-by-day").get(0).getContext("2d");
                // This will get the first returned node in the jQuery collection.
                var barChart = new Chart(barChartCanvas, {
                    type: 'bar',
                    data: dailyRevenueData2,
                    options: monthlyRevenueOptions
                });
            }
        }, function (err) {
            console.log(err);
        });
    })

    var monthlyRevenueData = {
        labels: revenueByMonthCurrentYearLabels,
        datasets: [{
            label: 'Revenue in VND',
            data: revenueByMonthCurrentYearData,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1,
            fill: false
        }]
    };

    var dailyRevenueData = {
        labels: revenueByDayCurrMonthLabels,
        datasets: [{
            label: 'Revenue in VND',
            data: revenueByDayCurrMonthData,
            borderWidth: 1,
            fill: false
        }]
    };

    var bestsellersMonthData = {
        labels: bestsellersCurrMonthLabels,
        datasets: [{
            label: 'Revenue in VND',
            data: bestsellersCurrMonthData,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1,
            fill: false
        }]
    };

    var bestsellersYearData = {
        labels: bestsellersCurrYearLabels,
        datasets: [{
            label: 'Revenue in VND',
            data: bestsellersCurrYearData,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1,
            fill: false
        }]
    };

    var bestsellersBrandYearData = {
        labels: bestsellersBrandCurrYearLabels,
        datasets: [{
            label: 'Revenue in VND',
            data: bestsellersBrandCurrYearData,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1,
            fill: false
        }]
    };

    var userRegisData = {
        labels: userRegisCurrYearLabels,
        datasets: [{
            label: 'Revenue in VND',
            data: userRegisCurrYearData,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1,
            fill: false
        }]
    };

    var monthlyRevenueOptions = {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true,
                    callback: function (value, index, values) {
                        return Intl.NumberFormat('vi-VI').format((value / 1000)) + '.000 VND';
                    }
                }
            }],
        },
        legend: {
            display: false
        },
        elements: {
            point: {
                radius: 0
            }
        }

    };

    var doughnutPieOptions = {
        responsive: true,
        animation: {
            animateScale: true,
            animateRotate: true
        }
    };

    var userRegisOptions = {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        },
        legend: {
            display: false
        },
        elements: {
            point: {
                radius: 0
            }
        }

    };

});