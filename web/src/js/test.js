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
            answers : []
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
            answers : []
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

            html += '<div class="form-check">';
            html += '<input class="form-check-input" type="radio" value="' + answer.value + '" name="'+ item.name +'" id="'+ id +'">';
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
            
            html += '<div class="form-check">';
            html += '<input class="form-check-input" type="checkbox" value="' + answer.value + '" name="'+ item.name +'" id="'+ id +'">';
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

        if (!item.name){
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

        console.log('=================answers',answerItem)
        return answerItem;
    }

    appName.init = function(){
        initArray();
        $(document).on("click", '#test-start', function() {
            var dialog = $(this).attr('ref')
            $(dialog).modal('show');
            bindQuestion();
         });
         
        $('#test-start-dialog').on("click", "#q-prev", function() {
            saveItem();
            currentIndex = currentIndex - 1;
            bindQuestion();
        });
        $('#test-start-dialog').on("click", "#q-next", function() {
            if (saveItem()) {
                currentIndex = currentIndex + 1;
                bindQuestion();
            }
        });
        $('#test-start-dialog').on("click", "#q-save", function() {
            console.log(answers);
        });
    };

}(jQuery, window.TestModule = window.TestModule || {}));