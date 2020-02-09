window.notify = function(message) {
    $.notify(message, {className: "success", position: "bottom right"})
};

window.ajax = function($actionName, field1name, field1, field2name, field2, $error) {
    var data = {};
    data[field1name] = field1;
    data[field2name] = field2;
    data["action"] = $actionName;
    $.ajax({
        type: "POST",
        url: "",
        dataType: "json",
        data: data,
        success: function (response) {
            if (response["error"]) {
                $error.text(response["error"]);
            } else {
                location.href = response["redirect"];
            }
        }
    });
};

window.myAjax = function(data, success) {
    $.ajax({
        type: "POST",
        url: "",
        dataType: "json",
        data: data,
        success: function (response) {
            success(response);
            if (response["redirect"]) {
                location.href = response["redirect"];
            }
        }
    });
};
