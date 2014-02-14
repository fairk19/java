//$(document).ready( function() {
            var form = document.getElementById('form');
            var sendButton = form.getElementsByClassName('submit')[0];

//            var result = document.getElementById(result_id);
            sendButton.addEventListener('click',AjaxFormRequest,false);

            function AjaxFormRequest() {
                            var result_id = "answer";
                            var form_id = "form";
                            var url = "http://localhost:8083/loginization/id";
                            jQuery.ajax({
                                url:     url, //Адрес подгружаемой страницы
                                type:     "POST", //Тип запроса
                                dataType: "html", //Тип данных
                                data: jQuery("#"+form_id).serialize(),
                                success: function(response) { //Если все нормально
                                document.getElementById(result_id).innerHTML = response;
                            },
                            error: function(response) { //Если ошибка
                            document.getElementById(result_id).innerHTML = "Ошибка при отправке формы";
                            }
                         });
            }

//})

//            function AjaxFormRequest(result_id,form_id,url) {
//                            jQuery.ajax({
//                                url:     url, //Адрес подгружаемой страницы
//                                type:     "POST", //Тип запроса
//                                dataType: "html", //Тип данных
//                                data: jQuery("#"+form_id).serialize(),
//                                success: function(response) { //Если все нормально
//                                document.getElementById(result_id).innerHTML = response;
//                            },
//                            error: function(response) { //Если ошибка
//                            document.getElementById(result_id).innerHTML = "Ошибка при отправке формы";
//                            }
//                         });
//            }



















//window.onload = function() {
//$(document).ready( function() {
//     var form = document.getElementById('form');
//     var submit = form.getElementsByClassName('submit')[0];
//
//     submit.addEventListener('click', submitClick, false);
//         function submitClick(){
//
//             alert("sending");
//             var ans = $.ajax({
//                               type: "POST",
//                               url: "/loginization/id",
//                               data: {login: 'user1', password: 'user1'},
//                             });
//             ans
//         }
//})
//

//}

//    function submitClick(){
//
//        alert("sending");
//        $.ajax({
//          type: "POST",
//          url: "/loginization/id",
//          data: {login: 'user1', password: 'user1'},
//          success: function(resp){
//            document.getElementById('answer').innerHTML = resp;
//            alert(resp);
//          }
//        });
//    }


//    function submitClick(){
//        xhttp = new XMLHttpRequest();
//        xhttp.onreadystatechange = function() {
//            if (xhttp.readyState == 4 && xhttp.Status == 200) {
//                alert(xhttp.responseText);
//            }
//        }
//        message_to_send = "login=\'user1\'& password=\'user1\'";
//        alert(message_to_send);
//        xhttp.open('post', 'http://localhost:8083/loginization/id',true);
//        xhttp.send(message_to_send);
//    }