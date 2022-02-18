function addCheckedIdsToUrl() {
    let checkboxes = document.getElementsByClassName('checkedId')
    let ids = new Array();
    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            ids.push(checkboxes[i].value);
        }
    }
    location.href = ids.join('&');
}

function fillTodaysDate() {
    const today = new Date();
    document.getElementById('dateForm').valueAsDate = (today.getMonth() + 1) + '/' + today.getDate() + '/' + today.getFullYear();
}

function fillLoggedUser() {
    document.getElementById('usernameForm').value = '[[${#request.userPrincipal.principal.fullName}]]';
}