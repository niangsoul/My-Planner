const loginForm = document.querySelector('form');

loginForm.addEventListener('submit', e => {
    e.preventDefault(); // 기본 submit 막기

    const formData = new URLSearchParams();
    formData.append('email', document.getElementById('email').value);
    formData.append('password', document.getElementById('password').value);

    fetch('/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: formData.toString()
    })
        .then(res => res.text())
        .then(data => {
            alert(data);
            if (data === "로그인 성공!") {
                window.location.href = "/"; // 메인 페이지로 이동
            }
        });
});