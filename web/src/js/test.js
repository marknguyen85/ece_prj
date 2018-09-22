(function ($, appName) {
    /// <param name="appName">namespace of application.</param>
    'use strict';
    var questions = [];
    var answers = [];
    var currentIndex = 1;

    var getRadioItem = function(stt, item_count){
        var item = {
            stt: stt,
            name: 'question_' + stt,
            title: 'Câu hỏi ' + stt,
            type : 'radio',
            answers : [],
            answered: []
        }
    
        for (var i = 1; i <= item_count; i++) { 
            item.answers.push({
                value: i,
                text: 'Đáp án ' + i
            })
        }
        return item;
    };
    
    var getCheckboxItem = function(stt, item_count){
        var item = {
            stt: stt,
            name: 'question_' + stt,
            title: 'Câu hỏi ' + stt,
            type : 'checkbox',
            answers : [],
            answered: []
        }
    
        for (var i = 1; i <= item_count; i++) { 
            item.answers.push({
                value: i,
                text: 'Đáp án ' + i
            })
        }
        return item;
    };
    
    var initArray = function(){
        for (var i = 1; i <= 10; i++) { 
            var rand = Math.floor((Math.random() * 2) + 1);
            switch(rand) {
                case 1:
                    questions.push(getCheckboxItem(i, 3))
                    break;
                case 2:
                    questions.push(getRadioItem(i, 3))
                    break;
            } 
        }
    }
    
    var generateRadio = (item) => {
        var html = '';
        for (var i = 0; i < item.answers.length; i++){
            var id = item.name + '_' + i;
            var answer = item.answers[i];
            var checked = item.answered.findIndex(val => val == answer.value) > -1 ? 'checked="checked"' : '';

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
        if (currentIndex < 1 || currentIndex >= questions.length){
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
        if (currentIndex < 1 || currentIndex >= questions.length){
            return false;
        }

        var item = questions[currentIndex - 1];

        if (!item || !item.name){
            return false;
        }

        var index = answers.findIndex(obj => obj.name === item.name)
        
        var answerItem = {
            name: item.name,
            answer: []
        };

        if (index > -1) {
            answerItem = answers[index];
        }

        $('#q-answer').find('input[name="' + item.name + '"]:checked').each(function(){
            answerItem.answer.push($(this).val());
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

        console.log('=================answers',answerItem);
        return answerItem;
    }

    var updateObj = ( old, updating ) => {
        for (var i in old) {
          if (old[i].value == value) {
            old[i].desc = desc;
             break; //Stop this loop, we found it!
          }
        }
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

    var starting  = false;
    var closeDialog = () => {
        for (var i = 0; i < countdownArray.length; i++) {
            clearInterval(countdownArray[i]);
        }
        
        $('#count-down-time').html("");
        starting  = false;
        $dialog = {};
    }

    var submit = () => {
        starting = false;
    }

    var $dialog = {};

    appName.init = function(){
        // if (!Common.checkAuthen()) {
        //     Common.redirect('/login.html');
        // }
        initArray();
        $(document).on("click", '#test-start', function(e) {
            e.preventDefault();
            var dialog = $(this).attr('ref');
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
                    $dialog.modal('hide');
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
            //set flag
            starting  = true;

            $('#q-start, #q-close').hide();
            $('#q-prev, #q-next, #q-save').show();

            countDownStart('#count-down-time', 10);
            bindQuestion();
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
                $dialog.modal('hide');
            }
        });

        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            var target = $(e.target).attr("href") // activated tab
            alert(target);
        });
    };

}(jQuery, window.TestModule = window.TestModule || {}));