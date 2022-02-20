function addCheckedIdsToUrl(currentObject) {
    let checkboxes = document.getElementsByClassName('checkedId')
    let ids = new Array();
    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            ids.push(checkboxes[i].value);
        }
    }
    location.href = "http://localhost:8080/" + currentObject + "/details?ids=" + ids.join(',');
}

function checkIfPasswordsMatch() {
    if (document.getElementById('pass2').value != document.getElementById('pass1').value) {
        document.getElementById('pass2').value = '';
        document.getElementById('pass1').value = '';
        alert('Passwords must match');
    }
}