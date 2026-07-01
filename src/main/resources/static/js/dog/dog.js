function openForm() {
    document.querySelector(".container").classList.add("open");
}

function closeForm() {
    document.querySelector(".container").classList.remove("open");
}

function dogCreateForm() {
    window.location.href = contextPath + "/users/dogs/regForm";
}

function saveDog() {
    document.querySelector(".dog-form").submit();
}

function dogEditForm(id) {
    window.location.href = contextPath + "/users/dogs/editForm/" + id;
}

function changePreview(input) {
    const file = input.files[0];
    if (!file) {
        return;
    }
    const reader = new FileReader();
    reader.onload = function (e) {
        document.getElementById("preview").src = e.target.result;
    };
    reader.readAsDataURL(file);
}

function deleteDog(id) {
    if(confirm("반려견 프로필을 삭제하시겠습니까?")) {
        window.location.href = contextPath + "/users/dogs/delete/" + id;
    } else {
        return;
    }
}