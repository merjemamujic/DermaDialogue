var ValidationModule = (function () {
    var validateForm = function (firstName, email, password, lastName) {
        var errorMessage = "";

        if (firstName === "" || lastName === "") {
            errorMessage = "Please enter your first name and last name.";
            document.getElementById("firstNameError").textContent = errorMessage;
            document.getElementById("lastNameError").textContent = errorMessage;
        } else {
            document.getElementById("firstNameError").textContent = "";
            document.getElementById("lastNameError").textContent = "";
        }

        if (!validateEmail(email)) {
            errorMessage = "Please enter a valid email address.";
            document.getElementById("emailError").textContent = errorMessage;
        } else {
            document.getElementById("emailError").textContent = "";
        }

        if (password === "") {
            errorMessage = "Please enter a password.";
            document.getElementById("passwordError").textContent = errorMessage;
        } else {
            document.getElementById("passwordError").textContent = "";
        }

        return errorMessage;
    };

    var validateEmail = function (email) {
        // Email validation regex pattern
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailPattern.test(email);
    };

    return {
        validateForm: validateForm
    };
})();
