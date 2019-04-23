function exit() {
    window.location.href = "jsp/logout.jsp";
}
function upload(id) {
    document.getElementById(id).setAttribute("name", "good_id");
    document.getElementById(id).setAttribute("value", id);
    document.form.submit();
}
function change_button(id) {
    var submit_id = "submit_btn_" + id;
    // var file_btn_id = "file_btn_" + id;
    // var file_cont_id = "file_cont_"+ id;
    document.getElementById(submit_id).className+="-new";
    document.getElementById(submit_id).setAttribute("onclick","upload(this.getAttribute('name'))");
    // document.getElementById(file_btn_id).className+="-new";
    // document.getElementById(file_btn_id).setAttribute("value","取消");
    // document.getElementById(file_cont_id).style.visibility="hidden";
}
function btn_color(id){
    var i = "file_btn_"+id;
    document.getElementById(i).style.background="rgb(240, 240, 240)";
}
function re_btn_color(id){
    var i = "file_btn_"+id;
    document.getElementById(i).style.background="rgb(249, 249, 249)";
}