var SUCCESS = "0";
var FAIL = "1";
var ALERT_TIME = 1500;

/**
 * 显示alert并一定时间后消失
 * formAlert 格式：
 <div id="formAlert" class="alert hidden" role="alert"></div>
 */
function showAlert(parameters) {
    var $formAlert = parameters.$formAlert;
    var data = parameters.data;
    var callback = parameters.callback;

    var status = data.status;
    var message = data.message;
    if (status == SUCCESS) {
        $formAlert.removeClass("alert-danger");
        $formAlert.addClass("alert-success");
    } else {
        $formAlert.addClass("alert-danger");
    }
    $formAlert.show();
    $formAlert.removeClass("hidden");
    $formAlert.text(message);
    setTimeout(function () {
        $formAlert.fadeOut(500, function () {
            if (callback != undefined && status == SUCCESS) {
                callback(data);
            }
        });
    }, ALERT_TIME);
}

/**
 * ajax请求，并在alert上显示返回消息
 * formAlert 格式：
 <div id="formAlert" class="alert hidden" role="alert"></div>
 * @param url           必填
 * @param params        选填 默认 $('#ajaxForm') 表单字段
 * @param $formAlert    选填 默认 $("#formAlert")
 * @param callback      选填 成功后调用
 */
function myAjaxForm(parameters) {
    var url = parameters.url;
    var params = parameters.params;
    var $formAlert = parameters.$formAlert;
    var callback = parameters.callback;

    if (params == undefined)
        params = $("#ajaxForm").serialize();
    if ($formAlert == undefined)
        $formAlert = $("#formAlert");
    $.ajax({
        type: "post",
        url: url,
        data: params,
        dataType: "json",
        success: function (data) {
            if (callback != undefined) {
                showAlert({
                    $formAlert: $formAlert,
                    data: data,
                    callback: callback
                });
            } else {
                showAlert({$formAlert: $formAlert, data: data});
            }
        }
    });
}


/**
 * 字段验证初始化
 * @param formId
 * @param jsonUrl
 * @param callback
 */
function dataBind(formId, jsonUrl, callback) {
    var $form = $('#' + formId);
    $form.bind('submit', function (e) {
        // Ajax validation
        var $inputs = $form.find('input');
        var data = collectFormData($inputs);

        $.post(jsonUrl, data, function (data) {
            $form.find('.form-group').removeClass('error');
            $form.find('.help-inline').empty();

            var $formAlert = $("#formAlert");
            if (data.status == SUCCESS || data.errors == null) {
                showAlert({
                    $formAlert: $formAlert,
                    data: data,
                    callback: callback
                });
                // $form.unbind('submit');
                return false;
            } else {
                for (var i = 0; data.errors != null && i < data.errors.length; i++) {
                    var item = data.errors[i];
                    var $controlGroup = $('#' + item.field);
                    $controlGroup.addClass('error');
                    $controlGroup.find('.help-inline').html(item.defaultMessage);
                }
            }
        }, 'json');
        e.preventDefault();
        return false;
    });
};

function collectFormData(fields) {
    var data = {};
    for (var i = 0; i < fields.length; i++) {
        var $item = $(fields[i]);
        data[$item.attr('name')] = $item.val();
    }
    return data;
}
