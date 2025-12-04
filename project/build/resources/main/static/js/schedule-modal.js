const addBtn = document.getElementById('add-schedule');
const modal = document.createElement('div');
modal.innerHTML = `
<div id="schedule-modal" style="display:none;position:fixed;top:0;left:0;width:100%;height:100%;background:rgba(0,0,0,0.5);justify-content:center;align-items:center;z-index:1000;">
    <div class="modal-content" style="background:#fff;padding:20px;border-radius:8px;width:300px;">
        <h3>일정 추가</h3>
        <label>제목</label><input type="text" id="schedule-title">
        <label>내용</label><textarea id="schedule-content"></textarea>
        <label>날짜</label><input type="date" id="schedule-date">
        <button id="save-schedule">저장</button>
        <button id="close-modal">취소</button>
    </div>
</div>`;
document.body.appendChild(modal);

const scheduleModal = document.getElementById('schedule-modal');
const closeModal = document.getElementById('close-modal');
const saveBtn = document.getElementById('save-schedule');

addBtn.addEventListener('click', () => scheduleModal.style.display = 'flex');
closeModal.addEventListener('click', () => scheduleModal.style.display = 'none');

saveBtn.addEventListener('click', () => {
    const title = document.getElementById('schedule-title').value;
    const content = document.getElementById('schedule-content').value;
    const date = document.getElementById('schedule-date').value;

    if(title && date){
        schedules.push({title, content, date});
        scheduleModal.style.display = 'none';
        document.getElementById('schedule-title').value = '';
        document.getElementById('schedule-content').value = '';
        document.getElementById('schedule-date').value = '';
        renderCalendar(currentMonth, currentYear); // 캘린더 갱신
    } else {
        alert('제목과 날짜를 입력해주세요.');
    }
});
