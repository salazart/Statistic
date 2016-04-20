(function ($) {
    var countRows = [2, 2, 2, 2, 2, 2, 2, 2, 2, 2];
    var controlCheckbox = 0;

    $(function () {

        $('.rf').each(function () {

            var form = $(this),
                btn = form.find('.btn_submit');

            // Функция проверки полей Input формы
            function checkInput() {
                form.find('.rfield').each(function () {
                    if ($(this).val() != '') {
                        $(this).removeClass('empty_field');
                    } else {
                        $(this).addClass('empty_field');
                    }
                });
            }

            // Функция проверки полей CheckBox формы


            function checkCheckBox() {
                for (var i = 0; i < 10; i++) {
                    $("input:checkbox[id=operator_" + i + "]").each(function () {
                        if ($(this).is(":checked") == true) {
                            $(this).removeClass('empty_field_checkbox');
                            countRows[i] = 1;
                            return false;
                        }
                        if ($(this).is(":checked") == false) {
                            $(this).addClass('empty_field_checkbox');
                            countRows[i] = 0;
                        }
                    });

                    if (countRows[i] == 0) {
                        form.find('.tdRow_' + i).addClass('emptyCheckbox');
                    };
                    if (countRows[i] == 1) {
                        form.find('.tdRow_' + i).removeClass('emptyCheckbox');
                    };
                };



                for (var i = 0; i < 10; i++) {
                    if (countRows[i] == 0) {
                        controlCheckbox = 1;
                        break;
                    } else {
                        controlCheckbox = 0;
                    }
                }
            }

            // Функция подсветки незаполненных полей
            function lightEmpty() {
                form.find('.empty_field').css({
                    'border-color': '#d8512d'
                });
                for (var i = 0; i < 10; i++) {
                    form.find('.emptyCheckbox').css({
                        'background-color': '#d8512d'
                    });
                };

                setTimeout(function () {
                    form.find('.empty_field').removeAttr('style');
                    form.find('.emptyCheckbox').removeAttr('style');
                }, 500);
            }

            setInterval(function () {
                checkInput();
                checkCheckBox();
                var sizeEmpty = form.find('.empty_field').size();
                if (sizeEmpty > 0) {
                    if (btn.hasClass('disabled')) {
                        return false
                    } else {
                        btn.addClass('disabled')
                    }
                } else {
                    btn.removeClass('disabled')
                }
            }, 500);

            btn.click(function () {
                lightEmpty();
                for (var i = 0; i < 10; i++) {
                    if (countRows[i] == 0) {
                        return false
                    }
                }
                if ($(this).hasClass('disabled')) {
                    return false
                } else {
                    form.submit();
                }
            });

        });

    });

})(jQuery);