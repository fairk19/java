window.onload = function() {
    var loginButton = document.getElementById('login');
    var regButton = document.getElementById('registration');

    var loginFormParent = document.getElementById('formParent');
    var loginForm = loginFormParent.getElementsByClassName('loginForm')[0];
    var loginFormClose = loginForm.getElementsByClassName('close_button')[0];
    var loginFormUsername = loginForm.getElementsByClassName('username')[0]
    var loginFormPassword = loginForm.getElementsByClassName('password')[0];
    var loginFormSubmitButton = loginForm.getElementsByClassName('send')[0];

    var regFormParent = document.getElementById('regFormParent');
    var regForm = regFormParent.getElementsByClassName('regForm')[0];
    var regFormClose = regFormParent.getElementsByClassName('close_button')[0];
    var regFormUserName = regFormParent.getElementsByClassName('username')[0];
    var regFormPassword = regFormParent.getElementsByClassName('password')[0];
    var regFormSubmitButton = regFormParent.getElementsByClassName('send')[0];

    loginForm.noValidate = true;
    loginButton.addEventListener('click',loginButtonClick,false);
    loginFormClose.addEventListener('click',closeFormClick, false);
    loginFormSubmitButton.addEventListener('click',submitLoginForm, false);

    regButton.addEventListener('click', regButtonClick, false);
    regFormClose.addEventListener('click',regFormCloseClick,false );
    regFormSubmitButton.addEventListener('click',submitRegForm, false);

    function regButtonClick() {
        if(!regFormParent.className.indexOf('displayNone')>=0 ) {
            regFormParent.classList.remove('displayNone');
        }
    }
    function regFormCloseClick() {
        if(regFormParent.className.indexOf('displayNone')<=0) {
            regFormParent.className += ' displayNone';
        }
    }
    function submitRegForm() {

                            var result_id = "regAnswer";
                            var form_id = "regForm";
                            var url = "http://localhost:8083/registration";
                            jQuery.ajax({
                                url:     url,
                                type:     "POST",
                                dataType: "html",
                                data: jQuery("#"+form_id).serialize(),
                                success: function(response) {
                                location.reload(true);

                            },
                            error: function(response) {
                            document.getElementById(result_id).innerHTML = "Ошибка при отправке формы";
                            }
                         });
    }
    function closeFormClick() {
        if(loginFormParent.className.indexOf('displayNone') <=0 ) {
            loginFormParent.className += ' displayNone';
        }
    }
    function loginButtonClick() {
        if (!loginFormParent.className.indexOf('displayNone')>=0) {
            loginFormParent.classList.remove('displayNone');
        }
    }
    function submitLoginForm() {
        var regex = loginFormUsername.getAttribute('pattern');
        var formIsValid = true;
        if(loginFormUsername.value.search(regex)==-1 || !lengthIsOk(loginFormUsername, 1,30)){
            formIsValid=false;
            addErrorMessage(loginFormUsername)
        } else {
            removeErrorMessage(loginFormUsername)
        }

        if (!lengthIsOk(loginFormPassword,1,20)) {
            formIsValid = false;
            addErrorMessage(loginFormPassword);
        } else {
            removeErrorMessage(loginFormPassword);
        }

        if (!formIsValid) {
            event.preventDefault();
        }else {
                var result_id = "answer";
                var form_id = "form";
                var url = "http://localhost:8083/login";
                jQuery.ajax({
                    url:     url,
                    type:     "POST",
                    dataType: "html",
                    data: jQuery("#"+form_id).serialize(),
                    success: function(response) {
                    if(response == -1) {
                        document.getElementById(result_id).innerHTML = "Неверный пароль";
                    } else if(response == -2) {
                        document.getElementById(result_id).innerHTML = "Нет пользователя с таким именем";
                    } else if(response == -3) {
                        document.getElementById(result_id).innerHTML = "Учетная запись уже используется";
                    } else {
                        location.reload(true);
                    }

                },
                error: function(response) {
                document.getElementById(result_id).innerHTML = "Ошибка при отправке формы";
                }
             });

        }

    }

    var addErrorMessage = function(element) {
        if (element.className.indexOf('error') < 0) {
            element.className += ' error';
        }
        var errorImg = element.parentNode.getElementsByClassName('errorImg')[0];
        if (!!!errorImg) {
            var errorElem = document.createElement('span');
            errorElem.className = 'errorImg';
            errorElem.innerHTML = "<i class='icon-warning-sign'></i>";
            element.parentNode.appendChild(errorElem);
        }
    }

    var removeErrorMessage = function(element) {
        if (element.className.indexOf('error') >=0) {
            element.classList.remove('error');

        }
        var errorImg = element.parentNode.getElementsByClassName('errorImg')[0];
        if (!!errorImg) {
            element.parentNode.removeChild(errorImg);
        }
    }

    var lengthIsOk = function(element,min,max) {
        if(element.value.length <min || element.value.length >max)
            return false;
        else return true;
    }

}