(function ($, appName) {
    /// <param name="appName">namespace of application.</param>
    'use strict';
    var questions = [];
    var answers = [];
    var currentIndex = 1;
    var skill_id = 0;
    var $dialog = {};
    var starting  = false;
    var employee_skill_test_id = 0;

    var getRadioItem = function(stt, data){
        var item_count = 5;
        var item = {
            stt: stt,
            detail_id: data.id,
            name: 'q_' + data.id,
            title: data.question,
            type : 'radio',
            answers : [],
            answered: 0
        }
    
        for (var i = 1; i <= item_count; i++) { 
            if(!data['answer' + i]) {
                continue;
            }

            item.answers.push({
                value: i,
                text: data['answer' + i]
            })
        }
        return item;
    };
    
    var getCheckboxItem = function(stt, data){
        
    };
    
    var parseArray = (data, callback) => {
        console.log('==========parseArray', data);
        employee_skill_test_id = data.employee_skill_test_id;

        for (var i = 1; i <= data.details.length; i++) {
            var rand = 2;
            switch(rand) {
                case 1:
                    questions.push(getCheckboxItem(i, data.details[i - 1]))
                    break;
                default:
                    questions.push(getRadioItem(i, data.details[i - 1]))
                    break;
            } 
        }

        if (data.details.length == 0) {
            alert('Đanh sách câu hỏi chưa sẵn sàng, hãy liên hệ quản trị viên');
            return false;
        }

        if (callback){
            callback();
        }
    }

    var initData = function(callback){
        var url = '/test/index?employee_skill_test_id=' + employee_skill_test_id;
        serviceInvoker.get(url, {}, {
            error: function(response){
                console.log('=================initData', response);
            },
            success: function(response){
                console.log('=================initData success', response);
                if (response && response.data) {
                    parseArray(response.data, callback);
                }
            }
        }, null, true)

    }
    
    var generateRadio = (item) => {
        var html = '';
        for (var i = 0; i < item.answers.length; i++){
            var id = item.name + '_' + i;
            var answer = item.answers[i];
            var checked = item.answered == answer.value ? 'checked="checked"' : '';

            html += '<div class="form-check">';
            html += '<input class="form-check-input" ' + checked + ' type="radio" value="' + answer.value + '" name="'+ item.name +'" id="'+ id +'">';
            html += '<label class="form-check-label" for="' + id + '">'+ answer.text +'</label>';
            html += '</div>';
        }

        return html;
    }

    var generateCheckboxes = (item) => {
        var html = '';
        for (var i = 0; i < item.answers.length; i++){
            var id = item.name + '_' + i;
            var answer = item.answers[i];
            var checked = item.answered.findIndex(val => val == answer.value) > -1 ? 'checked="checked"' : '';

            html += '<div class="form-check">';
            html += '<input class="form-check-input" ' + checked + 'type="checkbox" value="' + answer.value + '" name="'+ item.name +'" id="'+ id +'">';
            html += '<label class="form-check-label" for="' + id + '">'+ answer.text +'</label>';
            html += '</div>';
        }

        return html;
    }

    var bindQuestion = () => {
        if (currentIndex < 1 || currentIndex > questions.length){
            currentIndex = 1
        }

        var item = questions[currentIndex - 1];
        $('#q-title').html(item.title);
        $('#q-stt').html(currentIndex);
        $('#q-total').html(questions.length);
        var answerHtml = '';
        switch(item.type) {
            case 'radio':
                answerHtml = generateRadio(item)
                break;
            case 'checkbox':
                answerHtml = generateCheckboxes(item)
                break;
        }

        $('#q-answer').html(answerHtml);
    }

    var saveItem = () => {
        if (currentIndex < 1 || currentIndex > questions.length){
            return false;
        }

        var item = questions[currentIndex - 1];

        if (!item || !item.name){
            return false;
        }

        var index = answers.findIndex(obj => obj.name === item.name)
        
        var answerItem = {
            detail_id: item.detail_id,
            name: item.name,
            answer: 0
        };

        if (index > -1) {
            answerItem = answers[index];
        }

        $('#q-answer').find('input[name="' + item.name + '"]:checked').each(function(){
            answerItem.answer = $(this).val();
        })

        if (answerItem.answer.length == 0){
            return false;
        }

        if (index > -1){
            answers[index] = answerItem;
        }
        else {
            answers.push(answerItem);
        }

        // gán giấ trị đã chọn
        try {
            questions[currentIndex - 1].answered = answerItem.answer;
        } catch (error) {
            console.log(error);
        }

        console.log('=================saveItem',answerItem);
        return answerItem;
    }

    var countdownArray = []

    var countDownStart = (elem, minutes = 10) => {
        // Set the date we're counting down to
        var countDownDate = moment().add(minutes, 'm').valueOf();
        // Update the count down every 1 second
        var x = setInterval(function() {
            // Get todays date and time
            var now = new Date().getTime();

            // Find the distance between now and the count down date
            var distance = countDownDate - now;

            // Time calculations for days, hours, minutes and seconds
            var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
            var seconds = Math.floor((distance % (1000 * 60)) / 1000);

            // Display the result in the element with id="demo"
            var timeLeft = (hours > 0 ? hours + ":" : '') + minutes + ":" + seconds
            $(elem).html(timeLeft);

            // If the count down is finished, write some text 
            if (distance < 0) {
                clearInterval(x);
                $(elem).html("EXPIRED");
            }
        }, 1000);

        countdownArray.push(x);
    }

    var clearTimers = () => {
        for (var i = 0; i < countdownArray.length; i ++) {
            clearInterval(countdownArray[i]);
        }
    }

    var closeDialog = () => {
        Common.redirect('/test.html');
    }

    var submit = () => {
        var formData = {
            data : {
                employee_skill_test_id: employee_skill_test_id + '',
                user_id: currentUser.id + '',
                skill_id: skill_id,
                details: []
            }
        };

        for (var i = 0; i < answers.length; i ++) {
            formData.data.details.push({
                test_detail_id: answers[i].detail_id,
                answer: answers[i].answer + ''
            })
        }

        var url = '/test/update';
        serviceInvoker.post(url, formData, {
            error: function(response){
                alert('error in submit data');
            },
            success: function(response){
                try {
                    console.log(response);
                    if (response.result == 1) {
                        alert(response.message);
                        return false;
                    }

                    alert('Chúc mừng bạn đã hoàn thành bài thi số điểm đạt được:' + response.data.point)

                    $('#q-close').show();
                    $('#q-start, #q-prev, #q-next, #q-save').hide();
                    closeDialog();
                } catch (error) {
                    console.log(error);
                }
            }
        })
    }

    var bindEvents = () => {
        $(document).on("click", '#test-start', function(e) {
            e.preventDefault();
            var dialog = $(this).attr('ref');
            skill_id = $(this).attr('skill_id');
            employee_skill_test_id = $(this).attr('employee_skill_test_id');
            $dialog = $(dialog);
            $dialog.modal('show');

            $(dialog).on('hidden.bs.modal', function (e) {
                closeDialog();
            })
         });
        
         $(document).on("click", '#q-close', function(e) {
            e.preventDefault();
            if (starting) {
                if (confirm('Bạn có đồng ý gửi kết quả bài kiểm tra?')) {
                    // save
                    submit();
                }
            }
            else {
                $dialog.modal('hide');
            }
         });

        $(document).on("click", '#q-start', function(e) {
            e.preventDefault();
            if (!confirm("Bắt đầu làm bài thi?")) {
                return false;
            }

            initData(function(response){
                //set flag
                starting  = true;

                $('#q-start, #q-close').hide();
                $('#q-prev, #q-next, #q-save').show();

                countDownStart('#count-down-time', 10);
                bindQuestion();
            });
         });
         
        $('#test-start-dialog').on("click", "#q-prev", function(e) {
            e.preventDefault();
            saveItem();
            currentIndex = currentIndex - 1;
            bindQuestion();
        });

        // next
        $('#test-start-dialog').on("click", "#q-next", function(e) {
            e.preventDefault();
            saveItem();
            currentIndex = currentIndex + 1;
            bindQuestion();
        });

        // submit
        $('#test-start-dialog').on("click", "#q-save", function(e) {
            e.preventDefault();
            if (confirm('Bạn có đồng ý gửi kết quả bài kiểm tra?')) {
                // save
                submit();
            }
        });

        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            var target = $(e.target).attr("href") // activated tab
            alert(target);
        });
    }

    var bindHistories = (skill_test) => {
        var tb = $('#tbl-history-tested');
        var trs = '';

        for (var i = 0; i < skill_test.length; i ++) {
            var item = skill_test[i];
            trs += '<tr>';
            trs += '<td>' + item.skill.name + '</td>';
            trs += '<td class="text-center">' + item.starttime + '</td>';
            trs += '<td class="text-right">' + item.point + '/100</td>';
            trs += '</tr>';
        }

        tb.html(trs);
    }

    var bindTestAvailable = (skill_test) => {
        var tb = $('#tbl-available-tested');
        var trs = '';

        for (var i = 0; i < skill_test.length; i ++) {
            var item = skill_test[i];
            trs += '<tr>';
            trs += '<td>' + item.skill.name + '</td>';
            trs += '<td class="text-center">' + moment().format("YYYY-MM-DD") + '</td>';
            trs += '<td class="text-center">';
            trs += '    <button class="btn btn-sm btn-danger" employee_skill_test_id="' + item.skill.employee_skill_test_id + '" skill_id="' + item.skill.id + '" type="button" data-toggle="modal" id="test-start" ref="#test-start-dialog">';
            trs += '        <i class="fa fa-dot-circle-o"></i> Bắt đầu';
            trs += '    </button>';
            trs += '</td>';
            trs += '</tr>';
        }

        tb.html(trs);
    }

    var getHistoryTested = () => {
        var url = '/skilltest?starttime=1&user_id=' + currentUser.id;
        serviceInvoker.get(url, {}, {
            error: function(response){
                console.log('=================getHistoryTested', response);
            },
            success: function(response){
                if (response.data && response.data.employee_skill_test) {
                    bindHistories(response.data.employee_skill_test);
                }
            }
        }, null, true)
    }

    var getAvailableTest = () => {
        var url = '/skilltest?starttime=0&user_id=' + currentUser.id;
        serviceInvoker.get(url, {}, {
            error: function(response){
                console.log('=================getAvailableTest', response);
            },
            success: function(response){
                if (response.data && response.data.employee_skill_test) {
                    bindTestAvailable(response.data.employee_skill_test);
                }
            }
        }, null, true)
    }

    var currentUser = {};

    appName.init = function(){
        if (!Common.checkAuthen()) {
            Common.redirect('/login.html');
        }

        currentUser = Common.currentUser();
        
        getHistoryTested();
        getAvailableTest();
        bindEvents();
    };

}(jQuery, window.TestModule = window.TestModule || {}));