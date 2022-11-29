function loadF(e) {
    let isadmin = e.dataset.isadmin;
    console.log(isadmin);
    if( isadmin === "true") {
        document.getElementById("joinBtn").value = "Joined";
        document.getElementById("joinBtn").disabled = true;
    }
}

function loadEvent(e) {
    let isadmin = e.dataset.isadmin;
    let isgrpmember = e.dataset.grpmember;
    console.log(isadmin);

    if(isadmin === "true" && isgrpmember === "false") {
        document.getElementById("joinBtn").value = "Join Group";
        document.getElementById("joinBtn").disabled = true;
    } else if( isadmin === "true") {
        document.getElementById("joinBtn").value = "Joined";
        document.getElementById("joinBtn").disabled = true;
    }
}