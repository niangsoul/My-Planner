// 모달
const modal = document.getElementById('modal');
const modalText = document.getElementById('modal-text');
const modalClose = document.getElementById('modal-close');

modalClose.addEventListener('click', () => {
    modal.style.display = 'none';
});

// Step1: 휴대폰 인증번호 발송
document.getElementById('send-phone-code').addEventListener('click', () => {
    const phone = document.getElementById('phone').value;
    if (!phone) { alert('휴대폰 번호를 입력해주세요.'); return; }

    fetch('/send-phone-code', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `phone=${encodeURIComponent(phone)}`
    })
        .then(res => res.text())
        .then(data => {
            modalText.textContent = data;
            modal.style.display = 'flex';
        });
});

// Step1: 휴대폰 인증 확인
document.getElementById('verify-phone').addEventListener('click', () => {
    const phone = document.getElementById('phone').value;
    const code = document.getElementById('phone-code').value;
    if (!phone || !code) { alert('휴대폰 번호와 인증번호를 입력해주세요.'); return; }

    fetch('/verify-phone-code', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `phone=${encodeURIComponent(phone)}&code=${encodeURIComponent(code)}`
    })
        .then(res => res.text())
        .then(data => {
            modalText.textContent = data;
            modal.style.display = 'flex';
            if (data === "인증 성공") {
                document.getElementById('step1').style.display = 'none';
                document.getElementById('step2').style.display = 'block';
            }
        });
});

// Step2: 이메일 인증번호 발송
document.getElementById('send-email-code').addEventListener('click', () => {
    const email = document.getElementById('email').value;
    if (!email) { alert('이메일을 입력해주세요.'); return; }

    fetch('/send-email-code', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `email=${encodeURIComponent(email)}`
    })
        .then(res => res.text())
        .then(data => {
            modalText.textContent = data;
            modal.style.display = 'flex';
        });
});

// Step2: 이메일 인증 확인
document.getElementById('verify-email').addEventListener('click', () => {
    const email = document.getElementById('email').value;
    const code = document.getElementById('email-code').value;
    if (!email || !code) { alert('이메일과 인증번호를 입력해주세요.'); return; }

    fetch('/verify-email-code', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `email=${encodeURIComponent(email)}&code=${encodeURIComponent(code)}`
    })
        .then(res => res.text())
        .then(data => {
            modalText.textContent = data;
            modal.style.display = 'flex';
        });
});

// Step2: 회원가입 완료
document.getElementById('signup-submit').addEventListener('click', () => {
    const password = document.getElementById('password').value;
    const passwordConfirm = document.getElementById('password-confirm').value;
    if (password !== passwordConfirm) { alert('비밀번호가 일치하지 않습니다.'); return; }

    const params = new URLSearchParams();
    params.append('phone', document.getElementById('phone').value);
    params.append('email', document.getElementById('email').value);
    params.append('password', password);
    params.append('name', document.getElementById('name').value);
    params.append('nickname', document.getElementById('nickname').value);
    params.append('age', document.getElementById('age').value);
    params.append('gender', document.getElementById('gender').value);

    fetch('/signup', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: params.toString()
    })
        .then(res => res.text())
        .then(data => {
            alert(data);
            if (data === "회원가입 완료!") window.location.href = "/login";
        });
});
