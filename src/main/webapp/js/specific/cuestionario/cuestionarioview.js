
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
    var cuestionario = '<form name="myform">';
    cuestionario += '<div class="pregresp">';
    var tituloPintado = 0;
    var dataJSON;
    var iteradorJ = 0;
    var opcion = 0;
    var newOpcion = 0;
    var posicion = 0;
    miarray = new Array(jsonDataViewModule.bean.message.length);
    for (var i = 0; i < jsonDataViewModule.bean.message.length; i++) {
        if (tituloPintado == 0) {
            dataJSON = jsonDataViewModule.bean.message[i].titulo;
            cuestionario += '<h3>';
            cuestionario += dataJSON;
            cuestionario += "</h3>";
            cuestionario += "</div>";
            tituloPintado++;
        }

        if (iteradorJ < jsonDataViewModule.bean.message.length - 1) {
            iteradorJ = iteradorJ + 1;
        }

        if (jsonDataViewModule.bean.message[i].id_pregunta !== jsonDataViewModule.bean.message[iteradorJ].id_pregunta) {
            cuestionario += '<div class="pregunta">';
            dataJSON = jsonDataViewModule.bean.message[i].descripcionPregunta;
            cuestionario += dataJSON;
            cuestionario += "</div>";
            cuestionario += '<ul class="opciones">';
            while (opcion >= 0) {

                cuestionario += '<li><input type="radio" name="group' + i + '" value="' + jsonDataViewModule.bean.message[newOpcion].id_opcion + '">';
                dataJSON = jsonDataViewModule.bean.message[newOpcion].descripcionOpcion;
                cuestionario += dataJSON;

                cuestionario += "</input></li>";

                newOpcion++;
                opcion--;
            }
            cuestionario += "</ul>";
            miarray[posicion] = "group" + i;
            posicion++;
            opcion = 0;
        } else if (i === jsonDataViewModule.bean.message.length - 1) {
            cuestionario += '<div class="pregunta">';
            dataJSON = jsonDataViewModule.bean.message[i].descripcionPregunta;
            cuestionario += dataJSON;
            cuestionario += "</div>";
            cuestionario += '<ul class="opciones">';
            while (opcion >= 0) {

                cuestionario += '<li><input type="radio" name="group' + i + '" value="' + jsonDataViewModule.bean.message[newOpcion].id_opcion + '">';
                dataJSON = jsonDataViewModule.bean.message[newOpcion].descripcionOpcion;
                cuestionario += dataJSON;

                cuestionario += "</input></li>";

                newOpcion++;
                opcion--;
            }
            miarray[posicion] = "group" + i;
            posicion++;
        } else {
            opcion++;
        }
        cantidad++;
    }

    cuestionario += '</form>';
    cuestionario += '</br>';
    cuestionario += '</br>';
    cuestionario += '<input type="button" value="Enviar" id="enviarClick">';

    return cuestionario;
};


cuestionarioView.prototype.bind = function () {
    that=this;
    $("#enviarClick").click(function () {
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
        that.getPromesa({json: JSON.stringify(strValues)}).done(function (result) {

            if (result["status"] == "200") {
                resultadoMessage = 'Se han guardado las respuestas';
            } else {
                resultadoMessage = "ERROR: No se ha creado el registro";
            }
        });
    });
};

cuestionarioView.prototype.getPromesa = function (jsonfile) {

    return ajax.call(config.getAppUrl() + '?ob=respuesta&op=procesacuestionario', 'GET', jsonfile);
};


