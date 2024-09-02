function clearLoginmsg() {
    const loginMsg = document.getElementById('error-msg');

    if (loginMsg) {
        loginMsg.style.display = 'none';
    }
}
