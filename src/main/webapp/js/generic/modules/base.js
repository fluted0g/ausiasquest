/*
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 *
 * openAUSIAS: The stunning micro-library that helps you to develop easily
 *             AJAX web applications by using Java and jQuery
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
 */
var baseModule = function () {
};
baseModule.prototype.ajax_call = function (url, type, data) {
    return $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        method: type,
        url: url,
        data: data,
        timeout: 30000
    });
};
baseModule.prototype.promise_getOne = function (strClass, id) {
    return this.ajax_call(configuration.getAppUrl() + '?ob=' + strClass + '&op=getaggregateviewone&id=' + id, 'GET', '');
};
baseModule.prototype.promise_getMeta = function (strClass) {
    return this.ajax_call(configuration.getAppUrl() + '?ob=' + strClass + '&op=getmetainformation', 'GET', '');
};
baseModule.prototype.promise_getSome = function (strClass, rpp, page, filterParams, orderParams, systemfilterParams) {
    return this.ajax_call(configuration.getAppUrl() + '?ob=' + strClass + '&op=getaggregateviewsome' + '&rpp=' + rpp + '&page=' + page + filterParams + orderParams + systemfilterParams, 'GET', '');
};
baseModule.prototype.promise_getAll = function (strClass, filterParams, orderParams, systemfilterParams) {
    return this.ajax_call(configuration.getAppUrl() + '?ob=' + strClass + '&op=getaggregateviewall' + filterParams + orderParams + systemfilterParams, 'GET', '');
};
baseModule.prototype.promise_removeOne = function (strClass, id) {
    return this.ajax_call(configuration.getAppUrl() + '?ob=' + strClass + '&op=remove&id=' + id, 'GET', '');
};
baseModule.prototype.promise_getPromise = function (strClass, operation, params) {
    return this.ajax_call(configuration.getAppUrl() + '?ob=' + strClass + '&op=' + operation + params, 'GET', '');
};
baseModule.prototype.promise_setOne = function (strClass, jsonfile) {
    return this.ajax_call(configuration.getAppUrl() + '?ob=' + strClass + '&op=set', 'GET', jsonfile);
};






baseModule.prototype.html_escapeHtml = function (str) {
    return str.replace(/[&<>]/g, function (tag) {
        var tagsToReplace = {
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            '"': '&quot;',
            "'": '&#039;'
        }
        return tagsToReplace[tag] || tag;
    });
};
baseModule.prototype.html_escapeHtml2 = function (str) {
    if (typeof (str) === 'string') {
        return strclipString
                .replace(/&/g, "&amp;")
                .replace(/</g, "&lt;")
                .replace(/>/g, "&gt;")
                .replace(/"/g, "&quot;")
                .replace(/'/g, "&#039;");
    } else {
        return str;
    }
};
baseModule.prototype.html_htmlEncode = function (value) { //creates a ghost div for jQuery to normalize the html content
    return $('<div/>').text(value).html();
};
baseModule.prototype.html_htmlDecode = function (value) {
    return $('<div/>').html(value).text();
};
baseModule.prototype.html_print = function (value) {
    return this.string_clipString(this.html_escapeHtml(decodeURIComponent(value)));
};
baseModule.prototype.html_getId = function (value) {
    return _.filter(value, function (oItem) {
        return oItem.meta.IsId;
    })[0].data;
};
baseModule.prototype.html_printObject = function (value) {
    var arr_metadata = _.map(value.data.meta, function (oMeta) {
        if (oMeta.IsForeignKeyDescriptor) {
            return  value.data.bean[oMeta.Name];
        } else {
            return "";
        }
    });
    var strForeign = arr_metadata.join(' ');
    return '<a href="#/' + value.meta.ReferencesTable + '/view/' + value.data.bean.id + '">' + value.data.bean.id + "= " + this.html_print(strForeign) + '</a>';
};
baseModule.prototype.html_printObject2 = function (strClass, metaValue, beanValue) {
    var arr_metadata = _.map(metaValue, function (oMeta) {
        if (oMeta.IsForeignKeyDescriptor) {
            return  oMeta.Name + ': ' + beanValue[oMeta.Name] + ';';
        } else {
            return "";
        }
    });
    var strForeign = _.without(arr_metadata, '').join(' ');
    return '<a href="#/' + strClass + '/view/' + beanValue.id + '"><strong>' + beanValue.id + "</strong> (" + this.html_print(strForeign) + ')</a>';
};
baseModule.prototype.html_printValue = function (value) {
    switch (value.meta.Type) {
        case 'Boolean':
            if (value.data == true) {
                return this.html_getIcon('glyphicon-ok');
            } else {
                return this.html_getIcon('glyphicon-remove');
            }
            break;
        default:
            return this.html_print(value.data);
    }
};
baseModule.prototype.html_printPrincipal = function (value) {
    if (value.meta) {
        if (value.meta.IsObjForeignKey) {
            return  this.html_printObject2(value.meta.ReferencesTable, value.data.meta, value.data.bean);
        } else {
            return this.html_printValue(value);
        }
    } else {
        return this.html_print(value);
    }
};
baseModule.prototype.html_getIcon = function (strIcon) {
    return dom.i('class="glyphicon ' + strIcon + '"');
};
baseModule.prototype.html_dom = function (strLabel, strText, strAttr) {
    if (!strAttr)
        strAttr = '';
    else
        strAttr = ' ' + strAttr;
    return '<' + strLabel + strAttr + '>' + strText + '</' + strLabel + '>\n';
};
baseModule.prototype.html_dom2 = function (strLabel, strAttr, strText) {
    if (!strAttr)
        strAttr = '';
    else
        strAttr = ' ' + strAttr;
    if (!strText)
        return '<' + strLabel + strAttr + ' />';
    else
        return '<' + strLabel + strAttr + '>' + strText + '</' + strLabel + '>';

};
baseModule.prototype.html_getPageHeader = function (icon, title, subtitle) {
    return(
            dom.div('class="row"',
                    dom.div('class="col-xs-9 text-left"',
                            dom.h1('id="broth_title"', title) +
                            dom.h1('', dom.small('id="broth_subtitle"', subtitle))
                            ) +
                    dom.div('class="col-xs-3 text-right" id="broth_operationicon"', icon)
                    )
            );
};
baseModule.prototype.html_getSpinner = function () {
    return dom.img('src="images/ajax-loading.gif" alt="cargando..."', '');
};
baseModule.prototype.html_getPanel = function (id, title, content) {
    return (
            '<div class="panel panel-default">\n\
                            <div class="panel-heading">' + title + '</div>\n\
                            <div class="panel-body">\n\
                                <div class="text-center">\n\
                                    <div id="' + id + '">\n\
                            ' + content + '\n\
                                    </div>\n\
                                </div>\n\
                            </div>\n\
                        </div>'
            );
};
baseModule.prototype.html_linkBack = function () {
    history.back();
    return false;
};
baseModule.prototype.html_createGhostDiv = function (id, data) {
    var divContainer = $('<div>').attr({
        id: id
    });
    $('body').append(divContainer);
    $('#' + id).append(data);
};
baseModule.prototype.html_creoleParse = function (content, lugar) {
    var div = document.createElement('div');
    div.innerHTML = "";
    creole.parse(div, content);
    lugar.empty().html(div);
    var codigo = lugar.html();
    codigo = codigo.replace("<table>", '<table class="table table-bordered"><tbody>');
    codigo = codigo.replace("</table>", '</tbody></table>');
    lugar.empty().append(codigo);
};










baseModule.prototype.array_getArrayFromMultiSlicedArray = function (field, arrayToBeSliced) {
    var resultArray = [];
    $.each(arrayToBeSliced, function (index, value) {

        resultArray.push(value[field]);
    })
    return resultArray;
};
baseModule.prototype.array_filterArray = function (strSearchTerm, arrayData) {
    var resultArray = [];
    $.each(arrayData, function (index1, value1) {
        thisValue1 = value1;
        $.each(value1, function (index2, value2) {

            if ((new RegExp(strSearchTerm)).test(value2)) {
                resultArray.push(thisValue1);
                return false;
            }
        })
    })
    return resultArray;
};
baseModule.prototype.array_getIntegerArray = function (min, max) {
    var iArray = [];
    for (var counter = min; counter <= max; counter++) {
        iArray.push(counter);
    }
    return iArray;
};
baseModule.prototype.array_identificarArray = function (arr) {
    var newObj = {};
    for (var property in arr) {
        if (arr.hasOwnProperty(property)) {
            if (property.match("^obj_")) {
                newObj[this.string_identificar(property)] = arr[property];
            } else {
                newObj[property] = arr[property];
            }
        }
    }
    return newObj;
}





baseModule.prototype.button_getTableHeaderButtons = function (strId, strOb, strOp, UrlFromParamsWithoutOrder) { //meta.Name es strId
    return(
            dom.a('class="orderAsc" id="' + strId + '" href="#/' + strOb + '/' + strOp + '/' + UrlFromParamsWithoutOrder + '&order=' + strId + '&ordervalue=asc"',
                    this.html_getIcon('glyphicon-arrow-up')
                    ) +
            dom.a('class="orderDesc" id="' + strId + '" href="#/' + strOb + '/' + strOp + '/' + UrlFromParamsWithoutOrder + '&order=' + strId + '&ordervalue=desc"',
                    this.html_getIcon('glyphicon-arrow-down')
                    )
            );
}
baseModule.prototype.button_getTableToobarButton = function (strClass, strOperation, strId, strIcon) {
    return dom.a('class="btn btn-default ' + strOperation + '" id="' + strId + '" href="#/' + strClass + '/' + strOperation + '/' + strId + '"', this.html_getIcon(strIcon))
}
baseModule.prototype.button_getToolbarBar = function (strContent) {
    return dom.div('class="btn-toolbar" role="toolbar"', dom.div('class="btn-group btn-group-xs"', strContent));
}






baseModule.prototype.form_form = function (id, content) {
    return (
            dom.form('class="form-horizontal" role="form" action="#" id="' + id + '" name="formulario"',
                    dom.div('id="fields' + id + '"', content) +
                    dom.div('class="form-group"',
                            dom.div('class="col-sm-offset-2 col-sm-10"',
                                    dom.div('id="messages"', '')
                                    )
                            ) +
                    dom.div('class="form-group"',
                            dom.div('class="col-sm-offset-2 col-sm-10"',
                                    dom.button('class="btn btn-primary" id="submitForm"', 'Guardar')
                                    )
                            )

                    )
            );
}
baseModule.prototype.form_formInputTypeDate = function (fieldName, fieldShortName, fieldUltraShortName, controlWidth) {
    return(
            dom.div('id="' + fieldName + '-group" class="form-group"',
                    dom.label('class="col-sm-2 control-label" for="' + fieldName + '"', fieldShortName + ':') +
                    dom.div('class="control col-sm-' + controlWidth + '"',
                            dom.div('class="input-group date" id="' + fieldName + '_group"',
                                    dom.span('class="input-group-addon"',
                                            dom.span('class="glyphicon glyphicon-calendar"', '')
                                            ) +
                                    dom.input('type="text" class="form-control" id="' + fieldName + '" name="' + fieldName + '" placeholder="' + fieldUltraShortName + '"', '')
                                    )
                            )
                    ) +
            '<script type="text/javascript">$("#' + fieldName + '_group").datetimepicker({format: "DD/MM/YYYY",locale: "es"});</script>'
            );
}
baseModule.prototype.form_formForeign = function (fieldName, fieldShortName) {
    return(
            dom.div('class="form-group"',
                    dom.label('class="col-sm-2 control-label" for="' + fieldName + '"', fieldShortName + ':') +
                    dom.div('class="control col-sm-3"',
                            dom.div('class="input-group foreign" id="' + fieldName + '_group"',
                                    dom.span('class="input-group-addon" id="' + fieldName + '_button"',
                                            dom.span('class="glyphicon glyphicon-search"', '')
                                            ) +
                                    dom.input('readonly="true" class="form-control" id="' + fieldName + '" class="input-mini" name="' + fieldName + '" type="text" size="5" maxlength="5"', '')
                                    )
                            ) +
                    dom.label('class="col-sm-7" for="' + fieldName + '_desc" id="' + fieldName + '_desc"', '')
                    )
            );
}
baseModule.prototype.form_formCheckBox = function (fieldName, fieldShortName) {
    return(
            dom.div('id="' + fieldName + '-group" class="form-group"',
                    dom.label('class="col-sm-2 control-label"  for="' + fieldName + '"', fieldShortName + ":") +
                    dom.div('class="control col-sm-1"',
                            dom.input('type="checkbox" id="' + fieldName + '" name="' + fieldName + '" value="true"', '')
                            )
                    )
            );
}
baseModule.prototype.form_formInputTypeInteger = function (fieldName, fieldShortName, controlWidth) {
    return(
            dom.div('id="' + fieldName + '-group" class="form-group  has-success has-feedback"',
                    dom.label('class="col-sm-2 control-label"  for="' + fieldName + '"', fieldShortName + ":") +
                    dom.div('class="control col-sm-' + controlWidth + '"',
                            dom.input('type="text" id="' + fieldName + '" class="form-control" name="' + fieldName + '" size="15" placeholder="' + fieldName + '"', '')
                            )
                    )
            );
}
baseModule.prototype.form_formInputTypeText = function (fieldName, fieldShortName, controlWidth) {
    return(
            dom.div('id="' + fieldName + '-group" class="form-group has-feedback"',
                    dom.label('class="col-sm-2 control-label"  for="' + fieldName + '"', fieldShortName + ":") +
                    dom.div('class="control col-sm-' + controlWidth + '"',
                            dom.input('type="text" id="' + fieldName + '" class="form-control" name="' + fieldName + '" size="15" placeholder="' + fieldName + '"', '')
                            )
                    )
            );
}
baseModule.prototype.form_getFormTemplate = function (strClass, jsonMeta) {
    var that = this;
    var matrix_form = _.map(jsonMeta, function (value, index) {
        if (value.IsIdForeignKey == false && value.IsObjForeignKey == false) {
            if (value.Type == "String") {
                return that.form_formInputTypeText(value.Name, value.ShortName, that.form_getInputTypeTextLenght(value.MaxLength));
            }
            if (value.Type == "Boolean") {
                return that.form_formCheckBox(value.Name, value.ShortName);
            }
            if (value.Type == "Integer") {
                return that.form_formInputTypeInteger(value.Name, value.ShortName, that.form_getInputTypeTextLenght(value.MaxInteger.toString().length));
            }
            if (value.Type == "Date") {
                return that.form_formInputTypeDate(value.Name, value.ShortName, value.ShortName, that.form_getInputTypeTextLenght(10));
            }
        } else {
            if (value.IsObjForeignKey) {
                return that.form_formForeign(value.Name, value.ShortName);
            }
        }
    });
    var string_form = _.reduce(matrix_form, function (memo, control) {
        return memo + control;
    });
    return that.form_form(strClass + 'Form', string_form);
}
baseModule.prototype.form_getFormValues = function (strClass) {
    var valores = [];
    var disabled = $('#' + strClass + 'Form').find(':input:disabled').removeAttr('disabled');
    valores = $('#' + strClass + 'Form').serializeObject();
    disabled.attr('disabled', 'disabled');
    return valores;
}
baseModule.prototype.form_populateSelectBox = function (place, values, captions) {
    var combo = $(place);
    if (combo.is("select")) {
        $.each(values, function (index) {
            if (typeof captions === "undefined")
                combo.append($("<option />").val(this).text(this));
            else
                combo.append($("<option />").val(this).text(captions[index]));
        });
    }
}
baseModule.prototype.form_getInputTypeTextLenght = function (intMaxLenght) {
    if (intMaxLenght <= 255) {
        if (intMaxLenght <= 200) {
            if (intMaxLenght <= 150) {
                if (intMaxLenght <= 100) {
                    if (intMaxLenght <= 50) {
                        if (intMaxLenght <= 25) {
                            return 3;
                        } else {
                            return 4;
                        }
                        ;
                    } else {
                        return 5;
                    }
                    ;
                } else {
                    return 6;
                }
                ;
            } else {
                return 7;
            }
            ;
        } else {
            return 8;
        }
        ;
    } else {
        return 9;
    }
    ;
}
baseModule.prototype.form_getForeign = function (obMain, obForeign) {
    var that = this;
    $('#' + obMain + 'Form #obj_' + obForeign + '_button').unbind('click');
    $('#' + obMain + 'Form #obj_' + obForeign + '_button').click(function () {
        $('#' + obMain + 'Form').append(that.modal_getEmptyModal('modal01'));
        that.modal_loadModal('#modal01', that.modal_getModalHeader('Elección de ' + obForeign), "", that.modal_getModalFooter(), true);
        ausiasFLOW.initialize(ebpListModule, $('#modal-body'), obForeign, 'plist', {"vf": 4}, function (id) {
            $('#obj_' + obForeign).val(id);
            that.promise_getOne(obForeign, id).done(function (json) {
                $('#obj_' + obForeign + '_desc').html(that.html_printObject2(obForeign, json.message.meta.message, json.message.bean.message));
            });
            $('#modal01').modal('hide');
        });
        return false;
    });

}








baseModule.prototype.modal_getEmptyModal = function (name) {
    var modal = '<div id="' + name + '" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">';
    modal += '<div class="modal-dialog modal-lg">';
    modal += '<div class="modal-content">';
    modal += '<div class="modal-header" id="modal-header"></div>';
    modal += '<div class="modal-body" id="modal-body"></div>';
    modal += '<div class="modal-footer" id="modal-footer"></div>';
    modal += '</div>';
    modal += '</div>';
    modal += '</div>';
    return modal;
};
baseModule.prototype.modal_load = function (modalName, keyb) {
    $(modalName).modal({
        keyboard: keyb
    })
};
baseModule.prototype.modal_loadModal = function (modalName, headerData, bodyData, footerData, keyb) {
    $(modalName + ' .modal-header').empty().append(headerData);
    $(modalName + ' .modal-body').empty().append(bodyData);
    $(modalName + ' .modal-footer').empty().append(footerData);
    $(modalName).modal({
        keyboard: keyb
    })
};
baseModule.prototype.modal_getModalHeader = function (title) {
    cabecera = '<button id="full-width" type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>';
    cabecera += '<h1 id="myModalLabel">' + title + '</h1>';
    return cabecera;
};
baseModule.prototype.modal_getModalFooter = function () {
    return pie = '<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">Cerrar</button>';
};
baseModule.prototype.modal_loadModalNotify = function (place, message, afterNotifyFunction) {
    $(place).append(this.modal_getEmptyModal('modal01'));
    this.modal_loadModal('#modal01', this.modal_getModalHeader('Respuesta del servidor'), message, this.modal_getModalFooter(), true);
    $('#modal01').css({'right': '20px', 'left': '20px', 'width': 'auto', 'margin': '10px', 'display': 'block'});
    $('#modal01').on('hidden.bs.modal', afterNotifyFunction);
};












baseModule.prototype.pagination_getPageLinks = function (url, page_number, total_pages, neighborhood) {
    vector = "<ul class=\"pagination\">";
    if (page_number > 1)
        vector += ('<li><a class="pagination_link" id="' + (page_number - 1) + '" href="' + url + '&page=' + (page_number - 1) + '">prev</a></li>');
    if (page_number > neighborhood + 1)
        vector += ('<li><a class="pagination_link" id="1" href="' + url + '&page=1">1</a></li>');
    if (page_number > neighborhood + 2)
        vector += ('<li>' + '<a href="#">...</a>' + '</li>');
    for (i = (page_number - neighborhood); i <= (page_number + neighborhood); i++) {
        if (i >= 1 && i <= total_pages) {
            if (page_number === i) {
                vector += ('<li class="active"><a class="pagination_link" id="' + i + '" href="' + url + '&page=' + i + '">' + i + '</a></li>');
            } else
                vector += ('<li><a class="pagination_link" id="' + i + '" href="' + url + '&page=' + i + '">' + i + '</a></li>');
        }
    }
    if (page_number < total_pages - (neighborhood + 1))
        vector += ('<li>' + '<a href="#">...</a>' + '</li>');
    if (page_number < total_pages - (neighborhood))
        vector += ('<li><a class="pagination_link" id="' + total_pages + '" href="' + url + '&page=' + total_pages + '">' + total_pages + '</a></li>');
    if (page_number < total_pages)
        vector += ('<li><a class="pagination_link"  id="' + (page_number + 1) + '" href="' + url + '&page=' + (page_number + 1) + '">next</a></li>');
    vector += "</ul>";
    return vector;
};
baseModule.prototype.pagination_getRppLinks = function (UrlFromParamsWithoutRpp, rpp) {
    var botonera = '<ul class="pagination">';
    if (rpp == 10)
        botonera += '<li class="active">';
    else
        botonera += '<li>';
    botonera += '<a class="rpp_link" id="10" href="' + UrlFromParamsWithoutRpp + '&rpp=10">10</a></li>';
    if (rpp == 50)
        botonera += '<li class="active">';
    else
        botonera += '<li>';
    botonera += '<a class="rpp_link" id="50" href="' + UrlFromParamsWithoutRpp + '&rpp=50">50</a></li>';
    if (rpp == 100)
        botonera += '<li class="active">';
    else
        botonera += '<li>';
    botonera += '<a class="rpp_link" id="100" href="' + UrlFromParamsWithoutRpp + '&rpp=100">100</a></li>';
    botonera += '</ul>';
    return botonera;
};















baseModule.prototype.parameter_getUrlObjectFromUrlString = function (url) {
    var a;
    if (typeof url == 'undefined') {
        return {};
    } else {
        if (url == "") {
            return {};
        } else {
            a = url.split('&');
        }
    }
    var b = {};
    for (var i = 0; i < a.length; ++i)
    {
        var p = a[i].split('=');
        if (p.length != 2)
            p = ['id', p[0]]; //id parameter by default
        b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
    }
    return b;
};
baseModule.prototype.parameter_getStrSystemFilters = function (objFields) {
    var strResult = "";
    if (objFields['systemfilter'] != "") {
        strResult += objFields['systemfilter'];
        strResult += "=";
        strResult += objFields['systemfiltervalue'];
    }
    return strResult;
};
baseModule.prototype.parameter_validateUrlObjectParameters = function (objParams) {
    //security borders comprobation, pendent of moving
    if (objParams["vf"] > 20) {
        objParams["vf"] = 20;
    }
    if (objParams["page"] > 100000) {
        objParams["page"] = 100000;
    }
    if (objParams["rpp"] > 100) {
        objParams["rpp"] = 100;
    }
    return objParams;
};
baseModule.prototype.parameter_defaultizeUrlObjectParametersForLists = function (objParams) {
    if (typeof objParams["vf"] == 'undefined')
        objParams["vf"] = 99;
    return objParams;
};
baseModule.prototype.parameter_defaultizeUrlObjectParameters = function (objParams) {
    if (typeof objParams["id"] == 'undefined')
        objParams["id"] = 1;
    return objParams;
};
baseModule.prototype.parameter_getUrlObjectFromParamsWithoutParamArray = function (urlObj, nameParameterArray) {
    var newUrlObj = jQuery.extend(true, {}, urlObj); //http://stackoverflow.com/questions/122102/what-is-the-most-efficient-way-to-clone-an-object
    $.each(nameParameterArray, function () {
        delete newUrlObj[this];
    })
    return newUrlObj;
};
baseModule.prototype.parameter_getUrlStringFromParamsObject = function (urlObj) {
    var result = "";
    for (var key in urlObj) {
        result += "&" + key + "=" + urlObj[key];
    }
    return result.substring(1);
};
baseModule.prototype.parameter_printOrderParamsInUrl = function (objParams) {
    if (objParams)
        if (objParams.order) {
            return '&order=' + objParams.order + '&ordervalue=' + objParams.ordervalue;
        } else {
            return '';
        }
};
baseModule.prototype.parameter_printFilterParamsInUrl = function (objParams) {
    if (objParams)
        if (objParams.filter) {
            return "&filter=" + objParams.filter + "&filteroperator=" + objParams.filteroperator + "&filtervalue=" + objParams.filtervalue;
        } else {
            return '';
        }
};
baseModule.prototype.parameter_printSystemFilterParamsInUrl = function (objParams) {
    if (objParams)
        if (objParams.systemfilter) {
            return "&systemfilter=" + objParams.systemfilter + "&systemfilteroperator=" + objParams.systemfilteroperator + "&systemfiltervalue=" + objParams.systemfiltervalue;
        } else {
            return '';
        }
};




baseModule.prototype.string_identificar = function (value) {
    return value.replace("obj_", "id_");
};
baseModule.prototype.string_defaultizeValue = function (strValue, defaultValue) {
    if (typeof strValue !== 'undefined') {
        return strValue;
    } else {
        return defaultValue;
    }
};
baseModule.prototype.string_clipString = function (strResult, charsToClipStart) {
    charsToClipStart = this.string_defaultizeValue(charsToClipStart, 40);
    if (typeof strResult === 'string') {
        if (strResult.length > charsToClipStart)
            return strResult.substr(0, charsToClipStart).trim() + " ...";
        else
            return strResult.trim();
    } else {
        return strResult;
    }
};
baseModule.prototype.string_replaceAll = function (str, search, rpl) {
    return str.split(search).join(rpl);
};





baseModule.prototype.tab_getTab = function (params) {
    var tab1 = '';
    var tab2 = '';
    var ini = '<div role="tabpanel"><ul class="nav nav-tabs" role="tablist">';
    var midi = '</ul><div class="tab-content">';
    var fini = '</div></div>';
    $.each(params, function (index, value) {
        if (index == 0) {
            tab1 += '<li role="presentation" class="active"><a href="#pane' + index + '" aria-controls="pane' + index + '" role="tab" data-toggle="tab">' + value.name + '</a></li>';
            tab2 += '<div role="tabpanel" id="pane' + index + '" class="tab-pane fade active in">' + value.content + '</div>';
        } else {
            tab1 += '<li role="presentation"><a href="#pane' + index + '" aria-controls="pane' + index + '" role="tab" data-toggle="tab">' + value.name + '</a></li>';
            tab2 += '<div role="tabpanel" id="pane' + index + '" class="tab-pane fade">' + value.content + '</div>';
        }
    });
    return (ini + tab1 + midi + tab2 + fini);
};


baseModule.prototype.table_getTable = function (headers, body) {
    return '<table class="table table-responsive table-bordered table-hover table-striped table-condensed dataTable no-footer">\
            <thead id="tableHeaders">' + headers + '</thead>\
            <tbody id="tableBody">' + body + '</tbody>\
            </table>';
}



baseModule.prototype.util_notifyException = function (errorStatus, errorMessage) {
    console.log("Error " + errorStatus + ": " + errorMessage);
    return "Error " + errorStatus + ": " + errorMessage;
};
baseModule.prototype.util_delay = function () {
    var timer = 0;
    return function (callback, ms) {
        clearTimeout(timer);
        timer = setTimeout(callback, ms);
    };
}











baseModule.prototype.validation_getFormValidationCode = function (jsonMeta) {
    var that=this;
    var matrix_form = _.map(jsonMeta, function (value, index) {
        if (value.IsIdForeignKey == false && value.IsObjForeignKey == false) {
            if (value.Type == "String") {
                return  function () {
                    $('#' + value.Name).keyup(function (event) {
                        that.validation_resetValidationControl(value.Name);
                        var controlValue = $('#' + value.Name).val();
                        if (that.validation_validateMinLength(value.MinLength, controlValue)) {
                            if (that.validation_validateMaxLength(value.MaxLength, controlValue)) {
                                that.validation_showValidationOKControl(value.Name);
                            } else {
                                that.validation_showValidationErrorControl(value.Name, 'El valor ha de tener como máximo ' + value.MaxLength + ' caracteres');
                            }
                        } else {
                            that.validation_showValidationErrorControl(value.Name, 'El valor ha de tener como mínimo ' + value.MinLength + ' caracteres');
                        }
                    })
                }
            }
            if (value.Type == "Boolean") {

            }
            if (value.Type == "Integer") {
                return  function () {
                    $('#' + value.Name).keyup(function (event) {
                        that.validation_resetValidationControl(value.Name);
                        var controlValue = $('#' + value.Name).val();
                        if (that.validation_validateInteger(controlValue)) {
                            if (that.validation_validateMaxInteger(value.MaxInteger, controlValue)) {
                                that.validation_showValidationOKControl(value.Name);
                            } else {
                                that.validation_showValidationErrorControl(value.Name, 'EL valor entero ha de ser menor que ' + value.MaxInteger);
                            }
                        } else {
                            that.validation_showValidationErrorControl(value.Name, 'El valor ha de ser un número entero');
                        }
                    })
                }
            }
            if (value.Type == "Date") {
                return  function () {
                    $('#' + value.Name).keyup(function (event) {
                        that.validation_resetValidationControl(value.Name);
                        var controlValue = $('#' + value.Name).val();
                        if (that.validation_validateSpanishDate(controlValue)) {
                            that.validation_showValidationOKControl(value.Name);
                        } else {
                            that.validation_showValidationErrorControl(value.Name, 'Introduzca una fecha válida');
                        }
                    })
                }
            }
        } else {
            if (value.IsObjForeignKey) {
                return '';
            }
        }
    });
    return matrix_form;
}
baseModule.prototype.validation_resetValidationForm = function () {
    $(".feedback").remove();
    $(".form-group").removeClass("has-success");
    $(".form-group").removeClass("has-error");
    $(".form-group").removeClass("has-feedback");
}
baseModule.prototype.validation_resetValidationControl = function (strIdAttr) {
    $("#" + strIdAttr + "-group .feedback").remove();
    $("#" + strIdAttr + "-group").removeClass("has-success");
    $("#" + strIdAttr + "-group").removeClass("has-error");
    $("#" + strIdAttr + "-group").removeClass("has-feedback");
}
baseModule.prototype.validation_showValidationErrorControl = function (strIdAttr, strMsg) {
    $("#" + strIdAttr + "-group").addClass("has-error");
    $("#" + strIdAttr + "-group").addClass("has-feedback");
    $("#" + strIdAttr + "-group .control").append('<span class="feedback glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
    $("#" + strIdAttr + "-group .control").append('<span class="feedback control-label" for="' + strIdAttr + '">' + strMsg + '</span>');
}
baseModule.prototype.validation_showValidationOKControl = function (strIdAttr) {
    $("#" + strIdAttr + "-group").addClass("has-success");
    $("#" + strIdAttr + "-group").addClass("has-feedback");
    $("#" + strIdAttr + "-group .control").append('<span class="feedback glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
}
baseModule.prototype.validation_validateLeapYear = function (year) {
    if ((year % 100 != 0) && ((year % 4 == 0) || (year % 400 == 0))) {
        return true;
    } else {
        return false;
    }
}
baseModule.prototype.validation_validateSpanishDate = function (strDate) {
    if (strDate != undefined && strDate.value != "") {
        strDate = strDate.replace(/-/g, '/');
        var expreg = /^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;
        if (!expreg.test(strDate)) {
            return false;
        }
        //var intYear = parseInt(strDate.substring(0, 4));
        //var strDay = strDate.substring(8, 10);
        //var strMonth = strDate.substring(5, 7);

        var intYear = parseInt(strDate.substring(6, 10));
        var strDay = strDate.substring(0, 2);
        var strMonth = strDate.substring(3, 5);


        switch (strMonth) {
            case "01":
            case "03":
            case "05":
            case "07":
            case "08":
            case "10":
            case "12":
                nDays = 31;
                break;
            case "04":
            case "06":
            case "09":
            case "11":
                nDays = 30;
                break;
            case "02":
                if (this.validation_validateLeapYear(intYear)) {
                    nDays = 29
                } else {
                    nDays = 28
                }
                ;
                break;
            default:
                return false;
        }

        if (strDay > nDays || strDay == 0) {
            return false;
        }
        return true;
    }
}
baseModule.prototype.validation_validateNotempty = function (strString) {
    if (strString) {
        return true
    } else {
        return false;
    }
}
baseModule.prototype.validation_validateEmail = function (strString) {
    var pattern = /^[a-zA-Z0-9\-_]+(\.[a-zA-Z0-9\-_]+)*@[a-z0-9]+(\-[a-z0-9]+)*(\.[a-z0-9]+(\-[a-z0-9]+)*)*\.[a-z]{2,4}$/;
    if (pattern.test(strString)) {
        return true;
    } else {
        return false;
    }
}
baseModule.prototype.validation_validateFloatNumber = function (strString) {
    var pattern = /^-?\d+(\.\d+)?$/;
    if (pattern.test(strString)) {
        return true;
    } else {
        return false;
    }
}
baseModule.prototype.validation_validateInteger = function (strString) {
    var pattern = /^-?\d+$/;
    if (pattern.test(strString)) {
        return true;
    } else {
        return false;
    }
}
baseModule.prototype.validation_validateMaxInteger = function (intMaxInteger, intInteger) {
    if (intInteger > intMaxInteger) {
        return false;
    } else {
        return true;
    }
}
baseModule.prototype.validation_validateMaxLength = function (intMaxLength, strString) {
    if (strString.length > intMaxLength) {
        return false;
    } else {
        return true;
    }
}
baseModule.prototype.validation_validateMinLength = function (intMinLength, strString) {
    if (strString.length < intMinLength) {
        return false;
    } else {
        return true;
    }
}
baseModule.prototype.validation_loadValidationCallbacks = function (meta) {
    this.validation_resetValidationForm();
    var validationFunctionArray = this.validation_getFormValidationCode(meta);
    _.each(validationFunctionArray, function (validationFunction) {
        if (typeof (validationFunction) === "function") {
            validationFunction();
        }
        ;
    });
}
baseModule.prototype.validation_okValidation = function (f) {
    $('#' + this.clase + 'Form').on('success.form.bv', f);
}
