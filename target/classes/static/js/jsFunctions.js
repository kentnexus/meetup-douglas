/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
  }
  
  // Close the dropdown menu if the user clicks outside of it
  window.onclick = function(event) {
    if (!event.target.matches('.arrow')) {
      var dropdowns = document.getElementsByClassName("dropdown-content");
      var i;
      for (i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains('show')) {
          openDropdown.classList.remove('show');
        }
      }
    }
  }

function groupEdit(e) {
    const id = e.dataset.id;
    const name = e.dataset.name;
    const description = e.dataset.description;
    const isOwner = e.dataset.owner;

    if(isOwner === "true"){
        document.getElementById("eventEditForm").reset();
        document.getElementById("editMode").style.display="";
        document.getElementById("gName").innerText=name;
        document.getElementById("gDescription").innerText=description;
        document.getElementById("gId").defaultValue=id;
    }
    else{
        alert("You are not the group owner.")
    }
}

function eventEdit(e){
    const id = e.dataset.id;
    const name = e.dataset.name;
    const schedule = e.dataset.schedule;
    const timeStart = e.dataset.tstart;
    const timeEnd = e.dataset.tend;
    const description = e.dataset.description;
    const organizer = e.dataset.organizer;
    const isOrganizer = e.dataset.org;

    if(isOrganizer === "true"){
        document.getElementById("eventEditForm").reset();
        document.getElementById("editMode").style.display="";
        document.getElementById("eName").innerText=name;
        document.getElementById("eSchedule").innerText=schedule;
        document.getElementById("eTimeStart").innerText=timeStart;
        document.getElementById("eTimeEnd").innerText=timeEnd;
        document.getElementById("eDetails").innerText=description;
        document.getElementById("eOrganizer").innerText=organizer;
        document.getElementById("eId").defaultValue=id;
    }
    else{
        alert("You are not the organizer.")
        document.getElementById("editBtn").disabled = true;
    }
}

function dispHide(){
  document.getElementById("editMode").style.display="none";
  // document.getElementById("eventEditForm").reset();
}

function successGroup(){
    alert("The group has been saved.");
}

function isOwnerLeave(e){
    let isowner = e.dataset.owner;

    if(isowner==="true"){
        alert("You are the group owner. You can delete the group instead.")
        document.getElementById("leaveBtn").disabled = true;
        return false;
    } else {
       let c = confirm('Are you sure you want to leave the group?');
       if (c===true){
           return true;
       } else {
           return false;
       }
    }
}

function isOwnerDelete(e){
    let isowner = e.dataset.owner;

    if(isowner==="true"){
        let c = confirm('Are you sure you want to delete the group?');
        if(c === true){
            return true;
        }
        else {
            return false;
        }
    } else {
        alert("You are not the group owner.")
        document.getElementById("delBtn").disabled = true;
        return false;
    }
}

function isLeaveEvent(e){
    let isowner = e.dataset.owner;

    if(isowner==="true"){
        alert("You are the event organizer. You can delete the event instead.")
        document.getElementById("leaveBtn").disabled = true;
        return false;
    } else {
        let c = confirm('Are you sure you want to leave the event?');
        if (c===true){
            return true;
        } else {
            return false;
        }
    }
}

function isDeleteEvent(e){
    let isowner = e.dataset.owner;

    if(isowner==="true"){
        let c = confirm('Are you sure you want to delete the event?');
        if(c === true){
            return true;
        }
        else {
            return false;
        }
    } else {
        alert("You are not the event organizer.")
        document.getElementById("delBtn").disabled = true;
        return false;
    }
}

function greetings(e){
    let name = e.dataset.name;
    let greetings = "Hello, "+name;
    document.getElementById("greetings").innerHTML=greetings;
}

function gr(){

}