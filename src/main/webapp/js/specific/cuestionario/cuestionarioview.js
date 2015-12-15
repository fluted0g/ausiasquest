
/* 
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 * AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/openAUSIAS
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */
var cuestionarioView = function () {
    var miarray;
    var validados;
    var cantidad;
};

cuestionarioView.prototype = new viewModule();
cuestionarioView.prototype.getViewTemplate_func = function (strClass, jsonDataViewModule) {
    cantidad = 0;
    var cuestionario = '<form name="myform" class="cuestionario">';
    //cuestionario += '<div class="pregresp">';
    var tituloPintado = 0;
    var dataJSON;
    var iteradorJ = 0;
    var opcion = 0;
    var newOpcion = 0;
    var posicion = 0;
    var id_assigner = 0;
    miarray = new Array(jsonDataViewModule.bean.message.length);
    for (var i = 0; i < jsonDataViewModule.bean.message.length; i++) {
        if (tituloPintado == 0) {
            dataJSON = jsonDataViewModule.bean.message[i].titulo;
            $("#broth_title").empty().append(dataJSON);
//            cuestionario += '<h3>';
//            cuestionario += dataJSON;
//            cuestionario += "</h3>";
//            cuestionario += "</div>";
            tituloPintado++;
        }

        if (iteradorJ < jsonDataViewModule.bean.message.length - 1) {
            iteradorJ = iteradorJ + 1;
        }
        
        if (jsonDataViewModule.bean.message[i].id_pregunta !== jsonDataViewModule.bean.message[iteradorJ].id_pregunta) {
            cuestionario += '<div class="pregunta">';
            dataJSON = jsonDataViewModule.bean.message[i].descripcionPregunta;
            cuestionario += dataJSON;

            cuestionario += '<ul class="opciones">';
            
            while (opcion >= 0) {

                cuestionario += '<li><label class="label_radio r_off" for="id' + id_assigner + '"><input type="radio" class="input_radio" name="group' + i + '" id="id' + id_assigner + '" value="' + jsonDataViewModule.bean.message[newOpcion].id_opcion + '">';
                dataJSON = jsonDataViewModule.bean.message[newOpcion].descripcionOpcion;
                cuestionario += dataJSON;

                cuestionario += "</input></label></li>";
                id_assigner++;
                newOpcion++;
                opcion--;
            }
            cuestionario += "</ul>";
            cuestionario += "</div>";
            miarray[posicion] = "group" + i;
            posicion++;
            opcion = 0;
        } else if (i === jsonDataViewModule.bean.message.length - 1) {
            cuestionario += '<div class="pregunta">';
            dataJSON = jsonDataViewModule.bean.message[i].descripcionPregunta;
            cuestionario += dataJSON;

            cuestionario += '<ul class="opciones">';
            while (opcion >= 0) {

                cuestionario += '<li class="li_radio"><label class="label_radio r_off" for="group' + id_assigner + '"><input type="radio" name="group' + i + '" id="group' + id_assigner + '" class="input_radio" value="' + jsonDataViewModule.bean.message[newOpcion].id_opcion + '">';
                dataJSON = jsonDataViewModule.bean.message[newOpcion].descripcionOpcion;
                cuestionario += dataJSON;

                cuestionario += "</input></label></li>";

                id_assigner++;
                newOpcion++;
                opcion--;
            }
            cuestionario += "</div>";
            miarray[posicion] = "group" + i;
            posicion++;
        } else {
            opcion++;
        }
        cantidad++;
    }
    
    cuestionario += '<div class="g-recaptcha" data-callback="captcha_filled" data-expired-callback="captcha_expired" data-sitekey="6LdbtxITAAAAADq4bJ_0U9VZGiBCPm2sdOgZ-7Su"></div>';
 
    cuestionario += '<input type="button" value="Enviar" id="enviar_click" class="btn btn-default">';
    cuestionario += '</form>';
    
    cuestionario +='<script src="https://www.google.com/recaptcha/api.js"></script>';
    cuestionario +="<script>";
    cuestionario +="$('.label_radio').click(function(){setupLabel();});";
    cuestionario +="</script>";

    return cuestionario;
};

var captcha = false;

function captcha_filled() {
    captcha = true;
}

function captcha_expired() {
    captcha = false;
}

cuestionarioView.prototype.bind = function () {
    that = this;
    $("#enviar_click").click(function () {
        var resultado = "";
        var clickeado = "";
        var posicion = 0;
        validados = new Array();
        for (var i = 0; i < miarray.length; i++) {
            clickeado = document.getElementsByName(miarray[i]);

            for (var j = 0; j < clickeado.length; j++)
            {
                if (clickeado[j].checked) {
                    validados[posicion] = clickeado[j].value;
                    posicion++;
                }
            }
        }
        
        strValues = validados;
        if(captcha) {
        that.getPromesa({json: JSON.stringify(strValues)}).done(function (result) {

            if (result["status"] == "200") {
                resultadoMessage = 'Se han guardado las respuestas';               
                window.location = "http://localhost:8081/openAUSIAS/#/cuestionario/list";

                $(window).scrollTop(0);
            } else {
                resultadoMessage = "ERROR: No se ha creado el registro";
                alert(resultadoMessage);
            }
        });
    } else {
        alert("Rellena el captcha.");
    }
    });
};

cuestionarioView.prototype.getPromesa = function (jsonfile) {
    return this.ajax_call(configuration.getAppUrl() + '?ob=respuesta&op=procesacuestionario', 'GET', jsonfile);
};


//captcha para SERVER
cuestionarioView.prototype.sendCaptcha = function () {
    // enviar a "https://www.google.com/recaptcha/api/siteverify":
    // method: POST
    // secret: 6LdbtxITAAAAAMbRozjcg_5ojpYFGAcmmziMKpv3
    // response: g-recaptcha-response
    // remoteIp: user's ip adress
    //return this.ajax_call("https://www.google.com/recaptcha/api/siteverify", 'POST', g-recaptcha-response)
}


