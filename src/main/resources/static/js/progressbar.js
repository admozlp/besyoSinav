function move() {
  var elem = document.getElementById("myBar");
  var width = 0;
  var id = setInterval(frame, 55);

  function frame() {
    if (width >= 100) {

      var confirmButton = document.getElementById("confirm");
      confirmButton.className = "btn btn-success";
      confirmButton.innerHTML = "<i class='fas fa-coins'></i> Got Rewards!";
      confirmButton.removeAttribute('disabled');
      confirmButton.setAttribute('href', 'https://indaesa.com');
      clearInterval(id);
    } else {
      width++;
      elem.style.width = width + '%';
      elem.innerHTML = width * 1 + '%';
    }
  }
}/**
 * 
 */