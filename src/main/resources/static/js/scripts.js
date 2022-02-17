function addCheckedIdsToUrl() {
    let checkboxes = document.getElementsByClassName('checkedId')
    let ids = new Array();
    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            ids.push(checkboxes[i].value);
        }
    }
    location.href = 'compare/' + ids.join('&')
}