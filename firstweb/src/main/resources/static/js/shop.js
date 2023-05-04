

function logout() {
    window.location.href = "/users/login";
}

// 開啟、關閉 子欄位
const opens = document.querySelectorAll(".open");
const menus = document.querySelectorAll(".openMenu");

opens.forEach((open, index) => {

    open.addEventListener("click", () => {

        menus[index].classList.toggle("ok");

    });

});

// 下拉式選單 頁數、屬性 選擇後，不要重置為 null 欄位
function doOnload() {

    var tempHidPage = document.getElementById("hidPage").textContent;
    document.getElementById("page").value = tempHidPage;

    var tempHidCategory = document.getElementById("hidCategory").textContent;
    document.getElementById("category").value = tempHidCategory;

    var tempHidErrorMsg = document.getElementById("hidErrorMsg").textContent;
    if(tempHidErrorMsg == "Y"){

        document.getElementById("errorMsg").hidden = false;
        document.getElementById("msg-text").classList.add("show-text");

        // 控制訊息顯示時間，預設5秒後消失
        timeUp();
    }else{
        document.getElementById("errorMsg").hidden = true;
    }
}

// 下拉選單 選擇後 自動 submit 向後台傳遞參數
function reflashPage() {
    document.getElementById("search-reflash").submit();
}

// 倒計時器
var count = 5;
function timeUp() {

    count -= 1;

    if (count == 0) {
        document.getElementById("msg-text").classList.remove("show-text");
    }

    setTimeout("timeUp()", 1000);

    if (count < 0) {
        count = 0;
    }
}

$(".btn-add").click(function () {

    $(".btn-add").text("已加入購物車!");
})