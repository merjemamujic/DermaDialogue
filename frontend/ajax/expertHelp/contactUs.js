var ContactFormModule = (function () {
    // Private variables and functions
    var saveContactForm = function () {
        var formData = {
            firstName: $('#firstName').val(),
            lastName: $('#lastName').val(),
            email: $('#email').val(),
            subject: $('#subject').val()
        };

        $.ajax({
            type: 'POST',
            url: 'http://localhost/skincare/backend/support_request',
            data: formData,
            success: function (response) {
                handleSuccessResponse(response);
                redirectToEmail(response.email);
            },
            error: function (xhr, status, error) {
                handleErrorResponse(xhr, status, error);
            }
        });
    };

    var handleSuccessResponse = function (response) {
        alert("The request is stored in our database! Please procced to the redirected mail.");
        $('#contactModal').modal('hide');
    };

    var handleErrorResponse = function (xhr, status, error) {
        console.error(error);
        alert('Failed to save contact form.');
    };

    var redirectToEmail = function (email) {
        var subject = encodeURIComponent("Request for skin care products");
        var body = encodeURIComponent($('#subject').val());
        var email = encodeURIComponent("skincare@gmail.com")
        window.location.href = "mailto: " + email + "?subject=" + subject + "&body=" + body;
    };

    // Public API
    return {
        init: function () {
            $('#saveContactForm').click(saveContactForm);
        }
    };
})();

$(document).ready(function () {
    ContactFormModule.init();
});
